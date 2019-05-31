package com.my.test.codinginterviews.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 题目：
 * 把数组排成最小的数 -- newcoder 剑指Offer 32
 * 
 * 题目描述：
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
 * 
 */
public class PrintMinNumber
{
    
    /**
     * 思路1(自己进行快排)：
     * 1、建立一个排序规则，如 m、n两个数字，看是mn这样组合还是nm这样组合，哪种情况比较小，哪个数字就放在前面
     * 2、上述排序规则总注意mn组合后超过int范围的问题
     * 3、快排数组中的元素
     * 4、按排序后的结果组成数字
     * 
     * 思路2(使用Collections.sort)
     * 1、数组转List<Integer>
     * 2、自定义规则排序，排序后，拼接字符串即可
     */
    public String printMinNumber(int [] numbers) {
        StringBuilder res = new StringBuilder();
        
        if (numbers == null || numbers.length <= 0) {
            return res.toString();
        }
        
        int len = numbers.length;
        quickSort(numbers, 0, len-1);
        
        for (int num : numbers) {
            res.append(num);
        }
        
        return res.toString();
    }
    
    private void quickSort(int[] numbers, int first, int last) {
        if (first < last) {
            int mid = getMid(numbers, first, last);
            quickSort(numbers, first, mid-1);
            quickSort(numbers, mid+1, last);
        }
    }
    
    private int getMid(int[] numbers, int begin, int end) {
        int base = numbers[begin];
        while (begin < end) {
            while (begin < end && userDefineCompare(numbers[end],base) >= 0) {
                end--;
            }
            numbers[begin] = numbers[end];
            
            while (begin < end && userDefineCompare(numbers[begin],base) <= 0) {
                begin++;
            }
            numbers[end] = numbers[begin];
        }
        numbers[begin] = base;
        return begin;
    }
    
    // 自定义比较规则： num1num2 < num2num1 则 认为num1<num2
    private int userDefineCompare(int num1, int num2) {
        if (num1 == num2) {
            return 0;
        }
        
        String num1Str = String.valueOf(num1);
        String num2Str = String.valueOf(num2);
        
        return (num1Str + num2Str).compareTo(num2Str + num1Str);
    }

    /**
     * 思路2(使用Collections.sort)
     * 1、数组转List<Integer>
     * 2、自定义规则排序，排序后，拼接字符串即可
     */
    public String printMinNumberII(int [] numbers) {
        StringBuilder res = new StringBuilder();
        
        if (numbers == null || numbers.length <= 0) {
            return res.toString();
        }
        
        List<Integer> list = new ArrayList<>();
        for (int num : numbers) {
            list.add(num);
        }
        
        Collections.sort(list, new Comparator<Integer>(){

            @Override
            public int compare(Integer o1, Integer o2)
            {
                return userDefineCompare(o1, o2);
            }
            
        });
        
        for (Integer i : list) {
            res.append(i);
        }
        
        return res.toString();
    }
    
    public static void main(String[] args)
    {
        int[] arr = {3, 32, 321};
        System.out.println("min number: " + new PrintMinNumber().printMinNumber(arr));
        System.out.println("min number: " + new PrintMinNumber().printMinNumberII(arr));
        
        int[] arr1 = {1, 3, 5, 4, 2};
        System.out.println("min number: " + new PrintMinNumber().printMinNumber(arr1));
        System.out.println("min number: " + new PrintMinNumber().printMinNumberII(arr1));
    }

}
