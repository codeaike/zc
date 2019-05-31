package com.my.test.codinginterviews.array;

/**
 * 题目：
 * 旋转数组的最小数字 -- 剑指Offer 6
 * 
 * 题目描述：
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。 
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。 
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 *
 */
public class MinNumberInRotateArray {

    /**
     * 思路： 
     * 1、根据数组部分有序的特点，采用二分查找方法
        采用二分法解答这个问题，
        mid = low + (high - low)/2
        需要考虑三种情况：
        (1)array[mid] > array[high]:
        出现这种情况的array类似[3,4,5,6,0,1,2]，此时最小数字一定在mid的右边。
        low = mid + 1
        (2)array[mid] == array[high]:
        出现这种情况的array类似 [1,0,1,1,1] 或者[1,1,1,0,1]，此时最小数字不好判断在mid左边
        还是右边,这时只好一个一个试 ，
        high = high - 1
        (3)array[mid] < array[high]:
        出现这种情况的array类似[2,2,3,4,5,6,6],此时最小数字一定就是array[mid]或者在mid的左
        边。因为右边必然都是递增的。
        high = mid
        注意这里有个坑：如果待查询的范围最后只剩两个数，那么mid 一定会指向下标靠前的数字
        比如 array = [4,6]
        array[low] = 4 ;array[mid] = 4 ; array[high] = 6 ;
        如果high = mid - 1，就会产生错误， 因此high = mid
        但情形(1)中low = mid + 1就不会错误
     */
    public int minNumberInRotateArray(int [] array) {
        if (array == null || array.length <= 0) {
            return 0;
        }
        
        int len = array.length;
        int begin = 0;
        int end = len - 1;
        
        // mid = (begin + end) / 2
        int mid = end >> 1;
        
        while (begin < end) {
            mid = (begin + end) >> 1;
            // 如果当前end位置的元素大于mid位置，说明最小值在mid左侧
            if (array[end] > array[mid]) {
                end = mid;
            } else if (array[end] < array[mid]) {
                // 此时最小值在mid右侧
                begin = mid + 1;
            } else {
                // 元素重复, 向左侧推进end值
                end = end - 1;
            }
        }
        
        return array[begin];
    }


    /**
     * 思路：
                    （1）我们用两个指针left,right分别指向数组的第一个元素和最后一个元素。按照题目的旋转的规则，第一个元素应该是大于最后一个元素的（没有重复的元素）。
                    但是如果不是旋转，第一个元素肯定小于最后一个元素。
                    
                    （2）找到数组的中间元素。
                    中间元素大于第一个元素，则中间元素位于前面的递增子数组，此时最小元素位于中间元素的后面。我们可以让第一个指针left指向中间元素。
                    移动之后，第一个指针仍然位于前面的递增数组中。
                    中间元素小于第一个元素，则中间元素位于后面的递增子数组，此时最小元素位于中间元素的前面。我们可以让第二个指针right指向中间元素。
                    移动之后，第二个指针仍然位于后面的递增数组中。
                    这样可以缩小寻找的范围。
                    
                    （3）按照以上思路，第一个指针left总是指向前面递增数组的元素，第二个指针right总是指向后面递增的数组元素。
                    最终第一个指针将指向前面数组的最后一个元素，第二个指针指向后面数组中的第一个元素。
                    也就是说他们将指向两个相邻的元素，而第二个指针指向的刚好是最小的元素，这就是循环的结束条件。
                    到目前为止以上思路很耗的解决了没有重复数字的情况，这一道题目添加上了这一要求，有了重复数字。
                    因此这一道题目比上一道题目多了些特殊情况：
                    
                    我们看一组例子：｛1，0，1，1，1｝ 和 ｛1，1， 1，0，1｝ 都可以看成是递增排序数组｛0，1，1，1，1｝的旋转。
                    这种情况下我们无法继续用上一道题目的解法，去解决这道题目。因为在这两个数组中，第一个数字，最后一个数字，中间数字都是1。
                    第一种情况下，中间数字位于后面的子数组，第二种情况，中间数字位于前面的子数组。
                    因此当两个指针指向的数字和中间数字相同的时候，我们无法确定中间数字1是属于前面的子数组（绿色表示）还是属于后面的子数组（紫色表示）。
                    也就无法移动指针来缩小查找的范围，只能移动begin指针去尝试。
     */
    public int minNumberInRotateArrayII(int [] array) {
        if (array == null || array.length <= 0) {
            return 0;
        }
        
        int len = array.length;
        int begin = 0;
        int end = len - 1;
        
        // mid = (begin + end) / 2
        int mid = end >> 1;
        
        while (begin < end) {
            // 终止条件
            if (begin == end - 1) {
                mid = end;
                break;
            }
            mid = (begin + end) >> 1;
            // 如果当前begin位置的元素大于mid位置，说明最小值在mid左侧
            if (array[begin] > array[mid]) {
                end = mid;
            } else if (array[begin] < array[mid]) {
                // 此时最小值在mid右侧
                begin = mid;
            } else {
                // 元素重复, 向右侧推进begin
                begin = begin + 1;
            }
        }
        
        return array[mid];
    }
    
    public static void main(String[] args){
        int[] arr = {3,4,5,1,2};
        int[] arr1 = {1,1,1,0,1};
        MinNumberInRotateArray ins = new MinNumberInRotateArray();
        System.out.println(ins.minNumberInRotateArray(arr));
        System.out.println(ins.minNumberInRotateArray(arr1));
        System.out.println(ins.minNumberInRotateArrayII(arr));
        System.out.println(ins.minNumberInRotateArrayII(arr1));
    }
    
}

