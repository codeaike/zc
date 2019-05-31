package com.codinginterviews.str;

/**
 * 题目：
 * 把字符串转换成整数 -- newcoder 剑指Offer 49
 * 
 * 题目描述：
将一个字符串转换成一个整数(实现Integer.valueOf(string)的功能，
但是string不符合数字要求时返回0)，要求不能使用字符串转换整数的库函数。 
数值为0或者字符串不是一个合法的数值则返回0。
输入描述:
输入一个字符串,包括数字字母符号,可以为空
输出描述:
如果是合法的数值表达则返回该数字，否则返回0
 * 
 */
public class StrToInt {
	
	/**
	 * 思路： 
	 * 1、正负号判断
	 * 2、有符号无符号
	 * 3、'0'的ASCII码为48
	 */
    public int strToInt(String str) {
        if (str == null || str.isEmpty()) {
        	return 0;
        }
        
        boolean isNegative = false;
        
        char[] arr = str.toCharArray();
        
        int res = 0;        
        int len = arr.length;
        for (int i=0; i<len; i++) {
        	char cur = arr[i];
            if (i == 0 && (cur == '-' || cur == '+')) {
            	isNegative = cur == '-';
            } else if (cur >= '0' && cur <= '9') {
            	// res * 10 = res << 1 + res << 3
            	res = (res << 1) + (res << 3) + ((int)cur - 48);
        	} else {
        		return 0;
        	}
        }
        
        return isNegative ? res * (-1) : res;
    }
	
	public static void main(String[] args) {
		String str = "+2182";
		System.out.println(new StrToInt().strToInt(str));
		String str1 = "-2182";
		System.out.println(new StrToInt().strToInt(str1));
	}
}
