package com.my.test.codinginterviews;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 题目：
 * 从尾到头打印链表 -- 剑指Offer 3
 * 
 * 题目描述：
 * 输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
 */
public class PrintListFromTailToHead
{
    
    public class ListNode {
        int val;
        ListNode next = null;
        ListNode(int val) {
            this.val = val;
        }
    }
   /*
    * 思路：
    * 遍历链表，把元素压入栈中，利用栈后进先出特性，遍历栈中元素，逐个打印
    * 
    * 分析：不要使用链表反转后打印，会破坏
    */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> retList = new ArrayList<>();
        
        if (listNode == null) {
            return retList;
        }
        
        Stack<ListNode> stack = new Stack<>();
        while (listNode != null) {
            stack.push(listNode);
            listNode = listNode.next;
        }
        
        while(!stack.empty()) {
            retList.add(stack.pop().val);
        }
        
        return retList;
    }
    
}
