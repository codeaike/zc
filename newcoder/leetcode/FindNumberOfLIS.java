package com.my.test.leetcode.array;

import java.util.Arrays;

/**
 * 题目：
 * 最长递增子序列的个数 -- leetcode 673
 * 
 * 题目描述：
 * 
给定一个未排序的整数数组，找到最长递增子序列的个数。

示例 1:

输入: [1,3,5,4,7]
输出: 2
解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
示例 2:

输入: [2,2,2,2,2]
输出: 5
解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
注意: 给定的数组长度不超过 2000 并且结果一定是32位有符号整数。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindNumberOfLIS
{
    /**
     * 思路： 
     * 动态规划-> dp[i]表示第i个元素的最长序列组合
     * 1、从后往前求每个元素的最长序列组合
     * 2、dp[i]即为dp[i+1],dp[i+2]...判断nums[i]是否能加入序列中后，取最大值即可
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        int len = nums.length;
        if (len == 1) {
            return 1;
        }
        
        int max = 1;
        
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        
        int[] counts = new int[len];
        Arrays.fill(counts, 1);
        
        for (int i=len-2; i>=0; i--) {
            // 遍历dp[i+1], dp[i+2]... 同时判断本元素是否能加入
            for (int j=i+1; j<len; j++) {
                if (nums[i] < nums[j]) {
                    // 如果+1长于当前LIS 则组合数不变
                    if (1+dp[j] > dp[i]) {
                        dp[i] = 1+dp[j];
                        counts[i] = counts[j];
                    // 如果+1等于当前LIS 则说明找到了新组合
                    } else if (1+dp[j] == dp[i]) {
                        counts[i] += counts[j];
                    }
                }
            }
            max = Math.max(max, dp[i]);
        }
        
        // 统计dp中等于max的数量
        int count = 0;
        for (int i=0; i<len; i++) {
            if (dp[i] == max) {
                count += counts[i];
            }
        }
        return count;
    }
    
    
    public static void main(String[] args)
    {
        int[] nums = {1,3,5,4,7};
        System.out.println(new FindNumberOfLIS().lengthOfLIS(nums));
    }

}
