import com.sun.deploy.util.ArrayUtil;

public class Sum_of_StringNumber
{
        public String addStrings(String num1, String num2)
        {
            int length1 = num1.length();
            int length2 = num2.length();

            char[] result = new char[Math.max(length1, length2)];

            int carryover = 0;
            for(int i =0; i< result.length; i++)
            {
                int digit1;
                int digit2;

                 digit1 = length1 <= i ? 0 : num1.charAt(length1 - 1 - i) - '0';
                 digit2 = length2 <= i ? 0 : num2.charAt(length2 - 1 - i) - '0';

                int sum = digit1 + digit2 +  carryover;
                carryover =  sum/10;
                int remainder =  sum%10;

                result[result.length-i-1] = Character.forDigit(remainder,10);

                if(i ==result.length-1 && carryover != 0)
                {
                    StringBuilder sb = new StringBuilder();

                    sb.append(Character.forDigit(carryover,10));
                    sb.append(result);

                   return  sb.toString();
                }
            }

            return new String (result);
        }

        public static void main(String[] args)
        {
                System.out.print("--Out-- " +new Sum_of_StringNumber().addStrings("1234","67"));
        }

}