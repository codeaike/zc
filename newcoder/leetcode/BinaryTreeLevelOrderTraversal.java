package com.my.test.leetcode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目：
 * binary-tree-level-order-traversal -- newcoder 47
 * 二叉树的层次遍历 -- leetcode 102
 * 
 * 题目描述：
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree{3,9,20,#,#,15,7},

    3
   / \
  9  20
    /  \
   15   7

return its level order traversal as:

[
  [3],
  [9,20],
  [15,7]
]

 */
public class BinaryTreeLevelOrderTraversal
{
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    
    /**
     * 思路：
	 * 1、层次遍历的思想(类似BFS思想)
	 * 2、初始化一个队列，初始元素为root节点
	 * 3、遍历队列中的元素，每次遍历先获取当前队列的size,
	 *    依次把队列中的元素出队列，出队列的元素个数为size个,
	 *    如果出队列的元素有左右孩子节点，孩子节点入队列
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
        
        return ret;
    }
    
    public static void main(String[] args)
    {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        
        root.left = node1;
        root.right = node2;
        
        System.out.println(new BinaryTreeLevelOrderTraversal().levelOrderBottom(root));
    }

}
