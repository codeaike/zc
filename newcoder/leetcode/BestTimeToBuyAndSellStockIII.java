package com.leetcode.array;

/**
 * 题目：
 * best-time-to-buy-and-sell-stock-iii -- newcoder 28  
 * 买卖股票的最佳时机III -- leetcode 123
 * 
 * 题目描述：
 * Say you have an array for which the i th element 
 * is the price of a given stock on day i.
 * 
 * Design an algorithm to find the maximum profit. 
 * You may complete at most two transactions.
 * 
 * Note: 
 * You may not engage in multiple transactions at the same time 
 * (ie, you must sell the stock before you buy again).
 * 
 * 中文描述：
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

示例 1:

输入: [3,3,5,0,0,3,1,4]
输出: 6
解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
示例 2:

输入: [1,2,3,4,5]
输出: 4
解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。   
     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。   
     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
示例 3:

输入: [7,6,4,3,1] 
输出: 0 
解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。
 *  
 */
public class BestTimeToBuyAndSellStockIII {

	/**
	 * 思路：(贪心算法)
     * 1、用sell1表示初始时的利润为0，buy1表示最便宜股票的价格，
     * 	     用sell2表示交易两次的利润，buy2表示第一次售出股票后，再买入后面某一天股票后的收益
     * 2、从左到右遍历，buy1表示前些天买入最便宜股票的股价
     *    sell1保存前些天买入最便宜股票后再在股票最高时卖出股票的最大利润
     * 3、buy2表示第一次售出股票后，再买入后面某一天股票后的净收益
     *    sell2表示二次买卖或者一次买卖的最大收益(buy2之前的净收益+curPrice今天卖出股票后收益)
	 * 
	 * @param prices 股票价格数组
	 */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
        	return 0;
        }
        
        int buy1 = Integer.MIN_VALUE;
        int sell1 = 0;
        int buy2 = Integer.MIN_VALUE;
        int sell2 = 0;
        
        for (int i=0, len = prices.length; i<len; i++) {
        	int curPrice = prices[i];
        	// 最便宜的股票价格
        	buy1 = Math.max(buy1, - curPrice);
        	// 一次交易的最大收益
        	sell1 = Math.max(sell1, curPrice + buy1);
        	// 之前天先进行第一次交易后，在买入今天股票后的净利润
        	buy2 = Math.max(buy2, sell1 - curPrice);
        	// 二次交易的收益(买入今天股票后的收益)
        	sell2 = Math.max(sell2, buy2 + curPrice);
        }
        	
        return sell2;
    }
	
    
	public static void main(String[] args) {
		int[] arr1 = {3, 3, 5, 0, 0, 3, 1, 4};
		System.out.println(new BestTimeToBuyAndSellStockIII().maxProfit(arr1));
		int[] arr2 = {1, 2, 3, 4, 5};
		System.out.println(new BestTimeToBuyAndSellStockIII().maxProfit(arr2));
		int[] arr3 = {7, 6, 4, 3, 1};
		System.out.println(new BestTimeToBuyAndSellStockIII().maxProfit(arr3));

	}

}
