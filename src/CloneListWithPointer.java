public class CloneListWithPointer {

    public Node copyRandomList(Node head) {
        if (head == null) return head;

        Node current = head;
        while (current != null) { // first pass
            Node node = new Node(current.val);
            node.next = current.next;
            current.next = node;
            current = node.next;
        }

        current = head;
        while (current != null) { // second pass
            if (current.random != null)
                current.next.random = current.random.next;
            current = current.next.next;
        }

        current = head;
        Node dummy = current.next;
        Node dummyHead = dummy;

        while (current != null && dummy != null) { // third pass
            current.next = dummy.next;
            if (current.next != null)
                dummy.next = current.next.next;
            current = current.next;
            dummy = dummy.next;
        }
        return dummyHead;
    }

    public static void main(String[] args) {

    }
}

// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}