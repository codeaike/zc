package com.leetcode.dyncplanning;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 题目：
 * triangle -- newcoder 31
 * 三角形最小路径和  --leetcode 120
 * 
 * 相关话题：
 * 数组   动态规划
 * 
 * 题目描述：
 * 
Given a triangle, find the minimum path sum from top to bottom. 
Each step you may move to adjacent(相邻的) numbers on the row below.

For example, given the following triangle

[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]

The minimum path sum from top to bottom is11(i.e., 2 + 3 + 5 + 1 = 11).

Note: 
Bonus point if you are able to do this using only O(n) extra space, 
where n is the total number of rows in the triangle.
 * 
 */
public class Triangle {

	/**
	 * 思路：(动态规划)
	 * 自下向上dp
	 * 
	 * 给定一个三角形，找出从顶到底的最小路径和，每一步可以从上一行移动到下一行相邻的数字，
	 * 更新本行数字为：下一行最小的相邻数字+本行数字，如下实例6更新为6+1=7
    [                   
         [2],                 [2],              
        [3,4],              [3, 4],            [2],
       [6,5,7],      ==>   [7, 6, 10]     ==>  [9, 10]   ==>     [11]
      [4,1,8,3]
    ]
 
     * 自下向上 dp: 不需要额外的空间
     * dp[i][j] = min(dp[i+1][j], dp[i+1][j+1]) + triangle[i][j]
     * dp[i][j]: 表示到达 (i, j)最小路径的总和
	 *  
	 */
    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
    	if (triangle == null || triangle.size() <= 0) {
    		return 0;
    	}
    	int size = triangle.size();
    	if (size == 1) {
    		return triangle.get(0).get(0);
    	}
        
    	// 自低而上更新三角形，从倒数第二行开始更新
    	for (int i=size-2; i>=0; i--) {
    		int wid = triangle.get(i).size();
    		// 更新本行元素 dp[i,j] = dp[i,j] + min(dp[i+1,j],dp[i+1,j+1])
    		for (int j=0; j<wid; j++) {
    			int cur = triangle.get(i).get(j);
    			int next = cur + Math.min(triangle.get(i+1).get(j),triangle.get(i+1).get(j+1));
    			// 更新元素
    			triangle.get(i).set(j, next);
    		}
    	}
    	
    	// 返回顶部元素
    	return triangle.get(0).get(0);
    }
	
	public static void main(String[] args) {
		ArrayList<Integer> arr1 = new ArrayList<>(Arrays.asList(new Integer[]{2}));
		ArrayList<Integer> arr2 = new ArrayList<>(Arrays.asList(new Integer[]{3,4}));
		ArrayList<Integer> arr3 = new ArrayList<>(Arrays.asList(new Integer[]{6,5,7}));
		ArrayList<Integer> arr4 = new ArrayList<>(Arrays.asList(new Integer[]{4,1,8,3}));
		ArrayList<ArrayList<Integer>> triangle = new ArrayList<>();
		triangle.add(arr1);
		triangle.add(arr2);
		triangle.add(arr3);
		triangle.add(arr4);
		System.out.println(new Triangle().minimumTotal(triangle));
	}

}
