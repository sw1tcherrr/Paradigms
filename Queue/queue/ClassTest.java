package queue;

public class ClassTest {
    private static final ArrayQueue queue = new ArrayQueue();

    private static void dump() {
        System.out.println(queue.toStr());
        System.out.println();
    }

    private static void fillTail(int n) {
        System.out.println("Enqueue test");
        for (int i = 0; i < n; i++) {
            queue.enqueue(i);
        }

        dump();
    }

    private static void fillHead(int n) {
        System.out.println("Push test");
        for (int i = 10; i < 10 + n; i++) {
            queue.push(i);
        }

        dump();
    }

    private static void testPeek() {
        System.out.println("Peek/element test");

        Object e1 = queue.element(), e2 = queue.element();
        System.out.println(e1 + " " + e2);
        System.out.println("element 1 == element 2 : " + e1.equals(e2));

        e1 = queue.peek();
        e2 = queue.peek();
        System.out.println(e1 + " " + e2);
        System.out.println("peek 1 == peek 2 : " + e1.equals(e2));

        dump();
    }

    private static void testDelete(int n) {
        System.out.println("Deletion test");
        System.out.println("From head:");
        for (int i = 0; i < n; i++) {
            System.out.println(queue.dequeue());
        }
        System.out.println("From tail:");
        for (int i = 0; i < n; i++) {
            System.out.println(queue.remove());
        }

        dump();
    }

    private static void testClear() {
        System.out.println("Clear test");
        System.out.println("Size before: " + queue.size());
        System.out.println("Empty before: " + queue.isEmpty());

        queue.clear();

        System.out.println("Size after: " + queue.size());
        System.out.println("Empty after: " + queue.isEmpty());
        dump();
    }

    public static void main(String[] args) {
        System.out.println("Class tests");

        fillHead(4);
        fillTail(4);
        testDelete(2);
        testPeek();
        testClear();

        System.out.println("Multiple objects test");
        ArrayQueue queue2 = new ArrayQueue();
        System.out.println("queue == queue2 : " + queue2.equals(queue));

    }
}
