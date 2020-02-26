import java.util.*;

public class weightedDirectedGraph
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
        weightedDirectedGraph g = new weightedDirectedGraph(6);

        g.addEdge(0, 1, 6);
        g.addEdge(0, 2, 2);
        g.addEdge(1, 3, 3);
        g.addEdge(2, 1, 6);
        g.addEdge(2, 4, 5);
        g.addEdge(4, 3, 4);
        g.addEdge(1, 4, 5);
        g.addEdge(3, 2, -8);
        g.addEdge(4, 5, -1);

        int[] shortestDistance = g.dijkshtraAlgo(0);

        shortestDistance = g.bellmannFord_RelaxEdges(0);

        System.out.println("Distance from 0 =" +  shortestDistance);

    }

    weightedDirectedGraph(int v)   // Adjacent list
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
                edgeList.add(new Edge(i, temp.getPosition(), temp.getWeight()));
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
     * @return
     */
    public int[] dijkshtraAlgo(int source) // O(V2), cannot handle negative edge weights
    {
        //Graph representation using the adjacent list
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


    public int[] bellmannFord_RelaxEdges(int source) // O(E *V), taking neighbouring vertices .. wrong... Should relax the edges instead.. Handle negative edge weights
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
}

class graphNode
{
    private int position;
    private int weight;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    graphNode(int position, int weight)
    {
        this.position = position;
        this.weight = weight;
    }
}
class Edge
{
    private int source;
    private int destination;
    private int weight;

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Edge(int source, int destination, int weight)
    {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
    boolean equals(Edge edge)
    {
        if (this.source == edge.source && this.destination == edge.destination ) return true;
        else if (this.source == edge.destination && this.destination == edge.source) return true;
        else return false;
    }
}




