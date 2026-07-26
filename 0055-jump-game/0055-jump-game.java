class Solution {
    public boolean canJump(int[] nums) {

       
        int[] dp = new int[nums.length];
        Arrays.fill(dp,-1);
        return f(0,nums,dp);
    }

    boolean f(int i,int[] nums,int[] dp){

        if(i >= nums.length-1)
           return true;  // already reached

        if(dp[i] != -1)
           return dp[i] == 1;

        for(int jump=1;jump<=nums[i];jump++){

            if(f(i+jump,nums,dp)){
               dp[i] = 1;
               return true;
            }   
        }
        dp[i] = 0;
        return false;   
    }
}