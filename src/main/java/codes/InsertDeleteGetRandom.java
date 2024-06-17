package codes;


//leetcode 380
//https://leetcode.com/problems/insert-delete-getrandom-o1
/*
* Implement the RandomizedSet class:
RandomizedSet() Initializes the RandomizedSet object.
bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one element exists when this method is called). Each element must have the same probability of being returned.
You must implement the functions of the class such that each function works in average O(1) time complexity.
*
* */


import java.util.*;

public class InsertDeleteGetRandom {

    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();

        System.out.println(randomizedSet.insert(0));
        System.out.println(randomizedSet.remove(0));
        System.out.println(randomizedSet.insert(-1));
        System.out.println(randomizedSet.remove(0));
        System.out.println(randomizedSet.getRandom());

    }

    static class RandomizedSet {

        private final List<Integer> elements;
        private final Map<Integer, Integer> elementToIndexMap;
        private final Random random;

        public RandomizedSet() {
            random = new Random();
            elements = new ArrayList<>();
            elementToIndexMap = new HashMap<>();
        }

        public boolean insert(int val) {
            if (elementToIndexMap.containsKey(val)) {
                return false;
            }
            elements.add(val);
            int lastElementIndex = elements.size() - 1;
            elementToIndexMap.put(val, lastElementIndex);
            return true;
        }

        public boolean remove(int val) {
            //if it is not present return false
            if (!elementToIndexMap.containsKey(val)) {
                return false;
            }

            Integer indexOfElementToBeRemoved = elementToIndexMap.get(val);
            int lastElementIndex = elements.size() - 1;

            //replace the element with the last element
            elements.set(indexOfElementToBeRemoved, elements.get(lastElementIndex));
            elementToIndexMap.put(elements.get(indexOfElementToBeRemoved), indexOfElementToBeRemoved);
            elementToIndexMap.remove(val);
            elements.remove(lastElementIndex);
            return true;
        }

        public int getRandom() {
            int size = elements.size();
            int i = random.nextInt(size);
            return elements.get(i);
        }
    }

}
