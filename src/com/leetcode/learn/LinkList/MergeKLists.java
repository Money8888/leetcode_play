package com.leetcode.learn.LinkList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.leetcode.learn.LinkList.MergeTwoLists.mergeTwoLists;

/**
 * 合并k个有序链表
 */
public class MergeKLists {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l12 = new ListNode(4);
        ListNode l13 = new ListNode(5);
        ListNode l2 = new ListNode(1);
        ListNode l21 = new ListNode(3);
        ListNode l22 = new ListNode(4);
        ListNode l3 = new ListNode(2);
        ListNode l31 = new ListNode(6);
        l1.next = l12;
        l12.next = l13;
        l13.next = null;
        l2.next = l21;
        l21.next = l22;
        l22.next = null;
        l3.next = l31;
        l31.next = null;
        ListNode[] lists = new ListNode[]{l1, l2, l3};
        //ListNode result = mergeKLists(lists);
        // 分治算法
        ListNode result = newMergeKLists(lists);
        System.out.println(result);
    }

    // 分治算法
    private static ListNode newMergeKLists(ListNode[] lists) {
        if(lists.length == 0){
            return null;
        }
        if(lists.length == 1){
            return lists[0];
        }
        if(lists.length == 2){
            return mergeTwoLists(lists[0], lists[1]);
        }

        int mid = lists.length / 2;
        ListNode[] l1 = new ListNode[mid];
        for(int i = 0; i < mid; i++){
            l1[i] = lists[i];
        }
        ListNode[] l2 = new ListNode[lists.length-mid];
        for(int i = mid,j=0; i < lists.length; i++,j++){
            l2[j] = lists[i];
        }
        return mergeTwoLists(mergeKLists(l1),mergeKLists(l2));
    }

    // 合并多个有序链表
    private static ListNode mergeKLists(ListNode[] lists) {
        ListNode result = new ListNode(0);
        List<ListNode> nodeList = new ArrayList<>();
        for(int i = 0; i < lists.length; i++){
            while(lists[i] != null){
                nodeList.add(lists[i]);
                lists[i] = lists[i].next;
            }
        }
        nodeList.stream().map(e -> e.next = null).collect(Collectors.toList());
        nodeList.sort(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                if (o1.val < o2.val) {
                    return -1;
                } else if (o1.val > o2.val) {
                    return 1;
                }
                return 0;
            }
        });
        // 尾插法插入节点
        ListNode r = result;
        for (ListNode listNode : nodeList) {
            r.next = listNode;
            // 指向终端节点
            r = listNode;
        }
        r.next = null;
        return result.next;
    }
}
