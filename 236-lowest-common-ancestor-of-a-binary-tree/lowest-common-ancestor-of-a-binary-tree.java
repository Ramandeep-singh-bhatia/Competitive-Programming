/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

 /*
Intuition

The approach is pretty intuitive. Traverse the tree in a depth first manner. The moment you encounter either of the nodes p or q, return some boolean flag. The flag helps to determine if we found the required nodes in any of the paths. The least common ancestor would then be the node for which both the subtree recursions return a True flag. It can also be the node which itself is one of p or q and for which one of the subtree recursions returns a True flag.

Let us look at the formal algorithm based on this idea.

Algorithm

Start traversing the tree from the root node.
If the current node itself is one of p or q, we would mark a variable mid as True and continue the search for the other node in the left and right branches.
If either of the left or the right branch returns True, this means one of the two nodes was found below.
If at any point in the traversal, any two of the three flags left, right or mid become True, this means we have found the lowest common ancestor for the nodes p and q.
 */
/*class Solution {
    TreeNode ans = null;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p ,q);
        return ans;
    }

    public boolean dfs(TreeNode curr, TreeNode p, TreeNode q) {
        if(curr == null)
            return false;
        
        boolean left = dfs(curr.left, p ,q);

        boolean right = dfs(curr.right, p ,q);

        boolean mid = (curr == p || curr == q);
         
        if((mid && left) || (mid && right) || (left && right))
            ans = curr;
        
        return mid || left || right;
    }
}*/

/*class Solution {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // Stack for tree traversal
        Deque<TreeNode> qu = new ArrayDeque<>();
        //Queue<TreeNode> qu = new LinkedList<>();

        // HashMap for parent pointers
        Map<TreeNode, TreeNode> parent = new HashMap<>();

        parent.put(root, null);
        qu.add(root);

        // Iterate until we find both the nodes p and q
        while (!parent.containsKey(p) || !parent.containsKey(q)) {

            TreeNode node = qu.poll();

            // While traversing the tree, keep saving the parent pointers.
            if (node.left != null) {
                parent.put(node.left, node);
                qu.add(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                qu.add(node.right);
            }
        }

        TreeNode a = p, b = q;
        while(a != b){
            a = a == null ? q : parent.get(a);
            b = b == null ? p : parent.get(b);
        }
        return a;
    }

}*/
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    // Edge case
    if (root == null) return null;
    
    // [node, state, pFound, qFound]
    // state: 0 = new, 1 = processing left, 2 = processing right, 3 = done
    Deque<Object[]> stack = new ArrayDeque<>();
    stack.push(new Object[]{root, 0, false, false});
    
    TreeNode lca = null;
    
    while (!stack.isEmpty() && lca == null) {
        Object[] current = stack.peek();
        TreeNode node = (TreeNode)current[0];
        int state = (int)current[1];
        boolean pFound = (boolean)current[2];
        boolean qFound = (boolean)current[3];
        
        // Initial node check
        if (state == 0) {
            // Check if current node is p or q
            if (node == p) {
                pFound = true;
            }
            if (node == q) {
                qFound = true;
            }
            current[1] = 1; // Move to processing left child
            current[2] = pFound;
            current[3] = qFound;
            
            // If both p and q are the same node, we found LCA
            if (pFound && qFound) {
                lca = node;
                break;
            }
        }
        // Process left subtree
        else if (state == 1) {
            current[1] = 2; // Move to processing right child
            
            // Process left child if it exists
            if (node.left != null) {
                stack.push(new Object[]{node.left, 0, false, false});
                continue;
            }
        }
        // Process right subtree
        else if (state == 2) {
            current[1] = 3; // Mark as done
            
            // Process right child if it exists
            if (node.right != null) {
                stack.push(new Object[]{node.right, 0, false, false});
                continue;
            }
        }
        // Node and both subtrees processed
        else {
            stack.pop();
            
            // If we found both p and q in this subtree, this is the LCA
            if (pFound && qFound && lca == null) {
                lca = node;
                break;
            }
            
            // Propagate result to parent
            if (!stack.isEmpty()) {
                Object[] parent = stack.peek();
                boolean parentPFound = (boolean)parent[2];
                boolean parentQFound = (boolean)parent[3];
                
                parentPFound |= pFound; // Update parent's pFound
                parentQFound |= qFound; // Update parent's qFound
                
                parent[2] = parentPFound;
                parent[3] = parentQFound;
                
                // If parent now has both p and q, it's the LCA
                if (parentPFound && parentQFound && lca == null) {
                    lca = (TreeNode)parent[0];
                    break;
                }
            }
        }
    }
    
    return lca;
    }
}