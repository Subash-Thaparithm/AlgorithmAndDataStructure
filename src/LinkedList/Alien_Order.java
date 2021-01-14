package LinkedList;
import java.util.ArrayList;
import java.util.List;
public class Alien_Order {
        public String alienOrder(String[] words) {
            List<String> charOrder = new ArrayList<>();
            int i = 0;
            StringBuilder sb = new StringBuilder();
            Character a = words[i].charAt(0);
            sb.append(a);i++;
            Character b = null;
            while(i != words.length){
                b = words[i].charAt(0);
               if(a != b){
                  b = words[i].charAt(0);
                  sb.append(b);
                }
               else {
                   int e =0 ;
                   Character d = words[i-1].charAt(e);
                   Character c = words[i].charAt(e);
                   while(e != words[i-1].length() && e != words[i].length()){
                        d = words[i-1].charAt(e);
                        c = words[i].charAt(e);
                       if(d != c){
                           charOrder.add(new StringBuilder().append(d).append(c).toString());
                           e++;
                       }else e++;
                  }
               }
              i++;
              a=b;
            }
            String order = sb.toString();
            charOrder.add(order);
            System.out.println(charOrder.toArray().toString());
            return order ;
        }

    public static void main(String[] args) {
            String[] input = {"waert","wrf","er","ett","rftt"};
            new Alien_Order().alienOrder(input);
    }
}