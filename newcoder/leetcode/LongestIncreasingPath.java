package com.my.test.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目：
 * 矩阵中的最长递增路径 -- leetcode 329
 * 
 * 题目描述：
给定一个整数矩阵，找出最长递增路径的长度。

对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。

示例 1:

输入: nums = 
[
  [9,9,4],
  [6,6,8],
  [2,1,1]
] 
输出: 4 
解释: 最长递增路径为 [1, 2, 6, 9]。
示例 2:

输入: nums = 
[
  [3,4,5],
  [3,2,6],
  [2,2,1]
] 
输出: 4 
解释: 最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestIncreasingPath
{
    /**
     * 思路：
     * DFS+记忆数组，但是性能较低，可修改为DFS+dp
     */
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) {
            return 0;
        }
        
        int max = 0;
        
        int row = matrix.length;
        int col = matrix[0].length;
        
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                max = Math.max(max, getLongestIncreasingPath(matrix, row, col, i, j));
            }
        }
        
        return max;
    }
    
    // 获取从坐标(i,j)出发的最长路径
    private int getLongestIncreasingPath(int[][] matrix, int row, int col, int i, int j) {

        boolean[][] visited = new boolean[row][col];
        int[] maxLen = new int[1];
        
        getLongestIncreasingPath(matrix, visited, row, col, i, j, new ArrayList<>(), maxLen);
        
        return maxLen[0];
    }
    
    private void getLongestIncreasingPath(int[][] matrix, boolean[][] visited, int row, int col, 
                                         int i, int j, List<Integer> cache, int[] maxLen) {
        if (illegal(visited, row, col, i, j)) {
            return;
        }
        
        int cur = matrix[i][j];
        if (cache.isEmpty() || cache.get(cache.size()-1) < cur) {
            // 找到下一个满足条件的递增元素
            cache.add(cur);
            visited[i][j] = true;
            maxLen[0] = Math.max(maxLen[0], cache.size());
            
            // 寻找下一个
            getLongestIncreasingPath(matrix, visited, row, col, i-1, j, cache, maxLen);
            getLongestIncreasingPath(matrix, visited, row, col, i+1, j, cache, maxLen);
            getLongestIncreasingPath(matrix, visited, row, col, i, j-1, cache, maxLen);
            getLongestIncreasingPath(matrix, visited, row, col, i, j+1, cache, maxLen);
            
            // 回溯
            cache.remove(cache.size()-1);
            visited[i][j] = false;
        } 
    }
    
    private boolean illegal(boolean[][] visited, int row, int col, 
                            int i, int j) {
        return i<0 || i>=row || j<0 || j>=col || visited[i][j]== true;
    }
    

    /**
     * 思路：
     * DFS+dp
     * 1、dp[i][j]表示数组中以(i,j)为起点的最长递增路径的长度，初始将dp数组都赋为0，
     * 2、递归调用时，遇到某个位置(x, y), 如果dp[x][y]不为0的话，我们直接返回dp[x][y]即可，不需要重复计算。
     * 3、以数组中每个位置都为起点调用递归来做，比较找出最大值。在以一个位置为起点用DFS搜索时，对其四个相邻位置进行判断，
     *   如果相邻位置的值大于上一个位置，则对相邻位置继续调用递归，并更新一个最大值，搜素完成后返回即可
     */
    public int longestIncreasingPathII(int[][] matrix) {
        if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) {
            return 0;
        }
        
        int max = 0;
        
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row][col];
        
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                max = Math.max(max, dfs(matrix, dp, row, col, i, j));
            }
        }
        
        return max;
    }
    
    private int[][] paths = {{0,1},{0,-1},{1,0},{-1,0}};
    
    private int dfs(int[][] matrix, int[][] dp, int row, int col, int i, int j) {
        if (dp[i][j] > 0) {
            return dp[i][j];
        }
        int max = 1;
        for (int[] path : paths) {
            int x = i + path[0];
            int y = j + path[1];
            // 可以继续搜索
            if (x >= 0 && x <row && y >= 0 && y < col && matrix[x][y] > matrix[i][j]) {
                int len = 1 + dfs(matrix, dp, row, col, x, y);
                max = Math.max(max, len);
            }
        }
        dp[i][j] = max;
        return max;
    }
    
    public static void main(String[] args)
    {
        int[][] matrix = {{3,4,5},
                          {3,2,6},
                          {2,2,1}};
        System.out.println(new LongestIncreasingPath().longestIncreasingPathII(matrix));
    }

}
