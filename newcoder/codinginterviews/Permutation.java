package com.codinginterviews.str;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
 
/**
 * 题目：
 * 字符串的排列 -- newcoder 剑指Offer 27
 * 
 * 题目描述：
 * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
 * 例如输入字符串abc,则打印出由字符a,b,c 所能排列出来的所有字符串
 * abc,acb,bac,bca,cab和cba。
 * 
 * 输入描述:
 * 输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
 */
public class Permutation
{
    /**
     * 思路：
     * 
     * 对于无重复值的情况
     *
     * 固定第一个字符，递归取得首位后面的各种字符串组合；
     * 再把第一个字符与后面每一个字符交换，并同样递归获得首位后面的字符串组合； *递归的出口，就是只剩一个字符的时候，
     * 递归的循环过程，就是从每个子串的第二个字符开始依次与第一个字符交换，然后继续处理子串。
     *
     * 假如有重复值呢？
     * 由于全排列就是从第一个数字起，每个数分别与它后面的数字交换，我们先尝试加个这样的判断——如果一个数与后面的数字相同那么这两个数就不交换了。
     * 例如abb，第一个数与后面两个数交换得bab，bba。然后abb中第二个数和第三个数相同，就不用交换了。
     * 但是对bab，第二个数和第三个数不 同，则需要交换，得到bba。
     * 由于这里的bba和开始第一个数与第三个数交换的结果相同了，因此这个方法不行。
     *
     * 换种思维，对abb，第一个数a与第二个数b交换得到bab，然后考虑第一个数与第三个数交换，此时由于第三个数等于第二个数，
     * 所以第一个数就不再用与第三个数交换了。再考虑bab，它的第二个数与第三个数交换可以解决bba。此时全排列生成完毕！
     * 
     */
    public ArrayList<String> permutation(String str) {
        ArrayList<String> ret = new ArrayList<>();
        
        if (str == null || str.isEmpty()) {
            return ret;
        }
        
        char[] arr = str.toCharArray();
        
        permutation(arr, 0, ret);
        
        Collections.sort(ret);
        return ret;
    }
    
    private void permutation(char[] arr, int begin, ArrayList<String> cache) {
        if (begin == arr.length - 1) {
            cache.add(new String(arr));
            return;
        }
        
        int len = arr.length;
        for (int i=begin; i<len; i++) {
            // 与begin不同位置的元素相等，不需要交换
            if (i!=begin && arr[i]==arr[begin]) {
                continue;
            }
            // 交换元素
            swap(arr, begin, i);
            // 处理后续元素
            permutation(arr, begin+1, cache);
            // 数组复原
            swap(arr, begin, i);
            
        }
    }
    
    private void swap(char[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        arr[i] = (char)(arr[i]^arr[j]);
        arr[j] = (char)(arr[i]^arr[j]);
        arr[i] = (char)(arr[i]^arr[j]);
    }
    
    
    /**
     * 思路2(摘自牛客网)：
     * 
     * 字典序排列算法
     *
     * 可参考解析： http://www.cnblogs.com/pmars/archive/2013/12/04/3458289.html  （感谢作者）
     *
     * 一个全排列可看做一个字符串，字符串可有前缀、后缀。
     * 生成给定全排列的下一个排列.所谓一个的下一个就是这一个与下一个之间没有其他的。
     * 这就要求这一个与下一个有尽可能长的共同前缀，也即变化限制在尽可能短的后缀上。
     *
     * [例]839647521是1--9的排列。1—9的排列最前面的是123456789，最后面的987654321，
     * 从右向左扫描若都是增的，就到了987654321，也就没有下一个了。否则找出第一次出现下降的位置。
     *
     * 【例】 如何得到346987521的下一个
     * 1，从尾部往前找第一个P(i-1) < P(i)的位置
     * 3 4 6 <- 9 <- 8 <- 7 <- 5 <- 2 <- 1
     * 最终找到6是第一个变小的数字，记录下6的位置i-1
     *
     * 2，从i位置往后找到最后一个大于6的数
     * 3 4 6 -> 9 -> 8 -> 7 5 2 1
     * 最终找到7的位置，记录位置为m
     *
     * 3，交换位置i-1和m的值
     * 3 4 7 9 8 6 5 2 1
     * 4，倒序i位置后的所有数据
     * 3 4 7 1 2 5 6 8 9
     * 则347125689为346987521的下一个排列
     *
     */
 
    public ArrayList<String> permutationII(String str){
        ArrayList<String> list = new ArrayList<String>();
        if(str==null || str.length()==0){
            return list;
        }
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        list.add(String.valueOf(chars));
        int len = chars.length;
        while(true){
            int lIndex = len-1;
            int rIndex;
            while(lIndex>=1 && chars[lIndex-1]>=chars[lIndex]){
                lIndex--;
            }
            if(lIndex == 0)
                break;
            rIndex = lIndex;
            while(rIndex<len && chars[rIndex]>chars[lIndex-1]){
                rIndex++;
            }
            swap(chars,lIndex-1,rIndex-1);
            reverse(chars,lIndex);
 
            list.add(String.valueOf(chars));
        }
 
        return list;
    }
 
    private void reverse(char[] chars,int k){
        if(chars==null || chars.length<=k)
            return;
        int len = chars.length;
        for(int i=0;i<(len-k)/2;i++){
            int m = k+i;
            int n = len-1-i;
            if(m<=n){
                swap(chars,m,n);
            }
        }
 
    }
    
    public static void main(String[] args)
    {
        System.out.println(new Permutation().permutation("abc"));
        System.out.println(new Permutation().permutationII("abc"));
    }
 
}

