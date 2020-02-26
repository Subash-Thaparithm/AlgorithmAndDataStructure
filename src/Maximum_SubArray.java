public class Maximum_SubArray {
    public static int maxSubArray(int[] nums) {
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
            int[] input = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4 };
            System.out.println(Maximum_SubArray.maxSubArray(input));
        System.out.println(Maximum_SubArray.maxSubArray_Product(input));
        }
}
