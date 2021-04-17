package greedyAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * #763. Partition Labels
 */
public class Solution763 {
    public List<Integer> partitionLabels(String s) {
        List<Integer> partition = new ArrayList<>();
        if (s == null || s.length() == 0) return partition;

        int[] last = new int[26];
        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int index = last[s.charAt(i) - 'a'];
            end = Math.max(end, index);
            if (i == end) {
                partition.add(end - start + 1);
                start = end + 1;
            }
        }
        return partition;
    }

    public static void main(String[] args) {
        Solution763 s = new Solution763();
        System.out.println(Arrays.toString(s.partitionLabels("ababcbacadefegdehijhklij").toArray()));
    }
}
