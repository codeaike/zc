package com.codinginterviews;

/**
 * 题目：
 * 二维数组中的查找 -- 剑指Offer 1
 * 
 * 题目描述：
 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
 * 每一列都按照从上到下递增的顺序排序。
 * 
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * 
 */
public class TwoDimensionalArrayLookup
{
    /**
     * 思路：
     * 1、根据数组特点，从右上角位置开始查找
     * 2、如果相等，返回true即可
     * 3、如果目标值大于当前值，忽略本行，继续查找
     * 4、如果目标值小于当前值，忽略本列，继续查找
     */
    public boolean find(int target, int [][] array) {
        if (array == null) {
            return false;
        }
        
        int rowNum = array.length;
        if (rowNum <= 0) {
            return false;
        }
        
        int colNum = array[0].length;
        if (colNum <= 0) {
            return false;
        }
        
        // 定位到右上角元素
        int i = 0;
        int j = rowNum - 1;
        // 当前元素
        int curVal;
        // 遍历
        while (i < rowNum && j >= 0) {
            curVal = array[i][j];
            if (curVal == target) {
                return true;
            // 忽略本行，继续查找
            } else if (curVal < target) {
                i++;
            // 忽略本列继续查找
            } else {
                j--;
            }
            
        }
        return false;
    }
    
    public static void main(String[] args)
    {
        int[][] array = {{1,2,7},{3,5,8},{5,6,9}};
        TwoDimensionalArrayLookup instance = new TwoDimensionalArrayLookup();
        System.out.println(instance.find(1, array));
        System.out.println(instance.find(5, array));
        System.out.println(instance.find(9, array));
        System.out.println(instance.find(4, array));
        System.out.println(instance.find(10, array));
    }

}
