/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public void recoverTree(TreeNode root) {
        List<Integer> almostSorted = new ArrayList<>();
        inorder(root, almostSorted);
        int n = almostSorted.size();
        int val1 = 0;
        int val2 = 0;
        boolean firstSwap = false;
        for(int i = 0; i < n-1; i++){
            if(almostSorted.get(i) > almostSorted.get(i+1)){
                val2 = almostSorted.get(i+1);
                if(!firstSwap){
                    val1 = almostSorted.get(i);
                    firstSwap = true;
                } else {
                    break;
                }
            }
        }

        recover(root, val1, val2 ,2);
    }

    private void recover(TreeNode root, int val1, int val2, int count){
        if(root == null)
            return;
        if(root.val == val1 || root.val == val2){
            root.val = root.val == val1 ? val2 : val1;
            if(--count == 0)
                return;
        }

        recover(root.left, val1, val2, count);
        recover(root.right, val1, val2, count);

    }

    private void inorder(TreeNode root, List<Integer> almostSorted){
        if(root == null)
            return;
        inorder(root.left, almostSorted);
        almostSorted.add(root.val);
        inorder(root.right, almostSorted);
    }
}