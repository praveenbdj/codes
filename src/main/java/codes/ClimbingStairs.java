package codes;

//leetcode 70
//https://leetcode.com/problems/climbing-stairs
public class ClimbingStairs {
    public int climbStairs(int n) {
        int[] store = new int[n + 1];
        return noOfSteps(n, store);
    }

    private int noOfSteps(int n, int[] store) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        if (store[n] != 0) {
            return store[n];
        }
        store[n] = noOfSteps(n - 1, store) + noOfSteps(n - 2, store);
        return store[n];
    }

    public static void main(String[] args) {
        ClimbingStairs climbingStairs = new ClimbingStairs();
        System.out.println(
                climbingStairs.climbStairs(1)
        );
        System.out.println(
                climbingStairs.climbStairs(2)
        );
        System.out.println(
                climbingStairs.climbStairs(3)
        );
        System.out.println(
                climbingStairs.climbStairs(4)
        );
        System.out.println(
                climbingStairs.climbStairs(5)
        );
    }


}
