package com.codinginterviews.array;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 题目：
 * 最小的K个数 -- newcoder 剑指Offer 29
 *  
 * 题目描述：
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。 
 */
public class LeastKNumbers
{   
	/**
	 * 思路： 
	 * 1、使用最大堆，构建容量为K的最大堆
	 * 2、遍历数组，每次比较数组中的元素与堆顶元素大小，堆堆顶小入堆即可
	 */
    public ArrayList<Integer> getLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> ret = new ArrayList<>();
        if (input == null || k == 0) {
            return ret; 
        }
        
        int len = input.length;
        if (len < k) {
            return ret;
        }
        
        // 构建大顶堆
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, new Comparator<Integer>(){

            @Override
            public int compare(Integer o1, Integer o2)
            {
                return o2 - o1;
            }
            
        });
        
        // 遍历数组，把比堆顶元素小的元素加入最大堆中
        for (int i=0; i<len; i++) {
        	int curVal = input[i];
        	if (maxHeap.size() < k) {
        		maxHeap.add(curVal);
        	} else {
        		// 堆顶元素大于当前元素，则弹出堆顶元素，加入当前元素到堆中
        		if (maxHeap.peek() > curVal) {
        			maxHeap.poll();
        			maxHeap.add(curVal);
        		}
        	}
        }
        
        // 最大堆所有元素加入要返回的list中
        ret.addAll(maxHeap);
        
        return ret;
    }

    /**
     * 思路： 
     * 1、快排思想，N趟排序后，比base值小的在base的左侧
     * 2、当base的索引为k-1时，其左侧的必然都是小于input[k]，右侧的值均大于input[k]
     * 3、取出数组的前k个数即可
     */
    public ArrayList<Integer> getLeastNumbers_SolutionII(int [] input, int k) {
    	ArrayList<Integer> ret = new ArrayList<>();
    	if (input == null || k == 0) {
    		return ret;
    	}
    	
        int len = input.length;
        
        if (len < k) {
            return ret;
        }
        
        // 快排思想，mid前面的数小于input[mid]
    	int start = 0;
    	int end = len-1;
    	int mid = getMid(input, 0, len-1);
    	
    	// 目标索引,此处不能以k为目标索引，因为k可能等于数组的长度，以k为索引，数组越界
    	int targetIdx = k - 1;
    	
        while (mid != targetIdx) {
        	// 目标值在mid左侧
        	if (mid > targetIdx) {
        		end = mid-1;
            // 目标值在mid右侧        		
        	} else {
        		start = mid+1;
        	}
    		mid = getMid(input, start, end);
        }
        
        for (int i=0; i<=targetIdx; i++) {
        	ret.add(input[i]);
        }
        
    	return ret;
    }
    
    // 以arr[begin]为参照，比该值小的放在左侧，比该值大的放在右侧，并返回arr[begin]的索引
    private int getMid(int[] input, int begin, int end) {
    	// 比较数为input[begin]
    	int base = input[begin];
    	
    	// 左边数为参照，必须右起找第一个元素
    	while (begin < end) {
    		// 右起找第一个小于base的元素
    		while (begin<end && input[end]>=base) {
    			end--;
    		}
    		input[begin] = input[end];
    		
    		// 左起找第一个大于base的元素
    		while (begin<end && input[begin]<=base) {
    			begin++;
    		}
    		input[end] = input[begin];
    	}
    	
    	input[begin] = base;
    	
    	return begin;
    }

    public static void main(String[] args)
    {
    	int[] input = {4,5,1,6,2,7,3,8};
    	System.out.println(new LeastKNumbers().getLeastNumbers_Solution(input, 4));
    	System.out.println(new LeastKNumbers().getLeastNumbers_SolutionII(input, 8));
    }

}


