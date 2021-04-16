package greedyAlgorithm;

/**
 * #605. Can Place Flowers
 */
public class Solution605 {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (flowerbed == null || flowerbed.length <= 0) return false;
        if (flowerbed.length == 1) {
            if ((n == 1 && flowerbed[0] == 0) || (n == 0)){
                return true;
            }
            return false;
        }

        for (int i = 0; i < flowerbed.length; i++) {
            if (i == 0) {
                if (flowerbed[i] == 0 && flowerbed[i + 1] == 0) {
                    flowerbed[i] = 1;
                    n--;
                }
            } else if (i == flowerbed.length-1) {
                if(flowerbed[i] == 0 && flowerbed[i - 1] == 0){
                    flowerbed[i] = 1;
                    n--;
                }
            } else if (flowerbed[i] == 0 && flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0) {
                flowerbed[i] = 1;
                n--;
            }
        }
        return n <= 0;
    }

    public static void main(String[] args) {
        Solution605 s = new Solution605();
        System.out.println(s.canPlaceFlowers(new int[]{1,0,0,0,1}, 2));
    }
}
