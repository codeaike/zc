package com.leetcode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 题目：
 * recover-binary-search-tree -- newcoder 50
 * 恢复二叉搜索树 -- leetcode 99 
 * 
 * 题目描述：
 * 
Two elements of a binary search tree (BST) are swapped by mistake.
Recover the tree without changing its structure.

Note: 
A solution using O(n ) space is pretty straight forward. 
Could you devise a constant space solution?
 * 中文描述： 
 * 
二叉搜索树中的两个节点被错误地交换。

请在不改变其结构的情况下，恢复这棵树。

示例 1:

输入: [1,3,null,null,2]

   1
  /
 3
  \
   2

输出: [3,1,null,null,2]

   3
  /
 1
  \
   2
示例 2:

输入: [3,1,4,null,null,2]

  3
 / \
1   4
   /
  2

输出: [2,1,4,null,null,3]

  2
 / \
1   4
   /
  3
进阶:

使用 O(n) 空间复杂度的解法很容易实现。
你能想出一个只使用常数空间的解决方案吗？
 */
public class RecoverBinarySearchTree {
	
	static class TreeNode {
		 int val;
		 TreeNode left;
		 TreeNode right;
		 TreeNode(int x) { val = x; }
	}
	
	/**
	 * 思路1： 
	 * 1、o(n)空间复杂度，中序遍历树，得到当前中序遍历序列
	 * 2、排序得到的遍历序列，不一致则更新TreeNode的值
	 */
    public void recoverTree(TreeNode root) {
        if (root == null) {
        	return;
        }
        
        List<TreeNode> nodes = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        inOrder(root, nodes, values);
        
        Collections.sort(values);
        
        for (int i=0, size=values.size(); i<size; i++) {
        	nodes.get(i).val = values.get(i);
        }
    }
    
    private void inOrder(TreeNode root, List<TreeNode> nodes, List<Integer> values) {
    	if (root == null) {
    		return;
    	}
    	inOrder(root.left, nodes, values);
    	nodes.add(root);
    	values.add(root.val);
    	inOrder(root.right, nodes, values);
    }
	
    private TreeNode pre, first, second;
    
    /**
     * 思路2： 
     * 1、o(3)空间复杂度，但是使用了递归，空间复杂度增加为o(h)(h为树的高度)
     * 2、节点交换分两种情况，一种是相邻节点，这种交换不破坏两节点与之外的大小性，只破坏
		     两节点之间大小性。另一种是不相邻，这种破坏两节点分别与两边的节点。
	                 因此在第一次搜到一个不满足的节点后，要将第二个节点也赋指针，以考虑第一种情况
	 * 3、维护pre(前一个节点),first(第一个需要交换的节点),second(第二个节点)三个缓存          
     */
    public void recoverTreeII(TreeNode root) {
    	if (root == null) {
    		return;
    	}
    	inOrder(root);
    	// 交换两个元素的值
    	int tmp = first.val;
    	first.val = second.val;
    	second.val = tmp;
    }
    
    private void inOrder(TreeNode root) {
    	if (root == null) {
    		return;
    	}
    	
    	inOrder(root.left);
    	
		// 找到一个需要交换的节点
		if (pre != null && pre.val >= root.val) {
			// 初始化第一个需要交换的节点
			if (first == null){
				first = pre;
			}
			// 暂时初始化第二个为当前的root节点
			second = root;
		}
		pre = root;
    	
    	inOrder(root.right);
    }
    
	public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(3);
        TreeNode left_right = new TreeNode(2);
        root.left = left;
        left.right = left_right;
        // new RecoverBinarySearchTree().recoverTree(root);
        new RecoverBinarySearchTree().recoverTreeII(root);
        System.out.println(root.val);
	}

}
