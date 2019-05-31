package com.leetcode.tree;

/**
 * 题目： 
 * construct-binary-tree-from-inorder-and-postorder-traversal -- newcoder 43
 * 从中序与后序遍历序列构造二叉树 -- leetcode 106
 * 
 * 题目描述：
Given inorder and postorder traversal of a tree, 
construct the binary tree.

Note: 
You may assume that duplicates do not exist in the tree.

例如，给出
中序遍历 inorder = [9,3,15,20,7]
后序遍历 postorder = [9,15,7,20,3]
返回如下的二叉树：

    3
   / \
  9  20
    /  \
   15   7
 * 
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {

	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}	
	
	
	/**
	 * 思路： 
	 * 1、后序遍历序列中的最后一个元素时根节点
	 * 2、找到该根节点在中序遍历序列中的位置，左侧即为
	 *    左树的遍历序列，右侧为右树的遍历序列
	 * 3、根据左树遍历序列的数组长度len，找出后序遍历
	 * 	     序列的前len个元素，最后一个元素即为左树中的根节点，
	 *    右树的根节点是后序遍历序列的最后一个节点
	 * 4、递归进行上述过程，构建树
	 */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null) {
        	// empty param
        	return null;
        }
        
        int inLen = inorder.length;
        int postLen = postorder.length;
        
        if (inLen != postLen) {
        	// inValid param
        	return null;
        }
        
        // 只有一个元素
        if (inLen == 1) {
        	return new TreeNode(inorder[0]);
        }
        
    	return buildTree(inorder, 0, inLen-1, postorder, 0, postLen-1);
    }
    
    private TreeNode buildTree(int[] inOrder, int inBegin, int inEnd, 
    						   int[] postOrder, int postBegin, int postEnd) {
    	if (inBegin > inEnd || postBegin > postEnd) {
    		return null;
    	}
    	
    	// 1、根节点的值
    	int rootVal = postOrder[postEnd];
    	
    	// 2、找到该值在中序序列中的索引    	
    	int rootIdxInOrder = -1;
    	for (int i=inBegin; i<= inEnd; i++) {
    		if (inOrder[i] == rootVal) {
    			rootIdxInOrder = i;
    			break;
    		}
    	}
    	// 没找到，参数不合法，返回null
    	if (rootIdxInOrder < 0) {
    		return null;
    	}

    	// 3、生成头节点
    	TreeNode root = new TreeNode(postOrder[postEnd]);
    	
    	// 只有一个元素
    	if (inBegin == inEnd && postBegin == postEnd) {
    		return root; 
    	}
    	
    	// 4、左子树长度
    	int leftTreeLen = rootIdxInOrder - inBegin;
    	
    	// 5、后序遍历序列中，左子树遍历序列结束索引为postBegin + leftTreeLen - 1
    	int postIdxLeftTreeEnd = postBegin + leftTreeLen - 1;
    	
    	// 6、递归处理左右子树
    	
    	// 挂接左右节点
    	root.left = buildTree(inOrder, inBegin, rootIdxInOrder-1, 
    							postOrder, postBegin, postIdxLeftTreeEnd);
    	root.right = buildTree(inOrder, rootIdxInOrder+1, inEnd,
    							postOrder, postIdxLeftTreeEnd+1, postEnd-1);
    	
    	return root;
    } 
    
	public static void main(String[] args) {
		int[] inOrder = {9,3,15,20,7};
		int[] postOrder = {9,15,7,20,3};
		System.out.println(new ConstructBinaryTreeFromInorderAndPostorderTraversal().buildTree(inOrder, postOrder).val);
	}

}
