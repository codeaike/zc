package com.my.test.codinginterviews.tree;

/**
 * 题目：
 * 二叉搜索树的后序遍历序列 -- newcoder 剑指Offer 23
 * 
 * 题目描述：
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
 * 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 * 
 */
public class VerifySquenceOfBST
{
    /**
     * 思路：
     * 1、后序遍历的特征为 根节点在序列的最后 值为rootVal
     * 2、序列上半部分的值都小于rootVal，下部分的值都大于rootVal
     * 3、递归判断上半部分、下半部分的序列，是否是树的后序遍历序列
     */
    public boolean verifySquenceOfBST(int [] sequence) {
        if (sequence == null) {
            return false;
        }
        
        int len = sequence.length;
        
        if (len == 0) {
            return false;
        }
        
        return verifySquenceOfBST(sequence, 0, len - 1);
    }
    
    private boolean verifySquenceOfBST(int [] seq, int begin, int end) {
        // 一个元素时，为后序遍历序列
        if (begin >= end) {
            return true;
        }
        
        // root节点的值
        int rootVal = seq[end];
        
        // 序列中的最后一个左子树节点
        int leftEnd = begin;
        
        int i = begin;
        // 遍历找到左子树的序列 与 右子树序列, 获取分割索引
        while (seq[i] < rootVal) {
            leftEnd = i;
            i++;
        }
        
        // 判断leftEnd序列后的值,如果存在元素小于rootVal,则不是后序序列
        while (i < end) {
            if (seq[i] < rootVal) {
                return false;
            }
            i++;
        }
        
        return verifySquenceOfBST(seq, begin, leftEnd) && verifySquenceOfBST(seq, leftEnd+1, end-1);
    }
    
    /**
     * 思路(非递归)： 
     * 左子树一定比右子树小，因此去掉根后，数字分为left，right两部分，right部分的
     * 最后一个数字是右子树的根他也比左子树所有值大，因此我们可以每次只看有子树是否符合条件
     * 即可，即使到达了左子树左子树也可以看出由左右子树组成的树还想右子树那样处理
 
     * 对于左子树回到了原问题，对于右子树，左子树的所有值都比右子树的根小可以暂时把他看出右子树的左子树
     * 只需看看右子树的右子树是否符合要求即可
     */
    public boolean verifySquenceOfBSTII(int [] sequence) {
        int len = sequence.length;
        
        if(0 == len) {
            return false;
        }
 
        // 每次遍历起始索引
        int i = 0;
        // 要处理的数组的最后一个元素的索引
        len -= 1;
        while (len > 1)
        {
            // 右移，判断左子树 序列
            while (i < len && sequence[i++] < sequence[len]);
            // 右移，判断右子树 序列
            while (i < len && sequence[i++] > sequence[len]);
 
            // 没有遍历完毕，说明不是先序序列
            if (i < len) {
                return false;
            }
            
            // len-- 去除根节点, i置位
            len--;
            i = 0;
        }
        return true;
    }
    
    public static void main(String[] args)
    {
        int [] sequence = {1,2,3,4,5};
        System.out.println(new VerifySquenceOfBST().verifySquenceOfBST(sequence));
        System.out.println(new VerifySquenceOfBST().verifySquenceOfBSTII(sequence));
    }

}
