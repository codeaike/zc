package com.codinginterviews.array;

/**
 * 题目：
 * 求1+2+3+...+n -- newcoder 剑指Offer 47
 * 
 *  
 * 题目描述：
 * 求1+2+3+...+n，要求不能使用乘除法、
 * for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。  
 *
 */
public class Sum_Solution {

	/**
	 * 思路：
	 * 1、利用 && 的短路特性实现递归，n<=0时，不运行后面的语句
	 */
    public int sum_Solution(int n) {
        int sum = n;
        
        boolean flag = (n > 0) &&  (sum += sum_Solution(n-1)) > 0;

        return sum;
    }
    
    /**
     * 思路：
     * 1、使用公式 1+2+...+n = n*(n+1)/2 = (n^2 + n) >> 1
     */
    public int sum_SolutionII(int n) {
        return ((int)(Math.pow(n, 2)+n)) >> 1;
    }
	
	public static void main(String[] args) {
		System.out.println(new Sum_Solution().sum_Solution(5));
	}

}
