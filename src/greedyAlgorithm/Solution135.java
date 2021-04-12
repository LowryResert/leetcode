package greedyAlgorithm;

/**
* #135.Candy
*/
public class Solution135 {
    public static int candy(int[] ratings) {
        int count = ratings.length;
        int[] candies = new int[count];

        for (int i = 1; i < count; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }
        for (int i = count - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
                candies[i] = candies[i + 1] + 1;
            }
        }

        for (int candy : candies) {
            count += candy;
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(candy(new int[]{1,3,2,2,1}));
    }
}
