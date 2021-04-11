package codes;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

// this is more optimized version of LFUCache
public class LFUCacheSol2 {

    private int capacity, min;
    private Map<Integer, Integer> values;
    private Map<Integer, Integer> counts;
    private Map<Integer, LinkedHashSet<Integer>> countSet;


    public LFUCacheSol2(int capacity) {
        this.capacity = capacity;
        min = 0;
        values = new HashMap<>(capacity);
        counts = new HashMap<>();
        countSet = new HashMap<>();
    }

    public int get(int key) {
        if (!values.containsKey(key)) return -1;
        Integer count = counts.get(key);
        if (count == min && countSet.get(count).size() == 1)
            min++;
        countSet.get(count++).remove(key);
        counts.put(key, count);
        addCountSet(key, count);
        return values.get(key);
    }

    public void put(int key, int value) {
        if (capacity <= 0) return;
        if (get(key) == -1) {
            if (values.size() == capacity) {
                Integer oldest = countSet.get(min).iterator().next();
                countSet.get(min).remove(oldest);
                counts.remove(oldest);
                values.remove(oldest);
            }
            counts.put(key, 1);
            addCountSet(key, 1);
            min = 1;
        }
        values.put(key, value);
    }

    private void addCountSet(int key, int count) {
        countSet.computeIfAbsent(count, integer -> new LinkedHashSet<>());
        countSet.get(count).add(key);
    }

    public static void main(String[] args) {
        LFUCacheSol2 cache = new LFUCacheSol2(2);
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

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

}
