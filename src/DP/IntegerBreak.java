package DP;
public class IntegerBreak {
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        int max = 0;
        for (int i = 1; i < n; i++) {
            for (int j = i; j <= n; j++) {
                dp[j] = Math.max(dp[j], dp[j - i] * i);
                if (j == n) {
                    if (max < dp[j])
                        max = dp[j];
                }
            }
        }
        return max;
    }
    public static void main(String[] args) {
        // new IntegerBreak().integerBreak(10);
        // new IntegerBreak().findMaxForm(new String[]{"10","0001","111001","1","0"}, 5, 3);
        //new IntegerBreak().stringShift("wpdhhcj", new int[][]{{0, 7}, {1, 7}, {1, 0}, {1, 3}, {0, 3}, {0, 6}, {1, 2}});
       new MinimumPathSum().minPathSum(new int[][]{{1,3,1},{1,5,1},{4,2,1}});

    }
    public int findMaxForm(String[] strs, int countGivenZeroes, int countGivenOnes) {
        int[][] table = new int[countGivenZeroes + 1][countGivenOnes + 1];

        for (String currString : strs) {
            int countOnesInCurrString = currString.length() - currString.replace("1", "").length();
            int countZeroesInCurrString = currString.length() - countOnesInCurrString;

            for (int i = countGivenZeroes; i >= countZeroesInCurrString; i--)
                for (int j = countGivenOnes; j >= countOnesInCurrString; j--)
                    table[i][j] = Math.max(table[i][j], table[i - countZeroesInCurrString][j - countOnesInCurrString] + 1);
        }
        return table[countGivenZeroes][countGivenOnes];
    }
    public String stringShift(String s, int[][] shift) {
        int cumulativeamount = 0;
        for (int i = 0; i < shift.length; i++) {
            if (shift[i][0] == 0) cumulativeamount = cumulativeamount - shift[i][1];
            else cumulativeamount = cumulativeamount + shift[i][1];
        }
        cumulativeamount = cumulativeamount % s.length();
        char[] newString = new char[s.length()];
        for (int j = 0; j < s.length(); j++) {
            int indexnew = j + cumulativeamount;
            if (indexnew >= s.length()) indexnew = indexnew - s.length();
            if (indexnew < 0) indexnew = indexnew + s.length();
            newString[indexnew] = s.charAt(j);
        }
        return new String(newString);
    }
}