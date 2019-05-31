package com.codinginterviews.str;


/**
 * 题目：
 * 翻转单词顺序列 -- newcoder 剑指Offer 44
 * 
 * 题目描述：
 * 
牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。
同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。
例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，
正确的句子应该是“I am a student.”。
Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
 */
public class ReverseSentence {

	/**
	 * 思路： 
	 * 1、先把每个单词都进行反转
	 * 2、再把字符串全部反转
	 */
    public String reverseSentence(String str) {
        if(str == null || str.length() == 0) {
        	return str;
        }
        
        char[] arr = str.toCharArray();
        int len = arr.length;
        
        int start = 0;
        int end = 0;
        
        // 反转每个单词
        while (start < len && end < len) {
        	while (end < len && arr[end] != ' ') {
        		end++;
        	}
        	
        	reverse(arr, start, end - 1);
        	
        	start = end + 1;
        	end = start;
        }
        
        // 全量反转
        reverse(arr, 0, len-1);
        
        return new String(arr);
    }
	
    private void reverse(char[] arr, int i, int j) {
    	if (i == j) {
    		return;
    	}
    	while (i < j) {
    		char tmp = arr[i];
    		arr[i] = arr[j];
    		arr[j] = tmp;
    		
    		i++;
    		j--;
    	}
    }
    
	public static void main(String[] args) {
		String str = " student. a am  I ";
		System.out.println(new ReverseSentence().reverseSentence(str));
	}

}
