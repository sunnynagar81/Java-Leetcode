class Solution {
    public void rotate(int[] nums, int k) {

        int n = nums.length;
        if (n == 0) return;

        k = k % n;

        int[] temp = new int[k];

        // Store last k elements
        for (int i = 0; i < k; i++) {
            temp[i] = nums[n - k + i];
        }

        // Shift remaining elements right
        for (int j = n - k - 1; j >= 0; j--) {
            nums[j + k] = nums[j];
        }

        // Copy stored elements to the beginning
        for (int i = 0; i < k; i++) {
            nums[i] = temp[i];
        }
    }
}