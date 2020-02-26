import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

//A Full binary tree(a must for Heap) can be implemented using an array. Since we do not want to reallocate when adding elements, we can use ArrayList.
public class HeapImplementationArray<E>
{

    List<E> elements = new ArrayList<E>();//Max heap based
    private int lastindex = elements.size()-1; //Separate heap and sorted part of the list

    public void add(E element) // add to a max heap. should be max heap after adding
    {
        elements.add(element);

        int childindex = elements.size() - 1;
        int parentindex = getparentindex(childindex);

        while (childindex != 0 && compareTo(elements.get(parentindex), elements.get(childindex)) != 0)
        {
            swap(parentindex, childindex);

            childindex = parentindex;
            parentindex = getparentindex(childindex);
        }

    }
    public void removeRoot() // remove max element  from a max heap. should be max heap after removing
    {
        swap(0, lastindex);
        // elements.remove(elements.size() - 1);
        setLastindex(lastindex-1);

        int parentindex = 0;

        while (!isLeafNode(parentindex))
        {
            int rightchildindex = getrightchildindex(parentindex);
            int leftchildindex = getleftchildindex(parentindex);

            if (leftchildindex != Integer.MAX_VALUE && rightchildindex != Integer.MAX_VALUE)
            {
                int largerChild;
                if (compareTo(elements.get(leftchildindex), elements.get(rightchildindex)) == 0)
                    largerChild = leftchildindex;
                else largerChild = rightchildindex;

                if (compareTo(elements.get(parentindex), elements.get(largerChild)) != 0) {
                    swap(parentindex, largerChild);
                    parentindex = largerChild;
                } else break;

            }
            else if (leftchildindex != Integer.MAX_VALUE)
            {
                if (compareTo(elements.get(parentindex), elements.get(leftchildindex)) != 0) {
                    swap(parentindex, leftchildindex);
                    parentindex = leftchildindex;
                } else break;
            }
            int a=2;

        }
    }
    public int getleftchildindex(int parentindex)
    {

        if (2 * parentindex + 1 <= lastindex) return   2 * parentindex + 1 ;
        else return Integer.MAX_VALUE;
    }
    public int getrightchildindex(int parentindex)
    {
        if (2 * parentindex + 2 <= lastindex) return  2 * parentindex + 2;
        else return Integer.MAX_VALUE;
    }
    public int getparentindex(int childindex) {
        return (childindex - 1) / 2;
    }
    public boolean isLeafNode(int a)
    {
        if (2 * a + 1 <= lastindex) return false; //it is a leaf node if it does not have a left child.
        else return true;
    }
    public void swap(int a, int b) {
        E temp = elements.get(b);
        elements.set(b, elements.get(a));
        elements.set(a, temp);
    }
    public int compareTo(E a, E b)
    {
        if ((a.getClass() == Integer.class) && (a.getClass() == Integer.class) && ((Integer) a >= (Integer) b))
            return 0;
        else return -1;

    }
    public static void main(String args[])
    {
        HeapImplementationArray<Integer> maxheap = new HeapImplementationArray<Integer>();

        maxheap.add(5);
        maxheap.add(7);
        maxheap.add(3);
        maxheap.add(2);
        maxheap.add(6);
        maxheap.add(1);

        maxheap.setLastindex(maxheap.elements.size()-1);
        // Heap Sort

        while (maxheap.getLastindex() >= 0)
        {
            maxheap.removeRoot();
        }

        System.out.print(maxheap.elements);
    }
    public int getLastindex() {
        return lastindex;
    }
    public void setLastindex(int lastindex) {
        this.lastindex = lastindex;
    }
}
