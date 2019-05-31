package com.leetcode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目：
 * binary-tree-level-order-traversal-ii -- newcoder 42
 * 二叉树的层次遍历 II -- leetcode 107
 * 
 * 题目描述：
Given a binary tree, return the bottom-up level order 
traversal of its nodes' values. (ie, from left to right, 
level by level from leaf to root).

For example:
Given binary tree{3,9,20,#,#,15,7},

    3
   / \
  9  20
    /  \
   15   7

return its bottom-up level order traversal as:

[
  [15,7]
  [9,20],
  [3],
]

 */
public class BinaryTreeLevelOrderTraversalII
{
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    
    /**
     * 思路：
     * 1、利用队列进行层次遍历 
     */
    public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        
        if (root == null) {
            return ret;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        // 当前处理的节点
        TreeNode curNode;
        
        while (!queue.isEmpty()) {
            // 遍历本层元素, 并把结果加入返回列表中
            int size = queue.size();
            ArrayList<Integer> curList = new ArrayList<>();
            for (int i=0; i<size; i++) {
                curNode = queue.poll();
                // 当前值入列表
                curList.add(curNode.val);
                
                // 下层元素入队列
                if (curNode.left != null) {
                    queue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    queue.offer(curNode.right);
                }
            }
            
            // 当前层元素加入待返回列表
            ret.add(curList);
        }
        
        Collections.reverse(ret);
        
        return ret;
    }
    
    public static void main(String[] args)
    {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        
        root.left = node1;
        root.right = node2;
        
        System.out.println(new BinaryTreeLevelOrderTraversalII().levelOrderBottom(root));
    }

}
