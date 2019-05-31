package com.my.test.codinginterviews.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目：
 * 树的子结构 -- newcoder 剑指Offer 17
 * 
 * 题目描述：
 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 */
public class HasSubtree
{
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    
    /**
     * 思路：
     * 1、层次遍历root1, 找到与root2相同的节点
     * 2、找到后判断以此节点为根节点，是否能在root1中找到与root2相同的树结构(此处判断用递归查找)
     */
    public boolean hasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return false;
        }
        
        // 层次遍历root1
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root1);
        
        TreeNode cur;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i=0; i<size; i++) {
                cur = queue.poll();
                // 找到了与root2头结点相同的结点
                if (cur.val == root2.val) {
                    // 找到了即返回
                    if (isHasSubtreeHelper(cur, root2)) {
                        return true;
                    }
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
        
        return false;
    }
    
    /**
     * 思路：
     * 1、DFS寻找与root根节点值相同的root1中的节点
     * 2、找到后判断以此节点为根节点，是否能在root1中找到与root2相同的树结构
     */
    public boolean hasSubtreeII(TreeNode root1, TreeNode root2) {
        boolean hasFound = false;
        if (root1 != null && root2 != null) {
            if (root1.val == root2.val) {
                // 找到了即返回
                if (isHasSubtreeHelper(root1, root2)) {
                    hasFound = true;
                }
            }
            // 没有找到，继续找左右子树
            if (!hasFound) {
                hasFound = hasSubtreeII(root1.left, root2);
            }
            if (!hasFound) {
                hasFound = hasSubtreeII(root1.right, root2);
            }
        }
        return hasFound;
    }
    
    /**
     * 思路(代码更简洁的写法)：
     * 1、DFS寻找与root根节点值相同的root1中的节点
     * 2、找到后判断以此节点为根节点，是否能在root1中找到与root2相同的树结构
     */
    public boolean hasSubtreeIII(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return false;
        }
        
        return isHasSubtreeHelper(root1, root2) || hasSubtreeIII(root1.left, root2) || hasSubtreeIII(root1.right, root2); 
    }
    
    /**
     * 函数作用：
     * 如果root1和root2头结点相同，root1是否包含root2
     * 
     * 思路：
     * 1、root2为空，说明root2遍历完毕，则返回true
     * 2、root1为空，root2不为空，root1遍历完毕，root2还没有遍历完，返回false
     * 3、两者都不为空，置不同，返回false
     * 4、递归处理左右子树
     */
    private boolean isHasSubtreeHelper(TreeNode root1, TreeNode root2) {
        
        if (root2 == null) {
            return true;
        }
        
        if (root1 == null) {
            return false;
        }
        
        if (root1.val != root2.val) {
            return false;
        }

        return isHasSubtreeHelper(root1.left, root2.left) && isHasSubtreeHelper(root1.right, root2.right);
    }
    
    public static void main(String[] args)
    {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
        
        TreeNode left_left = new TreeNode(4);
        left.left = left_left;
        
        TreeNode left_right = new TreeNode(5);
        left.right = left_right;
        
        System.out.println(new HasSubtree().hasSubtree(root, left));
    }

}
