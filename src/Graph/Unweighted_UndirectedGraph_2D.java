package Graph;

import java.util.*;

public class Unweighted_UndirectedGraph_2D<T> {
    final private HashMap<T, Set<T>> adjacencyList;
    final private HashMap<T, Integer> indexOfPoints = new HashMap<>();

    /**
     * Create new Graph object.
     */
    public Unweighted_UndirectedGraph_2D() {
        this.adjacencyList = new HashMap<>();
    }

    /**
     * Add new vertex to the graph.
     *
     * @param v The vertex object.
     */
    public void addVertex(T v) {
        if (this.adjacencyList.containsKey(v)) {
            throw new IllegalArgumentException("Vertex already exists.");
        }
        this.adjacencyList.put(v, new HashSet<T>());
        int maxIndex =-1;
        if (indexOfPoints.size() != 0)
            maxIndex = Collections.max(indexOfPoints.values());
        this.indexOfPoints.put(v, ++maxIndex);
    }

    /**
     * Remove the vertex v from the graph.
     *
     * @param v The vertex that will be removed.
     */
    public void removeVertex(T v) {
        if (!this.adjacencyList.containsKey(v)) {
            throw new IllegalArgumentException("Vertex doesn't exist.");
        }

        this.adjacencyList.remove(v);

        for (T u: this.getAllVertices()) {
            this.adjacencyList.get(u).remove(v);
        }
    }

    /**
     * Add new edge between vertex. Adding new edge from u to v will
     * automatically add new edge from v to u since the graph is undirected.
     *
     * @param v Start vertex.
     * @param u Destination vertex.
     */
    public void addEdge(T v, T u) {
        if (!this.adjacencyList.containsKey(v) || !this.adjacencyList.containsKey(u)) {
            throw new IllegalArgumentException();
        }

        this.adjacencyList.get(v).add(u);
        this.adjacencyList.get(u).add(v);
    }

    /**
     * Remove the edge between vertex. Removing the edge from u to v will
     * automatically remove the edge from v to u since the graph is undirected.
     *
     * @param v Start vertex.
     * @param u Destination vertex.
     */
    public void removeEdge(Integer v, Integer u) {
        if (!this.adjacencyList.containsKey(v) || !this.adjacencyList.containsKey(u)) {
            throw new IllegalArgumentException();
        }

        this.adjacencyList.get(v).remove(u);
        this.adjacencyList.get(u).remove(v);
    }

    /**
     * Check adjacency between 2 vertices in the graph.
     *
     * @param v Start vertex.
     * @param u Destination vertex.
     * @return <tt>true</tt> if the vertex v and u are connected.
     */
    public boolean isAdjacent(T v, T u) {
        return this.adjacencyList.get(v).contains(u);
    }

    /**
     * Get connected vertices of a vertex.
     *
     * @param v The vertex.
     * @return An iterable for connected vertices.
     */
    public Iterable<T> getNeighbors(T v) {
        return this.adjacencyList.get(v);
    }

    /**
     * Get all vertices in the graph.
     *
     * @return An Iterable for all vertices in the graph.
     */
    public Iterable<T> getAllVertices() {
        return this.adjacencyList.keySet();
    }

    // A function used by DFS
    public void DFSUtil(T v,boolean visited[])
    {
        // Mark the current node as visited and print it
        visited[indexOfPoints.get(v)] = true;
        System.out.print(v+" ");

        // Recur for all the vertices adjacent to this vertex
        Iterator<T> i = adjacencyList.get(v).iterator();
        while (i.hasNext())
        {
            T n = i.next();
            if (!visited[indexOfPoints.get(n)])
                DFSUtil( n, visited);
        }
    }
    //The function to do DFS traversal. It uses recursive DFSUtil()
    public int removeStones_NaiveDFS(int[][] stones) {
        int countConnectedComponents = 0;
        //Construct undirected unweighted(weight=1) graph from co-ordinates whose edge connects all points which share row or column with other
        for (int i = 0; i <stones.length ; i++) {
            if (! adjacencyList.containsKey(stones[i]))  this.addVertex((T)stones[i]);
            for (int j = 1; j <stones.length ; j++) {
                if (! adjacencyList.containsKey(stones[j]))  this.addVertex((T)stones[j]);
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) addEdge((T)stones[i], (T)stones[j]);
            }
        }
        // Mark all the vertices as not visited(set as false by default in java)
        boolean visited[] = new boolean[this.adjacencyList.size()];
        // Call the recursive helper function to print DFS traversal
        for (T point:getAllVertices()) {
            if (! visited[indexOfPoints.get(point)])
            {
                DFSUtil(point , visited);
                countConnectedComponents++;
            }
        }
        return indexOfPoints.size() - countConnectedComponents;
    }

    public int removeStones_CleverDFS(int[][] stones) {
        if (stones.length ==0 || stones[0].length ==0) return 0;
        Map<Integer, List<int[]>> mapX = new HashMap<>();
        Map<Integer, List<int[]>> mapY = new HashMap<>();

        // building map of all stone on same row/col
        for(int[] stone : stones) {
            List<int[]> xList = mapX.getOrDefault(stone[0], new ArrayList<>());
            xList.add(stone);
            mapX.put(stone[0], xList);

            List<int[]> yList = mapY.getOrDefault(stone[1], new ArrayList<>());
            yList.add(stone);
            mapY.put(stone[1], yList);
        }

        Set<int[]> visited = new HashSet<>();

        int numComp = 0;
        for(int[] stone : stones) {
            if(!visited.contains(stone)) {
                numComp++;
                dfs(mapX,mapY, stone, visited);
            }
        }
        return stones.length - numComp;
    }

    private void dfs(Map<Integer, List<int[]>> mapX,
                     Map<Integer, List<int[]>> mapY,
                     int[] stone,
                     Set<int[]> visited) {
        visited.add(stone);
        for(int[] s : mapX.get(stone[0]))
            if(!visited.contains(s)) dfs(mapX, mapY, s, visited);

        for(int[] s : mapY.get(stone[1]))
            if(!visited.contains(s)) dfs(mapX, mapY, s, visited);
    }
}