package karma.service.learnandact.api


internal sealed class LearnAndActResponse<T> {

    /**
     * A successful response from the Pocket API.
     *
     * @param data The data returned from the Pocket API.
     */
    data class Success<T> internal constructor(val data: T) : LearnAndActResponse<T>()

    /**
     * A failure response from the Pocket API.
     */
    class Failure<T> internal constructor() : LearnAndActResponse<T>()

    companion object {

        /**
         * Wraps the given [target] in a [PocketResponse]: if [target] is
         * - null, then Failure
         * - a Collection and empty, then Failure
         * - a String and empty, then Failure
         * - otherwise, Success
         */
        internal fun <T : Any> wrap(target: T?): LearnAndActResponse<T> = when (target) {
            null -> Failure()
            is Collection<*> -> if (target.isEmpty()) Failure() else Success(target)
            is String -> if (target.isBlank()) Failure() else Success(target)
            else -> Success(target)
        }
    }
}
