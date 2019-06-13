package com.codinginterviews.array;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * 题目：
 * 滑动窗口的最大值 -- newcoder 剑指Offer 64
 * 
 * 题目描述：
 *  
给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，
那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}； 
针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个： {[2,3,4],2,6,2,5,1}，
 {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}， 
 {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
 */
public class MaxInWindows {

	/**
	 * 思路： 
	 * 1、遍历数组，每次取滑动窗口的所有值，遍历窗口中的元素，取出最大值
	 */
    public ArrayList<Integer> maxInWindows(int [] num, int size) {
        ArrayList<Integer> res = new ArrayList<>();
        if (num == null || size <= 0 || num.length < size) {
        	return res;
        }
        
        // 暴力破解法
        int len = num.length;
        int maxIdx = len - size;
        for (int i=0; i<= maxIdx; i++) {
        	// 获取当前序列的最大值
        	int curMax = num[i];
        	for (int j=i+1; j<i+size; j++) {
        		curMax = curMax > num[j] ? curMax : num[j];
        	}
        	// 最大值加入res
        	res.add(curMax);
        }
        
        return res;
    }
    
    /**
     * 思路：
     * 1、双端队列保存滑动窗口的最大值(保存在头部)，次大值数据
     * 2、窗口滑动，从右侧遍历，比当前值小的移出队列，队首元素过期 移出队列，
     *    当前元素的索引加入队列
     */
    public ArrayList<Integer> maxInWindowsII(int [] num, int size) {
        ArrayList<Integer> res = new ArrayList<>();
        if (num == null || size <= 0 || num.length < size) {
        	return res;
        }
        
        // 使用双端队列 缓存滑动窗口，最大值保存在头部
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        int len = num.length;
        
        for (int i=0; i<len; i++){
        	// 从后面依次弹出队列中比当前num值小的元素，同时也能保证队列首元素为当前窗口最大值下标
            while(!queue.isEmpty() && num[queue.peekLast()]<=num[i]) {
            	queue.pollLast();
            }
            // 当队首元素坐标对应的num不在窗口中，需要弹出
            if(!queue.isEmpty() && i-queue.peekFirst()+1>size) {
                queue.pollFirst();
            }
            // 把每次滑动的num下标加入队列
            queue.offerLast(i);
            // 当滑动窗口首地址i大于等于size时才开始写入窗口最大值
            if(i+1 >= size) {
                res.add(num[queue.peekFirst()]);
            }
        }
        
        return res;
    }
	
	public static void main(String[] args) {
		int[] num = {2,3,4,2,6,2,5,1};
		int size = 3;
		System.out.println(new MaxInWindows().maxInWindows(num, size));
		System.out.println(new MaxInWindows().maxInWindowsII(num, size));
	}

}
