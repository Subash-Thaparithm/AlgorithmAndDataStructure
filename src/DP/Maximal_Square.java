package DP;

import java.util.Deque;
import java.util.LinkedList;
public class Maximal_Square {
    public static void main(String[] args) {

        new Maximal_Square().largestArea_histogram(new int[]{2,1,2});
        new Maximal_Square().countSquares(new int[][]
        {
             {1,0,1},
              {1,1,0},
              {1,1,0}
      });

     new Maximal_Square().orderOfLargestPlusSign(5,new int[][]{{4,2}});
    }
    public int maximalSquare(char[][] matrix) {
        if (matrix.length ==0) return 0;
        int max = Character.getNumericValue(matrix[0][0]);
        //Construct a matrix[][] where each element holds the area of maximum subsquare upto this index
        int[][] dp = new int[matrix.length][matrix[0].length];
        //Copy the value of elements in the first row and first column
        for(int i=0; i<matrix.length;i++){
            dp[i][0] =  Character.getNumericValue(matrix[i][0]) ;
            if (matrix[i][0] =='1') max =1;
        }
        for(int i=0; i<matrix[0].length;i++){
            dp[0][i] = Character.getNumericValue(matrix[0][i]);
            if (matrix[0][i] =='1') max =1;
        }
        for(int i=1; i<matrix.length;i++)  {
            for(int j=1; j<matrix[0].length;j++)  {
                // Min(top, left, top-left-diagonal) +1
                if (matrix[i][j] =='1') dp[i][j] = Math.min(Math.min(dp[i-1][j] ,dp[i][j-1]),dp[i-1][j-1]) +1;
                else dp[i][j] =0;
                if (max<dp[i][j]) max = dp[i][j];

            }
        }
        return max*max; // Output Area
    }
    public int countSquares(int[][] matrix){
        int totalSquares = 0;
        if (matrix.length ==0) return 0;
        int max = (matrix[0][0]);
        //Construct a matrix[][] where each element holds the area of maximum subsquare upto this index
        int[][] dp = new int[matrix.length][matrix[0].length];
        //Copy the value of elements in the first row and first column
        for(int i=0; i<matrix.length;i++){
            dp[i][0] =  (matrix[i][0]) ;
            if (matrix[i][0] ==1) {
                max =1;
                totalSquares++ ;  // Square of size 1
            }
        }
        for(int i=1; i<matrix[0].length;i++){
            dp[0][i] = (matrix[0][i]);
            if (matrix[0][i] ==1) {
                max =1;
                totalSquares++ ; // Square of size 1
            }
        }
        for(int i=1; i<matrix.length;i++)  {
            for(int j=1; j<matrix[0].length;j++)  {
                // Min(top, left, top-left-diagonal) +1
                if (matrix[i][j] ==1) {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j] ,dp[i][j-1]),dp[i-1][j-1]) + 1;
                    totalSquares = totalSquares + dp[i][j] ;
                }
                else dp[i][j] =0;
                if (max < dp[i][j]) max = dp[i][j];
            }
        }
        return totalSquares; // Output no . of squares
    }
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        int [][] left = new int[N][N]; // Stores maximum number of consecutive 1s to the left of and including (i,j)
        int [][] right = new int[N][N];// Stores maximum number of consecutive 1s to the right of (i,j)
        int [][] top = new int[N][N];// Stores maximum number of consecutive 1s to the top of (i,j)
        int [][] down = new int[N][N];// Stores maximum number of consecutive 1s to the down of (i,j)
        /**
         * //Construct the input grid
         */
        int [][] input = new int[N][N];
        for (int i = 0; i <N ; i++) {
            for (int j = 0; j <N ; j++) {
             input[i][j] = 1;
            }
        }
        //Fill mines with 0 in input grid
        for (int i = 0; i < mines.length ; i++) {
            input[mines[i][0]][mines[i][1]] = 0;
            }
        //Fill first column with given values
        for (int i = 0; i <N ; i++) {
            left[i][0] = input[i][0];
        }
        //Fill last column with given values
        for (int i = 0; i <N ; i++) {
            right[i][N-1] = input[i][N-1];
        }
        for (int i = 0; i <N ; i++) {
            /**
             * //Left matrix
             */
            for (int j = 1; j <N ; j++) {
             if (input[i][j] ==1) left[i][j] = left[i][j-1] + input[i][j];
             else left[i][j]= 0;
            }
            /**
             *  //right matrix
             */
            for (int j = N-2; j >=0 ; j--) {
                if (input[i][j] ==1) right[i][j] = right[i][j+1] + input[i][j];
                else right[i][j]= 0;
            }
        }

        /**
         * //top matrix
         */
        //Fill first row with given values
        for (int i = 0; i <N ; i++) {
            top[0][i] = input[0][i];
        }
        //Fill last row with given values
        for (int i = 0; i <N ; i++) {
            down[N-1][i] = input[N-1][i];
        }
            for (int j = 0; j <N ; j++) {
                for (int i = 1; i <N ; i++) {
                if (input[i][j] ==1) top[i][j] = top[i-1][j] + input[i][j];
                else top[i][j]= 0;
            }
                for (int i = N-2; i >=0 ; i--) {
                    if (input[i][j] ==1) down[i][j] = down[i+1][j] + input[i][j];
                    else down[i][j]= 0;
                }
        }
        // Ans = Maximum of (minimum of 4 values)
        int [][] dp = new int[N][N];
        int maxPlusOneOrder = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] =Math.min(left[i][j], Math.min(right[i][j] , Math.min(top[i][j], down[i][j]))) ; // Take minimum of all 4 sub metrices value
                if (dp[i][j] > maxPlusOneOrder) maxPlusOneOrder = dp[i][j];
            }
        }
        return maxPlusOneOrder;
    }
    public int maximalRectangle_CoeringNotAllCases(char[][] matrix) {
        if (matrix.length ==0) return 0;
        int maxArea = Character.getNumericValue(matrix[0][0]);
        //Construct a matrix[][] where each element holds the area of maximum subsquare upto this index
        int[][] dpTop = new int[matrix.length][matrix[0].length];
        int[][] dpLeft = new int[matrix.length][matrix[0].length];
        int[][] dpTop_Max = new int[matrix.length][matrix[0].length];
        int[][] dpLeft_Max = new int[matrix.length][matrix[0].length];
        int[][] dpOutput = new int[matrix.length][matrix[0].length];
        //Copy the value of elements in the first row and first column for left matrix
        for(int i=0; i<matrix.length;i++){
            dpLeft[i][0] =  Character.getNumericValue(matrix[i][0]) ;

        }
        //Copy the value of elements in the first row and first column for top matrix
        for(int i=0; i<matrix[0].length;i++){
            dpTop[0][i] = Character.getNumericValue(matrix[0][i]);

        }
        //Compute the left output matrix
        for(int i=0; i<matrix.length;i++)  {
            for(int j=1; j<matrix[0].length;j++)  {
                if (matrix[i][j] =='1') dpLeft[i][j] = dpLeft[i][j-1] + Character.getNumericValue(matrix[i][j]);
                else dpLeft[i][j]= 0;
            }
        }
        //Compute the top output matrix
        for(int i=1; i<matrix.length;i++)  {
            for(int j=0; j<matrix[0].length;j++)  {
                if (matrix[i][j] =='1') dpTop[i][j] = dpTop[i-1][j] + Character.getNumericValue(matrix[i][j]);
                else dpTop[i][j]= 0;
            }
        }
        //Compute max rectangle output
        //Max area of first column  depends only on the dpTop matrix
        for (int i = 0; i <matrix.length ; i++) {
            if (dpTop[i][0] > maxArea) maxArea = dpTop[i][0];
        }
        //Max area of first row depends only on the dpLeft matrix
        for (int j = 0; j < matrix[0].length; j++) {
           if (dpLeft[0][j] > maxArea) maxArea = dpLeft[0][j];
        }

        for (int i = 1; i < matrix.length ; i++) {
            for (int j = 1; j < matrix[0].length ; j++) {
                int topValue = 0;
                int leftValue = 0;
                if (matrix[i][j] =='1') {
                    topValue = Math.min(Math.min(dpTop[i-1][j] ,dpTop[i][j-1]),dpTop[i-1][j-1]) +1;
                    dpTop_Max[i][j] = topValue;
                }
                if (matrix[i][j] =='1') {
                    leftValue = Math.min(Math.min(dpLeft[i-1][j] ,dpLeft[i][j-1]),dpLeft[i-1][j-1]) +1;
                    dpLeft_Max[i][j] = leftValue;
                }
                if (topValue==1 || leftValue ==1) dpOutput[i][j] = Math.max(dpTop[i][j] , dpLeft[i][j] ) ;
                else dpOutput[i][j] = topValue * leftValue ;

                if (maxArea<dpOutput[i][j]) maxArea = dpOutput[i][j];
            }
        }
        return maxArea ;
    }
    public int largestRectangleArea_BaseddOnhistogram(char[][] matrix) {
        //Initialise an array of length = input matrix column length with elements from first row of input matrix
        int [] histogramArray = new int[matrix[0].length];
        for (int i = 0; i < matrix[0].length; i++) {
            histogramArray[i] = Character.getNumericValue(matrix[0][i]);
        }

        int maxArea = 0;
        int area = maxHistogram(histogramArray);
        if (maxArea < area) maxArea = area;
        //Form histogram array for top to the current row
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                   if (matrix[i][j] =='0') histogramArray[j]=0;
                   else histogramArray[j]++;
            }
            //Calculate max area for this histogram , update max area
            area = maxHistogram(histogramArray);
            if (maxArea < area) maxArea = area;
        }
        return maxArea ;
    }
    public int largestArea_histogram(int[] heights) {
        //Stores the index of array instead of the actual value
        if (heights.length == 0) return 0;
        else if (heights.length == 1) return  heights[0];
       // Stack<Integer>  increasingHeight = new Stack<>();
        Deque<Integer> increasingHeight = new LinkedList<Integer>();
        int nextIndex = 0;
        int maxArea = 0;
        for(int i=0; i< heights.length; i++){
            if (!increasingHeight.isEmpty()) {
                if (nextIndex <= heights.length-1 && heights[nextIndex] < heights[increasingHeight.getLast()] || nextIndex == heights.length){
                //Calculate the possible histograms area between next index exclusive and the element in the stack with height less than next index height
                    while (! increasingHeight.isEmpty() && heights[increasingHeight.getLast()] > heights[nextIndex]){
                        int height = heights[increasingHeight.removeLast()];
                        // If the stack is empty
                        if (increasingHeight.isEmpty()) {
                            if (maxArea < height * (nextIndex)) maxArea = height * (nextIndex); // height * width
                        }
                        else {
                            //If the stack is not empty, pop the top element from the stack and calculate the possible largest histogram area including this top element histogram
                            if (maxArea <  height * (nextIndex - increasingHeight.getLast() -1) )  maxArea = height * (nextIndex - increasingHeight.getLast() -1); // height * width
                        }
                    }
                    if (nextIndex <= heights.length-1){
                        increasingHeight.add(nextIndex);
                        nextIndex ++;
                    }
                }
                else if (nextIndex <= heights.length-1 ){
                    increasingHeight.add(nextIndex);
                    nextIndex ++;
                }
            }
            else if (i < heights.length-1) //add element in the stack
            {
                increasingHeight.add(nextIndex);
                nextIndex ++;
            }
        }
        if (!increasingHeight.isEmpty()) {
                //Calculate the possible histograms area between next index exclusive and the element in the stack with height less than next index height
                while (! increasingHeight.isEmpty()){
                    int height = heights[increasingHeight.removeLast()];
                    // If the stack is empty
                    if (increasingHeight.isEmpty()) {
                        if (maxArea < height * (nextIndex)) maxArea = height * (nextIndex); // height * width
                    }
                    else {
                        //If the stack is not empty, pop the top element from the stack and calculate the possible largest histogram area including this top element histogram
                        if (maxArea <  height * (nextIndex - increasingHeight.getLast() -1) )  maxArea = height * (nextIndex - increasingHeight.getLast() -1); // height * width
                    }
                }
            }
        return maxArea ;
    }
    /**
     * Given an array representing height of bar in bar graph, find max histogram
     * area in the bar graph. Max histogram will be max rectangular area in the
     * graph.
     *
     * Maintain a stack
     *
     * If stack is empty or value at index of stack is less than or equal to value at current
     * index, push this into stack.
     * Otherwise keep removing values from stack till value at index at top of stack is
     * less than value at current index.
     * While removing value from stack calculate area
     * if stack is empty
     * it means that till this point value just removed has to be smallest element
     * so area = input[top] * i
     * if stack is not empty then this value at index top is less than or equal to
     * everything from stack top + 1 till i. So area will
     * area = input[top] * (i - stack.peek() - 1);
     * Finally maxArea is area if area is greater than maxArea.
     *
     *
     * Time complexity is O(n)
     * Space complexity is O(n)
     *
     * References:
     * http://www.geeksforgeeks.org/largest-rectangle-under-histogram/
     */
    public int maxHistogram(int heights[]){
                Deque<Integer> stack = new LinkedList<Integer>();
                int maxArea = 0;
                int area = 0;
                int i;
                for(i=0; i < heights.length;){
                    if(stack.isEmpty() || heights[stack.peekFirst()] <= heights[i]){
                        stack.offerFirst(i++);
                    }else{
                        int top = stack.pollFirst();
                        //if stack is empty means everything till i has to be
                        //greater or equal to input[top] so get area by
                        //input[top] * i;
                        if(stack.isEmpty()){
                            area = heights[top] * i;
                        }
                        //if stack is not empty then everythin from i-1 to input.peek() + 1
                        //has to be greater or equal to input[top]
                        //so area = input[top]*(i - stack.peek() - 1);
                        else{
                            area = heights[top] * (i - stack.peekFirst() - 1);
                        }
                        if(area > maxArea){
                            maxArea = area;
                        }
                    }
                }
                while(!stack.isEmpty()){
                    int top = stack.pollFirst();
                    //if stack is empty means everything till i has to be
                    //greater or equal to input[top] so get area by
                    //input[top] * i;
                    if(stack.isEmpty()){
                        area = heights[top] * i;
                    }
                    //if stack is not empty then everything from i-1 to input.peek() + 1
                    //has to be greater or equal to input[top]
                    //so area = input[top]*(i - stack.peek() - 1);
                    else{
                        area = heights[top] * (i - stack.peekFirst() - 1);
                    }
                    if(area > maxArea){
                        maxArea = area;
                    }
                }
                return maxArea;
        }
}