package com.leetcode.learn.LinkList;

/**
 *
 */
public class ReverseKGroup {
    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        head.next = l1;
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        int k = 2;
        ListNode result = reverseKGroup(head, k);
        System.out.println(result);
    }

    private static ListNode reverseKGroup(ListNode head, int k) {
        int count = 0;
        ListNode p = head;
        while(p != null && count < k){
            count ++;
            p = p.next;
        }
        // 如果k大于链表长度
        if(count < k){
            return head;
        }
        ListNode newHead = reverse(head, k);
        head.next = reverseKGroup(p, k);
        return newHead;
    }

    // 转置链表
    private static ListNode reverse(ListNode head, int k) {
        if(k == 1){
            return head;
        }
        ListNode curNode = reverse(head.next, k-1);
        head.next.next = head;
        head.next = null;
        return curNode;
    }
}
