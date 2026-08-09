class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        
        int l = strs.length;
        int[][][] dp = new int[l][m+1][n+1];
        for (int i = 0; i < l; i++) {
            for (int j = 0; j <= m; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return f(l-1,m,n,strs,dp);
    }
    int f(int ind,int m,int n,String[] strs,int[][][] dp){

        if(ind < 0){
            return 0;
        }

        if(dp[ind][m][n] != -1) return dp[ind][m][n];

        int zeros = 0;
        int ones = 0;

        for(char ch : strs[ind].toCharArray()){
            if(ch == '0')
              zeros++;
            else
              ones++;  
        }

        int notTake = f(ind-1,m,n,strs,dp);
        int take = 0;
        if(zeros <= m && ones <= n){
            take = 1 + f(ind-1,m-zeros,n-ones,strs,dp);
        }

        dp[ind][m][n] = Math.max(take,notTake);
        return dp[ind][m][n];
    }
}