package com.my.test.leetcode.tree;

/**
 * 题目：
 * convert-sorted-array-to-binary-search-tree -- newcoder 41
 * 将有序数组转换为二叉搜索树 -- leetcode 108
 * 
 * 题目描述：
 * 
Given an array where elements are sorted in ascending 
order, convert it to a height balanced BST.
本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

示例:
给定有序数组: [-10,-3,0,5,9],
一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：

      0
     / \
   -3   9
   /   /
 -10  5
 * 
 */
public class ConvertSortedArrayToBinarySearchTree
{
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * 思路：
     * 1、获取中间元素，根节点为中间元素
     * 2、递归处理数组的其他元素
     */
    public TreeNode sortedArrayToBST(int[] num) {
       if (num == null || num.length < 1) {
           return null;
       }
       
       if (num.length == 1) {
           return new TreeNode(num[0]);
       }
       
       int len = num.length;
       
       return toBST(num, 0, len - 1);
    }
    
    private TreeNode toBST(int[] num, int start, int end) {
        if (start > end) {
            return null;
        }

        if (start == end) {
            return new TreeNode(num[start]);
        }
        
        int mid = (start + end + 1) >> 1;
        TreeNode node = new TreeNode(num[mid]);
        
        node.left = toBST(num, start, mid - 1);
        node.right = toBST(num, mid + 1, end);
        
        return node;
    }
    
    public static void main(String[] args)
    {
        int[] num = {-10,-3,0,5,9};
        System.out.println(new ConvertSortedArrayToBinarySearchTree().sortedArrayToBST(num).val);
    }

}
