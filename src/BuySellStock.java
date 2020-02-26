class BuySellStock
{
    public int maxProfit(int[] prices)
    {
        int minindex=0 ;
        int min= Integer.MAX_VALUE;
        for(int i=0; i< prices.length; i++)
        {

            if(prices[i]<min)
            {
                min = prices[i];
                minindex = i;
            }
        }
        int max= Integer.MIN_VALUE;
        for(int i=minindex; i< prices.length; i++)
        {

            if(prices[i]>max)
            {
                max = prices[i];
            }
        }

        return (max-min);
    }
}
class Solution
{
    public int maxProfit(int[] prices)
    {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int max = 0;
        int[] dp = new int[prices.length];
        int lowest = prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i] = max;
            if (prices[i] - lowest >= max) {
                max = prices[i] - lowest;
                dp[i] = max;
            }
            if (prices[i] < lowest) {
                lowest = prices[i];
            }
        }
        // if there are two transaction process
        int newMax = max;
        int secondMax = 0;
        int highest = prices[prices.length - 1];
        // cursor i is the starting point of the second transaction
        for (int i = prices.length - 2; i >= 2; i--)
        {
            if (highest - prices[i] > secondMax)
            {
                secondMax = highest - prices[i];
                if (secondMax + dp[i - 1] > newMax)
                {
                    newMax = Math.max(newMax, secondMax + dp[i - 1]);
                }
            }
            if (prices[i] > highest)
            {
                highest = prices[i];
            }
        }
        return newMax;
    }
}