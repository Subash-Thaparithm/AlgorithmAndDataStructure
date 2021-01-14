package Generic;

import java.util.*;

public class jewelsStones
{
    public int numJewelsInStones(int[][] points)
    {
        Map<Double, List<AbstractMap.SimpleEntry<Integer,Integer>>> slopeLine = new HashMap<>();

       for(int i=0; i<points.length;i++)
           for(int j=i+1; j<points.length;j++)
             {

                 double m ;
                 if(points[j][0]-points[i][0] != 0)
                     m = (double)(points[j][1]-points[i][1])/(points[j][0]-points[i][0]);
                 else m = Integer.MAX_VALUE;

                List<AbstractMap.SimpleEntry<Integer, Integer>> line = new ArrayList<>();

                 line.add(new AbstractMap.SimpleEntry<Integer, Integer>(points[j][0],points[j][1] ));
                 line.add(new AbstractMap.SimpleEntry<Integer, Integer>(points[i][0],points[i][1] ));

                if (slopeLine.get(m) == null )
                    slopeLine.put(m, line);

                else
                {
                    if(!slopeLine.get(m).contains(new AbstractMap.SimpleEntry<Integer, Integer>(points[j][0],points[j][1] )))
                    {
                        List<AbstractMap.SimpleEntry<Integer, Integer>> line1 = slopeLine.get(m);
                        line1.add(new AbstractMap.SimpleEntry<Integer, Integer>(points[j][0],points[j][1] ));

                        slopeLine.put(m, line1);
                    }
                    if(!slopeLine.get(m).contains(new AbstractMap.SimpleEntry<Integer, Integer>(points[i][0],points[i][1] )))
                    {
                        List<AbstractMap.SimpleEntry<Integer, Integer>> line1 = slopeLine.get(m);
                        line1.add(new AbstractMap.SimpleEntry<Integer, Integer>(points[i][0],points[i][1] ));

                        slopeLine.put(m, line1);
                    }

                }


             }
int max=0;
        for (Map.Entry<Double, List<AbstractMap.SimpleEntry<Integer, Integer>>> entry:  slopeLine.entrySet() )
        {
            System.out.println("Slope = " + entry.getKey() + " Points = " + entry.getValue());
            if ( entry.getValue().size() > max) max = entry.getValue().size();
        }
        System.out.println("Max num is " + max);
        return max;

    }
    public static void main(String[] args)
    {
        int[][] input = {{1,1},{3,2},{5,3},{4,1},{2,3},{1,4} };
        new jewelsStones().numJewelsInStones( input );
    }

}
