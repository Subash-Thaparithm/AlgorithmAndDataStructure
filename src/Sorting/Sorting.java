package Sorting;

import java.util.*;

public class Sorting {
    public static void sort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }
    public static void main(String[] args) {
       /* new Sorting().sort(new int[]{7, 3, 6, 8, 4, 9,2,5,0, 100});
        new SelectionSort().sort(new int[]{7, 3, 6, 8, 4, 9, 1});
        int[] arr = {7, 3, 6, 8, 4, 9,2,5,0, 100};
        new Sorting().pigeonHole_MaximumGap(arr);*/
        //new Sorting().rangeBitwiseAnd(2147483645,2147483646);
        new Sorting().PatienceSorting_Better(new Integer[]{7, 3, 6, 8, 4, 9,2,5,0, 100});
    }
    public int maximumGap(int[] nums) {
        int bucketSize = (int) Math.sqrt(nums.length);
        bucketSort(nums, nums.length);
        int maxDiff = 0;

        for(int i=1; i< nums.length; i++)
            if (nums[i]-nums[i-1] > maxDiff) maxDiff = nums[i]-nums[i-1];

        return maxDiff;
    }
    public static void insertionSort(List<Integer> list) {
        int n = list.size();
        for (int i = 1; i < n; ++i) {
            int key = list.get(i);
            int j = i - 1;

            while (j >= 0 && list.get(j) > key) {
                list.set(j + 1, list.get(j));
                j = j - 1;
            }
            list.set(j + 1,  key);
        }
    }
    public  static void bucketSort(int[] arr, int bucketSize){
        // Create bucket array for storing lists
        List<Integer>[] buckets = new List[bucketSize];
        // Linked list with each bucket array index  as there may be hash collision
        for(int i = 0; i < bucketSize; i++){
            buckets[i] = new LinkedList<>();
        }
        // calculate hash and assign elements to proper bucket
        for(int i=0; i<arr.length;i++){
            buckets[hash(i, bucketSize)].add(arr[i]);
        }
        // sort buckets
        for(List<Integer> bucket : buckets){
            // Collections.sort(bucket);           // Merge sort is used O(nlogn). Ideally insertion sort should be used for almost sorted list.
            insertionSort(bucket);
        }
        int index = 0;
        // Merge buckets to get sorted array
        for(List<Integer> bucket : buckets){

            for(int num : bucket){
                arr[index++] = num;
            }
        }
    }
    // hash function used for element distribution
    private static int hash(int index, int bucketSize){
        return index%bucketSize;
    }
    public int pigeonHole_MaximumGap(int [] nums){
        int n = nums.length;
        if (n < 2) return 0;
        int l = getMin(nums), u = getMax(nums);
        int gap = Math.max((u - l) / (n - 1), 1);
        int m = ((u - l) / gap) + 1;
        int[] bucketsMin = new int[m];
        Arrays.fill(bucketsMin, Integer.MAX_VALUE);

        int[] bucketsMax = new int[m];
        Arrays.fill(bucketsMax, Integer.MIN_VALUE);

        for (int num : nums) {
            int k = (num - l) / gap;
            if (num < bucketsMin[k]) bucketsMin[k] = num;
            if (num > bucketsMax[k]) bucketsMax[k] = num;
        }
        int i = 0, j;
        gap = bucketsMax[0] - bucketsMin[0];
        while (i < m) {
            j = i + 1;
            while (j < m && bucketsMin[j] == Integer.MAX_VALUE && bucketsMax[j] == Integer.MIN_VALUE)
                j++;
            if (j == m) break;
            gap = Math.max(gap, bucketsMin[j] - bucketsMax[i]);
            i = j;
        }
        return gap;
    }


    public static int getMax(int[] inputArray){
        int maxValue = inputArray[0];
        for(int i=1;i < inputArray.length;i++){
            if(inputArray[i] > maxValue){
                maxValue = inputArray[i];
            }
        }
        return maxValue;
    }
    public static int getMin(int[] inputArray){
        int minValue = inputArray[0];
        for(int i=1;i < inputArray.length;i++){
            if(inputArray[i] < minValue){
                minValue = inputArray[i];
            }
        }
        return minValue;
    }
    public int rangeBitwiseAnd(int m, int n) {
        if (m ==0 || n==0) return 0;
        if (m ==n) return m;
        int result = 0;
        for(int i=m, j=m+1; i<= n; i++,j++) {
            String x = decimalToBinary(i);
            String y = decimalToBinary(j);
            if (x.length()>y.length()) {
                char [] array = new char[x.length()-y.length()];
                Arrays.fill(array, '0');
                y = y + array ;
            }
            else if (y.length()>x.length()) {
                char [] array = new char[y.length()-x.length()];
                Arrays.fill(array, '0');
                x = x + array ;
            }
            String output ="";
            for (int k = 0; k < y.length(); k++) {
               if (y.charAt(k) == x.charAt(k)) {
                  // output = output + "1";
                   result = result + (int)Math.pow(2,y.length()-1-k);
               }
               else break;
            }
        }
        return result;
    }
    private String decimalToBinary(int num){
        String str = "";
        while(num > 0)   {
            int y = num % 2;
            str = y + str;
            num = num / 2;
        }
        return str;
    }
    private int binaryToDecimal(String binaryString){
        return Integer.parseInt(binaryString,2);
    }

    /**
     * Similar to Solitaire game of card, https://www.youtube.com/watch?v=rqON9p_7Kx4
     */
    public int[] PatienceSorting(int[] nums) { // O(n)
        List<List<Integer>> list = new LinkedList<>();   // Construct descended sorted Decks
        List<Integer> minEachList = new LinkedList<>();  // ith element is the minimum element of ith deck so far
        // Construct descended sorted Decks
        for (int num:nums) {
            Iterator<Integer> iterator = minEachList.iterator();
            Integer deckIndexNewElem = Integer.MAX_VALUE;

            //Searching for index of deck where it has to be inserted. Use binary search instead.
            while(iterator.hasNext()) {
                int elem = iterator.next();
                if (elem<deckIndexNewElem && elem>=num) {
                    deckIndexNewElem = elem;
                }
            }

            if (deckIndexNewElem!=Integer.MAX_VALUE && num<=deckIndexNewElem) {
                list.get(minEachList.indexOf(deckIndexNewElem)).add(num);
                minEachList.set(minEachList.indexOf(deckIndexNewElem),num);
            }
            else {
                List<Integer> newDeck = new LinkedList<>();
                newDeck.add(num);
                list.add(newDeck);
                minEachList.add(num);
            }

        }
        int [] result = new int[nums.length];
        int j=0;
        // Merge all the sorted Decks
        List<Integer> lowest;
        while (j < nums.length) { // while we still have something to add
            lowest = null;
            for (List<Integer> l : list) {
                if (! l.isEmpty()) {
                    if (lowest == null) {
                        lowest = l;
                    } else if (l.get(l.size()-1).compareTo(lowest.get(lowest.size()-1)) <= 0) {
                        lowest = l;
                    }
                }
            }
            result[j] = lowest.get(lowest.size()-1);
            lowest.remove(lowest.size()-1);
            j++;
        }
        return result;
    }

    public static <E extends Comparable<? super E>> E[] PatienceSorting_Better (E[] nums)
    {
        List<Pile<E>> piles = new ArrayList<Pile<E>>();
        // sort into piles
        for (E x : nums)
        {
            Pile<E> newPile = new Pile<E>();
            newPile.push(x);
            int i = Collections.binarySearch(piles, newPile);
            if (i < 0) i = ~i;  // new element is not in any of the list
            if (i != piles.size())
                piles.get(i).push(x);
            else
                piles.add(newPile);
        }
        System.out.println("longest increasing subsequence has length = " + piles.size());

        // priority queue allows us to retrieve least pile efficiently
        PriorityQueue<Pile<E>> heap = new PriorityQueue<Pile<E>>(piles);
        for (int c = 0; c < nums.length; c++)
        {
            Pile<E> smallPile = heap.poll();
            nums[c] = smallPile.pop();
            if (!smallPile.isEmpty())
                heap.offer(smallPile);
        }
        assert(heap.isEmpty());
        return nums;
    }

    private static class Pile<E extends Comparable<? super E>> extends Stack<E> implements Comparable<Pile<E>>
    {
        public int compareTo(Pile<E> y) { return peek().compareTo(y.peek()); }
    }
}