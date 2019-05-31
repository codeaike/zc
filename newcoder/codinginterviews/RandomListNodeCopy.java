package com.my.test.codinginterviews.tree;

/**
 * 题目：
 * 复杂链表的复制 -- newcoder 剑指Offer 25
 * 
 * 题目描述：
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向
 * 任意一个节点），返回结果为复制后复杂链表的head。
 * （注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
 */
public class RandomListNodeCopy
{
    
    static class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
        
        @Override
        public String toString() {
            if (this.next == null) {
                return String.valueOf(this.label);
            }
            return this.label + "->" + this.next.toString() + 
                       (this.random == null ? "" : "(" + this.random.label + ")");
        }
    }
    /**
     * 思路：
     * 1、复制原链表的每个节点 A->B->C =>A->A'->B->B'->C->C' 
     * 
     * 2、A'等 根据A的random指向 分别指向正确的位置
     * 
     * 3、拆分链表，偶数序号元素拆出来 组成新链表
     */
    public RandomListNode clone(RandomListNode pHead) {
        if (pHead == null) {
            return null;
        }
        
        // 复制next指针
        RandomListNode curNode = pHead;
        
        while (curNode != null) {
            RandomListNode copy = new RandomListNode(curNode.label);
            
            // 缓存原来的下一个元素
            RandomListNode next = curNode.next;
            
            // 插入新元素
            copy.next = next;
            curNode.next = copy;
            
            // 指针后移
            curNode = next;
        }
        
        // 复制random指针
        curNode = pHead;
        
        while (curNode != null) {
            RandomListNode nextCurNode = curNode.next.next;
            
            // 复制random
            if (curNode.random != null) {
                curNode.next.random = curNode.random.next;
            }
            
            curNode = nextCurNode;
        }
        
        // 返回偶数序号元素组成的链表
        curNode = pHead;
        RandomListNode newNode = pHead.next;
        
        while (curNode != null) {
            RandomListNode cloneNode = curNode.next;
            curNode.next = cloneNode.next;
            if (cloneNode.next != null) {
                cloneNode.next = cloneNode.next.next;
            }
            curNode = curNode.next;
        }
        
        return newNode;
        
    }
    
    
    public static void main(String args[]) {
        
        RandomListNode head = createTestLinkedList(5, new RandomListNode(4));
        // 原链表
        System.out.println("origin link: " + head);

        // 删除重复元素
        System.out.println("copy link: " + new RandomListNodeCopy().clone(head));
        
    }
    
    private static RandomListNode createTestLinkedList(int n, RandomListNode addNode) {
        RandomListNode head = new RandomListNode(0);
        RandomListNode head1 = new RandomListNode(0);
        head1.random = head;
        head.next = head1;
        RandomListNode curNode = head1;
        int count = 1;
        for (int i = 1; i < n; i++) {
            curNode.next = new RandomListNode(i);
            curNode = curNode.next;
            if (i == 2 && count >= 0) {
                i--;
                count--;
            }
        }
        curNode.next = addNode;
        return head;
    }
}
