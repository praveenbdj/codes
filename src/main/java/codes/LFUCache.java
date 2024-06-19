package codes;


import helper.Helper;

import java.util.*;

public class LFUCache {

    private class KeyValue {
        private final int key;
        private int value;
        private int count;
        private long time;

        public KeyValue(int key) {
            this.key = key;
            this.count = 0;
        }

        public KeyValue update() {
            this.count++;
            this.time = LFUCache.this.getTime();
            return this;
        }

        public int getCount() {
            return count;
        }

        public long getTime() {
            return time;
        }
    }

    private long nano;
    private final int capacity;
    private final Map<Integer, KeyValue> keyMap;
    private final PriorityQueueImpl<KeyValue> queue;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        keyMap = new HashMap<>(capacity);
        queue = new PriorityQueueImpl<>(Comparator.comparing(KeyValue::getCount).thenComparing(KeyValue::getTime), capacity);
    }

    public int get(int key) {
        KeyValue keyValue = keyMap.getOrDefault(key, null);
        if (keyValue == null) return -1;
        keyValue.update();
        queue.adjustIncrement(keyValue);
        return keyValue.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;
        KeyValue keyValue = keyMap.getOrDefault(key, null);
        if (keyValue == null) {
            if (keyMap.size() == capacity) {
                KeyValue pop = queue.pop();
                keyMap.remove(pop.key);
            }
            keyValue = new KeyValue(key);
            keyValue.update();
            keyMap.put(key, keyValue);
            queue.add(keyValue);
        } else {
            keyValue.update();
            queue.adjustIncrement(keyValue);
        }
        keyValue.value = value;
    }

    private long getTime() {
        return nano++;
    }

    private static class PriorityQueueImpl<E> {

        private final List<E> list;
        private final Comparator<E> comparator;
        private final Map<E, Integer> indexes;

        public PriorityQueueImpl(Comparator<E> comparator, int size) {
            this.comparator = comparator;
            list = new ArrayList<>(size);
            indexes = new HashMap<>(size);
        }

        public void add(E var) {
            add(list.size(), var);
            heapifyUp(list.size() - 1);
        }

        public E peek() {
            return this.list.size() == 0 ? null : this.list.get(0);
        }

        public E pop() {
            if (this.list.size() == 0) return null;
            E first = this.list.get(0);
            E last = list.get(list.size() - 1);

            list.remove(list.size() - 1);//TODO complexity?
            indexes.remove(last);

            indexes.remove(first);

            add(0, last);
            heapifyDown(0);
            return first;
        }

        public void adjustIncrement(E var) {
            Integer i = indexes.get(var);
            heapifyDown(i);
        }

        private void heapifyDown(int i) {
            if (i < 0 || i >= list.size() / 2) return;
            int left = getLeft(i);
            int right = getRight(i);
            if (left < list.size() && comparator.compare(list.get(left), list.get(i)) < 0 ||
                    right < list.size() && comparator.compare(list.get(right), list.get(i)) < 0) {
                if (right >= list.size() || comparator.compare(list.get(left), list.get(right)) < 0) {
                    E ex = list.get(i);
                    add(i, list.get(left));
                    add(left, ex);
                    heapifyDown(left);
                } else {
                    E ex = list.get(i);
                    add(i, list.get(right));
                    add(right, ex);
                    heapifyDown(right);
                }
            }

        }

        private void heapifyUp(int i) {
            E var = list.get(i);
            int parent = getParent(i);
            while (parent != -1) {
                E pEl = list.get(parent);
                if (this.comparator.compare(var, pEl) < 0) {
                    add(parent, var);
                    add(i, pEl);
                    i = getParent(i);
                    parent = getParent(i);
                    continue;
                }
                break;
            }
        }

        public void printOut() {
            if (list.size() > 0) {
                System.out.println(Helper.serialize(list.toArray()));
            }
        }

        private void add(int i, E var) {
            if (i >= list.size()) list.add(var);
            else list.set(i, var);
            indexes.put(var, i);
        }

        private int getParent(int i) {
            return i < 1 ? -1 : (i - 1) / 2;
        }

        private int getLeft(int i) {
            return 2 * i + 1;
        }

        private int getRight(int i) {
            return 2 * i + 2;
        }

    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
