package com.leetcode.learn.LinkList;

/**
 * 合并链表
 * 链表有序
 */
public class MergeTwoLists {
    public static void main(String[] args) {
        //ListNode head = new ListNode(0);
        ListNode l1 = new ListNode(1);
        ListNode l12 = new ListNode(2);
        ListNode l13 = new ListNode(4);
        ListNode l2 = new ListNode(1);
        ListNode l21 = new ListNode(3);
        ListNode l22 = new ListNode(4);
        //head.next = l1;
        l1.next = l12;
        l12.next = l13;
        l13.next = null;
        l2.next = l21;
        l21.next = l22;
        l22.next = null;
        ListNode result = mergeTwoLists(l1, l2);
        System.out.println(result);
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode curNode = result;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                curNode.next = l1;
                curNode = curNode.next;
                l1 = l1.next;
            }else {
                curNode.next = l2;
                curNode = curNode.next;
                l2 = l2.next;
            }
        }

        if(l1 == null){
            curNode.next = l2;
        }
        if(l2 == null){
            curNode.next = l1;
        }
        return result.next;
    }
}
