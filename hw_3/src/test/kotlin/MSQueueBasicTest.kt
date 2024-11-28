import org.jetbrains.kotlinx.lincheck.annotations.*
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import kotlin.test.Test

class MSQueueBasicTest {
    private val q = MSQueue()

    @Operation
    fun enqueue() = q.enqueue(1)

    @Operation
    fun dequeue() = q.dequeue()

    @Test
    fun stressTest() = StressOptions().check(this::class)
}