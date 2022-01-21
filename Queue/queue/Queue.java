package queue;

// Model:
//      {a[1] ... a[n]}
//      a[1] - head
//      a[n] - tail
//      n - size
// Inv:
//      n >= 0
//      forall i in [1..n] a[i] != null
// Immutable:
//      n == n' && a[1..n] == a'[1..n']

public interface Queue {
    // Pre: e != null
    // Post: n == n' + 1 && a[1..n'] == a'[1..n'] && a[n' + 1] == e
    void enqueue(Object e);

    // Pre: size > 0
    // Post: R == a[1] && Immutable
    Object element();

    // Pre: size > 0
    // Post: R == a'[1] && a[1..n-1] = a'[2..n'] && n == n' - 1
    Object dequeue();

    // Pre:
    // Post: R == n && Immutable
    int size();

    // Pre:
    // Post: R == (n == 0) && Immutable
    boolean isEmpty();

    // Pre:
    // Post: n == 0
    void clear();

    // Pre: k > 0
    // Post: R == [ a[k], a[2k] ... a[(n' / k) * k] ] && Immutable
    Queue getNth(int k);

    // Pre: k > 0
    // Post: a == [ a'[1] ... a'[k - 1] , a'[k + 1] ... a'[2k - 1], ... , a'[(n' / k) * k + 1] ... a'[n'] ]
    // && n = n' - n' / k
    void dropNth(int k);

    // Pre: k > 0
    // Post: R == [ a[k], a[2k] ... a[(n' / k) * k] ]
    // && a == [ a'[1] ... a'[k - 1] , a'[k + 1] ... a'[2k - 1], ... , a'[(n' / k) * k + 1] ... a'[n'] ]
    // && n = n' - n' / k
    Queue removeNth(int k);
}
