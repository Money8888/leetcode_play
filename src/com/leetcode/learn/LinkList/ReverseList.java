package com.leetcode.learn.LinkList;

/**
 * 链表翻转
 */
public class ReverseList {

    public static void main(String[] args) {
        ListNode l11 = new ListNode(1);
        ListNode l12 = new ListNode(2);
        ListNode l13 = new ListNode(3);
        ListNode l14 = new ListNode(4);
        ListNode l15 = new ListNode(5);
        ListNode l16 = new ListNode(6);
        ListNode l17 = new ListNode(7);
        ListNode l18 = new ListNode(8);
        ListNode l19 = new ListNode(9);
        ListNode l20 = new ListNode(10);
        l11.next = l12;
        l12.next = l13;
        l13.next = l14;
        l14.next = l15;
        l15.next = l16;
        l16.next = l17;
        l17.next = l18;
        l18.next = l19;
        l19.next = l20;
        l20.next = null;

        ListNode node = reverseList(l11);
        System.out.println(node);
    }

    /**
     * 翻转链表
     * 递归实现
     * @param p
     * @return
     */
    private static ListNode reverseList(ListNode p) {
        if(p == null || p.next == null){
            return p;
        }

        ListNode newNode = reverseList(p.next);

        // 获取尾结点
        ListNode tail = p.next;
        // 尾结点与当前首节点交换指针
        tail.next = p;
        p.next = null;
        return newNode;
    }
}
