package codes;

import java.util.ArrayList;
import java.util.List;

public class AmazonHackerRank {

    public static long howManySwaps(List<Integer> arr) {
        return sortAndCountSwaps(arr, 0, arr.size() - 1);
    }

    private static long sortAndCountSwaps(List<Integer> arr, int l, int r) {
        long count = 0;
        if (l < r) {
            int m = (l + r) / 2;
            count += sortAndCountSwaps(arr, l, m);
            count += sortAndCountSwaps(arr, m + 1, r);
            count += mergeAndCountSwaps(arr, l, m, r);
        }
        return count;
    }

    private static long mergeAndCountSwaps(List<Integer> arr, int l, int m, int r) {
        List<Integer> left = arr.subList(l, m + 1);
        List<Integer> right = arr.subList(m + 1, r + 1);
        long swaps = 0;
        int i = 0, j = 0, k = l;
        while (i < left.size() && j < right.size()) {
            if (left.get(i) <= right.get(j)) {
                arr.set(k++, left.get(i++));
            } else {
                arr.set(k++, right.get(j++));
                swaps += (m + 1) - (l + i);
            }
        }
        while (i < left.size()) {
            arr.set(k++, left.get(i++));
        }
        while (j < right.size()) {
            arr.set(k++, right.get(j++));
        }
        return swaps;
    }

    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(20);
        arr.add(6);
        arr.add(4);
        arr.add(5);
        System.out.println(
                howManySwaps(arr)
        );
    }

    private static List<Integer> subList(List<Integer> arr, int l, int r) {
        List<Integer> list = new ArrayList<Integer>();
        int i = l;
        while (i < r) {
            list.add(arr.get(i++));
        }
        return list;
    }
}
