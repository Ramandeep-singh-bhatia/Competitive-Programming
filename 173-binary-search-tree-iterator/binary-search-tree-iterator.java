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

 /*
    The question suggest to do an inorder traversal and design a BST iteratoe which means that we will have to go its extreme left then the node and then right and then extreme left and so on. Why? because the inorder traversal is left -> node -> right. It goes to the extreme left and when the node is null it goes back and for the node it checks if there is a right. If it has a right it goes to the right and then again explores the extreme left. 
    There are 3 things we need to implement constructor, next and has next
    next means that at any node if we have a node we can go next as per the inorder traversal we do that and return the node. has next means if there are more nodes we can go when hasNext is called we can return true. To keep track of the node while going to the left most node in the BST we can store the nodes in a stack. 
    For constructor, we add the left nodes to the stack. 
    For next, we can get the topmost node from the stack, chek if there is a right child of the node then go to the extreem left of the node and keep adding it to the stack
    for hasNext, if there are still nodes in the stack, it means we have more nodes that we can traverse using inorder so we return true 

    Time - O(1)
    Space - O(H) i.e. logN for balanced BST and O(N) for skewed 
 */
class BSTIterator {
    Stack<TreeNode> s;
    public BSTIterator(TreeNode root) {
        s = new Stack<>();
        leftInorder(root);
    }

    private void leftInorder(TreeNode node){
        
        while(node != null){
            this.s.add(node);
            node = node.left;
        }
            
    }
    
    public int next() {
        TreeNode topMost = this.s.pop();
        if(topMost.right != null)
            leftInorder(topMost.right);

        return topMost.val;
    }
    
    public boolean hasNext() {
        return this.s.size() > 0;
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */