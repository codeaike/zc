package com.codinginterviews.list;

import java.util.Stack;

/*
 * 题目：
 * 两个链表的第一个公共结点 -- newcoder 剑指Offer 36
 * 
 * 题目描述：
 * 输入两个链表，找出它们的第一个公共结点。
 */
public class FindFirstCommonNode {

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
     * 获取单链表的第一个交点
     * 如果两个链表相交，因为链表的指针只有一个，所以相交后，后面的节点必定重合，因此相交只能是Y型，而不是X型
     * 
     * node0->node1->node5->node6
     * node2->node3->node5->node6
     * 
     * 思路：
     * 分别把两个链表压入栈中，弹出元素，最后一个相同的元素即为第一个交点
     * 
     * 分析：此方式简单易行，需要遍历两次(两个链表+一次栈寻找);但是需要借助额外空间，需要优化
     */
    public static ListNode findFirstCommonNode(ListNode pHead1, ListNode pHead2) {
    	if(pHead1 == null || pHead2 == null) {
    		return null;
    	}
    	Stack<ListNode> stack1 = new Stack<>();
    	while (pHead1 != null) {
    		stack1.push(pHead1);
    		pHead1 = pHead1.next;
    	}
    	
    	Stack<ListNode> stack2 = new Stack<>();
    	while (pHead2 != null) {
    		stack2.push(pHead2);
    		pHead2 = pHead2.next;
    	}
    	
    	ListNode commonNode = null;
    	
    	while (!stack1.empty() && !stack2.empty()) {
    		ListNode pop1 = stack1.pop();
    		ListNode pop2 = stack2.pop();
    		if ( pop1 == pop2) {
    			commonNode = pop1;
    		} else {
    			break;
    		}
    	}
    	
    	return commonNode;
    }
    
    /*
     * 获取单链表的第一个交点
     * 如果两个链表相交，因为链表的指针只有一个，所以相交后，后面的节点必定重合，因此相交只能是Y型，而不是X型
     * 
     * node0->node1->node5->node6
     * node2->node5->node6
     * 
     * 思路：
     * 1、分别读取两个链表长度，然后用两个指针分别指向两个链表
     * 2、长的先走lenMax-lenMin步，元素相等时，则找到第一个相交节点
     * 
     * 分析：此方式简单易行，需要遍历两次(两个链表 + 一次寻找)
     */
    public static ListNode findFirstCommonNodeII(ListNode pHead1, ListNode pHead2) {
    	if (pHead1 == null || pHead2 == null) {
    		return null;
    	}
    	
    	// 分别遍历获取长度
    	int len1 = 0;
    	ListNode tmpNode1 = pHead1;
    	while (tmpNode1 != null) {
    		len1++;
    		tmpNode1 = tmpNode1.next;
    	}
    	
    	int len2 = 0;
    	ListNode tmpNode2 = pHead2;
    	while (tmpNode2 != null) {
    		len2++;
    		tmpNode2 = tmpNode2.next;
    	}
    	
    	// 赋值快慢指针
    	ListNode fast = null;
    	ListNode low = null;
    	int num = 0;
    	if (len1 >= len2) {
    		fast = pHead1;
    		low = pHead2;
    		num = len1 - len2;
    	} else {
    		fast = pHead2;
    		low = pHead1;
    		num = len2 - len1;
    	}
    	
    	// fast 指针 先走num步
		for (int i=0; i< num; i++) {
			fast = fast.next;
		}
    	
    	while (fast != null && low != null) {
    		if (fast == low) {
    			return fast;
    		}
    		fast = fast.next;
    		low = low.next;
    	}
		
    	return null; 
    }
    
    /**
     * 思路(摘自牛客网)：
     * 1、长度相同有公共结点，第一次就遍历到；没有公共结点，走到尾部NULL相遇，返回NULL
	 * 2、长度不同有公共结点，第一遍差值就出来了，第二遍一起到公共结点；没有公共，一起到结尾NULL。 
     */
    public static ListNode findFirstCommonNodeIII(ListNode pHead1, ListNode pHead2) {
    	if (pHead1 == null || pHead2 == null) {
    		return null;
    	}
    	
    	ListNode p1 = pHead1;
    	ListNode p2 = pHead2;
    	
    	while (p1 != p2) {
    		p1 = p1 == null ? pHead2 : p1.next;
    		p2 = p2 == null ? pHead1 : p2.next;
    	}
    	
    	return p1;
    }
    
    public static void main(String args[]) {
    	
        ListNode node1 = new ListNode(11);
        ListNode node2 = new ListNode(12);
        node1.next = node2;
        
    	ListNode head1 = createTestLinkedList(7, node1);
    	ListNode head2 = createTestLinkedList(8, node1);
        		
        
        // 链表相交
        System.out.println("link common node: " + findFirstCommonNode(head1, head2));

        // 链表相交
        System.out.println("link common node: " + findFirstCommonNodeII(head1, head2));

        // 链表相交
        System.out.println("link common node: " + findFirstCommonNodeIII(head1, head2));

        
    }
    
    private static ListNode createTestLinkedList(int n, ListNode addNode) {
        ListNode head = new ListNode(0);
        ListNode curNode = head;
        for (int i = 1; i < n; i++) {
            curNode.next = new ListNode(i);
            curNode = curNode.next;
        }
        curNode.next = addNode;
        return head;
    }

}
