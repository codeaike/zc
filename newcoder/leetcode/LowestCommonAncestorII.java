package com.my.test.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目：
 * 二叉树的最近公共祖先 -- leetcode 236
 * 
 * 题目描述：
 * 
给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 
的深度尽可能大（一个节点也可以是它自己的祖先）。”

例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]

https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/15/binarytree.png

示例 1:
输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
输出: 3
解释: 节点 5 和节点 1 的最近公共祖先是节点 3。

示例 2:
输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
输出: 5
解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。

说明:
所有节点的值都是唯一的。
p、q 为不同节点且均存在于给定的二叉树中。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LowestCommonAncestorII
{
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }    
    
    /**
     * 思路：
     * 1、在左、右子树中分别查找是否包含p或q，如果（两种情况：左子树包含p，右子树包含q/左子树包含q，右子树包含p），
     *   那么此时的根节点就是最近公共祖先
     * 2、如果左子树包含p和q，那么到root->left中查找，最近公共祖先在左子树里面
     * 3、如果右子树包含p和q，那么到root->right中查找，最近公共祖先在右子树里面
     * 4、注意：不可能left和right的返回值同时都是nullptr
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) {
            return root;
        }
        
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        if (left!=null && right!=null) {
            return root;
        }
        
        return left == null ? right : left;
    }
    
    /**
     * 思路(非递归)：
     * 1、找到root->p的路径
     * 2、找到root->q的路径
     * 3、两条路径求最后一个相交节点
     */
    public TreeNode lowestCommonAncestorII(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) {
            return root;
        }
        
        List<TreeNode> pPath = findPath(root, p);
        List<TreeNode> qPath = findPath(root, q);
        
        TreeNode common = null;
        for (int i=0, j=0; i<pPath.size() && j<qPath.size(); i++,j++) {
            if (pPath.get(i) == qPath.get(j)) {
                common = pPath.get(i);
            }
        }
        
        return common;
    }
    
    private List<TreeNode> findPath(TreeNode root, TreeNode node) {
        List<TreeNode> path = new ArrayList<>();
        dfs(root, node, new ArrayList<>(), path);
        return path;
    }
    
    private void dfs(TreeNode root, TreeNode node, List<TreeNode> tmp, List<TreeNode> path) {
        if (root == null) {
            return;
        }
        
        tmp.add(root);
        
        if (root == node) {
            path.addAll(new ArrayList<>(tmp));
        }

        dfs(root.left, node, tmp, path);
        dfs(root.right, node, tmp, path);
        
        tmp.remove(tmp.size()-1);
    }
    
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode right = new TreeNode(2);
        root.right = right;
        TreeNode left = new TreeNode(3);
        root.left = left;
        System.out.println(new LowestCommonAncestorII().lowestCommonAncestorII(root, left, right).val);
        
    }
}
