package com.codinginterviews;
 
/**
 * ReplaceSpace<br>
   剑指Offer 2
 * 
 * 题目描述:
 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 * 
 */
public class ReplaceSpace
{
 
    /**
     * 使用string的库函数
     */
    public static String replaceSpaceByStr(StringBuffer str) {
        if (str == null) {
            return null;
        }
        return str.toString().replaceAll(" ", "%20");
    }
    
    /**
     * 使用char数组
     * 
     * 思路：
     * 1、扩容数组为新数组
     * 2、两个指针，一个指向原数组最后一位，一个指向新数组最后一位，开始替换元素
     */
    public static String replaceSpace(StringBuffer str) {
        if (str == null) {
            return null;
        }
        char[] arr = str.toString().toCharArray();
        return replaceSpace(arr);
    }
    
    /**
     * @param arr 字符串转换的字符数组
     * @return
     */
    public static String replaceSpace(char[] arr) {
        int len = arr.length;
        if (len <= 0) {
            return "";
        }
        
        // 统计几个空格，计算扩容后数组长度
        int spaceCount = 0;
        for (char c : arr) {
            if (c == ' ') {
                spaceCount++;
            }
        }
        
        // 数组扩容
        // 用位移运算符时，要注意运算符优先级，+ 优先于 <<，所以要加括号
        int newLength = len + (spaceCount << 1);
        char[] newArr = new char[newLength];
        System.arraycopy(arr, 0, newArr, 0, len);
        
        // 新数组中替换' ' => %20
        int curIndex = len - 1;
        int newIndex = newLength - 1;
        while (curIndex >= 0) {
            // ' '替换为%20
            if (newArr[curIndex] == ' ') {
                newArr[newIndex--] = '0';
                newArr[newIndex--] = '2';
                newArr[newIndex--] = '%';
                curIndex--;
            // 非空 则后移元素，计位器前移
            } else {
                newArr[newIndex--] = newArr[curIndex--];
            }
        }
        
        return new String(newArr);
    }
    
    public static void main(String[] args)
    {
        System.out.println(replaceSpaceByStr(new StringBuffer("We are happy")));
        System.out.println(replaceSpace(new StringBuffer("We are happy")));
    }
 
}
