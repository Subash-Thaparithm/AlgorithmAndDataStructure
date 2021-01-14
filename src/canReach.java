public class canReach {
        boolean [] visited ;
        public boolean canReach(int[] arr, int start) {
            visited = new boolean[arr.length];
            visited[start] = true;
            int n= arr.length;
            boolean canReachLeft = false;
            boolean canReachRight = false;

            if ((start - arr[start]) >= 0) canReachLeft = canReach( arr, start - arr[start], start);

            if ((start + arr[start]) < n) canReachRight = canReach( arr, start + arr[start], start);

            if (canReachLeft || canReachRight) return true;
            else return false;
        }

        private boolean canReach(int[] arr, int start, int originalStart) {
            if ( ! visited[start])visited[start] = true;
            else return false;
            if (arr[start] == 0) return true;
            if (start == originalStart) return false;
            int n= arr.length;

            boolean canReachLeft = false;
            boolean canReachRight = false;

            if ((start - arr[start]) >= 0) canReachLeft = canReach( arr, start - arr[start], originalStart);
            if (canReachLeft ) return true;
            if ((start + arr[start]) < n) canReachRight = canReach( arr, start + arr[start], originalStart);
            if (canReachRight) return true;


            else return false;
        }

    public int jump(int[] nums) {
            int [] dp = new int[nums.length];
            dp[0] = 0;

            for(int target=1; target<nums.length; target++)
                for(int j=0; j<=target;j++) {
                    if (j + nums[j]>=target){
                       if(dp[target] != 0) dp[target] = Math.min(dp[target], 1 + dp[j]);
                       else  dp[target] =  1 + dp[j];
                    }
                }
            return dp[nums.length-1];
    }

    public int minJump(){
      int []nums = new int[]{1,3,5,3,2,2,6,1,6,8,9};
       int n = nums.length;
        if(n<2)return 0;
        int level=0,currentMax=0,i=0,nextMax=0;

        while(currentMax-i+1>0){		              //nodes count of current level>0
            level++;
            for(i=i;i<=currentMax;i++){	              //traverse current level , and update the max reach of next level
                nextMax=Math.max(nextMax,nums[i]+i);
                if(nextMax>=n-1)return level;         // if last element is in level+1,  then the min jump=level
            }
            currentMax=nextMax;
        }
        return 0;
    }


    public static void main(String[] args) {
        new canReach().minJump();
    }

}
