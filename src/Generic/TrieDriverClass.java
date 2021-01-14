package Generic;

import java.util.ArrayList;
import java.util.List;

public class TrieDriverClass {
    public static void main(String args[]){
        new TrieDriverClass().trieActions();
    }
    public void trieActions(){
  /*      ["Trie","insert","search","search","search","startsWith","startsWith","startsWith"]
[[],["hello"],["hell"],["helloa"],["hello"],["hell"],["helloa"],["hello"]]*/

        String[] wordList = {"hello"};

        Trie trie = new Trie();
        trie.insert(wordList[0]);

        boolean doesExists =  trie.search("hell");
        doesExists =  trie.startsWith("hell");
        doesExists =  trie.startsWith("helloa");
        doesExists =  trie.startsWith("hello");

        doesExists =  trie.startsWith("apple");
        doesExists =  trie.startsWith("ap");
        System.out.println(trie.root);
    }
    class Trie {
        TrieNode root;

        /** Initialize your data structure here. */
        public Trie() {
            this.root = new TrieNode();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            insert(word, root);
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            return  search(word, root);
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            return startsWith(prefix, root);
        }
        public boolean insert(String word, TrieNode node){
            if (word.length() ==0) return true;
            char[] characters = word.toCharArray();
            if (node.children != null) {
                for (TrieNode child : node.children) {
                    if (Character.toLowerCase(child.value) == Character.toLowerCase(characters[0])) {
                        if (word.length() ==1) child.isWordEnd = true;
                        return insert(word.substring(1), child);
                    }
                }
            }
            for (int i=0;i<characters.length;i++) {
                TrieNode newNode = new TrieNode(characters[i]);

                if(node.children == null) node.children = new ArrayList<TrieNode>();
                node.children.add(newNode);
                node = newNode;
                if(i==characters.length-1) node.isWordEnd = true;
            }
            return true;
        }
        public boolean insert_noRecursion(String word, TrieNode node){
            if (word.length() ==0) return true;
            char[] characters = word.toCharArray();

            if (node.children != null) {
                for (TrieNode child : node.children) {
                    if (Character.toLowerCase(child.value) == Character.toLowerCase(characters[0])) {
                        if (word.length() ==1) child.isWordEnd = true;
                        return insert(word.substring(1), child);
                    }
                }
            }
            for (int i=0;i<characters.length;i++) {

                TrieNode newNode = new TrieNode(characters[i]);

                if(node.children == null) node.children = new ArrayList<TrieNode>();
                node.children.add(newNode);
                node = newNode;
                if(i==characters.length-1) node.isWordEnd = true;
            }
            return true;
        }

        /**
         * Searches for a word in the subtrees of node
         */
        public boolean search(String word, TrieNode node){
            if(node.value == null && node.children == null) return false;   // if it is an empty Trie
            if (node.children == null) return false;
            char[] characters = word.toCharArray();
            for (TrieNode child:node.children) {
                if (Character.toLowerCase(child.value) == Character.toLowerCase(characters[0])) {
                    if (word.length() ==1 && child.isWordEnd) return true;
                    else if (word.length()==1) return false;
                    return search(word.substring(1), child);
                }
            }
            return false;
        }
        public boolean startsWith(String prefix, TrieNode node){
            if(node.value == null && node.children == null) return false;
            if (node.children == null) return false;
            char[] characters = prefix.toCharArray();
            for (TrieNode child:node.children) {
                if (prefix.length() ==0) break;
                if (Character.toLowerCase(child.value) == Character.toLowerCase(characters[0])) {
                    if (prefix.length() ==1) return true;
                    return startsWith(prefix.substring(1), child);
                }
            }
            return false;
        }
    }
    class TrieNode{
        boolean isWordEnd;
        Character value;
        List<TrieNode> children;
        public TrieNode(Character value){
            this.value = value;
        }
        public TrieNode(){
            this.value = null;
        }
    }
}
