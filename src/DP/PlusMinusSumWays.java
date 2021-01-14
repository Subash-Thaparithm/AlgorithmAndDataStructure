package DP;
import java.util.List;
public class PlusMinusSumWays {
    public int findTargetSumWays(int[] nums, int S) {
        int index = nums.length - 1;
        int curr_sum = 0;
        return dp(nums, S, index, curr_sum);
    }
    public int dp(int[] nums, int target, int index, int curr_sum){
        // Base Cases
        if (index < 0 && curr_sum == target)
        return 1;
        if (index < 0)
        return 0 ;

        // Decisions
        int positive = dp(nums, target, index-1, curr_sum + nums[index]);
        int negative = dp(nums, target, index-1, curr_sum - nums[index]);

        return positive + negative;
    }
    public int findTargetSumWays2(int[] nums, int S) {
        int sum = 0;
        for(int n: nums){
            sum += n;
        }
        if (S < -sum || S > sum) { return 0;}

        int[][] dp = new int[nums.length + 1][ 2 * sum + 1];
        dp[0][0 + sum] = 1; // 0 + sum means 0, 0 means -sum,  check below graph
        for(int i = 1; i <= nums.length; i++){
            for(int j = 0; j < 2 * sum + 1; j++){

                if(j + nums[i - 1] < 2  * sum + 1) dp[i][j] += dp[i - 1][j + nums[i - 1]];
                if(j - nums[i - 1] >= 0) dp[i][j] += dp[i - 1][j - nums[i - 1]];
            }
        }
        return dp[nums.length][sum + S];
    }
    // https://leetcode.com/problems/target-sum/discuss/97334/Java-(15-ms)-C%2B%2B-(3-ms)-O(ns)-iterative-DP-solution-using-subset-sum-with-explanation
    // Find a subset of nums that need to be positive, and the rest of them negative, such that the sum is equal to target
    //Let P be the positive subset and N be the negative subset
    //Then let's see how this can be converted to a subset sum problem:
    //sum(P) - sum(N) = target
    //sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
    //2 * sum(P) = target + sum(nums)
    public int findTargetSumWays3(int[] nums, int s) {
        int sum = 0;
        for (int n : nums)
            sum += n;
        return sum < s || (s + sum) % 2 > 0 ? 0 : subsetSum(nums, (s + sum) >>> 1);
    }
    public int subsetSum(int[] nums, int sum) {
        int[] dp = new int[sum + 1];
        dp[0] = 1;
        for (int eachProvidedNumber : nums)
            for (int i = sum; i >= eachProvidedNumber; i--)
                dp[i] += dp[i - eachProvidedNumber];
        return dp[sum];
    }
    /*public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int n : candidates)
            for (int i = target; i >= n; i--)
                dp[i] += dp[i - n];
        return dp[target];
    }*/
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
    private static boolean isSumSubsetExist(int[] array, int sum) {
        int col = sum+1;
        int row = array.length+1;
        boolean[][] dp = new boolean[row][col];
        for(int i=0;i<row;i++) {
            dp[i][0] = true;
        }
        for(int i=1;i<col;i++) {
            dp[0][i] = false;
        }
        for (int i = 1; i < row; i++) {
            int value = array[i-1];
            /* Create DP. */
            for (int j = 1; j < col; j++) {
                if(j<value) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j-value];
                }
            }
        }
        return dp[row-1][col-1];
    }
    private int maxDifferenceSubset(int[] stones) {
        int n = stones.length;
        if (n == 1) return stones[0];
        //sum = sum of all weights in A
        int sum=0;
        for (int num : stones) {
            sum = sum + num;
        }
        boolean dp[][] = new boolean[n + 1][sum / 2];
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
            dp[0][i] = false;
            dp[0][0] = true;
        }
         int reach = 0;
        for (int i = 1; i < n; i++)
            for (int j = 1; i <= (sum / 2) + 1; j++){
                dp[i][j] = dp[i - 1][j];
                if (j >= stones[i - 1])
                dp[i][j] = dp[i][j] || dp[ i - 1][j - stones[i - 1]];
                if(dp[n][j]) reach = Math.max(reach, j);
             }
        return sum - reach*2 ;
    }
    private int maxDifferenceSubset1(int[] stones) {
        int n = stones.length;
        int total = 0;
        for(int i = 0; i < n; i++){
            total += stones[i];
        }
        int req = total / 2;
        boolean[] dp = new boolean[req + 1];
        dp[0] = true;
        int reach = 0;
        for(int i = 0; i < n; i++){
            for(int j = req; j - stones[i] >= 0; j--){
                dp[j] = dp[j] || dp[j - stones[i]];
                if(dp[j]) reach = Math.max(reach, j);
            }
        }
        return total - (2 * reach);
    }

    static int maxDifferenceSubset2(int stones[])
    {        // Calculate sum of all elements
        int n = stones.length;
        int sum = 0;
        for (int i = 0; i < n; i++)             sum += stones[i];
        boolean dp[][] = new boolean[n + 1][sum + 1];
        // Initialize first column as true 0 sum is possible  with all elements.
        for (int i = 0; i <= n; i++)
            dp[i][0] = true;
        // Initialize top row, except dp[0][0], as false. With 0 elements, no other sum except 0 is possible
        for (int i = 1; i <= sum; i++)
            dp[0][i] = false;
        // Fill the partition table in bottom up manner
        for (int i = 1; i <= n; i++)        {
            for (int j = 1; j <= sum; j++)            {
                // If i'th element is excluded
                dp[i][j] = dp[i - 1][j];
                // If i'th element is included
                if (stones[i - 1] <= j)
                    dp[i][j] |= dp[i - 1][j - stones[i - 1]];
            }
        }
        // Initialize difference of two sums.
        int diff = Integer.MAX_VALUE;
        // Find the largest j such that dp[n][j] is true where j loops from sum/2 t0 0
        for (int x2 = sum / 2; x2 >= 0; x2--){ // x2 is the sum of one of the 2 parts of the array
            // Find the
            if (dp[n][x2] == true)            {
                diff = sum - 2 * x2; // j=x2 is the sum of one of the 2 parts of array .  x1 +x2 = sum. x1+x2-x2-x2 = sum-x2-x2 i.e x1-x2 = sum -2*x2
                break;
            }
        }
        return diff;
    }

    public static void main(String[] args) {
        new PlusMinusSumWays().maxDifferenceSubset1(new int[]{2,7,4,1,8,1});
    }

}