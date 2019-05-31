package com.codinginterviews.array;

/**
 * 题目：
 * 扑克牌顺子 -- newcoder 剑指Offer 45
 *  
 * 题目描述：
 * 
LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,2个小王
(一副牌原本是54张^_^)...他随机从中抽出了5张牌,想测测自己的手气,
看看能不能抽到顺子,如果抽到的话,他决定去买体育彩票,嘿嘿！！
“红心A,黑桃3,小王,大王,方片5”,“Oh My God!”不是顺子.....LL不高兴了,
他想了想,决定大\小 王可以看成任何数字,并且A看作1,J为11,Q为12,K为13。
上面的5张牌就可以变成“1,2,3,4,5”(大小王分别看作2和4),“So Lucky!”。
LL决定去买体育彩票啦。 现在,要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何，
 如果牌能组成顺子就输出true，否则就输出false。
为了方便起见,你可以认为大小王是0。
 */
public class IsContinuous {

	/**
	 * 题目解释：
	 * numbers为LL抽到牌的数字序列，其中数字0代表抽到了大\小王
	 * 
	 * 思路：
	 * 牌能组成顺子的条件是：
	 * 1、numbers中的最大值与最小值之差小于5
	 * 2、numbers中的非0数字不重复
	 * 3、忽略numbers中的数字0
	 */
    public boolean isContinuous(int[] numbers) {
    	if (numbers == null || numbers.length <= 0) {
    		return false;
    	}
    	
    	// 初始化最小值与最大值
    	int max = 0;
    	int min = 13;
    	
    	// 记录重复数字的缓存，总共14个数字，所以开辟14个元素的数组
    	int[] dp = new int[14];
    	for (int i=0, len=numbers.length; i<len; i++) {
    		int val = numbers[i];
    		// 忽略0
    		if (val == 0) {
    			continue;
    		}
    		// 有重复数字，直接返回false
    		dp[val] += 1;
    		if (dp[val] > 1) {
    			return false;
    		}
    		// 更新最大最小值
    		if (val > max) {
    			max = val;
    		}
    		if (val < min) {
    			min = val;
    		}
    	}
    	
    	return max - min < 5;
    }
	
	public static void main(String[] args) {
		int[] nums = {1,3,0,0,5};
		System.out.println(new IsContinuous().isContinuous(nums));
	}

}
