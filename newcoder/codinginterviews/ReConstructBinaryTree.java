package com.codinginterviews;

/**
 * 题目：
 * 重建二叉树 -- 剑指Offer 4
 * 
 * 
 * 题目描述：
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，
 * 则重建二叉树并返回。
 */
public class ReConstructBinaryTree
{
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }   
    
    
    /**
     * 思路： 
     * 1、前序遍历序列中的第一个元素为根节点
     * 2、找到该根节点在中序遍历序列中的位置，左侧即为
     *    左树的遍历序列，右侧为右树的遍历序列
     * 3、根据左树遍历序列的数组长度len，找出前序遍历
     *       序列的第2到第len+1个元素，第一个元素即为左树中的根节点，
     *    右树的根节点是前序遍历序列的最后一个节点
     * 4、递归进行上述过程，构建树
     */    
    public TreeNode reConstructBinaryTree(int [] pre, int [] in) {
        if (pre == null || in == null) {
            // empty param
            return null;
        }
        
        int preLen = pre.length;
        int inLen = in.length;
        
        if (preLen != inLen) {
            // inValid param
            return null;
        }
        
        // 只有一个元素
        if (preLen == 1) {
            return new TreeNode(pre[0]);
        }
        
        return buildTree(pre, 0, preLen-1, in, 0, inLen-1);
    }
    
    private TreeNode buildTree(int[] preOrder, int preBegin, int preEnd, 
                                int[] inOrder, int inBegin, int inEnd) {
        if (preBegin > preEnd || inBegin > inEnd) {
            return null;
        }
        
        // 1、根节点的值
        int rootVal = preOrder[preBegin];
        
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
        TreeNode root = new TreeNode(rootVal);
        
        // 只有一个元素，直接返回，减少递归层次
        if (preBegin == preEnd && inBegin == inEnd) {
            return root; 
        }
        
        // 4、左子树长度
        int leftTreeLen = rootIdxInOrder - inBegin;
        
        // 5、前序遍历序列中，左子树遍历序列结束索引为preBegin + 1 + leftTreeLen - 1
        int preIdxLeftTreeEnd = preBegin + leftTreeLen;
        
        // 6、递归处理左右子树，挂接左右节点， 要去掉当前根节点的索引
        root.left = buildTree(preOrder, preBegin+1, preIdxLeftTreeEnd, 
                                inOrder, inBegin, rootIdxInOrder-1);
        root.right = buildTree(preOrder, preIdxLeftTreeEnd+1, preEnd, 
                                inOrder, rootIdxInOrder+1, inEnd);
        
        return root;
    }
    
}
