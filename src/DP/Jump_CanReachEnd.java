package DP;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Jump_CanReachEnd {
    public boolean canJump(int[] nums) {
        boolean [] canReach = new boolean[nums.length];
        canReach[nums.length-1] = true;
        for (int i = nums.length-1; i>=0 ; i--) {
            for (int j =1; j<= nums[i]; j++) {
                if(i+j<=nums.length-1) {
                    if (canReach[i + j] || i + j == nums.length - 1) { canReach[i] = true; continue;}
                }
            }
        }
        return canReach[0];
    }

    public boolean canJump1(int[] nums, Set<Integer> indexes) {
        if (indexes.size() ==0) return false;
        Set<Integer> newIndexes = new HashSet<Integer>();
        for (int index:indexes) {
                if(index +nums[index]>=nums.length-1) {
                    return true;
                }
            for (int i=1;i<=nums[index];i++) {
                if (index +i <nums.length) newIndexes.add(index + i);
            }
        }
        return canJump1(nums, newIndexes);
    }
}
