package com.my.test.codinginterviews.other;

/**
 * 题目：
 * 丑数 -- newcoder 剑指Offer 33
 *  
 * 题目描述：
 * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。
 * 例如6、8都是丑数，但14不是，因为它包含质因子7。 
 * 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。 
 */
public class GetUglyNumber
{
    
    /**
     * 思路(性能较低)：
     * 1、从1开始遍历正整数，判断是否为丑数，获取第index个丑数
     */
    public int GetUglyNumber_Solution(int index) {
        if (index < 1) {
            // 参数非法
            return 0;
        }
        
        int res = 0;
        
        for (int idx=1; idx <= index;) {
            if (isUglyNumber(++res)) {
                idx++;
            }
        }
        
        return res;
    }
    
    private boolean isUglyNumber(int num) {
        if (num < 1) {
            return false;
        }
        if (num == 1) {
            return true;
        }
        while (num % 2 == 0) {
            num = num / 2;
        }
        while (num % 3 == 0) {
            num = num / 3;
        }
        while (num % 5 == 0) {
            num = num / 5;
        }
        return num == 1;
    }
    
    /**
     * 思路：
     * 1、动态规划求解，维护一个之前求解的丑数的数组，下一个丑数由之前的某个丑数推导出来
     * 2、每个下一个丑数，必然是历史丑数乘以2、3、或5得到的
     * 3、暂取下一个丑数为当前丑数乘以2、3、或5得到的三个数中的最小值
     * 4、为保证丑数递增，则需要分别维护2、3、5对应的一组历史丑数，小于当前丑数，对应的历史丑数的索引
     * 5、下一个丑数即为三个数字维护的历史丑数 乘以 自身获取的三个数字中的最小数
     */
    public int GetUglyNumber_SolutionII(int index) {

        if (index <= 0) {
            return -1;
        }
        
        int p2 = 0;
        int p3 = 0;
        int p5 = 0;
        
        // 暂存丑数
        int[] dp = new int[index];
        // 初始化第一个丑数
        dp[0] = 1;
        
        // 下一个丑数的位置
        int nextUglyIndex = 1;
        
        while (nextUglyIndex < index) {
            int nextUglyNum = getMin(dp[p2] * 2, dp[p3] * 3, dp[p5] * 5);
 
            // 更新数组的下一个元素
            dp[nextUglyIndex] = nextUglyNum;
            
            // 更新p2、p3、p5
            while (dp[p2] * 2 == nextUglyNum) {
                p2++;
            }
            
            while (dp[p3] * 3 == nextUglyNum) {
                p3++;
            }
            
            while (dp[p5] * 5 == nextUglyNum) {
                p5++;
            }
            
            nextUglyIndex++;
        }
        
        return dp[index - 1];
    }
    
    private int getMin(int num1, int num2, int num3) {
        int minNum12 = Math.min(num1, num2);
        return Math.min(minNum12, num3);
    }
    
    public static void main(String[] args) {
        System.out.println(new GetUglyNumber().GetUglyNumber_Solution(1));
        System.out.println(new GetUglyNumber().GetUglyNumber_Solution(1000));
        
        System.out.println(new GetUglyNumber().GetUglyNumber_SolutionII(1));
        System.out.println(new GetUglyNumber().GetUglyNumber_SolutionII(1000));
    }
}
