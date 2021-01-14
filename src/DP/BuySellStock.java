package DP;

import java.util.Arrays;
public class BuySellStock {
    public int maxProfit_TimeExceeded(int k, int[] prices) {
        if (prices.length < 2) return 0;
        int[] oneLessTransacitonProfitArray = new int [prices.length];
        int[] currTransacitonProfitArray = new int [prices.length];
        //Fill first row and first column with Zeroes
        for(int i=0; i< prices.length;i++){
            oneLessTransacitonProfitArray[i]=0;
        }
        for(int tr=1; tr < k+1;tr++){
            int maxDiff = -prices[0];
            for(int day=0; day< prices.length;day++){
                // profit[tr][day] = prices[day] + Max of {for all previous days, buy at day m, profit till day m;}
                if (day ==0) { currTransacitonProfitArray[0] = 0; continue;}
                currTransacitonProfitArray[day] = Math.max(currTransacitonProfitArray[day-1], prices[day] + maxDiff );
                maxDiff = Math.max(maxDiff, oneLessTransacitonProfitArray[day] - prices[day]);
            }
            oneLessTransacitonProfitArray = Arrays.copyOf(currTransacitonProfitArray, oneLessTransacitonProfitArray.length);
        }
        return currTransacitonProfitArray[prices.length-1];
    }
    public int maxProfitLinearSpace(int k, int[] prices) {
        if (k == 0 || prices.length == 0) {
            return 0;
        }
        int T[][] = new int[k+1][prices.length];

        for (int i = 1; i < T.length; i++) {
            int maxDiff = -prices[0];
            for (int j = 1; j < T[0].length; j++) {
                T[i][j] = Math.max(T[i][j-1], prices[j] + maxDiff);
                maxDiff = Math.max(maxDiff, T[i-1][j] - prices[j]);
            }
        }
        return T[k][prices.length-1];
    }
    public int maxProfit_MoreMemory(int k, int[] prices) {
        if (prices.length < 2) return 0;
        int[][] profit = new int [k+1][prices.length];
        //Fill first row and first column with Zeroes
        for(int i=0; i< profit[0].length;i++){
            profit[0][i] = 0;
        }
        for(int i=0; i< profit.length;i++){
            profit[i][0] = 0;
        }

        //Create the profit array
        for(int tr=1; tr < k+1;tr++){
            for(int day=1; day< prices.length;day++){
                // profit[tr][day] = prices[day] + Max of {for all previous days, buy at day m, profit till day m;}
                int prevBuyDay = -1;
                int preBuyDayNetProfitValue = Integer.MIN_VALUE;;
                for(int prev =0; prev < day; prev++){
                    int netProfit = -prices[prev] + profit[tr-1][prev];
                    if(netProfit > preBuyDayNetProfitValue) {
                        preBuyDayNetProfitValue = netProfit ;
                        prevBuyDay = prev;
                    }
                }
                profit[tr][day] = Math.max(profit[tr][day-1], prices[day] + preBuyDayNetProfitValue );


            }
        }
        return profit[k][prices.length-1];
    }

    /**
     * If k > length of prices array
     */
    public int allTimeProfit(int arr[]){
        int profit = 0;
        int localMin = arr[0];
        for(int i=1; i < arr.length;i++){
            if(arr[i-1] >= arr[i]){
                localMin = arr[i];
            }else{
                profit += arr[i] - localMin;
                localMin = arr[i];
            }

        }
        return profit;
    }

}