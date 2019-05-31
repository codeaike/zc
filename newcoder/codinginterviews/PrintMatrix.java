package com.codinginterviews.array;

import java.util.ArrayList;

/**
 * 题目：
 * 顺时针打印矩阵 -- newcoder 剑指Offer 19
 * 
 * 题目描述：
 * 
输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
例如，如果
输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 
则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 */
public class PrintMatrix
{
    /**
     * 思路：
     * 1、顺时针打印就是按圈数循环打印，一圈包含两行或者两列，
     * 2、在打印的时候会出现某一圈中只包含一行，要判断从左向右打印和从右向左打印的时候是否会出现重复打印，
     * 3、同样只包含一列时，要判断从上向下打印和从下向上打印的时候是否会出现重复打印的情况 
     */
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> print = new ArrayList<>();
        if (matrix == null) {
            return print;
        }
        
        int rowNum = matrix.length;
        if (rowNum <= 0) {
            return print;
        } 
        
        int colNum = matrix[0].length;
        if (colNum <= 0) {
            return print;
        }
        
        // 计算打印的圈数
        int circle = (Math.min(rowNum, colNum) - 1)/2 + 1;
        
        for (int i=0; i<circle; i++) {
            //从左向右打印
            for(int j=i; j<colNum-i; j++) {
                print.add(matrix[i][j]);   
            }
            //从上往下的每一列数据
            for(int k=i+1; k<rowNum-i; k++) {
                print.add(matrix[k][colNum-1-i]);
            }
            //判断是否会重复打印(从右向左的每行数据)
            for(int m=colNum-i-2; (m>=i)&&(rowNum-i-1!=i); m--) {
                print.add(matrix[rowNum-i-1][m]);
            }
            //判断是否会重复打印(从下往上的每一列数据)
            for(int n=rowNum-i-2; (n>i)&&(colNum-i-1!=i); n--) {
                print.add(matrix[n][i]);
            }
        }
        
        return print;
    }
    
    /**
     * 思路：
     * 1、用左上和右下的坐标定位出一次要旋转打印的数据，一次旋转打印结束后，往对角分别前进和后退一个单位。
     * 2、需要加入条件判断，防止出现单行或者单列的情况。 
     */
    public ArrayList<Integer> printMatrixII(int [][] matrix) {
        ArrayList<Integer> print = new ArrayList<>();
        if (matrix == null) {
            return print;
        }
        
        int rowNum = matrix.length;
        if (rowNum <= 0) {
            return print;
        } 
        
        int colNum = matrix[0].length;
        if (colNum <= 0) {
            return print;
        }
        
        // 初始化边界
        int left = 0;
        int right = colNum - 1;
        int top = 0;
        int bottom = rowNum - 1;
        
        while (left<=right && top<=bottom) {
            // 遍历上面一行
            for (int i=left; i<=right; ++i) {
                print.add(matrix[top][i]);
            }
            // 遍历右侧一列
            for (int j=top+1; j<=bottom; ++j) {
                print.add(matrix[j][right]);
            }
            
            // 单行或者单列元素时，上面的一次遍历即可
            // 最后一次时，只遍历一遍即可
            if (top != bottom) {
                // 倒序遍历下面一行
                for (int i=right-1; i>=left; --i) {
                    print.add(matrix[bottom][i]);
                }
            }
            // 最后一次时，只遍历一遍即可
            if (left != right) {
                // 遍历左侧一列
                for (int j=bottom-1; j>top; --j) {
                    print.add(matrix[j][left]);
                }
            }
            // 一圈遍历结束
            left++;
            top++;
            right--;
            bottom--;
        }
        
        return print;
    }
    
    public static void main(String[] args)
    {
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        System.out.println(new PrintMatrix().printMatrix(matrix));
        System.out.println(new PrintMatrix().printMatrixII(matrix));
    }

}

