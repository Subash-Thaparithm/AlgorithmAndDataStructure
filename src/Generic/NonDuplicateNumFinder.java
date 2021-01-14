package Generic;

public class NonDuplicateNumFinder {
    private static int findNonDuplicateNum(int[] list) {
        //Sort in linear time
        //traverse and find
        //OR
        // Bitwise XOR operations, n ^ n = 0, n ^ 0 = n
        int result = 0;

        for (int i : list) {
            result ^= i;
        }

        return result;

    }

    public static void main(String[] args) {
        int list[] = {0,0,1,2};

        NonDuplicateNumFinder.find2NonDuplicates(list);
    }

    private static int findNonTriplicateNum(int[] nums) {
        //This algorithm is by experiment or research or logic.
        int result = 0, count = 0;
        for (int i = 0; i < 32; i++) {
            count = 0;
            for (int num:nums)
            {
                // Count number of 1 bits in ith position for all numbers given
                // if ((num & (1 << i)) != 0) count++;
            }
            // Set the ith bit or position in the result with 0 or 1  based on whether count is multiple of 3 or not respectively.
            result |= ((count % 3) << i);

        }
        return result;
    }

    public int singleNumber(int[] nums) {
        int result = 0;
        for(int i = 0; i < 32; i++) {
            int sum = 0;
            for(int j = 0; j < nums.length; j++) {
                if(((nums[j] >> i) & 1) == 1) { // bitwise right shift
                    sum++;
                    sum %= 3;
                }
            }
            if(sum != 0) {
                result |= sum << i; //bitwise left shift
            }
        }
        return result;
    }

    public static int[] find2NonDuplicates(int[] nums){
        //XOR all number together
        int XOR =nums[0];
        for (int i=1; i<nums.length;i++) {
            XOR = XOR ^nums[i] ;
        }

        //get the right most set bit
        //https://algorithms.tutorialhorizon.com/find-the-right-most-set-bit-of-a-number/
        int right_most_set_bit = XOR & ~ (XOR -1);

        // Form 2 sets, 2 distinct elements in 2 diff sets, all duplicate numbers in one set together
        int[] setA=new int[nums.length-1];
        int[] setB=new int[1];

        int indexA=0, indexB=0;
        for (int i=0; i<nums.length;i++) {
            if((nums[i] & right_most_set_bit)!=0) {setA[indexA] = nums[i];indexA++;}
            else {setB[indexB] = nums[i];indexB++;}
        }
        //separate XOR, result
        int a = setA[0];
        int b = setB[0];

        for (int i=1; i<setA.length;i++) {
            a = a ^setA[i] ;
        }
        for (int i=1; i<setB.length;i++) {
            b = b ^setB[i] ;
        }

        return new int[]{a, b};
    }
}