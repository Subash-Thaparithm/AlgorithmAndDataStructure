import java.util.*;
public class Miscellaneous {
    public static void main(String[] args) {
        MedianFinder finder = new MedianFinder();
        /*finder.addNum_UsingQueue(0);
        finder.addNum_UsingQueue(0);
        finder.addNum_UsingQueue(2);
        finder.addNum_UsingQueue(6);
        finder.addNum_UsingQueue(1);
        finder.addNum_UsingQueue(4);
       finder.findMedian_UsingQueue();*/
        int[][] matrix = new int[][]{
                {1,1} };
        int [] nums = {2, 3, 4, 6, 10, 9, 15};
        int end = 9;
        int start = 3;
        int [] arr = Arrays.copyOfRange(nums, 0, start+1);

        int startindex = Arrays.binarySearch(arr, end);
        startindex = -(startindex);

        boolean canFind = finder.searchMatrix(matrix, 2);
        int a = 3;
        new Miscellaneous().leastInterval_JobScheduling(new char[]{'A','A'}, 3);//'F','F','G','G','H','H','I','I','J','J','K','K','L','L','M','M','N','N','O','O','P','P','Q','Q','R','R','S','S','T','T','U','U','V','V','W','W','X','X','Y','Y','Z','Z'
       // } ,2);
    }

    public int leastInterval_JobScheduling(char[] tasks, int n) {

        int[] map = new int[26];
        for (char c: tasks)
            map[c - 'A']++;
        Arrays.sort(map);
        int time = 0;
        while (map[25] > 0) {
            int i = 0;
            while (i <= n) {
                if (map[25] == 0)
                    break;
                if (i < 26 && map[25 - i] > 0)
                    map[25 - i]--;
                time++;
                i++;
            }
            Arrays.sort(map);
        }
        return time;
    }
}
/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
class MedianFinder {
    public boolean searchMatrix(int[][] matrix, int target) {
        int searchRow = 0, searchColumn = matrix[0].length-1;
        int insertionPointcol = 0;
        while(searchRow < matrix.length && searchColumn >= 0){
            insertionPointcol = Arrays.binarySearch(matrix[searchRow], target);
            if (insertionPointcol < 0)  insertionPointcol = -(insertionPointcol+1);
            else return true;
            if (insertionPointcol>0)  searchColumn = insertionPointcol-1;
            else if (matrix[searchRow][insertionPointcol] != target) return false;
            int[] columnArr = getColumn(matrix, searchColumn);

            int insertionpointRow = Arrays.binarySearch(columnArr, target);
            if (insertionpointRow >= 1) return true;
            else searchRow = -(insertionpointRow+1);

            if (searchRow == matrix.length-1 && searchColumn == 0) return false;
        }
        return false;
    }
    /** initialize your data structure here. */
    List<Integer> orderedSet = new ArrayList<>();
    public MedianFinder() {

    }
    public void addNum(int num) {
        if (orderedSet.isEmpty()) {orderedSet.add(num); return; }
        int insertionPoint = Collections.binarySearch(orderedSet,num);
        if (insertionPoint < 1)        insertionPoint = -(insertionPoint+1);

        orderedSet.add(insertionPoint, num);
    }
    public double findMedian() {
       int n = orderedSet.size();
       if (n %2 ==1) return (double)orderedSet.get((n+1)/2-1);
       else return (double)(orderedSet.get(n/2) + orderedSet.get( (n/2) - 1))/2;
    }
    private Queue<Long> small = new PriorityQueue(),
            large = new PriorityQueue();
    public void addNum_UsingQueue(int num) {
        large.add((long) num);
        small.add(-large.poll());
        if (large.size() < small.size())
            large.add(-small.poll());
    }
    public double findMedian_UsingQueue() {
        return large.size() > small.size()
                ? large.peek()
                : (large.peek() - small.peek()) / 2.0;
    }
    public  int[] getColumn(int[][] array, int index){
        int[] column = new int[array.length]; // Here I assume a rectangular 2D array!
        for(int i=0; i<column.length; i++){
            column[i] = array[i][index];
        }
        return column;
    }
}