package com.codinginterviews.list;

/**
 * 题目：
 * 删除链表中重复的结点 -- newcoder 剑指Offer 56
 * 
 * 题目描述：
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，
 * 返回链表头指针。 
 * 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 *
 */
public class DeleteDuplication {
	static class ListNode {
		int val;
		ListNode next;
		public ListNode(int val) {
			this.val = val;
		}
		
		// 便于打印不能作用与循环链表
        @Override
        public String toString() {
            if (this.next == null) {
                return String.valueOf(this.val);
            }
            return this.val + "->" + this.next.toString();
        }
	}
	
 
	
	/**
	 * 题目：
	 * 删除链表中所有重复的元素  0->1->1->2  =>   0->2 
	 * 
	 * 思路：
	 * 1、新建一个临时头节点，避免头节点是重复元素时，特殊处理
	 * 2、返回链表头节点
	 */
	public static ListNode deleteDuplication(ListNode pHead) {
		if (pHead == null) {
			return pHead;
		}
		
		// 临时头节点
		ListNode root = new ListNode(-1);
		root.next = pHead;
		
		// 两个指针
		ListNode preNode = root;
		ListNode curNode = pHead;
		
		while (curNode != null && curNode.next != null) {
			// 遇到重复节点，则遍历所有相同节点，pre->next 指向 最后一个相同节点的next
			if (curNode.val == curNode.next.val) {
				while (curNode != null && curNode.next != null && curNode.val == curNode.next.val) {
					curNode = curNode.next;
				}
				// curNode此时为重复元素中的最后一个元素
				// 删除重复元素
				preNode.next = curNode.next;
			} else {
				preNode = curNode;
			}
			// 后移curNode
			curNode = curNode.next;
		}
		
        return root.next;		
	}
	
	/**
	 * 思路：
	 * 1、新建前驱节点，next指向pHead，方便处理头节点元素重复的场景
	 * 2、双指针，一个pre指针指向前一个不重复的节点，一个cur指向当前遍历的节点，分情况处理
	 * 3、当遍历到重复的节点时，pre指针的next指向当前重复节点的最后一个节点的next,即删除重复元素，cur指针后移
	 * 4、当遍历到不重复的节点，只需要pre、cur指针同时后移即可
	 */
    public static ListNode deleteDuplicationII(ListNode pHead){
        if(pHead == null || pHead.next == null) {
            return pHead;
        }
        ListNode root = new ListNode(-1);
        root.next = pHead;
        
        ListNode pre = root;
        ListNode cur = pHead;
        
        while (cur != null) {
            boolean dup = false;
            while (cur.next != null && cur.val == cur.next.val) {
                dup = true;
                cur = cur.next;
            }
            if (dup) {
                pre.next = cur.next;
                cur = cur.next;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
        return root.next; 
    }
	
/**
 * ==========================================================================
 * 下面两个题目为删除重复元素，但是保留其中之一
 * ==========================================================================
 * 	
 */
	
	
	/**
	 * 题目：
	 * o(n)时间  有序的链表删除重复元素,保留第一个重复的元素
	 * 0->1->1->2  =>   0->1->2 
	 * 
	 * 思路：
	 * 1、遍历链表，一个指针指向前驱节点  一个指向下一个节点
	 * 2、返回新的链表(新链表，头结点引用)
	 * 
	 */
	public static ListNode deleteDuplicationReamainFirstOne(ListNode pHead) {
		if (pHead == null) {
			throw new RuntimeException("link is empty");
		}
		
		ListNode root = new ListNode(-1);
		root.next = pHead;
		
		ListNode pre = root;
		ListNode curNode;
		
		while (pre != null) {
			curNode = pre.next;
			// 元素重复时 删除后续重复元素
			if (curNode != null && pre.val == curNode.val) {
				pre.next = curNode.next;
			// 不重复时 pre后移一位
			} else {
				pre = pre.next;
			}
		}
		
		return root.next;
	}
	
	
	/**
	 * 题目:
	 * o(n)时间  有序的链表删除重复元素,保留最后一个重复的元素
	 * 0->1->1->2  =>   0->1->2 
	 * 
	 * 思路：
	 * 1、遍历链表，一个指针指向前驱节点 一个指向当前节点
	 * 2、返回新的链表(新链表，头结点引用)
	 * 
	 */
	public static ListNode deleteDuplicationRemainLastOne(ListNode head) {
		if (head == null) {
			throw new RuntimeException("link is empty");
		}
		
		ListNode newHead = head;
		
		ListNode preNode = newHead;
		ListNode curNode = newHead.next;
	
		// 当头结点与下一结点值相同，需要删除头结点及后续相同节点
		if (preNode.val == curNode.val) {
			while (preNode.val == curNode.val) {
				preNode = preNode.next;
				curNode = curNode.next;
			}
			newHead = preNode;
		}
		
		// 头结点与下一结点值不同
		while (curNode != null && curNode.next != null) {
			if (curNode.val != curNode.next.val) {
				preNode = curNode;
			} else {
				preNode.next = curNode.next;
			}
			curNode = curNode.next;
		}
		
		return newHead;
	}
	
    public static void main(String args[]) {
        
		ListNode head = createTestLinkedList(5, new ListNode(4));
        // 原链表
        System.out.println("origin link: " + head);
 
        // 删除重复元素
        System.out.println("remove repeat node: " + deleteDuplicationII(head));
 
        
		ListNode head1 = createTestLinkedList(5, new ListNode(4));
        // 删除重复元素，保留第一个重复的元素
        System.out.println("remove repeat node but left first one: " + deleteDuplicationReamainFirstOne(head1));
        
        
		ListNode head2 = createTestLinkedList(5, new ListNode(4));
        // 删除重复元素，保留最后一个重复的元素
        System.out.println("remove repeat node but left last one: " + deleteDuplicationRemainLastOne(head2));
        
    }
    
    private static ListNode createTestLinkedList(int n, ListNode addNode) {
        ListNode head = new ListNode(0);
        ListNode head1 = new ListNode(0);
        head.next = head1;
        ListNode curNode = head1;
        int count = 1;
        for (int i = 1; i < n; i++) {
            curNode.next = new ListNode(i);
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

