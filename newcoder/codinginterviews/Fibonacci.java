package com.codinginterviews.other;

/**
 * 
 * [剑指Offer-7]斐波那切数列 ：0 ，1，1，2，3，5，8，13...这样的数列称为斐波那契数列
 * 
 * [剑指Offer-8]跳台阶： 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法
 * 
 * 兔子繁殖：一般而言，兔子在出生两个月后，就有繁殖能力，一对兔子每个月能生出一对小兔子来。如果所有兔子都不死，那么一年以后可以繁殖多少对兔子？
 * 
 */
public class Fibonacci
{
    /**
     * n = 0 时 f(n) = 0
     * n = 1时 f(n) = 1
     * 
     * n > 1时，f(n) = f(n-1) + f(n-2)
     * 
     * 求f(n)
     */
    
    /**
     * 递归解法
     */
    public static int fibonacci(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return fibonacci(n-1) + fibonacci(n-2);
    }
    
    /**
     * 非递归解法
     */
    public static int fibonacciII(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        
        // 缓存 三个值来实现
        int f1 = 0;
        int f2 = 1;
        int sum_n = 0;
        for (int i = 2; i <= n; i++) {
            sum_n = f1 + f2;
            f1 = f2;
            f2 = sum_n;
        }
        
        return sum_n;
    }
    
    
    /**
     * 
     * 题目描述:
     *      一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法
     * 思路：
     *      得到n个台阶，一共可以跳多少个2步,这个也是一个斐波那契数列的一个应用。
     *      对于本题前提只有一次跳一阶，和跳两阶。
     *      如果两种跳法，1阶或者2阶，那么假定第一次跳的是一阶，那么剩下的是n-1个台阶，跳法是f（n - 1）
     *      假定第一次跳的是2阶，那么剩下的是n-2个台阶，跳法是f(n - 2)那么这个时候就能够得到总的跳法为 f(n) = f(n - 1) + f(n - 2)，
     *      然后这个出口就是:只有一阶的时候f(1) = 1, 只有两阶的时候f(2) = 2;
     */
    public static int jumpFloor(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        return jumpFloor(n-1) + jumpFloor(n-2);
    }
    
    public static int jumpFloorII(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        
        // 三个指针来实现
        int f1 = 1;
        int f2 = 2;
        int cur = 0;
        for (int i=3; i<=n; i++) {
            cur = f1 + f2;
            f1 = f2;
            f2 = cur;
        }
        return cur;
    }

    /**
     * 斐波那契数列又因数学家列昂纳多·斐波那契以兔子繁殖为例子而引入，故又称为“兔子数列”。
     * 一般而言，兔子在出生两个月后，就有繁殖能力，一对兔子每个月能生出一对小兔子来。如果所有兔子都不死，那么一年以后可以繁殖多少对兔子？
     * 我们不妨拿新出生的一对小兔子分析一下：
     * 第一个月小兔子没有繁殖能力，所以还是一对
     * 两个月后，生下一对小兔对数共有两对
     * 三个月以后，老兔子又生下一对，因为小兔子还没有繁殖能力，所以一共是三对
     * －－－－－－
     * 依次类推
     * 幼仔对数=前月成兔对数
     * 成兔对数=前月成兔对数+前月幼仔对数
     * 总体对数=本月成兔对数+本月幼仔对数
     * 总体对数变化为： 1 1 2 3 5 8 13 21 34 55 89 144
     * 可以看出幼仔对数、成兔对数、总体对数都构成了一个数列。这个数列有关十分明显的特点，那是：前面相邻两项之和，构成了后一项。
     * 
     */
    public static int rabbitCount(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return rabbitCount(n-1) + rabbitCount(n-2);
    }
    
    public static int rabbitCountII(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        // 三个指针来实现
        int f1 = 1;
        int f2 = 1;
        int cur = 0;
        for (int i=3; i<=n; i++) {
            cur = f1 + f2;
            f1 = f2;
            f2 = cur;
        }
        return cur;
    }
    
    
    public static void main(String[] args)
    {
        // 斐波那契数列f(6)
        System.out.println("Fibonacci(6): " + fibonacci(6) + " <-> " + fibonacciII(6));
        
        // 跳到第6层台阶，有多少种跳法
        System.out.println("JumpFloor(6): " + jumpFloor(6) + " <-> " + jumpFloorII(6));
        
        // 一年后兔子的数量
        System.out.println("RabbitCount(12): " + rabbitCount(12) + " <-> " + rabbitCountII(12));
    }

}
