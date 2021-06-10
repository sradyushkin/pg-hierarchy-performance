package generate

import settings.PgConnector
import java.sql.Connection
import java.sql.Types
import java.util.*

class DataGenerator {

    private val appProp: String = "application.properties"

    fun perform() {
        val connection = PgConnector().getConnection()
        fillFirstTable(connection)
        fillSecondTable(connection)
        connection.close()
    }

    private fun fillFirstTable(connection: Connection) {
        println("Generate rows for Ltree table")
        val maxNumber = getMaxNumber()
        var i: Int = 1
        val name = "name"

        val prepareStatement = connection.prepareStatement("INSERT into org_structure_tree(name, tree) VALUES (?, ?);")

        while (i <= maxNumber) {
            val tree = i - 1;
            prepareStatement.setString(1, name + "_$i")
            prepareStatement.setObject(2, if (tree > 0) "$i.$tree" else i.toString(), Types.OTHER)
            prepareStatement.addBatch()
            i++
        }

        prepareStatement.executeBatch()
        connection.commit()
    }

    private fun fillSecondTable(connection: Connection) {
        println("Generate rows for simple link table")

        val maxNumber = getMaxNumber()
        var i: Int = 1
        val name = "name"

        val prepareStatement = connection.prepareStatement("INSERT into org_structure(name, parent_id) VALUES (?, ?);")

        while (i <= maxNumber) {
            val parentId = i - 1;
            prepareStatement.setString(1, name + "_$i")
            prepareStatement.setObject(2, if (parentId > 0) parentId else null)
            prepareStatement.addBatch()
            i++
        }

        prepareStatement.executeBatch()
        connection.commit()
    }

    private fun getMaxNumber(): Int {
        val prop = Properties()
        prop.load(DataGenerator::class.java.getResourceAsStream("../$appProp"))
        return Integer.parseInt(prop.getProperty("max.row.number"))
    }
}