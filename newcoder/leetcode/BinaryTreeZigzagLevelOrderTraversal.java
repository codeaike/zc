package com.leetcode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目： 
 * binary-tree-zigzag-level-order-traversal -- newcoder 46 
 * 二叉树的锯齿形层次遍历 -- leetcode 103
 * 
 * 题目描述：
 * 
Given a binary tree, return the zigzag level 
order traversal of its nodes' values. 
(ie, from left to right, then right to left 
for the next level and alternate between).

For example:
Given binary tree{3,9,20,#,#,15,7},

    3
   / \
  9  20
    /  \
   15   7

return its zigzag level order traversal as:

[
  [3],
  [20,9],
  [15,7]
]
 *
 */
public class BinaryTreeZigzagLevelOrderTraversal {
	static class TreeNode {
		 int val;
		 TreeNode left;
		 TreeNode right;
		 TreeNode(int x) { val = x; }
	}

	/**
	 * 层次遍历思路： 
	 * 1、层次遍历的思想(类似BFS思想)
	 * 2、初始化一个队列，初始元素为root节点
	 * 3、遍历队列中的元素，每次遍历先获取当前队列的size,
	 *    依次把队列中的元素出队列，出队列的元素个数为size个,
	 *    如果出队列的元素有左右孩子节点，孩子节点入队列
	 * 
	 * 本题思路：
	 * 锯齿形遍历，仅需要额外维护一个标识位，
	 * 标识当前层的元素遍历顺序，需要反转则反转当前层元素即可
	 * 
	 * 方式1、始终正序添加元素到list缓存，该层遍历完毕，
	 *       反转该list(反转list额外增加一次遍历list,复杂性)
	 * 方式2、添加元素时判断该层是否需要倒序，需要倒序，每次添加
	 *       新元素到list头部
	 */
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
    	ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
    	if (root == null) {
    		return ret;
    	}
    	
    	// 初始化一个队列
    	Queue<TreeNode> queue = new LinkedList<>();
    	queue.offer(root);
    	
    	// 0代表需要倒序, 1代表正序
    	int needReverse = 1;
    	
    	TreeNode curNode;
    	
    	while (!queue.isEmpty()) {
    		// 缓存当前层元素的值
    		ArrayList<Integer> cur = new ArrayList<>();
    		// 当前层元素个数
    		int size = queue.size();
    		// 遍历当前层的元素
    		for (int i=0; i<size; i++) {
    			curNode = queue.poll();
    			// 添加当前值到缓存
    			cur.add(curNode.val);
    			// 添加下层元素到队列
    			// 需要倒序打印 则先添加右孩子再添加左孩子
				if (curNode.left != null) {
					queue.offer(curNode.left);
				}
				if (curNode.right != null) {
					queue.add(curNode.right);
				}
    		}
    		
    		// 添加当前层遍历序列到ret & 更新倒序遍历标识位
    		if (needReverse == 0) {
    			Collections.reverse(cur);
    		}
    		ret.add(cur);
    		needReverse = 1 - needReverse;
    	}
    	
    	return ret;
    }
	
    public ArrayList<ArrayList<Integer>> zigzagLevelOrderII(TreeNode root) {
    	ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
    	if (root == null) {
    		return ret;
    	}
    	
    	// 初始化一个队列
    	Queue<TreeNode> queue = new LinkedList<>();
    	queue.offer(root);
    	
    	// 0代表需要倒序, 1代表正序
    	int needReverse = 1;
    	
    	TreeNode curNode;
    	
    	while (!queue.isEmpty()) {
    		// 缓存当前层元素的值
    		ArrayList<Integer> cur = new ArrayList<>();
    		// 当前层元素个数
    		int size = queue.size();
    		// 遍历当前层的元素
    		for (int i=0; i<size; i++) {
    			curNode = queue.poll();
    			// 添加当前值到缓存，需要倒序则每次添加元素链表头部
    			if (needReverse == 0) {
    				cur.add(0, curNode.val);
    			} else {
    				cur.add(curNode.val);
    			}
    			// 添加下层元素到队列
    			// 需要倒序打印 则先添加右孩子再添加左孩子
				if (curNode.left != null) {
					queue.offer(curNode.left);
				}
				if (curNode.right != null) {
					queue.add(curNode.right);
				}
    		}
    		
    		// 添加当前层遍历序列到ret & 更新倒序遍历标识位
    		ret.add(cur);
    		needReverse = 1 - needReverse;
    	}
    	
    	return ret;
    }
	
	public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode left_left = new TreeNode(4);
        TreeNode left_left_left = new TreeNode(5);
        TreeNode right = new TreeNode(3);
        TreeNode right_right = new TreeNode(6);
        right.right = right_right;
        root.left = left;
        left.left = left_left;
        left_left.left = left_left_left;
        root.right = right;
        System.out.println(new BinaryTreeZigzagLevelOrderTraversal().zigzagLevelOrder(root));
        System.out.println(new BinaryTreeZigzagLevelOrderTraversal().zigzagLevelOrderII(root));
	}

}
