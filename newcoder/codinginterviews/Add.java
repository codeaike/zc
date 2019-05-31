package com.codinginterviews.bitoper;

/**
 * 题目：
 * 不用加减乘除做加法 -- newcoder 剑指Offer 48
 * 
 * 题目描述：
 * 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
 */
public class Add {
 
	/**
	 * 思路：
	 * 1、整数转变为二进制后，相加为0,0->0 0,1->1 1,1->0. 先不考虑进位，为异或操作结果
	 * 2、考虑进位，只有1,1时需要进位，则两数字相与，只有1,1为1，这样就找到了需要进位的，然后该数左移一位
	 * 3、前面两部的结果相加，相加的过程仍然是重复以上两个步骤，直到不产生新的进位为止
	 */
	public static int add(int num1, int num2) {
		int sum = 0;
		int c = 0;
		do {
			// 第一步
			sum = num1 ^ num2;
			// 第二步，并左移结果
			c = (num1 & num2) << 1;
			// 第三步,相加sum c
			num1 = sum;
			num2 = c;
		} while(c != 0);
		
		return sum;
	}
	
	public static void main(String[] args) {
		System.out.println("27+35: " + add(27,35));
	}
 
}

