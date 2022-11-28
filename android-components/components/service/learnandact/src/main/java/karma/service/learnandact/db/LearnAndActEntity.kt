package karma.service.learnandact.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = LearnAndActDatabase.TABLE_NAME_STORIES)
class LearnAndActEntity(
    @PrimaryKey
    val id: Int,
    val type: String,
    val title: String,
    val description: String,
    val action: String,
    val actionUrl: String,
    val duration: String,
    val imageUrl: String)
