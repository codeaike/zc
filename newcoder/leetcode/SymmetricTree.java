package com.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目：
 * symmetric-tree -- newcoder 48
 * 对称二叉树 -- leetcode 101
 *  
 *  
 * 题目描述：
Given a binary tree, check whether it is a mirror of itself 
(ie, symmetric around its center).

For example, this binary tree is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3

But the following is not:

    1
   / \
  2   2
   \   \
   3    3
 *  
 */
public class SymmetricTree {

	static class TreeNode {
		 int val;
		 TreeNode left;
		 TreeNode right;
		 TreeNode(int x) { val = x; }
	}
	
	/**
	 * 思路： 
	 * 1、递归判断两侧的节点是否是对称的
	 */
    public boolean isSymmetric(TreeNode root) {
    	if (root == null || (root.left == null && root.right == null)) {
    		return true;
    	}
    	return isSymmetric(root.left, root.right);
    }
	
    private boolean isSymmetric(TreeNode left, TreeNode right) {
    	// 两侧都是null, 是对称的
    	if (left == null && right == null) {
    		return true;
    	}
    	// 一侧是null, 不是对称的
    	if (left == null || right == null) {
    		return false;
    	}
    	// 两侧都不是null
    	if (left.val != right.val) {
    		return false;
    	}
    	// 递归判断孩子节点是否是对称的，两侧节点分别比较，依次向中间推进
    	return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }
    
	/**
	 * 思路：
	 * 1、非递归，层次遍历思想，但是 每次把最外侧节点插入队列
	 * 2、每次遍历当前层元素，弹出两个元素，判断这两个元素是否对称 
	 */
    public boolean isSymmetricII(TreeNode root) {
    	if (root == null || (root.left == null && root.right == null)) {
    		return true;
    	}
    	
    	// 层次遍历
    	Queue<TreeNode> queue = new LinkedList<>();
    	queue.offer(root.left);
    	queue.offer(root.right);
    	
    	while (!queue.isEmpty()) {
    		int size = queue.size();
    		// 遍历该层
    		while (size > 0) {
    			// 弹出两侧元素
    			TreeNode node1 = queue.poll();
    			TreeNode node2 = queue.poll();
    			// 更新size
    			size -= 2;
    			
    			// 两元素为null,则继续下一对元素
    			if (node1 == null && node2 == null) {
    				continue;
    			}
    			// 两元素一个为null, 不对称，直接返回
    			if (node1 == null || node2 == null) {
    				return false;
    			}
    			// 两元素都存在, 但是值不相等，则不对称
    			if (node1.val != node2.val) {
    				return false;
    			}
    			// 下一层元素加入队列, 从两侧成对添加到队列中
    			queue.offer(node1.left);
    			queue.offer(node2.right);
    			
    			queue.offer(node1.right);
    			queue.offer(node2.left);
    		}
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
        
        System.out.println(new SymmetricTree().isSymmetric(root));
        System.out.println(new SymmetricTree().isSymmetricII(root));
	}

}
