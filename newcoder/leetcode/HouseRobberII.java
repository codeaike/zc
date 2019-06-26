package com.leetcode.dyncplanning;

/**
 * 题目：
 * 打家劫舍II -- leetcode 213
 * house robber ii
 * 
 * 题目描述：
 * 
你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。
同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。

示例 1:
输入: [2,3,2]
输出: 3
解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。

示例 2:
输入: [1,2,3,1]
输出: 4
解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
     偷窃到的最高金额 = 1 + 3 = 4 。
 */
public class HouseRobberII
{
    /**
     * 思路(动态规划)： 
     * 1、因为首尾相连，所以首尾两家只能选择一个，或者都不抢
     * 2、我们这里变通一下，如果我们把第一家和最后一家分别去掉，各算一遍能抢的最大值，然后比较两个值取其中较大的一个即为所求
     */
    public int rob(int[] nums) {
        if (nums == null) {
            return 0;
        }
        
        int len = nums.length;
        if (len == 1) {
            return len <= 0 ? 0 : nums[0];
        }
        
        return Math.max(rob(nums, 0, len - 1), rob(nums, 1, len));
    }
    
    private int rob(int[] nums, int start, int end) {
        int first = 0;
        int second = 0;
        int rob = 0;
        for (int i=start; i<end; i++) {
            rob = Math.max(first + nums[i], second);
            first = second;
            second = rob;
        }
        return rob;
    }
    
    public static void main(String[] args)
    {
        HouseRobberII rob = new HouseRobberII();
        int[] nums1 = {2,3,2};
        System.out.println(rob.rob(nums1));
        int[] nums2 = {1,2,3,1};
        System.out.println(rob.rob(nums2));
    }

}
