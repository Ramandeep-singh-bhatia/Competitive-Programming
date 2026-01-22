/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null)
            return head;
        
        int length = 0;
        ListNode temp = head;
        while(temp != null){
            length++;
            temp = temp.next;
        }

        k = k % length;
        if(k == 0)
            return head;

        ListNode newTail = head;
        for(int i = 0; i < length - k - 1; i++){
            newTail = newTail.next;
        }

        ListNode newHead = newTail.next;

        ListNode oldTail = newHead;
        while(oldTail.next != null){
            oldTail = oldTail.next;
        }

        oldTail.next = head;

        newTail.next = null;

        return newHead;
    }
}