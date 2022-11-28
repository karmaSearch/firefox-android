package karma.service.learnandact

import android.content.Context
import androidx.annotation.VisibleForTesting
import karma.service.learnandact.api.LearnAndActEndpoint
import karma.service.learnandact.api.LearnAndActResponse
import mozilla.components.concept.fetch.Client

class LearnAndActUseCases {
    /**
     * Allows for refreshing the list of pocket stories we have cached.
     *
     * @param context Android Context. Prefer sending application context to limit the possibility of even small leaks.
     */
    internal inner class RefreshLearnAndAct(
        @VisibleForTesting
        internal val context: Context
    ) {
        /**
         * Do a full download from Pocket -> persist locally cycle for recommended stories.
         */
        suspend operator fun invoke(): Boolean {
            val client = fetchClient
            if (client == null) {
                logger.error("Cannot download new stories. Service has incomplete setup")
                return false
            }

            val pocket = getLearnAndActEndpoint(client)
            val response = pocket.getLearnAndActBlocs()

            if (response is LearnAndActResponse.Success) {
                getLearnAndActRepository(context)
                    .addAllLearnAndAct(response.data)
                return true
            }

            return false
        }
    }


    @VisibleForTesting
    internal fun getLearnAndActRepository(context: Context) = LearnAndActRepository(context)

    internal inner class GetLearnAndActBlocs(private val context: Context) {
        suspend operator fun invoke(): List<LearnAndAct> {

            return getLearnAndActRepository(context)
                .getLearnAndActBlocs()
        }
    }

    @VisibleForTesting
    internal fun getLearnAndActEndpoint(client: Client) = LearnAndActEndpoint.newInstance(client)

    internal companion object {
        @VisibleForTesting internal var fetchClient: Client? = null

        /**
         * Convenience method for setting the the HTTP Client which will be used
         * for all REST communications with the Pocket server.
         *
         * Already downloaded data can still be queried but no new data can be downloaded until
         * this parameter is set.
         */
        internal fun initialize(client: Client) {
            fetchClient = client
        }

        /**
         * Convenience method for cleaning up any resources held for communicating with the Pocket server.
         *
         * Already downloaded data can still be queried but no new data can be downloaded until
         * [initialize] is used again.
         */
        internal fun reset() {
            fetchClient = null
        }
    }
}