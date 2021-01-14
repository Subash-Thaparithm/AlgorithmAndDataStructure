package Generic;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// This is the interface that allows for creating nested lists.
  // You should not implement it, or speculate about its implementation
   interface NestedInteger
  {
      /**
       *  @return true if this NestedInteger holds a single integer, rather than a nested list.
        */

      public boolean isInteger();

      /** @return the single integer that this NestedInteger holds, if it holds a single integer
      // Return null if this NestedInteger holds a nested list
      */

      public Integer getInteger();

      /** @return the nested list that this NestedInteger holds, if it holds a nested list
      // Return null if this NestedInteger holds a single integer
      */

      public List<NestedInteger> getList();
  }

public class NestedIterator implements Iterator<Integer>
{
    private Queue<Integer> queue;

    public NestedIterator(List<NestedInteger> nestedList)
    {
        this.queue = new LinkedList<>();

        for(NestedInteger nestedInteger: nestedList)
        {
            addTOQueue(nestedInteger);
        }
    }

    @Override
    public Integer next()
    {
     return queue.poll();

    }

    @Override
    public boolean hasNext()
    {
        return ! queue.isEmpty();
    }

    public void  addTOQueue(NestedInteger nestedInteger)
    {
        if(nestedInteger.isInteger()) queue.offer(nestedInteger.getInteger());
        else
            {
            for (NestedInteger nestedInteger1 : nestedInteger.getList()) {
                addTOQueue(nestedInteger);
            }
        }
    }

}

