import java.util.*

class DataGenerator {

    private val appProp: String = "application.properties"

    fun perform() {
        fillFirstTable()
        fillSecondTable()
    }

    private fun fillFirstTable() {
        println("Generate rows for Ltree table")
    }

    private fun fillSecondTable() {
        println("Generate rows for simple link table")
    }

    private fun getMaxNumber(): Int {
        val prop = Properties()
        prop.load(DataGenerator::class.java.getResourceAsStream(appProp))
        return Integer.parseInt(prop.getProperty("max.row.number"))
    }
}