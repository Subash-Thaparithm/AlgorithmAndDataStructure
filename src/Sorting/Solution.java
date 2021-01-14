package Sorting;
import java.util.*;
import java.util.stream.Collectors;
public class Solution {
    public String alienOrder(String[] words) {
        List<String> charOrder = new ArrayList<>();
        Set<Character> totalchars = new HashSet<>();
        int i = 0;
        StringBuilder sb = new StringBuilder();
        Character a = words[i].charAt(0);
        totalchars.add(a);
        sb.append(a);i++;
        Character b = null;
        while(i != words.length){
            b = words[i].charAt(0);
            if(a != b){
                b = words[i].charAt(0);
                sb.append(b);
            }
            else {
                int e =0 ;
                Character d = null;
                Character c = null;
                while(e != words[i-1].length() && e != words[i].length()){
                    d = words[i-1].charAt(e);
                    c = words[i].charAt(e);
                    if(d != c){
                        charOrder.add(new StringBuilder().append(d).append(c).toString());
                        break;
                    }else  {
                        if(e+1 == words[i].length() && e+1 != words[i-1].length())
                            return ""; // Invalid test case
                        e++;
                    }
                }
            }
            i++;
            a=b;
        }
        String order = sb.toString();

        for(int k=0; k<charOrder.size();k++){
            for (int j=0;j<charOrder.get(k).length();j++){
                totalchars.add(charOrder.get(k).charAt(j));
            }
        }
        for (Character chr:order.toCharArray() ) {
            totalchars.add(chr);
        }

       // System.out.println("Order:-->"+Arrays.toString(charOrder.toArray())+" "+order);

        Topological topological = new Topological();
        topological.n = totalchars.size();
        topological.s = new Stack<>();
        topological.adj = new HashMap<Character, ArrayList<Character>>();
        topological.tsort = new ArrayList<>();
        for(Character chr:totalchars)
            topological.adj.put(chr,new ArrayList<>());
        // Insert edges, String order = sb.toString();
        // charOrder.add(order);
        for(String str:charOrder){
            topological.addEdge(str.charAt(0), str.charAt(1));
        }
        for(int j=0; j<order.length()-1;j++){
            topological.addEdge(order.charAt(j), order.charAt(j+1));
        }
        for(Character j:totalchars) {
            if (topological.visited[j] == 0)  {
                topological.dfs(j);
            }
        }
        Set<Character> remaining = new HashSet<>();
        for(String word:words){
            for (Character chr:word.toCharArray()) {
                if(! totalchars.contains(chr))
                    remaining.add(chr);
            }
        }
        // If cycle exist
        if (topological.check_cycle(  totalchars )){
            System.out.println("Cycle is Yes");
            return "";
        }
        else
            System.out.println("Cycle is No");

       // System.out.println("Answer: " + Arrays.toString(topological.tsort.toArray()));
        StringBuilder rem = new StringBuilder();
        for (Character chr:remaining) {
            rem.append(chr);
        }
        String remain = rem.toString();
        if(remain.isEmpty())
            return topological.tsort.stream().map(String::valueOf).collect(Collectors.joining());
        else return (topological.tsort.stream().map(String::valueOf).collect(Collectors.joining()) + remain);
    }
}


class Topological{
    public int t, n, m, a;
    // Stack to store the
// visited vertices in
// the Topological Sort
    public Stack<Character> s;
    // Store Topological Order
    public ArrayList<Character> tsort;
    // Adjacency list to store edges
    public Map<Character,ArrayList<Character>> adj;
    // To ensure visited vertex
    public int[] visited = new int[(int)1e5 + 1];
    // Function to perform DFS
    public void dfs(Character u)
    {
        // Set the vertex as visited
        visited[u] = 1;
        for(Character it : adj.get(u))  {
            // Visit connected vertices
            if (visited[it] == 0)
                dfs(it);
        }
        // Push into the stack on
        // complete visit of vertex
        s.push(u);
    }
    // Function to check and return
    // if a cycle exists or not
    public boolean check_cycle(  Set<Character> totalchars )  {
        // Stores the position of
        // vertex in topological order
        Map<Character, Integer> pos = new HashMap<>();
        int ind = 0;
        // Pop all elements from stack
        while (!s.isEmpty())  {
            pos.put(s.peek(), ind);
            // Push element to get
            // Topological Order
            tsort.add(s.peek());
            ind += 1;
            // Pop from the stack
            s.pop();
        }
        for(Character chr:totalchars)
        {
            for(Character it : adj.get(chr))  {
                // If parent vertex
                // does not appear first
                if(pos.containsKey(chr) && pos.containsKey(it)){
                    if (pos.get(chr) > pos.get(it))  {
                        // Cycle exists
                        return true;
                    }
                }
            }
        }
        // Return false if cycle
        // does not exist
        return false;
    }
    // Function to add edges
    // from u to v
    public void addEdge(Character u, Character v) {

        List<Character> list = adj.get(u);
        adj.get(u).add(v);
    }
}