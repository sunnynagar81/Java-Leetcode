class Solution {
    public int rob(int[] nums) {
        
        int ind = nums.length - 1;

        if(nums.length == 1) return nums[0];

        int[] dp = new int[ind + 1];

        dp[0] = nums[0];
        int neg = 0;

        for(int i=1;i<=ind;i++){

            int pick = nums[i];
            if(i > 1)
              pick += dp[i - 2];

            int notPick = 0 + dp[i - 1];

            dp[i] = Math.max(pick,notPick);
        }
        return dp[ind];
       
    }
}