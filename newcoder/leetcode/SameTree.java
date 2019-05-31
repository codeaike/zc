package com.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目：
 * same-tree -- newcoder 49
 * 相同的树 -- leetcode 100
 *  
 *  
 * 题目描述：
Given two binary trees, write a function to check if they are equal or not.

Two binary trees are considered equal 
if they are structurally identical 
and the nodes have the same value.
 *  
 */
public class SameTree {

	static class TreeNode {
		 int val;
		 TreeNode left;
		 TreeNode right;
		 TreeNode(int x) { val = x; }
	}
	
	/**
	 * 思路： 
	 * 1、递归判断两侧的节点是否是相等的
	 */
    public boolean isSameTree(TreeNode p, TreeNode q) {
    	// 两侧都是null, 是相等
    	if (p == null && q == null) {
    		return true;
    	}
    	// 一侧是null, 不是相等的
    	if (p == null || q == null) {
    		return false;
    	}
    	// 两侧都不是null，但是值不相等
    	if (p.val != q.val) {
    		return false;
    	}
    	// 递归判断孩子节点是否是相等的
    	return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
    
	/**
	 * 思路：
	 * 1、非递归，层次遍历思想
	 * 2、利用两个队列遍历两个树 
	 */
    public boolean isSameTreeII(TreeNode p, TreeNode q) {
    	if (p == null && q == null) {
    		return true;
    	}
    	
    	// 层次遍历
    	Queue<TreeNode> queue1 = new LinkedList<>();
       	Queue<TreeNode> queue2 = new LinkedList<>();
    	
    	queue1.offer(p);
    	queue2.offer(q);
    	
    	
    	TreeNode cur1;
    	TreeNode cur2;
    	// 同时处理两个队列
    	while (!queue1.isEmpty() && !queue2.isEmpty()) {
    		cur1 = queue1.poll();
    		cur2 = queue2.poll();
    		
    		// 都为空，则继续
    		if (cur1 == null && cur2 == null) {
    			continue;
    		}
    		
    		// 一个为空，返回false
    		if (cur1 == null || cur2 == null) {
    			return false;
    		}
    		
    		// 不为空，值不相等，返回false
    		if (cur1.val != cur2.val) {
    			return false;
    		}
    		
    		// 本元素比较OK，添加孩子节点到队列中
    		queue1.offer(cur1.left);
    		queue1.offer(cur1.right);
    		
    		queue2.offer(cur2.left);
    		queue2.offer(cur2.right);
    	}
    	
    	return true;
    }
    
	public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode left_left = new TreeNode(3);
        TreeNode right = new TreeNode(2);
        TreeNode right_right = new TreeNode(3);
        root.left = left;
        left.left = left_left;
        root.right = right;
        right.right = right_right;
        
        System.out.println(new SameTree().isSameTree(root, root));
        System.out.println(new SameTree().isSameTreeII(root, root));
	}

}
