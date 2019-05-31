package com.codinginterviews.array;
 
/**
 * 题目：
 * 数组中只出现一次的数字 -- newcoder 剑指Offer 40
 * 
 * 题目描述：
 * 一个整型数组里除了两个数字之外，其他的数字都出现了两次。
 * 请写程序找出这两个只出现一次的数字。
 */
public class FindNumsAppearOnce
{
    public void findNumsAppearOnce(int [] array, int num1[] , int num2[]) {
        if (array == null || array.length <= 1) {
            return;
        }
 
        int len = array.length;
        
        // 得到异或结果
        int result = 0;
        for (int i=0; i<len; i++) {
            result ^= array[i];
        }
        
        int flag = 1;
        // 找到右起第一个为1的位置，其余位置赋值为0
        while ((result & flag) == 0) {
        	flag <<= 1;
        }
        
        for (int i=0; i<len; i++) {
        	// 以该位置是否为0分割数组
            if ((flag & array[i]) == 0) {
            	num1[0] ^= array[i];
            } else {
            	num2[0] ^= array[i];
            }
        }
    }
	
	
    /**
     * 思路：
     * 1、两个数字相同，异或结果为0
     * 2、对所有数字取异或，右起找第一个二进制位为1的位置。
     * 3、按照该二进制是否为1，拆分为两个数组，则两个出现一次的不同的数字必然分割在两个数组
     * 4、对两个数组分别进行异或操作即可
     */
    // num1,num2分别为长度为1的数组。传出参数
    // 将num1[0],num2[0]设置为返回结果
    public void findNumsAppearOnceI(int [] array, int num1[] , int num2[]) {
        if (array == null || array.length <= 1) {
            return;
        }
 
        int len = array.length;
        
        // 得到异或结果
        int result = 0;
        for (int i=0; i<len; i++) {
            result ^= array[i];
        }
        
        // 得到右起二进制位位1的索引
        String resultToBinaryString = Integer.toBinaryString(result);
        // 从右开始
        int binaryIdx = resultToBinaryString.length() - 1;
        for (; binaryIdx>=0; binaryIdx--) {
            if (resultToBinaryString.charAt(binaryIdx) == '1') {
                break;
            }
        }
        // 得到右起索引
        binaryIdx = resultToBinaryString.length() - 1 - binaryIdx;
        
        // 以该索引位置的值是否为1分割数组
        int num1Val = 0;
        int num2Val = 0;
        
        for (int i=0; i<len; i++) {
            String str = Integer.toBinaryString(array[i]);
            int strLen = str.length();
            // 通过右起索引 找到真正的索引
            int curIdx = strLen - 1 - binaryIdx;
            if (curIdx > 0 && strLen > curIdx && str.charAt(curIdx) == '1') {
                num1Val ^= array[i];
            } else {
                num2Val ^= array[i];
            }
        }
        
        // 赋值元素到num1[] num2[]
        num1[0] = num1Val;
        num2[0] = num2Val;
    }
 
     
    /**
     * 题目：
     * 数组中除了一个数字，其他的数字都出现两次，找出这个数字
     * 
     * 思路：
     * 1、两个相同的数字做异或操作，结果是0
     * 2、数组中所有的数字做异或操作，最后的结果就是出现了一次的唯一数字
     */
    public int findNumAppearOnce(int[] arr) {
        if (null == arr || arr.length <= 0) {
            return -1;
        }
        
        int result = 0;
        
        for (int i : arr) {
            result ^= i;
        }
        
        return result;
    }
    
    /**
     * 目标：
     * 数组中除了两个数字，其他的数字都出现两次，找出这两个数字
     * 
     * 思路：
     * 1、两个相同的数字做异或操作，结果是0
     * 2、数组中所有的数字做异或操作，最后的结果必然不为0
     * 3、找出结果中二进制位中最后一个为1的二进制位n
     * 4、按照二进制位n是否为1分类数组为两份，则1个数字在第一组，两一个必在第二组，且两组中其他的也都是出现了两次的数字
     * 5、分类后分别进行异或操作
     */
    public void findNumsAppearOnceII(int[] array, int[] num1, int[] num2) {
        if (null == array || array.length <= 1) {
            return;
        }
        
        // 数组中的元素进行异或操作
        int allXOR = 0;
        for (int i : array) {
            allXOR ^= i;
        }
        
        // 获取异或结果转化为二进制 最后一个为1的二进制位
        int index = getLastBinaryOneIndex(allXOR);
        
        
        // 根据数据的倒数index位是否为1来分割数组
        for (int j : array) {
            if (isNumBinaryOneIndex(j, index)) {
                num1[0] ^= j;
            } else {
                num2[0] ^= j;
            }
        }
        
    }
    
    
    /**
     * 获取数字转化为二进制 倒数第几位为1
     * 从1开始计数，如1的返回值为1,2的返回值为2 
     */
    private int getLastBinaryOneIndex(int num) {
        int index = 1;
        while ((num & 1) != 1 && index < 32) {
            num = num >>> index;
            index++;
        }
        return index;
    }
    
    /**
     * 数字的二进制-倒数 binaryOneIndex位是否为1
     * 
     * 为1返回true
     * 为0返回false
     * 
     */
    private boolean isNumBinaryOneIndex(int num, int binaryOneIndex) {
        int numRightIndex = num >>> (binaryOneIndex - 1);
        return (numRightIndex & 1) == 1;
    }
        
    
    public static void main(String[] args)
    {
        int[] array = {4,6,1,1,1,1};
        int[] num1 = new int[1];
        int[] num2 = new int[1];
        new FindNumsAppearOnce().findNumsAppearOnce(array, num1, num2);
        System.out.println(num1[0] + " " + num2[0]);
        
        num1[0] = 0;
        num2[0] = 0;
        new FindNumsAppearOnce().findNumsAppearOnceII(array, num1, num2);
        System.out.println(num1[0] + " " + num2[0]);
    }
 
}
