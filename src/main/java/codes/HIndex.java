package codes;

import java.util.Arrays;

//leetcode 274
//https://leetcode.com/problems/h-index
public class HIndex {
    public static int hIndex(int[] citations) {
        Arrays.sort(citations);
        int length = citations.length;
        int citation = 0;
        for (int i = length - 1; i >= 0; i--) {
            if (citations[i] >= citation + 1) {
                citation++;
            }
        }
        return citation;
    }

    public static void main(String[] args) {
        System.out.println(
                hIndex(
                        new int[]{3, 0, 6, 1, 5}
                )
        );
        System.out.println(
                hIndex(
                        new int[]{1, 3, 1}
                )
        );
        System.out.println(
                hIndex(
                        new int[]{4, 4, 4, 4, 4, 4, 5}
                )
        );
        System.out.println(
                hIndex(
                        new int[]{100}
                )
        );
    }
}
