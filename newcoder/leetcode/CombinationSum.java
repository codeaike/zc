package com.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 题目： 
 * 组合总和 -- leetcode 39
 * 
 * 题目描述：
 * 
给定一个无重复元素的数组 candidates 和一个目标数 target ，
找出 candidates 中所有可以使数字和为 target 的组合。
candidates 中的数字可以无限制重复被选取。

说明：
所有数字（包括 target）都是正整数。
解集不能包含重复的组合。 

示例 1:
输入: candidates = [2,3,6,7], target = 7,
所求解集为:
[
  [7],
  [2,2,3]
]

示例 2:
输入: candidates = [2,3,5], target = 8,
所求解集为:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/combination-sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CombinationSum {
	/**
	 * 思路： 
	 * 1、回溯算法
	 * 2、递归找和为target的组合，出口为和超过了target
	 */
    public List<List<Integer>> combinationSum(int[] bb, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (bb == null) {
            return res;
        }
        
        addCombinations(bb, 0, target, new ArrayList<Integer>(), res);
        
        return res;
    }
    
    private void addCombinations(int[] bb, int start, int target, List<Integer> cache, List<List<Integer>> res) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(cache));
            return;
        }
        for (int i=start; i<bb.length; i++) {
            cache.add(bb[i]);
            addCombinations(bb,i,target-bb[i],cache,res);
            cache.remove(cache.size()-1);
        }
    }
	
    
    /**
     * 思路： 
     * 优化后的回溯
     */
    public List<List<Integer>> combinationSumII(int[] bb, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (bb == null) {
            return res;
        }
        
        // 排序数组后 可以在递归的时候减少递归次数，配合 if (bb[i] > target) break;
        Arrays.sort(bb);
        
        addCombinationsII(bb, 0, target, new ArrayList<Integer>(), res);
        
        return res;
    }
    
    private void addCombinationsII(int[] bb, int start, int target, List<Integer> cache, List<List<Integer>> res) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(cache));
            return;
        }
        for (int i=start; i<bb.length; i++) {
        	// 配合排序后的数组 提升性能
            if (bb[i] > target) {
                break;
            }
            cache.add(bb[i]);
            addCombinationsII(bb,i,target-bb[i],cache,res);
            cache.remove(cache.size()-1);
        }
    }
}
