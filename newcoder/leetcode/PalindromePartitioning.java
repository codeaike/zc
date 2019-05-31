package com.leetcode.str;

import java.util.ArrayList;

/**
 * 题目：
 * palindrome-partitioning
 * 
 *
 * 题目描述：
 * Given a string s, partition s such that 
 * every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 * For example, given s ="aab",
 * Return
  [
    ["aa","b"],
    ["a","a","b"]
  ]
 * 
 */
public class PalindromePartition {

    public ArrayList<ArrayList<String>> partition(String s) {
    	if(s == null) {
    		return null;
    	}
    	
    	ArrayList<ArrayList<String>> caches = new ArrayList<>();
    	ArrayList<String> cache = new ArrayList<>();
    	partition(s,cache,caches);
    	
    	return caches;
    }
	
    public void partition(String s, ArrayList<String> cache, ArrayList<ArrayList<String>> caches) {
    	if (s == null || s.isEmpty()) {
    		caches.add(new ArrayList<>(cache));
    		return;
    	}
    	int len = s.length();
    	for (int i=1; i<=len; i++) {
    		String sub = s.substring(0,i);
    		// 前面回文数
    		if (isPalindrome(sub)) {
    			cache.add(sub);
    			// 处理后面的字符串
    			partition(s.substring(i, len), cache, caches);
    			// 恢复，删除列表的最后一个元素
    			cache.remove(cache.size() - 1);
    		}
    	}
    }

    private boolean isPalindrome(String str) {
    	return new StringBuilder(str).reverse().toString().equals(str);
    }
    
	public static void main(String[] args) {
		String s = "aab";
		ArrayList<ArrayList<String>> caches = new PalindromePartition().partition(s);
		for (ArrayList<String> lst: caches) {
			System.out.println(lst);
		}
	}

}
