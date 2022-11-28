package karma.service.learnandact

import android.content.Context
import mozilla.components.concept.fetch.Client

class LearnAndActService(
    private val context: Context,
    private val learnAndActConfig: LearnAndActConfig
)  {
    internal var scheduler = LearnAndActRefreshScheduler(learnAndActConfig)

    private val useCases = LearnAndActUseCases()
    internal var getLearnAndActUsecase = useCases.GetLearnAndActBlocs(context)

    /**
     * Entry point to start fetching Learn and act in the background.
     *
     * Use this at an as high as possible level in your application.
     * Must be paired in a similar way with the [stopPeriodicStoriesRefresh] method.
     *
     * This starts the process of downloading and caching Pocket stories in the background,
     * making them available for the [getStories] method.
     */
    fun startLearnAndActRefresh() {
        LearnAndActUseCases.initialize(learnAndActConfig.client)
        scheduler.schedulePeriodicRefreshes(context)
    }

    /**
     * Single stopping point for the "get Learn and act" functionality.
     *
     * Use this at an as high as possible level in your application.
     * Must be paired in a similar way with the [startLearnAndActRefresh] method.
     *
     * This stops the process of downloading and caching Pocket stories in the background.
     */
    fun stopPeriodicLearnAndActRefresh() {
        scheduler.stopPeriodicRefreshes(context)
        LearnAndActUseCases.reset()
    }


    suspend fun getLearnAndAct(): List<LearnAndAct> {
        return getLearnAndActUsecase.invoke()
    }
}