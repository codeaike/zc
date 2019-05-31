package com.leetcode.tree;

/**
 * 题目： 
 * convert-sorted-list-to-binary-search-tree -- newcoder 40
 * 有序链表转换二叉搜索树 -- leetcode 109
 * 
 * 题目描述：
 * Given a singly linked list where elements are sorted
 * in ascending order, convert it to a height balanced BST.
 * 
 * 中文描述：
 * 
给定一个单链表，其中的元素按升序排序，
将其转换为高度平衡的二叉搜索树。

本题中，一个高度平衡二叉树是指一个二叉树每个节点
 的左右两个子树的高度差的绝对值不超过 1。

示例:

给定的有序链表： [-10, -3, 0, 5, 9],

一个可能的答案是：[0, -3, 9, -10, null, 5], 
它可以表示下面这个高度平衡二叉搜索树：

      0
     / \
   -3   9
   /   /
 -10  5
 * 
 */
public class ConvertSortedListToBinarySearchTree {

	static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}	
	
	
	/**
	 * 思路：
	 * 1、获取链表的中间元素，并以链表中间元素为界，切断链表
	 * 2、根据链表中间元素生成树的根节点，根节点左节点为左链表中间节点，右节点为右链表中间节点
	 * 3、递归生成左右两侧元素 
	 */
    public TreeNode sortedListToBST(ListNode head) {
       if (head == null) {
    	   return null;
       }
       if (head.next == null) {
    	   return genTreeNode(head);
       }
       
       // 获取中间节点，生成树的头节点
       ListNode mid = getMidAndCutList(head);
       TreeNode root = genTreeNode(mid);
       
       root.left = sortedListToBST(head);
       root.right = sortedListToBST(mid.next);
       
       return root;
    }
	
    private TreeNode genTreeNode(ListNode node) {
    	return node == null ? null : new TreeNode(node.val);
    }
    
    // 获取中间节点 并以中间节点为界限切断链表(mid在后半段)
    private ListNode getMidAndCutList(ListNode head) {
    	if (head == null || head.next == null) {
    		return head;
    	}
    	ListNode first = head;
    	ListNode second = head;
    	ListNode pre = new ListNode(0);
    	pre.next = head;
    	while (first != null && first.next != null) {
    		first = first.next.next;
    		second = second.next;
    		pre = pre.next;
    	}
    	// 断链 mid 与前面的链表断开
    	pre.next = null;
    	return second;
    }
    
	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		System.out.println(new ConvertSortedListToBinarySearchTree().sortedListToBST(node1).val);
	}

}
