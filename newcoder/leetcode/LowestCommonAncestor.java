package com.my.test.leetcode.tree;

/**
 * 题目：
 * 二叉搜索树的最近公共祖先 -- leetcode 235
 * 
 * 题目描述：
 * 
给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 
的深度尽可能大（一个节点也可以是它自己的祖先）。”

例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]

https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/binarysearchtree_improved.png

示例 1:
输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
输出: 6 
解释: 节点 2 和节点 8 的最近公共祖先是 6。

示例 2:
输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
输出: 2
解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
 

说明:
所有节点的值都是唯一的。
p、q 为不同节点且均存在于给定的二叉搜索树中。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LowestCommonAncestor
{
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }    
    
    /**
     * 思路(递归)：
     * 1、从根节点开始遍历树
     * 2、如果节点 p 和节点 q 都在右子树上，那么以右孩子为根节点继续 1 的操作
     * 3、如果节点 p 和节点 q 都在左子树上，那么以左孩子为根节点继续 1 的操作
     * 4、如果条件 2 和条件 3 都不成立，这就意味着我们已经找到节 p 和节点 q 的 LCA 了
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) {
            return root;
        }
        
        int val = root.val;
        // 都在左树上
        if (val > p.val && val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        // 都在右树上    
        } else if (val < p.val && val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }
    
    /**
     * 思路：非递归
     */
    public TreeNode lowestCommonAncestorII(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) {
            return root;
        }
        
        TreeNode cur = root;
        int val;
        
        while (cur != null) {
            val = cur.val;
            if (val>p.val && val>q.val) {
                cur = cur.left;
            } else if (val<p.val && val<q.val) {
                cur = cur.right;
            } else {
                return cur;
            }
        }
        return null;
    }
}
