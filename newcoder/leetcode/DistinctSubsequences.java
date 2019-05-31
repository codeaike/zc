package com.leetcode.str;


/**
 * 题目：
 * distinct-subsequences -- newcoder 36 
 * 不同的子序列 -- leetcode 115
 * 
 * 
 * 题目描述：
 * 
Given a string S and a string T, count the number of distinct subsequences of T in S.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie,"ACE"is a subsequence of"ABCDE"while"AEC"is not).

Here is an example:
S ="rabbbit", T ="rabbit"

Return 3.

说明：
如下图所示, 有 3 种可以从 S 中得到 "rabbit" 的方案。
(上箭头符号 ^ 表示选取的字母)

rabbbit
^^^^ ^^
rabbbit
^^ ^^^^
rabbbit
^^^ ^^^
 */
public class DistinctSubsequences {

    /**
     * 思路(摘自newcoder)：
     * 
                我们需要一个二维数组dp(i)(j)来记录长度为i的字串在长度为j的母串中出现的次数，
                这里长度都是从头算起的，而且遍历时，保持子串长度相同，先递增母串长度，母串最长时再增加一点子串长度重头开始计算母串。
                
                首先我们先要
                1、初始化矩阵，当子串长度为0时，所有次数都是1，当母串长度为0时，所有次数都是0.当母串子串都是0长度时，次数是1（因为都是空，相等）。
                接着，
                2、如果子串的最后一个字母和母串的最后一个字母不同，说明新加的母串字母没有产生新的可能性，可以沿用该子串在较短母串的出现次数，
                所以dp(i)(j) = dp(i)(j-1)。
                3、如果子串的最后一个字母和母串的最后一个字母相同，说明新加的母串字母带来了新的可能性，
                我们不仅算上dp(i)(j-1)，也要算上新的可能性。那么如何计算新的可能性呢，
                其实就是在既没有最后这个母串字母也没有最后这个子串字母时，子串出现的次数，我们相当于为所有这些可能性都添加一个新的可能。
                所以，这时dp(i)(j) = dp(i)(j-1) + dp(i-1)(j-1)。
                
                计算元素值时，当末尾字母一样，实际上是左方数字加左上方数字，当不一样时，就是左方的数字。
                以rabbbit和rabbit为例的矩阵示意图可参考：
         https://uploadfiles.nowcoder.com/images/20180403/659633_1522741596706_4A47A0DB6E60853DEDFCFDF08A5CA249
     * 
     */
    public int numDistinct(String S, String T) {
        if (S == null || T == null) {
            return 0;
        }
        int sLen = S.length();
        int tLen = T.length();
        if (sLen < tLen) {
            return 0;
        }
        
        // 初始化dp
        int[][] dp = new int[tLen+1][sLen+1];
        // 初始化第一行
        for (int j=0; j<sLen; j++) {
            dp[0][j] = 1;
        }
        
        char[] sArr = S.toCharArray();
        char[] tArr = T.toCharArray();
        for (int i=1; i<tLen+1; i++) {
            for (int j=1; j<sLen+1;j++) {
                if (tArr[i-1] == sArr[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + dp[i][j-1];
                } else {
                    dp[i][j] = dp[i][j-1];
                }
            }
        }
        
        return dp[tLen][sLen];
    }
    
    /*
     * 思路：
     dp(i , j ) 表示T[0,j] 在S[0，i] 中的匹配个数
                如果不使用S[i] , 那么f(i , j) = f(i-1 , j)
                如果使用了S[i] , 那么一定有 S[i] == T[j] , f( i , j ) = f(i -1 , j -1 )
                所以当S[i]==T[j]时，有dp( i , j ) = dp( i -1 , j ) +　dp(i - 1 , j - 1);
                当S[i]!=T[j]时，有 dp( i , j ) = dp( i -1 , j );
                在使用中不难发现该dp二维数组可以降维，
                注意改变数组元素值时由后往前
   * 
   */
    
    public int numDistinctII(String S, String T) {
        if (S == null || T == null) {
            return 0;
        }
        int sLen = S.length();
        int tLen = T.length();
        if (sLen < tLen) {
            return 0;
        }
        
        char[] sArr = S.toCharArray();
        char[] tArr = T.toCharArray();
        
        // 动态规划状态值缓存
        int[] dp = new int[tLen + 1];
        dp[0] = 1;
        
        for (int i=1; i<sLen+1; i++) {
            for (int j=Math.min(i, tLen); j>0; j--) {
                if (sArr[i-1] == tArr[j-1]) {
                    dp[j] = dp[j-1] + dp[j];
                }
            }
        }
        
        return dp[tLen];
    }
    
    public int numDistinctIII(String S, String T) {
        if (S == null || T == null) {
            return 0;
        }
        int sLen = S.length();
        int tLen = T.length();
        if (sLen < tLen) {
            return 0;
        }
        
        char[] sArr = S.toCharArray();
        char[] tArr = T.toCharArray();
        
        // 动态规划状态值缓存
        int[] dp = new int[tLen];
        
        for (int i=0; i<sLen; i++) {
            for (int j= tLen - 1; j>=0; j--) {
                if (sArr[i] == tArr[j]) {
                    if (j != 0) {
                        dp[j] = dp[j-1] + dp[j];
                    } else {
                        dp[0]++;
                    }
                }
            }
        }
        
        return dp[tLen-1];
    }
    
    public static void main(String[] args) {
        String S = "rabbbit", T = "rabbit";
        System.out.println(new DistinctSubsequences().numDistinct(S, T));
        System.out.println(new DistinctSubsequences().numDistinctII(S, T));
        System.out.println(new DistinctSubsequences().numDistinctIII(S, T));
    }

}

