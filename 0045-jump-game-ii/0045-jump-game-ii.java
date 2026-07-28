class Solution {
    public int jump(int[] nums) {

        int[] dp = new int[nums.length];
        Arrays.fill(dp,-1);
        return f(0,nums,dp);
    }

    int f(int i,int[] nums,int[] dp){

        if(i >=nums.length-1)
          return 0;

        if(dp[i] != -1) return dp[i];  

        int minJumps = Integer.MAX_VALUE;

        for(int jump=1;jump<=nums[i];jump++){

            int next = f(i+jump,nums,dp);

            if(next != Integer.MAX_VALUE){

                minJumps = Math.min(minJumps,1+next);
            }
        }
        return dp[i] = minJumps;  
    }
}