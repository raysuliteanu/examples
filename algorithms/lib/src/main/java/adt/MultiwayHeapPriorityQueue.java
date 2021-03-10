package adt;

/** from Algorithms in Java by Sedgewick, Pt 5 Ch 20; see Program 20.10 */
public class MultiwayHeapPriorityQueue {
    public static final int DEFAULT_HEAP_NUM_WAYS = 3;
    private int size = 0;
    private final int ways;
    private final int[] pq;
    private final int[] qp;

    // NOTE: weights array is modified outside of this class; do not make a copy of the array on construction!
    private final Double[] weights;

    public MultiwayHeapPriorityQueue(int maxSize, final Double[] weights) {
        this(maxSize, weights, DEFAULT_HEAP_NUM_WAYS);
    }

    public MultiwayHeapPriorityQueue(int maxSize, final Double[] weights, final int ways) {
        this.weights = weights;
        pq = new int[maxSize + 1];
        qp = new int[maxSize + 1];
        for (int i = 0; i <= maxSize; i++) {
            pq[i] = 0;
            qp[i] = 0;
        }

        this.ways = ways;
    }

    public boolean empty() {
        return size == 0;
    }

    public void insert(int v) {
        pq[++size] = v;
        qp[v] = size;
        swim(size);
    }

    public int getMin() {
        exchange(1, size);
        sink(size - 1);
        return pq[size--];
    }

    public void lower(int k) {
        swim(qp[k]);
    }

    private boolean less(int i, int j) {
        return weights[pq[i]] < weights[pq[j]];
    }

    private void exchange(int i, int j) {
        int temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    private void swim(int k) {
        while (k > 1 && less(k, (k + ways - 2) / ways)) {
            exchange(k, (k + ways - 2) / ways);
            k = (k + ways - 2) / ways;
        }
    }

    private void sink(int N) {
        int j;
        int k = 1;
        while ((j = ways * (k - 1) + 2) <= N) {
            for (int i = j + 1; i < j + ways && i <= N; i++) {
                if (less(i, j)) {
                    j = i;
                }
            }

            if (!less(j, k)) {
                break;
            }

            exchange(k, j);
            k = j;
        }
    }
}
