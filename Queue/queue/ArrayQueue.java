package queue;

import java.util.Arrays;
import java.util.Objects;

public class ArrayQueue extends AbstractQueue{
    private Object[] elements = new Object[2];
    private int head = 0;

    private int tail() {
        return (head + size - 1) % elements.length;
    }

    protected void enqueueImpl(Object e) {
        ensureCapacity(size + 1, 0);
        elements[(head + size) % elements.length] = e;
    }

    private Object[] toArray(int new_size, int start) {
        Object[] elements_ = new Object[new_size];
        if (size != 0) {
            if (tail() < head) {
                System.arraycopy(elements, head, elements_, start, elements.length - head);
                System.arraycopy(elements, 0, elements_, start + elements.length - head, tail() + 1);
            } else {
                System.arraycopy(elements, head, elements_, start, size);
            }
        }
        return elements_;
    }

    private void ensureCapacity(int capacity, int start) {
        if (elements.length < capacity) {
            elements = toArray(capacity * 2, start);
            head = 0;
        }
    }

    protected Object elementImpl() {
        return elements[head];
    }

    protected Object dequeueImpl() {
        Object val = elements[head];
        elements[head] = null;
        head = (head + 1) % elements.length;
        return val;
    }

    protected Queue createObj() {
        return new ArrayQueue();
    }

    protected void clearImpl() {
        elements = new Object[2];
        head = 0;
    }

    // from HW2
    public void push(Object e) {
        Objects.requireNonNull(e);
        int l = elements.length;
        ensureCapacity(size + 1, 1);
        size++;
        if (size <= l) {
            head = (head - 1 + l) % l;
        }

        elements[head] = e;
    }

    public Object peek() {
        assert size > 0;

        return elements[tail()];
    }

    public Object remove() {
        assert size > 0;

        Object val = elements[tail()];
        elements[tail()] = null;
        size--;
        return val;
    }

    public Object[] toArray() {
        return toArray(size, 0);
    }

    public String toStr() {
        return Arrays.toString(toArray());
    }
}
