

public class InsertionSortList
{

    public ListNode insertionSortList(ListNode head)
    {
        ListNode sortedList=null ;           // Empty list to insert nodes in sorted order
        ListNode remaining;                   // Pop up node from list

        while (head != null)
        {
            remaining = head.next;           //cu
            head.next = null;              // list contains only head node

            sortedList = insert( sortedList,  head);
            head = remaining;
        }
        return sortedList;
    }


    private ListNode insert(ListNode sortedList, ListNode node)
    {
        if (sortedList == null || sortedList.val >= node.val)
        {
            node.next = sortedList;
            sortedList = node;
        }
        else
        {
            ListNode current = sortedList;
            /* Locate the node before the point of insertion */
            while (current.next != null && current.next.val < node.val)
            {
                current = current.next;
            }
            node.next = current.next;
            current.next = node;
        }

        return sortedList;
    }

    public  static void main(String[] args)
    {
        ListNode list = new ListNode(2);
        list.push(list, 5);
        list.push(list,20);
        list.push(list,4);
        list.push(list,3);
        list.push(list,30);
        System.out.println("Linked List before Sorting..");
        new InsertionSortList().insertionSortList(list);
        System.out.println("\nLinkedList After sorting");

    }

}

class ListNode
{
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public void push(ListNode head, int val)
    {

        ListNode newnode = new ListNode(val);
        while(head.next != null)
        {
            head = head.next;
        }
        head.next = newnode;


    }
}