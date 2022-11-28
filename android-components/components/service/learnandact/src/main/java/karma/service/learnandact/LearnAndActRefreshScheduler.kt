package karma.service.learnandact

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.work.*
import karma.service.learnandact.RefreshLearnAndActWorker.Companion.REFRESH_WORK_TAG
import mozilla.components.support.base.worker.Frequency

class LearnAndActRefreshScheduler(private val learnAndActConfig: LearnAndActConfig) {
    internal fun schedulePeriodicRefreshes(context: Context) {
        logger.info("Scheduling pocket recommendations background refresh")

        val refreshWork = createPeriodicWorkerRequest(
            frequency = learnAndActConfig.frequency
        )

        getWorkManager(context)
            .enqueueUniquePeriodicWork(REFRESH_WORK_TAG, ExistingPeriodicWorkPolicy.KEEP, refreshWork)
    }

    internal fun stopPeriodicRefreshes(context: Context) {
        getWorkManager(context)
            .cancelAllWorkByTag(REFRESH_WORK_TAG)
    }

    @VisibleForTesting
    internal fun createPeriodicWorkerRequest(
        frequency: Frequency
    ): PeriodicWorkRequest {
        val constraints = getWorkerConstrains()

        return PeriodicWorkRequestBuilder<RefreshLearnAndActWorker>(
            frequency.repeatInterval,
            frequency.repeatIntervalTimeUnit
        ).apply {
            setConstraints(constraints)
            addTag(REFRESH_WORK_TAG)
        }.build()
    }

    @VisibleForTesting
    internal fun getWorkerConstrains() = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    @VisibleForTesting
    internal fun getWorkManager(context: Context) = WorkManager.getInstance(context)
}
