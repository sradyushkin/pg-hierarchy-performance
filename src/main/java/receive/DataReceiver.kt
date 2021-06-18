package receive

import settings.PgConnector
import java.sql.Connection
import java.sql.ResultSet

class DataReceiver {

    private val time = "Execution Time:"

    fun perform() {
        val connection = PgConnector().getConnection()
        receiveFirstTable(connection)
        receiveSecondTable(connection)
        connection.close()
    }

    private fun receiveFirstTable(connection: Connection) {
        println("Receive data from ltree table")

        val query = "EXPLAIN ANALYZE SELECT count(*) FROM org_structure_tree WHERE tree ~ '*'"

        parseExecutionTime(connection.createStatement().executeQuery(query))
    }

    private fun receiveSecondTable(connection: Connection) {
        println("Receive data from simple link table")

        val query =
            "EXPLAIN ANALYZE WITH RECURSIVE r AS (" +
                    "SELECT " +
                        "id, " +
                        "name, " +
                        "parent_id " +
                    "FROM org_structure " +
                    "UNION " +
                    "SELECT " +
                        "o.id, " +
                        "o.name, " +
                        "o.parent_id " +
                    "FROM org_structure o " +
                    "INNER JOIN r ON r.id = o.parent_id" +
                    ") " +
                    "SELECT count(*) FROM r;"

        parseExecutionTime(connection.createStatement().executeQuery(query))
    }

    private fun parseExecutionTime(rs: ResultSet) {
        while (rs.next()) {
            val plan = rs.getString("QUERY PLAN")
            if (plan.startsWith(time)) {
                println(plan)
            }
        }
    }
}