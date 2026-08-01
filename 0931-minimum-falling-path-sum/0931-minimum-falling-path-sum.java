class Solution {
    public int minFallingPathSum(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;

        int[][] dp = new int[m][n];

        for(int j = 0; j < n; j++)
           dp[0][j] = matrix[0][j];

        for(int i=1;i<=m-1;i++){
            for(int j=0;j<n;j++){
           int s = matrix[i][j] + dp[i-1][j];

           int ld = matrix[i][j];
           if(j>0)
             ld += dp[i-1][j-1];
           else
             ld += (int)1e9;  
           int rd = matrix[i][j];
           if(j < n-1)
             rd += dp[i-1][j+1];
           else
             rd += (int)1e9;  

           dp[i][j] = Math.min(s,Math.min(ld,rd));

          }
        }
        
        int ans = Integer.MAX_VALUE;

        for(int j = 0; j < n; j++)
            ans = Math.min(ans, dp[m-1][j]);

        return ans;
    }
}