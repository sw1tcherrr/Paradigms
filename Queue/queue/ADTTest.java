package queue;

import static queue.ArrayQueueADT.*;

public class ADTTest {
    private static final queue.ArrayQueueADT queue = new queue.ArrayQueueADT();

    private static void dump() {
        System.out.println(toStr(queue));
        System.out.println();
    }

    private static void fillTail(int n) {
        System.out.println("Enqueue test");
        for (int i = 0; i < n; i++) {
            enqueue(queue, i);
        }

        dump();
    }

    private static void fillHead(int n) {
        System.out.println("Push test");
        for (int i = 10; i < 10 + n; i++) {
            push(queue, i);
        }

        dump();
    }

    private static void testPeek() {
        System.out.println("Peek/element test");

        Object e1 = element(queue), e2 = element(queue);
        System.out.println(e1 + " " + e2);
        System.out.println("element 1 == element 2 : " + e1.equals(e2));

        e1 = peek(queue);
        e2 = peek(queue);
        System.out.println(e1 + " " + e2);
        System.out.println("peek 1 == peek 2 : " + e1.equals(e2));

        dump();
    }

    private static void testDelete(int n) {
        System.out.println("Deletion test");
        System.out.println("From head:");
        for (int i = 0; i < n; i++) {
            System.out.println(dequeue(queue));
        }
        System.out.println("From tail:");
        for (int i = 0; i < n; i++) {
            System.out.println(remove(queue));
        }

        dump();
    }

    private static void testClear() {
        System.out.println("Clear test");
        System.out.println("Size before: " + size(queue));
        System.out.println("Empty before: " + isEmpty(queue));

        clear(queue);

        System.out.println("Size after: " + size(queue));
        System.out.println("Empty after: " + isEmpty(queue));
        dump();
    }

    public static void main(String[] args) {
        System.out.println("ADT tests");

        fillHead(4);
        fillTail(4);
        testDelete(2);
        testPeek();
        testClear();

        System.out.println("Multiple objects test");
        queue.ArrayQueueADT queue2 = new queue.ArrayQueueADT();
        System.out.println("queue == queue2 : " + queue2.equals(queue));

    }
}
