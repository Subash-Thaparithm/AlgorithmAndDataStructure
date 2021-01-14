package Generic;

public class MoveZeroesToTheEnd {
    public void moveZeroes(int[] nums) {
        Integer firstzero = null;
        Integer endzero = null;
        Integer firstnonzero = null;
        Integer endnonzero = null;

        for (int i=0; i<nums.length; i++) {
            if (  nums[i] == 0 ) {
                if (i != 0 && nums[i-1] != 0){
                    //swap previous blocks of zeroes and non zeroes if exist both
                    if (firstzero != null) {
                        int newStartZero = endnonzero-firstnonzero + 1 + firstzero ;
                        swap(nums,  firstzero,  endzero,  firstnonzero,  endnonzero);
                        firstzero = newStartZero;
                        endzero = i;//correct
                        firstnonzero = null;
                        endnonzero = null;

                    }
                    else {
                        endzero = i;
                        if (firstzero == null) firstzero = i;
                    }
                    //change indexes of zeroes and non zeroes
                }
                else {
                    endzero = i;
                    if (firstzero == null) firstzero = i;
                }

            }
            else if (  nums[i] != 0 ) {
                if (firstzero != null ) endnonzero = i;
                if (firstzero!= null && firstnonzero == null) firstnonzero = i;
                if (i == nums.length-1) swap(nums, firstzero,endzero,firstnonzero,endnonzero);
            }

        }
    }
    //swap block of zeroes and non zeroes
    private void swap(int[] nums, Integer firstzero, Integer endzero, Integer firstnonzero, Integer endnonzero)
    {
        if (firstzero == null || firstnonzero == null) return;
        int countNonZeroes = endnonzero-firstnonzero+1;
        for(int i=0; i<countNonZeroes;i++) {
          nums[firstzero] = nums[firstnonzero];
          firstzero++; firstnonzero++;
      }
      for(int j=firstzero;j<=endnonzero;j++){
        nums[j] = 0;
      }
    }

    public static void main(String args[]){
        int [] nums =  {4,2,4,0,0,3,0,5,1,0};
        new MoveZeroesToTheEnd().moveZeroes(nums);
        System.out.println (nums);
    }
}