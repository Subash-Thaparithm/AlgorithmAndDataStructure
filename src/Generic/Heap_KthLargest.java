package Generic;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

 public class Heap_KthLargest
 {

     //Using Max heap: n +k logn
     public static int FindKthLargest(int[] ints, int k)
     {
         // create an max-heap using PriorityQueue class from all
         // elements in the list
         PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
         // or pass Comparator.reverseOrder()

         for (int i:ints  )
         {
             pq.add(i);
         }


         // pop from max-heap exactly (k-1) times
         while (--k > 0)
         {
             pq.poll();
         }

         // return the root of max-heap
         return pq.peek();
     }

     //Using Min heap: O(nlogk) faster since n>>>k
     public static int FindKthLargestMinheap(int[] ints, int k)
     {
         // create an min-heap using PriorityQueue class and insert
         // first k elements of the array into the heap
         PriorityQueue<Integer> pq = new PriorityQueue<>();

         for (int i=0;i<k;i++)
         {
             pq.add(ints[i]);
         }

         // do for remaining array elements
         for (int i = k; i < ints.length; i++)
         {
             // if current element is more than the root of the heap
             if (ints[i] > pq.peek())
             {
                 // replace root with the current element
                 pq.poll();
                 pq.add(ints[i]);
             }
         }

         // return the root of min-heap
         return pq.peek();
     }

     public static void main(String[] args)
     {
        int[] nums =new int[]{7, 4, 6, 3, 9, 1};
         int k = 2;


         System.out.println("K'th largest element in the array is " +
                 FindKthLargest(nums, k));
     }
 }



