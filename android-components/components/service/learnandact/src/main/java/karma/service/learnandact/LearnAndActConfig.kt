package karma.service.learnandact

import mozilla.components.concept.fetch.Client
import mozilla.components.support.base.worker.Frequency
import java.util.concurrent.TimeUnit

internal const val DEFAULT_REFRESH_INTERVAL = 4L
@Suppress("TopLevelPropertyNaming")
internal val DEFAULT_REFRESH_TIMEUNIT = TimeUnit.HOURS

class LearnAndActConfig(
    val client: Client,
    val frequency: Frequency = Frequency(
        DEFAULT_REFRESH_INTERVAL,
        DEFAULT_REFRESH_TIMEUNIT
    )
)