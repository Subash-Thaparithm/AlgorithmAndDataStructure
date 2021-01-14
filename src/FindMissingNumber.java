import Sorting.QuickSort;

public class FindMissingNumber {
  public int findDisappearedNumbers(int[] nums) {
        QuickSort.quickSort(nums,0,nums.length-1);

        for(int i = 0; i < nums.length-1; i++) {
            if(nums[i]==nums[i+1]) return nums[i];
        }
        return -1;
    }

    public static void main(String[] args) {
       System.out.println(new FindMissingNumber().firstMissingPositive(new int[]{3,4,-1,1}));
    }
     int partition(int arr[], int left, int right)
    {
        int i = left, j = right;

        int tmp;

        int pivot = arr[(left + right) / 2];



        while (i <= j) {

            while (arr[i] < pivot)

                i++;

            while (arr[j] > pivot)

                j--;

            if (i <= j) {

                tmp = arr[i];

                arr[i] = arr[j];

                arr[j] = tmp;

                i++;

                j--;

            }

        };


        return i;

    }
    private  void quickSort(int arr[], int low, int high)
    { int index = partition(arr, low, high);

        if (low < index - 1)

            quickSort(arr, low, index - 1);

        if (index < high)

            quickSort(arr, index, high);
    }

    public int firstMissingPositive(int[] nums) {

        int i=0;
        while(i< nums.length){
            if (nums[i]>0 && nums[i]<=nums.length && nums[nums[i]-1] != nums[i])// Third condition being true      //indicates that either the number is in its right index or the swapping postions are equal and no need to swap
            {//swap and put the number in its place
                int temp = nums[i];
                nums[i] = nums[nums[i]-1];
                nums[nums[i]-1] = temp;
            }
            else i++;
        }
        for( i =0; i< nums.length; i++){
            System.out.print(nums[i] + ",");
        }

        for( i =0; i< nums.length; i++){
            if (nums[i] !=i+1) return i+1;
        }

        return -1;
    }
}
