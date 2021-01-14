package DP;

import java.util.*;

public class LIS_MakeArrayIncreasing {
    //https://leetcode.com/problems/make-array-strictly-increasing/discuss/377495/Java-dp-solution-%3A-A-simple-change-from-Longest-Increasing-Subsequence
    public int makeArrayIncreasing_EXplainedPoorly(int[] arr1, int[] arr2) {
        int n = arr1.length;
        /**
         * sort Array2 and remove duplicates
         */
        Arrays.sort(arr2);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr2.length; i++){
            if (i+1 < arr2.length && arr2[i] == arr2[i+1])
                continue;
            list.add(arr2[i]);
        }
        int[] newarr2 = new int[list.size()];
        for (int i = 0; i < list.size(); i++)
            newarr2[i] = list.get(i);
        arr2 = newarr2;

        /**
         * Add min and max to both sides of array1
         */
        int[] newarr1 = new int[n+2];
        for (int i = 0; i < n; i++)
            newarr1[i+1] = arr1[i];
        newarr1[n+1] = Integer.MAX_VALUE;
        newarr1[0] = Integer.MIN_VALUE;
        arr1 = newarr1;

        /**
         * perform dp based on LIS
         */
        int[] dp = new int[n+2];
        Arrays.fill(dp, Integer.MAX_VALUE);
        //dp[i] -> answer to change array 0 to i
        dp[0] = 0;
        for (int i = 1; i < n+2; i++){
            for (int j = 0; j < i; j++){  //the dp relationship in LIS problem
                if (arr1[j] < arr1[i] && dp[j] != Integer.MAX_VALUE){
                    //add a simple check function: the function checks if arr1[j] to arr1[i] can be swapped from arr2, to make a complete increasing array, ending at arr1[i].
                    int change = check(arr1, arr2, j, i); // Number of swaps required to make array from j to i strictly increasing
                    if (change >= 0){
                        dp[i] = Math.min(dp[i], dp[j] + change);
                    }
                }
            }
        }
        return dp[n+1] == Integer.MAX_VALUE? -1:dp[n+1];
    }
    //change number from start+1 to end-1
    private int check(int[] arr1, int[] arr2, int start, int end){
        if (start+1 == end)
            return 0;
        int min = arr1[start];
        int max = arr1[end];
        int idx = Arrays.binarySearch(arr2, min);
        if (idx < 0)
            idx = -idx-1;
        else
            idx = idx+1;

        int maxcount = end-start-1;
        int endi = idx + maxcount-1;
        if (endi < arr2.length && arr2[endi] < max)
            return maxcount;
        else
            return -1;
    }

    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        Arrays.sort(arr2);
        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(-1, 0);
        for(int a1 : arr1){
            Map<Integer, Integer> temp = new HashMap<>();
            for(Map.Entry<Integer, Integer> entry : dp.entrySet()){
                int key = entry.getKey();
                int val = entry.getValue();
                if(a1 > key){
                    int lastCount = temp.containsKey(a1) ? temp.get(a1) : Integer.MAX_VALUE;
                    temp.put(a1, Math.min(lastCount, val));
                }
                int i = binarySearch(arr2, key);
                if(i != arr2.length){
                    int lastCount = temp.containsKey(arr2[i]) ? temp.get(arr2[i]) : Integer.MAX_VALUE;
                    temp.put(arr2[i], Math.min(lastCount, val + 1));
                }
            }
            dp = temp;
        }
        int res = Integer.MAX_VALUE;
        if(dp.size() == 0){
            res = -1;
        }else{
            for(Map.Entry<Integer, Integer> entry : dp.entrySet()){
                res = Math.min(res, entry.getValue());
            }
        }
        return res;
    }

    private int binarySearch(int[] arr, int t){
        int left = 0;
        int right = arr.length;
        while(left < right){
            int mid = left + (right - left) / 2;
            if(arr[mid] <= t){
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        return left;
    }

    public int makeArrayIncreasing_betterDontknow(int[] arr1, int[] arr2) {
        if (arr1 == null || arr1.length == 0) return -1;
        if (arr1.length == 1) return 0;
        TreeSet<Integer> ts = new TreeSet<>();
        if (arr2 != null) {
            for (int i = 0; i < arr2.length; i++) ts.add(arr2[i]);
        }

        int[][] dp = new int[arr1.length + 1][arr1.length + 1];
        for (int i = 0; i < dp.length; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);
        dp[0][0] = Integer.MIN_VALUE;

        for (int j = 1; j < dp.length; j++) {
            for (int i = 0; i <= j; i++) {
                if (arr1[j - 1] > dp[i][j - 1]) {
                    dp[i][j] = arr1[j - 1];
                }
                if (i > 0 && ts.higher(dp[i - 1][j - 1]) != null) {
                    dp[i][j] = Math.min(dp[i][j], ts.higher(dp[i - 1][j - 1]));
                }
                if (j == dp.length - 1 && dp[i][j] != Integer.MAX_VALUE) return i;
            }
        }
        return -1;
    }
}
