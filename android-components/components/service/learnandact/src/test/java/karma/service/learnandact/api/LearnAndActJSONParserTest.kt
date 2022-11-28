package karma.service.learnandact.api

import androidx.test.ext.junit.runners.AndroidJUnit4
import karma.service.learnandact.helper.LearnAndActTestResources
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.reflect.KVisibility


@RunWith(AndroidJUnit4::class)
class LearnAndActJSONParserTest {

    private lateinit var parser: LearnAndActJSONParser

    @Before
    fun setUp() {
        parser = LearnAndActJSONParser()
    }

    @Test
    fun `GIVEN a LearnAndActJSONParser THEN its visibility is internal`() {
        Assert.assertEquals(KVisibility.INTERNAL, LearnAndActJSONParser::class.visibility)
    }

    @Test
    fun `GIVEN LearnAndActJSONParser WHEN parsing valid learnandact THEN LearnAndActAPI are returned`() {
        val expected = LearnAndActTestResources.apiExpectedLearnAndAct
        val JSON = LearnAndActTestResources.endointLearnAndActResponse
        val actual = parser.jsonToLearnAndApi(JSON)

        Assert.assertNotNull(actual)
        Assert.assertEquals(2, actual!!.size)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `WHEN parsing stories recommendations for an empty string THEN null is returned`() {
        Assert.assertNull(parser.jsonToLearnAndApi(""))
    }

    @Test
    fun `WHEN parsing stories recommendations for an invalid JSON String THEN null is returned`() {
        Assert.assertNull(parser.jsonToLearnAndApi("{!!}}"))
    }
}