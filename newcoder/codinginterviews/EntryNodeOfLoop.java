package com.codinginterviews.list;

/**
 * 题目： 
 * 链表中环的入口结点 -- newcoder 剑指Offer 55
 * 
 * 题目描述：
 * 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
 */
public class EntryNodeOfLoop {

	static class ListNode {
	    int val;
	    ListNode next = null;

	    ListNode(int val) {
	        this.val = val;
	    }
	}

	/**
	 * 数学证明，摘自牛客网：
	 *  图片地址：https://uploadfiles.nowcoder.com/images/20170311/646147_1489199577531_5B1EFEB8A03B04A4781BC78AC00B89CE
		假设x为环前面的路程（黑色路程），a为环入口到相遇点的路程（蓝色路程，假设顺时针走）， c为环的长度（蓝色+橙色路程）
		当快慢指针相遇的时候：
		此时慢指针走的路程为Sslow = x + m * c + a
		快指针走的路程为Sfast = x + n * c + a
		2 Sslow = Sfast
		2 * ( x + m*c + a ) = (x + n *c + a)
		从而可以推导出：
		x = (n - 2 * m )*c - a
		= (n - 2 *m -1 )*c + c - a
		即环前面的路程 = 数个环的长度（为可能为0） + c - a
		什么是c - a？这是相遇点后，环后面部分的路程。（橙色路程）
		所以，我们可以让一个指针从起点A开始走，让一个指针从相遇点B开始继续往后走，
		2个指针速度一样，那么，当从原点的指针走到环入口点的时候（此时刚好走了x）
		从相遇点开始走的那个指针也一定刚好到达环入口点。
		所以2者会相遇，且恰好相遇在环的入口点。
		最后，判断是否有环，且找环的算法复杂度为：
		时间复杂度：O(n)
		空间复杂度：O(1)
	 */
	
	/**
	 * 思路：
	 * 1、快慢指针遍历链表，若相遇，则链表存在环
	 * 2、一个指针从相遇的节点出发，一个从链表头部出发，两个指针相遇的位置即为环的入口节点 
	 */
    public ListNode entryNodeOfLoop(ListNode pHead)
    {
        if (pHead == null || pHead.next == null) {
        	return null;
        }
        
        // 获取环中的一个节点
        ListNode meetNode = listNodeInCircle(pHead);
        if (meetNode == null) {
        	return null;
        }
        
        // 一指针从meetNode出发，一指针从头节点出发，相遇点即为环的入口节点
        ListNode first = meetNode;
        ListNode second = pHead;
        while (first != second) {
        	first = first.next;
        	second = second.next;
        }
        
        return first;
        
    }
	
    private ListNode listNodeInCircle(ListNode pHead) {
    	ListNode fast = pHead;
    	ListNode slow = pHead;
    	while (slow != null && fast != null && fast.next != null) {
    		fast = fast.next.next;
    		slow = slow.next;
    		if (fast == slow) {
    			return slow;
    		}
    	}
    	return null;
    }
    

    
    /**
     * 思路： 
     * 1、快慢指针遍历链表，若相遇，则链表存在环
     * 2、获取环的长度。以此相遇节点为开始节点，开始遍历，再次走到该节点，即得到环的长度
	 * 2、一个指针从链表头部出发，先走环的长度步，一个再从链表头部出发，两个指针相遇的位置即为环的入口节点 
     */
    public ListNode entryNodeOfLoopII(ListNode pHead) {
    	if (pHead == null || pHead.next == null) {
    		return null;
    	}
    	
    	// 暂存环的头结点
    	ListNode first = pHead;
    	ListNode second = pHead;
    	
    	// 获取环长度
    	int circleLen = getCircleLen(pHead);
    	if (circleLen <= 0) {
    		return null;
    	}
    	
    	// 第一个指针先走环的长度
    	for (int i = 0; i < circleLen; i++) {
    		first = first.next;
    	}
    	
    	while (first != null && second != null) {
    		if (first == second) {
    			return first;
    		}
    		first = first.next;
    		second = second.next;
    	}
    	
    	return null;
    }
    private int getCircleLen(ListNode node) {
    	// 获取一个环内节点
    	ListNode oneCircleNode = listNodeInCircle(node);
		
		// 以环内节点为起始，再次遍历到该节点，说明到达了一周
		if (oneCircleNode != null) {
			ListNode curNode = oneCircleNode.next;
			int count = 1;
			while (curNode != null) {
				if (curNode == oneCircleNode) {
					return count;
				}
				count++;
				curNode = curNode.next;
			}
		}
    	
    	return 0; 
    }

    /**
     * 思路：
     * 1、断链法，可以改变原有链表结构时，可使用该方法
     * 2、两指针遍历链表，一个起始指向第二个节点，另一个指向头节点，后一个指针一直断链，并依次推进两个指针，当前一个指针为null时，后一个指针会指向环的入口节点
     */
    public ListNode EntryNodeOfLoopIII(ListNode pHead){
    	if(pHead==null|| pHead.next==null) {
    		return null;
    	}
    	 
    	ListNode fast=pHead.next;
    	ListNode slow=pHead;

    	while(fast!=null){
    		slow.next=null;
    		slow=fast;
    		fast=fast.next;
    	}
    	 
    	return slow;
    }
}
