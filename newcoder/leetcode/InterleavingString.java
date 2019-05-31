package com.my.test.leetcode.dyncplanning;

/**
 * 题目：
 * interleaving-string -- newcoder 52
 * 交错字符串 -- leetcode 97
 * 
 * 题目描述：
 * 
Given s1, s2, s3, find whether s3 is formed by 
the interleaving of s1 and s2.
给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的

For example,
Given:
s1 ="aabcc",
s2 ="dbbca",

When s3 ="aadbbcbcac", return true.
When s3 ="aadbbbaccc", return false.
 */
public class InterleavingString
{
    /**
     * 思路：
     * 1、动态规划求解，模拟出矩阵
     * 如下：
          Ø d b b c a
        Ø T F F F F F
        a T F F F F F
        a T T T T T F
        b F T T F T F
        c F F T T T T
        c F F F T F T
        
     * 2、dp[i][j]表示s3的前i+j个字符可以由s1的前i个字符和s2的前j个字符交织而成
     * 3、状态转移方程：有两种情况
        (1) 第一个状态转移方程：
        dp[i][j]= {dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)}
        dp[i-1][j]表示若s3的前i+j-1个字符能够由s1前i-1个字符和s2的前j个字符交织而成，那么只需要s1的第i个字符与s3的第i+j个字符相等（charAt索引从0开始），那么dp[i][j]=true;
        
        (2) 第二个状态转移方程：
        dp[i][j]= {dp[i][j-1] && s2.charAt(j - 1) == s3.charAt(i + j - 1)}
        dp[i][j-1]表示若s3的前i+j-1个字符能够由s1前i个字符和s2的前j-1个字符交织而成，那么只需要s2的第j个字符与s3的第i+j个字符相等（charAt索引从0开始），那么dp[i][j]=true;
     *  
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();
        
        if (len1 + len2 != len3) {
            return false;
        }
        
        // 构建动态规划矩阵
        // dp[i][j]表示s3的前i+j个字符可以由s1的前i个字符和s2的前j个字符交织而成
        boolean[][] dp = new boolean[len1+1][len2+1];
        dp[0][0] = true;
        
        // 更新第一行元素
        for (int j=1; j<=len2; j++) {
            dp[0][j] = dp[0][j-1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }
        
        // 更新第一列元素
        for (int i=1; i<=len1; i++) {
            dp[i][0] = dp[i-1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }
        
        // 更新其他元素
        for (int i=1; i<=len1; i++) {
            for (int j=1; j<=len2; j++) {
                dp[i][j]= (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || 
                                (dp[i][j-1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }
        
        return dp[len1][len2];
    }

    public static void main(String[] args)
    {
        String s1 ="aabcc";
        String s2 ="dbbca";
        
        System.out.println(new InterleavingString().isInterleave(s1, s2, "aadbbcbcac"));
        System.out.println(new InterleavingString().isInterleave(s1, s2, "aadbbbaccc"));
    }

}
