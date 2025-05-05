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
    We got a string to parse. Every time we get a number, we create a new node. We also have to take care of the sign. Once we have the Node, we move to the next index to see if it is a (. Every open bracket means a new node and we will have to put the node first tothe left of the node. So we recursively call the function again to create a new node. If we have another ( that means we need to make anothr node as left node. If we get a ) that means we are done processing the node so we move to the next index. If we do not have two consecutive ( this means that the new node that we calculate will be the right child of the previous node. We can recursively call the function again to process this new node and so on until we reach the end of the string. 
    Time. - O(N)
    Space - O(H) because of recursion. For skewed it will be O(N)
 */
class Solution {
    int index = 0;
    public TreeNode str2tree(String s) {
        int n = s.length();
        int num = 0; 
        int sign = 1;

        if(index >= n)
            return null;

        if(s.charAt(index) == '-'){
            sign = -1;
            index++;
        }
        while(index < n && Character.isDigit(s.charAt(index))){
            num = num * 10 + (s.charAt(index) - '0');
            index++;
        }

        TreeNode node = new TreeNode(sign * num);

        if(index < n && s.charAt(index) == '('){
            index++;
            node.left = str2tree(s);
            index++;
        }

        if(index < n && s.charAt(index) == '('){
            index++;
            node.right = str2tree(s);
            index++;
        }

        return node;
    }
}