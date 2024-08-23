package database

import org.jetbrains.exposed.sql.Database

class Connection {
    companion object{
        fun createConnection() {
            Database.connect(
                url = "jdbc:postgresql://localhost:5432/university",
                driver = "org.postgresql.Driver",
                user = "postgres",
                password = "changeme"
            )
        }
    }
}