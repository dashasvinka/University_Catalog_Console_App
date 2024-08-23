import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.json.jsonb

/*@Serializable
data class DomainItem(val domain: String)

@Serializable
data class WebItem(val domain: String)*/
object UniversityTable: IdTable<Int>("university_data"){
    override val id = integer("id").entityId()
    val name = varchar("name", 255)
    val country = varchar("country", 255)
    val state_province = varchar("state_province", 500).nullable()
    val alpha_two_code = varchar("alpha_two_code", 255)
    val domains = jsonb<List<String>>("domains", Json {ignoreUnknownKeys = true})
    val web_pages = jsonb<List<String>>("web_pages", Json {ignoreUnknownKeys = true})
}