package com.leetcode.array;

/**
 * 题目： 
 * 组合总和IV -- leetcode 377
 * 
 * 题目描述：
 * 
给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数。

示例:

nums = [1, 2, 4]
target = 4

所有可能的组合为：
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(2, 1, 1)
(2, 2)
(4)

请注意，顺序不同的序列被视作不同的组合。

因此输出为 7。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/combination-sum-iv
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CombinationSumIV {
	/**
	 * 思路： 
	 * 1、动态规划
	 * 2、求target为n 相当于dp[i]表示target为i的结果个数 
	 * 遍历nums数组，如果i>=x，dp[i]+=dp[i-x],因为
	 * dp[3]=dp[2]+1或dp[1]+2或dp[0]+3
	 * 
	 * 再举个例子
	 *  dp[i]=dp[i-nums[0]]+dp[i-nums[1]]+dp[i=nums[2]]+...
	 *  比如nums=[1,3,4],target=7;
	 *  dp[7]=dp[6]+dp[4]+dp[3]
	 *  其实就是说7的组合数可以由三部分组成，1和dp[6]，3和dp[4],4和dp[3];
	 */
    public int combinationSum4(int[] nums, int target) {
        if (nums == null) {
        	return 0;
        }

        int[] dp = new int[target+1];
        dp[0] = 1;
        for (int i=1; i<=target; i++) {
        	for (int x : nums) {
        		if (i > x) {
        			dp[i] += dp[i-x];
        		}
        	}
        }
        return dp[target];
    }
}
