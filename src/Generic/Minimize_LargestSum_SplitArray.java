package Generic;

public class Minimize_LargestSum_SplitArray {
    public int splitArray(int[] nums, int m) {
        if (nums.length == 0) {
            return 0;
        }
        int left = 0, right = 0;
        for (int n : nums) {
            left = Math.max(left, n);
            right += n;
        }
        if (m == nums.length) {
            return left;
        }
        if (m == 1) {
            return right;
        }
        while (left < right) {
            int mid = (left + right) / 2;
            if (canSplit(nums, mid, m)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean canSplit(int[] nums, int maxVal, int m) {
        int countSub = 1;
        int currSum = 0;
        for (int n : nums) {
            currSum += n;
            if (currSum > maxVal) {
                currSum = n;
                countSub++;
                if (countSub > m) {
                    return false;
                }
            }
        }
        return true;
    }

    //https://www.researchgate.net/post/How_can_we_partition_a_set_of_n_positive_numbers_into_k_parts_such_that_the_largest_part_in_the_sense_of_summation_is_minimized
    public int splitArray_DP(int[] nums, int m) {
        if (m <= 0) {
            return -1;
        }
        if (nums.length == 0) {
            return 0;
        }

        int[][] f = new int[m][nums.length];
        f[0][0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            f[0][i] = f[0][i - 1] + nums[i];
        }

        for (int i = 1; i < m; i++) {
            for (int j = i; j < nums.length; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = i - 1; k < j; k++) {
                    min = Math.min(min, Math.max(f[i - 1][k], f[0][j] - f[0][k]));
                }
                f[i][j] = min;
            }
        }
        return f[m - 1][nums.length - 1];
    }

   /* public int splitArray_DP_Faster(int[] nums, int m) {

    int n = nums.length;
    int sums = 0;
        for (int v:nums)        {
            sums+= sums +v;
        }
        int [] dp = list(sums);

    dp_cal = lambda j: max(dp[j], sums[i]-sums[j])

        for r in range(2, m + 1):
    k = n - 1
            for i in range(n, r - 1, -1):
            while k >= r and dp_cal(k-1) <= dp_cal(k):
    k -= 1
    dp[i] = dp_cal(k)

        return dp[n];
    }*/

   public static void main(String[] args) {
        new Minimize_LargestSum_SplitArray().splitArray_DP(new int[]{3,6,7, 2, 5, 10, 8,12,11}, 3);
    }
}