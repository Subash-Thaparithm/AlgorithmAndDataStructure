package Generic;

public class ProductExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        //Use 2 loops to calculate left sum and right sum for each element and product them
        int [] leftProduct = new int[nums.length];
        //int [] rightProduct = new int[nums.length];
        int cumulativeProduct = 1;
        for (int i = 0; i <nums.length ; i++) {
            if (i ==0)  leftProduct[i] = 1;
            else {
                cumulativeProduct *= nums[i-1];
                leftProduct[i] = cumulativeProduct;
            }
        }
         cumulativeProduct = 1;
        for (int i =nums.length-1 ;i>=0; i--) {
            if (i ==nums.length-1)  leftProduct[i] = leftProduct[i];
            else {
                cumulativeProduct *= nums[i+1];
                leftProduct[i] = cumulativeProduct *  leftProduct[i];;
            }
        }
        return leftProduct;
        }
}