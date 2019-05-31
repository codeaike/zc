package com.codinginterviews.array;


/**
 * 题目：
 * 调整数组顺序使奇数位于偶数前面 -- newcoder 13
 * 
 * 题目描述：
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 * 使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，
 * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。 
 *
 */
public class ReOrderArray {

	/**
	 * 思路：
	 * 1、从后向前遍历数组，如[1,2,3,4,5,7]
	 * 2、遇到奇数交换，则继续向前遍历，直到找到一个偶数，把当前位置赋值为该偶数，
	 *    遍历到的这些奇数向前推进。如： [1,2,3,4,5,7]->[1,2,3,5,7,4]
	 * 
	 * 说明：
	 * 每次推进一个元素，性能较低 
	 *  
	 */
    public void reOrderArray(int [] array) {
        if (array == null || array.length <= 1) {
        	return;
        }
        
        int len = array.length;
        int i = len-1;
        
        // 从前到后遍历
        while (i >= 0) {
        	// 当前为偶数
        	if ((array[i] & 1) == 0) {
        		i--;
        		continue;
        	}
        	int curIdx = i;
        	// 当前遍历的元素为奇数
        	while (i >= 0 && (array[i] & 1) == 1) {
        		// 索引前移
        		i--;
        	}
        	// 找到了偶数元素
        	if (i >= 0 && (array[i] & 1) == 0) {
        		// 保存找寻的偶数元素的值
        		int tmp = array[i];
        		// 其中遍历到的奇数元素 向前推进一次,从cur
        		while (i < curIdx) {
        			array[i] = array[i+1];
        			i++;
        		}
        		// 赋值最后一个元素
        		array[curIdx] = tmp;
        	}
        	i--;
        }
    }
	
	/**
	 * 思路：
	 * 1.要想保证原有次序，则只能顺次移动或相邻交换。
	 * 2.i从左向右遍历，找到第一个偶数。
	 * 3.j从i+1开始向后找，直到找到第一个奇数。
	 * 4.将[i,...,j-1]的元素整体后移一位，最后将找到的奇数放入i位置，然后i++。
	 * 5.终止条件，i || j 越界
	 * 
	 * 说明：
	 * 本方法每次推进多次元素，性能较高
	 */
	public void reOrderArrayII(int[] array) {
	    if(array == null || array.length==0) {
	        return;
	    }
	    int i = 0, j = 0, len = array.length;
	    while(i < len && j < len){
	    	// 找到第一个奇数
	        while(i<len && (array[i] & 1)==1) {
	            i++;
	        }
	        j = i+1;
	        // 找到之后的第一个偶数
	        while(j<array.length && (array[j] & 1)==0) {
	            j++;
	        }
	        // 中间的元素后移
	        if(j < len){
	            int tmp = array[j];
	            for (int k = j-1; k >=i; k--) {
	                array[k+1] = array[k];
	            }
	            array[i++] = tmp;
	        }
	    }
	}
    
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5,6,8};
		int[] arr1 = {2,2,3,1,2,3,4,5,6,8};
		ReOrderArray inst = new ReOrderArray();
		inst.reOrderArray(arr);
		inst.reOrderArray(arr1);
		print(arr);
		print(arr1);
		
		int[] arr2 = {1,2,3,4,5,6,8};
		int[] arr3 = {2,2,3,1,2,3,4,5,6,8};
		ReOrderArray inst1 = new ReOrderArray();
		inst1.reOrderArrayII(arr2);
		inst1.reOrderArrayII(arr3);
		print(arr2);
		print(arr3);
	}
	

	private static void print(int[] arr) {
		for (int val : arr) {
			System.out.print(val + " ");
		}
		System.out.println("\n");
	}
}
