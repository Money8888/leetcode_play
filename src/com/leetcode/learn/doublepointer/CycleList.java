package com.leetcode.learn.doublepointer;

/**
 * 环形链表问题
 *  判断链表是否含环
 *  返回环的起点
 */
public class CycleList {
    public static void main(String[] args) {
        ListNode l11 = new ListNode(1);
        ListNode l12 = new ListNode(2);
        ListNode l13 = new ListNode(3);
        ListNode l14 = new ListNode(4);
        l11.next = l12;
        l12.next = l13;
        l13.next = l14;
        l14.next = l12;
        boolean flag = isHasCycle(l11);
        System.out.println(flag);
        System.out.println(cycleStart(l11));
    }

    /**
     * 是否含环
     * @param
     */
    private static boolean isHasCycle(ListNode head) {
        // 声明两个指针，一快一慢
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast ==slow){
                return true;
            }
        }
        return false;
    }

    private static ListNode cycleStart(ListNode head){
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast ==slow){
                break;
            }
        }
        // 此时快慢指针相遇
        // 让一个指针返回起点
        slow = head;
        while (fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

}
