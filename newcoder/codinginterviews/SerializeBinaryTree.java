package com.codinginterviews.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目：
 * 序列化二叉树 -- newcoder 剑指Offer 61
 * 
 * 题目描述：
 * 请实现两个函数，分别用来序列化和反序列化二叉树.
 */
public class SerializeBinaryTree {

	static class TreeNode {
	    int val = 0;
	    TreeNode left = null;
	    TreeNode right = null;

	    public TreeNode(int val) {
	        this.val = val;
	    }
	}
	
	/**
	 * 思路：
	 * 根据前序遍历规则完成序列化与反序列化。
	 * 所谓序列化指的是遍历二叉树为字符串；
	 * 所谓反序列化指的是依据字符串重新构造成二叉树。
	 * 
 	 * 依据前序遍历序列来序列化二叉树，因为前序遍历序列是从根结点开始的。
 	 * 当在遍历二叉树时碰到Null指针时，这些Null指针被序列化为一个特殊的字符“#”。
  	 * 另外，结点之间的数值用逗号隔开。
	 */
	
	/**
	 * 思路：
	 * 1、逗号隔开每个元素，如果元素为null,用#表示
	 */
    String Serialize(TreeNode root) {
        if (root == null) {
        	return "";
        }
        StringBuilder sb = new StringBuilder(); 
        serializeHelper(root, sb);
        return sb.toString();
    }
    
    void serializeHelper(TreeNode node, StringBuilder sb) {
    	if (node == null) {
    		sb.append("#,");
    		return;
    	}
    	// 处理非null元素
    	sb.append(node.val + ",");
    	// 处理左右孩子节点
    	serializeHelper(node.left, sb);
    	serializeHelper(node.right, sb);
    }
 
    private int index = -1;
    
    /**
     *  思路：
     *  反序列化，解析字符串为二叉树
     */
    TreeNode Deserialize(String str) {
        if (str == null || str.isEmpty()) {
        	return null;
        }
        String[] strArr = str.split(",");
    	return deserializeHelper(strArr);
    }
    
    TreeNode deserializeHelper(String[] strArr) {
    	index++;
    	if (!strArr[index].equals("#")) {
    		TreeNode node = new TreeNode(-1);
    		node.val = Integer.parseInt(strArr[index]);
    		node.left = deserializeHelper(strArr);
    		node.right = deserializeHelper(strArr);
    		return node;
    	}
    	return null;
    }
	
    /**
     * 思路：
     * 1、非递归，层次遍历的思想
     */
    String SerializeII(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        if(root != null){
            queue.add(root);
        }
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node != null){
                queue.offer(node.left);
                queue.offer(node.right);
                sb.append(node.val + ",");
            }else{
                sb.append("#" + ",");
            }
        }
        // 去掉末尾的逗号
        if(sb.length() != 0)
            sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
    
    
    TreeNode DeserializeII(String str) {
        TreeNode head = null;
        if(str == null || str.length() == 0) {
            return head;
        }
        String[] nodes = str.split(",");
        TreeNode[] treeNodes = new TreeNode[nodes.length];
        // 构建元素到数组中
        for(int i=0; i<nodes.length; i++){
            if(!nodes[i].equals("#")) {
                treeNodes[i] = new TreeNode(Integer.valueOf(nodes[i]));
            }
        }
        // 挂接元素之间的关系
        for(int i=0, j=1; j<treeNodes.length; i++){
            if(treeNodes[i] != null){
                treeNodes[i].left = treeNodes[j++];
                treeNodes[i].right = treeNodes[j++];
            }
        }
        return treeNodes[0];
    }
    
	public static void main(String[] args) {
        TreeNode root = createBinaryTree();
        String str = new SerializeBinaryTree().SerializeII(root);
        System.out.println(str);
        
        System.out.println(new SerializeBinaryTree().DeserializeII(str).val);
    }
    
    private static TreeNode createBinaryTree() {
        TreeNode root = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        
        root.left = node1;
        root.right = node2;
        
        // TreeNode node3 = new TreeNode(3);
        // node1.left = node3;
        
        TreeNode node4 = new TreeNode(4);
        node1.right = node4;
        
        TreeNode node5 = new TreeNode(5);
        node2.left = node5;
        
        TreeNode node6 = new TreeNode(6);
        node2.right = node6;
        
        return root;
    }

}
