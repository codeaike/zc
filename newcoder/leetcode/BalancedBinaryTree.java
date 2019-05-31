package com.leetcode.tree;

/**
 * 题目： 
 * balanced-binary-tree -- newcoder 39
 * 平衡二叉树 -- leetcode 110
 * 
 * 题目描述：
 * 
Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is 
defined as a binary tree in which the depth of the 
two subtrees of every node never differ by more than 1.
 *
 */
public class BalancedBinaryTree {

	static class TreeNode {
		 int val;
		 TreeNode left;
		 TreeNode right;
		 TreeNode(int x) { val = x; }
	}
	
	private boolean isBalance = true;
	
	/**
	 * 思路：
	 * 1、递归判断左右子树是否为平衡树
	 * 2、为平衡树的条件为左右子树高度不大于1
	 */
    public boolean isBalanced(TreeNode root) {
    	if (root == null) {
    		return true;
    	}
    	
    	int left = getDepth(root.left);
    	int right = getDepth(root.right);
    	return Math.abs(left-right) <=1 && isBalanced(root.left) && isBalanced(root.right);
    }
    
    // 求出树的深度
    private int getDepth(TreeNode node) {
    	
    	if (node == null) {
    		return 0;
    	}
    	if (node.left == null && node.right == null) {
    		return 1;
    	}
    	
    	// 子树中深度最大的加本节点深度
    	return Math.max(getDepth(node.left), getDepth(node.right)) + 1;
    }
	
    /**
     * 思路2：
     * 1、递归获取左右子树深度，同时更新全局是否平衡标识
     * 2、执行以上步骤后，返回全局标识
     * 
     * 说明：
     * 本方法性能要高于上面的，但是略显函数职责不单一
     * 
     */
    public boolean isBalancedII(TreeNode root) {
    	getDepthAndUpdateIsBalance(root);
    	return isBalance;
    }
    
    // 没找到不平衡子树则返回树的深度，找到则停止递归寻找并更新isBalance为false
    private int getDepthAndUpdateIsBalance(TreeNode node) {
    	// 之前递归迭代找到了不平衡子树，停止递归寻找
    	if (!isBalance || node == null) {
    		return 0;
    	}
    	
    	int left = getDepthAndUpdateIsBalance(node.left);
    	int right = getDepthAndUpdateIsBalance(node.right);
    	
    	// 找到不平衡的子树，更新isBalance为false
    	if (Math.abs(left - right) > 1) {
    		isBalance = false;
    	}
    	
    	return Math.max(left, right) + 1;
    }
    
	public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode left_left = new TreeNode(1);
        TreeNode left_left_left = new TreeNode(1);
        TreeNode right = new TreeNode(3);
        root.left = left;
        left.left = left_left;
        left_left.left = left_left_left;
        root.right = right;
        System.out.println(new BalancedBinaryTree().isBalanced(root));
	}

}
