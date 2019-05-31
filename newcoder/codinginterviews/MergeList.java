package com.my.test.codinginterviews.list;

/*
 * 题目：
 * 合并两个排序的链表-- newcoder 剑指Offer 16
 * 
 * 题目描述:
 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 */
public class MergeList
{
 
    static class ListNode{
        private int val;
        
        private ListNode next;
        
        public ListNode (int value) {
            this.val = value;
        }
        
        @Override
        public String toString() {
            if (this.next == null) {
                return String.valueOf(this.val);
            }
            return this.val + "->" + this.next.toString();
        }
        
    }
    
    /**
     * 思路：
     * 比较两链表的头结点，小的为新链表的头结点，分别遍历两个链表的下一个节点，小的作为新链表的下一个节点
     */
    public static ListNode mergeList(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null) {
            return null;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1 == null) {
            return list2;
        }
        
        // 新链表头结点
        ListNode newHead;
        // 两个链表的指针
        ListNode curNode1 = list1;
        ListNode curNode2 = list2;
        // 两个链表中头结点小的为新链表的头结点
        if (list1.val <= list2.val) {
            newHead = list1;
            curNode1 = list1.next;
        }
        else {
            newHead = list2;
            curNode2 = list2.next;
        }
        
        ListNode tail = newHead;
        // 遍历两个链表
        while (curNode1 != null && curNode2 != null) {
            if (curNode1.val <= curNode2.val) {
                tail.next = curNode1;
                curNode1 = curNode1.next;
            }
            else {
                tail.next = curNode2;
                curNode2 = curNode2.next;
            }
            // 更新新链表指针
            tail = tail.next;
        }
        
        // 加上余下的后续链表
        if (curNode1 != null) {
            tail.next = curNode1;
        }
        
        if (curNode2 != null) {
            tail.next = curNode2;
        }
        
        return newHead;
    }
    
    /*
    * 思路：
    * 1、更加简洁的方法，使用pre节点，虚拟新链表头节点的前一个元素
    * 2、比较两链表的头结点，小的为新链表的头结点，分别遍历两个链表的下一个节点，小的作为新链表的下一个节点
    */
    public ListNode mergeListII(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null) {
            return null;
        }
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        if (list1 != null) {
            cur.next = list1;
        }
        if (list2 != null) {
            cur.next = list2;
        }
        return pre.next;
    }
 
    public static void main(String args[]) {
        // 原链表
        ListNode head1 = createTestLinkedList(8);
        System.out.println("Origin link1: " + head1);
        
        ListNode head2 = createTestLinkedList(9);
        System.out.println("Origin link2: " + head2);
        
        // 合并两个有序链表
        System.out.println("Merged link: " + mergeList(head1, head2));
    }
    
    private static ListNode createTestLinkedList(int len) {
        ListNode head = new ListNode(0);
        ListNode curNode = head;
        for (int i = 1; i < len; i++) {
            curNode.next = new ListNode(i);
            curNode = curNode.next;
        }
        return head;
    }
 
}
