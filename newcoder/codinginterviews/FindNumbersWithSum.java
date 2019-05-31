package com.codinginterviews.array;

import java.util.ArrayList;

/**
 * 题目:
 * 和为S的两个数字 -- newcoder 剑指Offer 42
 * 
 * 题目描述：
输入一个递增排序的数组和一个数字S，在数组中查找两个数，
使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。
输出描述:
对应每个测试案例，输出两个数，小的先输出。
 *
 */
public class FindNumbersWithSum {

	/**
	 * 思路：
	 * 1、双指针，一个指向头节点，一个指向尾节点
	 * 2、数组有序，利用首尾指针，找到相差最大的和等于S的两个元素即可
	 */
    public ArrayList<Integer> findNumbersWithSum(int [] array,int sum) {
    	ArrayList<Integer> res = new ArrayList<>();
    	if (array == null || array.length <= 1) {
    		return res;
    	}
    	
    	int i = 0;
    	int j = array.length - 1;
    	
    	while (i < j) {
    		int curSum = array[i] + array[j];
    		if (sum == curSum) {
    			res.add(array[i]);
        		res.add(array[j]);
    			break;
    		} else if (sum > curSum){
    			i++;
    		} else {
    			j--;
    		}
    	}
    	
    	return res;
    }
	
	public static void main(String[] args) {
		int[] arr = {1,2,8,9};
		System.out.println(new FindNumbersWithSum().findNumbersWithSum(arr,10));
		
		int[] arr1 = {-2,-1,1,2,5,8,9,11,12,14};
		System.out.println(new FindNumbersWithSum().findNumbersWithSum(arr1,10));

	}

}
