package Graph;



import java.util.*;

public class WeightedUnDirectedGraph
{
    //Array of List
    private int numVertices;
    private LinkedList[] graphInternal;
    private boolean[] visited ;
    private boolean[] currentrecursionStack;
    private int startIndex;
    private boolean isCycle;

    public static void main(String args[])
    {
        //Initialise the graph
        WeightedUnDirectedGraph g = new WeightedUnDirectedGraph(4);

        g.addEdge(0, 1, 2);
        g.addEdge(1, 2, 2);
        g.addEdge(2, 3, 3);
        g.addEdge(2, 1, 6);
        g.addEdge(0, 2, 1);

        //int[] shortestDistance = g.dijkshtraAlgo(0);
        //shortestDistance = g.bellmannFord_RelaxEdges(0);

         //System.out.println("Distance from 0 =" +  shortestDistance);
         System.out.println("The minimum spanning tree is: "); (g.Prims(0)).printGraph();
         System.out.println("The minimum spanning tree is: "); (g.kruskal()).printGraph();
    }
    public WeightedUnDirectedGraph(int v)   // Adjacent list
    {
        this.numVertices = v;
        visited= new boolean[numVertices];
        setCurrentrecursionStack(new boolean[numVertices]);
        graphInternal = new LinkedList[v];

        for(int i=0; i< getNumVertices();i++)
        {
            graphInternal[i] = new LinkedList<graphNode>();
        }
    }
    /**
     *
     * @param vertex the vertex of which adjacent list is to be constructed
     * @param list A linked list which will be added as adjacent list of this vertex
     */
    private void addlist(int vertex, LinkedList list)
    {
        graphInternal[vertex] = list;
    }
    public void addEdge(int source, int destination, int weight)
    {
        if( graphInternal[source] == null)
            graphInternal[source] = new LinkedList<graphNode>();
        graphInternal[source].add(new graphNode(destination, weight));
    }
    public int getNumVertices() {
        return numVertices;
    }
    public void setNumVertices(int numVertices) {
        this.numVertices = numVertices;
    }
    public LinkedList[] getGraphInternal() {
        return graphInternal;
    }
    public void setGraphInternal(LinkedList[] graphInternal) {
        this.graphInternal = graphInternal;
    }
    // A utility function to print the adjacency list
    // representation of graph
    void printGraph()
    {
        for(Edge edge: this.getEdges())
        {
            System.out.print(" Edge  :" + edge.getSource() +"--->"+ edge.getDestination() +" :"+ edge.getWeight());


            System.out.println("\n");
        }
    }
    List<Edge> getEdges()
    {
        List<Edge> edgeList = new ArrayList<Edge>();
        for(int i=0; i<graphInternal.length;i++)
        {
            for (Object j:graphInternal[i]) {
                graphNode temp = (graphNode)j;
                Edge newEdge = new Edge(i, temp.getPosition(), temp.getWeight());

                if (! edgeList.contains(newEdge))
                    edgeList.add(newEdge);
            }
        }
        return edgeList;
    }
    /**
     * Assuming the graph is a connected graph
     * @param startVertex
     * @param visited
     * @param currentrecursionStack
     */
    public  boolean  DFS(int startVertex, boolean[] visited, boolean[] currentrecursionStack)
    {
        if (currentrecursionStack[startVertex]) { // Already visited
            setCycle(true);
            System.out.println("Cycle is detected");
            return true;
        }
        visited[startVertex] = true;                                    // To avoid cycle in graph
        currentrecursionStack[startVertex] = true;
        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> i = graphInternal[startVertex].listIterator(); // No preorder, postorder or inorder as there are no root and leaves
        while (i.hasNext()) {
            int n = i.next();

            if( DFS(n, visited, currentrecursionStack)) {
                return true;
            }
            //else return true for undirected graph.
        }
        currentrecursionStack[startVertex] = false;
        return false;

    }
    public void startDFS(boolean[] visited, boolean[] currentrecursionStack)
    {
        // Start with all the vertices to check whether it is a cyclic graph since it is a directed graph
        for(int j=0; j< this.numVertices;j++) {
            if (DFS( j, visited,  currentrecursionStack)) return;
        }
    }
    /**
     * Shortest path from single vertex to all the other vertices. Djkshtra Algorithm
     * Directed as well as undirected graph
     * @return
     */
    public int[] dijkshtraAlgo(int source) // O(V2)
    {
        //DirectedUnweightedGraph representation using the adjacent list
        boolean[] visited = new boolean[numVertices]; // Initialise with false
        int[] currentDistance = new int[numVertices]; // Initialise with infinity except source
        PriorityQueue<graphNode> priorityQueue = new PriorityQueue<>((a, b) -> a.getWeight() - b.getWeight());  // Minimum Priority Queue

        for(int i=0; i<numVertices;i++)
        {
            if (i==source)
            {
                currentDistance[i] = 0;
                priorityQueue.add(new graphNode(i,0));
            }
            else {
                currentDistance[i] = Integer.MAX_VALUE;
            }
        }

        while(! priorityQueue.isEmpty()) { // loop V times

            graphNode node = priorityQueue.remove();

            for (Object j : this.getGraphInternal()[node.getPosition()]) // loop V times
            {
                graphNode temp = (graphNode)j;
                if (! visited[temp.getPosition()])
                {
                    if (currentDistance[node.getPosition()] + temp.getWeight() < currentDistance[temp.getPosition()])
                        currentDistance[temp.getPosition()] = currentDistance[node.getPosition()] + temp.getWeight();
                    visited[node.getPosition()] = true;

                    priorityQueue.add(temp);
                }
            }
        }
        return currentDistance;
    }
    public int[] bellmannFord_RelaxEdges(int source) // O(E *V), taking neighbouring vertices .. wrong... Should relax the edges instead
    {
        int[] currentDistance = new int[numVertices]; // Initialise with infinity except source

        for(int i=0; i<numVertices;i++)
        {
            if (i==source)
            {
                currentDistance[i] = 0;
            }
            else {
                currentDistance[i] = Integer.MAX_VALUE;
            }
        }
        // Relaxation by iterating over all the edges
        for(int i=0;i<numVertices-1;i++)
        {
            for (Edge edge : this.getEdges())
            {
                if (currentDistance[edge.getSource()] + edge.getWeight() < currentDistance[edge.getDestination()])
                    currentDistance[edge.getDestination()] =currentDistance[edge.getSource()] + edge.getWeight();
            }
        }

        // Relaxation once more to detect negative sum cycle
        for(int i=0; i<1 ;i++)
        {
            for (Edge edge : this.getEdges())
            {
                if (currentDistance[edge.getSource()] + edge.getWeight() < currentDistance[edge.getDestination()])
                    System.out.println("Negative edge weight sum cycle detected.....");
            }
        }
        return currentDistance;
    }
    public boolean[] getVisited() {
        return visited;
    }

    public void setVisited(boolean[] visited) {
        this.visited = visited;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public boolean isCycle() {
        return isCycle;
    }

    public void setCycle(boolean cycle) {
        isCycle = cycle;
    }

    public boolean[] getCurrentrecursionStack() {
        return currentrecursionStack;
    }

    public void setCurrentrecursionStack(boolean[] currentrecursionStack) {
        this.currentrecursionStack = currentrecursionStack;
    }
    /**
     * O(E + V log V) amortized time, if you use a Fibonacci Heap. For dense graph
     * Prim’s algorithm works on undirected graphs only, since the concept of an MST assumes that graphs are inherently undirected.
     * Handle negative weights
     * @param starting
     * @return
     */
    public WeightedUnDirectedGraph Prims(int starting) // Minimum Spanning Tree
    {
        Set<Integer> visited = new TreeSet<>();

        //Add the starting node to the tree

        visited.add(starting);

        WeightedUnDirectedGraph spanningTree = new WeightedUnDirectedGraph(numVertices);

        while(visited.size() != numVertices)
        {
            //Select the shortest or one of the shortest edges connecting visited nodes to unvisited nodes
            Edge nearestEdge = null;
           for(Edge edge:this.getEdges())
           {
               if((visited.contains(edge.getSource()) && ! visited.contains(edge.getDestination()))||
                 (! visited.contains(edge.getSource()) &&  visited.contains(edge.getDestination())))
                 {
                     if (nearestEdge == null) nearestEdge = edge;
                      else if (nearestEdge.getWeight() > edge.getWeight())
                      {
                          nearestEdge = edge;
                      }
                 }
           }
           if(nearestEdge != null) {
               spanningTree.addEdge(nearestEdge.getSource(), nearestEdge.getDestination(), nearestEdge.getWeight());
               visited.add(nearestEdge.getSource());
               visited.add(nearestEdge.getDestination());
           }
        }
        return spanningTree;

    }
    //O(E log V) time, for sparse graph
    public weightedDirectedGraph kruskal() // Minimum Spanning tree
    {
        //Start with the shortest edge and add to the tree
        //Repeatedly select the shortest edge from remaining and add it unless it creates a cycle and connects already visited both nodes
        Set<Integer> visited = new TreeSet<>();
        weightedDirectedGraph spanningTree = new weightedDirectedGraph(numVertices);
        while(visited.size() != numVertices) {
            Edge shortestEdge = null;
            for (Edge edge : this.getEdges()) {
                //Select the shortest or one of them
                if  ( !(visited.contains(edge.getSource()) && visited.contains(edge.getDestination()))) // Make sure the edge has not been added already
                {
                    if (shortestEdge == null) shortestEdge = edge;
                    else if (edge.getWeight() < shortestEdge.getWeight()) shortestEdge = edge;

                }
            }

            if(shortestEdge != null) {
                spanningTree.addEdge(shortestEdge.getSource(), shortestEdge.getDestination(), shortestEdge.getWeight());
                visited.add(shortestEdge.getSource());
                visited.add(shortestEdge.getDestination());
            }


        }
        return spanningTree;

    }

}




