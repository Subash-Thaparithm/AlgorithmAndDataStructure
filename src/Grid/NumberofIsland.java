package Grid;

public class NumberofIsland {
    /**
     * Scan through the elements. If 1 is reached, use DFS for its 4 neighbouring elements and continue like DFS further if they are 1. Label visited elements as 0.
     * Increase counter after  DFS returns .
     */
    public int numIslands(char[][] grid) {
        int countIsland = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                  if (grid[i][j] =='1') {
                      DFS(grid, i,j);
                      countIsland++;
                  }
            }
        }
        return countIsland;
    }
    private void DFS(char[][] grid, int row, int column){
        grid[row][column] ='0';
        if (column!=0 && grid[row][column-1]=='1') DFS(grid,row,column-1);
        if (column != grid[0].length-1 && grid[row][column+1]=='1') DFS(grid,row,column+1);
        if (row != 0 && grid[row-1][column]=='1') DFS(grid,row-1,column);
        if (row != grid.length-1 && grid[row+1][column]=='1') DFS(grid,row+1,column);
    }
}
