package karma.service.learnandact.api

import androidx.annotation.VisibleForTesting
import karma.service.learnandact.logger
import mozilla.components.support.ktx.android.org.json.mapNotNull
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


internal class LearnAndActJSONParser {
    /**
     * @return The stories, removing entries that are invalid, or null on error; the list will never be empty.
     */
    fun jsonToLearnAndApi(json: String): List<LearnAndActApi>? = try {
        val articleArray = JSONArray(json)
        val stories = articleArray.mapNotNull(JSONArray::getJSONObject) { jsonToLearnAndActApi(it) }

        // We return null, rather than the empty list, because devs might forget to check an empty list.
        if (stories.isNotEmpty()) stories else null
    } catch (e: JSONException) {
        logger.warn("invalid JSON from the Pocket endpoint", e)
        null
    }

    private fun jsonToLearnAndActApi(json: JSONObject): LearnAndActApi? = try {
        LearnAndActApi(
            // These three properties are required for any valid recommendation.
            id = json.getInt("id"),
            type = json.getString("type"),
            duration = json.getString("duration"),
            mobile_image = json.getString("mobileImage"),
            title = json.getString("title"),
            description = json.getString("description"),
            action = json.getString("action"),
            link = json.getString("link")
            )
    } catch (e: JSONException) {
        logger.warn("invalid JSON from the L&A endpoint", e)
        null
    }

}
