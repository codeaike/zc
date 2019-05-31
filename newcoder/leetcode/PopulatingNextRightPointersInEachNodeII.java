package com.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目：
 * populating-next-right-pointers-in-each-node-ii -- newcoder 34
 * 填充每个节点的下一个右侧节点指针II -- leetcode 117
 * 
 * 题目描述：
 * 
Follow up for problem "Populating Next Right Pointers in Each Node".
What if the given tree could be any binary tree? 
Would your previous solution still work?

Note:

You may only use constant extra space.

For example,
Given the following binary tree,

         1
       /  \
      2    3
     / \    \
    4   5    7

After calling your function, the tree should look like:

         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL
 *  
 */
public class PopulatingNextRightPointersInEachNodeII
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
     * 1、既适合完全二叉树, 有适用于非完全二叉树
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
     * 1、维护下一层元素的pre节点(虚拟出新的节点)和当前处理的节点cur
     * 2、每次处理本层元素，建立下一层元素之间的连接
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
        
        // 当前处理的层的起始节点
        TreeLinkNode cur = root;
        
        // 处理的当前层的节点
        while (cur != null) {
        	// 虚拟下层节点的头节点
        	TreeLinkNode pre = new TreeLinkNode(0);
        	TreeLinkNode tmp = pre;
        	
        	// 遍历当前层节点，下层节点建立next指针
        	while (cur != null) {
        		if (cur.left != null) {
        			tmp.next = cur.left;
        			tmp = tmp.next;
        		}
        		if (cur.right != null) {
        			tmp.next = cur.right;
        			tmp = tmp.next;		
        		}
        		// 处理下一个元素
        		cur = cur.next;
        	}
        	// 下一层的起始节点
        	cur = pre.next;
        }
    }
    
    public static void main(String[] args)
    {
        TreeLinkNode root = new TreeLinkNode(1);
        TreeLinkNode left = new TreeLinkNode(2);
        TreeLinkNode right = new TreeLinkNode(3);
        root.left = left;
        root.right = right;
        new PopulatingNextRightPointersInEachNodeII().connectII(root);
        System.out.println(root);
        System.out.println(root.left);
        System.out.println(root.right);
    }

}
