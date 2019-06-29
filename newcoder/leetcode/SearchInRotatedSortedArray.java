package com.leetcode.array;

/**
 * 题目： 
 * 搜索旋转排序数组 -- leetcode 33
 * 
 * 题目描述：
 *
假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。

你可以假设数组中不存在重复的元素。

你的算法时间复杂度必须是 O(log n) 级别。

示例 1:

输入: nums = [4,5,6,7,0,1,2], target = 0
输出: 4
示例 2:

输入: nums = [4,5,6,7,0,1,2], target = 3
输出: -1

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SearchInRotatedSortedArray {

    public int search(int[] nums, int target) {
        if (nums == null || nums.length <= 0) {
        	return -1;
        }
        int len = nums.length;
        int start = 0;
        int end = len - 1;
        while (start <= end) {
        	int mid = (start + end) >> 1;
        	if (nums[mid] == target) {
        		return mid;
        	} else if (nums[mid] > target){
        		// mid在左侧递增大区间
        		if (nums[mid] > nums[end]) {
        			// 目标值大于最右侧值，左侧寻找
        			if (target > nums[end]) {
        				end = mid - 1;
        		    // 否则右侧寻找
        			} else {
        				start = mid + 1;
        			}
        	    // mid在小区间, 此时mid又大于target，向左侧寻找
        		} else {
        			end = mid - 1;
        		}
        	} else {
        		// mid在左侧递增大区间
        		if (nums[mid] > nums[end]) {
        			// 右侧寻找
        			start = mid + 1;
        	    // mid在小区间, 此时mid又小于target
        		} else {
        			if (target > nums[end]) {
        				// 左侧寻找
        				end = mid - 1;
        		    // 否则右侧寻找
        			} else {
        				start = mid + 1;
        			}
        		}
        	}
        }
        return -1;
    }
	
	public static void main(String[] args) {
		int[] nums = {4,5,6,7,0,1,2};
		System.out.println(new SearchInRotatedSortedArray().search(nums, 0));
		System.out.println(new SearchInRotatedSortedArray().search(nums, 3));
	}

}
