package Generic;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

public class MaxPointsOnLine
{
    public int maxPoints(int[][] points)
    {

      Map<AbstractMap.SimpleEntry<Integer, Integer>, Integer>  distinctCount = new HashMap<>();

        for(int i=0; i<points.length;i++)
            {
                if (! distinctCount.containsKey(new AbstractMap.SimpleEntry<Integer, Integer>(points[i][0],points[i][1] )))  distinctCount.put(new AbstractMap.SimpleEntry<Integer, Integer>(points[i][0],points[i][1] ), 1);
                else distinctCount.put(new AbstractMap.SimpleEntry<Integer, Integer>(points[i][0],points[i][1] ), distinctCount.get(new AbstractMap.SimpleEntry<Integer, Integer>(points[i][0],points[i][1] ))  +1);
            }

        if(points.length ==1) return 1;


        Map<AbstractMap.SimpleEntry<BigDecimal, BigDecimal>, List<AbstractMap.SimpleEntry<Integer,Integer>>> slopeLine = new HashMap<>();

        for(int i=0; i<points.length;i++)
            for(int j=i+1; j<points.length;j++)
            {
                BigDecimal m = new BigDecimal(0.0);
               // m = m.setScale(15, BigDecimal.ROUND_DOWN);
                BigDecimal intercept = new BigDecimal(0.0);


                if(points[j][0]-points[i][0] != 0)
                    m = BigDecimal.valueOf(points[j][1]-points[i][1]).divide(BigDecimal.valueOf(points[j][0]-points[i][0]), MathContext.DECIMAL128);
                else m = BigDecimal.valueOf(Integer.MAX_VALUE);

                if (m == BigDecimal.valueOf(Integer.MAX_VALUE)) intercept = BigDecimal.valueOf(points[i][0]) ;
                else  intercept =  BigDecimal.valueOf(points[i][1]).subtract (m .multiply(BigDecimal.valueOf(points[i][0])));       //  y-mx;

                AbstractMap.SimpleEntry<BigDecimal, BigDecimal> uniqueLineIdentifier = new AbstractMap.SimpleEntry<BigDecimal, BigDecimal>(m,intercept);


                List<AbstractMap.SimpleEntry<Integer, Integer>> line = new ArrayList<>();

                if (slopeLine.get(uniqueLineIdentifier) == null )
                {
                    for(int l=0; l< distinctCount.get(new AbstractMap.SimpleEntry<Integer, Integer>(points[j][0],points[j][1]));l++)
                        line.add(new AbstractMap.SimpleEntry<Integer, Integer>(points[j][0],points[j][1]));

                    if(! Arrays.equals(points[i], points[j]))
                        for(int l=0; l< distinctCount.get(new AbstractMap.SimpleEntry<Integer, Integer>(points[i][0],points[i][1]));l++)
                            line.add(new AbstractMap.SimpleEntry<Integer, Integer>(points[i][0],points[i][1]));

                    slopeLine.put(uniqueLineIdentifier, line);
                }
                else
                {
                    if(!slopeLine.get(uniqueLineIdentifier).contains(new AbstractMap.SimpleEntry<Integer, Integer>(points[j][0],points[j][1] )))
                    {
                        List<AbstractMap.SimpleEntry<Integer, Integer>> line1 = slopeLine.get(uniqueLineIdentifier);

                        for(int l=0; l< distinctCount.get(new AbstractMap.SimpleEntry<Integer, Integer>(points[j][0],points[j][1]));l++)
                            line1.add(new AbstractMap.SimpleEntry<Integer, Integer>(points[j][0],points[j][1]));

                        slopeLine.put(uniqueLineIdentifier, line1);
                    }
                    if(!slopeLine.get(uniqueLineIdentifier).contains(new AbstractMap.SimpleEntry<Integer, Integer>(points[i][0],points[i][1] )))
                    {
                        List<AbstractMap.SimpleEntry<Integer, Integer>> line1 = slopeLine.get(uniqueLineIdentifier);
                        if(! Arrays.equals(points[i], points[j]))
                            for(int l=0; l< distinctCount.get(new AbstractMap.SimpleEntry<Integer, Integer>(points[i][0],points[i][1]));l++)
                                line1.add(new AbstractMap.SimpleEntry<Integer, Integer>(points[i][0],points[i][1]));

                        slopeLine.put(uniqueLineIdentifier, line1);
                    }
                }
            }
        int max=0;

        for (Map.Entry<AbstractMap.SimpleEntry<BigDecimal, BigDecimal>, List<AbstractMap.SimpleEntry<Integer, Integer>>> entry:  slopeLine.entrySet() )
        {
            System.out.println("Slope = " + entry.getKey() + " Points = " + entry.getValue());
            if ( entry.getValue().size() > max) max = entry.getValue().size();
        }
        return max;
    }
   public static void main(String[] args)
   {
      // System.out.println(new MaxPointsOnLine().maxPoints(new int[][]{{0,-12},{5,2},{2,5},{0,-5},{1,5},{2,-2},{5,-4},{3,4},{-2,4},{-1,4},{0,-5},{0,-8},{-2,-1},{0,-11},{0,-9}}
       System.out.println(new MaxPointsOnLine().maxPoints(new int[][]{{0,0},{94911151,94911150},{94911152,94911151}}
       ));
   }
}