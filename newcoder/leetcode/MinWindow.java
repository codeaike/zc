package com.leetcode.str;

/**
 * 题目： 
 * minimum-window-substring -- newcoder 73 leetcode 76 
 * 
 * 题目描述：
 * 
Given a string S and a string T, find the minimum window in S which 
will contain all the characters in T in complexity O(n).
For example,
S ="ADOBECODEBANC"
T ="ABC"
Minimum window is"BANC".

Note: 
If there is no such window in S that covers all characters in T, 
return the emtpy string"".

If there are multiple such windows, you are guaranteed 
that there will always be only one unique minimum window in S.
 *
 */
public class MinWindow {

	/**
	 * 思路：
	 * 1、遍历s,截取t的长度个字符，判断是否有满足条件的，有的话直接返回
	 * 2、遍历s,截取t的长度+1个字符，判断是否有满足条件的
	 * ...
	 * 截取的长度递增
	 */
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
        	return "";
        }
        
        int len = t.length();
        // 遍历s
        for (int i=len, lenS=s.length(); i<=lenS; i++) {
        	for (int j=0; j+i<=lenS; j++) {
        		// 截取字符串
        		String sub = s.substring(j, j+i);
        		StringBuilder sb = new StringBuilder(sub);
        		// 判断sub是否包含t
        		int count = 0;
        		for (int k=0; k<len; k++) {
        			int idx = sb.indexOf(t.charAt(k) + "");
        			if (idx >= 0) {
        				sb.deleteCharAt(idx);
        				count++;
        			}
        		}
        		// 找到目标
        		if (count == len) {
        			return sub;
        		}
        	}
        }
        
        return "";
    }
	
    /**
     * 目标：
     * 给定一个字符串source和一个目标字符串target，在字符串source中找到包括所有目标字符串字母的子串。
     * 
     * 注意事项：
     * 如果在source中没有这样的子串，返回""，如果有多个这样的子串，返回起始位置最小的子串。
     * 
     * 个人的思路(待改进)：
     * 1、遍历source的char数组，
     * 2、两个指针，一个指针i指向当前遍历的起始位置，一个指针j指向终止位置，
     * 3、判断中间的部分是否覆盖子串，覆盖则记录当前的起止位置到缓存，当前长度到缓存，因为i不变时再往下找，长度均比当前找到的要大，所以直接重置指针i,j即可
     * 4、重置指针到下一个元素 i++,j=i(可优化为j=i+子串长度-1)
     * 
     * 缺陷，每次判断是否覆盖字符串，性能较低
     * 
     */
    public String minWindowII(String s, String t) {
        if (s == null || t == null || s.isEmpty() || t.isEmpty()) {
            return "";
        }
        
        char[] sourceArr = s.toCharArray();
        char[] targetArr = t.toCharArray();
        
        int sourceLen = sourceArr.length;
        int targetLen = targetArr.length;
        
        // 遍历source串开始索引
        int i = 0;
        // 最大开始索引
        int maxI = sourceLen - targetLen; 
        // 当前遍历source串的结束索引
        int j = 0;
        
        // 当前找到的匹配子串
        // 是否找到
        boolean hasFound = false;
        // 初始找子串索引,假定为整个source
        // 记录当前找到的能覆盖子串的开始、截止索引
        int curSubStrStart = 0;
        int curSubStrEnd = sourceLen - 1;
        int minLen = sourceLen;
        // 当前匹配子串的跨度
        int curLen = 0;
        // i>maxI之后，剩余的字符串长度不够，所以没有必须要继续遍历了
        while (i <= maxI) {
            // 如果当前区域能覆盖子串
            curLen = j - i + 1;
            if (isCoverSubstr(sourceArr, i, j, targetArr) && curLen <= minLen) {
                hasFound = true;
                curSubStrStart = i;
                curSubStrEnd = j;
                minLen = curLen;
                // 找到后则找到当前i条件下的最小串,无需继续寻找
                i++;
                j = i + targetLen - 1;
            // 没找到则后移指针
            } else {
                j++;
            }
            // j到达末位，则前移
            if (j == sourceLen) {
                i++;
                j = i + targetLen - 1;
            }
        }
        
        if (hasFound) {
            return s.substring(curSubStrStart, curSubStrEnd + 1);
        }
        
        return "";
    }
    
    // 源字符串的char数组，起始到终止区间，是否包含目标字符串
    private boolean isCoverSubstr(char[] sourceArr, int sBegin, int sEnd, char[] targetArr) {
        int targetLen = targetArr.length;
        if (targetLen > sEnd - sBegin + 1) {
            return false;
        }
        
        String subSourceStr = new String(sourceArr).substring(sBegin, sEnd + 1);
        StringBuilder sb = new StringBuilder(subSourceStr);
        
        // 从源字符串中逐个删除目标元素
        for (int i = 0; i < targetLen; i++) {
        	int idx = sb.indexOf(String.valueOf(targetArr[i]));
        	if (idx < 0) {
        		return false;
        	}
        	sb.deleteCharAt(idx);
        }
        
        return true;
    }
 
    
    /**
     * 此思路AC
     * 
     * 目标：
     * 给定一个字符串source和一个目标字符串target，在字符串source中找到包括所有目标字符串字母的子串。
     * 
     * 注意事项：
     * 如果在source中没有这样的子串，返回""，如果有多个这样的子串，返回起始位置最小的子串。
     * 
     * 
     * 大牛思路：
     * 1、子串构建一个map,里面存储每个char字符出现的个数(首先采用一个大小为256的数组充当hashmap的功能，记录tartget中字母出现次数)
     * 2、遍历source数组，开始时start=0，i=0; start记录当前字串的起点，i相当于当前字串的终点。
     * 3、用found表示当前字串中包含target中字符的数目，如果found=target.length()则表明当前字串包含了target中所有字符，如果满足，进入下一步。
     * 4、删除当前start占用的字符的数目，found计数器也要减1
     * 5、将start后移，取出start前面多余的元素，已达到字串最小的目标。
     * 6、判断，如果当前字串小于历史搜到的最小字串，则将当前字串的长度，起始点，结束点都记录，更新。
     * 7、将start后移，寻找下一个字串
     *  
     */
    public String minWindowIII(String s, String t) {
        if (s == null || t == null || s.isEmpty() || t.isEmpty()) {
            return "";
        }
        // 共256个字符，所以初始一个256的数组代替map
    	int[] td = new int[256];
    	for (char c : t.toCharArray()) {
    		td[c]++;
    	}
    	
    	// 找到的满足条件的匹配字符串的起止索引
    	int first = -1;
    	int end = 0;
    	int minLen = s.length();
    	
    	// 标示当前遍历的起始位置
    	int start = 0;
    	
    	// 标示找到的匹配个数
        int found = 0;
        
        // target的长度
        int tLen = t.length();
        // source的map
        int[] sd = new int[256];
        
        for (int i = 0; i < s.length(); i++) {
        	char curChar = s.charAt(i);
        	sd[curChar]++;
        	
        	// td中存在该元素的空缺，则计数器+1
        	if (sd[curChar] <= td[curChar]) {
        		found++;
        	}
        	
        	if (found == tLen) {
        		// 删除无用的元素
        		while (start <= i && sd[s.charAt(start)] > td[s.charAt(start)]) {
        			sd[s.charAt(start)]--;
        			start++;
        		}
        		
        		// 第一次发现 或者 判断当前发现的字符串是否小于之前发现的
        		if (first<0 || i-start+1 < minLen) {
        			first = start;
        			end = i;
        			minLen = i - start + 1;
        		}
        		
        		// 把start前移，当前sd的缓存去除当前start指向的元素，found计数器减1
        		sd[s.charAt(start)]--;
        		found--;
        		start++;
        	}
        }
        
    	if (first >= 0) {
    		return s.substring(first, end + 1);
    	}
    	
    	return "";
    } 
        
	public static void main(String[] args) {
		String s = "ABC", t = "AC";
		System.out.println(new MinWindow().minWindow(s, t));
		System.out.println(new MinWindow().minWindowII(s, t));
		System.out.println(new MinWindow().minWindowIII(s, t));
	}

}
