package com.leetcode.array;

/**
 * 题目：
 * best-time-to-buy-and-sell-stock-ii -- newcoder 29  
 * 买卖股票的最佳时机II -- leetcode 122
 * 
 * 题目描述：
 * Say you have an array for which the i th element is 
 * the price of a given stock on day i.
 * 
 * Design an algorithm to find the maximum profit. 
 * You may complete as many transactions as you like 
 * (ie, buy one and sell one share of the stock multiple times). 
 * However, you may not engage in multiple transactions at the same time
 * (ie, you must sell the stock before you buy again).
 * 
 * 中文描述：
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

示例 1:

输入: [7,1,5,3,6,4]
输出: 7
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
示例 2:

输入: [1,2,3,4,5]
输出: 4
解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
示例 3:

输入: [7,6,4,3,1]
输出: 0
解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *  
 */
public class BestTimeToBuyAndSellStockII {

	/**
	 * 思路：(贪心算法)
	 * 1、初始化收益为0
	 * 2、从左到右遍历，发现当前元素比刚遍历的元素大，
	 *    即可取得收益，累加结果即为最大收益
	 * 
	 * @param prices 股票价格数组
	 */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
        	return 0;
        }
        int maxProfit = 0;
        
        for (int i=1, len = prices.length; i<len; i++) {
        	// 如果可取得收益，累加收益
        	if (prices[i] > prices[i-1]) {
        		maxProfit += prices[i] - prices[i-1]; 
        	}
        }
        	
        return maxProfit;
    }
	
    
	public static void main(String[] args) {
		int[] arr1 = {7, 1, 5, 3, 6, 4};
		System.out.println(new BestTimeToBuyAndSellStockII().maxProfit(arr1));
		int[] arr2 = {1, 2, 3, 4, 5};
		System.out.println(new BestTimeToBuyAndSellStockII().maxProfit(arr2));
		int[] arr3 = {7, 6, 4, 3, 1};
		System.out.println(new BestTimeToBuyAndSellStockII().maxProfit(arr3));

	}

}
