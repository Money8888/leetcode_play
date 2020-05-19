package com.leetcode.learn.LinkList;

/**
 * 交换相邻的节点
 */
public class SwapPairs {
    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode l12 = new ListNode(4);
        ListNode l13 = new ListNode(5);
        ListNode l2 = new ListNode(1);
        ListNode l21 = new ListNode(3);
        head.next = l12;
        l12.next = l13;
        l13.next = l2;
        l2.next = l21;
        l21.next = null;
        ListNode result = swapPairs(head);
        System.out.println(result);
    }

    /**
     * 递归
     * @param head
     * @return
     */
    private static ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode firstNode = head;
        ListNode secondNode = head.next;

        firstNode.next = swapPairs(secondNode.next);
        secondNode.next = firstNode;

        return secondNode;
    }
}
