package Disjointsets;
import java.util.*;

/**
 * @author Subash thapa
 * Video link - https://youtu.be/ID00PMy0-vE
 * Disjoint sets using path compression and union by rank
 * Supports 3 operations
 * 1) makeSet
 * 2) union
 * 3) findSet
 *
 * For m operations and total n elements time complexity is O(m*f(n)) where f(n) is
 * very slowly growing function. For most cases f(n) <= 4 so effectively
 * total time will be O(m). Proof in Coreman book.
 */
public class DisjointSet<T> {
    /**
     * Here a connected component contains 2 members of same couple. The desired final state is that all are with each other who belongs to same couple.
     * Final desired state will have N connected components. i.e N couples with each other.
     */
    private int countCoupleTogether;
    private int islands = 0;
    private Node root;

    Map<Integer, Integer> f = new HashMap<>();
    public DisjointSet(int countVertices){
        for (int i = 0; i < countVertices; i++) {
            makeSet((T)Integer.valueOf(i));
        }
        countCoupleTogether = countVertices;
    }
    public DisjointSet(){
    }
    private Map<T, Node> map = new HashMap<>();  // Store the nodes in a hashmap. Data value of nodes are unique.

    /**
     * Create a set with only one element.
     */
    public void makeSet(T data) {
        Node node = new Node();
        node.data = data;
        node.parent = node;
        node.rank = 0;
        map.put(data, node);
    }
    public void makeSet_NameEmail(T data, T name) {
        Node node = new Node();
        node.data = data;
        node.name = name;
        node.parent = node;
        node.rank = 0;
        map.put(data, node);
    }
    /**
     * Combines two sets together to one.
     * Does union by rank
     *
     * @return true if data1 and data2 are in different set before union else false.
     */
    public boolean union(T data1, T data2) { // Edge from data ----> data2
        Node node1 = map.get(data1);
        Node node2 = map.get(data2);

        Node parent1 = findSet(node1);
        Node parent2 = findSet(node2);

        //if they are part of same set do nothing
        if (parent1.data == parent2.data) {
            return false;
        }
        //else whoever's rank is higher becomes parent of other
        if (parent1.rank >= parent2.rank) {
            //increment rank only if both sets have same rank
            parent1.rank = (parent1.rank == parent2.rank) ? parent1.rank + 1 : parent1.rank;
            parent2.parent = parent1;
        } else {
            parent1.parent = parent2;
        }
        countCoupleTogether--;
        return true;
    }

    /**
     * Finds the representative of this set
     */
    public int findSet(int data) {
        return (int)findSet(map.get(data)).data;
    }
    /**
     * Find the representative recursively and does path
     * compression as well.
     */
    private Node findSet(Node node) {
        Node parent = node.parent;
        if (parent == node) {
            return parent;
        }
        node.parent = findSet(node.parent);
        return node.parent;
    }
    /**
     * Find the redundant connection
     */
    public int[] findRedundantConnection(int[][] edges) {
        for (int[] edge : edges) {
            if (! map.containsKey(edge[0])) makeSet((T)Integer.valueOf(edge[0]));
            if (! map.containsKey(edge[1])) makeSet((T)Integer.valueOf(edge[1]));
            if (!union((T)Integer.valueOf(edge[0]) , (T)Integer.valueOf(edge[1]))) return edge;
        }
        return null;
    }

    public int[] findRedundantDirectedConnection(int[][] edges) {
        int edgeCount = edges.length;
        int[] parent = new int[edgeCount+1], parentArray = new int[edgeCount+1];
        Arrays.fill(parent, -1);
        int firstDuplicateParentNode = -1, secondDuplicateParentNode = -1, cycleFormingEdge = -1;
        boolean cycleFound = false;
        boolean isOneEdgeremoved = false;
        for(int i = 0; i < edgeCount; i++) {
            int source = edges[i][0], target = edges[i][1];
            if (parent[target] != -1) {
                firstDuplicateParentNode = parent[target];
                secondDuplicateParentNode = i; isOneEdgeremoved = true;
                continue;
            }
            parent[target] = i;

            int p1 = find(parentArray, source);
            if (p1 == target) {
                cycleFound = true;
                cycleFormingEdge = i;
            }
            else parentArray[target] = p1;
        }

        if (! cycleFound) return edges[secondDuplicateParentNode]; // no cycle found by removing second
        if (! isOneEdgeremoved) return edges[cycleFormingEdge]; // no edge removed
        return edges[firstDuplicateParentNode];
    }

    private int find(int[] ds, int i) {
        return ds[i] == 0 ? i : (ds[i] = find(ds, ds[i]));
    }


    /**
     * Count minimum swap of couples
     */
    public int countConnectedComponents(int[] row) {
        int N = row.length/ 2;
        for (int i = 0; i < N; i++) {
            int a = row[2*i];
            int b = row[2*i + 1];
            union((T)Integer.valueOf(a/2), (T)Integer.valueOf(b/2));
        }
        return countCoupleTogether;
    }
    public int minSwapsCouples(int[] row) {
          return row.length/2 -countConnectedComponents(row);
    }
    /**
     * Remove stones
     */
    public int removeStones(int[][] stones) {
        for (int i = 0; i < stones.length; ++i)
            union_Cordinates(stones[i][0], ~stones[i][1]);
        return stones.length - islands;
    }
    public void union_Cordinates(int x, int y) {
        x = find_Coordinates(x);
        y = find_Coordinates(y);
        if (x != y) {
            f.put(x, y);
            islands--;
        }
    }
    public int find_Coordinates(int x) {
        if (f.putIfAbsent(x, x) == null)  // If the key was not already added in the map.
            islands++;
        if (x != f.get(x))
            f.put(x, find_Coordinates(f.get(x)));
        return f.get(x);
    }
    /**
     * Accounts merge
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        for (int i=0 ; i< accounts.size();i++ ) {
            List<String> account = accounts.get(i);
            for (int j = 1; j < account.size(); j=j+2) {
                if (!map.containsKey(account.get(j))) makeSet_NameEmail((T)account.get(j), (T)account.get(0));
                if ((account.size()>j+1) && !map.containsKey(account.get(j+1))) makeSet_NameEmail((T)account.get(j+1), (T)account.get(0));
                if (j!=1) {
                    union((T)account.get(j), (T)map.get(account.get(j-1)).data);
                   if (account.size()>j+1)  union((T)account.get(j+1), (T)account.get(j));
                }
                else if((account.size()>j+1)) union((T)account.get(j), (T)account.get(j+1));
            }
        }
        Map<String, List<String>> mapParentToList = new HashMap<String,List<String>>();
        List<List<String>> mergedAccounts = new ArrayList<>();
        for (Map.Entry<T, Node> email:map.entrySet()) {
            Node parent = findSet(email.getValue());
            if (mapParentToList.containsKey(parent.data)) mapParentToList.get(parent.data).add(email.getKey().toString());
            else mapParentToList.put(parent.data.toString(), new ArrayList<String>(){{
               // add(email.getValue().name.toString());
                add(email.getKey().toString());
            }});
        }
        for (List<String> list: mapParentToList.values()) {
             Collections.sort(list);
             list.add(0, map.get(list.get(0)).name.toString());
             mergedAccounts.add(list);
        }
        return mergedAccounts;
    }
    /**
     * Main driver method
     */
    public static void main(String args[]) {
        DisjointSet ds = new DisjointSet();
        List<String> account1 = new ArrayList<String>(){{
                   add("Fern");
                   add("Fern5@m.co");
               }};
        List<String> account2= new ArrayList<String>(){{
            add("Hanzo");
            add("Hanzo3@m.co");
            add("Hanzo1@m.co");
        }};

        List<String> account3 = new ArrayList<String>(){{
            add("Ethan");
            add("Ethan5@m.co");
        }};
        List<String> account4 = new ArrayList<String>(){{
            add("Kevin");
            add("Kevin3@m.co");
            add("Kevin5@m.co");
        }};
        List<String> account5 = new ArrayList<String>(){{
            add("Gabe");
            add("Gabe0@m.co");
            add("Gabe3@m.co");
            add("Gabe100@m.co");
        }};
        /* [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],
        ["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],
        ["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],
        ["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],
        ["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]*/
        /* List<List<String>> accounts = new ArrayList<>();
        accounts.add(account1); accounts.add(account2); accounts.add(account3);accounts.add(account4);accounts.add(account5);
        ds.accountsMerge(accounts);*/
       /*  int [] edge = ds.findRedundantConnection_DirectedGraph(new int[][] {{2,1},{3,1},{4,2},{1,4}}); //ds.findRedundantConnection(new int[][]{{1,2},{1,3},{2,3}});
         edge = ds.findRedundantConnection_DirectedGraph(new int[][] {{1,2}, {2,3}, {3,4}, {4,1}, {1,5}});
         System.out.println(edge);*/
         //Count number of swaps to make couple seated together
         /*int [] row = new int[]{0, 2, 1, 3};
        ds = new DisjointSet(row.length/2);
        int components = ds.countConnectedComponents(row);
        int numofSwaps = row.length/2 -components ;*/
    }
}
 class Node<T> {
    T data;       // data represents a node
    Node parent;
    int rank;
    T name;
}