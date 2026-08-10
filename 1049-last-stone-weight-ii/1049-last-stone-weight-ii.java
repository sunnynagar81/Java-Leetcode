class Solution {
    public int lastStoneWeightII(int[] stones) {
        
        int n = stones.length;
        int total = 0;
        for(int stone : stones){
            total += stone;
        }

        int offset = total;

        int[][] dp = new int[n][2*total+1];
        for(int[] row : dp){
            Arrays.fill(row,-1);
        }

        return f(n-1,0,stones,dp,offset);
    }

    int f(int ind,int sum,int[] stones,int[][] dp,int offset){

        
        if(ind < 0){
            return Math.abs(sum);
        }

        int index = sum + offset;

        if(dp[ind][index] != -1) return dp[ind][index];

        int add = f(ind-1,sum + stones[ind],stones,dp,offset);

        int sub = f(ind-1,sum - stones[ind],stones,dp,offset);

        dp[ind][index] = Math.min(add,sub);
        return dp[ind][index];
    }
}