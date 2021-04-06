package com.ning.array;

public class LinkedListLeetCode {
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    /**
     time:2021/4/2  21. 合并两个有序链表
     将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     递归
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) {
            return l2;
        }else if(l2 == null) {
            return l1;
        }else if(l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next,l2);
            return l1;
        }else {
            l2.next = mergeTwoLists(l2.next,l1);
            return l2;
        }
    }
    /**
     time:2021/4/6  83. 删除排序链表中的重复元素
     存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
     返回同样按升序排列的结果链表。
     */
    public ListNode deleteDuplicationElement(ListNode listNode) {
        if(listNode == null) {
            return listNode;
        }
        ListNode cur = listNode;
        while(cur.next != null) {
            if(cur.val == cur.next.val) {
                cur.next = cur.next.next;
            }else {
                cur = cur.next;
            }
        }
        return listNode;
    }
}
