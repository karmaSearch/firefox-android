package karma.service.learnandact

import android.content.Context
import androidx.annotation.VisibleForTesting
import karma.service.learnandact.api.LearnAndActApi
import karma.service.learnandact.db.LearnAndActDatabase

internal class LearnAndActRepository(context: Context) {
    private val database: Lazy<LearnAndActDatabase> = lazy { LearnAndActDatabase.get(context) }
    @VisibleForTesting
    internal val pocketRecommendationsDao by lazy { database.value.learnAndActDao() }

    /**
     * Get the current locally persisted list of Pocket recommended articles.
     */
    suspend fun getLearnAndActBlocs(): List<LearnAndAct> {
        return pocketRecommendationsDao.getLearnAndAct().map { it.toLearnAndAct() }.asReversed()
    }
    /**
     * Replace the current list of locally persisted Pocket recommended articles.
     *
     * @param stories The list of Pocket recommended articles to persist locally.
     */
    suspend fun addAllLearnAndAct(stories: List<LearnAndActApi>) {
        pocketRecommendationsDao.cleanOldAndInsertNewLearnAndAct(stories.map { it.toLearnAndActEntity() })
    }
}
