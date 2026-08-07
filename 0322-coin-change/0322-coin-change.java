class Solution {
    public int coinChange(int[] coins, int amount) {

        int n = coins.length;
        int[][] dp = new int[n][amount + 1];
        for(int[] row : dp){
            Arrays.fill(row,-1);
        }

        int ans = f(n-1,amount,coins,dp);

        if(ans >= (int)1e9)
          return -1;
        return ans;  
    }

    int f(int ind,int amount,int[] coins,int[][] dp){

        if(ind == 0){
            if(amount % coins[0] == 0)
              return amount/coins[0];
            
            return (int)1e9;
        }

        if(dp[ind][amount] != -1) return dp[ind][amount];
    
        int notTake = 0 + f(ind-1,amount,coins,dp);
        int Take = Integer.MAX_VALUE;
        if(coins[ind] <= amount)
          Take = 1 + f(ind,amount - coins[ind],coins,dp);

        dp[ind][amount] = Math.min(Take,notTake);  

        return dp[ind][amount];
    }
}