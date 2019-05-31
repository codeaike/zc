package com.my.test.leetcode.tree;

import java.util.Stack;

/**
 * 题目：
 * validate-binary-search-tree -- newcoder 51
 * 验证二叉搜索树 -- leetcode 98
 * 
 * 题目描述：
 * 
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:
The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.

 * 中文描述：
 * 
给定一个二叉树，判断其是否是一个有效的二叉搜索树。

假设一个二叉搜索树具有如下特征：
节点的左子树只包含小于当前节点的数。
节点的右子树只包含大于当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。
示例 1:

输入:
    2
   / \
  1   3
输出: true
示例 2:

输入:
    5
   / \
  1   4
     / \
    3   6
输出: false
解释: 输入为: [5,1,4,null,null,3,6]。
     根节点的值为 5 ，但是其右子节点值为 4 。
 */
public class ValidateBinarySearchTree
{
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    
    private TreeNode pre;
    private boolean isValidBST = true;
    
    /**
     * 思路1：
     * 1、递归方式进行中序遍历
     * 2、如果遍历结果是非递增的，则不是二叉搜索树
     * 
     * 说明：
     * 此方式实现简单，但是需要全部遍历完树，性能较低
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        
        // 中序遍历
        inOrder(root);
        
        return isValidBST;
    }

    // 中序遍历树，遍历的结果不缓存 只判断
    private void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        
        // 当前值是否大于上一个值
        if (pre != null && root.val <= pre.val) {
            isValidBST = false;
        }
        
        // 更新pre
        pre = root;
        
        inOrder(root.right);
    }
    
    /**
     * 思路2：
     * 1、非递归中序遍历
     * 2、 遍历过程中判断当前值是否大于上一个值，否则返回false
     * 
     * 说明：
     * 此方式遍历到第一个不满足条件的子树 即停止
     */
    public boolean isValidBSTII(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode pre = null;
        
        while (!stack.empty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            
            cur = stack.pop();
            
            if (pre != null && pre.val >= cur.val) {
                return false;
            }
            pre = cur;
            
            cur = cur.right;
        }
        
        return true;
    }
    
    public static void main(String[] args)
    {
        TreeNode root = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.right = right;
        TreeNode left = new TreeNode(1);
        right.left = left;
        System.out.println(new ValidateBinarySearchTree().isValidBST(root));
        System.out.println(new ValidateBinarySearchTree().isValidBSTII(root));
    }

}
