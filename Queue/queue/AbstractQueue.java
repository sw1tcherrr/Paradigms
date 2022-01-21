package queue;

import java.util.Objects;

public abstract class AbstractQueue implements Queue {
    protected int size;

    public void enqueue(Object e) {
        Objects.requireNonNull(e);
        enqueueImpl(e);
        size++;
    }

    protected abstract void enqueueImpl(Object e);

    public Object element() {
        assert size > 0;
        return elementImpl();
    }

    protected abstract Object elementImpl();

    public Object dequeue() {
        assert size > 0;
        Object val = dequeueImpl();
        size--;
        return val;
    }

    protected abstract Object dequeueImpl();

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        clearImpl();
        size = 0;
    }

    protected abstract void clearImpl();

    private void Nth(int n, Queue q, boolean remove) {
        int s = size;
        for (int i = 1; i <= s; i++) {
            Object elt = dequeue();
            if (i % n == 0) {
                if (!remove)
                    enqueue(elt);
                if (q != null)
                    q.enqueue(elt);
            } else {
                enqueue(elt);
            }
        }
    }

    private Queue NthWithQ(int n, boolean remove) {
        Queue q = createObj();
        Nth(n, q, remove);
        return q;
    }

    public Queue getNth(int n) {
        return NthWithQ(n, false);
    }

    public void dropNth(int n) {
        Nth(n, null, true);
    }

    public Queue removeNth(int n) {
        return NthWithQ(n, true);
    }

    protected abstract Queue createObj();
}
