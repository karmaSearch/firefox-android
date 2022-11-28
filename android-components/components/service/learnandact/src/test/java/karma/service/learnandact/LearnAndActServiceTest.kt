package karma.service.learnandact

import androidx.test.ext.junit.runners.AndroidJUnit4
import karma.service.learnandact.api.LearnAndActApi
import kotlinx.coroutines.runBlocking
import mozilla.components.support.test.any
import mozilla.components.support.test.mock
import mozilla.components.support.test.robolectric.testContext
import mozilla.components.support.test.whenever
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import kotlin.reflect.KVisibility

@RunWith(AndroidJUnit4::class)
class LearnAndActServiceTest {

    private val service = LearnAndActService(testContext, LearnAndActConfig(mock())).also {
        it.scheduler = mock()
        it.getLearnAndActUsecase = mock()
    }

    @Test
    fun `GIVEN PocketStoriesService WHEN getStories THEN getLearnAndActUsecase should return`() = runBlocking {
        val stories = listOf(mock<LearnAndAct>())
        whenever(service.getLearnAndActUsecase.invoke()).thenReturn(stories)

        val result = service.getLearnAndAct()

        Assert.assertEquals(stories, result)
    }
}