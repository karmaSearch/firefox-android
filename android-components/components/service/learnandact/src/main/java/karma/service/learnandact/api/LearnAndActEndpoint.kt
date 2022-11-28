package karma.service.learnandact.api

import androidx.annotation.VisibleForTesting
import androidx.annotation.WorkerThread
import mozilla.components.concept.fetch.Client

internal class LearnAndActEndpoint(
    @VisibleForTesting internal val rawEndpoint: LearnAndActEndpointRaw,
    private val jsonParser: LearnAndActJSONParser
) {

    @WorkerThread
    fun getLearnAndActBlocs(): LearnAndActResponse<List<LearnAndActApi>> {
        val response = rawEndpoint.getLearnAndActBlocs()
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
