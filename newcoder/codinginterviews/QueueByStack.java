package com.codinginterviews;
 
import java.util.Stack;
 
/**
 * 两个栈实现队列 -- 剑指Offer 5
 * 
 * 思路：
 * 添加元素时 向stack1中添加
 * 删除元素时 从stack2中删除
 * 
 */
public class QueueByStack {
 
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    
    public void push(int node) {
        stack1.push(node);
    }
    
    public int pop() {
        if (stack2.empty() && stack1.empty()) {
            // 假设正常入栈数据不存在-1，-1认为非法
            return -1;
        }
        if (!stack2.empty()) {
            return stack2.pop();
        }
        while (!stack1.empty()) {
            stack2.push(stack1.pop());
        }
        return stack2.pop();
        
    }
 
}
