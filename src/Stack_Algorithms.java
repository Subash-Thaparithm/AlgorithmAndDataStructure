import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

public class Stack_Algorithms {
    public static void main(String[] args) {
        new Stack_Algorithms().removeKdigits("10200", 1);
        new Stack_Algorithms().maximumSwap(1993);
    }
    /**
     * Deleting k digits means keeping n - k digits, where n is the total number of digits.
     * Use a stack that you keep sorted ascendingly. You remove elements from it as long as you can still make it to n - k digits and your current element is smaller than the top
     * of the stack:
     */
    public String removeKdigits(String num, int k) {
        Stack<Integer> stack = new Stack<Integer>();
        int countRemainingElements = num.length();

        for(int i=0; i< num.length(); i++)  {
            countRemainingElements = num.length() - i;//including this

            if (stack.isEmpty()) stack.push(Integer.parseInt(String.valueOf(num.charAt(i))));
            else if (stack.peek() <= Integer.parseInt(String.valueOf(num.charAt(i)))) stack.push(Integer.parseInt(String.valueOf(num.charAt(i))));
            else {
                while (! stack.isEmpty() && k>0 &&stack.size() -1 + countRemainingElements >= num.length() - k  && stack.peek() > Integer.parseInt(String.valueOf(num.charAt(i))) ) {
                    stack.pop();
                    k--;
                }
                stack.push(Integer.parseInt(String.valueOf(num.charAt(i))));
           }
        }
        String out = "" ;
        for (int i=0; i< stack.size();i++) {
            if ( out.length() != num.length()-k)   out = out + stack.get(i) ;
            else break;
        }
        out = out.replaceFirst("^0+(?!$)", "");
        return out ;
    }

    public String removeKdigits_Faster(String num, int k) {
    int len = num.length();
    //corner case
        if(k==len)
            return "0";

    Stack<Character> stack = new Stack<>();
    int i =0;
        while(i<num.length()){
        //whenever meet a digit which is less than the previous digit, discard the previous one
        while(k>0 && !stack.isEmpty() && stack.peek()>num.charAt(i)){
            stack.pop();
            k--;
        }
        stack.push(num.charAt(i));
        i++;
    }

    // corner case like "1111"
        while(k>0){
        stack.pop();
        k--;
    }

    //construct the number from the stack
    StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty())
                sb.append(stack.pop());
        sb.reverse();

    //remove all the 0 at the head
        while(sb.length()>1 && sb.charAt(0)=='0')
                sb.deleteCharAt(0);
        return sb.toString();
}

    public int maximumSwap(int num) { // Apply optimisation
        char[] A = Integer.toString(num).toCharArray();
        char[] ans = Arrays.copyOf(A, A.length);
        for (int i = 0; i < A.length; i++) {
            for (int j = i+1; j < A.length; j++) {
                char tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
                for (int k = 0; k < A.length; k++){
                    if (A[k] != ans[k]){
                        if (A[k] > ans[k]) {
                            ans = Arrays.copyOf(A, A.length);
                        }
                        break;
                    }
                }
                A[j] = A[i];
                A[i] = tmp;
            }
        }
        return Integer.valueOf(new String(ans));
    }
    // requires i0 < i1 < s.length()
    public String swap(String s, int i0, int i1) {
        String s1 = s.substring(0, i0);
        String s2 = s.substring(i0+1, i1);
        String s3 = s.substring(i1+1);
        return s1+s.charAt(i1)+s2+s.charAt(i0)+s3;
    }
     int largestNum(int num)
    {  // converting the number to the string
        String num_in_str = "" + num;
        String temp = num_in_str;
        // swamping each digit
        for (int i = 0; i < num_in_str.length(); i++) {
            for (int j = i + 1; j < num_in_str.length();j++) {
                // Swapping and checking for the larger
                num_in_str = swap(num_in_str, i, j);
                if (temp.compareTo(num_in_str) < 0)
                    temp = num_in_str;
                // Reverting the changes
                num_in_str = swap(num_in_str, i, j);
            }
        }
        return Integer.parseInt(temp);
    }
}
