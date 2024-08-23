package api

import University
import UniversityTable
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class UniversityApi {
    fun loadAndSaveUniversities(country: String) {
        runBlocking {
            val client = HttpClient(CIO) {
                install(ContentNegotiation) {
                    json()
                }
            }
            val response: List<University> = client.get("http://universities.hipolabs.com/search") {
                parameter("country", country)
            }.body()
            transaction {
                addLogger(StdOutSqlLogger)
                UniversityTable.deleteWhere { UniversityTable.country eq country}
                UniversityTable.batchInsert(response) {university ->
                    this[UniversityTable.name] = university.name
                    this[UniversityTable.country] = university.country
                    this[UniversityTable.state_province] = university.state
                    this[UniversityTable.alpha_two_code] = university.alpha_two_code
                    this[UniversityTable.domains] = university.domains
                    this[UniversityTable.web_pages] = university.web_pages
                }
            }
        }
    }
}