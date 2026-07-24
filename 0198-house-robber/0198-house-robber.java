class Solution {
    public int rob(int[] nums) {
        
        int ind = nums.length - 1;
        int[] dp = new int[ind + 1];
        Arrays.fill(dp,-1);
        return f(nums,ind,dp);
    }

    int f(int[] nums,int ind,int[] dp){

        if(ind == 0) return nums[0];
        if(ind < 0) return 0;

        if(dp[ind] != -1) return dp[ind];

        int pick = nums[ind] + f(nums,ind - 2,dp);

        int notPick = 0 + f(nums,ind - 1,dp);

        dp[ind] = Math.max(pick,notPick);

        return dp[ind];
    }
}