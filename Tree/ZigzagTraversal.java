import java.util.*;

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
};

class ZigzagTraversal {
  public static List<List<Integer>> traverse(TreeNode root) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    int level = 1; 
    while(!q.isEmpty()){
    	List<Integer> l = new ArrayList<>();
	int samelevel = q.size();
	for(int i = 0; i < samelevel; i++){
    		TreeNode currentnode = q.poll();
        	if(level%2 == 1){
			l.add(currentnode.val);
		}else
			l.add(0,currentnode.val);

		if(currentnode.left != null)
			q.add(currentnode.left);

		if(currentnode.right != null)
			q.add(currentnode.right);
		
	}
	level++;
	result.add(l);
    }
	
    return result;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(12);
    root.left = new TreeNode(7);
    root.right = new TreeNode(1);
    root.left.left = new TreeNode(9);
    root.right.left = new TreeNode(10);
    root.right.right = new TreeNode(5);
    root.right.left.left = new TreeNode(20);
    root.right.left.right = new TreeNode(17);
    List<List<Integer>> result = ZigzagTraversal.traverse(root);
    System.out.println("Zigzag traversal: " + result);
  }
}