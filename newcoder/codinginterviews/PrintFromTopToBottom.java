package com.my.test.codinginterviews.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目：
 * 从上往下打印二叉树 -- newcoder 剑指Offer 22
 * 
 * 题目描述：
 * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
 */
public class PrintFromTopToBottom
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
     * 2、每次弹出队列中的一个元素，并把左右孩子加入队列即可
     */
    public ArrayList<Integer> printFromTopToBottom(TreeNode root) {
        ArrayList<Integer> cache = new ArrayList<>();
        
        if (root == null) {
            return cache;
        }
        
        // 初始化队列
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        
        // 当前处理的节点
        TreeNode curNode;
        
        while (!q.isEmpty()) {
            // 弹出队列中的一个元素
            curNode = q.poll();
            
            cache.add(curNode.val);
            
            // 左右孩子入队列
            if (curNode.left != null) {
                q.offer(curNode.left);
            }
            if (curNode.right != null) {
                q.offer(curNode.right);
            }
        }

        return cache;
    }
    
    public static void main(String[] args)
    {

        TreeNode root = createBinaryTree();
        System.out.println(new PrintFromTopToBottom().printFromTopToBottom(root));
    }
    
    private static TreeNode createBinaryTree() {
        TreeNode root = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        
        root.left = node1;
        root.right = node2;
        
        TreeNode node3 = new TreeNode(3);
        node1.left = node3;
        
        return root;
    }


}
