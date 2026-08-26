class Solution {
    public int findNumberOfLIS(int[] nums) {

        int n = nums.length;

        int[] dp = new int[n];
        int[] count = new int[n];

        Arrays.fill(dp,1);
        Arrays.fill(count,1);

        int maxLen = 1;
        int ans = 0;
        //LIS
        for(int ind=0;ind<n;ind++){
            for(int prev=0;prev<ind;prev++){

                if(nums[prev] < nums[ind] && dp[prev]+1 > dp[ind]){
                    dp[ind] = dp[prev] + 1;
                    count[ind] = count[prev];
                }
                else if(nums[prev] < nums[ind] && dp[prev] + 1 == dp[ind]){
                    count[ind] += count[prev];
                }
            }
            maxLen = Math.max(maxLen,dp[ind]);
        }

        for(int i=0;i<n;i++){
            if(dp[i] == maxLen)
              ans += count[i];
        }
        return ans;
        
    }
}