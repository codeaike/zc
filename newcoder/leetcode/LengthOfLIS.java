package com.my.test.leetcode.array;

import java.util.Arrays;

/**
 * 题目：
 * 最长上升子序列 -- leetcode 300
 * 
 * 题目描述：
 * 
给定一个无序的整数数组，找到其中最长上升子序列的长度。

示例:

输入: [10,9,2,5,3,7,101,18]
输出: 4 
解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
说明:

可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
你算法的时间复杂度应该为 O(n2) 。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。 
 */
public class LengthOfLIS
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
        
        int resMax = 1;
        
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        
        for (int i=len-1; i>=0; i--) {
            // 遍历dp[i+1], dp[i+2]... 同时判断本元素是否能加入
            if (i==len-1) {
                dp[i] = 1;
                continue;
            }
            for (int j=i+1; j<len; j++) {
                if (nums[i] < nums[j]) {
                    dp[i] = Math.max(dp[i], 1+dp[j]);
                }
            }
            resMax = Math.max(resMax, dp[i]);
        }
        
        return resMax;
    }
    
    
    public static void main(String[] args)
    {
        int[] nums = {1,3,6,7,9,4,10,5,6};
        System.out.println(new LengthOfLIS().lengthOfLIS(nums));
    }

}
