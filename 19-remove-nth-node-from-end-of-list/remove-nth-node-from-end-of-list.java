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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head.next == null && n == 1){
            return head.next;
        }
        int len = 0; 
        ListNode temp = head;
        while(temp != null){
            len++;
            temp = temp.next;
        }
        System.out.println("len- " + len);
        if(len == n)
            return head.next;

        temp = head;
        ListNode prev = head;
        int count = 0;
        while(temp != null){
            if(count == len - n){
                break;
            }
            count++;
            prev = temp;
            temp = temp.next;
        }
        System.out.println("count- " + count);
        prev.next = temp.next;

        return head;
    }
}*/

/* Two Pass - 
    To remove the nth element fromt he end, we can start by finding the length of the linked list. Once we have the length, the nth element from the end will be (len - n)th element from the begning. 
    So if len == n, we have technically remove the head. So we return head.next.
    We can move temp to len - n length to reach the element we need to remove. 
    If n is 1 we know that we have to remove the last element hence temp.next = temp.next.next; will be out of bounds. hence n ==1 condition is required. 
*/

/*class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
       
        ListNode temp = head;
        int i = 1;
        int length = 0;
        while (temp != null){
            length++;
         temp = temp.next;   
        }
        if(length == n)
            return head.next;
        temp = head;
        while(i < length-n && temp != null){
            i++;
            temp = temp.next;
        }
        if(n==1)
            temp.next = null;
        else
            temp.next = temp.next.next;
        
        return head;
    }
}*/

/*
    One Pass- 
    For One pass we can actually use sliding window concept. We know that the window length is given as n. So in one loop we move n places to get the end of the window.
    Next wehne we move the start pointer along with the end pointer we know that each window is of length n. At the end of the loop when end reaches to the tail.next, the start will be exactly n + 1 element before the end which is what we require in the problem. 
*/

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode temp = new ListNode(-1);
        temp.next = head;
        ListNode end = temp;
        ListNode start = temp;
        int count = 0;
        while (count <= n){
            end = end.next;
            count++;
        }

        while(end != null){
            end = end.next;
            start = start.next;
        }
        
        start.next = start.next.next;

        return temp.next;
    }
}