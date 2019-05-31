package com.codinginterviews.tree;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 题目：
 * 二叉树中和为某一值的路径 -- newcoder 剑指Offer 24
 * 
 * 题目描述：
 * 输入一颗二叉树的跟节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 * (注意: 在返回值的list中，数组长度大的数组靠前)
 * 
 */
public class FindPath
{
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
    
    /**
     * 思路：
     * 1、用深度优先搜索DFS
     * 2、每当DFS搜索到新节点时，都要保存该节点。而且每当找出一条路径之后，
     *    都将这个保存到list的路径保存到最终结果二维list中
     * 3、并且，每当DFS搜索到子节点，发现不是路径和时，返回上一个结点时，
     *    需要把该节点从list中移除
     */
    public ArrayList<ArrayList<Integer>> findPath(TreeNode root, int target) {
    	if (root == null) {
    		return ret;
    	}

    	findPath(root, target, new ArrayList<Integer>());
    	
    	return ret;
    }
    

    public void findPath(TreeNode root, int target, ArrayList<Integer> cache) {
    	if (root == null) {
    		return;
    	}
    	
    	int val = root.val;
    	int remainVal = target - val;
    	
    	// 当前节点进列表
    	cache.add(val);
    	
    	// 当前节点是叶子节点
    	if (remainVal == 0 && root.left == null && root.right == null) {
    		ret.add(new ArrayList<>(cache));
    	}
    	
    	// 处理叶子节点
    	findPath(root.left, remainVal, cache);
    	findPath(root.right, remainVal, cache);
    	
    	// 回溯到上一个节点
    	cache.remove(cache.size() - 1);
    }
    
    /**
     * 思路：
     * 1.按先序遍历把当前节点cur的左孩子依次入栈同时保存当前节点，每次更新当前路径的和sum；
     * 2.判断当前节点是否是叶子节点以及sum是否等于expectNumber，如果是，把当前路径放入结果中。
     * 3.遇到叶子节点cur更新为NULL，此时看栈顶元素，如果栈顶元素的把栈顶元素保存在last变量中，同时弹出栈顶元素，当期路径中栈顶元素弹出，sum减掉栈顶元素，这一步骤不更改cur的值；
     * 4.如果步骤3中的栈顶元素的右孩子存在且右孩子之前没有遍历过，当前节点cur更新为栈顶的右孩子，此时改变cur=NULL的情况。
     */
    public ArrayList<ArrayList<Integer>> findPathII(TreeNode root, int target) {
    	ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    	if (root == null) {
    		return res;
    	}
    	
    	Stack<TreeNode> stack = new Stack<>();
    	// 当前和
    	int sum = 0;
    	ArrayList<Integer> curList = new ArrayList<>();
    	TreeNode cur = root;
    	TreeNode pre = null;
	    while (cur != null || !stack.empty()){
	        if (cur == null){
	            TreeNode temp = stack.peek();
	            if (temp.right != null && temp.right != pre){
	                cur = temp.right; //转向未遍历过的右子树
	            }else{
	                pre = temp; //保存上一个已遍历的节点
	                stack.pop();
	                if (!curList.isEmpty()) {
	                	curList.remove(curList.size()-1); //从当前路径删除
	                }
	                sum -= temp.val;
	            }  }
	        else{
	            stack.push(cur);
	            sum += cur.val;
	            curList.add(cur.val);
	            if (cur.left == null && cur.right == null && sum == target){
	                res.add(new ArrayList<>(curList));
	            }
	            cur = cur.left; //先序遍历，左子树先于右子树
	        }
	    }
	    return res;
    }
    
    /**
     * 思路：
     * 非递归法：后序遍历
     * 1.进栈时候，把值同时压入路径的向量数组，修正路径的和
     * 2.出栈时候，先判断和是否相等，且该节点是否是叶节点，
     *   判断完成后保持和栈一致，抛出路径，修改路径的和
     * 3.向量数组和栈的操作要保持一致 
     */
    public ArrayList<ArrayList<Integer>> findPathIII(TreeNode root, int target) {
    	ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    	if (root == null) {
    		return res;
    	}
    	
    	Stack<TreeNode> stack = new Stack<>();
    	ArrayList<Integer> curList = new ArrayList<>();
    	TreeNode cur = root;
    	int remainVal = target;
    	
        while (cur != null || !stack.empty()){
            while (cur != null){
                stack.push(cur);
                curList.add(cur.val); 
                remainVal -= cur.val;
                //能左就左，否则向右
                cur = cur.left != null ? cur.left : cur.right;
            }
            cur = stack.peek();
            if (remainVal == 0 && cur.left == null && cur.right == null) {
                res.add(new ArrayList<>(curList));
            }
            stack.pop(); 
            if (!curList.isEmpty()) {
            	curList.remove(curList.size() - 1);
            } 
            remainVal += cur.val;
            //右子数没遍历就遍历，如果遍历就强迫出栈
            if (!stack.empty() && stack.peek().left == cur) {
                cur = stack.peek().right;
            } else {
                cur = null;//强迫出栈
            }
        }
    	
    	return res;
    }
    
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode left_left = new TreeNode(1);
        TreeNode right = new TreeNode(3);
        root.left = left;
        left.left = left_left;
        root.right = right;
        System.out.println(new FindPath().findPath(root, 4));
        System.out.println(new FindPath().findPathII(root, 4));
        System.out.println(new FindPath().findPathIII(root, 4));
    }
}


