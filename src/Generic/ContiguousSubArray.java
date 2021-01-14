package Generic;

import java.util.HashMap;
import java.util.Map;

public class ContiguousSubArray {
    int maxLen(int nums[])
    {
        Map<Integer, Integer> mapSumToIndex = new HashMap<>(); // Maps the latest index where the specific cumulative sum in the graph occurs
        mapSumToIndex.put(0, -1);

        int maxlength = 0, currSum =0;
        for(int i=0; i< nums.length; i++){
            if(nums[i] ==0) currSum--;
            else currSum++;

            if (! mapSumToIndex.containsKey(currSum)) mapSumToIndex.put(currSum, i); // Stores pair of first index of occurance and current total length separated by :
            else {
                 int firstIndex = mapSumToIndex.get(currSum);
                 int length = i -firstIndex;
                 mapSumToIndex.put(currSum, firstIndex);

                 if (maxlength < length) maxlength = length;
            }
        }
        return maxlength;
    }

    public static void main(String[] args) {
        int[] input = {0,1,1,0,1,0,0};
        new ContiguousSubArray().maxLen(input);
    }
}
