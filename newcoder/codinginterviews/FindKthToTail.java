package com.my.test.codinginterviews.list;

/*
 * 题目：
 * 链表中倒数第k个结点 -- newcoder 剑指Offer 14
 * 
 * 题目描述：
 * 输入一个链表，输出该链表中倒数第k个结点。
 * 
 * 扩展：
 * 链表的中间节点
 * 
 */
public class FindKthToTail
{

    /*
     * 元素的基本定义
     */
    static class ListNode{
        
        private int val;
        
        private ListNode next;
        
        public ListNode (int val) {
            this.val = val;
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
     * 1、两个指针起始指向head，一个先走k步
     * 2、另外一个在一起走，当第一个指向尾节点时，此指针到达倒数第k个
     * 
     */
    public static ListNode findKthToTail(ListNode head, int k) {
        if (head == null || k <= 0) {
            System.out.println("Invalid param");
            return null;
        }
        
        ListNode fast = head;
        // 快的指针先走k步
        for (int i = 0; i < k; i++) {
            if (fast == null) {
                // 链表长度不足k，返回null
                return null;
            }
            fast = fast.next;
        }
        
        ListNode later = head;
        // 继续一起遍历
        while (fast != null) {
            later = later.next;
            fast = fast.next;
        }
        return later;
    }

    /**
     * 思路：
     * 1、两个指针 一个一次走一步 一个一次走两步
     * 2、此函数，当链表元素为偶数时，取中间两个的后一个元素
     */
    public static ListNode findMidNode(ListNode node) {
        if (node == null) {
            return null;
        }
        
        ListNode first = node;
        ListNode second = node;
        
        while (second != null && second.next != null) {
            first = first.next;
            second = second.next.next;
        }
        
        return first;
    }
    
    /**
     * 思路：
     * 1、两个指针 一个一次走一步 一个一次走两步
     * 2、此函数，当链表元素为偶数时，取中间两个的前一个元素
     */
    public static ListNode findMidPreNode(ListNode node) {
        if (node == null) {
            return null;
        }
        
        ListNode first = node;
        ListNode second = node;
        
        while (second != null && second.next != null && second.next.next != null) {
            first = first.next;
            second = second.next.next;
        }
        
        return first;
    }
    
    
    public static void main(String args[]) {
        // 原链表
        System.out.println("Origin link: " + createTestLinkedList(9));
        
        // 打印链表后2个元素
        System.out.println("print last 2 data: " + findKthToTail(createTestLinkedList(9), 2));
        
        System.out.println("find mid last one: " + findMidNode(createTestLinkedList(8)));
        
        System.out.println("find mid pre one: " + findMidPreNode(createTestLinkedList(8)));
    }
    
    private static ListNode createTestLinkedList(int n) {
        ListNode head = new ListNode(0);
        ListNode curNode = head;
        for (int i = 1; i < n; i++) {
            curNode.next = new ListNode(i);
            curNode = curNode.next;
        }
        return head;
    }

}

