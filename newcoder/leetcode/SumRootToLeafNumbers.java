package com.leetcode.tree;

/**
 * 题目：
 * 求根到叶子节点数字之和 
 * sum-root-to-leaf-numbers
 *
 * 题目描述：
 * Given a binary tree containing digits from0-9only, each root-to-leaf path could represent a number.
 * An example is the root-to-leaf path1->2->3which represents the number123.
 * Find the total sum of all root-to-leaf numbers.
 * For example,
 
    1
   / \
  2   3
 * The root-to-leaf path1->2represents the number12.
 * The root-to-leaf path1->3represents the number13.
 * Return the sum = 12 + 13 =25.
 *
 */
public class SumRootToLeafNumbers {

    static class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
    }
    
    private int sum = 0;

    /**
     * 思路： 
     * 1、深度遍历DFS(也可用先序遍历)
     * 2、本层的节点值等于上一层的节点值*10 + 本节点值
     */
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (isLeaf(root)) {
            return root.val;
        }
        
        // 深度遍历
        dfs(root, 0);
         
        return sum;
    }
    
    private void dfs(TreeNode node, int nums) {
        // 本层的节点值等于上一层的节点值*10 + 本节点值
        int curNums = (nums * 10) + node.val;
        if (node.left != null) {
            dfs(node.left, curNums);
        }
        if (node.right != null) {
            dfs(node.right, curNums);
        }
        // 是叶子节点则加入sum
        if (isLeaf(node)) {
            sum += curNums;
        }
    }
    
    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }
    
    
    /**
     * 思路：
     * 先序遍历实现
     */
    public int sumNumbersByPreOrder(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (isLeaf(root)) {
            return root.val;
        }
        
        // 先序遍历
        return preOrder(root, 0);
         
    }
    
    
    private int preOrder(TreeNode node, int sums) {
        if (node == null) {
            return 0;
        }
        
        int curSums = sums * 10 + node.val;
        
        // 是叶子节点返回当前值
        if (isLeaf(node)) {
            return curSums;
        }
        
        // 不是叶子节点，继续遍历
        return preOrder(node.left, curSums) + preOrder(node.right, curSums);
    }
    
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        root.left = node1;
        root.right = node2;
        
        System.out.println(new SumRootToLeafNumbers().sumNumbers(root));
        
        System.out.println(new SumRootToLeafNumbers().sumNumbersByPreOrder(root));
    }

}
