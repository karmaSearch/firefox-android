package karma.service.learnandact.db

import androidx.room.*


@Dao
internal interface LearnAndActDao {
    /**
     * Add new stories to the database.
     * Stories already existing will not be updated in any way.
     * Already persisted blocs but not present in [blocs] will be removed from the database.
     *
     * @param learn and act new list of [LearnAndActEntity]s to replace the currently persisted ones.
     */
    @Transaction
    suspend fun cleanOldAndInsertNewLearnAndAct(stories: List<LearnAndActEntity>) {
        val old = getLearnAndAct()
        delete(old)
        insertLearnAndAct(stories)
    }

    @Query("SELECT * FROM ${LearnAndActDatabase.TABLE_NAME_STORIES}")
    suspend fun getLearnAndAct(): List<LearnAndActEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLearnAndAct(stories: List<LearnAndActEntity>)

    @Delete
    suspend fun delete(stories: List<LearnAndActEntity>)
}
