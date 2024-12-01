import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import java.util.*
import kotlin.test.Test

class MSQueueSingleThreadTest {
    private lateinit var q : MSQueue

    @BeforeEach
    fun initMSQueue() {
        q = MSQueue()
    }

    @Test
    fun testEnqueueDequeue() {
        q.enqueue(1)
        q.enqueue(2)
        q.enqueue(3)

        assertEquals(Optional.of<Int>(1), q.dequeue())
        assertEquals(Optional.of<Int>(2), q.dequeue())
        assertEquals(false, q.isEmpty())

        q.enqueue(4)

        assertEquals(Optional.of<Int>(3), q.dequeue())
        assertEquals(Optional.of<Int>(4), q.dequeue())
        assertEquals(true, q.isEmpty())
    }

    @Test
    fun testEmpty() {
        assertEquals(true, q.isEmpty())
        q.enqueue(1)
        assertEquals(false, q.isEmpty())
    }

    @Test
    fun testEmptyDequeue() {
        assertEquals(Optional.empty<Int>(), q.dequeue())
    }
}