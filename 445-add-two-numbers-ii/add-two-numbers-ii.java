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
/*class Solution {
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
}*/

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> sl1 = new Stack<>();
        Stack<Integer> sl2 = new Stack<>(); 

        while(l1 != null){
            sl1.push(l1.val);
            l1 = l1.next;
        }

        while(l2 != null){
            sl2.push(l2.val);
            l2 = l2.next;
        }
        int sum = 0, carry = 0;
        ListNode result = null;
        while(!sl2.isEmpty() || !sl1.isEmpty() || carry != 0){
            sum = carry;
            if(!sl1.isEmpty()){
                sum += sl1.pop();
            }

            if(!sl2.isEmpty()){
                sum += sl2.pop();
            }

            ListNode temp = new ListNode(sum % 10);
            temp.next = result;
            result = temp;

            carry = sum / 10;
        }

        return result;
    }
}