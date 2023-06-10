package karma.service.learnandact.api

import android.annotation.SuppressLint
import karma.service.learnandact.logger
import mozilla.components.support.ktx.android.org.json.mapNotNull
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*


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

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)

    private fun jsonToLearnAndActApi(json: JSONObject): LearnAndActApi? = try {
        LearnAndActApi(
            // These three properties are required for any valid recommendation.
            id = json.getInt("id"),
            contentType = json.optStringNotNull("contentType"),
            imageUrl = json.optStringNotNull("imageUrl"),
            title = json.optStringNotNull("title"),
            content = json.optStringNotNull("content"),
            destinationUrlLabel = json.optStringNotNull("destinationUrlLabel"),
            destinationUrl = json.optStringNotNull("destinationUrl"),
            publishedAt = dateFormat.parse(json.optStringNotNull("publishedAt"))  ?: Date()
        )
    } catch (e: JSONException) {
        logger.warn("invalid JSON from the L&A endpoint", e)
        null
    }

   fun JSONObject.optStringNotNull(key: String): String {
        return if (isNull(key)) "" else getString(key)
    }

}
