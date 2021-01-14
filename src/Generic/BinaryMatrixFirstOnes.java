package Generic;

import java.util.ArrayList;
import java.util.List;

// This is the BinaryMatrix's API interface. You should not implement it, or speculate about its implementation
interface BinaryMatrix {
    public int get(int x, int y) ;
    public List<Integer> dimensions = null ;
};
    public class BinaryMatrixFirstOnes {

       public static void main(String[] args) {
           int[][] matrix = new int[][]{{0,0},{1,1}};
           BinaryMatrixImpl binaryMatrix = new BinaryMatrixImpl(matrix);
           new BinaryMatrixFirstOnes().leftMostColumnWithOne(binaryMatrix);
          }
          public int leftMostColumnWithOne_1(BinaryMatrixImpl binaryMatrix) {
              int rowCount = binaryMatrix.dimensions.get(0);
              int colCount = binaryMatrix.dimensions.get(1);

              int [] colElements = new int[colCount];
              int currMinCol = -1;
              for(int row=0;row<rowCount;row++) {
                  int currRowMinCol = -1;
                  if (currMinCol != -1) colCount = currMinCol;
                  for(int col=0;col<colCount;col++) {
                      colElements[col] = binaryMatrix.get(row, col);
                  }
                  int colIndex = binarySearch(colElements, 0, colCount-1,1);
                  if (colIndex != -1){
                      currRowMinCol = colIndex;
                      for ( int col = colIndex-1; col >=0; col--) {
                           if (colElements[col] == 1) currRowMinCol = col;
                      }
                  }
                  if (currMinCol == -1 ) currMinCol = currRowMinCol;
                  else if (currRowMinCol !=-1 && currRowMinCol < currMinCol) currMinCol = currRowMinCol;
                  if (currMinCol == 0) return 0;
              }
              return currMinCol;
          }
          public int leftMostColumnWithOne(BinaryMatrixImpl binaryMatrix) {
            int rowCount = binaryMatrix.dimensions.get(0);
            int colCount = binaryMatrix.dimensions.get(1);

            int currMinColOne = colCount-1;
            for(int row=0;row<rowCount;row++) {
                    for(int col=currMinColOne;col>=0;col--){
                    int element = binaryMatrix.get(row, col);
                    if (element ==0) {
                        currMinColOne = col;
                        break;
                    }
                    else {
                        currMinColOne--;
                    }
                    }
            }
            if (currMinColOne == colCount-1) return -1;
            return currMinColOne +1;
        }
          private int binarySearch(int[] nums, int low, int high,int Target) { // returns index
              if (high < low) return -1;
              int midIndex = (low + high) / 2;

              if (Target == nums[midIndex]) return midIndex;

              else if (Target < nums[midIndex]) {
                  high = midIndex - 1;
              } else if (Target > nums[midIndex]) {
                  low = midIndex + 1;
              }
              return binarySearch(nums, low, high,Target);
          }
    }
    class BinaryMatrixImpl implements  BinaryMatrix{
        public List<Integer> dimensions = new ArrayList<>();
        int [][] matrix;
        public int get(int x, int y) {
              return matrix[x][y];
        }
        public BinaryMatrixImpl(int row, int col){
            this.dimensions.add(row);
            this.dimensions.add(col);
            this.matrix = new int[row][col];
        }
        public BinaryMatrixImpl(int[][] matrix){
            this.dimensions.add(matrix.length);
            this.dimensions.add(matrix[0].length);
            this.matrix = matrix;
        }
      }
