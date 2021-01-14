package Generic;

public class Linear_Sort_Constant_space
{

    public int missingNumber(int[] nums)
    {

        for(int i=0; i< nums.length;i++)
        {
            if (nums[i] != i)  swap(nums, i, nums[i]);
        }

        for(int i=0; i<= nums.length-1;i++)
        {
            if (nums[i] != i)  return i;
        }
        return nums.length;

    }

    private void swap(int[] nums, int index1, int index2)
    {
        if (index2 > nums.length-1) index2 = nums.length-1;
        int temp = nums[index1];
        nums[index1] =nums[index2];
        nums[index2]= temp;

        if (nums[index1] != 1)  swap(nums, index1, nums[index1]);

    }

    public static void main(String[] args)
    {
        int[] nums = new int[]{1,2,3};

        System.out.println(new Linear_Sort_Constant_space().missingNumber(nums));
    }
}