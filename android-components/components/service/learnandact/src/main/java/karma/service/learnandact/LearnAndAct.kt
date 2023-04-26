package karma.service.learnandact

data class LearnAndAct constructor(val id: Int, val type: LearnAndActContentType, val title: String, val description: String, val action: String, val actionUrl: String, val imageUrl: String)

enum class LearnAndActContentType {
    NEWS,
    VICTORY,
    ACT,
    LEARN,
    UNDEFINED
}
