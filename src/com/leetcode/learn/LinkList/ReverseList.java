package com.leetcode.learn.LinkList;

import java.util.List;

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

        //ListNode node = reverseList(l11);
        ListNode newNode = reverseListByIter(l11);
        //ListNode newNode = reverseK(l11,4);
        //ListNode newNode = reverseBetween(l11,2, 4);
        //System.out.println(node);
        System.out.println(newNode);
    }

    /**
     * 反转链表
     * 迭代实现
     * @param p
     * @return
     */
    private static ListNode reverseListByIter(ListNode p){
        if(p == null || p.next == null){
            return p;
        }
        // work为工作指针，跟随p的变化而变化

        ListNode pre = null;
        ListNode work = p;
        while (work != null){
            p = p.next;
            // 更改指针指向，从后面的节点(work)指向步调慢的前驱节点(pre)
            work.next = pre;
            // 保证跳出循环后，pre一定是尾结点
            pre = work;
            work = p;
        }

        // 已经遍历到p，work都为null，此时pre为指向最后一个节点(逆置后的头结点)
        p = pre;
        return p;
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

        // 递归的含义是逆置p.next为头结点的链表，并返回新链表的头指针
        ListNode newNode = reverseList(p.next);

        // 此时p还是指向没逆置的第二个节点的,即该节点为p.next
        // 但逆置后第二个节点变成了倒数第二个节点，p此时是倒数第一个节点
        // 倒数第二个节点该指向p 所以是p.next.next = p
        // p为尾结点后当然p的指针域为null，所以p.next = null
        // 此时一定是newNode为新的头结点
        p.next.next = p;
        p.next = null;
        return newNode;
    }

    /**
     * 反转链表的前k个节点
     */
    // 用于记录第k+1个节点的位置
    private static ListNode pNext = null;

    private static ListNode reverseK(ListNode p, int k){
        if(k == 1){
            pNext = p.next;
            return p;
        }

        ListNode newNode = reverseK(p.next, k - 1);
        p.next.next = p;
        p.next = pNext;
        return newNode;
    }

    // 逆置[m,n]范围内的节点
    private static ListNode reverseBetween(ListNode p, int m, int n){
        if(m == 1){
            return reverseK(p, n);
        }
        // 相当于往前推进了一步
        p.next = reverseBetween(p.next, m - 1, n - 1);
        return p;
    }
}
