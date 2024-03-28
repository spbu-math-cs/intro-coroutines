package tasks

import contributors.MockGithubService
import contributors.expectedResults
import contributors.testRequestData
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class Request4SuspendKtTest {
    @Test
    fun testSuspend() =
        runBlocking {
            val startTime = System.currentTimeMillis()
            val result = loadContributorsSuspend(MockGithubService, testRequestData)
            assertEquals(
                expectedResults.users,
                result,
                "Wrong result for 'loadContributorsSuspend'",
            )
            val totalTime = System.currentTimeMillis() - startTime
        /*
        // TODO: uncomment this assertion
        Assert.assertEquals(
            expectedResults.timeFromStart, totalTime,
            "The calls run consequently, so the total virtual time should be 4000 ms: " +
                    "1000 for repos request plus (1000 + 1200 + 800) = 3000 for sequential contributors requests)",
        )
         */
            assertTrue(
                "The calls run consequently, so the total time should be around 4000 ms: " +
                    "1000 for repos request plus (1000 + 1200 + 800) = 3000 for sequential contributors requests)",
            ) {
                totalTime in expectedResults.timeFromStart..(expectedResults.timeFromStart + 500)
            }
        }
}
