package com.leetcode.str;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目：
 * 单词接龙 -- leetcode 127
 * word-ladder -- newcoder 25
 * 
 * 题目描述：
 * Given two words (start and end), and a dictionary, 
 * find the length of shortest transformation sequence from start to end, 
 * such that:
 * Only one letter can be changed at a time
 * Each intermediate word must exist in the dictionary
 * 
 * For example,
 * Given:
 * start ="hit"
 * end ="cog"
 * dict =["hot","dot","dog","lot","log"]
 * As one shortest transformation is"hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length5.
 * 
 * Note:
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * 
 */
public class WordLadder {

	/**
	 * 思路：
	 * 1、广度优先搜索思想
	 * 2、新建一个队列，队列第一个元素初始化为start 
	 * 3、判断当前队列中的元素是否与end只差别一个字母，是则返回
	 * 3、否则放入dict中与start相差一个的所有元素为队列的下一组元素
	 * 3、队列中没有元素了，还没找到，则返回0
	 */
    public int ladderLength(String start, String end, HashSet<String> dict) {
        if (dict == null || start == null || end == null) {
        	return 0;
        }

        // 队列初始化，第一个元素为start
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        
        // 初始化单词接龙元素个数
        int lenRes = 0;

        // 循环队列
        while (!queue.isEmpty()) {
        	// 已处理过得层数+1
        	lenRes++;
        	
        	// 当前处理的队列元素数量
        	int size = queue.size();
        	// 还有元素需要处理，即上一次处理，队列中发现了多余一个的可以接龙的单词
        	while (size > 0) {
            	// 弹出队列中的一个元素，并记数
            	String str = queue.poll();
            	// 当前层的元素数-1
            	size--;
            	// 判断是否与end元素仅相差一个char
            	if (isDiffOneChar(str, end)) {
            		return lenRes + 1;
            	}
            	// 字典中有与当前str只相差一个char的元素，加入到队列中，下次循环处理
            	else {
            		Iterator<String> its = dict.iterator();
            		while (its.hasNext()) {
            			String cur = its.next();
            			// 字典中的元素与当前元素只差一个char，则加入队列，值从dict中移除
            			if (isDiffOneChar(str, cur)) {
            				// 加入队列
            				queue.offer(cur);
            				// 从字典中删除
            				its.remove();
            			}
            		}
            	}
        	}
        }
        
        return 0;
    }
	
    // 是都相差一个char，输入的str length相同
    private boolean isDiffOneChar(String str1, String str2) {
    	int len = str1.length();
    	int count = 0;
    	for (int i=0; i<len; i++) {
    		if (str1.charAt(i) != str2.charAt(i)) {
    			count++;
    		}
    	}
    	return count == 1;
    }
    
	public static void main(String[] args) {
		String start = "hit";
		String end = "cog";
		String[] dict = {"hot","dot","dog","lot","log"};
		HashSet<String> set = new HashSet<>(Arrays.asList(dict));
		System.out.println(new WordLadder().ladderLength(start, end, set));
	}

}
