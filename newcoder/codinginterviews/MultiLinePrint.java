package com.codinginterviews.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目：
 * 把二叉树打印成多行 -- newcoder 剑指Offer 60
 * 
 * 题目描述：
 * 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
 */
public class MultiLinePrint {
 
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
     * 1、初始化一个队列，初始元素为root
     * 2、遍历元素，每次首先获取当前队列的节点个数，即当前队列的size
     * 3、弹出size次元素，则本次遍历到的均为本层的元素
     * 4、每次弹出元素的同时，把元素的左右孩子加入队列，以便下次遍历
     */
    public ArrayList<ArrayList<Integer>> print(TreeNode pRoot) {
    	ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    	if (pRoot == null) {
    		return res;
    	}
    	
    	Queue<TreeNode> queue = new LinkedList<>();
    	queue.offer(pRoot);
    	
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
    			
    			curList.add(curNode.val);
    			
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
    	}
    	
    	return res;
    }
    
    /**
     * 思路：
     * 1、每行数据加入队列，记录当前行的节点数，和下一行的节点数
     * 2、当前行打印完毕后，更新当前行节点数为下一行节点数量，换行，元素加入列表。
     */
    public static ArrayList<ArrayList<Integer>> printII(TreeNode pRoot) {
    	ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    	if (pRoot == null) {
    		return res;
    	}
        
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(pRoot);
        
        TreeNode curNode;
        // 当前行节点数
        int current = 1;
        // 下一行节点数
        int next = 0;
        
        ArrayList<Integer> cache = new ArrayList<>();
        
        while(!q.isEmpty()) {
            // 打印当前元素
            curNode = q.poll();
            current--;
            
            cache.add(curNode.val);
            
            if (curNode.left != null) {
                q.offer(curNode.left);
                next++;
            }
            if (curNode.right != null) {
                q.offer(curNode.right);
                next++;
            }
            // 当前行元素打印完毕
            if (current == 0) {
                current = next;
                next = 0;
                // cache 加入 res
                res.add(new ArrayList<Integer>(cache));
                cache.clear();
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
        
        return root;
    }
    
}
	 

