package com.my.test.codinginterviews.stackqueue;

import java.util.Stack;

/**
 * 题目：
 * 栈的压入、弹出序列 -- newcoder 剑指Offer 21
 * 
 * 题目描述：
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
 * 假设压入栈的所有数字均不相等。
 * 
 * 例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，
 * 但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
 */
public class IsPreOrder
{
    /**
     * 思路：
     * 基于元素入栈序列，模拟出栈过程，最后还有元素残留，即为不是正确出栈序列
     * 
     * 1、pushA元素依次入栈，必须先入栈元素，否则最后一个元素无法正确处理
     * 2、循环--如果栈顶元素是要出栈的元素，则弹出，出栈元素索引+1
     * 3、最后返回，栈是否为空 即可
     */
    public boolean isPopOrder(int[] pushA, int[] popA) {
        // 校验
        if (pushA == null || popA == null) {
            return false;
        }
        
        int len1 = pushA.length;
        int len2 = popA.length;
        if (len1 == 0 || len2 == 0 || len1 != len2) {
            return false;
        }
        
        // 利用辅助栈处理
        Stack<Integer> stack = new Stack<>();
        
        // 要出栈元素索引
        int j=0;
        
        for (int i=0; i<len1; i++) {
            // 入栈元素
            stack.push(pushA[i]);
            // 栈顶元素=要弹出的元素，则栈中元素出栈
            while (!stack.empty() && stack.peek() == popA[j]) {
                stack.pop();
                j++;
            }
        }
        
        return stack.empty();
    }
    
    /**
     * 思路：
     * 基于元素出栈序列，模拟入栈过程
     * 
     * 解决这个问题很直观的想法就是建立一个辅助栈，把输入的第一个序列中的数字依次压入该辅助栈，并按照第二个序列的顺序依次从该栈中弹出数字。
     * 
     * 判断一个序列是不是栈的弹出序列的规律：如果下一个弹出的数字刚好是栈顶数字，那么直接弹出。
     * 如果下一个弹出的数字不在栈顶，我们把压栈序列中还没有入栈的数字压入辅助栈，直到把下一个需要弹出的数字压入栈顶为止。
     * 如果所有的数字都压入栈了仍然没有找到下一个弹出的数字，那么该序列不可能是一个弹出序列。
     */
    public boolean isPopOrderII(int[] pushA, int[] popA) {
        
        // 校验
        if (pushA == null || popA == null) {
            return false;
        }
        
        int len1 = pushA.length;
        int len2 = popA.length;
        if (len1 == 0 || len2 == 0 || len1 != len2) {
            return false;
        }
        
        // 利用辅助栈处理
        Stack<Integer> stack = new Stack<>();
        
        // 入栈序列索引
        int pushInx = 0;
        
        for (int i = 0; i < len2; i++) {
            int curEle = popA[i];
            
            // 如果当前元素不是要弹出的元素 则数据入栈
            while (pushInx < len1 && (stack.empty() || stack.peek() != curEle)) {
                stack.push(pushA[pushInx]);
                pushInx++;
            }
            
            // 弹出当前元素
            if (stack.peek() == curEle) {
                stack.pop();
            } else {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * 思路：
     * 此思路与以上两种类似， 都是基于元素出栈序列，模拟入栈过程
     */
    public boolean isPopOrderIII(int [] pushA, int [] popA) {
        // 都为空 返回false
        if (pushA == null || popA == null) {
            return false;
        }
        
        int lenPush = pushA.length;
        int lenPop = popA.length;
        
        // 长度不一致返回false
        if (lenPush == 0 || lenPop == 0 || lenPush != lenPop) {
            return false;
        }
        
        // 利用一个栈缓存入栈元素
        Stack<Integer> stack = new Stack<>();
        stack.push(pushA[0]);
        // 已经入栈的元素在pushA中的最大索引
        int i = 0;
        // 遍历弹出序列
        for (int j=0; j<lenPop; j++) {
            // 当前要弹出的元素
            int cur = popA[j];
            // 查看栈顶是否为该元素，不是则入栈剩余元素，直到栈顶是该元素，找不到则返回false
            if (!stack.empty() && stack.peek() == cur) {
                stack.pop();
            } else {
                // 余下未入栈的元素中是否有要弹出的元素
                while (i<lenPush && pushA[i] != cur) {
                    stack.push(pushA[i]);
                    i++;
                }
                // 遍历完序列未找到
                if (i == lenPush) {
                    return false;
                // 找到了目标元素，此时需要入栈元素，然后弹出元素，计算器+1
                // 简化后为计算器+1即可
                } else {
                    i++;
                }
            }
        }
        
        return true;
    }
    
    public static void main(String[] args)
    {
        int[] pushA = {1,2,3,4,5};
        int[] popA1 = {4,5,3,2,1};
        int[] popA2 = {4,3,5,1,2};
        System.out.println(new IsPreOrder().isPopOrder(pushA, popA1));
        System.out.println(new IsPreOrder().isPopOrder(pushA, popA2));
        System.out.println(new IsPreOrder().isPopOrderII(pushA, popA1));
        System.out.println(new IsPreOrder().isPopOrderII(pushA, popA2));
        System.out.println(new IsPreOrder().isPopOrderIII(pushA, popA1));
        System.out.println(new IsPreOrder().isPopOrderIII(pushA, popA2));
    }

}
