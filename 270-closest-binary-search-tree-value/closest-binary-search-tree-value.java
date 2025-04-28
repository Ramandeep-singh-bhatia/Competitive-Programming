class Solution {
    public int closestValue(TreeNode root, double target) {
        int result = root.val;

        while (root != null) {

            if (Math.abs(root.val - target) < Math.abs(result - target) 
                || (Math.abs(root.val - target) == Math.abs(result - target) 
                    && root.val < result)) {
                result = root.val;
            }
            
            if(target < root.val)
                root = root.left;
            else
                root = root.right;
        }

        return result;
    }
}
