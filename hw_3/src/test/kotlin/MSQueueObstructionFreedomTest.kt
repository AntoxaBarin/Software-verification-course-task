import org.jetbrains.kotlinx.lincheck.annotations.*
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import kotlin.test.Test

class MSQueueObstructionFreedomTest {
    private val q = MSQueue()

    @Operation
    fun enqueue() = q.enqueue(1)

    @Operation
    fun dequeue() = q.dequeue()

    @Test
    fun modelCheckingTest() = ModelCheckingOptions()
        .checkObstructionFreedom()
        .check(this::class)
}