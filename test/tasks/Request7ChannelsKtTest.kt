package tasks

import contributors.MockGithubService
import contributors.concurrentProgressResults
import contributors.testRequestData
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class Request7ChannelsKtTest {
    @Test
    fun testChannels() =
        runBlocking {
            val startTime = System.currentTimeMillis()
            var index = 0
            loadContributorsChannels(MockGithubService, testRequestData) { users, _ ->
                val expected = concurrentProgressResults[index++]
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
