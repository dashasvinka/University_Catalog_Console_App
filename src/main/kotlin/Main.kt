import api.UniversityApi
import cui.UniversityManager
import database.Connection
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun main(args: Array<String>) {

    // подключаемся к базе данных
    Connection.createConnection()

    // получаем от юзера страну для поиска университетов
    val manager: UniversityManager = UniversityManager()
    val country = manager.getCountryToSearchUniversity()

    // получаем данные с  API-ки и сохраняем в БД
    val uni: UniversityApi = UniversityApi()
    uni.loadAndSaveUniversities(country)

    // возвращаем юзеру информацию об университетах в указанной стране
    val universities =
        transaction {
            UniversityTable
                .selectAll()
                .where { UniversityTable.country eq country }
                .toList()
        }
    println(universities)
}