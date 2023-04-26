package karma.service.learnandact.api

import androidx.annotation.VisibleForTesting
import androidx.annotation.WorkerThread
import mozilla.components.concept.fetch.Client

internal class LearnAndActEndpoint(
    @VisibleForTesting internal val rawEndpoint: LearnAndActEndpointRaw,
    private val jsonParser: LearnAndActJSONParser
) {

    @WorkerThread
    fun getLearnAndActBlocs(page:Int = 1): LearnAndActResponse<List<LearnAndActApi>> {
        val response = rawEndpoint.getLearnAndActBlocs(page)
        val blocs = response?.let { jsonParser.jsonToLearnAndApi(it) }
        return LearnAndActResponse.wrap(blocs)
    }

    companion object {
        /**
         * Returns a new instance of [PocketEndpoint].
         *
         * @param client the HTTP client to use for network requests.
         */
        fun newInstance(client: Client): LearnAndActEndpoint {
            val rawEndpoint = LearnAndActEndpointRaw.newInstance(client)
            return LearnAndActEndpoint(rawEndpoint, LearnAndActJSONParser())
        }
    }
}
