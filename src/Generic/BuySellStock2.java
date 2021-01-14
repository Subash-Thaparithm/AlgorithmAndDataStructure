package Generic;

class BuySellStock2
    {
        public int maxProfit(int[] prices)
        {
            // start from a valley and go to the highest peak before another valley. Add all profits.

            int minCurrentUpwardSlope;

            if(prices.length >= 2)  minCurrentUpwardSlope = prices[0];
            else return 0;

            int maxCurrentUpwardSlope = Integer.MIN_VALUE;
            int valley =Integer.MAX_VALUE;
            boolean lastDescended = false;
            int profit = 0;

            if(prices[0] < prices[1])
            {
                if(prices.length ==2) return prices[1]-prices[0];

                minCurrentUpwardSlope = prices[0];
                maxCurrentUpwardSlope =  prices[1];
            }
            else
            {
                if(prices.length ==2) return 0;

                lastDescended = true;
                valley = prices[1];
            }

            for(int i=2; i< prices.length; i++)
            {
                if(lastDescended)
                {
                    if (valley > prices[i])   // Continue Descending)
                        valley = prices[i];

                    if (valley < prices[i])  // Start Ascending
                    {
                        minCurrentUpwardSlope = valley;
                        maxCurrentUpwardSlope = prices[i];
                        lastDescended = false;
                        if (i == prices.length - 1) return profit + maxCurrentUpwardSlope - valley;
                    }
                }
                else if (!lastDescended)
                {
                    if (maxCurrentUpwardSlope < prices[i]) // Continue Asacending
                        maxCurrentUpwardSlope = prices[i];

                    else if (maxCurrentUpwardSlope > prices[i]) // Start descending)
                    {
                        lastDescended = true;
                        valley = prices[i];
                        profit = profit + maxCurrentUpwardSlope - minCurrentUpwardSlope;
                    }
                }
                if(i == prices.length -1 && !lastDescended )
                {
                        return profit + maxCurrentUpwardSlope -minCurrentUpwardSlope ;
                }
            }

            return profit;
        }
    }