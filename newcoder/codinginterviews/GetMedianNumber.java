package com.codinginterviews.array;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * 题目：
 * 数据流中的中位数 -- newcoder 剑指Offer 63
 * 
 * 题目描述：
如何得到一个数据流中的中位数？
如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。
 *
 */
public class GetMedianNumber {
	/**
	 * 思路：
	 * 1、使用LinkedList缓存数据流，新加入时有序添加
	 * 2、取中位数时，根据size是奇数/偶数，得到中位数
	 */
	
	private LinkedList<Integer> list = new LinkedList<>();
	
    public void Insert(Integer num) {
    	// 列表为空或者列表第一个元素大于等于当前要插入的元素，插入list头部
    	if (list.isEmpty() || list.getFirst() >= num) {
    		list.addFirst(num);
    		return;
    	}
    	
    	// 有序插入
    	for (Integer i : list) {
    		// 找到要插入的位置
    		if (num <= i) {
    			int index = list.indexOf(i);
    			list.add(index, num);
    			return;
    		}
    	}
    	
    	// 需要插入最后
    	list.addLast(num);
    }

    public Double getMedian() {
    	int size = list.size();
    	if (size == 0) {
    		return 0.0d;
    	}
    	int mid = size >>> 1;
    	// 是偶数，则取中间两个数的平均值
    	if ((size & 1) == 0) {
    		
    		return (list.get(mid-1) + list.get(mid)) / 2.0d;
    	}
    	// 是奇数, 则取中间的数
        return (double)list.get(mid);
    }
    
    // 最大堆承载输入数据流的前半部分元素
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(100,
    		new Comparator<Integer>(){

    			@Override
				public int compare(Integer o1, Integer o2) {
					return o2 - o1;
				}
    			
    		});
    // 最小堆承载输入数据流中的后半部分元素
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    
    private int count = 0;
    
    public void InsertII(Integer num) {
    	count++;
    	// 奇数次添加元素加入最小堆，偶数次加入最大堆，维持两个堆元素数量相差不超过1
    	if ((count & 1) == 1) {
    		maxHeap.offer(num);
    		// 最大堆中的最大元素加入最小堆
    		minHeap.offer(maxHeap.poll());
    	} else {
    		minHeap.offer(num);
    		// 最小堆中的最小元素加入最大堆
    		maxHeap.offer(minHeap.poll());
    	}
    }

    public Double getMedianII() {
    	// 元素为奇数个, 取最小堆的最小元素即可
    	if ((count & 1) == 1) {
    		return Double.valueOf(minHeap.peek());
    	} else {
    		return Double.valueOf(maxHeap.peek() + minHeap.peek()) / 2;
    	}
    }
}
