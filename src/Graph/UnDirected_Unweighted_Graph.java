package Graph;
import java.util.*;

/**
 * A simple undirected and unweighted graph implementation.
 */
import java.util.*;
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return new ArrayList<List<String>>();
        UnDirected_Unweighted_Graph g = new UnDirected_Unweighted_Graph();
        if(! wordList.contains(beginWord)) wordList.add(0,beginWord);
        List<List<String>> numTransform = g.ladderLength(beginWord,endWord, wordList);
        return numTransform;
    }
}
/**
 * A simple undirected and unweighted graph implementation.
 */
public class UnDirected_Unweighted_Graph {
    final private HashMap<Integer, Set<Integer>> adjacencyList;
    /**
     * Create new Graph object.
     */
    public UnDirected_Unweighted_Graph() {
        this.adjacencyList = new HashMap<>();
    }
    /**
     * Add new edge between vertex. Adding new edge from u to v will
     * automatically add new edge from v to u since the graph is undirected.
     *
     * @param v Start vertex.
     * @param u Destination vertex.
     */
    public void addEdge(Integer v, Integer u) {
        if (!this.adjacencyList.containsKey(v)) this.adjacencyList.put(v, new HashSet<Integer>());
        if (!this.adjacencyList.containsKey(u)) this.adjacencyList.put(u, new HashSet<Integer>());
        this.adjacencyList.get(v).add(u);
        this.adjacencyList.get(u).add(v);
    }
    /* It is almost like the Dijkshtra algorithm except no use of exact positive distance and comparing.
     * */
    private void canReachOriginFrom(int fromNode, int destNode, boolean[] visited, HashMap<Integer, Integer> distance, HashMap<Integer, Set<Integer>> alternativeAdjList) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(fromNode);
        visited[fromNode] = true;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbour : adjacencyList.get(node)) {
                alternativeAdjList.get(node).add(neighbour);
                int curDistance = distance.get(node);
                if (!visited[neighbour] && neighbour != node) {
                    if (!distance.containsKey(neighbour) || curDistance + 1 < distance.get(neighbour))
                        distance.put(neighbour, curDistance + 1);
                    queue.add(neighbour);
                    visited[neighbour] = true;
                    if (destNode == neighbour) {
                        break ;
                    } else {
                        queue.offer(neighbour);
                    }
                }
            }
        }
        return ;
    }
    //https://leetcode.com/problems/word-ladder/solution/
    public List<List<String>> ladderLength(String beginWord, String endWord, List<String> wordList) {
        //Construct a graph first where words that differ by a single character are connected
        for (int i = 0; i < wordList.size(); i++) {
            for (int j = 1; j < wordList.size(); j++) {
                if (i != j && differByOneCharacter(wordList.get(i), wordList.get(j), 1)) {
                    addEdge(i, j);
                }
            }
        }
        HashMap<Integer, Set<Integer>> alternativeAdjList = new  HashMap<Integer, Set<Integer>>();
        boolean[] visited = new boolean[wordList.size()];
        List<List<String>> outputWords = new ArrayList<>();
        HashMap<Integer, Integer> distance = new HashMap<Integer, Integer>();// Distance of every node from the start node
        if (!adjacencyList.containsKey(wordList.indexOf(beginWord))) return outputWords;
        if (!adjacencyList.containsKey(wordList.indexOf(endWord))) return outputWords;
        distance.put(wordList.indexOf(beginWord), 0);

        for (String str : wordList)
            alternativeAdjList.put(wordList.indexOf(str), new HashSet<Integer>());
        canReachOriginFrom(wordList.indexOf(beginWord), wordList.indexOf(endWord), visited, distance,alternativeAdjList);
        dfs(beginWord, endWord, wordList, alternativeAdjList, distance, new ArrayList<String>(), outputWords);

        return outputWords;
    }
    private boolean differByOneCharacter(String word1, String word2, int mistakesAllowed) {
        if (word1.equals(word2)) // if word1 equals word2, we can always return true
            return true;
        if (word1.length() == word2.length()) { // if word1 is as long as word 2
            for (int i = 0; i < word1.length(); i++) { // go from first to last character index the words
                if (word1.charAt(i) != word2.charAt(i)) { // if this character from word 1 does not equal the character from word 2
                    mistakesAllowed--; // reduce one mistake allowed
                    if (mistakesAllowed < 0) { // and if you have more mistakes than allowed
                        return false; // return false
                    }
                }
            }
        }
        return true;
    }
    // DFS: output all paths with the shortest distance.
    private void dfs(String cur, String end, List<String> wordList, HashMap<Integer, Set<Integer>> nodeNeighbors,   HashMap<Integer, Integer> distance, List<String> solution, List<List<String>> res) {
        System.out.println("First DFS: Length of neighbour set =" + nodeNeighbors.size());
        solution.add(cur);
        if (end.equals(cur)) {
            res.add(new ArrayList<String>(solution));
        } else {
            for (Integer next : nodeNeighbors.get(wordList.indexOf(cur))) {
                if (distance.containsKey(next) && distance.containsKey(wordList.indexOf(cur)) && distance.get(next) == distance.get(wordList.indexOf(cur)) + 1) {
                    dfs(wordList.get(next), end, wordList, nodeNeighbors, distance, solution, res);
                }
            }
        }
        solution.remove(solution.size() - 1);
    }


    public static void main(String[] args) {
        UnDirected_Unweighted_Graph g = new UnDirected_Unweighted_Graph();
        System.out.println("Distance = " + g.ladderLength("kiss","tusk",new ArrayList<String>() {
            {
                add("dusk");
                add("kiss");
                add("musk");
                add("tusk");
                add("diss");
                add("disk");
                add("sang");
                add("ties");
                add("muss");
            }
        }));
    }
}
