class Solution {
    int maxside = 0;
    public int maximalSquare(char[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        for(int[] row : dp){
            Arrays.fill(row,-1);
        }

        f(0,0,matrix,dp);
        return maxside * maxside;
    }

    int f(int i,int j,char[][] matrix,int[][] dp){
        
        int m = matrix.length;
        int n = matrix[0].length;

        if(i == m || j == n)
          return 0;

        if(dp[i][j] != -1) return dp[i][j];  

        int right = f(i,j+1,matrix,dp);
        int left = f(i+1,j,matrix,dp);
        int daigonal = f(i+1,j+1,matrix,dp);

        if(matrix[i][j] == '1'){
            dp[i][j] = 1+ Math.min(right,Math.min(left,daigonal));
            maxside = Math.max(maxside,dp[i][j]);

            return dp[i][j];
        }
        else{
            dp[i][j] = 0;
        }
        return dp[i][j];  
    }
}