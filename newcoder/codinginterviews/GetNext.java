package com.codinginterviews.tree;

/**
 * 题目： 
 * 二叉树的下一个结点 -- newcoder 剑指Offer 57
 * 
 * 题目描述：
 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
 * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 *
 */
public class GetNext {

	static class TreeLinkNode {
	    int val;
	    TreeLinkNode left = null;
	    TreeLinkNode right = null;
	    // 父节点指针
	    TreeLinkNode next = null;

	    TreeLinkNode(int val) {
	        this.val = val;
	    }
	}
	
	/**
	 * 思路：
	 * 分析二叉树的下一个节点，一共有以下情况：
	 * 1.二叉树为空，则返回空；
	 * 2.节点右孩子存在，则设置一个指针从该节点的右孩子出发，一直沿着指向左子结点的指针找到的叶子节点即为下一个节点；
	 * 3.节点右孩子不存在，节点是根节点，返回null。
	 *   节点不是根节点，如果该节点是其父节点的左孩子，则返回父节点；
	 *   否则继续向上遍历其父节点的父节点，重复之前的判断，返回结果。
	 */
    public TreeLinkNode getNext(TreeLinkNode pNode)
    {
        if (pNode == null) {
        	return null;
        }
        // 右节点不为空
        if (pNode.right != null) {
            pNode = pNode.right;
            while (pNode.left != null) {
                pNode = pNode.left;
            }
            return pNode;
        }
        // 右节点为空，父节点不为空
        while (pNode.next != null) {
        	// 是左孩子则返回父节点
            if(pNode.next.left == pNode) {
            	return pNode.next;
            }
            // 非左孩子，向上找父节点，重复此过程
            pNode = pNode.next;
        }
        // 其他情况返回null
        return null;
    }
	

}
