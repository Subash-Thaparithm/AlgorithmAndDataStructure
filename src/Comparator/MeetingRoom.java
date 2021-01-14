package Comparator;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
public class MeetingRoom {
    public boolean canAttendMeetings(int[][] intervals) {

        Arrays.sort(intervals, new IntervalComparator());
        for(int i=0; i<intervals.length-1;i++){
             if(intervals[i][1] > intervals[i+1][0]  ) return false;
        }
        return true;
    }
    public int minMeetingRooms(int[][] intervals) {
            Arrays.sort(intervals, new IntervalComparator());
            List<List<List<Integer>>> output = new ArrayList<>();

            ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(intervals[0][0],intervals[0][1]));
            List<List<Integer>> first =new ArrayList<List<Integer>>();
            first.add(list);
            output.add(first);

            for(int i=1; i<intervals.length;i++){
                int targetIndex = -1;
                int minEnd = Integer.MAX_VALUE;
                for(int j =0;j<output.size();j++){
                       int length = output.get(j).size();
                       if(intervals[i][0] >= output.get(j).get(length-1).get(1))  {
                           if(minEnd > output.get(j).get(length-1).get(1))
                               minEnd = output.get(j).get(length-1).get(1);
                               targetIndex = j;
                       }
               }
                if(targetIndex == -1) {
                   list = new ArrayList<Integer>(Arrays.asList(intervals[i][0],intervals[i][1]));
                    first =new ArrayList<List<Integer>>();
                    first.add(list);
                    output.add(first);
                } else output.get(targetIndex).add(Arrays.asList(intervals[targetIndex][0],intervals[targetIndex][1]));
            }
            return output.size();
        }
    class IntervalComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] A, int[] B){
            if(A[0] == B[0])
                return A[1]-B[1];
            else return A[0] -B[0];
        }
    }
}
class IntervalComparator implements Comparator<int[]> {
    @Override
    public int compare(int[] A, int[] B){
        if(A[0] == B[0])
            return A[1]-B[1];
        else return A[0] -B[0];
    }
}