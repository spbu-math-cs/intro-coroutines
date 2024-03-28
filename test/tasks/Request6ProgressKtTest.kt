package tasks

import contributors.MockGithubService
import contributors.progressResults
import contributors.testRequestData
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class Request6ProgressKtTest {
    @Test
    fun testProgress() =
        runBlocking {
            val startTime = System.currentTimeMillis()
            var index = 0
            loadContributorsProgress(MockGithubService, testRequestData) {
                    users, _ ->
                val expected = progressResults[index++]
                val time = System.currentTimeMillis() - startTime
            /*
            // TODO: uncomment this assertion
            Assert.assertEquals(
                expected.timeFromStart,
                time,
                "Expected intermediate result after virtual ${expected.timeFromStart} ms:",
            )
             */
                assertEquals(
                    expected.users,
                    users,
                    "Wrong intermediate result after $time:",
                )
            }
        }
}
