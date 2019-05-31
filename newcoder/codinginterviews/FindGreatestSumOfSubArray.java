package com.my.test.codinginterviews.array;

/**
 * 题目：
 * 连续子数组的最大和 -- newcoder 剑指Offer 30
 * 
 * 题目描述：
 * 
HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。今天测试组开完会后,
他又发话了:在古老的一维模式识别中,常常需要计算连续子向量的最大和,当向量全为正数的时候,问题很好解决。
但是,如果向量中包含负数,是否应该包含某个负数,并期望旁边的正数会弥补它呢？
例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。
给一个数组，返回它的最大连续子序列的和，你会不会被他忽悠住？(子向量的长度至少是1)
 */
public class FindGreatestSumOfSubArray
{
    /**
     * 思路：
     * 1、 遍历数组，统计连续子序列的和，记录当前子序列的和的最大值
     * 2、如果连续字符列的和小于等于0，置位连续子序列的和为0，起始元素变为下一个元素
     */
    public int findGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length <= 0) {
            return 0;
        }
        // 初始化sum为第一个元素的值
        int sum = array[0];
        // 初始化maxSum为第一个元素的值
        int maxSum = sum;
        // 遍历元素
        for (int i=1, len=array.length; i<len; i++) {
            // 当前连续字符列的和
            int curSum = sum + array[i];
            // 更新连续子序列的最大和
            maxSum = Math.max(maxSum, curSum);
            // 更新sum，小于等于0，置位连续子序列的和为0
            sum = curSum < 0 ? 0 : curSum;
        }
        return maxSum;
    }
    
    public static void main(String[] args)
    {
        int[] arr = {6,-3,-2,7,-15,1,2,2};
        System.out.println(new FindGreatestSumOfSubArray().findGreatestSumOfSubArray(arr));
    }

}
