package com.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 题目： 
 * 组合总和II -- leetcode 40
 * 
 * 题目描述：
 * 
给定一个数组 candidates 和一个目标数 target ，找出 candidates 
中所有可以使数字和为 target 的组合。

candidates 中的每个数字在每个组合中只能使用一次。

说明：

所有数字（包括目标数）都是正整数。
解集不能包含重复的组合。 
示例 1:

输入: candidates = [10,1,2,7,6,1,5], target = 8,
所求解集为:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
示例 2:

输入: candidates = [2,5,2,1,2], target = 5,
所求解集为:
[
  [1,2,2],
  [5]
]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/combination-sum-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CombinationSumII {
	/**
	 * 思路： 
	 * 1、回溯算法
	 * 2、需要去除本元素，和重复的元素(排序后处理)
	 */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        
        if (candidates == null) {
            return res;
        }
        
        Arrays.sort(candidates);
        addCombinations(candidates, 0, target, new ArrayList<Integer>(), res);
        
        return res;
    }
    
    private void addCombinations(int[] candidates, int start, int target, List<Integer> cache, List<List<Integer>> res){
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(cache));
        }
        for (int i=start; i<candidates.length; i++) {
            if (candidates[i] > target) {
                return;
            }
            // 去重
            if (i>start && candidates[i] == candidates[i-1]) {
                continue;
            }
            
            cache.add(candidates[i]);
            addCombinations(candidates, i+1, target-candidates[i], cache, res);
            cache.remove(cache.size()-1);
        }
    }
}
