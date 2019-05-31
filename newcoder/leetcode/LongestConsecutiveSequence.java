package com.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目：
 * longest-consecutive-sequence newcoder 23
 * 最长连续序列  leetcode 128
 * 
 * 题目描述：
 * Given an unsorted array of integers, find the length of 
 * the longest consecutive elements sequence.
 * 
 * For example,
 * Given[100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is[1, 2, 3, 4]. 
 * Return its length:4.
 * 
 * Your algorithm should run in O(n) complexity.
 */
public class LongestConsecutiveSequence {

	/**
	 * 思路：
	 * 1、遍历一遍数组构建一个Map, key value均为数组中的值
	 * 2、遍历数组，记录该元素所在的连续序列长度，最短为元素本身，长度为1
	 * 3、返回最大的连续序列长度
	 */
	public int longestConsecutive(int[] num) {
		if (num == null) {
			return 0;
		}
		int len = num.length;
		if (len <= 1) {
			return len;
		}
		Map<Integer, Integer> cache = new HashMap<>(); 
		for (int i=0; i<len; i++) {
			int curNum = num[i];
			cache.put(curNum, curNum);
		}
		
		// 二次遍历，获取最长子序列的长度
		int longest = 0;
		
		for (int ele : num) {
			longest = Math.max(longest, getEleConsecutiveSeqLenAndDelSeqEle(ele, cache));
		}
		
        return longest;
    }
	
	// 获取元素ele所在的连续序列长度, 并从map中删除统计到的连续序列元素
	private int getEleConsecutiveSeqLenAndDelSeqEle(int ele, Map<Integer, Integer> cache) {
		// 元素所在的序列已经被统计过
		if (cache.get(ele) == null) {
			return 0;
		}
		// 连续序列长度初始化为1，ele元素
		int count = 1;
		
		// 统计小于ele的连续元素个数
		int pre = ele - 1;
		while (cache.get(pre) != null) {
			count++;
			// 删除元素，避免该序列元素被重复统计
			cache.remove(pre);
			pre--;
		}
		
		// 统计大于ele的连续元素个数
		int post = ele + 1;
		while (cache.get(post) != null) {
			count++;
			// 删除元素，避免该序列元素被重复统计
			cache.remove(post);
			post++;
		}
		return count;
	}
	
	public static void main(String[] args) {
		int[] num = {100, 4, 200, 1, 3, 2};
		System.out.println(new LongestConsecutiveSequence().longestConsecutive(num));
	}

}
