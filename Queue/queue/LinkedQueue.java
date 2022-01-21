package queue;

public class LinkedQueue extends AbstractQueue {
    private static class Node {
        private Node next;
        private final Object val;

        public Node(Node next, Object val) {
            this.next = next;
            this.val = val;
        }
    }

    private Node head;
    private Node tail;

    protected void enqueueImpl(Object e) {
        Node n = new Node(null, e);
        if (tail == null) {
            tail = n;
            head = tail;
        } else {
            tail.next = n;
            tail = n;
        }
    }

    protected Object elementImpl() {
        return head.val;
    }

    protected Object dequeueImpl() {
        Object val = head.val;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return val;
    }

    protected Queue createObj() {
        return new LinkedQueue();
    }

    protected void clearImpl() {
        head = null;
        tail = null;
    }
}
