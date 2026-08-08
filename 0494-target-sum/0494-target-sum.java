class Solution {
    public int findTargetSumWays(int[] nums, int target) {

        int n = nums.length;
        return f(n-1,0,nums,target);
    }

    int f(int i,int sum,int[] nums,int target){

        if(i < 0){
            return sum == target ? 1 : 0;
        }

        int add = f(i-1,sum + nums[i],nums,target);

        int subtract = f(i-1,sum - nums[i],nums,target);

        return add + subtract;
    }
}