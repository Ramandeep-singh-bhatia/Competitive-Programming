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
    For the given tree we have to delete all the nodes from the tree that are in the array to_delete. If we traverse from root to leaf and delete the node in between, we might not have the option to reach the leave node. So it makes sense that we move all the way down to the leaf and then when moving upwards delete the node. When we are deleteing the nodes we have already processed the nodes below it. 

    Traversing through each node, Obviously it can be BFS or DFS and check at each node if that particular value is the one than needs to be deleted. I would prefer a DFS here and the reason is for BFS we will be going top to bottom and if we reach a node which needs to be deleted and there are child nodes that are still not reached, it would be difficult to get the reference od thode nodes. It makes sense to fo a bottom up approach so that if we reach that node, we can always have the reference stored for its child nodes. if we have to check if that particular node exist in the to_delete array, we will have to traverse though each value whihc will be costly, so I would rather prefer to traverse thought the array once and move the data in the sraay into a data structure from where I can pull the data in O(1). I would be using a set for that. 

    From computation point of view, while doing DFS, the base case would obviously be when I reach the leaf of the tree. In that case we will return null. Else for every node in the tree we will check if it exist in the to_deleet and remove the node from the tree. We will also keep adding the reference of the node we are not deleting inot a Array list which will be the nodes remaining after we remove the nodes from to_delete. This is what we will be returning.

 */ 
class Solution {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> result = new ArrayList<>();
        Set<Integer> toDelete = new HashSet<>();

        for (int i = 0; i < to_delete.length; i++){
            toDelete.add(to_delete[i]);
        }

        root = deleteNode(root, toDelete, result);
        // deleteNode will eventually return the root of the tree if it is nt deleted. We should add that to the result. 
        if(root != null){
            result.add(root);
        }

        return result;
    }

    private TreeNode deleteNode(TreeNode root, Set<Integer> toDelete, List<TreeNode> result) {
        if(root == null)
            return null;
        
        root.left = deleteNode(root.left, toDelete, result);
        root.right = deleteNode(root.right, toDelete, result);

        // If the node is in to delete, we check if we have a child for the node. Id yes we will add them to the result as we don't want to loose the reference.

        if(toDelete.contains(root.val)){
            if(root.left != null)
                result.add(root.left);
            if(root.right != null)
                result.add(root.right);
                // Return null to its parent to delete the current node
            return null;
        }
        return root;
    }
    
}

