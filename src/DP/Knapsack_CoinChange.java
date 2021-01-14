package DP;

public class Knapsack_CoinChange {
        // m is size of coins array
        // (number of different coins)
        static int minCoins(int coins[], int amount)
        {
            // table[i] will be storing the minimum number of coins required for i value. So table[V] will have result
            int table[] = new int[amount + 1];
            // Base case (If given value V is 0)
            table[0] = 0;
            // Initialize all table values as Infinite
            for (int i = 1; i <= amount; i++)
                table[i] = Integer.MAX_VALUE;
            // Compute minimum coins required for all  values from 1 to V
            for (int value = 1; value <= amount; value++)
            { // Go through all coins smaller than i
                for (int coinIndex = 0; coinIndex < coins.length; coinIndex++)
                    if (coins[coinIndex] <= value) {
                        if (table[value - coins[coinIndex]] != Integer.MAX_VALUE && table[value - coins[coinIndex]] + 1 < table[value])
                            table[value] = table[value - coins[coinIndex]] + 1;
                    }
            }
            return table[amount];
        }
    static int getNumberOfWays(int[] Coins, int amount)    {
        // Create the ways array to 1 plus the amount to stop overflow
        int[] table = new int[amount + 1];
        // Set the first way to 1 because its 0 and there is 1 way to make 0 with 0 coins
        table[0] = 1;
        // Go through all of the coins
        for (int coinIndex = 0; coinIndex < Coins.length; coinIndex++) {
            // Make a comparison to each index value of ways with the coin value.
            for (int value = 0; value <= amount; value++) {
                if (Coins[coinIndex] <= value) {
                    // Update the ways array
                    table[value] = table[value - Coins[coinIndex]] + table[value];
                }
            }
        }
        // return the value at the Nth position
        // of the ways array.
        return table[amount];
    }
    public static void main(String args[]){

        int [] coins = {1,2,3};

    }
}
