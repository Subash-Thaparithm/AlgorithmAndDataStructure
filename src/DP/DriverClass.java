package DP;

public class DriverClass {
    public static void main(String[] args) {
        int [] arr1 = new int[]{1,5,3,6,7};
        int [] arr2 = new int[]{1,3,2,4};
        new LIS_MakeArrayIncreasing().makeArrayIncreasing_betterDontknow(arr1, arr2);

       int profit =  new BuySellStock().maxProfitLinearSpace(2, new int[]{3,3,5,0,0,3,1,4});
         profit =  new BuySellStock().maxProfit_MoreMemory(2, new int[]{3,3,5,0,0,3,1,4});
       int a = 2;
    }
}
