package karma.service.learnandact.api

import androidx.annotation.VisibleForTesting
import androidx.annotation.WorkerThread
import karma.learnandact.fetchBodyOrNull
import mozilla.components.concept.fetch.Client
import mozilla.components.concept.fetch.MutableHeaders
import mozilla.components.concept.fetch.Request
import mozilla.components.service.learnandact.BuildConfig
import java.util.*

internal class LearnAndActEndpointRaw internal constructor(
    @VisibleForTesting internal val client: Client
) {
    /**
     * Gets the current stories recommendations from the Pocket server.
     *
     * @return The stories recommendations as a raw JSON string or null on error.
     */
    @WorkerThread
    fun getLearnAndActBlocs(page: Int = 1): String? = makeRequest(page)

    /**
     * @return The requested JSON as a String or null on error.
     */
    @WorkerThread // synchronous request.
    private fun makeRequest(page: Int = 1): String? {
        val locale = Locale.getDefault().toString().replace("_","-")
        val request = Request(endpointUrl+locale+"&pageNumber="+page)

        return client.fetchBodyOrNull(request)
    }

    companion object {
        private const val endpointUrl = "https://api.karmasearch.org/posts?locale="
        /**
         * Returns a new instance of [PocketEndpointRaw].
         *
         * @param client the HTTP client to use for network requests.
         */
        fun newInstance(client: Client): LearnAndActEndpointRaw {
            return LearnAndActEndpointRaw(client)
        }
    }
}