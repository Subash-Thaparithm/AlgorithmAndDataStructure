import java.util.*;

//Directed unweighted graph
public class Graph
{
    //Array of List
    private int numVertices;
    private LinkedList[] graphInternal;
    private boolean[] visited ;
    private int startIndex;
    private boolean isCycle;

     Graph(int v)
    {
        this.numVertices = v;
        visited= new boolean[numVertices];
        graphInternal = new LinkedList[v];

        for(int i=0; i< getNumVertices();i++)
        {
            graphInternal[i] = new LinkedList();
        }
    }

    public static void main(String args[])
    {
        //Initialise the graph
        Graph graph = new Graph(3);

      // LinkedList list = new LinkedList(Arrays.asList(1,2));


      /* graph.graphInternal[0] = list;

       list = new LinkedList(Arrays.asList(2));
        graph.graphInternal[1] = list;

        list = new LinkedList(Arrays.asList(0,3));
        graph.graphInternal[2] = list;

        list = new LinkedList(Arrays.asList(3));
        graph.graphInternal[3] = list;*/

        /* graph.graphInternal[4] = list;*/
        //list = new LinkedList(Arrays.asList(3,0,1));

      /*  graph.addEdge(0,2);
        graph.addEdge(0,1);
        graph.addEdge(1,2);

        graph.setStartIndex(0);

        graph.DFS(graph.startIndex, graph.getVisited());*/

      // new GraphSolution().canFinish();

        System.out.println("Cycle detection = " + graph.isCycle);

    }

    private void addlist(int vertex, LinkedList list)
    {
        graphInternal[vertex] = list;
    }
    private void addEdge(int source, int destination)
    {
        if( graphInternal[source] == null)
            graphInternal[source] = new LinkedList();
        graphInternal[source].add(destination);
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
        for(int v = 0; v < this.graphInternal.length; v++)
        {
            System.out.println("Adjacency list of vertex "+ v);
            System.out.print("head");
            for(Object pCrawl: graphInternal[v]){
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
        Iterator<Integer> i = graphInternal[startVertex].listIterator(); // No preorder, postorder or inorder as there are no root and leaves
        while (i.hasNext())
        {
            int n = i.next();

           // if (n ==startVertex || n == startVertex) isCycle = true;
            if (!visited[n])
                DFS(n, visited);
            else isCycle = true;
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
}

//Use node object instead of int for weighted edge
class node
{
    private int vertexOrder;
    private int weight;
}

