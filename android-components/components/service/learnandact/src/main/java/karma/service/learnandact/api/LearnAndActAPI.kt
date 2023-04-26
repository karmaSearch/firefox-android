package karma.service.learnandact.api


internal data class LearnAndActApi(val id: Int,
                                   val contentType : String,
                                   val imageUrl : String,
                                   val title : String,
                                   val content : String,
                                   val destinationUrlLabel : String,
                                   val destinationUrl : String)
