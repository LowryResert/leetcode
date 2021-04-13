package greedyAlgorithm;

import java.util.Arrays;
import java.util.Comparator;

/**
 * #435. Non-overlapping Intervals
 */
public class Solution435 {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length <= 1) return 0;

        Arrays.sort(intervals, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                if (o1[1] < o2[1]) {
                    return -1;
                }
                return 1;
            } else if (o1[0] < o2[0]) {
                return -1;
            }
            return 1;
        });

        int ret = 0;
        int[] curInterval = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            if (isOverlapping(curInterval, intervals[i])) {
                ret += 1;
                if (curInterval[0] == intervals[i][0]) {
                    curInterval[1] = Math.min(curInterval[1], intervals[i][1]);
                } else {
                    if (curInterval[1] > intervals[i][1]) {
                        curInterval = intervals[i];
                    }
                }
            } else {
                curInterval = intervals[i];
            }
        }

        return ret;
    }

    /**
     * check if these two intervals are overlapping.
     * if so, return true, otherwise return false.
     */
    public boolean isOverlapping(int[] i1, int[] i2) {
        if (i1[0] == i2[0]) {
            return true;
        } else if (i1[0] > i2[0]) {
            return i1[0] < i2[1];
        } else {
            return i2[0] < i1[1];
        }
    }

    public static void main(String[] args) {
//        Solution435 solution435 = new Solution435();
//        System.out.println(solution435.isOverlapping(new int[]{0,2}, new int[]{2,4}));
//        int[][] intervals = new int[][]{{0,2},{1,3},{2,4},{3,5},{4,6}};
//        System.out.println(Arrays.deepToString(intervals));
//        System.out.println(solution435.eraseOverlapIntervals(intervals));
    }
}
