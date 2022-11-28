package karma.learnandact

import androidx.annotation.WorkerThread
import karma.service.learnandact.logger
import mozilla.components.concept.fetch.Client
import mozilla.components.concept.fetch.Request
import mozilla.components.concept.fetch.Response
import mozilla.components.concept.fetch.isSuccess
import java.io.IOException

@WorkerThread // synchronous network call.
internal fun Client.fetchBodyOrNull(request: Request): String? {
    val response: Response? = try {
        fetch(request)
    } catch (e: IOException) {
        logger.debug("network error", e)
        null
    }

    return response?.use { if (response.isSuccess) response.body.string() else null }
}
