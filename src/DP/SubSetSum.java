package DP;

public class SubSetSum {
    public boolean canPartition(int [] nums, int sum){
       /* int sum = 0;
        for (int i = 0; i <nums.length ; i++) {
            sum = sum + nums[i];
        }
        sum = sum /2;
        if (sum % 2 != 0) return false ;
        */
       boolean [][] dp = new boolean[nums.length][sum+1];
       // First column always true
        for (int i = 0; i <nums.length ; i++) {
            dp[i][0] = true ;
        }
        //First row from  second column
        for (int i = 1; i <sum +1 ; i++) {
           if (nums[0] == i)  dp[0][i] = true ;
           else  dp[0][i] = false;
        }
        for (int i = 1; i < nums.length ; i++) {
            for (int j = 1; j < sum+1 ; j++) {
                  if (j < nums[i]) dp[i][j] = dp[i-1][j];
                  else {
                      if (dp[i-1][j] || dp[i-1][j-nums[i]]) dp[i][j] = dp[i-1][j];
                  }
            }
        }
        return dp[nums.length-1][sum];
    }

    public static void main(String[] args) {
        new SubSetSum().canPartition(new int[]{1,5,11,5},10);
    }
}