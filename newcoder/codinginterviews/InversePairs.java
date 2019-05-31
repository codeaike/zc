package com.my.test.codinginterviews.array;

/**
 * 题目：
 * 数组中的逆序对 -- newcoder 剑指Offer 35
 * 
 * 题目描述：
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 * 输入一个数组,求出这个数组中的逆序对的总数P。
 * 并将P对1000000007取模的结果输出。 即输出P%1000000007
 * 
 * 输入 1,2,3,4,5,6,7,0 
 * 输出 7
 */
public class InversePairs
{
    /**
     * 思路(性能较低)：
     * 1、暴力穷举
     * 2、两层遍历输出最终结果
     */
    public int inversePairs(int [] array) {
        if (array == null || array.length <= 1) {
            return 0;
        }
        
        int len = array.length;
        int count = 0;
        
        for (int i=0; i<len-1; i++) {
            int base = array[i];
            for (int j=i+1; j<len; j++) {
                if (array[j] < base) {
                    count++;
                }
            }
        }
        
        return count;
    }
    
    /**
     * 思路(性能较低)：
     * 1、借鉴归并排序的思想，统计逆序对数
     * 2、分开计算子序列的逆序对数，合并时，逐个遍历元素，获取逆序对数 
     */
    public int inversePairsII(int [] array) {
        if (array == null || array.length <= 1) {
            return 0;
        }
        return getInversePairsNum(array, 0, array.length-1);
    }
    
    private int getInversePairsNum(int[] arr, int start, int end) {
        if (start < end) {
            int mid = (start + end) >> 1;
            int first = getInversePairsNum(arr, start, mid);
            int second = getInversePairsNum(arr, mid+1, end);
            int merge = getInversePairsNum(arr, start, mid, end);
            return first + second + merge;
        }
        return 0;
    }
    
    private int getInversePairsNum(int[] arr, int start, int mid, int end) {
        int sum = 0;
        // 归并两个子序列，获取两个子序列结合后新增的逆序对
        for (int i = start; i <= mid; i++) {
            int curValue = arr[i];
            for (int j = mid + 1; j <= end; j++) {
                int value = arr[j];
                if (curValue > value) {
                    sum++;
                }
            }
        }
        return sum;
    }
    
    /**
     * 优化思路：
     * 1、分开计算子序列的逆序对数
     * 2、合并时，子序列进行排序，从排序后的最后一个元素开始比较
     * 3、如果上一个子序列最后一个元素大于后一个子序列的最后一个元素，则逆序对个数为后一个子序列的长度，并把大的元素，暂存一个复制数组的最后一个空缺位
     * 4、如果上一个子序列的最后一个元素小于后一个子序列的最后一个元素，则把后一个序列的最后一个元素放到复制数组的最后一个空缺位
     * 5、处理两个子序列未处理完的部分
     * 6、排序后的部分 更新到 原数组中 
     */
    public int inversePairsIII(int [] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        
        // 归并获取逆序对数量
        return getInversePairsNumFast(array, 0, array.length - 1);
        
    }
    
    private int getInversePairsNumFast(int[] arr, int start, int end) {
        if (start == end) {
            return 0;
        }
        
        int mid = (start + end) >> 1;
        
        int left = getInversePairsNumFast(arr, start, mid) % 1000000007;
        int right = getInversePairsNumFast(arr, mid + 1, end) % 1000000007;
        
        // 记数第一个序列的最后一个索引
        int i = mid;
        
        // 记数第二个序列的最后一个索引
        int j = end;
        
        // copy当前子数组序列段用于排序
        int len = end - start + 1;
        int[] copy = new int[len];
        // 第一个数据要放的位置
        int copyIndex = len - 1;
        
        // 逆序对的数量
        int count = 0;
        
        // 遍历两个序列，从最后一个元素开始
        while (i >= start && j >= mid + 1) {
            if (arr[i] > arr[j]) {
                // 逆序对增加 当前第二个序列的元素个数
                count += j - mid;
                // 大的元素拷贝到拷贝的排序数组中
                copy[copyIndex] = arr[i];
                
                if(count >= 1000000007) {//数值过大求余
                    count %= 1000000007;
                }
                
                // i位置前移
                i--;
            } else {
                // 此种情况 当前第一个序列中的所有元素无法与第二个序列中的这个元素组成逆序对
                // count不变，第二个序列中的当前元素进入拷贝数组
                copy[copyIndex] = arr[j];
                
                //j位置前移
                j--;
            }
            // copyIndex位置前移
            copyIndex--;
        }
        
        // 处理剩余的第一个序列的元素
        while (i >= start) {
            copy[copyIndex--] = arr[i--];
        }
        
        // 处理剩余的第二个序列的元素
        while (j >= mid + 1) {
            copy[copyIndex--] = arr[j--];
        }
        
        // copy数组复制到原数组
        for(int k = 0; k < len; k++) {
            arr[k + start] = copy[k];
        }
        
        return (count + left + right) % 1000000007;
    }
    
    
    public static void main(String[] args)
    {
        int[] arr = {1,2,3,4,5,6,7,0};
        System.out.println(new InversePairs().inversePairs(arr));
        System.out.println(new InversePairs().inversePairsII(arr));
        System.out.println(new InversePairs().inversePairsIII(arr));
        
        int[] arr1 = {7,5,6,4};
        System.out.println(new InversePairs().inversePairs(arr1));
        System.out.println(new InversePairs().inversePairsII(arr1));
        System.out.println(new InversePairs().inversePairsIII(arr1));
    }

}
