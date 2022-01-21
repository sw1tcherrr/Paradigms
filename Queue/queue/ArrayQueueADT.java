package queue;// Model:
//      {a[1] ... a[n]}
//      a[1] - head
//      a[n] - tail
//      n - size
// Inv:
//      n >= 0
//      forall i in [1..n] a[i] != null
// Immutable:
//      n == n' && a[1..n] == a'[1..n']

import java.util.Arrays;
import java.util.Objects;

public class ArrayQueueADT {
    private int size;
    private Object[] elements = new Object[2];
    private int head = 0;

    // Pre: queue != null && elements != null && elements.length != 0
    // Post: R == (head + size - 1) % elements.length == index of tail && Immutable
    private static int tail(ArrayQueueADT queue) {
        Objects.requireNonNull(queue);

        return (queue.head + queue.size - 1) % queue.elements.length;
    }

    // Pre: queue != null && e != null
    // Post: n == n' + 1 && a[1..n'] == a'[1..n'] && a[n' + 1] == e
    public static void enqueue(ArrayQueueADT queue, Object e) {
        Objects.requireNonNull(queue);
        Objects.requireNonNull(e);

        ensureCapacity(queue, queue.size + 1, 0);
        queue.size++;

        queue.elements[tail(queue)] = e;
    }

    // Pre: queue != null && e != null
    // Post: n == n' + 1 && a[2..n] == a'[1..n'] && a[1] == e
    public static void push(ArrayQueueADT queue, Object e) {
        Objects.requireNonNull(queue);
        Objects.requireNonNull(e);

        int l = queue.elements.length;
        ensureCapacity(queue, queue.size + 1, 1);
        queue.size++;
        if (queue.size <= l) {
            queue.head = (queue.head - 1 + l) % l;
        }

        queue.elements[queue.head] = e;
    }

    // Pre: queue != null
    // Post: R == a[1..n] && Immutable
    public static Object[] toArray(ArrayQueueADT queue) {
        Objects.requireNonNull(queue);

        Object[] elements_ = new Object[queue.size];
        if (queue.size != 0) {
            if (tail(queue) < queue.head) {
                System.arraycopy(queue.elements, queue.head, elements_, 0, queue.elements.length - queue.head);
                System.arraycopy(queue.elements, 0, elements_, queue.elements.length - queue.head, tail(queue) + 1);
            } else {
                elements_ = Arrays.copyOfRange(queue.elements, queue.head, tail(queue) + 1);
            }
        }
        return elements_;
    }

    // Pre: queue != null && elements != null && elements.length != 0 && 0 <= start <= 1
    // Post: head == 0 && head < tail() && elements.length == elements'.length * 2
    // && elements[0..elements'.length-1] == a[1..n] && Immutable
    private static void ensureCapacity(ArrayQueueADT queue, int capacity, int start) {
        Objects.requireNonNull(queue);

        if (queue.elements.length < capacity) {
            Object[] elements_ = new Object[capacity * 2];
            System.arraycopy(toArray(queue), 0, elements_, start, queue.size);
            queue.elements = elements_;
            queue.head = 0;
        }
    }

    // Pre: queue != null && size > 0
    // Post: R == a[1] && Immutable
    public static Object element(ArrayQueueADT queue) {
        Objects.requireNonNull(queue);
        assert queue.size > 0;

        return queue.elements[queue.head];
    }

    // Pre: queue != null && size > 0
    // Post: R == a[n] && Immutable
    public static Object peek(ArrayQueueADT queue) {
        Objects.requireNonNull(queue);
        assert queue.size > 0;

        return queue.elements[tail(queue)];
    }

    // Pre: queue != null && size > 0
    // Post: R == a'[n'] && a[1..n] = a'[1..n'-1] && n == n' - 1
    public static Object remove(ArrayQueueADT queue) {
        Objects.requireNonNull(queue);
        assert queue.size > 0;

        Object val = queue.elements[tail(queue)];
        queue.elements[tail(queue)] = null;
        queue.size--;
        return val;
    }

    // Pre: queue != null && size > 0
    // Post: R == a'[1] && a[1..n-1] = a'[2..n'] && n == n' - 1
    public static Object dequeue(ArrayQueueADT queue) {
        Objects.requireNonNull(queue);
        assert queue.size > 0;

        Object val = queue.elements[queue.head];
        queue.elements[queue.head] = null;
        queue.size--;
        queue.head = (queue.head + 1) % queue.elements.length;
        return val;
    }

    // Pre: queue != null
    // Post: R == n && Immutable
    public static int size(ArrayQueueADT queue) {
        Objects.requireNonNull(queue);

        return queue.size;
    }

    // Pre: queue != null
    // Post: R == (n == 0) && Immutable
    public static boolean isEmpty(ArrayQueueADT queue) {
        Objects.requireNonNull(queue);

        return queue.size == 0;
    }

    // Pre: queue != null
    // Post: n == 0
    public static void clear(ArrayQueueADT queue) {
        Objects.requireNonNull(queue);

        queue.head = 0;
        queue.size = 0;
        Arrays.fill(queue.elements, null);
    }

    // Pre:  queue != null
    // Post: R == "[head, ... , tail()]" && Immutable
    public static String toStr(ArrayQueueADT queue) {
        Objects.requireNonNull(queue);

        return Arrays.toString(toArray(queue));
    }
}
