package Generic;

import java.util.HashSet;
import java.util.Set;

public class CountAllWithSuccessorArray {
    public int countElements(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int num:arr
             ) {set.add(num);

        }
        int count = 0;
        for (int num:arr
             ) {
            if (set.contains(++num)) count++;
        }
        return count;
    }
}
