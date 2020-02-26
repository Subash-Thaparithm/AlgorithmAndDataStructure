import java.util.LinkedList;

public class JavaLinkedList
{

    public  static void main(String[] args)
    {
        LinkedList<Integer> list = new LinkedList<>();

        //Linked list as a Queue
            list.add(1); //First or Head element
            list.add(2);
            list.add(3);
            list.add(4);
            list.add(5); //Last or tail
        while(list.size() != 0)
        {
            System.out.println(list.pollFirst());

        }
        //Linked list as a Stack
        list.add(1); //First
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5); //Last
        while(list.size() != 0)
        {
            System.out.println(list.pollLast());

        }

        //Linked list as a Linked list

    }
}
