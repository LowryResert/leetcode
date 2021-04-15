package dailyPractice;

import java.util.Arrays;

/**
 * #213. House Robber II
 */
public class Solution213 {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        return Math.max(myRob(Arrays.copyOfRange(nums, 0, nums.length - 1)), myRob(Arrays.copyOfRange(nums, 1, nums.length)));
    }

    private int myRob(int[] nums) {
        int p1 = 0, p2 = 0, temp;
        for (int num : nums) {
            temp = p2;
            p2 = Math.max(p1 + num, p2);
            p1 = temp;
        }
        return p2;
    }

    public static void main(String[] args) {

    }
}
