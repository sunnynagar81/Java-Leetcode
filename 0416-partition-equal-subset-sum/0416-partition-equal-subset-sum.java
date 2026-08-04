class Solution {
    public boolean canPartition(int[] nums) {

        int n = nums.length;
        int totSum = 0;
        for(int i=0;i<n;i++){
            totSum += nums[i];
        }

        if(totSum % 2 != 0) return false;

        int target = totSum / 2;

        int[][] dp = new int[n][target + 1];
        for(int[] arr : dp){
            Arrays.fill(arr,-1);
        }

        return f(n-1,target,nums,dp);
    }

    boolean f(int ind,int target,int[] nums,int[][] dp){

        if(target == 0)
          return true;

        if(ind == 0)
          return (nums[0] == target);

        if(dp[ind][target] != -1) return dp[ind][target] == 1;  

        boolean notTake = f(ind-1,target,nums,dp);

        boolean Take = false;
            if(target >= nums[ind]){
                Take = f(ind-1,target - nums[ind],nums,dp);
            }

        boolean ans = Take | notTake;
        dp[ind][target] = ans ? 1 : 0;
        return ans;        
    }
}