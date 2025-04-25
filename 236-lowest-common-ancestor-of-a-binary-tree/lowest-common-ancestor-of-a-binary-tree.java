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

/*
    For the given binary tree, if we have to solve this without using parent pointer, we will need to keep few things in mind. 
    If there was a stack, or we were using a dfs approach, we could have easily navigated to previous node once we are done with the left and right subtree, but here since we will be using an irteratibe approach we will have to keep track of what is processed and what is pending for each node. For each node, the p or q could be the node itself, or it could be in left subtree or right subtree. So for each node, we need to keep track of whether we found p or q. Like I said it could be the node itself, node in left subtree or node in right subtree. So for each node we can have boolean to track whether we have found p or q. 
    Also we will have to keep track of whether we have processed the left subtree and right subtree of the node, before we complete the node and move to the previous node. So we can keep track of the state which denotes 
    0 - whether we are getting this node for the first time,
    1 - whether we are processing the left subtree of the node,
    2 - whether we are processing the right subtree of the node,
    3 - whether we have completed the processing of both left and right subtree of the node

    0 - whether we are getting this node for the first time
        If the node itself is p or q we update the node information. In case we have found both pa nd q for any node, we can say that we have found the lca, If not we can change the state of the node to 1 meaning we have to process the left sub tree of the node. 
    1 - whether we are processing the left subtree of the node
        If node.left is not null, we push the node along with its state info, and its boolean infor for p and q found into the stack.  
        Also we update the current node whose left subtree is being processed to 2 which means that we now have to process the right subtree
    2 - whether we are processing the left subtree of the node
        If node.left is not null, we push the node along with its state info, and its boolean infor for p and q found into the stack.
        Also we update the current node whose right subtree is being processed to 3 which means that we now have processed both left and right subtree
    3 - Means for the current node, we have processed both left and right subtree. So we pop the node from stack and check if for the current node we have found p and q. If yes and there is no lca, we found our lca. Else, take the peek node from stack, which is the parent of the current node and update the boolean variable representing whether we have found p or q. We can check if the boolean is already true meaning the parent has already found p or q from either itself, or its left or right subtree. If it is true we keep it true, if it is false, we have to check if current node has p or q so we update that to its parent. 

    Why do we need to keep track of the pFound and qFound and pass it on to its parent. It is possible that we have found just p or q  till this node from its left subtree or right subtree or even from the node itself. We pass this information to its parent so that it explore the other subtree or itself and verify if we have found both p and q before returning the lca.
Time - O(n)
Space - O(h). Will be O(n) in worst case for skewed trees

*/
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
                parent[2] = (boolean)parent[2] || pFound;
                parent[3] = (boolean)parent[3] || qFound;
                
                // If parent now has both p and q, it's the LCA
                if ((boolean)parent[2] && (boolean)parent[3] && lca == null) {
                    lca = (TreeNode)parent[0];
                    break;
                }
            }
        }
    }
    
    return lca;
    }
}