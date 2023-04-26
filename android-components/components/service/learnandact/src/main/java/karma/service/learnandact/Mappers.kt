package karma.service.learnandact

import karma.service.learnandact.api.LearnAndActApi
import karma.service.learnandact.db.LearnAndActEntity

internal fun LearnAndActApi.toLearnAndAct(): LearnAndAct =
    LearnAndAct(id = id,
        type = LearnAndActContentType.valueOf(contentType.uppercase()),
    title = title,
    description = content,
    imageUrl = imageUrl,
    action = destinationUrlLabel,
    actionUrl = destinationUrl)

/**
 * Map Room entities to the object type that we expose to service clients.
 */
internal fun LearnAndActEntity.toLearnAndAct(): LearnAndAct =
    LearnAndAct(id = id,
        type = LearnAndActContentType.valueOf(type.uppercase()),
        title = title,
        description = description,
        imageUrl = imageUrl,
        action = action,
        actionUrl = actionUrl)


internal fun LearnAndActApi.toLearnAndActEntity(): LearnAndActEntity =
    LearnAndActEntity(id = id,
        type = contentType,
        title = title,
        description = content,
        duration = "",
        imageUrl = imageUrl,
        action = destinationUrlLabel,
        actionUrl = destinationUrl)


