import org.jetbrains.kotlinx.lincheck.*
import org.jetbrains.kotlinx.lincheck.annotations.*
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import java.util.*
import java.util.Optional.empty
import java.util.Optional.of
import kotlin.test.Test

class MSQueueStraightforwardTest {
    private val q = MSQueue()

    @Operation
    fun enqueue(value: Int) = q.enqueue(value)

    @Operation
    fun dequeue(): Optional<Int> = q.dequeue()

    @Test
    fun modelCheckSequentialSpecTest() = ModelCheckingOptions()
        .actorsBefore(10)
        .threads(3).actorsPerThread(3).iterations(3)
        .actorsAfter(10)
        .sequentialSpecification(SequentialQueue::class.java)
        .check(this::class)
    @Test
    fun stressSequentialSpecTest() = StressOptions()
        .actorsBefore(10)
        .threads(3).actorsPerThread(3).iterations(3)
        .actorsAfter(10)
        .sequentialSpecification(SequentialQueue::class.java)
        .check(this::class)
}

class SequentialQueue {
    private val list = LinkedList<Int>()

    fun enqueue(x: Int) {
        list.add(x)
    }
    fun dequeue(): Optional<Int> {
        if (list.isEmpty()) {
            return empty<Int>()
        }
        val res: Int = list.poll()
        return of<Int>(res)
    }
}