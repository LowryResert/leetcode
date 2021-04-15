package dailyPractice;

/**
 * #198. House Robber
 */
public class Solution198 {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) {
            return nums[0];
        }

        int p1 = nums[0];
        int p2 = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int temp = p2;
            p2 = Math.max(p2, p1 + nums[i]);
            p1 = temp;
        }

        return p2;
    }

    public static void main(String[] args) {
//        Solution198 solution198 = new Solution198();
//        System.out.println(solution198.rob(new int[]{2,7,9,3,1,20}));
    }
}
