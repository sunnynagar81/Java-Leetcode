class Solution {

    public int cherryPickup(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int[][][] dp = new int[m][n][n];
        for(int[][] row : dp){
            for(int[] col : row){
              Arrays.fill(col,-1);
            }  
        }

        return f(0, 0, n - 1, grid,dp);
    }

    int f(int i, int j1, int j2, int[][] grid,int[][][] dp) {

        int m = grid.length;
        int n = grid[0].length;

        // Out of bounds
        if (j1 < 0 || j1 >= n || j2 < 0 || j2 >= n)
            return -(int)1e9;

        // Last row
        if (i == m - 1) {

            if (j1 == j2)
                return grid[i][j1];

            return grid[i][j1] + grid[i][j2];
        }

        // already covered
        if(dp[i][j1][j2] != -1) return dp[i][j1][j2];

        int ans = -(int)1e9;

        for (int d1 = -1; d1 <= 1; d1++) {

            for (int d2 = -1; d2 <= 1; d2++) {

                if(j1 == j2)
                  ans = Math.max(ans,grid[i][j1] + f(i+1,j1+d1,j2+d2,grid,dp));
                else
                  ans = Math.max(ans,grid[i][j1] + grid[i][j2] + f(i+1,j1+d1,j2+d2,grid,dp));
    
            }
        }

        return dp[i][j1][j2]=ans;
    }
}