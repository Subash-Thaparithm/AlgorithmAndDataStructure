package DP;

import Sorting.QuickSort;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class LastStoneWeight {
    public int lastStoneWeight(int[] stones) {
        /*//Bubble-sort, Insertion-sort and Selection-sort use constant memory but O(n2) time complexity
        //https://www.geeksforgeeks.org/quick-sort-vs-merge-sort/
        //Sort this array , best possible algorithm in terms of space and time
        //Merge sort vs quick sort , merge sort is best for worst case i.e O(nlogn), quick sort is O(n2) for worst case. For array or fixed size list or smaller DS, use quicksort*/
        if (stones == null || stones.length == 0) return 0;
        else if (stones.length ==1) return  stones[0];

        QuickSort.quickSort(stones,0,stones.length-1);
        int result = getNewSortedArrayAfterCollision(stones);
        return result;
       /* // Use linkedlist instead of array since we have to reposition elements after collision. Insertion and deletion is less costly for linked list.
        // Java anyway converts list into array for sorting and converts back to list. So list or array performs similar for sorting but linkedlist is little slower. Use array list.
        //Use TreeSet or Priority Queue if sorting is the main thing
        //https://www.geeksforgeeks.org/why-quick-sort-preferred-for-arrays-and-merge-sort-for-linked-lists/
        // Use arraylist and then sort if every time*/
    }
    private int getNewSortedArrayAfterCollision(int [] sortedStones) {
        if (sortedStones == null || sortedStones.length ==0) return  0;
        else if (sortedStones.length ==1) return  sortedStones[0];
        else if (sortedStones.length ==2) {
            if (sortedStones[1] > sortedStones[0])   return   sortedStones[1] - sortedStones[0];
            else return 0;
        }
            for(int i=sortedStones.length-1, j=sortedStones.length-2; i>=0 && j>=0;) {
                if (sortedStones[i] > sortedStones[j])  {
                    sortedStones[j] =  sortedStones[i] - sortedStones[j];;
                    if (j == 0)  return sortedStones[0];
                    if (sortedStones[j] < sortedStones[j-1]) break;
                    else { i=i-1;j=j-1;}
                }
                else {
                    if (j >= 2) {
                        sortedStones =  Arrays.copyOf(sortedStones, sortedStones.length-2);
                        i=i-2;j=j-2;
                    }
                    else if (j==0) return 0;
                    else return sortedStones[0];
                }
            }
            sortedStones =  Arrays.copyOf(sortedStones, sortedStones.length-1);
            QuickSort.quickSort(sortedStones,0,sortedStones.length-1);

            return getNewSortedArrayAfterCollision(sortedStones) ;

    }
    public int lastStoneWeight1(int[] stones) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        for(int i : stones) {
            queue.add(i);
        }
        int x;
        int y;
        while(queue.size() > 1) {
            y = queue.poll();
            x = queue.poll();
            if(y > x) {
                queue.offer(y-x);
            }
        }
        return queue.isEmpty() ? 0 : queue.poll();
    }
    public static void main(String args[]){
        int input[] = {857,149,920,468,623,117,984,537,51,160,512,271,852,372,728,160,512,363,292,838,802,459,961,837,165,203,133,518,184,733};
        int output = new LastStoneWeight().lastStoneWeight(input);

        System.out.println(output);
    }
}