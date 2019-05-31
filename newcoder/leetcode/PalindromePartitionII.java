package com.leetcode.dyncplanning;

/**
 * 题目：
 * palindrome-partitioning  
 * 
 * 题目描述：
 * Given a string s, partition s such that 
 * every substring of the partition is a palindrome.
 * 
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * 
 * For example, given s ="aab",
 * Return 1 since the palindrome partitioning ["aa","b"] 
 * could be produced using 1 cut.
 */
public class PalindromePartitionII {

	/**
	 * 解题思路：动态规划问题(一维数组表示，复杂度大)
	 * dp[i] - 表示子串（0，i）的最小回文切割，则最优解在dp[s.length-1]中。
	 * 
	 * 分几种情况：
	 * 1.初始化：当字串s.substring(0,i+1)(包括i位置的字符)是回文时，dp[i] = 0(表示不需要分割)；否则，dp[i] = i（表示至多分割i次）;
	 * 2.对于任意大于1的i，如果s.substring(j,i+1)(j<=i,即遍历i之前的每个子串)是回文时，dp[i] = min(dp[i], dp[j-1]+1);
	 * 3.如果s.substring(j,i+1)(j<=i)不是回文时，dp[i] = min(dp[i],dp[j-1]+i+1-j);
	 */
    public int minCut(String s) {
    	if (s == null || s.isEmpty()) {
    		return 0;
    	}
    	int len = s.length();
    	int[] dp = new int[len];
    	for (int i=0; i<len; i++) {
    		String str = s.substring(0, i+1);
    		// 是回文则需要cut 0次，否则初始化为字符串长度 
    		dp[i] = isPalindrome(str) ? 0 : i;
    		if (dp[i] == 0) {
    			continue;
    		}
    		
    		// 不是回文
    		for (int j=1; j<=i; j++) {
    			// j到i+1的子串
    			String subStr = s.substring(j, i+1);
    			// 是回文 则dp[j-1]+1即可，不是则dp[j-1]+后面的元素个数
    			if (isPalindrome(subStr)) {
    				dp[i] = Math.min(dp[i], dp[j-1] + 1);
    			} else {
    				dp[i] = Math.min(dp[i], dp[j-1] + i - j + 1);
    			}
    		}
    	}
    	return dp[len-1];
    }

    private boolean isPalindrome(String str) {
    	return new StringBuilder(str).reverse().toString().equals(str);
    }
    
    
    // 更加快速的大牛的解法, 摘自牛客网
    /**
    
    * 动态规划的题，最主要就是写出状态转移方程
    * 状态转移，其实就是怎么把一个大的状态表示为两个或者多个已知的状态
    * 以此题为例，设f[i][j]为最小的切点数，那么有：
    * 1、s[i][j]为回文字符串，则f[i][j] = 0;
    * 2、增加一个切点p，将s[i][j]切割为两端s[i][p]、s[p+1][j],则f[i][j] = f[i][p]+f[p+1][j]
    * 所谓的状态转移方程就是上面的式子
    *
    * 接着来看看怎么组织程序，先看看状态转移的思路：
    * 以"aab"为例，"aab"明显不是回文串
    * 所以 f("aab") = min( (f("a")+f("ab")) , (f("aa")+f("b")) ) + 1;
    * f("a") = 0;
    * f("ab") = f("a")+f("b") +1  = 0+0+1 = 1;
    * f("aa") = 0;
    * f("b") = 0;
    * 即f("aab") = 1;
    *
    * 聪慧的你一定能看出来，这是一个递归调用的过程，计算f("ab")需要先计算f("a")、f("b")
    * 用递归实现动态规划，在思路上是最简单的，大部分的题目都可以用这种方式解决
    *
    * 但是有一些数据变态的题目，加上测试机子给的堆栈太小，这种递归的算法很容易就爆栈了
    * 我们需要用我们的聪明智慧，把递归的程序写为非递归的。
    * 把解题思路从下往上看，假设我们先求出来了f("a")，f("b")
    * 那么我们可以求出f("aa"),f("ab")
    * 接着我们就可以得出答案f("aab")了
    * 在这个过程中，我们需要牺牲空间（f[1000][1000]）代替堆栈记录递归调用的返回值
    * 而且这种方式有个好处，就是可以减少计算量
    * 比如计算f("aab")时需要计算f("aa")+f("b")
    * 计算f("ab")事需要计算f("a")+f("b")
    * 这里就计算了两次f("b");
    * 在第一次计算f("b")之后,将f("b")记录下来，可以减少一次计算量
    * 动态规划本质上是暴力搜索，只不过咋这个暴力搜索的过程中，减少了不必要的计算，这样就提升了算法解决问题的速度
    * 在一些题目中，你还可以根据题目减少某些分支的计算
    * 比如只要判断这个字符串是回文串，就可以直接返回0，不需要一步步计算其中的子序列
    *
    */
     
    public static class Solution {
	    private String s;
	    private int dp[][];
	     
	    public int minCut(String s) {
	    	if (s == null || s.isEmpty()) {
	    		return 0;
	    	}
		    this.s = s;
		    int len = s.length();
		    this.dp = new int[len][len];
		    //先求解小段的子序列
		    for(int t=0;t<=len;t++){
			    for(int i=0,j=t;j<len;i++,j++){
			    	dp[i][j] = compCut(i,j);
			    }
		    }
		    return dp[0][s.length()-1];
	    }
	     
	    // 状态转移方程的实现
	    private int compCut(int i,int j) {
		    if(isPalindrome(i,j)) {
		    	return 0;
		    }
		    int min = s.length();
		    for(int p=i;p<j;p++) {
			    int a = dp[i][p];
			    int b = dp[p+1][j];
			    a = a + b + 1;
			    min = min<a?min:a;
		    }
		    return min;
	    }
	     
	    //判断是否回文串
	    private boolean isPalindrome(int i,int j){
	    	while(i<j) {
	    		if(s.charAt(i) != s.charAt(j))
	    			return false;
	    			i++;
	    			j--;
	    	}
	    	return true;
	    }
    }
    
	public static void main(String[] args) {
		String s = "aab";
		System.out.println(new PalindromePartitionII().minCut(s));
		System.out.println(new PalindromePartitionII.Solution().minCut(s));
	}

}
