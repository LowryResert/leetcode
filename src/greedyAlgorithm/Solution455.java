package greedyAlgorithm;

import java.util.Arrays;

/**
* #455.Assign Cookies
*/
public class Solution455 {
    public static int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        System.out.println(Arrays.toString(g));
        System.out.println(Arrays.toString(s));

        int ret = 0;
        boolean[] flag = new boolean[s.length];
        for (int k : g) {
            for (int j = 0; j < s.length; j++) {
                if (!flag[j] && k <= s[j]) {
                    ret += 1;
                    flag[j] = true;
                    break;
                }
            }
        }

        return ret;
    }

    /*public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int lg=g.length;
        int ls=s.length;
        int i=0,j=0;
        while(j<ls && i<lg){
            if(g[i]<=s[j]){
                i++;
            }
            j++;
        }
        return i;
    }*/

    public static void main(String[] args) {
        int[] g = {2,1,3,4,2};
        int[] s = {1,2,3,2};
        System.out.println(findContentChildren(g,s));
    }
}
