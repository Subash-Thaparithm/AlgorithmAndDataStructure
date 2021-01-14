package Queue;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Your FirstUnique object will be instantiated and called as such:
 * FirstUnique obj = new FirstUnique(nums);
 * int param_1 = obj.showFirstUnique();
 * obj.add(value);
 */
// Queue implementation using linked list.
class FirstUnique<T> {
    LinkedList_Custom list = new LinkedList_Custom();
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();

    Integer uniqueElement = null;
    public FirstUnique(int[] nums) {
        for (int num:nums) {
            add(num);
        }
    }

    public int showFirstUnique() {
       if (list.length ==0) return -1;
       else return list.head.data;
    }

    public void add(int value) {
        if (! map.containsKey(value)) {
            list.push_atBack(value);
            map.put(value, list.length-1);
        }
        else {
            int index = map.get(value);
            list.deleteNode(map.get(value));
            //Remove from map, re-arrange index of other elements
            for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
                if (entry.getValue()>index) entry.setValue(entry.getValue()-1);
            }
             //map.remove(value);
        }
    }

    public static void main(String[] args) {
        int [] input = new int[]{392,59,691,331,805};
        FirstUnique firstUnique = new FirstUnique(input);
        int ans=firstUnique.showFirstUnique(); // return 2
        //,5,1,2,5,3,4,5,6
        firstUnique.add(392);            // the queue is now [2,3,5,5]
         ans=firstUnique.showFirstUnique(); // return 2
        firstUnique.add(331);            // the queue is now [2,3,5,5,2]
        ans=firstUnique.showFirstUnique(); // return 3
        firstUnique.add(805);            // the queue is now [2,3,5,5,2,3]
        ans=firstUnique.showFirstUnique(); // return -1
        ans=firstUnique.showFirstUnique();
        ans=firstUnique.showFirstUnique();
    }
     static int[] readFromFile() {
        // TODO code application logic here

        // // read KeyWestTemp.txt

        // create token1
        String token1 = "";

        // for-each loop for calculating heat index of May - October

        // create Scanner inFile1
         Scanner inFile1 = null;
         try {
             inFile1 = new Scanner(new File("C:\\Users\\a681622\\Desktop\\Input_Leetcode.txt")).useDelimiter(",\\s*");
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         }

         // Original answer used LinkedList, but probably preferable to use ArrayList in most cases
        // List<String> temps = new LinkedList<String>();
        List<Integer> temps = new ArrayList<Integer>();

        // while loop
        while (inFile1.hasNext()) {
            // find next line
            token1 = inFile1.next();
            temps.add(Integer.parseInt(token1));
        }
        inFile1.close();
        int[] output = new int[temps.size()];
         for(int i = 0; i < temps.size(); i++) output[i] = temps.get(i);

        return  output;
    }
}
// A complete working Java program to delete a node in a linked list
// at a given position
class LinkedList_Custom
{
    Node head; // head of list
    Node end; // head of list
    int length=0;


    /* Inserts a new Node at front of the list. */
    public void push_atFront(int new_data)
    {
		/* 1 & 2: Allocate the Node &
				Put in the data*/
        Node new_node = new Node(new_data);

        /* 3. Make next of new Node as head */
        new_node.next = head;

        /* 4. Move the head to point to new Node */
        head = new_node;
        length++;
    }

    /* Inserts a new Node at front of the list. */
    public void push_atBack(int new_data)
    {
        Node temp = head;
        while (temp != null && temp.next != null)
            temp = temp.next;
			/* 1 & 2: Allocate the Node &
				Put in the data*/
        Node new_node = new Node(new_data);

        /* 3. Make next of new Node as head */
        if (length != 0)temp.next = new_node;
        else head = new_node;
        length++;
    }
    /* Given a reference (pointer to pointer) to the head of a list
    and a position, deletes the node at the given position */
    void deleteNode(int position)
    {
        // If linked list is empty
        if (head == null)
            return;

        // Store head node
        Node temp = head;

        // If head needs to be removed
        if (position == 0)
        {
            head = temp.next; // Change head
            length--;
            return;
        }

        // Find previous node of the node to be deleted
        for (int i=0; temp!=null && i<position-1; i++)
            temp = temp.next;

        // If position is more than number of ndoes
        if (temp == null || temp.next == null)
            return;

        // Node temp->next is the node to be deleted
        // Store pointer to the next of node to be deleted
        Node next = temp.next.next;

        temp.next = next; // Unlink the deleted node from list
        length--;
    }

    /* This function prints contents of linked list starting from
        the given node */

}

/* Linked list Node*/
class Node
{
    int data;
    Node next;
    Node(int d)
    {
        data = d;
        next = null;
    }
}

class FirstUnique_Simple{
    Set<Integer> unique = new LinkedHashSet<>();
    Set<Integer> all = new HashSet<>();
    public FirstUnique_Simple(int[] nums) {
        for (int num:nums
             ) {
            add(num);
        }
    }
    public void add(int value) {
        if (all.add(value)) unique.add(value);
        else unique.remove(value);

    }
    public int showFirstUnique() {
        if (unique.isEmpty()) return -1;
        else return unique.iterator().next();

    }
}
