package Generic;

import java.util.Arrays;

public class CherryPickup_Recursive_Dynamic_TopDown
{
    int[][][] memo;
    int[][] grid;
    int N;
    public int cherryPickup(int[][] grid)
    {
        this.grid = grid;
        N = grid.length;
        memo = new int[N][N][N];
        for (int[][] layer: memo)
            for (int[] row: layer)
                Arrays.fill(row, Integer.MIN_VALUE);
        return Math.max(0, dp(0, 0, 0)); // Calculate max cherries collected by 2 persons independently when started from (0,0),(0,0) to (N-1,N-1)
    }
    public int dp(int r1, int c1, int c2) // Calculate max cherries collected by 2 persons independently when started from (r1,c1),(r2,c2) to (N-1,N-1)
    {
        int r2 = r1 + c1 - c2; // Total number of steps travelled after T steps  = r1 +c1 = r2+c2=T
        if (N == r1 || N == r2 || N == c1 || N == c2 ||
                grid[r1][c1] == -1 || grid[r2][c2] == -1)
        {
            return -999999;
        }
        else if (r1 == N-1 && c1 == N-1) {
            return grid[r1][c1];
        } else if (memo[r1][c1][c2] != Integer.MIN_VALUE) {
            return memo[r1][c1][c2];
        } else {
            int ans = grid[r1][c1];
            if (c1 != c2) ans += grid[r2][c2];
            ans += Math.max(Math.max(dp(r1, c1+1, c2+1), dp(r1+1, c1, c2+1)),
                    Math.max(dp(r1, c1+1, c2), dp(r1+1, c1, c2)));
            memo[r1][c1][c2] = ans;
            return ans;
        }
    }
}