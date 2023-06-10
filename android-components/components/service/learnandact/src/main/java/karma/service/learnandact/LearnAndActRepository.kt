package karma.service.learnandact

import android.content.Context
import androidx.annotation.VisibleForTesting
import karma.service.learnandact.api.LearnAndActApi
import karma.service.learnandact.db.LearnAndActDatabase
import karma.service.learnandact.db.LearnAndActEntity

internal class LearnAndActRepository(context: Context) {
    private val database: Lazy<LearnAndActDatabase> = lazy { LearnAndActDatabase.get(context) }
    @VisibleForTesting
    internal val learnAndActsDao by lazy { database.value.learnAndActDao() }

    /**
     * Get the current locally persisted list of Pocket recommended articles.
     */
    suspend fun getLearnAndActBlocs(): List<LearnAndAct> {
        return learnAndActsDao.getLearnAndAct().sortedWith(compareByDescending<LearnAndActEntity> { it.publishedDate }.thenByDescending { it.id })
        .map {
            it.toLearnAndAct() }
    }
    /**
     * Replace the current list of locally persisted Pocket recommended articles.
     *
     * @param stories The list of Pocket recommended articles to persist locally.
     */
    suspend fun addAllLearnAndAct(objects: List<LearnAndActApi>) {
        learnAndActsDao.cleanOldAndInsertNewLearnAndAct(objects.map { it.toLearnAndActEntity() })
    }

}
