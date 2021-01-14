package Generic;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

public class MapTypes
{
    public static void main(String[] args)
    {
        // Creating an empty HashMap
        HashMap<Integer, String> hash_map = new HashMap<Integer, String>();

        // Mapping string values to int keys
        hash_map.put(10, "Geeks");
        hash_map.put(15, "4");
        hash_map.put(20, "Geeks");
        hash_map.put(25, "Welcomes");
        hash_map.put(30, "You");

        // Displaying the HashMap
        System.out.println("Initial Mappings in HashMap are: " + hash_map);

        // Inserting existing key along with new value
        String returned_value = (String)hash_map.put(50, "All");

        // Verifying the returned value
        System.out.println("Returned value is: " + returned_value);

        // Displayin the new map
        System.out.println("New HashMap is: " + hash_map);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Creating an empty HashMap
        LinkedHashMap<Integer, String> linked_hash_map = new LinkedHashMap<Integer, String>();

        // Mapping string values to int keys
        linked_hash_map.put(10, "Geeks");
        linked_hash_map.put(15, "4");
        linked_hash_map.put(20, "Geeks");
        linked_hash_map.put(25, "Welcomes");
        linked_hash_map.put(30, "You");

        // Displaying the HashMap
        System.out.println("Initial Mappings in LinkedHashMap are: " + linked_hash_map);

        // Inserting existing key along with new value
        String returned_value1 = (String)linked_hash_map.put(50, "All");

        // Verifying the returned value
        System.out.println("Returned value LinkedHashMap is: " + returned_value1);

        // Displayin the new map
        System.out.println("New linked_hash_map is: " + linked_hash_map);

        /////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Creating an empty HashMap
        TreeMap<Integer, String> tree_map = new TreeMap<Integer, String>();

        // Mapping string values to int keys
        tree_map.put(10, "Geeks");
        tree_map.put(15, "4");
        tree_map.put(20, "Geeks");
        tree_map.put(25, "Welcomes");
        tree_map.put(30, "You");

        // Displaying the HashMap
        System.out.println("Initial Mappings in tree_map are: " + tree_map);

        // Inserting existing key along with new value
        String returned_value2 = (String)tree_map.put(50, "All");

        // Verifying the returned value
        System.out.println("Returned value tree_map is: " + returned_value2);

        // Displayin the new map
        System.out.println("New tree_map is: " + tree_map);


    }
}
