package com.my.test.codinginterviews.other;

/**
 * 题目：
 * 矩形覆盖 -- 剑指Offer 10
 *  
 * 题目描述：
 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。
 * 请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？ 
 */
public class RectCover
{
    /**
     * 思路：
     * 数学推导：
     * n = 1 时 , f(1) = 1 
     * n = 2 时 , f(2) = 2 
     * n = 3 时 , f(1) = 3 
     * n = 4 时 , f(1) = 5
     * ...
     * n = n 时，f(n) = f(n-1) + f(n-2) 
     * 
     * 即为f(n) = 
     * (1)f(n-1): 保持原来n = n-1时内容，并扩展一个 2*1 方块
     * (2)f(n-2): 新增加的2*1 方块与临近的2*1方块组成 2*2结构，然后可以变形成 “=”
     * 
     * 可以尝试将题目改成1*3方块覆盖3*n、1*4方块覆盖4*n。
                    相应的结论应该是：
                    （1）1 * 3方块 覆 盖3*n区域：f(n) = f(n-1) + f(n - 3)， (n > 3)
                    （2） 1 *4 方块 覆 盖4*n区域：f(n) = f(n-1) + f(n - 4)，(n > 4)
                    更一般的结论，如果用1*m的方块覆盖m*n区域，递推关系式为f(n) = f(n-1) + f(n-m)，(n > m)。
     * 
     */
    public int rectCover(int target) {
        if (target == 0 || target == 1 || target == 2) {
            return target;
        }
        return rectCover(target-1) + rectCover(target-2);
    }
    
    public static void main(String[] args)
    {
        RectCover inst = new RectCover();
        System.out.println(inst.rectCover(5));
    }

}
