package karma.service.learnandact.helper

import karma.service.learnandact.api.LearnAndActApi


/**
 * Accessors to resources used in testing.
 */
internal object LearnAndActTestResources {
    val endointLearnAndActResponse = this::class.java.classLoader!!.getResource(
        "learnandact.json"
    )!!.readText()

    val apiExpectedLearnAndAct: List<LearnAndActApi> = listOf(
        LearnAndActApi(
            id = 0,
            title = "Save the Okavango River Basin from Oil Drilling",
            link = "https://okavango.rewild.org/",
            mobile_image = "https://storage.googleapis.com/learn-and-act-and-images.appspot.com/L&A/images/mobile/en/Save_Okavango_river.webp",
            duration = "",
            type = "Act",
            action = "Sign the petition !",
            description = "Sign an open letter to stop oil and gas drilling by Canadian company ReconAfrica in the Okavango River Basin. Share this action with others!"
        ),
        LearnAndActApi(
            id = 1,
            title = "Protect tropical forest and marine habitats",
            link = "https://action.ifaw.org/page/79725/action/1",
            mobile_image = "https://storage.googleapis.com/learn-and-act-and-images.appspot.com/L&A/images/mobile/en/Protect_marine_habitats.webp",
            duration = "",
            type = "Act",
            action = "Sign the petition !",
            description = "Sign the petition to push the United States government to help protect fragile ecosystems around the world.",
        )
    )
}
