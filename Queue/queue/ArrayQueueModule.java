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

public class ArrayQueueModule {
    private static int size;
    private static Object[] elements = new Object[2];
    private static int head = 0;

    // Pre: elements != 0 && elements.length != 0
    // Post: R == (head + size - 1) % elements.length == index of tail && Immutable
    private static int tail() {
        return (head + size - 1) % elements.length;
    }
    
    // Pre: e != null
    // Post: n == n' + 1 && a[1..n'] == a'[1..n'] && a[n' + 1] == e
    public static void enqueue(Object e) {
        Objects.requireNonNull(e);

        ensureCapacity(size + 1, 0);
        size++;

        elements[tail()] = e;
    }

    // Pre: e != null
    // Post: n == n' + 1 && a[2..n] == a'[1..n'] && a[1] == e
    public static void push(Object e) {
        Objects.requireNonNull(e);
        int l = elements.length;
        ensureCapacity(size + 1, 1);
        size++;
        if (size <= l) {
            head = (head - 1 + l) % l;
        }

        elements[head] = e;
    }

    // Pre:
    // Post: R == a[1..n] && Immutable
    public static Object[] toArray() {
        Object[] elements_ = new Object[size];
        if (size != 0) {
            if (tail() < head) {
                System.arraycopy(elements, head, elements_, 0, elements.length - head);
                System.arraycopy(elements, 0, elements_, elements.length - head, tail() + 1);
            } else {
                elements_ = Arrays.copyOfRange(elements, head, tail() + 1);
            }
        }
        return elements_;
    }

    // Pre: elements != null && elements.length != 0 && 0 <= start <= 1
    // Post: head == 0 && head < tail() && elements.length == elements'.length * 2
    // && elements[0..elements'.length-1] == a[1..n] && Immutable
    private static void ensureCapacity(int capacity, int start) {
        if (elements.length < capacity) {
            Object[] elements_ = new Object[capacity * 2];
            System.arraycopy(toArray(), 0, elements_, start, size);
            elements = elements_;
            head = 0;
        }
    }

    // Pre: size > 0
    // Post: R == a[1] && Immutable
    public static Object element() {
        assert size > 0;

        return elements[head];
    }

    // Pre: size > 0
    // Post: R == a[n] && Immutable
    public static Object peek() {
        assert size > 0;

        return elements[tail()];
    }

    // Pre: size > 0
    // Post: R == a'[n'] && a[1..n] = a'[1..n'-1] && n == n' - 1
    public static Object remove() {
        assert size > 0;

        Object val = elements[tail()];
        elements[tail()] = null;
        size--;
        return val;
    }

    // Pre: size > 0
    // Post: R == a'[1] && a[1..n-1] = a'[2..n'] && n == n' - 1
    public static Object dequeue() {
        assert size > 0;

        Object val = elements[head];
        elements[head] = null;
        size--;
        head = (head + 1) % elements.length;
        return val;
    }

    // Pre:
    // Post: R == n && Immutable
    public static int size() {
        return size;
    }

    // Pre:
    // Post: R == (n == 0) && Immutable
    public static boolean isEmpty() {
        return size == 0;
    }

    // Pre:
    // Post: n == 0
    public static void clear() {
        head = 0;
        size = 0;
        Arrays.fill(elements, null);
    }

    // Pre:
    // Post: R == "[head, ... , tail()]" && Immutable
    public static String toStr() {
        return Arrays.toString(toArray());
    }
}
