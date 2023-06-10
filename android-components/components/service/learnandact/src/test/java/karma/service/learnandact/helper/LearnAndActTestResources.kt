package karma.service.learnandact.helper

import karma.service.learnandact.api.*
import java.text.SimpleDateFormat
import java.util.*


/**
 * Accessors to resources used in testing.
 */
internal object LearnAndActTestResources {
    val endointLearnAndActResponse = this::class.java.classLoader!!.getResource(
        "learnandact.json",
    )!!.readText()

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
    val apiExpectedLearnAndAct: List<LearnAndActApi> = arrayListOf(
            LearnAndActApi(1410,
                "act",
                "https://cms-prod.cellar-c2.services.clever-cloud.com/221012_LOI_20_ANIMAUX_3a1831ce4f.webp",
            "Faisons mieux entendre la voix des animaux en Europe",
                "Signez la pétition pour un commissaire européen au bien-être des animaux",
                "",
                "https://www.euforanimals.eu/fr",
                dateFormat.parse("2023-06-01T08:46:48.978Z") ?: Date()
            ),
        LearnAndActApi(1411,
            "learn",
            "https://cms-prod.cellar-c2.services.clever-cloud.com/221012_PLASTIQUE_716185b4b3.webp",
            "Plastique : idées fausses et fausses bonnes idées, un recap clair, net et proactif",
            "Recap, clarifications, solutions pour une compréhension des enjeux liés à l'utilisation du plastique.",
            "Lire l'article",
            "https://baleinesousgravillon.com/plastique-idees-fausses-et-fausses-bonnes-idees-un-recap-clair-net-et-proactif/",
            dateFormat.parse("2023-05-31T08:25:59.847Z") ?: Date()
        )
    )
}
