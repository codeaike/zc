package com.codinginterviews.array;

/**
 * 题目： 
 * 数组中重复的数字 -- newcoder 剑指Offer 50
 * 
 * 题目描述：
 * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，
 * 但不知道有几个数字是重复的。也不知道每个数字重复几次。请找出数组中任意一个重复的数字。 
 * 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
 *
 */
public class Duplicate {
    // Parameters:
    //    numbers:     an array of integers
    //    length:      the length of array numbers
    //    duplication: (Output) the duplicated number in the array number,length of duplication array is 1,so using duplication[0] = ? in implementation;
    //                  Here duplication like pointor in C/C++, duplication[0] equal *duplication in C/C++
    //    这里要特别注意~返回任意重复的一个，赋值duplication[0]
    // Return value:       true if the input is valid, and there are some duplications in the array number
    //                     otherwise false
	
	/**
	 * 思路：
	 * 1、使用一个长度为len的boolean数组缓存元素是否出现过
	 * 2、当再次出现的数字已经在数组中，即找到重复元素
	 */
    public boolean duplicate(int numbers[], int length, int [] duplication) {
    	if (numbers == null || length <= 0) {
    		return false;
    	}
    	
    	// 长度为n的布尔数组存储数字出现的个数
    	boolean[] cache = new boolean[length];
    	
    	for (int i=0; i<length; i++) {
    		int val = numbers[i];
    		// 出现了重复数字
    		if (cache[val] == true) {
    			duplication[0] = val;
    			return true;
    		}
    		cache[val] = true;
    	}
    	
    	return false;
    }
    
    /**
     * 思路：
     * 1、利用现有数组设置标志，当一个数字被访问过后，可以设置对应位上的数 + n
     * 2、之后再遇到相同的数时，会发现对应位上的数已经大于等于n了，那么直接返回这个数即可
     * 
     * 注意：
     * 1、未考虑n特别大，+n后int越界的情况
     * 2、假设输入的数组 满足数组内的值小于length的要求 
     */
    public boolean duplicateII(int numbers[], int length, int [] duplication) {
    	if (numbers == null || length <= 0) {
    		return false;
    	}
    	
        for (int i= 0; i<length; i++) {
            int index = numbers[i];
            if (index >= length) {
                index -= length;
            }   

            if (numbers[index] >= length) {
                duplication[0] = index;
                return true;
            }   

            numbers[index] = numbers[index] + length;
        }   

    	return false;
    }
    
    public static void main(String args[]) {
    	int[] arr = {2,3,1,0,2,5,3};
    	int[] duplication = new int[1];
    	System.out.println(new Duplicate().duplicateII(arr, 7, duplication));
    	System.out.println(duplication[0]);
    }
}
