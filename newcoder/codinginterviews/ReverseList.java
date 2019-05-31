package com.my.test.codinginterviews.list;

import java.util.Stack;
 
/*
 * 题目：
 * 反转链表 -- newcoder 剑指Offer 15
 * 
 * 题目描述:
 * 输入一个链表，反转链表后，输出新链表的表头。
 */
public class ReverseList
{
 
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
    
    /*
     * 思路：
     * 遍历链表，把元素压入栈中，利用栈后进先出特性，遍历栈中元素，重组链表实现翻转
     * 
     * 分析：此方式简单易行，但是需要额外空间，需要遍历两次(一次链表+一次栈遍历)
     */
    public static ListNode reverseListByStack(ListNode head) {
        // 1、判空
        if (head == null) {
            return null;
        }
        
        // 2、链表元素压入栈中
        ListNode curNode = head;
        Stack<ListNode> stack = new Stack<>();
        while (curNode != null) {
            stack.push(curNode);
            curNode = curNode.next;
        }
        
        // 3、重组新链表
        ListNode newNode = stack.pop();
        
        // 遍历方案1
        /*
        ListNode tmpNode = newNode;
        while (!stack.isEmpty()) {
            ListNode curPopNode = stack.pop();
            tmpNode.next = curPopNode;
            tmpNode = curPopNode;
        }
        tmpNode.next = null;
        */
        // 遍历方案2
        while(!stack.isEmpty()) {
            ListNode curPopNode = stack.pop();
            curPopNode.next.next = curPopNode;
            curPopNode.next = null;
        }
        
        return newNode;
    }
    
    /* 
     * 思路：
     * 1、只需遍历一次，遍历时拆开链表，当前元素指向前一个元素
     * 2、使用两个指针，一个指向当前遍历的节点，一个指向新链表的头结点
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        
        // 新链表指针
        ListNode newNode = null;
        // 当前处理的元素
        ListNode cur = head;
        
        while (cur != null) {
            // 暂存下一个元素
            ListNode tmpCurNext = cur.next;
            // 当前元素指向新链表头部，断掉之前的链表
            cur.next = newNode;
            // 更新新链表头结点
            newNode = cur;
            // 更新当前节点为下一个节点
            cur = tmpCurNext;
        }
        
        return newNode;
    }
    
    public static void main(String args[]) {
        // 原链表
        System.out.println("Origin link: " + createTestLinkedList());
        
        // 反转链表
        System.out.println("link reverse: " + reverseList(createTestLinkedList()));
        
    }
    
    private static ListNode createTestLinkedList() {
        ListNode head = new ListNode(0);
        ListNode curNode = head;
        for (int i = 1; i < 10; i++) {
            curNode.next = new ListNode(i);
            curNode = curNode.next;
        }
        return head;
    }
 
}
