public class SortLinkedList {
    ListNode head = null;

    // ListNode a, b;
    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    ListNode sortedMerge(ListNode a, ListNode b) {
        ListNode result = null;
		/* Base cases */
        if (a == null)
            return b;
        if (b == null)
            return a;

		/* Pick either a or b, and recur */
        if (a.val <= b.val) {
            result = a;
            result.next = sortedMerge(a.next, b);
        } else {
            result = b;
            result.next = sortedMerge(a, b.next);
        }
        return result;
    }

    ListNode mergeSort(ListNode h) {
        // Base case : if head is null
        if (h == null || h.next == null) {
            return h;
        }

        // get the middle of the list
        ListNode middle = getMiddle(h);
        ListNode nextofmiddle = middle.next;

        // set the next of middle ListNode to null
        middle.next = null;

        // Apply mergeSort on left list
        ListNode left = mergeSort(h);

        // Apply mergeSort on right list
        ListNode right = mergeSort(nextofmiddle);

        // Merge the left and right lists
        ListNode sortedlist = sortedMerge(left, right);
        return sortedlist;
    }

    // Utility function to get the middle of the linked list
    ListNode getMiddle(ListNode h) {
        // Base case
        if (h == null)
            return h;
        ListNode fastptr = h.next;
        ListNode slowptr = h;

        // Move fastptr by two and slow ptr by one
        // Finally slowptr will point to middle ListNode
        while (fastptr != null) {
            fastptr = fastptr.next;
            if (fastptr != null) {
                slowptr = slowptr.next;
                fastptr = fastptr.next;
            }
        }
        return slowptr;
    }

    // Utility function to print the linked list
    void printList(ListNode headref) {
        while (headref != null) {
            System.out.print(headref.val + " ");
            headref = headref.next;
        }
    }

    public static void main(String[] args)
    {


    }
}