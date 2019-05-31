package com.my.test.codinginterviews.str;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 题目：
 * 第一个只出现一次的字符 --newcoder 剑指Offer 34
 * 
 * 题目描述：
 * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,
 * 并返回它的位置, 如果没有则返回 -1（需要区分大小写）.
 */
public class FirstNotRepeatingChar
{
    /**
     * 思路：
     * 1、遍历字符串，Hash存储字符串每个字符出现的次数
     * 2、顺序遍历上面存储的结果，如果该字符出现次数为1次，则找到该字符及其位置
     */
    public int firstNotRepeatingChar(String str) {
        if (str == null || str.isEmpty()) {
            return -1;
        }
        
        // 使用有序Map
        Map<Character, Integer> cache = new LinkedHashMap<>();
        
        int len = str.length();
        // 遍历字符串
        for (int i=0; i<len; i++) {
            Character cur = str.charAt(i);
            Integer val = cache.get(cur);
            if (val == null) {
                cache.put(cur, 1);
            } else {
                cache.put(cur, val + 1);
            }
        }
        
        int res = 0;
        // 遍历缓存
        for (int j=0; j<len; j++) {
            if (cache.get(str.charAt(j)) == 1) {
                break;
            }
            res++;
        }
        
        return res;
    }
    
    
    public static void main(String[] args)
    {
        System.out.println(new FirstNotRepeatingChar().firstNotRepeatingChar("googgle"));
        System.out.println(new FirstNotRepeatingChar().firstNotRepeatingChar("leetcode"));
        System.out.println(new FirstNotRepeatingChar().firstNotRepeatingChar("loveleetcode"));
    }

}
