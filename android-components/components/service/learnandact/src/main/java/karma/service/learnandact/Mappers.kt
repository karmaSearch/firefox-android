package karma.service.learnandact

import karma.service.learnandact.api.LearnAndActApi
import karma.service.learnandact.db.LearnAndActEntity

internal fun LearnAndActApi.toLearnAndAct(): LearnAndAct =
    LearnAndAct(type = type,
    title = title,
    description = description,
    duration = duration,
    imageUrl = mobile_image,
    action = action,
    actionUrl = link)


/**
 * Map Room entities to the object type that we expose to service clients.
 */
internal fun LearnAndActEntity.toLearnAndAct(): LearnAndAct =
    LearnAndAct(type = type,
        title = title,
        description = description,
        duration = duration,
        imageUrl = imageUrl,
        action = action,
        actionUrl = actionUrl)


internal fun LearnAndActApi.toLearnAndActEntity(): LearnAndActEntity =
    LearnAndActEntity(id = id,
        type = type,
        title = title,
        description = description,
        duration = duration,
        imageUrl = mobile_image,
        action = action,
        actionUrl = link)


