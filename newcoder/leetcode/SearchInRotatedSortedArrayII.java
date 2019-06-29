package com.leetcode.array;

/**
 * 题目： 
 * 搜索旋转排序数组II -- leetcode 81
 * 
 * 题目描述：
 *
假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。

编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。

示例 1:

输入: nums = [2,5,6,0,0,1,2], target = 0
输出: true
示例 2:

输入: nums = [2,5,6,0,0,1,2], target = 3
输出: false
进阶:

这是 搜索旋转排序数组 的延伸题目，本题中的 nums  可能包含重复元素。
这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SearchInRotatedSortedArrayII {

    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length <= 0) {
        	return false;
        }
        int len = nums.length;
        int start = 0;
        int end = len - 1;
        while (start <= end) {
        	int mid = (start + end) >> 1;
        	if (nums[mid] == target) {
        		return true;
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
        		} else if (nums[mid] < nums[end]) {
        			end = mid - 1;
        		// 相等时 右移start寻找
        		} else {
        			end = end - 1;
        		}
        	} else {
        		// mid在左侧递增大区间
        		if (nums[mid] > nums[end]) {
        			// 右侧寻找
        			start = mid + 1;
        	    // mid在小区间, 此时mid又小于target
        		} else if (nums[mid] < nums[end]){
        			if (target > nums[end]) {
        				// 左侧寻找
        				end = mid - 1;
        		    // 否则右侧寻找
        			} else {
        				start = mid + 1;
        			}
        	    // 相等时左移end寻找
        		} else {
        			end = end - 1;
        		}
        	}
        }
        return false;
    }
	
	public static void main(String[] args) {
		int[] nums = {0,2,2};
		System.out.println(new SearchInRotatedSortedArrayII().search(nums, 0));
		System.out.println(new SearchInRotatedSortedArrayII().search(nums, 3));
	}

}
