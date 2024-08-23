package cui

class UniversityManager {
    fun getCountryToSearchUniversity(): String {
        println("Введите страну для получения списка университетов:")
        val country = readLine()!!.trim()
        return country
    }
}