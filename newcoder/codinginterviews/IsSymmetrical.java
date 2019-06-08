package com.codinginterviews.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目：
 * 对称的二叉树 -- newcoder 剑指Offer 58
 *  
 *  
 * 题目描述：
 * 请实现一个函数，用来判断一颗二叉树是不是对称的。
 * 注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 * 
 */
public class IsSymmetrical {

	static class TreeNode {
		 int val;
		 TreeNode left;
		 TreeNode right;
		 TreeNode(int x) { val = x; }
	}
	
	/**
	 * 思路： 
	 * 1、递归判断两侧的节点是否是对称的
	 */
    public boolean isSymmetrical(TreeNode pRoot) {
    	if (pRoot == null) {
    		return true;
    	}
    	return isSymmetrical(pRoot.left, pRoot.right);
    }
	
    private boolean isSymmetrical(TreeNode left, TreeNode right) {
    	// 两者都为null, 为对称
    	if (left == null && right == null) {
    		return true;
    	}
    	// 只有一个为null, 非对称
    	if (left == null || right == null) {
    		return false;
    	}
    	// 值不相同为非对称
    	if (left.val != right.val) {
    		return false;
    	}
    	// 左节点的左孩子与右节点的右孩子比较，右孩子与右节点的左孩子比较
    	return isSymmetrical(left.left, right.right) && isSymmetrical(left.right, right.left);
    }
    
	/**
	 * 思路：
	 * 1、非递归，层次遍历思想，但是 每次把最外侧节点插入队列
	 * 2、每次遍历当前层元素，弹出两个元素，判断这两个元素是否对称 
	 */
    public boolean isSymmetricalII(TreeNode pRoot) {
    	if (pRoot == null || (pRoot.left == null && pRoot.right == null)) {
    		return true;
    	}
    	
    	// 层次遍历
    	Queue<TreeNode> queue = new LinkedList<>();
    	queue.offer(pRoot.left);
    	queue.offer(pRoot.right);
    	
    	while (!queue.isEmpty()) {
    		int size = queue.size();
    		// 遍历该层
    		while (size > 0) {
    			// 弹出两侧元素
    			TreeNode node1 = queue.poll();
    			TreeNode node2 = queue.poll();
    			// 更新size
    			size -= 2;
    			
    			// 两元素为null,则继续下一对元素
    			if (node1 == null && node2 == null) {
    				continue;
    			}
    			// 两元素一个为null, 不对称，直接返回
    			if (node1 == null || node2 == null) {
    				return false;
    			}
    			// 两元素都存在, 但是值不相等，则不对称
    			if (node1.val != node2.val) {
    				return false;
    			}
    			// 下一层元素加入队列, 从两侧成对添加到队列中
    			queue.offer(node1.left);
    			queue.offer(node2.right);
    			
    			queue.offer(node1.right);
    			queue.offer(node2.left);
    		}
    	}
    	
    	return true;
    }
    
	public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode left_left = new TreeNode(3);
        TreeNode right = new TreeNode(2);
        TreeNode right_right = new TreeNode(3);
        root.left = left;
        left.left = left_left;
        root.right = right;
        right.right = right_right;
        
        System.out.println(new IsSymmetrical().isSymmetrical(root));
        System.out.println(new IsSymmetrical().isSymmetricalII(root));
	}

}


