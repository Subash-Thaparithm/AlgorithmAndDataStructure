package Generic;

import java.util.ArrayList;
import java.util.List;

public class HappyNumber {
    private boolean isHappyNumber(List<Integer> previousSum, int num){
        int sum = calculatesumofSquaresDigits(num);
        if (sum ==1) return true;
        else if (previousSum.contains(sum)) return false;
        else previousSum.add(sum);

        return isHappyNumber(previousSum, sum);
    }
    private int calculatesumofSquaresDigits(int num){
     List<Integer> list = extractDigits(new ArrayList<>(),num);
     int sum = 0;
     for (Integer value:list) {
            sum = sum + value *value;
        }
     return sum;
    }
    private List<Integer> extractDigits(List<Integer> result, int num){
        int remainder = num%10;
        int quotient = num/10;

        result.add(remainder);
        if(quotient==0) {result.add(quotient); return result;}
        else return extractDigits(result, quotient);
    }
    public boolean isHappy(int n) {
        return isHappyNumber(new ArrayList<>(), n);
    }
    public static void main(String[] args){
        boolean result = new HappyNumber().isHappy(2);
        System.out.println(result);
    }
}