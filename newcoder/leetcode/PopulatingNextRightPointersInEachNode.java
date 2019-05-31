package com.my.test.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目：
 * populating-next-right-pointers-in-each-node -- newcoder 35
 * 填充每个节点的下一个右侧节点指针 -- leetcode 116
 * 
 * 题目描述：
 * 
Given a binary tree

    struct TreeLinkNode {
      TreeLinkNode *left;
      TreeLinkNode *right;
      TreeLinkNode *next;
    }

Populate each next pointer to point to its next right node. 
If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
You may assume that it is a perfect binary tree (ie, all leaves are at 
the same level, and every parent has two children).

For example,
Given the following perfect binary tree,

         1
       /  \
      2    3
     / \  / \
    4  5  6  7

After calling your function, the tree should look like:

         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 -> NULL

 *  
 */
public class PopulatingNextRightPointersInEachNode
{
    static class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;
        TreeLinkNode(int x) { val = x; }
        @Override
        public String toString() {
            return val + (next==null?"(null)":"("+next.val+")");
        }
    }
    
    /**
     * 思路(non-constant extra space)：
     * 1、层次遍历，遍历的同时更新节点的指向
     * 
     * 说明：
     * 1、既适合完全二叉树
     */
    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            root.next = null;
            return;
        }
        
        // 层次遍历
        Queue<TreeLinkNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 遍历当前层元素
            int size = queue.size();
            for (int i=0; i<size; i++) {
                TreeLinkNode cur = queue.poll();
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                // 更新节点指向, 当前层的最后一个元素指向null, 否则指向当前层的下一个元素 
                cur.next = i==size-1 ? null : queue.peek(); 
            }
        }
    }
    
    /**
     * 思路(constant extra space)：
     * 1、利用完全二叉树特性，维护上一层的起始节点start，和当前处理的上一层的节点cur
     * 2、额外空间为固定大小
     */
    public void connectII(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            root.next = null;
            return;
        }
        
        // 起始节点
        TreeLinkNode start = root;
        // 处理的当前层的节点
        TreeLinkNode cur;
        while (start.left != null) {
            cur = start;
            while (cur != null) {
                // 挂接左节点的指针
                cur.left.next = cur.right;
                // 挂接右节点的next指针
                if (cur.next != null) {
                    cur.right.next = cur.next.left;
                }
                cur = cur.next;
            }
            start = start.left;
        }
    }
    
    public static void main(String[] args)
    {
        TreeLinkNode root = new TreeLinkNode(1);
        TreeLinkNode left = new TreeLinkNode(2);
        TreeLinkNode right = new TreeLinkNode(3);
        root.left = left;
        root.right = right;
        new PopulatingNextRightPointersInEachNode().connectII(root);
        System.out.println(root);
        System.out.println(root.left);
        System.out.println(root.right);
    }

}
