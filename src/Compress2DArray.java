import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class Compress2DArray
{
    //Flatten the 2D array
    //Sort the Array
    //Bend the array

    public int[][] compress(int[][] inputArr)
    {
        //Flatten the array
        List<Integer> flattened = new ArrayList<>();
        for (int[] arr:inputArr )
        {
            for (int num:arr)
            {
             flattened.add(num);
            }
        }

        //Sort the array list
        List<Integer> sortedFlattened = new ArrayList<>(flattened);

        Collections.sort(sortedFlattened);
        //Label the flattened array
        List<Integer> labelledSorted = new ArrayList<>();
        int currLabel =1;
        labelledSorted.add(currLabel);

        for(int i=2; i<=flattened.size();i++)
        {
            if(sortedFlattened.get(i-1) == sortedFlattened.get(i-2)) labelledSorted.add(currLabel);
            else labelledSorted.add(++currLabel);
        }
        //Label the input array
        for(int i=0; i<flattened.size();i++ )
        {
            int element = flattened.get(i);

            OptionalInt index = IntStream.range(0, flattened.size())
                    .filter(j -> sortedFlattened.get(j)==element)
                    .findFirst();
            int order = labelledSorted.get(index.getAsInt());
            flattened.set(i, order);//Correct adding index
        }
        //bend the labelled back to 2D array
        int[][] output = new int[inputArr.length][inputArr[0].length];
        int column=0;
        int row =0;
        for(int i=0; i<flattened.size();i++ )
        {
            output[row][column]= flattened.get(i);
            column++;

            if(column == inputArr[0].length)
            {
                column =0;
                row++;
            }
        }

    return output;
    }
    public static void main(String args[])
    {
       int arr[][] = { {20, 80, 60, 70},  {11, 90, 22, 44},{33, 99, 49, 88}
    };

        System.out.println(new Compress2DArray().compress(arr));
    }
}
