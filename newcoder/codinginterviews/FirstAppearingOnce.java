package com.codinginterviews.str;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目：
 * 字符流中第一个不重复的字符 -- newcoder 剑指Offer 53
 * 
 * 题目描述：
请实现一个函数用来找出字符流中第一个只出现一次的字符。
例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。

输出描述:
如果当前字符流没有存在出现一次的字符，返回#字符。
 * 
 */
public class FirstAppearingOnce {
	
	// 存储每个字符出现的次数(char范围为-128~127，其中ASCII表对照0~127)
	private int[] countCache = new int[256];
	// 存储输入的char
	private Queue<Character> queue = new LinkedList<>();
	
	
	/**
	 * 思路： 
	 *  1、用一个128大小的数组统计每个字符出现的次数
     *  2、用一个队列，如果第一次遇到ch字符，则插入队列；其他情况不在插入
     *  3、求解第一个出现的字符，判断队首元素是否只出现一次，如果是直接返回，否则删除继续第3步骤
	 */
	
    // Insert one char from stringstream
    public void insert(char ch)
    {
        countCache[ch] += 1;
        if (countCache[ch] == 1) {
        	queue.offer(ch);
        }
    }
    
    // return the first appearence once char in current stringstream
    public char firstAppearingOnce()
    {
    	char res = '#';
    	if (queue.size() <= 0) {
    		return res;
    	}
    	while (!queue.isEmpty()) {
    		char cur = queue.peek();
    		if (countCache[cur] == 1) {
    			return cur;
    		} else {
    			queue.poll();
    		}
    	}
    	return res;
    }
    
}
