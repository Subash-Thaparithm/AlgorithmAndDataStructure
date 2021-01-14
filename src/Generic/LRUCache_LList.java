package Generic;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUCache_LList { // Not efficient in worst case

    private int capacity;
    LinkedList<Integer> list = new LinkedList<>();
    private Map<Integer, Integer> cache = new HashMap<Integer, Integer>(); // cache containing list of key and index of linked list containing value

    public LRUCache_LList(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        /*if( !cache.containsKey(key)) { System.out.print(-1 +" ,"); return -1;}
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
        System.out.print(cache.get(key).getKey() +" ,"); return cache.get(key).getKey();*/
        return 0;
    }
    public void put(int newkey, int newValue) {//index 0-->highest rank, index length-1-->lowest rank
        int LRUAgeBit = Integer.MAX_VALUE;
        int LRUkey = -1;
            if (cache.size() < capacity) {
                if (cache.containsKey(newkey)){
                    list.add(cache.get(newkey),newValue);


                     //decrease age bit of more recently used age bits than current by one
                    // add key and value, increase its rank, decrease others
                    // decrease the age bit of those which has age bit higher than current age bit of existing
                }else {
                    list.add(newkey,newValue);
                    //decrease age bit of more recently used age bits than current by one
                    // add key and value, increase its rank, decrease others
                    // decrease the age bit of those which has age bit higher than current age bit of existing
                }
        } else {
                if (cache.containsKey(newkey)){
                    list.add(cache.get(newkey),newValue);
                    //decrease age bit of more recently used age bits than current by one
                    // add key and value, increase its rank, decrease others
                    // decrease the age bit of those which has age bit higher than current age bit of existing
                }else {
                    //delete old key, add new
                    list.add(newkey,newValue);
                    //decrease age bit of more recently used age bits than current by one
                    // add key and value, increase its rank, decrease others
                    // decrease the age bit of those which has age bit higher than current age bit of existing
                }

            }
        }
    public static void main(String args[]){
        LRUCache cache = new LRUCache(3);

        cache.put(1,1);
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
        cache.get(5);
    }
}




