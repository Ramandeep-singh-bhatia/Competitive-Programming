import java.util.*;

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
};

class MinimumBinaryTreeDepth {
  public static int findDepth(TreeNode root) {
    // TODO: Write your code here
    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    int depth = 1;
    while(!q.isEmpty()){
      int samelevel = q.size();
      for(int i = 0; i < samelevel; i++){
        TreeNode currentnode = q.poll();
        if(currentnode.left == null && currentnode.right == null)
          return depth;
        if(currentnode.left != null)
          q.add(currentnode.left);
        if(currentnode.right != null)
          q.add(currentnode.right);
      }
      depth++;
    }
    return -1;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(12);
    root.left = new TreeNode(7);
    root.right = new TreeNode(1);
    root.right.left = new TreeNode(10);
    root.right.right = new TreeNode(5);
    System.out.println("Tree Minimum Depth: " + MinimumBinaryTreeDepth.findDepth(root));
    root.left.left = new TreeNode(9);
    root.right.left.left = new TreeNode(11);
    System.out.println("Tree Minimum Depth: " + MinimumBinaryTreeDepth.findDepth(root));
  }
}

