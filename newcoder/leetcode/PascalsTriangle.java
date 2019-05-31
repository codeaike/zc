package com.leetcode.array;

import java.util.ArrayList;

/**
 * 题目：
 * pascals-triangle -- newcoder 33
 * 杨辉三角 -- leetcode 118
 * 
 * 题目描述:
 * 
Given numRows, generate the first numRows of Pascal's triangle.

For example, given numRows = 5,
Return

[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]

 *
 */
public class PascalsTriangle {

	/**
	 * 思路：
	 * 1、根据杨辉三角特点(每个数是它左上方和右上方的数的和)生成元素
	 * 2、第一行元素可单独处理
	 * 3、其余行的元素生成，需要首先获取上一行元素，两侧元素为1，中间元素=上一行的相邻元素相加
	 */
    public ArrayList<ArrayList<Integer>> generate(int numRows) {
    	ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
    	
    	if (numRows <= 0) {
    		return ret;
    	}

    	ArrayList<Integer> first = new ArrayList<>();
    	first.add(1);
    	ret.add(first);
    	
    	// 根据杨辉三角特点，在杨辉三角中，每个数是它左上方和右上方的数的和，组合元素
    	for (int i=1; i<numRows; i++) {

    		ArrayList<Integer> pre = ret.get(i - 1);
    		int len = pre.size();
    		
    		ArrayList<Integer> cur = new ArrayList<>(len+1);
    		
    		// 赋值两侧元素(值为1) + 中间元素
    		for (int j=0; j <= len; j++) {
    			// 两侧元素
    			if (j == 0 || j == len) {
    				cur.add(1);
    				continue;
    			}
    			
    			// 获取上一行中相邻元素的值
    			int left = pre.get(j-1);
    			int right = pre.get(j);
    			// 加和即为本行元素
    			cur.add(left+right);
    		}
    		
    		// 加入列表
    		ret.add(cur);
    	}
    	
    	return ret;
    }
	
	public static void main(String[] args) {
		System.out.println(new PascalsTriangle().generate(5));
	}

}
