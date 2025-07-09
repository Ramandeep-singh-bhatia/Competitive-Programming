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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode rl1 = reverseList(l1);
        ListNode rl2 = reverseList(l2);
        int carry = 0, sum = 0;
        ListNode result = new ListNode();
        while(rl1 != null || rl2 != null){
            if(rl1 == null && rl2 != null){
                sum = rl2.val + carry;
                rl2 = rl2.next;
            }

            if(rl2 == null && rl1 != null){
                sum = rl1.val + carry;
                rl1 = rl1.next;
            }

            if(rl1 != null && rl2 != null){
                sum = rl1.val + rl2.val + carry;
                rl1 = rl1.next;
                rl2 = rl2.next;
            }

            result.val = sum % 10;
            carry = sum / 10;
            ListNode temp = new ListNode(carry);
            temp.next = result;
            result = temp;
        }
        return carry == 0 ? result.next : result;
    }

    private ListNode reverseList(ListNode head){
        ListNode prev = null, temp = null;
        while(head != null){
            temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }
        return prev;
    }
}