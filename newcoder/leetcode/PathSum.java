package com.my.test.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目：
 * path-sum -- newcoder 38
 * 路径总和 -- leetcode 112
 * 
 * 题目描述：
 * 
Given a binary tree and a sum, determine if the tree has a root-to-leaf path 
such that adding up all the values along the path equals the given sum.

For example:
Given the below binary tree andsum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 * 
 */
public class PathSum
{

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    
    /**
     * 思路：
     * 1、递归求解，出口为本节点值为当前需要的值，且本节点为叶子节点
     * 2、更新当前值-节点值为下一个值，判断左右孩子节点是否满足要求
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        
        // 递归出口，本节点值为当前需要的值，且本节点为叶子节点
        if (root.val == sum && root.left == null && root.right == null) {
            return true;
        }
        
        // 更新当前值-节点值为下一个值，判断左右孩子节点是否满足要求
        return hasPathSum(root.left, sum-root.val) || hasPathSum(root.right, sum-root.val);
    }
    
    /**
     * 思路：
     * 1、BFS，使用两个队列缓存数据，一个缓存节点数据，一个缓存根到当前节点的值
     */
    public boolean hasPathSumII(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.offer(root);
        Queue<Integer> values = new LinkedList<>();
        values.offer(root.val);
        
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.poll();
            int value = values.poll();
            
            // 找到目标节点
            if (value == sum && node.left == null && node.right == null) {
                return true;
            }
            
            if (node.left != null) {
                nodes.offer(node.left);
                values.offer(value + node.left.val);
            }
            
            if (node.right != null) {
                nodes.offer(node.right);
                values.offer(value + node.right.val);
            }
        }
        
        return false;
    }
    
    public static void main(String[] args)
    {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
        System.out.println(new PathSum().hasPathSum(root, 3));
        System.out.println(new PathSum().hasPathSum(root, 4));
        System.out.println(new PathSum().hasPathSum(root, 5));
        System.out.println(new PathSum().hasPathSumII(root, 3));
        System.out.println(new PathSum().hasPathSumII(root, 4));
        System.out.println(new PathSum().hasPathSumII(root, 5));
    }

}
