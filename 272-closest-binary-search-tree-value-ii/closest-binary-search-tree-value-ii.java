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
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> result = new ArrayList<>();
        List<Integer> distance = new ArrayList<>();
        inorder(root,distance);

        distance.sort((a,b) -> Double.compare(Math.abs(a - target) , Math.abs(b - target)));

        for(int i = 0; i < k; i++){
            result.add(distance.get(i));
        }

        return result;
    }

    private void inorder(TreeNode node, List<Integer> distance){
        if(node == null)
            return;

        inorder(node.left, distance);
        distance.add(node.val);
        inorder(node.right, distance);
    }
}*/


class Solution {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> result = new ArrayList<>();
        List<Integer> sortedValues = new ArrayList<>();
        inorder(root, sortedValues);

        int index = binarySearch(sortedValues, target);

        int left = index - 1;
        int right = index;

        while(result.size() < k){
            if(left < 0){
                result.add(sortedValues.get(right));
                right++;
            } else if(right >= sortedValues.size()) {
                result.add(sortedValues.get(left));
                left--;
            } else {
                double leftDist = Math.abs(sortedValues.get(left) - target);
                double rightDist = Math.abs(sortedValues.get(right) - target);

                if(leftDist <= rightDist){
                    result.add(sortedValues.get(left));
                    left--;
                } else {
                    result.add(sortedValues.get(right));
                    right++;
                }
            }

        }

        return result;
    }

    private int binarySearch(List<Integer> l, double target){
        int left = 0;
        int right = l.size();

        while(left < right) {
            int mid = left + (right - left) / 2;
            if(l.get(mid) < target){
                left = mid + 1;
            } else
                right = mid;
        }

        return left;
    }

    private void inorder(TreeNode node, List<Integer> sortedValues){
        if(node == null)
            return;

        inorder(node.left, sortedValues);
        sortedValues.add(node.val);
        inorder(node.right, sortedValues);
    }
}