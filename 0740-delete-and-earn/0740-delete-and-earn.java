class Solution {
    public int deleteAndEarn(int[] nums) {
    
        int max = 0;

        for(int num : nums)
           max = Math.max(max,num);

        int[] earn = new int[max + 1];

        for(int num : nums)
           earn[num] += num;

        int[] dp = new int[max + 1];
        Arrays.fill(dp,-1);   

        return f(max,earn,dp);    
    }

    int f(int i,int[] earn,int[] dp){

        if(i == 0) return earn[0];
        if(i<0) return 0;

        if(dp[i] != -1) return dp[i];

        int pick = earn[i] + f(i-2,earn,dp);

        int notPick = f(i-1,earn,dp);

        dp[i] = Math.max(pick,notPick);

        return dp[i];
    }

}