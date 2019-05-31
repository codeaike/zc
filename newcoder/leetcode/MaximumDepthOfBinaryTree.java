package com.leetcode.tree;

/**
 * 题目： 
 * maximum-depth-of-binary-tree -- newcoder 45 
 * 二叉树的最大深度 -- leetcode 104
 * 
 * 题目描述：
 * 
Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along 
the longest path from the root node down to 
the farthest leaf node.
 *
 */
public class MaximumDepthOfBinaryTree {
	static class TreeNode {
		 int val;
		 TreeNode left;
		 TreeNode right;
		 TreeNode(int x) { val = x; }
	}

	/**
	 * 思路1： 
	 * 1、递归求解，递归出口为元素为空或者元素为叶子节点
	 * 2、树的深度为左子树、右子树的深度中的最大值+1
	 * 
	 * 思路2：
	 * 层次遍历树，记录树的层次数，即为树的深度
	 */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
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
        System.out.println(new MaximumDepthOfBinaryTree().maxDepth(root));
	}

}
