package com.leetcode.learn.LinkList;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头
 */
public class AddTwoNumbers {
    public static void main(String[] args) {
        ListNode l11 = new ListNode(1);
        ListNode l12 = new ListNode(9);
        ListNode l13 = new ListNode(9);
        ListNode l14 = new ListNode(9);
        ListNode l15 = new ListNode(9);
        ListNode l16 = new ListNode(9);
        ListNode l17 = new ListNode(9);
        ListNode l18 = new ListNode(9);
        ListNode l19 = new ListNode(9);
        ListNode l20 = new ListNode(9);
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

        ListNode l21 = new ListNode(9);
//        ListNode l22 = new ListNode(6);
//        ListNode l23 = new ListNode(4);
//        l21.next = l22;
//        l22.next = l23;
        l21.next = null;

        ListNode result;
        result = addNewTwoNumbers(l11, l21);
        System.out.println(result);
    }

    /**
     * 不能转化成数值相加，不然会溢出
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //l1, l2逆序
        List list1 = new ArrayList();
        List list2 = new ArrayList();
        while(l1 != null){
            int p = l1.val;
            list1.add(p);
            l1 = l1.next;
        }
        while(l2 != null){
            int q = l2.val;
            list2.add(q);
            l2 = l2.next;
        }
        int num1 = 0;
        int num2 = 0;
        for(int i = 0; i < list1.size(); i++){
            num1 += (int)list1.get(i) * Math.pow(10, i);
        }
        for(int j = 0; j < list2.size(); j++){
            num2 += (int)list2.get(j) * Math.pow(10, j);
        }
        // 求和
        String resultNum = String.valueOf(num1 + num2);
        // 根据位数转化
        List<ListNode> listNodes = new ArrayList<>();
        for(int k = 0; k < resultNum.length(); k++){
           ListNode listNode = new ListNode(Integer.parseInt(String.valueOf(resultNum.charAt(k))));
           listNodes.add(listNode);
        }
        ListNode result = listNodes.get(listNodes.size() - 1);
        for(int m = listNodes.size() - 1; m > 0; m--){
            listNodes.get(m).next = listNodes.get(m - 1);
        }
        listNodes.get(0).next = null;

        return result;
    }

    /**
     * 按位相加，进一位为carry标志位
     * 不需要调换顺序，
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode  addNewTwoNumbers(ListNode l1, ListNode l2) {
        ListNode root = new ListNode(0);
        ListNode cursor = root;
        int carry = 0;
        while(l1 != null || l2 != null || carry != 0) {
            int l1Val = l1 != null ? l1.val : 0;
            int l2Val = l2 != null ? l2.val : 0;
            int sumVal = l1Val + l2Val + carry;
            carry = sumVal / 10;

            ListNode sumNode = new ListNode(sumVal % 10);
            cursor.next = sumNode;
            cursor = sumNode;

            if(l1 != null){
                l1 = l1.next;
            }
            if(l2 != null){
                l2 = l2.next;
            }
        }

        return root.next;
    }
}
