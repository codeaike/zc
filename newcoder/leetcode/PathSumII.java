package com.leetcode.tree;

import java.util.ArrayList;

/**
 * 题目:
 * path-sum-ii -- newcoder 37
 * 路径总和II -- leetcode 113
 * 
 * 题目描述：
 * 
Given a binary tree and a sum, find all root-to-leaf paths 
where each path's sum equals the given sum.

For example:
Given the below binary tree andsum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return

[
   [5,4,11,2],
   [5,8,4,5]
]
 *
 */
public class PathSumII {

	static class TreeNode {
		 int val;
		 TreeNode left;
		 TreeNode right;
		 TreeNode(int x) { val = x; }
	}
	
	private ArrayList<ArrayList<Integer>> cache = new ArrayList<>();

	/**
	 * 思路：
	 * 1、用深度优先搜索DFS
	 * 2、每当DFS搜索到新节点时，都要保存该节点。而且每当找出一条路径之后，
	 *    都将这个保存到list的路径保存到最终结果二维list中
	 * 3、并且，每当DFS搜索到子节点，发现不是路径和时，返回上一个结点时，
	 *    需要把该节点从list中移除
	 */
    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
    	if (root == null) {
    		return cache;
    	}
    	
    	findPath(root, new ArrayList<Integer>(), sum);
    	
    	return cache;
    }
	
    private void findPath(TreeNode root, ArrayList<Integer> list, int sum) {
    	if (root == null) {
    		return;
    	}
    	
    	int curVal = root.val;
    	list.add(curVal);
    	
    	if (curVal == sum && root.left == null && root.right == null) {
    		cache.add(new ArrayList<>(list));
    	}
    	
    	findPath(root.left, list, sum - curVal);
    	findPath(root.right, list, sum - curVal);
    	
    	list.remove(list.size()-1);
    }
    
	public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode left_left = new TreeNode(1);
        TreeNode right = new TreeNode(3);
        root.left = left;
        left.left = left_left;
        root.right = right;
        System.out.println(new PathSumII().pathSum(root, 4));
	}

}
