package com.codinginterviews.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 题目：
 * 二叉搜索树的第k个结点 -- newcoder 剑指Offer 62
 * 
 * 题目描述：
 * 给定一棵二叉搜索树，请找出其中的第k小的结点。
 * 例如， （5，3，7，2，4，6，8）    中，按结点数值大小顺序第三小结点的值为4。
 */
public class KthNode {
	public class TreeNode {
	    int val = 0;
	    TreeNode left = null;
	    TreeNode right = null;

	    public TreeNode(int val) {
	        this.val = val;
	    }
	}
	
	/**
	 * 思路：
	 * 1、二叉搜索树中序遍历的结果是排序的
	 * 2、递归遍历，遍历结果的第k个元素，即为第k小的元素
	 */
    TreeNode kthNode(TreeNode pRoot, int k)
    {
        if (pRoot == null || k <= 0) {
        	return null;
        }
        
        List<TreeNode> cache = new ArrayList<>();
        
        kthNodeHelper(pRoot, k, cache);
        
        return k <= cache.size() ? cache.get(k-1) : null;
    }
    
    void kthNodeHelper(TreeNode node, int k, List<TreeNode> cache) {
    	if (node == null) {
    		return;
    	}
    	kthNodeHelper(node.left, k, cache);
    	cache.add(node);
    	if (cache.size() == k) {
    		return;
    	}
    	kthNodeHelper(node.right, k, cache);
    }
    
    
    /**
     * 思路：
     * 1、非递归中序遍历，遍历到第k个元素停止遍历
     */
    TreeNode kthNodeII(TreeNode pRoot, int k) {
        if (pRoot == null || k <= 0) {
        	return null;
        }
        
        List<TreeNode> cache = new ArrayList<>();
        
        // 利用栈实现中序遍历
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curNode = pRoot;
        
        while (!stack.isEmpty() || curNode != null) {
        	// 获取最左下方的节点
        	while (curNode != null) {
        		stack.push(curNode);
        		curNode = curNode.left;
        	}
        	
        	// 弹出队列中元素
        	curNode = stack.pop();
        	
        	cache.add(curNode);
        	if (cache.size() == k) {
        		break; 
        	}
        	
        	// curNode指向右节点
        	curNode = curNode.right;
        }
        
        return k <= cache.size() ? cache.get(k-1) : null;
    }
    
}
