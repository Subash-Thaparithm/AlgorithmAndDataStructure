package DP;

public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
       int [][] dp = new int[grid.length][grid[0].length];   // dp[i][j] denotes the minimum cost to reach this point

        dp[0][0] = grid[0][0];
        //Initialise first row. Could be reached only from its previous elements in same row
        for (int i = 1; i <grid[0].length ; i++) {
             dp[0][i] = dp[0][i-1] + grid[0][i];
        }
        //Initialise first column. Could be reached only from its previous elements in same column
        for (int i = 1; i <grid.length ; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        //Calculate for the rest of the matrix
        for (int i = 1; i <grid.length ; i++) {
            for (int j = 1; j <grid[0].length ; j++) {
                if (dp[i][j-1] <= dp[i-1][j]) dp[i][j] = grid[i][j] +dp[i][j-1] ;
                else dp[i][j] = grid[i][j] + dp[i-1][j];
            }
        }
        return dp[grid.length-1][grid[0].length-1];
    }
}
