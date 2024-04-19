import java.util.*;

/**
 * @param <V>   {@inheritDoc}
 * @param <Key> {@inheritDoc}
 */
public class BinaryMinHeapImpl<Key extends Comparable<Key>, V> implements BinaryMinHeap<Key, V> {
    /**
     * {@inheritDoc}
     */

    ArrayList<Entry<Key, V>> heap;
    HashMap<V, Integer> tracker;

    BinaryMinHeapImpl() {
        heap = new ArrayList<>();
        tracker = new HashMap<>();
    }

    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public boolean isEmpty() {
        return (0 == size());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsValue(V value) {
        return (tracker.containsKey(value));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(Key key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        if (containsValue(value)) {
            throw new IllegalArgumentException("contains value");
        }

        Entry<Key, V> enter = new Entry<>(key, value);
        heap.add(enter);
        tracker.put(value, heap.size() - 1);

        newInsert(heap.size());
    }
    void newInsert(int i) {
        while (i > 1 && (heap.get(i - 1).key).compareTo(heap.get(i / 2 - 1).key) < 0) {
            Entry<Key, V> c = heap.get(i - 1);
            Entry<Key, V> p = heap.get(i / 2 - 1);

            heap.set(i - 1, heap.get(i / 2 - 1));
            tracker.remove(p.value);
            tracker.put(p.value, i - 1);

            heap.set(i / 2 - 1, c);
            tracker.remove(c.value);
            tracker.put(c.value, i / 2 - 1);

            i = i / 2;
        }
    }

    void swap(int parent, int index) {
        V v1 = heap.get(parent).value;
        V v2 = heap.get(index).value;

        Entry<Key, V> e1 = heap.get(parent);
        Entry<Key, V> e2 = heap.get(index);

        heap.set(parent, e2);
        heap.set(index, e1);

        tracker.put(v1, index);
        tracker.put(v2, parent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void decreaseKey(V value, Key newKey) {
        if (newKey == null) {
            throw new IllegalArgumentException();
        }
        if (!containsValue(value)) {
            throw new NoSuchElementException();
        }

        int index = tracker.get(value);
        Key oldKey = heap.get(index).key;

        if (newKey.compareTo(oldKey) >= 0) {
            throw new IllegalArgumentException();
        }

        heap.set(index, new Entry<>(newKey, value));
        tracker.put(value, index);

        newInsert(index + 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entry<Key, V> peek() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException();
        }
        return heap.get(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entry<Key, V> extractMin() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException();
        }
        Entry<Key, V> bottom = heap.get(heap.size() - 1);
        Entry<Key, V> top = heap.get(0);

        heap.set(0, bottom);
        tracker.put(bottom.value, 0);

        heap.remove(size() - 1);
        tracker.remove(top.value);

        minHeapify(bottom.key, 0);

        return top;
    }
    void minHeapify(Key key, int index) {
        int l = 2 * index + 1;
        int r = 2 * index + 2;
        int min;

        if ((l < size()) && (l >= index) && (key.compareTo(heap.get(l).key)) > 0) {
            min = l;
        } else {
            min = index;
        }
        if ((r < size()) && (r >= index) && ((heap.get(min).key).compareTo(heap.get(r).key)) > 0) {
            min = r;
        }
        if (min != index) {
            swap(min, index);
            minHeapify(key, min);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<V> values() {
        return tracker.keySet();
    }
}