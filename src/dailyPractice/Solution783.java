package dailyPractice;

/**
 * #783. Minimum Distance Between BST Nodes
 */
public class Solution783 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private int diff;
    private int preVal;

    public int minDiffInBST(TreeNode root) {
        diff  = Integer.MAX_VALUE;
        preVal = Integer.MIN_VALUE;
        inTraverse(root);
        return diff;
    }

    public void inTraverse(TreeNode p) {
        if (p != null) {
            inTraverse(p.left);
            if (preVal != Integer.MIN_VALUE) {
                diff = Math.min(p.val - preVal, diff);
            }
            preVal = p.val;
            inTraverse(p.right);
        }
    }
}
