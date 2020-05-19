package com.leetcode.learn.LinkList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 删除倒数第几个节点
 */
public class RemoveNthFromEnd {
    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode l1 = new ListNode(1);
//        ListNode l2 = new ListNode(2);
//        ListNode l3 = new ListNode(3);
//        ListNode l4 = new ListNode(4);
//        ListNode l5 = new ListNode(5);
        head.next = l1;
//        l1.next = l2;
//        l2.next = l3;
//        l3.next = l4;
//        l4.next = l5;
        l1.next = null;
        ListNode result = removeNthFromEnd(head, 1);
        System.out.println(result);
    }

    /**
     * 删除倒数第n个节点
     * @param head
     * @param n
     * @return
     */
    private static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode l = head.next;
        // 存储当前节点的上下节点
        Map<Integer, ListNode> nodeMap = new HashMap<>();
        int num = 0;
        int len = 0;
        while (l != null){
            nodeMap.put(num, l);
            len ++;
            l = l.next;
            num ++;
        }
        // 获取待删除的节点
        int index = len - n;
        if(index < 0 || index >= len){
            return head;
        }
        if(index == 0 && len > 1){
            head.next = nodeMap.get(1);
            return head;
        }else if(len == 1 && index == 0){
            head.next = null;
            return head;
        }
        if(index == len - 1){
            nodeMap.get(len - 2).next = null;
            return head;
        }
        nodeMap.get(index - 1).next = nodeMap.get(index + 1);
        return head;
    }
}
