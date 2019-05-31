package com.leetcode.array;

/**
 * 题目：
 * 被围绕的区域
 * surrounded-regions
 * 
 * 题目描述：
 * Given a 2D board containing'X'and'O', capture all regions surrounded by'X'.
 * A region is captured by flipping all'O's into'X's in that surrounded region .
 * For example,
    X X X X
    X O O X
    X X O X
    X O X X
 * 
 * After running your function, the board should be:
    X X X X
    X X X X
    X X X X
    X O X X
 * 
 * 解释:
 * 1、被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 
 * 2、任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。
 * 3、如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 */
public class SurroundedRegions
{
    /**
     * 思路：
     * 1、所有与四条边相连的O都保留，其他O都变为X
     * 2、遍历四条边上的O，并深度遍历与其相连的O，将这些O都转为*
     * 3、将剩余的O变为X
     * 4、将剩余的*变为O
     */
    public void solve(char[][] board) {
        if (board == null || board.length <= 1 || board[0].length <= 1) {
        	return;
        }
        
        // 行数 列数
        int rowNum = board.length;
        int colNum = board[0].length;
        
        // 1、四条边上开始深度遍历，边上O及与边上O相连的全变为*
        for (int i=0; i<rowNum; i++) {
        	// 第一列元素开始深度遍历
        	dfs(board, i, 0);
        	// 最后一列元素开始深度遍历
        	dfs(board, i, colNum - 1);
        }
        
        for (int j=0; j<colNum; j++) {
        	// 第一行元素开始深度遍历
        	dfs(board, 0, j);
        	// 最后一行元素开始深度遍历
        	dfs(board, rowNum - 1, j);
        }
        
        // 2、其他为O的元素替换为X
        replaceAToB(board, rowNum, colNum, 'O', 'X');
        
        // 3、为*的元素替换为O
        replaceAToB(board, rowNum, colNum, '*', 'O');
    }
    
    // 替换元素
    private void replaceAToB(char[][] board, int rowNum, int colNum, char a, char b) {
        for (int i=0; i<rowNum; i++) {
        	for(int j=0; j<colNum; j++) {
        		if (board[i][j] == a) {
        			board[i][j] = b;
        		}
        	}
        }
    }
    
    // 深度遍历
    private void dfs(char[][] board, int row, int col) {
    	// 非法判断
    	if (row<0 || row>= board.length || col<0 || col>=board[0].length) {
    		return;
    	}
    	if (board[row][col] == 'O') {
    		// 替换为*
    		board[row][col] = '*';
    		// 深度遍历上下左右
    		dfs(board, row-1, col);
    		dfs(board, row+1, col);
    		dfs(board, row, col-1);
    		dfs(board, row, col+1);
    	}
    }
    
    public static void main(String[] args)
    {
        char[][] board = {{'X', 'X', 'X', 'X'},{'X', 'O', 'O', 'X'},{'X', 'X', 'O', 'X'},{'X', 'O', 'X', 'X'}};
        new SurroundedRegions().solve(board);
        for (int i=0; i<board.length; i++) {
            System.out.println(board[i]);
        }
        
        char[][] board1 = {{'X', 'O', 'X', 'X'},{'O', 'X', 'O', 'X'},{'X', 'O', 'X', 'O'},{'O', 'X', 'O', 'X'}};
        new SurroundedRegions().solve(board1);
        for (int i=0; i<board1.length; i++) {
            System.out.println(board1[i]);
        }
    }

}
