package Generic;

public class Maximum_SubArray {
    public static int maxSubArray_Sum(int[] nums) {
            int max_sum=nums[0];
            int sum=0;

            for(int i=0;i<nums.length;i++)
            {
                sum=sum+nums[i];
                if(max_sum <sum)
                    max_sum=sum;
                if(sum<0)
                    sum=0;
            }
            return max_sum;
        }

    public static int maxSubArray_Product(int[] nums) {

        //These 2 variables
        int[] max = new int[nums.length]; // max[i] denotes the Max. product so far if we must include max[i] and  and if array ends here
        int[] min = new int[nums.length]; // min[i] denotes the min. product so far if we must include min[i] and if array ends here

        max[0] = min[0] = nums[0];
        int result = nums[0];  // Maximum product subarray of whole input

        for(int i=1; i<nums.length; i++){
            if(nums[i]>0){
                max[i]=Math.max(nums[i], max[i-1]*nums[i]);
                min[i]=Math.min(nums[i], min[i-1]*nums[i]);
            }else{
                max[i]=Math.max(nums[i], min[i-1]*nums[i]);
                min[i]=Math.min(nums[i], max[i-1]*nums[i]);
            }

            result = Math.max(result, max[i]);
        }

        return result;
    }

    public static void main(String[] args) {
            int[] input = new int[]{9,-10,15,20,7 };
            System.out.println(Maximum_SubArray.maxSubArray_Sum(input));
            int sum = Max_SubArray_DivideandConquer.maxSumSubarray(input,0,input.length-1);
            System.out.println(Maximum_SubArray.maxSubArray_Product(input));
        }
}
 class Max_SubArray_DivideandConquer {

    // function to return maximum number among three numbers
    static int getmMaxValue(int a, int b, int c)
    {
        if (a>=b && a>=c)
            return a;
        else if (b>=a && b>=c)
            return b;
        return c;
    }

    // function to find maximum sum of subarray crossing the middle element
    static int maxCrossingSubarray(int ar[], int low, int mid, int high)
    {
    /*
      Initial leftSum should be -infinity.
    */
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        int i;

    /*
      iterating from middle
      element to the lowest element
      to find the maximum sum of the left
      subarray containing the middle
      element also.
    */
        for (i=mid; i>=low; i--)
        {
            sum = sum+ar[i];
            if (sum>leftSum)
                leftSum = sum;
        }

    /*
      Similarly, finding the maximum
      sum of right subarray containing
      the adjacent right element to the
      middle element.
    */
        int rightSum = Integer.MIN_VALUE;
        sum = 0;

        for (i=mid+1; i<=high; i++)
        {
            sum=sum+ar[i];
            if (sum>rightSum)
                rightSum = sum;
        }

    /*
      returning the maximum sum of the subarray
      containing the middle element.
    */
        return (leftSum+rightSum);
    }

    // function to calculate the maximum subarray sum
    static int maxSumSubarray(int ar[], int low, int high)
    {
        if (high == low) // only one element in an array
        {
            return ar[high];
        }

        // middle element of the array
        int mid = (high+low)/2;

        // maximum sum in the left subarray
        int maximumSumLeftSubarray = maxSumSubarray(ar, low, mid);
        // maximum sum in the right subarray
        int maximumSumRightSubarray = maxSumSubarray(ar, mid+1, high);
        // maximum sum in the array containing the middle element
        int maximumSumCrossingSubarray = maxCrossingSubarray(ar, low, mid, high);

        // returning the maximum among the above three numbers
        return getmMaxValue(maximumSumLeftSubarray, maximumSumRightSubarray, maximumSumCrossingSubarray);
    }

}
