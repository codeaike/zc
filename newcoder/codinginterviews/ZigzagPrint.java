package com.codinginterviews.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
 
/**
 * 题目：
 * 按之字形顺序打印二叉树 -- newcoder 剑指Offer 59
 * 
 * 题目描述：
 * 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，
 * 第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推
 * 
 */
public class ZigzagPrint {
 
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int value) {
            this.val = value;
        }
    }
    
    /**
     * 思路：
     * 1、多行遍历二叉树，同时维护行遍历正序/倒序标识位
     * 2、每次行遍历时，是正序则加入行遍历列表最后位置，否则加入列表头部
     */
    public static ArrayList<ArrayList<Integer>> print(TreeNode pRoot) {
    	ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    	if (pRoot == null) {
    		return res;
    	}
    	
    	Queue<TreeNode> queue = new LinkedList<>();
    	queue.offer(pRoot);
    	
    	// 当前层应该按照什么顺序，0代表正序
    	int curOrder = 0;
    	
    	// 当前处理的元素
    	TreeNode curNode;
    	
    	while (!queue.isEmpty()) {
    		// 当前层的节点数量
    		int size = queue.size();
    		
    		// 当前层的节点值，按照应该打印的顺序添加到列表中
    		ArrayList<Integer> curList = new ArrayList<>();
    		
    		// 遍历当前层的元素
    		for (int i=0; i<size; i++) {
    			// 弹出本层元素
    			curNode = queue.poll();
    			
    			// 正序则插入列表末尾，倒序打印的行则插入列表头部
    			if (curOrder == 0) {
    				curList.add(curNode.val);
    			} else {
    				curList.add(0, curNode.val);
    			}
    			
    			// 添加左右孩子节点到队列
    			if (curNode.left != null) {
    				queue.offer(curNode.left);
    			}
    			if (curNode.right != null) {
    				queue.offer(curNode.right);
    			}
    		}
    		
    		// 当前行元素遍历的列表加入res
    		res.add(curList);
    		// 更新是否倒序标识
    		curOrder = 1 - curOrder;
    	}
    	
    	return res;
    }
    
    /**
     * 思路：
     * 第一行正向输出，第二行反向输出，第三行正向输出，依次类推...
     */
    public static ArrayList<ArrayList<Integer>> printII(TreeNode pRoot) {
    	ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    	if (pRoot == null) {
    		return res;
    	}
        
        // 当前行元素容器
        List<TreeNode> curNodes = new ArrayList<TreeNode>();
        
        // 下一行元素容器
        List<TreeNode> nextLineNodes = new ArrayList<TreeNode>();
        
        // 初始化
        curNodes.add(pRoot);
        
        // 当前行从左到右打印
        int flag = 1;
        
        // 当前处理的节点
        TreeNode curNode;
        
        ArrayList<Integer> cache = new ArrayList<>();
        
        while(!curNodes.isEmpty()) {
            // 取出当前行的最后一个元素
            // 可尝试每次取出第一个元素打印，这样的话第三行开始的元素会顺序混乱
            curNode = curNodes.remove(curNodes.size() - 1);
            
            // 当前遍历的元素 加入列表
            cache.add(curNode.val);
            
            // 当前行从左往右打印，则下行插入从左往右
            // 因为取出下一行元素的时候，是逆序的，这样下下行才能真正按照从右往左插入元素
            if (flag == 1) {
                if (curNode.left != null) {
                    nextLineNodes.add(curNode.left);
                }
                if (curNode.right != null) {
                    nextLineNodes.add(curNode.right);
                }
            // 否则从右往左插入，从而实现从左到右读取
            } else {
                if (curNode.right != null) {
                    nextLineNodes.add(curNode.right);
                }
                if (curNode.left != null) {
                    nextLineNodes.add(curNode.left);
                }
            }
 
            // 当前行元素遍历完毕
            if (curNodes.isEmpty()) {
            	// 当前层元素加入列表
            	res.add(new ArrayList<Integer>(cache));
            	cache.clear();
                // 也可交换两个列表，这样更节省空间
                // 赋值下一行节点给当前行
                curNodes = nextLineNodes;
                nextLineNodes = new ArrayList<TreeNode>();
                
                // 更换标识
                flag = 1 - flag;
            }
        }
        
        return res;
    }
    
    public static void main(String[] args) {
        TreeNode root = createBinaryTree();
        System.out.println(printII(root));
    }
    
    private static TreeNode createBinaryTree() {
        TreeNode root = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        
        root.left = node1;
        root.right = node2;
        
        TreeNode node3 = new TreeNode(3);
        node1.left = node3;
        
        TreeNode node4 = new TreeNode(4);
        node1.right = node4;
        
        TreeNode node5 = new TreeNode(5);
        node2.left = node5;
        
        TreeNode node6 = new TreeNode(6);
        node2.right = node6;
        
        return root;
    }
    
}
	 

