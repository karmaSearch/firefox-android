package karma.service.learnandact.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LearnAndActEntity::class], version = 1)
internal abstract class LearnAndActDatabase : RoomDatabase() {
    abstract fun learnAndActDao(): LearnAndActDao

    companion object {
        private const val DATABASE_NAME = "learn_and_act_db"
        const val TABLE_NAME_STORIES = "learn_and_act"

        @Volatile
        private var instance: LearnAndActDatabase? = null

        @Synchronized
        fun get(context: Context): LearnAndActDatabase {
            instance?.let { return it }

            return Room.databaseBuilder(
                context,
                LearnAndActDatabase::class.java,
                DATABASE_NAME
            )
                .build().also {
                    instance = it
                }
        }
    }
}