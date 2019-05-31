package com.my.test.codinginterviews.tree;

import java.util.Stack;

/**
 * 题目：
 * 二叉搜索树与双向链表 -- newcoder 剑指Offer 26
 * 
 * 题目描述：
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
 * 要求不能创建任何新的结点，只能调整树中结点指针的指向。
 */
public class ConvertBSTToList
{

    static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }
    
    /**
     * 思路：
     * 1、中序遍历BST的结果是排序的
     * 2、按照遍历顺序组建为双向链表即可
     * 
     * 中序遍历过程：
     * 1、利用栈与当前节点的指针
     * 2、处理根节点，如果有左孩子，就处理左孩子，记左孩子为链表的最后一个节点，后续指向当前节点
     * 3、如果左孩子为空，记录当前节点为链表的最后一个节点
     * 4、处理右孩子节点  
     */
    public TreeNode convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) {
            return null;
        }
        
        TreeNode newHead = null;
        
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        TreeNode cur = pRootOfTree;
        
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            // 从栈中取出元素
            cur = stack.pop();
            
            // 第一个取出栈中元素
            if (pre == null) {
                newHead = cur;
                pre = newHead;
            // 构建双向链表
            } else {
                pre.right = cur;
                cur.left = pre;
                
                // 推进pre
                pre = cur;
            }
            
            // 指向右孩子
            cur = cur.right;
        }
        
        return newHead;
    }
    
    /**
     * 思路：
     * 1、中序遍历
     * 2、一个元素的数组，缓存新链表的尾节点
     */
    public TreeNode convertII(TreeNode pRootOfTree) {
        if (pRootOfTree == null) {
            return null;
        }
    
        // 一个元素的数组，缓存新链表的尾节点
        TreeNode[] nodeArr = new TreeNode[1];
        TreeNode tail = getTail(pRootOfTree, nodeArr);
        
        // 找到头节点
        while (tail != null && tail.left != null) {
            tail = tail.left;
        }
        
        return tail;
    }
    
    private TreeNode getTail(TreeNode root, TreeNode[] tailNodeArr) {
        if (root == null) {
            return null;
        }
        
        TreeNode left = root.left;
        if (left != null) {
            getTail(left, tailNodeArr);
        }

        root.left = tailNodeArr[0];
        
        if (tailNodeArr[0] != null) {
            tailNodeArr[0].right = root;
        }
        
        tailNodeArr[0] = root;
        
        TreeNode right = root.right;
        if (right != null) {
            getTail(right, tailNodeArr);
        }
        
        return tailNodeArr[0];
    }
    
}
