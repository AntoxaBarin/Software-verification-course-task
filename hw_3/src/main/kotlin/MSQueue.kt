import java.util.*
import java.util.Optional.empty
import java.util.concurrent.atomic.AtomicReference

class Node(value: Int, next: Node?) {
    val Value = value
    var N = AtomicReference(next)
}

class MSQueue {
    private var dummy = Node(0, null)
    private var H = AtomicReference(dummy)
    private var T = AtomicReference(dummy)

    fun isEmpty() : Boolean {
        return H.get() == T.get()
    }

    fun enqueue(data: Int) {
        val newTail = Node(data, null)
        while (true) {
            val tail = T.get()
            if (tail.N.compareAndSet(null, newTail)) {
                T.compareAndSet(tail, newTail)
                return
            }
            else {
                T.compareAndSet(tail, tail.N.get())
            }
        }
    }

    fun dequeue(): Optional<Int> {
        while (true) {
            val head = H.get()
            val tail = T.get()
            val headNext = H.get().N.get()
            if (head == tail) {
                if (headNext == null) {
                    return empty<Int>()
                }
                else {
                    T.compareAndSet(tail, headNext)
                }
            }
            else {
                if (H.compareAndSet(head, headNext)) {
                    return Optional.of<Int>(headNext!!.Value)
                }
            }
        }
    }

}