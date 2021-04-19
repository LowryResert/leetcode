package dailyPractice;

import java.util.Arrays;

/**
 * #27. Remove Element
 */
public class Solution027 {
    public int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i++] = nums[j];
            }
        }
        return i;
    }

    public static void main(String[] args) {
        Solution027 s = new Solution027();
        int[] a = new int[]{2,3,1,4,2,4,2,3,5,2};
        System.out.println(s.removeElement(a, 2));
        System.out.println(Arrays.toString(a));
    }
}
