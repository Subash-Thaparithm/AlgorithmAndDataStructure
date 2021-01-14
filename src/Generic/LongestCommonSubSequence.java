package Generic;

public class LongestCommonSubSequence {
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length() +1][text2.length()+1];

        //First row and first column fill with 0
        for(int i=0; i< dp[0].length; i++){
            dp[0][i] = 0;
        }
        for(int i=0; i< dp.length; i++){
            dp[i][0] = 0;
        }

        for(int i=1; i< dp.length; i++){
            for(int j=1; j< dp[0].length; j++) {
                if (text1.charAt(i-1) != text2.charAt(j-1) ) dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                else dp[i][j] = 1 + dp[i-1][j-1];
            }
        }
        return dp[text1.length()][text2.length()];

    }

    public static void main(String[] args) {
        new LongestCommonSubSequence().longestCommonSubsequence("abcde", "ace" );
    }
}
