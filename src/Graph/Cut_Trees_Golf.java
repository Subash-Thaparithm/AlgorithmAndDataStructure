package Graph;

import java.util.*;

public class Cut_Trees_Golf {
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    Map<Integer, AbstractMap.SimpleEntry<Integer, Integer>> heightSortedTrees = new TreeMap<>();
    //Sort and store trees according to their height
    //Travel to each trees with shortest path algorithm e.g Dijkshtra and add the steps
    //If all trees cannot be travelled, return -1
    //OR
    //Connected components and traverse the single connec. comps.
    //Edge cases
    //If more than one connected components with trees in diff comps., return -1
    public int cutOffTree(List<List<Integer>> forest) {
        //Edge cases
        if (forest.size() == 0) return 0;
        for (int row = 0; row < forest.size(); row++) {
            for (int col = 0; col < forest.get(0).size(); col++) {
                if (forest.get(row).get(col) > 1)
                    heightSortedTrees.put(forest.get(row).get(col), new AbstractMap.SimpleEntry<Integer, Integer>(row, col));
            }
        }
        int minTotalSteps = 0;
        AbstractMap.SimpleEntry<Integer, Integer> source = new AbstractMap.SimpleEntry<>(0,0);
        Iterator<Map.Entry<Integer, AbstractMap.SimpleEntry<Integer, Integer>>> treeIter = heightSortedTrees.entrySet().iterator();
        while(treeIter.hasNext()){
            Map.Entry<Integer, AbstractMap.SimpleEntry<Integer, Integer>> dest = treeIter.next();
            Integer distance = BFS_ShortestPath(forest,source.getKey(), source.getValue(), dest.getValue().getKey(),dest.getValue().getValue());
             distance = bfs(forest,source.getKey(),source.getValue(),dest.getValue().getKey(),dest.getValue().getValue());
            if (distance == null) return -1;
            else minTotalSteps += distance;

            source = dest.getValue() ;
        }

        return minTotalSteps;
    }

    private List<AbstractMap.SimpleEntry<Integer, Integer>> getNeighbours(int row, int col, List<List<Integer>> forest) {
        //Consider all four horizontal and vertical neighbours of the same colour
        List<AbstractMap.SimpleEntry<Integer, Integer>> neighbours = new ArrayList<>();
        if (isIndicesWithinRange(row - 1, col, forest) && forest.get(row-1).get(col) >0)
            neighbours.add(new AbstractMap.SimpleEntry<Integer, Integer>(row - 1, col));
        if (isIndicesWithinRange(row + 1, col, forest) && forest.get(row+1).get(col) >0)
            neighbours.add(new AbstractMap.SimpleEntry<Integer, Integer>(row + 1, col));
        if (isIndicesWithinRange(row, col - 1, forest) && forest.get(row).get(col-1) >0)
            neighbours.add(new AbstractMap.SimpleEntry<Integer, Integer>(row, col - 1));
        if (isIndicesWithinRange(row, col + 1, forest) && forest.get(row).get(col+1) >0)
            neighbours.add(new AbstractMap.SimpleEntry<Integer, Integer>(row, col + 1));

        return neighbours;
    }

    private boolean isIndicesWithinRange(int row, int col, List<List<Integer>> forest) {
        if (row >= 0 && row < forest.size() && col < forest.get(0).size() && col >= 0) return true;
        else return false;
    }

    //Use dijkshtra algorithm, modified version of BFS
    private Integer BFS_ShortestPath( List<List<Integer>> forest,int srcRow,int srcCol,int destRow,int destCol) {
        //Use BFS , No use of graph DS ,Define a queue of pair to insert points
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[forest.size()][forest.get(0).size()];
       // Map<int[], Integer> shortestPathFromSource = new HashMap<>();
        //shortestPathFromSource.put(new int[]{srcRow,srcCol}, 0);
        queue.add(new int[]{srcRow,srcCol,0});
        visited[0][0]=true;
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            for (AbstractMap.SimpleEntry<Integer, Integer> neighbour : getNeighbours(point[0], point[1], forest)) {
                //add in queue if not visited ,Flood fill
                if (!visited[neighbour.getKey()][neighbour.getValue()]==true) {
                   // shortestPathFromSource.put(new int[]{neighbour.getKey(),neighbour.getValue()}, shortestPathFromSource.get(new int[]{point[0],point[1]}) + 1);
                    queue.add(new int[]{neighbour.getKey(),neighbour.getValue(),point[2] + 1});
                    visited[neighbour.getKey()][neighbour.getValue()] = true;
                    if (neighbour.getKey() == destRow && neighbour.getValue()==destCol) return point[2] + 1;

                }
            }
        }
        return null;
    }
    //Avoid function calls and use of objects. Use primitive types, less function calls .....
    public Integer bfs(List<List<Integer>> forest, int sr, int sc, int tr, int tc) {
        int R = forest.size(), C = forest.get(0).size();
        Queue<int[]> queue = new LinkedList();
        queue.add(new int[]{sr, sc, 0});
        boolean[][] seen = new boolean[R][C];
        seen[sr][sc] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == tr && cur[1] == tc) return cur[2];
            for (int di = 0; di < 4; ++di) {
                int r = cur[0] + dr[di];
                int c = cur[1] + dc[di];
                if (0 <= r && r < R && 0 <= c && c < C &&
                        !seen[r][c] && forest.get(r).get(c) > 0) {
                    seen[r][c] = true;
                    queue.add(new int[]{r, c, cur[2]+1});
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {

    }
}