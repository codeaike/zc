package com.codinginterviews.recall;

/**
 * 题目：
 * 机器人的运动范围 -- newcoder 剑指Offer 66
 * 
 * 题目描述：
 * 
地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，
每一次只能向左，右，上，下四个方向移动一格，
但是不能进入行坐标和列坐标的数位之和大于k的格子。

 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。
 但是，它不能进入方格（35,38），因为3+5+3+8 = 19。
 请问该机器人能够达到多少个格子？
 */
public class MovingCount {

	/**
	 * 思路(回溯)： 
	 * 1、从(0,0)开始走，每成功走一步标记当前位置为true,然后从当前位置往四个方向探索，
		     返回1 + 4 个方向的探索值之和。
	 * 2、探索时，判断当前节点是否可达的标准为：
	 * 1）当前节点在矩阵内；
	 * 2）当前节点未被访问过；
	 * 3）当前节点满足limit限制。
	 */
    public int movingCount(int threshold, int rows, int cols) {
        int visited[][] = new int[rows][cols]; //记录是否已经走过
        return helper(0, 0, rows, cols, visited, threshold);
    }
 
    private int helper(int i, int j, int rows, int cols, int[][] visited, int threshold) {
        if (i < 0 || i >= rows || j < 0 || j >= cols || numSum(i) + numSum(j)  > threshold || visited[i][j] == 1) {
        	return 0;    
        }
        visited[i][j] = 1;
        return helper(i - 1, j, rows, cols, visited, threshold)
            + helper(i + 1, j, rows, cols, visited, threshold)
            + helper(i, j - 1, rows, cols, visited, threshold)
            + helper(i, j + 1, rows, cols, visited, threshold)
            + 1;
    }
 
    private int numSum(int number) {
        int sum = 0;
        while (number>0) {
            sum += number%10;
            number /= 10;
        }
        return sum;
    }
	
}
