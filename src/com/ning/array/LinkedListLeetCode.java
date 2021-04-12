package com.ning.array;

import java.util.*;

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
     递归 时间复杂度：o(n+m) 空间复杂度：o(n+m) n,m为两个链表的长度
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
     时间复杂度:o(n) 空间复杂度:o(1)
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
    /**
     time:2021/4/7 141. 环形链表
     给定一个链表，判断链表中是否有环。
     如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
     如果链表中存在环，则返回 true 。 否则，返回 false 。
     时间复杂度:o(n) 空间复杂度:o(1)
     */
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) {
            return false;
        }
        //快慢指针
        ListNode slow = head;
        ListNode fast = head.next;
        while(slow != fast) {
            if(fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
    /**
     time:2021/4/8 160. 相交链表
     编写一个程序，找到两个单链表相交的起始节点。
     时间复杂度:o(n) 空间复杂度:o(1)
     */
    //方法(1)
    public ListNode isInterSection1(ListNode nodeA,ListNode nodeB) {
        int lenA = getLength(nodeA);
        int lenB = getLength(nodeB);
        while(lenA != lenB) {//控制长度相等
            if(lenA < lenB) {
                lenB--;
                nodeB = nodeB.next;
            }else {
                lenA--;
                nodeA = nodeA.next;
            }
        }
        while(nodeA != nodeB) {//长度相等 判断节点是否相等 相等则有交点
            nodeA = nodeA.next;
            nodeB = nodeB.next;
        }
        return nodeA;//返回结果要么是交点 要么是空
    }
    //方法(1)得到节点的长度
    public int getLength(ListNode listNode) {
        int len = 0;
        while(listNode != null) {
            len++;
            listNode = listNode.next;
        }
        return len;
    }

    //方法(2) 双指针 时间复杂度o(n) 空间复杂度o(1)
    public ListNode isInterSection2(ListNode node1,ListNode node2) {
        ListNode temp1 = node1;
        ListNode temp2 = node2;
        while(temp1 != temp2) {
            temp1 = temp1 == null ? node2 : temp1.next;
            temp2 = temp2 == null ? node1 : temp2.next;
        }
        return temp1;
    }

    //方法(3) 哈希 时间复杂度o(n) 空间复杂度o(1)
    public ListNode isInterSection3(ListNode node1,ListNode node2) {
        Set<ListNode> set = new HashSet<>();//用set去重
        while(node1 != null) {//将node1全部放入set中
            set.add(node1);
            node1 = node1.next;
        }
        while(node2 != null) {//遍历node2 如果set包含node2 则有交点 返回node2
            if(set.contains(node2)) {
                return node2;
            }
            node2 = node2.next;
        }
        return null;//没有返回null
    }
    /**
     time:2021/4/9 203. 移除链表元素
     给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
     时间复杂度:o(n) 空间复杂度:o(1)
     */
    public ListNode removeElement(ListNode head,int val) {
        ListNode temp = new ListNode(0);
        temp.next = head;
        ListNode pre = temp;
        ListNode cur = head;
        while(cur != null) {
            if(cur.val == val) {
                pre.next = cur.next;
            }else {
                pre = cur;
            }
            cur = cur.next;
        }
        return temp.next;
    }
    /**
     time:2021/4/9  206. 反转链表
     反转一个单链表。
     时间复杂度:o(n) 空间复杂度:o(1)
     */
    public ListNode reverseLinked(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while(cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
    /**
     time:2021/4/12  234. 回文链表
     请判断一个链表是否为回文链表。
     */
    //方法(1) 使用栈 时间复杂度o(n) 空间复杂度o(n)需要创建额外空间长度为n的栈
    public boolean isPalindRome1(ListNode head) {
        Stack<Integer> stack = new Stack<>();//创建栈
        ListNode temp1 = head;
        while(temp1 != null) {
            stack.push(temp1.val);//进栈
            temp1 = temp1.next;
        }
        ListNode temp2 = head;
        while(temp2 != null) {
            if(stack.pop() != temp2.val) {//出栈判断 不相等直接false
                return false;
            }
            temp2 = temp2.next;//相等 遍历链表
        }
        return true;
    }
    //方法(2)双指针 时间复杂度o(n) 空间复杂度o(n)需要创建额外空间长度为n的数组
    public boolean isPalindRome2(ListNode head) {
        List<Integer> list = new ArrayList<>();//定义数组
        ListNode temp = head;
        while(temp != null) {
            list.add(temp.val);//放到数组里面去
            temp = temp.next;
        }
        int start = 0;//定义头指针
        int end = list.size()-1;//定义尾指针
        while(start < end) {//头指针小于尾指针时(不用相等)
            if(list.get(start) != list.get(end) ) {//如果头尾指针获得的值不相等 return false
                return false;
            }
            start++;//两个指针同时移动
            end--;
        }
        return true;
    }
    //方法(3) 快慢指针 时间复杂度o(n) 空间复杂度o(1)
    public boolean isPalindRome3(ListNode head) {
        if(head == null) {
            return true;
        }
        //得到后半部分的链表
        ListNode halfLinked = getEndHalfLinked(head);
        //反转后半部分的链表 传入的是next的原因是保证了回文中间的单个值不需要判断是否是回文
        ListNode reverseEndHalfLinked = reverseEndHalf(halfLinked.next);
        //定义p1 用于遍历最开始的链表
        ListNode p1 = head;
        //定义p2 用于遍历反转的后半部分的链表
        ListNode p2 = reverseEndHalfLinked;
        while (p2 != null) {//条件为反转后的链表不为空
            if(p1.val != p2.val) {//如果不是回文 则值不相等
                return false;
            }
            //回文的话 同时移动两个节点
            p1 = p1.next;
            p2 = p2.next;
        }
        //还原反转的后半部分的链表
        reverseEndHalf(reverseEndHalfLinked);
        return true;
    }
    //反转后半部分的链表
    private ListNode reverseEndHalf(ListNode nextNode) {
        ListNode pre = null;
        ListNode cur = nextNode;
        while (cur != null) {
            ListNode next = cur.next;//1 2 8 9 10 创建临时节点存放当前节点的下一个节点 cur=1 next=2 cur=8 next=9
            cur.next = pre;//cur的下一个地址为空 cur的下一个地址为pre=1的地址
            pre = cur;//pre指向cur pre=cur=1 pre=cur=8
            cur = next;//cur指向next cur=next=2 cur=next=9
        }
        return pre;
    }
    //得到后半部分的链表
    private ListNode getEndHalfLinked(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
