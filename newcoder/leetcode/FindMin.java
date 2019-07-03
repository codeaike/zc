package com.my.test.leetcode.array;

/**
 * 题目：
 * 寻找旋转排序数组中的最小值 -- leetcode 153
 * 
 * 题目描述：
 * 
假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

请找出其中最小的元素。

你可以假设数组中不存在重复元素。

示例 1:

输入: [3,4,5,1,2]
输出: 1
示例 2:

输入: [4,5,6,7,0,1,2]
输出: 0

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class FindMin {
 /**
  * 思路：
  * 二分查找
  */
 public int findMin(int[] nums) {
     if (nums == null || nums.length <= 0) {
         return 0;
     }
     
     int len = nums.length;
     if (len == 1) {
         return nums[0];
     }
     
     int start = 0;
     int end = nums.length - 1;
     while (start < end) {
         int mid = (start + end) >> 1;
         // 需要与nums[end]比较才能判断出来mid的位置
         if (nums[mid] > nums[end]) {
             start = mid + 1;
         } else if (nums[mid] < nums[end]) {
             end = mid;
         } else {
             // 存在重复元素时，此题目不重复 可直接使用break
             end = end - 1;
         }
     }
     return nums[start];
 }
}

