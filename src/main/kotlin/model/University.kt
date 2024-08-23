import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class University(
     val name: String,
     val country: String,
     @SerialName("state-province") val state: String?,
     val alpha_two_code: String,
     val domains: List<String>,
     val web_pages: List<String>
)

@Serializable
data class UniversityResponse(
     val hits: List<University>
)