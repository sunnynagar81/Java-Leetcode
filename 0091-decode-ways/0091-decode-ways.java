class Solution {
    public int numDecodings(String s) {

        int n = s.length();
        int[] dp = new int[n];
        Arrays.fill(dp,-1);
        return f(0,s,dp);
    }

    int f(int i,String s,int[] dp){

        if(i == s.length()) return 1; 

        if(s.charAt(i) == '0') return 0;

        if(dp[i] != -1) return dp[i];

        int onedigit = f(i+1,s,dp);
        int twodigit = 0;

        if(i+1 < s.length()){

            int num = (s.charAt(i) - '0') * 10 + (s.charAt(i+1) - '0');

            if(num >= 10 && num <= 26){
                twodigit = f(i+2,s,dp);
            }
        }
        dp[i] = onedigit + twodigit;
        return dp[i];
    }
}