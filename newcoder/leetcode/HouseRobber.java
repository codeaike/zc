package com.leetcode.dyncplanning;

/**
 * 题目：
 * 打家劫舍 -- leetcode 198
 * house robber
 * 
 * 题目描述：
 * 
你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。

示例 1:
输入: [1,2,3,1]
输出: 4
解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     偷窃到的最高金额 = 1 + 3 = 4 。

示例 2:
输入: [2,7,9,3,1]
输出: 12
解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 */
public class HouseRobber
{
    /**
     * 思路(动态规划)：
     * 1、使用动态规划求解，偷窃前n号房屋的金额为n-2号房屋的金额+本次 与 n-1号房屋的金额取最大值
     * 2、递推方程为f(n) = Max(f(n-1), f(n-2)+nums[n])
     * 3、因为递推时只需要前两个结果，所以简化为维护两个状态值即可
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }

        int first = 0;
        int second = 0;
        int rob = 0;

        int len = nums.length;
        for (int i=0; i<len; i++) {
            rob = Math.max(first + nums[i], second);
            first = second;
            second = rob;
        }
        
        return rob;
    }
    
    public static void main(String[] args)
    {
        HouseRobber rob = new HouseRobber();
        int[] nums1 = {1,2,3,1};
        System.out.println(rob.rob(nums1));
        int[] nums2 = {2,7,9,3,1};
        System.out.println(rob.rob(nums2));
    }

}
