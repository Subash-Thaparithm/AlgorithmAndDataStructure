package Generic;

import java.util.*;

public class LinkedListActions {
    public SinglyListNode insertionSortList(SinglyListNode head) {
        SinglyListNode sortedList = null;           // Empty list to insert nodes in sorted order
        SinglyListNode remaining;                   // Pop up node from list

        while (head != null) {
            remaining = head.next;           //cu
            head.next = null;              // list contains only head node

            sortedList = insert(sortedList, head);
            head = remaining;
        }
        return sortedList;
    }
    private SinglyListNode insert(SinglyListNode sortedList, SinglyListNode node) {
        if (sortedList == null || sortedList.val >= node.val) {
            node.next = sortedList;
            sortedList = node;
        } else {
            SinglyListNode current = sortedList;
            /* Locate the node before the point of insertion */
            while (current.next != null && current.next.val < node.val) {
                current = current.next;
            }
            node.next = current.next;
            current.next = node;
        }

        return sortedList;
    }
    public static void main(String[] args) {
        //[[1,4,5],[1,3,4],[2,6]]
        ListNode first = new ListNode(1);
        ListNode second = new ListNode(4);
        ListNode third = new ListNode(5);
        first.next = second;
        second.next = third;

        ListNode fourth = new ListNode(1);
        ListNode fifth = new ListNode(3);
        ListNode sixth = new ListNode(4);
        fourth.next = fifth;
        fifth.next = sixth;

        ListNode seventh = new ListNode(2);
        ListNode eighth = new ListNode(6);
        seventh.next = eighth;

        new LinkedList_Own().mergeKLists(new ListNode[]{fourth, first,seventh});


        SinglyListNode list = new SinglyListNode(2);
        list.push(list, 5);
        list.push(list, 20);
        list.push(list, 4);
        list.push(list, 3);
        list.push(list, 30);
        System.out.println("Linked List before Sorting..");

        System.out.println("\nLinkedList After sorting");
      // your code goes here
        /*LRUCache lrucache = new LRUCache(2);

        lrucache.put(1, 1);
        lrucache.put(2, 2);

        System.out.println(lrucache.get(1));

        lrucache.put(3, 3);

        System.out.println(lrucache.get(2));

        lrucache.put(4, 4);

        System.out.println(lrucache.get(1));
        System.out.println(lrucache.get(3));
        System.out.println(lrucache.get(4));*/
    }
    // Floyd cycle detection algorithm , Hare and Tortroise algorithm
    public boolean detectCycle(SinglyListNode node) {
        SinglyListNode slow = node;
        SinglyListNode fast = node;

        while (slow != null || fast != null) {
            if (slow.next != null) slow = slow.next;
            else return false;

            if (fast.next != null && fast.next.next != null) fast = fast.next.next;
            else return false;

            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
    // Floyd cycle detection algorithm , Hare and Tortroise algorithm
    public SinglyListNode detectCycleHead2(SinglyListNode head) {
        SinglyListNode slow = head;
        SinglyListNode fast = head;

        while (slow != null || fast != null) {
            if (slow.next != null) slow = slow.next;
            else return null;

            if (fast.next != null && fast.next.next != null) fast = fast.next.next;
            else return null;

            if (slow == fast) { // Repeat one more loop, starting slow at head, fast at curr, one step at a time this time, meeting point is the answer
                slow = head;
                while (true) {
                    slow = slow.next;
                    fast = fast.next;
                    if (slow == fast) return slow;
                }
            }
        }
        return null;
    }

    public SinglyListNode detectCycleHead(SinglyListNode node) {
        Set<SinglyListNode> traversedNodes = new HashSet<SinglyListNode>();
        SinglyListNode curr = node;

        while (curr != null) {
            if (curr.next != null) {
                curr = curr.next;
            } else return null;
            if (traversedNodes.contains(curr)) return curr;
            else traversedNodes.add(curr);
        }
        return null;
    }

    public SinglyListNode findMiddleElement(SinglyListNode head) {
        SinglyListNode fast = head;
        SinglyListNode slow = head;

        while (fast != null && fast.next != null) {
            if (slow != null) slow = slow.next;
            if (fast.next != null) fast = fast.next.next;
        }
        return slow;
    }

}

class SinglyListNode {
    int val;
    SinglyListNode next;

    public SinglyListNode(int val) {
        this.val = val;
    }

    public void push(SinglyListNode head, int val) {

        SinglyListNode newnode = new SinglyListNode(val);
        while (head.next != null) {
            head = head.next;
        }
        head.next = newnode;


    }
}

class DoublyLinkedList {
   int value;
   int key;
   DoublyLinkedList left;
   DoublyLinkedList right;
}

class LRUCache {
    HashMap<Integer, DoublyLinkedList> hashmap;
    DoublyLinkedList start, end;// Start has highest rank, end the lowest
    int LRU_SIZE;

    public LRUCache(int capacity) {
        hashmap = new HashMap<Integer, DoublyLinkedList>();
        LRU_SIZE = capacity;
    }

    public int get(int key) {
        if (hashmap.containsKey(key)) // Key Already Exist, just update the
        {
            DoublyLinkedList entry = hashmap.get(key);
            removeNode(entry);
            addAtStart(entry);
            return entry.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (hashmap.containsKey(key)) // Key Already Exist, just update the value and move it to top
        {
            DoublyLinkedList entry = hashmap.get(key);
            entry.value = value;
            removeNode(entry);
            addAtStart(entry);
        } else {
            DoublyLinkedList newnode = new DoublyLinkedList();
            newnode.left = null;
            newnode.right = null;
            newnode.value = value;
            newnode.key = key;
            if (hashmap.size() >= LRU_SIZE) // We have reached maxium size so need to make room for new element.
            {
                hashmap.remove(end.key);
                removeNode(end);
                addAtStart(newnode);

            } else {
                addAtStart(newnode);
            }

            hashmap.put(key, newnode);
        }
    }

    private void addAtStart(DoublyLinkedList node) {
        node.right = start;
        node.left = null;
        if (start != null)
            start.left = node;
        start = node;
        if (end == null)
            end = start;
    }

    private void removeNode(DoublyLinkedList node) {

        if (node.left != null) {
            node.left.right = node.right;
        } else {
            start = node.right;
        }

        if (node.right != null) {
            node.right.left = node.left;
        } else {
            end = node.left;
        }
    }
}

// A complete working Java program to delete a node in a linked list
// at a given position
class LinkedList_Own   implements Comparable<LinkedList_Own>
{
    ListNode head; // head of list

    public LinkedList_Own(ListNode node){
        this.head = node;
    }
    public LinkedList_Own(){
    }
    /* Inserts a new Node at front of the list. */
    public void pushatFront(int new_data)
    {
		/* 1 & 2: Allocate the Node &
				Put in the data*/
        ListNode new_node = new ListNode(new_data);

        /* 3. Make next of new Node as head */
        new_node.next = head;

        /* 4. Move the head to point to new Node */
        head = new_node;
    }

    /* Inserts a new Node at back of the list. */
    public void pushatBack(int new_data)
    {
		/* 1 & 2: Allocate the Node &
				Put in the data*/
        ListNode new_node = new ListNode(new_data);
        ListNode temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new ListNode(new_data);
    }

    /* Given a reference (pointer to pointer) to the head of a list
    and a position, deletes the node at the given position */
    void deleteNode(int position)
    {
        // If linked list is empty
        if (head == null)
            return;

        // Store head node
        ListNode temp = head;

        // If head needs to be removed
        if (position == 0)
        {
            head = temp.next; // Change head
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
        ListNode next = temp.next.next;

        temp.next = next; // Unlink the deleted node from list
    }

    /* This function prints contents of linked list starting from
        the given node */
    public void printList()
    {
        ListNode tnode = head;
        while (tnode != null)
        {
            System.out.print(tnode.data+" ");
            tnode = tnode.next;
        }
    }

    public ListNode mergeKLists(ListNode[] nodes) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int count = 0;
        for (ListNode node:nodes) {
            while(node != null) {
                heap.add(node.data);
                count++;
                node = node.next;
            }
        }
        // priority queue allows us to retrieve least pile efficiently
        if (heap.isEmpty()) return null;
        ListNode output = new ListNode(heap.poll());
        for (int c = 0; c < count; c++)
        {
            ListNode smallPile = new ListNode(heap.poll());
            output.next = smallPile;
            output = output.next;
        }
        return output;
    }
    public int compareTo(LinkedList_Own y){
             if (head ==null ) return -1;
             if ( y==null || y.head==null) return 1;
            return Integer.compare(head.data, y.head.data);
    }
    /* Drier program to test above functions. Ideally this function
    should be in a separate user class. It is kept here to keep
    code compact */
    /*public static void main(String[] args)
    {
        *//* Start with the empty list *//*
        LinkedList_Own llist = new LinkedList_Own();

        llist.push(7);
        llist.push(1);
        llist.push(3);
        llist.push(2);
        llist.push(8);

        System.out.println("\nCreated Linked list is: ");
        llist.printList();

        llist.deleteNode(4); // Delete node at position 4

        System.out.println("\nLinked List after Deletion at position 4: ");
        llist.printList();
    }*/
}
/* Linked list Node*/
class ListNode
{
    int data;
    ListNode next;
    ListNode(int d)
    {
        data = d;
        next = null;
    }
}