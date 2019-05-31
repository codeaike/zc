package com.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目：
 * two-sum
 * 
 * 题目描述：
 * Given an array of integers, find two numbers such that they add up to a specific target number.
 * The function twoSum should return indices of the two numbers such that they add up to the target, 
 * where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
 * 
 * You may assume that each input would have exactly one solution.
 * 
 * Input: numbers={2, 7, 11, 15}, target=9
 * Output: index1=1, index2=2
 * 
 */
public class TwoSum
{
    /**
     * 思路：(需要两次遍历数组)
     * 1、遍历数组，把每个值作为key, 索引+1 作为value
     * 2、再次遍历数组，查看map中是否有target-curNum作为key的项，有的话则找到目标值
     */
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length <= 1) {
            return null;
        }
        
        // 缓存所有值到hash表中，取值时间复杂度o(1)
        Map<Integer, Integer> cache = new HashMap<>();
        int len = numbers.length;
        for (int i=0; i<len; i++) {
            cache.put(numbers[i], i);
        }
        
        int[] ret = new int[2];
        // 再次遍历数组，找出目标值
        for (int i=0; i<len; i++) {
            int val = numbers[i];
            int otherVal = target - val;
            Integer otherValIndex = cache.get(otherVal);
            // 另外一个值在数组中, 并且不是当前遍历的数字
            if (otherValIndex != null && i != otherValIndex) {
                // 索引值+1
                ret[0] = i + 1;
                ret[1] = otherValIndex + 1;
                return ret;
            }
        }
        
        return ret;
    }

    /**
     * 思路：(一次遍历数组即可)
     * 1、遍历数组，如果map包含key为curNum的值，则找到目标值。否则把target-curNum作为key, 索引 作为value放入map
     */
    public int[] twoSum1(int[] numbers, int target) {
        if (numbers == null || numbers.length <= 1) {
            return null;
        }
        
        // 缓存所有值到hash表中，取值时间复杂度o(1)
        Map<Integer, Integer> cache = new HashMap<>();
        int[] ret = new int[2];
        
        int len = numbers.length;
        for (int i=0; i<len; i++) {
            int val = numbers[i];
            if (cache.containsKey(val)) {
                // target-val的索引值
                ret[0] = cache.get(val) + 1;
                // 当前值的索引值
                ret[1] = i + 1;
                return ret;
            }
            // 放入cache, target-val作为key, i为value
            cache.put(target - val, i);
        }
        
        return ret;
    }
    
    public static void main(String[] args)
    {
        int[] numbers = {2, 7, 11, 15};
        int[] ret = new TwoSum().twoSum(numbers, 9);
        System.out.println(ret[0] + ", " + ret[1]);
        int[] ret1 = new TwoSum().twoSum1(numbers, 9);
        System.out.println(ret1[0] + ", " + ret1[1]);
    }

}
