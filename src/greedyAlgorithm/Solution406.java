package greedyAlgorithm;

import java.util.Arrays;

/**
 * #406. Queue Reconstruction by Height
 */
public class Solution406 {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (person1, person2) -> {
            if (person1[0] != person2[0]) {
                return person1[0] - person2[0];
            } else {
                return person2[1] - person1[1];
            }
        });
        int n = people.length;
        int[][] ans = new int[n][];
        for (int[] person : people) {
            int spaces = person[1] + 1;
            for (int i = 0; i < n; ++i) {
                if (ans[i] == null) {
                    --spaces;
                    if (spaces == 0) {
                        ans[i] = person;
                        break;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution406 s = new Solution406();
        System.out.println(Arrays.deepToString(s.reconstructQueue(new int[][]{{4, 4}, {7, 1}, {7, 0}, {5, 2}, {5, 0}, {6, 1}})));
    }
}
