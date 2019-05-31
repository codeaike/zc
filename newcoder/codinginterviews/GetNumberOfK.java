package com.codinginterviews.array;

/**
 * 题目：
 * 数字在排序数组中出现的次数 -- newcoder 37 
 *  
 * 题目描述：
 * 统计一个数字在排序数组中出现的次数。
 */
public class GetNumberOfK {

	/**
	 * 思路： 
	 * 1、二分查找k在array中出现的最左侧的位置i
	 * 2、二分查找k在array中出现的最右侧的位置j
	 * 3、返回 j-i+1即可
	 */
    public int getNumberOfK(int [] array , int k) {
        if (array == null || array.length == 0) {
            return 0;
        }
        
        int len = array.length;
        int left = getFirstNumIndex(array, k, 0, len - 1);
        int right = getLastNumIndex(array, k, 0, len - 1);
        
        if (left >= 0 && right >= left) {
            return right - left + 1;
        }
        
        return 0;
    }
    
    // 递归 二分查找的变形 查找最左侧的k元素
    private int getFirstNumIndex(int[] arr, int k, int start, int end) {
        // 判断非法条件
        if (start > end) {
            return -1;
        }
        
        // 在数字没有溢出的前提下，对于正数和负数，左移一位都相当于乘以2的1次方，左移n位就相当于乘以2的n次方
        // 右移一位相当于除2，右移n位相当于除以2的n次方
        int mid = (start + end) >> 1;
        
        int cur = arr[mid];
        // 找到了num对应的首个索引
        if (cur == k ) {
            if (mid == 0 || (mid > 0 && arr[mid - 1] < cur)) {
                return mid;
            }
        } 
        // 没有找到
        int newStart = start;
        int newEnd = end;
        
        // 目标数据在右侧
        if (cur < k) {
            newStart = mid + 1;
        // 目标数据在左侧, 相等情况去左侧找         
        } else {
            newEnd = mid - 1;
        }
        
        // 重新查找下一区间
        return getFirstNumIndex(arr, k, newStart, newEnd);
    }
 
    // 非递归 二分查找的变形 查找最右侧的k元素
    private int getLastNumIndex(int[] arr, int k, int start, int end) {
        if (start > end) {
            return -1;
        }
        int len = arr.length;
        int mid = (start + end) >> 1;
        
        while(start <= end){
            if (arr[mid] > k){
                end = mid-1;
            } else if (arr[mid] < k){
                start = mid+1;
            } else if (mid+1 < len && arr[mid+1] == k){
                start = mid+1;
            } else {
                return mid;
            }
            mid = (start + end) >> 1;
        }
        return -1;
    }

    /**
     * 思路： 
     * 1、因为data中都是整数，所以可以稍微变一下，不是搜索k的两个位置，而是搜索k-0.5和k+0.5
     * 2、这两个数应该插入的位置，然后相减即可。
     */
    public int getNumberOfKII(int [] array , int k) {
        return biSearch(array, k+0.5) - biSearch(array, k-0.5) ;
    }
    
    private int biSearch(int [] array , double k) {
    	int start = 0, end = array.length - 1;     
        while(start <= end){
            int mid = (end + start) >> 1;
            if(array[mid] < k)
                start = mid + 1;
            else if(array[mid] > k)
                end = mid - 1;
        }
        return start;
    }
    
	public static void main(String[] args) {
		int[] arr = {1,2,3,3,3,3,4,5};
		int[] arr1 = {1,1,1,1,1,1,1,1};
		int[] arr2 = {3};
		System.out.println(new GetNumberOfK().getNumberOfK(arr, 6));
		System.out.println(new GetNumberOfK().getNumberOfK(arr1, 1));
		System.out.println(new GetNumberOfK().getNumberOfK(arr2, 3));
	}

}
