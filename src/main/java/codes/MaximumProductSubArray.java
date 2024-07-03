package codes;

import java.util.Optional;

//leetcode 152
//https://leetcode.com/problems/maximum-product-subarray
public class MaximumProductSubArray {

    public static int maxProduct(int[] nums) {
        int maxProduct = Integer.MIN_VALUE;
        Optional<Integer> negProd = Optional.empty();
        Optional<Integer> posProd = Optional.empty();
        for (int num : nums) {
            if (num > 0) {
                int p1 = negProd.orElse(1) * num;
                int p2 = posProd.orElse(1) * num;
                maxProduct = Math.max(maxProduct, p2);
                posProd = Optional.of(p2);
                if (negProd.isPresent()) {
                    negProd = Optional.of(p1);
                }
            } else if (num < 0) {
                int p1 = negProd.orElse(1) * num;
                int p2 = posProd.orElse(1) * num;
                maxProduct = Math.max(maxProduct, num);
                maxProduct = Math.max(maxProduct, p1);
                maxProduct = Math.max(maxProduct, p2);
                if (negProd.isPresent()) {
                    negProd = Optional.of(p2);
                    posProd = Optional.of(p1);
                } else {
                    negProd = Optional.of(Math.min(p1, p2));
                    posProd = Optional.empty();
                }
            } else {
                maxProduct = Math.max(maxProduct, num);
                negProd = Optional.empty();
                posProd = Optional.empty();
            }
        }

        return maxProduct;
    }

    public static void main(String[] args) {
        System.out.println(
                maxProduct(new int[]{2, 3, -2, 4})
        );
        System.out.println(
                maxProduct(new int[]{-2, 0, -1})
        );
        System.out.println(
                maxProduct(new int[]{-1, 4, -4, 5, -2, -1, -1, -2, -3})
        );
        System.out.println(
                maxProduct(new int[]{0, 10, 10, 10, 10, 10, 10, 10, 10, 10, -10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 0})
        );
        System.out.println(
                maxProduct(new int[]{7, -2, -4})
        );
        System.out.println(
                maxProduct(new int[]{2, -5, -2, -4, 3})
        );
    }

}
