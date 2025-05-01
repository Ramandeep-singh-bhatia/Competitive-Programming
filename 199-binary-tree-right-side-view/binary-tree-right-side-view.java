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

 // Another solution that takes less time

 /*class Solution {
    List<Integer> results = new LinkedList<>();

    void helper(TreeNode node, int depth){
        if (node == null){
            return;
        }

        if (depth >= results.size()){
            results.add(node.val);
        }

        if (node.right == null){
            helper(node.left, depth+1);
        } else {
            helper(node.right, depth+1);
        }

        if (node.left == null){
            helper(node.right, depth+1);
        } else {
            helper(node.left, depth+1);
        }

    }

    public List<Integer> rightSideView(TreeNode root) {
        helper(root, 0);
        return results;
        
    }
}*/

 

 /*
    Intution:
    Do BFS to traverse through same level. 
    Time - O(N)
    Space - O(D) D - Diameter

    For level order traverse, we aill have to add all the nodes in same level once before we move to the next level. Hence we will iterate through each node in the queue and add the left and right of those nodes in a loop before we move to next level
 */

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if(root == null)
            return result;
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);


        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                TreeNode node = q.poll();
                if(node.left != null){
                    q.add(node.left);
                }

                if(node.right != null){
                    q.add(node.right);
                }

                if( i == size - 1){
                    result.add(node.val);
                }
            }
        }

        return result;
    }
} 

/*public class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        rightView(root, result, 0);
        return result;
    }
    
    public void rightView(TreeNode curr, List<Integer> result, int currDepth){
        if(curr == null){
            return;
        }
        if(currDepth == result.size()){
            result.add(curr.val);
        }
        
        rightView(curr.right, result, currDepth + 1);
        rightView(curr.left, result, currDepth + 1);
        
    }
}*/