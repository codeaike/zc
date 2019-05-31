package com.my.test.leetcode.array;

import java.util.ArrayList;

/**
 * 题目：
 * pascals-triangle-ii -- newcoder 32
 * 杨辉三角II -- leetcode 119
 * 
 * 题目描述:
 * 
Given an index k, return the k th row of the Pascal's triangle.

For example, given k = 3,
Return[1,3,3,1].

Note: 
Could you optimize your algorithm to use only O(k) extra space?
 *
 */
public class PascalsTriangleII {

    /**
     * 思路：
     * 1、根据杨辉三角特点(每个数是它左上方和右上方的数的和)生成元素
     * 2、使用固定大小的列表缓存每行元素，从第一行开始递推下一行元素
     * 3、需要缓存两个列表，一个为上一行元素，一个为本行元素，正序遍历赋值本行元素
     */
    public ArrayList<Integer> getRow(int rowIndex) {
        if (rowIndex < 0) {
            return new ArrayList<>();
        }
        
        int len = rowIndex + 1;
        ArrayList<Integer> pre = new ArrayList<>(len);
        ArrayList<Integer> ret = new ArrayList<>(len);
        for (int i=0; i<=rowIndex; i++) {
            // 更新该行元素
            for (int j=0; j<=i; j++) {
                // 更新首尾元素
                if (j==0 || j==i) {
                    ret.add(1);
                    continue;
                }
                // 更新中间元素
                ret.add(pre.get(j-1) + pre.get(j));
            }
            // 非最后一次循环，更新缓存
            if (i < rowIndex) {
                // 一行遍历结束更新缓存
                pre = new ArrayList<>(ret);
                ret = new ArrayList<>(len);
            }
        }
        
        return ret;
    }
    
    /**
     * 思路：
     * 1、根据杨辉三角特点(每个数是它左上方和右上方的数的和)生成元素
     * 2、使用固定大小的列表缓存每行元素，从第一行开始递推下一行元素
     * 3、缓存一个列表即可，倒序遍历赋值本行元素
     */
    public ArrayList<Integer> getRowII(int rowIndex) {
        if (rowIndex < 0) {
            return new ArrayList<>();
        }
        
        int len = rowIndex + 1;
        ArrayList<Integer> ret = new ArrayList<>(len);
        // 先添加一个元素，初始化ret容量,否则set(idx,value)报错
        ret.add(1);
        for (int i=1; i<=rowIndex; i++) {
            // 倒序更新该行元素
            for (int j=i-1; j>=0; j--) {
                // 更新首尾元素
                if (j==0) {
                    ret.add(1);
                    continue;
                }
                // 更新中间元素
                ret.set(j, ret.get(j-1) + ret.get(j));
            }
        }
        
        return ret;
    }
    
    public static void main(String[] args) {
        System.out.println(new PascalsTriangleII().getRow(3));
        System.out.println(new PascalsTriangleII().getRow(5));
        System.out.println(new PascalsTriangleII().getRowII(3));
        System.out.println(new PascalsTriangleII().getRowII(5));
    }

}
