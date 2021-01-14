package DP;

import java.util.Arrays;
import java.util.Comparator;

public class JobScheduling {
    public int jobScheduling_MaxProfit(int[] startTime, int[] endTime, int[] profit) {
        //Sort the profit array on the basis of end time
        ArrayIndexComparator indexComparator = new ArrayIndexComparator(endTime);
        Integer [] indexes = indexComparator.createIndexArray();
        Arrays.sort(indexes, indexComparator);

        int [] sortedProfit = new int[endTime.length];
        int [] sortedStartTime = new int[endTime.length];
        int [] sortedEndTime = new int[endTime.length];
        int [] dpprofit = new int[endTime.length];
        for (int i = 0; i < endTime.length ; i++) {
            sortedProfit[i] = profit[indexes[i]];
            sortedStartTime[i] = startTime[indexes[i]];
            sortedEndTime[i] = endTime[indexes[i]];
            dpprofit[i] = profit[indexes[i]];
        }
        for (int i = 0; i < endTime.length; i++) {
            for (int j = 0; j < i; j++) {
                if (sortedStartTime[i] >= sortedEndTime[j] )
                {
                    dpprofit[i] = Math.max(dpprofit[i], dpprofit[j] + sortedProfit[i]);
                }
            }
        }
        int maxProfit = 0;
        for (int val:dpprofit
        ) {
            if (maxProfit < val) maxProfit = val;
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        JobScheduling job = new JobScheduling();
        //job.jobScheduling_MaxProfit(new int[]{338,279});
    }
}
 class ArrayIndexComparator implements Comparator<Integer>
{
    private final int[] array;

    public ArrayIndexComparator(int[] array)
    {
        this.array = array;
    }

    public Integer[] createIndexArray()
    {
        Integer[] indexes = new Integer[array.length];
        for (int i = 0; i < array.length; i++)
        {
            indexes[i] = i; // Autoboxing
        }
        return indexes;
    }

    @Override
    public int compare(Integer index1, Integer index2)
    {
        // Autounbox from Integer to int to use as array indexes
        return array[index1]-(array[index2]);
    }
}
