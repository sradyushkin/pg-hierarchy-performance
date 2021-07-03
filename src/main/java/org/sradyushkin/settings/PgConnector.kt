package org.sradyushkin.settings

import org.sradyushkin.generate.DataGenerator
import java.sql.Connection
import java.sql.DriverManager
import java.util.*

class PgConnector {
    private val dbProp: String = "liquibase/liquibase.properties"

    fun getConnection(): Connection {
        val credentials = Properties()
        credentials["user"] = getPropertyByKey("username")
        credentials["password"] = getPropertyByKey("password")
        val connection = DriverManager.getConnection(getPropertyByKey("url"), credentials)
        connection.autoCommit = false
        return connection
    }

    private fun getPropertyByKey(key: String): String {
        val prop = Properties()
        prop.load(DataGenerator::class.java.getResourceAsStream("/$dbProp"))
        return prop.getProperty(key)
    }
}