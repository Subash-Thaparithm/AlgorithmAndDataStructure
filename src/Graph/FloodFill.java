package Graph;
import java.util.*;

public class FloodFill {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        //Edge cases
        if (image.length == 0) return image;
        else if (sr >= image.length || sc >= image[0].length) return image;
        //Use BFS to flood fill the matrix, No use of graph DS
        //Define a queue of pair to insert points
        int sourceColour =  image[sr][sc];
        Queue<AbstractMap.SimpleEntry<Integer,Integer>> queue = new LinkedList<>();
        List<AbstractMap.SimpleEntry<Integer,Integer>> visited = new ArrayList<>();

        queue.add(new AbstractMap.SimpleEntry<Integer, Integer>(sr,sc));
        visited.add(new AbstractMap.SimpleEntry<Integer, Integer>(sr,sc));
        image[sr][sc] = newColor;
        while (!queue.isEmpty()){
            AbstractMap.SimpleEntry<Integer,Integer> point = queue.poll();
            for(AbstractMap.SimpleEntry<Integer,Integer> neighbour: getNeighbours(point,image,sourceColour)) {
                //add in queue if not visited ,Flood fill
                if (! visited.contains(neighbour)) {
                    queue.add(neighbour);
                    visited.add(neighbour);
                }
                image[neighbour.getKey()][neighbour.getValue()] = newColor;
            }
        }
        return image;
    }
    private List<AbstractMap.SimpleEntry<Integer,Integer>> getNeighbours(AbstractMap.SimpleEntry<Integer,Integer> point,int[][] image,int sourceColour){
        //Consider all four horizontal and vertical neighbours of the same colour
        int row = point.getKey();
        int col = point.getValue();
        List<AbstractMap.SimpleEntry<Integer,Integer>> neighbours = new ArrayList<>();
        if(isIndicesWithinRange(row-1,col,image) && image[row-1][col]==sourceColour)
            neighbours.add(new AbstractMap.SimpleEntry<Integer, Integer>(row-1,col));
        if(isIndicesWithinRange(row+1,col,image) && image[row+1][col]==sourceColour)
            neighbours.add(new AbstractMap.SimpleEntry<Integer, Integer>(row+1,col));
        if(isIndicesWithinRange(row,col-1,image) && image[row][col-1]==sourceColour)
            neighbours.add(new AbstractMap.SimpleEntry<Integer, Integer>(row,col-1));
        if(isIndicesWithinRange(row,col+1,image) && image[row][col+1]==sourceColour)
            neighbours.add(new AbstractMap.SimpleEntry<Integer, Integer>(row,col+1));

        return neighbours;
    }
    private boolean isIndicesWithinRange(int row, int col, int[][] image){
        if(row>=0 && row < image.length && col<image[0].length && col >= 0) return true;
        else return false;
    }
    public static void main(String[] args) {

    }
}
