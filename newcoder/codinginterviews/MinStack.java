package com.codinginterviews.stack;

import java.util.Arrays;

/**
 * 题目：
 * 包含min函数的栈 -- 剑指Offer 20
 * 
 * 题目描述： 
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中
 * 所含最小元素的min函数（时间复杂度应为O（1））。
 *
 */
public class MinStack {

	// 数组实现栈
    static class Stack<T> {
    	// 初始化容量为10
    	@SuppressWarnings("unchecked")
		private T[] arr = (T[])new Object[10];
    	
    	int capacity = 0;
    	
    	int maxCapacity = 10;
    	
    	public void push(T t) {
    		if (needEnsureCapacity()) {
    			grow();
    		}
    		this.arr[capacity++] = t;
    	}
    	
    	private boolean needEnsureCapacity() {
    		return capacity + 1 > maxCapacity;
    	}
    	
    	// 每次扩容为当前最大容量的1.5倍
    	private void grow() {
    		this.maxCapacity = this.maxCapacity * 3 / 2 + 1;
    		this.arr = Arrays.copyOf(this.arr, this.maxCapacity);
    	}
    	
    	public T pop() {
    		if (capacity <= 0) {
    			return null;
    		}
    		
    		T val = this.arr[capacity-1];
    		this.arr[capacity-1] = null;
    		capacity--;
    		return val;
    	}
    	
    	public T peek() {
    		if (capacity <= 0) {
    			return null;
    		}
    		return this.arr[capacity-1];
    	}
    	
    	public boolean isEmpty() {
    		return capacity == 0;
    	}
    }
	
    
    /**
     * 思路： 
     * 1、使用两个栈，第一个容纳元素，第二个记录当前的最小值
     * 2、第二个栈，push时，如果当前元素比栈顶元素小，则push一次当前栈顶元素，
     * 3、pop时，直接弹出栈顶元素即可
     */
    
    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();
    
    public void push(int node) {
        stack.push(node);
        if (minStack.isEmpty()) {
            minStack.push(node);
        } else {
            minStack.push(Math.min(minStack.peek(), node));
        }
    }
    
    public void pop() {
        stack.pop();
        minStack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int min() {
        return minStack.peek();
    }
}
