package tasks

import contributors.MockGithubService
import contributors.expectedConcurrentResults
import contributors.testRequestData
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class Request5ConcurrentKtTest {
    @Test
    fun testConcurrent() =
        runBlocking {
            val startTime = System.currentTimeMillis()
            val result = loadContributorsConcurrent(MockGithubService, testRequestData)
            assertEquals(
                expectedConcurrentResults.users,
                result,
                "Wrong result for 'loadContributorsConcurrent'",
            )
            val totalTime = System.currentTimeMillis() - startTime
        /*
        // TODO: uncomment this assertion
        Assert.assertEquals(
            "The calls run concurrently, so the total virtual time should be 2200 ms: " +
                    "1000 ms for repos request plus max(1000, 1200, 800) = 1200 ms for concurrent contributors requests)",
            expectedConcurrentResults.timeFromStart, totalTime
        )
         */
            assertTrue(
                "The calls run concurrently, so the total virtual time should be 2200 ms: " +
                    "1000 ms for repos request plus max(1000, 1200, 800) = 1200 ms for concurrent contributors requests)",
            ) {
                totalTime in expectedConcurrentResults.timeFromStart..(expectedConcurrentResults.timeFromStart + 1000)
            }
        }
}
