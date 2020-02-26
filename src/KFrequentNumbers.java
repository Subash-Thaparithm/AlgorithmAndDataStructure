import java.util.*;

public class KFrequentNumbers extends HeapImplementationArray<Map.Entry<Integer,Integer>>
{
    public List<Integer> topKFrequent(int[] nums, int k)
    {

        Map<Integer,Integer> num2Freq = new HashMap<>();
        for (int num:nums)
        {
            Integer existing = num2Freq.putIfAbsent(num,1);
            if(existing != null) num2Freq.put(num,num2Freq.get(num)+1);
        }

        KFrequentNumbers maxheap = new KFrequentNumbers();

        for (Map.Entry<Integer,Integer> node:num2Freq.entrySet())
        {
            maxheap.add(node);
        }

        maxheap.setLastindex(maxheap.elements.size()-1);
        // Heap Sort

        while (maxheap.getLastindex() >= 0)
        {
            maxheap.removeRoot();
        }
        List<Integer> output = new ArrayList<>();
        for (int i = maxheap.elements.size()-1; i>=maxheap.elements.size()-k;i--)
        {
            output.add(maxheap.elements.get(i).getKey());
        }

        return output;
    }
    @Override
    public int compareTo(Map.Entry<Integer,Integer> a, Map.Entry<Integer,Integer> b)
    {
        if ( a.getValue() >=  b.getValue())
            return 0;
        else return -1;
    }

    public static void main(String args[])
    {
        int[] nums = new int[]{1,1,1,1,1,2,2,2,2,4,4,5,5,5};
        int k=3;

        Map<Integer,Integer> num2Freq = new HashMap<>();
        for (int num:nums)
        {
            Integer existing = num2Freq.putIfAbsent(num,1);
            if(existing != null) num2Freq.put(num,num2Freq.get(num)+1);
        }

        Comparator<Map.Entry<Integer,Integer>> mycomparator = new Comparator<Map.Entry<Integer, Integer>>()
        {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2)
            {
                if ( o1.getValue() <  o2.getValue())
                    return 1;
                else if(o1.getValue() >  o2.getValue())
                    return -1;
                else return 0;
            }
        };
        PriorityQueue<Map.Entry<Integer,Integer>> numtoFreqQueue = new PriorityQueue<>( mycomparator);

        for (Map.Entry<Integer,Integer> node:num2Freq.entrySet())
        {
            numtoFreqQueue.add(node);
        }
        List<Integer> output = new ArrayList<>();

        while (k>=1)
        {
            output.add(numtoFreqQueue.peek().getKey());
            numtoFreqQueue.remove();
            k=k-1;
        }

        System.out.println(output);
    }
}