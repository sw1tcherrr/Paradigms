package queue;

import static queue.ArrayQueueModule.*;

public class ModuleTest {
    private static void dump() {
        System.out.println(toStr());
        System.out.println();
    }

    private static void fillTail(int n) {
        System.out.println("Enqueue test");
        for (int i = 0; i < n; i++) {
            enqueue(i);
        }

        dump();
    }

    private static void fillHead(int n) {
        System.out.println("Push test");
        for (int i = 10; i < 10 + n; i++) {
            push(i);
        }

        dump();
    }

    private static void testPeek() {
        System.out.println("Peek/element test");

        Object e1 = element(), e2 = element();
        System.out.println(e1 + " " + e2);
        System.out.println("element 1 == element 2 : " + e1.equals(e2));

        e1 = peek();
        e2 = peek();
        System.out.println(e1 + " " + e2);
        System.out.println("peek 1 == peek 2 : " + e1.equals(e2));

        dump();
    }

    private static void testDelete(int n) {
        System.out.println("Deletion test");
        System.out.println("From head:");
        for (int i = 0; i < n; i++) {
            System.out.println(dequeue());
        }
        System.out.println("From tail:");
        for (int i = 0; i < n; i++) {
            System.out.println(remove());
        }

        dump();
    }

    private static void testClear() {
        System.out.println("Clear test");
        System.out.println("Size before: " + size());
        System.out.println("Empty before: " + isEmpty());

        clear();

        System.out.println("Size after: " + size());
        System.out.println("Empty after: " + isEmpty());
        dump();
    }

    public static void main(String[] args) {
        System.out.println("Module tests");

        fillHead(4);
        fillTail(4);
        testDelete(2);
        testPeek();
        testClear();

        System.out.println("Multiple objects test");
        System.out.println("Can't create objects");

    }
}
