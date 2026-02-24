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
/*class Solution {
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root.left);
        q.add(root.right);
        while(!q.isEmpty()){
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if(t1 == null && t2 == null) continue;
            if(t1 == null || t2 == null) return false;
            if(t1.val != t2.val) return false;
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }
}*/

/*class Solution {
    public boolean isSymmetric(TreeNode root) {
        return dfs(root.left, root.right);
    }

    private boolean dfs(TreeNode p, TreeNode q){
        if(p == null && q == null)
            return true;
        if(p == null || q == null)
            return false;
        return p.val == q.val && dfs(p.left, q.right) && dfs(p.right, q.left);
    }
}*/

class Solution {
    public boolean isSymmetric(TreeNode root) {
        // using a queue to explicitly manage the pairs we need to compare,
        // replacing what the call stack was doing implicitly in the recursive version
        Queue<TreeNode> queue = new LinkedList<>();

        // seed the queue with the first pair we need to compare
        queue.offer(root.left);
        queue.offer(root.right);

        while (!queue.isEmpty()) {
            // pull the next pair to compare
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();

            // both null means this pair is symmetric, keep going
            if (left == null && right == null) continue;

            // one null and one not means asymmetric, we're done
            if (left == null || right == null) return false;

            // values must match at this level
            if (left.val != right.val) return false;

            // enqueue children in the cross pattern — same logic as the recursion:
            // left's left child mirrors right's right child
            queue.offer(left.left);
            queue.offer(right.right);

            // left's right child mirrors right's left child
            queue.offer(left.right);
            queue.offer(right.left);
        }

        // if we exhausted the queue without finding any asymmetry, it's symmetric
        return true;
    }
}