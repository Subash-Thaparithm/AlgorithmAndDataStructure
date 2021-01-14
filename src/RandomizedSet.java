
import java.math.BigInteger;

public class RandomizedSet {

        int size = 0;
        MyHashMap obj = null;

        /** Initialize your data structure here. */
        public RandomizedSet() {
            obj = new MyHashMap();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            int hash = obj.getHash(BigInteger.valueOf(val));
            if (obj.containsKey(val)) return false;
            else {
                obj.put(hash,val);
                size ++;
                return true;
            }
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if (! obj.containsKey(val)) return false;
            else {
                obj.remove(val);
                size--;
                return true;
            }
        }

        /** Get a random element from the set. */
        public int getRandom() {
            int random = (int)(Math.random() * size)-1;// Random number from 0 to size-1
            return obj.get(random);
        }
    }

    class MyHashMap {
        int capacity = 100000;
        Node[] store = new Node[capacity];

        /** Initialize your data structure here. */
        public MyHashMap() {

        }

        /** value will always be non-negative. */
        public void put(int hash, int value) {
            int index = hash;
            if(store[index] == null) {
                Node node = new Node(value, value);
                store[index] = node;
            }
            else {
                //Traverse the list, update or add the key,value
                Node node =store[index];
                Node prev = null;
                while(node != null){
                    //        System.out.println("put while loop for " + key);
                    if(node.key == value) {
                        node.value =value;
                        return;
                    }else {
                        prev = node;
                        node = node.next;
                    }
                }
                Node newNode = new Node(value, value);
                prev.next = newNode;
            }
        }

        /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
        public int get(int key) {
            int index =  getHash( BigInteger.valueOf(key));

            if(store[index] == null) {
                return -1;
            }
            else {
                //Traverse the list
                Node node =store[index];
                while(node != null){
                    //       System.out.println("Get for  key = "+ key);
                    if(node.key == key) {
                        return node.value;
                    }else {
                        node = node.next;
                    }
                }
                // System.out.println("Not found value for key = "+ key);
                return -1;
            }
        }

        /** Removes the mapping of the specified value key if this map contains a mapping for the key */
        public void remove(int key) {
            int index =  getHash(BigInteger.valueOf(key));
            if(store[index] == null) {
                return ;
            }
            else {
                //Traverse the list
                Node node =store[index];
                Node prev = null;
                while(node != null){
                    // System.out.println("remove while loop for " + key);
                    if(node.key == key) {
                        //Delete this node
                        if(prev == null) {
                            store[index]=node.next;
                            return ;
                        }
                        else{
                            prev.next =node.next;
                            return;
                        }
                    }else {
                        prev = node;
                        node = node.next;
                    }
                }
                return ;
            }
        }

        public boolean containsKey(int key){
            int value = get(key);
            if(value == -1) return false;
            else return true;
        }

        public int getHash(BigInteger key){
            BigInteger index = null;
            if(key.compareTo(BigInteger.ZERO) == -1)
                index = key.multiply(BigInteger.valueOf(-1).remainder(BigInteger.valueOf(capacity)));
            else index =  key.remainder(BigInteger.valueOf(capacity)).multiply(BigInteger.valueOf(2));

            return index.intValue();
        }
    }

    class Node{
        int key=-1;
        int value=-1;
        public Node(int key,int value){
            this.key= key;
            this.value =value;
        }
        public Node next;


    }

