package com.leetcode.tree;

/**
 * 题目：
 * binary-tree-maximum-path-sum newcoder 27
 * 二叉树中的最大路径和   leetcode 124
 * 
 * 题目描述：
 * Given a binary tree, find the maximum path sum.
 * The path may start and end at any node in the tree.
 * 
 * For example:
 * Given the below binary tree,

       1
      / \
     2   3
 * Return 6.
 * 
 * 输入: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

 * 输出: 42
 */
public class BinaryTreeMaximumPathSum
{
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    
    // 初始化为int最小值
    private int sum = Integer.MIN_VALUE;
    
    /**
     * 思路：
     * 首先我们分析一下对于指定某个节点为根时，最大的路径和有可能是哪些情况:
     * 第一种是左子树的路径加上当前节点，
     * 第二种是右子树的路径加上当前节点，
     * 第三种是左右子树的路径加上当前节点（相当于一条横跨当前节点的路径），
     * 第四种是只有自己的路径。
     * 
     * 乍一看似乎以此为条件进行自下而上递归就行了，然而这四种情况只是
     * 用来计算以当前节点根的最大路径，如果当前节点上面还有节点，
     * 那它的父节点是不能累加第三种情况的。所以我们要计算两个最大值，
     * 一个是当前节点下最大路径和，另一个是如果要连接父节点时最大的路径和。
     * 我们用前者更新全局最大量，用后者返回递归值就行了。 
     */
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        if (root.left == null && root.right == null) {
            return root.val;
        }
        
        getMaxPathNum(root);
        
        return sum;
    }

    
    private int getMaxPathNum(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        // 左右节点取不小于0的
        // 因为节点的值可以为负数，所以最大值取0和子树值的较大者
        int left = Math.max(getMaxPathNum(node.left), 0);
        int right = Math.max(getMaxPathNum(node.right), 0);
        
        // 如果将当前node作为根节点，那么最大值是node.val+左子树最大值+右子树最大值
        sum = Math.max(sum, left + right + node.val);
        
        // 只能返回左右子树中较大值加上node.val
        return Math.max(left, right) + node.val;
    }
    
    public static void main(String[] args)
    {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        
        root.left = node1;
        root.right = node2;
        
        System.out.println(new BinaryTreeMaximumPathSum().maxPathSum(root));
    }

}
