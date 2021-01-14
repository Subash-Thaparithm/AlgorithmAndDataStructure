package Generic;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class LRUCache_WithoutLinkedList { // Not efficient in worst case

    private int capacity;
    private Map<Integer, AbstractMap.SimpleEntry<Integer, Integer>> cache= new HashMap<Integer, AbstractMap.SimpleEntry<Integer, Integer>>(); // cache containing list of pair and its age bit

    private Map<Integer, AbstractMap.SimpleEntry<Integer, Integer>> cacheDList = new HashMap<Integer, AbstractMap.SimpleEntry<Integer, Integer>>(); // cache containing list of pair and its age bit

    public LRUCache_WithoutLinkedList(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if( !cache.containsKey(key)) { System.out.print(-1 +" ,"); return -1;}
        //Find MRU
        int MRU = Integer.MIN_VALUE;
        for (Map.Entry<Integer, AbstractMap.SimpleEntry<Integer, Integer>> node : cache.entrySet()) {
            int ageBit = node.getValue().getValue();
            if (ageBit > MRU) {
                MRU = ageBit;
            }
        }
        //decrease age bit of more recently used age bits than current by one
        for (Map.Entry<Integer, AbstractMap.SimpleEntry<Integer, Integer>> node : cache.entrySet()) {
            if (node.getValue().getValue() > cache.get(key).getValue()) {
                node.getValue().setValue(node.getValue().getValue() - 1);
            }
        }
        cache.get(key).setValue(MRU);
         System.out.print(cache.get(key).getKey() +" ,"); return cache.get(key).getKey();
    }

    public void put(int key, int value) {
        int LRUAgeBit = Integer.MAX_VALUE;
        int LRUkey = -1;

        for (Map.Entry<Integer, AbstractMap.SimpleEntry<Integer, Integer>> node : cache.entrySet()) {
            int ageBit = node.getValue().getValue();
            if (ageBit < LRUAgeBit) {
                LRUAgeBit = ageBit;
                LRUkey = node.getKey();
            }
        }
        if (cache.size() < capacity) {
            if (cache.containsKey(key)){ // decrease the age bit of those which has age bit higher than current age bit of existing
                AbstractMap.SimpleEntry<Integer, Integer> newPair = new AbstractMap.SimpleEntry<Integer, Integer>(value, cache.size());
                //decrease age bit of more recently used age bits than current by one
                for (Map.Entry<Integer, AbstractMap.SimpleEntry<Integer, Integer>> node : cache.entrySet()) {
                    if ( node.getValue().getValue() > cache.get(key).getValue())  {
                        node.getValue().setValue(node.getValue().getValue() - 1);
                    }
                }
                cache.put(key, newPair);
            }else {
                AbstractMap.SimpleEntry<Integer, Integer> newPair = new AbstractMap.SimpleEntry<Integer, Integer>(value, cache.size() + 1);
                cache.put(key, newPair);
            }
        } else {
            if (cache.containsKey(key)) {
                AbstractMap.SimpleEntry<Integer, Integer> newPair = new AbstractMap.SimpleEntry<Integer, Integer>(value, cache.size());
                //decrease age bit of more recently used age bits than current by one
                for (Map.Entry<Integer, AbstractMap.SimpleEntry<Integer, Integer>> node : cache.entrySet()) {
                    if (node.getValue().getValue() > cache.get(key).getValue()) {
                        node.getValue().setValue(node.getValue().getValue() - 1);
                    }
                }
                cache.put(key, newPair);
            }
            else {
                //delete LRU
                for (Map.Entry<Integer, AbstractMap.SimpleEntry<Integer, Integer>> node : cache.entrySet()) {
                    int ageBit = node.getValue().getValue();
                    if (ageBit < LRUAgeBit) {
                        LRUAgeBit = ageBit;
                        LRUkey = node.getKey();
                    }
                }
                cache.remove(LRUkey);
                //update age bit
                for (Map.Entry<Integer, AbstractMap.SimpleEntry<Integer, Integer>> node : cache.entrySet()) {
                    node.getValue().setValue(node.getValue().getValue() - 1);
                }
                //put new entry
                AbstractMap.SimpleEntry<Integer, Integer> newPair = new AbstractMap.SimpleEntry<Integer, Integer>(value, cache.size() + 1);
                cache.put(key, newPair);
            }
        }
        System.out.print("null, ");
    }

    public static void main(String args[]){


        /*cache.put(1,1);
        cache.put(2,2);
        cache.put(3,3);
        cache.put(4,4);

        cache.get(4);
        cache.get(3);
        cache.get(2);
        cache.get(1);

        cache.put(5,5);

        cache.get(1);
        cache.get(2);
        cache.get(3);
        cache.get(4);
        cache.get(5);*/


    }
}

class EfficientLRUCache{

}



