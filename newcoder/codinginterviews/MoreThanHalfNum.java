package com.my.test.codinginterviews.array;

/**
 * 题目：
 * 数组中出现次数超过一半的数字 --  newcoder 剑指Offer 28
 * 
 * 题目描述：
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 
 * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。
 * 如果不存在则输出0。
 */
public class MoreThanHalfNum {
 
    /**
     * 思路：
     * 1、快排思想，获取排序后数组中间的数字(利用快排思想，不需要完成整个快排)
     * 2、超过一半的数组必然位于排序后的数组中间
     * 3、再确认该数字的出现次数是否超过一半
     */
    public static int MoreThanHalfNum_Solution(int[] array) {
        if (array == null || array.length <= 0) {
            return 0;
        }
        
        int len = array.length;
        
        int mid = getMiddle(array, 0, len-1);
        
        while (mid != len/2) {
            if (mid < len/2) {
                mid = getMiddle(array, mid + 1, len-1);
            } else {
                mid = getMiddle(array, 0, mid - 1);
            }
        }
        
        // 获取数组的中位数
        int num = array[mid];
        
        // 判断数据的出现次数 是否真正超过一半
        int numCount = 0;
        for (int i = 0; i< len; i++) {
            if (array[i] == num) {
                numCount++;
            }
        }
        
        // 无出现次数大于一半的数
        if (numCount * 2 <= len) {
            return 0;
        }
        
        return num;
    }
    
    private static int getMiddle(int arr[], int low, int high) {
        int base = arr[low];
        while (low < high) {
            while ( low < high && base <= arr[high]) {
                high--;
            }
            arr[low] = arr[high];
            
            while ( low < high && base >= arr[low]) {
                low++;
            }
            arr[high] = arr[low];
        }
        // 基准值左侧小于基准值，右侧大于基准值
        arr[low] = base;
        return low;
    }
    
    /**
     * 思路：
     * 1、根据数字特征，因为数组中该数字出现次数超过一半
     * 2、若遍历数组，记录该元素，遇到相同的就次数加1，不同的减一，为负数时，更换元素，
     * 3、则最后一个必然是出现次数超过一半的数字 
     */
    public static int MoreThanHalfNum_SolutionII(int[] array) {
        if (array == null || array.length <= 0) {
            return 0;
        }
        int len = array.length;
        int curNum = array[0];
        int count = 1;
        for (int i=1; i<len; i++) {
            // 相同则增加count
            if (array[i] == curNum) {
                count++;
            } else {
                // 不同则减少
                if (count > 0) {
                    count --;
                // 出现负值情况则重置数据
                } else {
                    curNum = array[i];
                    count = 1;
                }
            }
        }
        
        // 判断数据的出现次数 是否真正超过一半
        int numCount = 0;
        for (int i = 0; i< len; i++) {
            if (array[i] == curNum) {
                numCount++;
            }
        }
        
        // 无出现次数大于一半的数
        if (numCount * 2 <= len) {
            return 0;
        }
        
        return curNum;
    } 
    
    public static void main(String[] args) {
        int[] arr1 = new int[]{1,4,3,4,4,4,5,6,4,4,4,1};
        System.out.println(MoreThanHalfNum_Solution(arr1));
 
        
        int[] arr2 = new int[]{1,4,3,1,5,6,4,4};
        System.out.println(MoreThanHalfNum_Solution(arr2));
        
        
        int[] arr3 = new int[]{1,4,3,4,4,4,5,6,4,4,4,1};
        System.out.println(MoreThanHalfNum_SolutionII(arr3));
 
        
        int[] arr4 = new int[]{1,4,3,1,5,6,4,4};
        System.out.println(MoreThanHalfNum_SolutionII(arr4));
    }
 
}
