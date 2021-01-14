package Graph;

import java.util.*;

//Directed unweighted graph, used also for Topological sorting
public class DirectedUnweightedGraph
{
    //Array of List
    private int numVertices;
    private LinkedList[] neighbourList;
    private boolean[] visited ;
    private int startIndex;
    public boolean isCycle; // Does not work correctly. Rather use Courses_DirectedGraph_Unweighted_CycleDetection class for this
    private LinkedList<Integer> topologicalOrder = new LinkedList<>();

     DirectedUnweightedGraph(int v)
    {
        this.numVertices = v;
        visited= new boolean[numVertices];
        neighbourList = new LinkedList[v];

        for(int i=0; i< getNumVertices();i++)
        {
            neighbourList[i] = new LinkedList();
        }
    }
    DirectedUnweightedGraph(int numVertices, int[][] edgeList)
    {
        this(numVertices);

        for(int i=0; i< edgeList.length;i++)
        {
            addEdge(edgeList[i][0], edgeList[i][1]);
        }
    }

    public static void main(String args[])
    {
        //Initialise the graph
        DirectedUnweightedGraph graph = new DirectedUnweightedGraph(3);

      // LinkedList list = new LinkedList(Arrays.asList(1,2));

      /* graph.graphInternal[0] = list;

       list = new LinkedList(Arrays.asList(2));
        graph.graphInternal[1] = list;

        list = new LinkedList(Arrays.asList(0,3));
        graph.graphInternal[2] = list;

        list = new LinkedList(Arrays.asList(3));
        graph.graphInternal[3] = list;*//*

        *//* graph.graphInternal[4] = list;*//*
        //list = new LinkedList(Arrays.asList(3,0,1));

       *//*  graph.addEdge(0,2);
        graph.addEdge(0,1);
        graph.addEdge(1,2);

        graph.setStartIndex(0);

        graph.DFS(graph.startIndex, graph.getVisited());*/

        // new GraphSolution().canFinish();
        int[][] edgePairs1 = {{0,1},{0,2},{1,2}};
        DirectedUnweightedGraph graphTopology = new DirectedUnweightedGraph(3,edgePairs1);
       // LinkedList<Integer> stack = graphTopology.topologicalSort();
        int[] result = graphTopology.findOrder(3,edgePairs1);
        System.out.println("Topological sorted order is: " + result);
    }

    private void addlist(int vertex, LinkedList list)
    {
        neighbourList[vertex] = list;
    }
    private void addEdge(int source, int destination)
    {
        if( neighbourList[source] == null)
            neighbourList[source] = new LinkedList();
        neighbourList[source].add(destination);
    }

    public int getNumVertices() {
        return numVertices;
    }

    public void setNumVertices(int numVertices) {
        this.numVertices = numVertices;
    }

    public LinkedList[] getNeighbourList() {
        return neighbourList;
    }

    public void setNeighbourList(LinkedList[] neighbourList) {
        this.neighbourList = neighbourList;
    }

    // A utility function to print the adjacency list
    // representation of graph
     void printGraph()
    {
        for(int v = 0; v < this.neighbourList.length; v++)
        {
            System.out.println("Adjacency list of vertex "+ v);
            System.out.print("head");
            for(Object pCrawl: neighbourList[v]){
                System.out.print(" -> "+pCrawl);
            }
            System.out.println("\n");
        }
    }

    public  void  DFS(int startVertex, boolean[] visited)
    {
        visited[startVertex] = true;                                    // To avoid cycle in graph
        System.out.print(startVertex + " ");

        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> i = neighbourList[startVertex].listIterator(); // No preorder, postorder or inorder as there are no root and leaves
        while (i.hasNext())
        {
            int n = i.next();

           // if (n ==startVertex || n == startVertex) isCycle = true;
            if (!visited[n])
                DFS(n, visited);
            else isCycle = true;
        }
    }
    public void DFS_topological(int vertex){
        if(!visited[vertex]){
            visited[vertex] = true;
            for(Object v:neighbourList[vertex]){
                DFS_topological((int)v);
            }
            this.topologicalOrder.push(vertex);
        }
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

    public LinkedList<Integer> topologicalSort(){
        for(int i=0;i<numVertices;i++) DFS_topological(i);
        return this.topologicalOrder;
    }
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Courses_DirectedGraph_Unweighted_CycleDetection cycleDetection = new Courses_DirectedGraph_Unweighted_CycleDetection(numCourses, prerequisites);
        cycleDetection.DFS(cycleDetection.getStartIndex(), cycleDetection.getVisited(),cycleDetection.getCurrentrecursionStack());
        int[] NO_FILES = {};
        if (! cycleDetection.canFinish(numCourses, prerequisites)) return NO_FILES;

        DirectedUnweightedGraph graphTopology = new DirectedUnweightedGraph(numCourses, prerequisites);
        LinkedList<Integer> stack = graphTopology.topologicalSort();
        Collections.reverse(stack);
        System.out.println("Topological sorted order is: " + stack);
        int[] order = new int[stack.size()];

        if (!graphTopology.isCycle){
            for(int i=stack.size()-1; i>=0;i--){
            order[i] = stack.get(i);
            }
        }
        return order;
     }
}

//Use node object instead of int for weighted edge
class node
{
    private int vertexOrder;
    private int weight;
}

