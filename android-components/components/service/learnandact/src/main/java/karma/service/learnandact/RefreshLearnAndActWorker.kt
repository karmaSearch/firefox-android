package karma.service.learnandact

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RefreshLearnAndActWorker(private val context: Context,
                               params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            if (LearnAndActUseCases().RefreshLearnAndAct(context).invoke()
            ) {
                Result.success()
            } else {
                Result.retry()
            }
        }
    }

    internal companion object {
        const val REFRESH_WORK_TAG =
            "karma.feature.learnandact.refresh.work.tag"
    }
}