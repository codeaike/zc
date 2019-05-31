package com.leetcode.str;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * 题目：
 * valid-palindrome -- newcoder 26
 * 验证回文字符串 -- leetcode 125
 * 
 * 题目描述：
 * Given a string, determine if it is a palindrome, 
 * considering only alphanumeric(字母或数字) characters and ignoring cases.
 * For example,
 * "A man, a plan, a canal: Panama"is a palindrome.
 * "race a car"is not a palindrome.
 * 
 * Note: 
 * Have you consider that the string might be empty? This is a good question to ask during an interview.
 * For the purpose of this problem, we define empty string as valid palindrome. 
 */
public class ValidPalindrome {

	/**
	 * 思路1：(首尾指针分别遍历到元素末尾和起始位置)
	 * 1、使用双指针分别从字符串首尾开始遍历(需要先去除首尾非字母数字元素)
	 * 2、如果是非字母、数字字符，则忽略，指针前移
	 * 3、如果是字母数字，则比较两个指针指向的字符是否相同，不相同则返回false
	 * 
	 */
    public boolean isPalindrome(String s) {
        if (s == null || s.length() <= 1) {
        	return true; 
        }
        
        char[] arr = s.toCharArray();

        // 去除首尾 非字母数字元素，避免下面遍历时指针越界
        arr = removeNoneAlphanumericChar(arr);
        if (arr == null || arr.length <= 1) {
        	return true;
        }
        
        int len = arr.length;
        
        // 首尾两个指针
        int start = 0;
        int end = len - 1;
        
        // 首尾指针同时遍历 
        while (start <= len - 1 || end >= 0) {
        	// 头指针遍历 直到遍历到是字母数字的char位置
        	while (!isAlphanumericChar(arr[start])) {
        		start++;
        	}
        	// 尾指针遍历 直到遍历到是字母数字的char位置
        	while(!isAlphanumericChar(arr[end])) {
        		end--;
        	}
        	
        	// 首尾指针指向元素 是否忽略大小写相等
        	if (arr[start] != arr[end] && Math.abs(arr[start] - arr[end]) != 32) {
        		return false;
        	}
        	
        	// 指针前移
    		start++;
    		end--;
        }
        
        return true;
    }
	
    private char[] removeNoneAlphanumericChar(char[] arr) {
    	int len = arr.length;
    	int start = 0;
    	int end = len - 1;
    	while (!isAlphanumericChar(arr[start])) {
    		start++;
    		// 全部为非数字字母
    		if (start >= len) {
    			return null;
    		}
    	}
    	while (!isAlphanumericChar(arr[end])) {
    		end--;
    		if (end < 0) {
    			return null;
    		}
    	}
    	return Arrays.copyOfRange(arr, start, end + 1);
    }
    
    private boolean isAlphanumericChar(char c) {
    	String regex = "[0-9a-zA-Z]{1}";
    	if (Pattern.matches(regex, String.valueOf(c))) {
    		return true;
    	}
    	return false;
    }
    
	/**
	 * 思路2：(首尾指针分别遍历，直到头指针i>=尾指针j)
	 * 1、使用双指针分别从字符串首尾开始遍历(条件为i<j)
	 * 2、如果是非字母、数字字符，则忽略，指针前移(i<j && xxx)
	 * 3、如果是字母数字，则比较两个指针指向的字符是否相同，不相同则返回false(i<j && xxx)
	 * 
	 */
    public boolean isPalindromeFast(String s) {
    	if (s == null || s.length() <= 1) {
    		return true;
    	}
    	int len = s.length();
    	char[] arr = s.toCharArray();
    	int i = 0;
    	int j = len - 1;
    	while (i < j) {
    		while (i<j && !isAlphanumericChar(arr[i])) {
    			i++;
    		}
    		while (i<j && !isAlphanumericChar(arr[j])) {
    			j--;
    		}
    		if (i<j && arr[i] != arr[j] && Math.abs(arr[i]-arr[j]) != 32) {
    			return false;
    		}
    		i++;
    		j--;
    	}
    	return true;
    }
    
	public static void main(String[] args) {
		System.out.println(new ValidPalindrome().isPalindrome("A man, a plan, a canal: Panama"));
		System.out.println(new ValidPalindrome().isPalindrome("race a car"));
		System.out.println(new ValidPalindrome().isPalindrome("a."));
		System.out.println(new ValidPalindrome().isPalindrome(".,"));
		
		System.out.println(new ValidPalindrome().isPalindromeFast("A man, a plan, a canal: Panama"));
		System.out.println(new ValidPalindrome().isPalindromeFast("race a car"));
		System.out.println(new ValidPalindrome().isPalindromeFast("a."));
		System.out.println(new ValidPalindrome().isPalindromeFast(".,"));
	}

}
