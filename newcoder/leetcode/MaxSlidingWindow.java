package com.my.test.leetcode.array;

import java.util.LinkedList;

/**
 * 题目：
 * 滑动窗口最大值 -- leetcode 239
 * 
 * 题目描述：
 * 
给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口 k 内的数字。滑动窗口每次只向右移动一位。

返回滑动窗口最大值。

示例:

输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
输出: [3,3,5,5,6,7] 
解释: 

  滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
注意：

你可以假设 k 总是有效的，1 ≤ k ≤ 输入数组的大小，且输入数组不为空。

进阶：

你能在线性时间复杂度内解决此题吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/sliding-window-maximum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxSlidingWindow
{
    /**
     * 思路：
     * 1、双端队列, 最大值放在队列头部
     * 2、如果一个值比它前面的值要大，那么它前面的值就永远不可能成为最大值
     * 2、因为当前元素比前面的元素大，则当前的窗口最大值为当前元素，否则为窗口中目前的最大值
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length <= 0 || k <= 0) {
            return new int[0];
        }
        int len = nums.length;
        // k有效不需要校验k
        int[] res = new int[len - k + 1];
        
        // 双端队列
        LinkedList<Integer> cache = new LinkedList<>();
        
        for (int i=0; i<len; i++) {
            // 添加元素到队列，保证队列递增,比当前元素小的都弹出
            while (!cache.isEmpty() && nums[cache.peekLast()] < nums[i]) {
                // 队列中加入元素索引
                cache.removeLast();
            }
            // 队列中加入元素索引
            cache.addLast(i);
            // 需要移除队列中过期的元素
            if (i - cache.peekFirst() >= k) {
                cache.removeFirst();
            }
            // 如果队列中i>=k-1, 记录当前队列中的最大值
            if (i >= k - 1) {
                res[i-k+1] = nums[cache.peekFirst()];
            }
        }
        
        return res;
    }
    
    public static void main(String[] args)
    {
        int[] nums = {1,3,1,2,0,5}; 
        int k = 3;
        System.out.println(new MaxSlidingWindow().maxSlidingWindow(nums, k));
        int[] nums1 = {1,-1}; 
        int k1 = 1;
        System.out.println(new MaxSlidingWindow().maxSlidingWindow(nums1, k1));
    }

}
