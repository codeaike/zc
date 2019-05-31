package com.codinginterviews.str;

/**
 * 题目描述：
 * 左旋转字符串 -- newcoder 剑指Offer 43
 * 
 * 题目描述：
汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，
就是用字符串模拟这个指令的运算结果。对于一个给定的字符序列S，
请你把其循环左移K位后的序列输出。例如，字符序列S=”abcXYZdef”,
要求输出循环左移3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！ 
 */
public class LeftRotateString
{
    /**
     * 思路：
     * 1、如反转两位，asdfgh => dfghas
     * 2、先分别反转前两位元素、后几位元素(=>sahgfd)，在全量反转字符串(=>dfghsa)，得到目标字符串
     */
    public static String leftRotateString(String str, int n) {
        if (str == null || str.isEmpty() || n <= 0 || n >= str.length()) {
            return str;
        }
        char[] arr = str.toCharArray();
        
        // 交换前几个字符
        reverse(arr, 0, n - 1);
        
        // 交换后几个字符
        int len = arr.length;
        reverse(arr, n, len - 1);
        
        // 全量反转
        reverse(arr, 0, len - 1);
        
        return new String(arr);
    }
    
    /**
     * 反转char数组中指定区间的元素 
     */
    private static void reverse(char[] arr, int start, int end) {
        if (end - start < 1) {
            return;
        }
        while (start < end) {
            // 交换两个位置上的元素
            // 使用异或运算交换，不需要额外空间
            arr[start] = (char)(arr[start] ^ arr[end]);
            arr[end] = (char)(arr[start] ^ arr[end]);
            arr[start] = (char)(arr[start] ^ arr[end]);
            
            start++;
            end--;
        }
    }
    
    public static void main(String[] args)
    {
        String str = "ABC DEF GHI  JK ";
        System.out.println(leftRotateString(str, 2));
        
        String str1 = "ABCDEF";
        System.out.println(leftRotateString(str1, 0));
    }

}

