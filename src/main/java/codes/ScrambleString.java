package codes;

//leetcode 87
//https://leetcode.com/problems/scramble-string/
public class ScrambleString {

    public static void main(String[] args) {
        ScrambleString scrambleString = new ScrambleString();

        System.out.println(
                scrambleString.isScramble("abccde", "edabcc")
        );
        System.out.println(
                scrambleString.isScramble("abccde", "deccba")
        );
        System.out.println(
                scrambleString.isScramble("abccde", "abcdec")
        );
        System.out.println(
                scrambleString.isScramble("abccde", "abcdec")
        );
        System.out.println(
                scrambleString.isScramble("abcdefghij", "efghijabcd")
        );
        System.out.println(
                scrambleString.isScramble("abcdefghijklmnop", "efghijklmnopabcd")
        );
        System.out.println(
                scrambleString.isScramble("abcd", "abdc")
        );
    }

    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        int length = s1.length();

        Boolean[][][][] dp = new Boolean[length][length][length][length];

        return isScramble(s1, s2, dp, 0, length - 1, 0, length - 1);
    }

    private boolean isScramble(String s1, String s2,
                               Boolean[][][][] dp,
                               int i, int j,
                               int k, int l) {
        int length = j - i;

        if (length == 0) {
            return
                    s1.charAt(i) == s2.charAt(k);
        }

        if (dp[i][j][k][l] != null) {
            return dp[i][j][k][l];
        }

        boolean ans = false;
        for (int piv = 0; piv < length; piv++) {
            boolean notShuffled = isScramble(s1, s2, dp, i, i + piv, k, k + piv)
                    && isScramble(s1, s2, dp, i + piv + 1, j, k + piv + 1, l);

            boolean shuffled = isScramble(s1, s2, dp, i, i + piv, l - piv, l)
                    && isScramble(s1, s2, dp, i + piv + 1, j, k, l - piv - 1);
            ans = notShuffled || shuffled;
            if (ans) {
                break;
            }
        }
        dp[i][j][k][l] = ans;
        return ans;
    }

}
