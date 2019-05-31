package com.my.test.codinginterviews.other;

/**
 * 题目：
 * 数值的整数次方 -- 剑指Offer 12
 * 
 * 题目描述：
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 */
public class Power
{
    /**
     * 思路：
     * 1、写出指数的二进制表达，例如13表达为二进制1101。
     * 2、举例:10^1101 = 10^0001*10^0100*10^1000。
     * 3、通过&1和>>1来逐位读取1101，为1时将该位代表的乘数累乘到最终结果。
     */
    public double power(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        }
        double res = 1, cur = base;
        boolean isExpPositive = exponent > 0;
        
        if (!isExpPositive) {
            if(base == 0) {
                throw new RuntimeException("invalid param");
            }
            exponent = -exponent;
        }
        
        while(exponent != 0){
            if ((exponent & 1) == 1)
                res *= cur;
            cur *= cur;
            exponent >>= 1;
        }
        return isExpPositive ? res : (1/res);       
    }
    
    /**
     * 思路：
     * n为偶数，a^n=a^n/2*a^n/2;
     * n为奇数，a^n=（a^（n-1）/2）*（a^（n-1/2））*a
     * 时间复杂度O（logn）
     */
    public double powerII(double base, int exponent) {
        int n = Math.abs(exponent);
        if (n == 0) { 
            return 1;
        }
        if (n == 1) {
            return base;
        }
        
        double result = powerII(base, n >> 1);
        result *= result;
        
        // 奇数再乘以base
        if ((n & 1) == 1) { 
            result *= base;
        }
        
        if (exponent < 0) {
            result = 1 / result;
        }
        return result;
    }
    
    /**
     * 思路(累乘，时间空间复杂度o(n))：
     * 1、分类别计算
     * 2、若exp为0，返回1
     * 3、若exp为正，返回base*base*...(exp次)
     * 4、若exp为负，返回1/base*1/base*...(exp次)
     */
    public double powerIII(double base, int exponent) {
        if (exponent > 0) {
            return base * powerIII(base, exponent - 1);
        } else if(exponent < 0) {
            return (1/base) * powerIII(base, exponent + 1);
        } else {
            return 1;
        }
    }
    

    public static void main(String[] args)
    {
        Power p = new Power();
        System.out.println(p.power(1.2d, 2));
        System.out.println(p.power(1.2d, 0));
        System.out.println(p.power(1.2d, -2));
        
        System.out.println(p.powerII(1.2d, 2));
        System.out.println(p.powerII(1.2d, 0));
        System.out.println(p.powerII(1.2d, -2));
    }

}
