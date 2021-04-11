package codes;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

class LRUCache {

    private int capcity;
    private Map<Integer, Integer> values;
    private LinkedHashSet<Integer> set;

    public LRUCache(int capacity) {
        this.capcity = capacity;
        values = new HashMap<>(capacity);
        set = new LinkedHashSet<>(capacity);
    }

    public int get(int key) {
        Integer val = values.getOrDefault(key, -1);
        if (val != -1) {
            set.remove(key);
            set.add(key);
        }
        return val;
    }

    public void put(int key, int value) {
        if (values.containsKey(key)) {
            set.remove(key);
        } else if (values.size() == capcity) {
            Integer oldest = set.iterator().next();
            set.remove(oldest);
            values.remove(oldest);
        }
        set.add(key);
        values.put(key, value);
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
