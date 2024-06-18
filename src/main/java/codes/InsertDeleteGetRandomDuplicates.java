package codes;


//leetcode 381
//https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed

import java.util.*;

public class InsertDeleteGetRandomDuplicates {

    public static void main(String[] args) {
        RandomizedCollection randomizedCollection = new RandomizedCollection();

//        System.out.println(randomizedCollection.insert(4));
//        System.out.println(randomizedCollection.insert(3));
//        System.out.println(randomizedCollection.insert(4));
//        System.out.println(randomizedCollection.insert(2));
//        System.out.println(randomizedCollection.insert(4));
//        System.out.println(randomizedCollection.getRandom());
//        System.out.println(randomizedCollection.remove(4));
//        System.out.println(randomizedCollection.remove(3));
//        System.out.println(randomizedCollection.remove(4));
//        System.out.println(randomizedCollection.remove(4));
//        System.out.println(randomizedCollection.getRandom());

        System.out.println(randomizedCollection.insert(1));
        System.out.println(randomizedCollection.remove(1));
        System.out.println(randomizedCollection.insert(1));
        System.out.println(randomizedCollection.getRandom());


    }

    static class RandomizedCollection {

        private final List<Integer> elements;
        private final Map<Integer, Set<Integer>> elementToIndexMap;
        private final Random random;

        public RandomizedCollection() {
            random = new Random();
            elements = new ArrayList<>();
            elementToIndexMap = new HashMap<>();
        }

        public boolean insert(int val) {
            boolean returnValue;
            if (elementToIndexMap.containsKey(val)) {
                returnValue = false;
            } else {
                returnValue = true;
                elementToIndexMap.put(val, new HashSet<>());
            }
            elements.add(val);
            int lastElementIndex = elements.size() - 1;
            elementToIndexMap.get(val).add(lastElementIndex);
            return returnValue;
        }

        public boolean remove(int val) {
            //if it is not present return false
            if (!elementToIndexMap.containsKey(val)) {
                return false;
            }

            int lastElementIndex = elements.size() - 1;
            //if last item is val
            if (elements.get(lastElementIndex) == val) {
                elements.remove(lastElementIndex);
                elementToIndexMap.get(val).remove(lastElementIndex);
                if (elementToIndexMap.get(val).isEmpty()) {
                    elementToIndexMap.remove(val);
                }
                return true;
            }

            Set<Integer> allIndicesOfVal = elementToIndexMap.get(val);
            int pivotIndex = allIndicesOfVal.iterator().next();
            int newElement = elements.get(lastElementIndex);

            //replace the element with the last element
            elements.set(pivotIndex, newElement);
            allIndicesOfVal.remove(pivotIndex);
            if (allIndicesOfVal.isEmpty()) {
                elementToIndexMap.remove(val);
            }

            Set<Integer> replacedElementIndices = elementToIndexMap.get(newElement);
            replacedElementIndices.remove(lastElementIndex);
            replacedElementIndices.add(pivotIndex);
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
