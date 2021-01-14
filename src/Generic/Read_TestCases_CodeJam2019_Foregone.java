package Generic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Read_TestCases_CodeJam2019_Foregone {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            String numCases = reader.readLine();

            for (int i = 0; i < Integer.parseInt(numCases); i++) {
                int input = Integer.parseInt(reader.readLine());
                int mid = input / 2;

                for (int x = 1, y = input-1; x<=mid && mid < y ;x++,y--){
                  if(x+y==input)     {
                      if (! String.valueOf(x).contains("4") && ! String.valueOf(y).contains("4") ) {
                          System.out.println("Case #" + (i+1) + ": " + x +" " + y);
                          break;
                      }
                  }
                }
            }
        }
         catch (IOException e) {
            e.printStackTrace();
        }
    }


}
