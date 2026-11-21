class Solution {
    public List<Integer> majorityElement(int[] nums) {
 
        int n = nums.length;

        int cnt1 = 0, cnt2 = 0;
        int el1 = Integer.MIN_VALUE;
        int el2 = Integer.MIN_VALUE;

        // Step 1: Find two potential candidates
        for (int i = 0; i < n; i++) {

            if (cnt1 == 0 && nums[i] != el2) {
                cnt1 = 1;
                el1 = nums[i];
            }
            else if (cnt2 == 0 && nums[i] != el1) {
                cnt2 = 1;
                el2 = nums[i];
            }
            else if (nums[i] == el1) {
                cnt1++;
            }
            else if (nums[i] == el2) {
                cnt2++;
            }
            else {
                cnt1--;
                cnt2--;
            }
        }

        // Step 2: Verify candidates
        cnt1 = 0;
        cnt2 = 0;

        for (int i = 0; i < n; i++) {

            if (nums[i] == el1) cnt1++;
            else if (nums[i] == el2) cnt2++;
        }

        List<Integer> ans = new ArrayList<>();

        if (cnt1 > n / 3) ans.add(el1);
        if (cnt2 > n / 3) ans.add(el2);

        return ans;
        
    }
}