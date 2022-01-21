package search;

public class BinarySearchMax {
    // Pre: a != null && exists k: forall i in [0..k - 1]:  a[i] < a[i + 1]  && forall i in [k..a.length-2]: a[i] > a[i + 1]
    // Post: 0 <= R <= a.length - 1 && R is first index, that: a[R] > a[R + 1] (if R + 1 < a.length) && a[R] - max(a[0..a.length])
    public static int findSplitIterative(int[] a) {
        // Pre:
        int l = -1;
        // Post: l == -1

        // Pre: a != null
        int r = a.length;
        // Post: r == a.length && r > 0

        // Inv: a[l] < a[l + 1] if a[l+1] exists
        // && a[r] > a[r + 1] if a[r+1] exists
        // && l <= r
        while (r - l > 1) {
            // Pre: Inv && r - l > 1
            int m = (l + r) / 2;
            // Post: m == (l + r) / 2 && l < m < r && Inv

            if (m + 1 < a.length && a[m] < a[m + 1]) {
                // Pre: m + 1 < a.length && a[m] < a[m + 1]
                l = m;
                // Post: l == m && a[l + 1] exists && a[l] < a[l + 1] && l < r (-> Inv)
                // && l > l' && r == r' -> loop will end
            } else {
                // Pre: m + 1 >= a.length || a[m] >= a[m + 1]
                r = m;
                // Post: r == m && (a[r] - last element || a[r] < a[r + 1]) && l < r (-> Inv)
                // && r < r' && l == l' -> loop will end
            }
        }
        // Post: l == r - 1 && Inv && Pre:
        // a[r] > a[r + 1] if a[r+1] exists
        // && a[l] < a[r]
        // -> r (- first such index that a[r] > a[r + 1]
        // && forall i in [0..l] a[i] < r && forall i in [r + 1, a.length]  a[i] < r)
        // -> a[r] - max

        return r;
    }

    // Pre: a != null && -1 <= l < a.length && 0 <= r <= a.length && l < r && exists k: forall i in [0..k - 1]:  a[i] < a[i + 1]  && forall i in [k..a.length-2]: a[i] > a[i + 1]
    // Inv: a[l] < a[l + 1] if a[l+1] exists && a[r] > a[r + 1] if a[r+1] exists && -1 <= l < a.length && 0 <= r <= a.length && l <= r
    // Post: 0 <= R <= a.length - 1 && R is first index, that: a[R] > a[R + 1] (if R + 1 < a.length) && a[R] - max(a[0..a.length])
    public static int findSplitRecursive(int[] a, int l, int r) {
        if (r - l <= 1) {
            // Pre: l == r - 1 && Inv && Pre:
            // a[r] > a[r + 1] if a[r+1] exists
            // && a[l] < a[r]
            // -> r (- first such index that a[r] > a[r + 1]
            // && forall i in [0..l] a[i] < r && forall i in [r + 1, a.length]  a[i] < r)
            // -> a[r] - max
            return r;
        }

        // Pre: Inv && r - l > 1
        int m = (l + r) / 2;
        // Post: m == (l + r) / 2 && l < m < r && Inv

        if (m + 1 < a.length && a[m] < a[m + 1]) {
            // Pre: m + 1 < a.length && a[m] < a[m + 1] && Inv
            // && l > l' && r == r' -> recursion will end
            return findSplitRecursive(a, m, r);
        } else {
            // Pre: m + 1 >= a.length || a[m] >= a[m + 1] && Inv
            // && r < r' && l == l' -> recursion will end
            return findSplitRecursive(a, l, m);
        }
    }

    // Pre: args != null && args.length > 0 && forall i in [0..args.length - 1]:  args[i] can be parsed to int &&
    // && exists k: forall i in [0..k - 1]:  a[i] < a[i + 1]  &&
    // && forall i in [k..a.length-2]: a[i] > a[i + 1]
    // Post: a[k] is printed
    public static void main(String[] args) {
        // Pre:
        int[] a = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            a[i] = Integer.parseInt(args[i]);
        }
        // Post: a - array of integers from args

        // Pre: a != null, -1 <= -1 < a.length, 0 <= a.length <= a.length
        int k = findSplitRecursive(a, -1, a.length);
        // Post: k - index of max element

        System.out.println(a[k]);
    }
}
