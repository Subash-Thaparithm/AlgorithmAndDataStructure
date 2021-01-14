package DP;

import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;

public class MinimumCost_Traintickets {
    public int mincostTickets(int[] days, int[] costs) {
           int dailyCost = costs[0];
           int weeklyCost = costs[1];
           int monthlyCost = costs[2];
           boolean isWeeklyTicketCheaper = true;
           boolean isMonthlyTicketCheaper = true;
           if (weeklyCost > 7 * dailyCost) {System.out.println("Weekly ticket is expensive than daily ticket.");isWeeklyTicketCheaper = false;}
           if (isWeeklyTicketCheaper) {
               if (monthlyCost > 4 * weeklyCost + 2 * dailyCost) {
                   System.out.println("Monthly ticket is expensive than weekly plus daily ticket.");
                   isMonthlyTicketCheaper = false;
               }
           }
           else  {
            if (monthlyCost > 30 * dailyCost) {
                System.out.println("Monthly ticket is expensive than  daily ticket.");
                isMonthlyTicketCheaper = false;
            }
        }
           int currentMonthlyStart=0;
           int currentWeeklyStart=0;
           if (days.length ==0) return 0;
           if (days.length >=3) return 3 * costs[0];
           int totalCost = costs[0];
           for (int i = 1; i < days.length ; i++) {
               if (days[i]-days[i-1] >= 30) {
                   currentMonthlyStart = i ; currentWeeklyStart = i;
               }
               else if (days[i]-days[i-1] >= 7) {
                 currentWeeklyStart = i ;
            }

           }
           return 0 ;
    }

    public int mincostTickets_elegant(int[] days, int[] costs) {
        AbstractMap.SimpleEntry<Integer, Integer> pair ;
        Queue<AbstractMap.SimpleEntry<Integer, Integer>> last7 = new LinkedList();
        Queue<AbstractMap.SimpleEntry<Integer, Integer>>  last30 = new LinkedList();
        int cost=0;
        for (int d : days) {
            while (!last7.isEmpty() && last7.peek().getKey() + 7 <= d) last7.poll();
            while (!last30.isEmpty() && last30.peek().getKey() + 30 <= d) last30.poll();
            last7.add(new AbstractMap.SimpleEntry( d, cost + costs[1]));
            last30.add(new AbstractMap.SimpleEntry(d, cost + costs[2] ));
            cost = Math.min(Math.min( cost + costs[0], last7.peek().getValue()), last30.peek().getValue() );
        }
        return cost;
    }

    public static void main(String[] args) {

    }
}
