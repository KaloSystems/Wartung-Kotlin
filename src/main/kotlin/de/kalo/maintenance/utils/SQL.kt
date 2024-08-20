package de.kalo.maintenance.utils

import de.kalo.maintenance.utils.manager.SQLManager
import java.sql.Connection
import java.sql.DriverManager
import java.sql.DriverManager.println
import java.sql.ResultSet
import java.sql.SQLException

class SQL {

    private var connection: Connection? = null

    fun connect(
        hostname: SQLManager,
        port: SQLManager,
        database: SQLManager,
        username: SQLManager,
        password: SQLManager
    ) {
        try {
            connection = DriverManager.getConnection(
                "jdbc:mysql://$hostname:$port/$database",
                username.toString(),
                password.toString()
            )
            println("Die Verbindung mit MySQL wurde hergestellt!")
        } catch (e: SQLException) {
            println("Die Verbindung mit MySQL ist fehlgeschlagen! Fehler: " + e.message)
        }
    }

    fun close() {
        try {
            if (connection != null) {
                connection!!.close()
                println("Die Verbindung mit MySQL wurde beendet!")
            }
        } catch (e: SQLException) {
            println("§cDie Verbindung mit MySQL konnte nicht beendet werden! Fehler: " + e.message)
        }
    }

    fun getConnection(): Connection? {
        return connection
    }

    fun update(query: String?) {
        if (getConnection() != null) try {
            val st = getConnection()!!.createStatement()
            st.executeUpdate(query)
            st.close()
        } catch (e: SQLException) {
            System.err.print(e)
        }
    }

    fun query(query: String?): ResultSet? {
        if (getConnection() != null) {
            var rs: ResultSet? = null
            try {
                val st = getConnection()!!.createStatement()
                rs = st.executeQuery(query)
            } catch (e: SQLException) {
                System.err.print(e)
            }
            return rs
        }
        return null
    }

    fun createTable(table: String, table_values: String) {
        update("CREATE TABLE IF NOT EXISTS $table($table_values)")
    }

}