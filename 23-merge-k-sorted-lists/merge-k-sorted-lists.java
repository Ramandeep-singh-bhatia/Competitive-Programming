/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }

/*
    Brute Force - 
    Put all the node value into an array list
    Sort the arrayList
    Put the arraylist value into a linkedlist and return

    Time - O(nlogn)
    Space - O(n)
*/

public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        ArrayList<Integer> nodes = new ArrayList<>();
        ListNode head = new ListNode(0);
        ListNode point = head;
        for (ListNode l : lists) {
            while (l != null) {
                nodes.add(l.val);
                l = l.next;
            }
        }
        Collections.sort(nodes);
        for (int x : nodes) {
            point.next = new ListNode(x);
            point = point.next;
        }
        return head.next;
    }
}

 /*
    Below are two implementation of merge k sorted lists.
    one is Merge list one by one where we take the list and combine with the next one and keep doing that till it is merged to the last one. This will have a time complexity of O(Nk) where N is the number of nodes in the whole list and k being the list length. 
    n is the number of nodes in 1 list and with k list the number of nodex would be n*K which on big O notation is mentioned as N i.e. - O(Nk) else O(nk^2)

    Second - Merge List divide and conquere - A better approach will be to take two list combine and move the next two. After 1 iteration the number of List in the array is reduced to half hence making it O(Nlogk) - O(n*klogk)

    We take an interval which is initially 1 meaning that we will compare the list next to each other. As we move to the next iteration we multiply the interval by 2 making it compare the list node at 0, 2 then 3, 5 merge and so on. 
 */

/*class Solution {
    public ListNode mergeKLists(ListNode[] lists) {

        //Merge List Divide and Conquere
        int len = lists.length;
        if(len == 0 || lists == null)
            return null;
        int interval = 1;
        while(interval < len){
            for(int i = 0; i < len - interval; i += interval * 2){
                lists[i] = mergeSortedList(lists[i], lists[i + interval]);
            }
            interval *= 2;
        }
        return lists[0];

        // Merge list one by one
        /*int n = lists.length;
        if(n == 0 || lists == null)
            return null;
        ListNode mergedList = lists[0];
        for(int i = 1; i < n; i++){
            mergedList = mergeSortedList(mergedList, lists[i]);
        }

        return mergedList;*/
    /*}

    private ListNode mergeSortedList(ListNode l1, ListNode l2){
        if(l1 == null)
            return l2;
        else if(l2 == null)
            return l1;
        else if(l1 == null && l2 == null)
            return null;
        
        ListNode head = new ListNode(-1);
        ListNode mergedList = head;

        while(l1 != null && l2 != null){
            if(l1.val <= l2.val){
                mergedList.next = l1;
                l1 = l1.next;
            } else {
                mergedList.next = l2;
                l2 = l2.next;
            }

            mergedList = mergedList.next;
        }

        mergedList.next = (l1 == null) ? l2 : l1;

        return head.next;
    }
}*/