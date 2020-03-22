package hard;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MergeKSortedLists {

	public static void main(String[] args) {

		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(4);
		l1.next.next = new ListNode(5);

		ListNode l2 = new ListNode(1);
		l2.next = new ListNode(3);
		l2.next.next = new ListNode(4);

		ListNode l3 = new ListNode(2);
		l3.next = new ListNode(6);

		ListNode merged = minHeapMerge(new ListNode[] { l1, l2, l3 });
		print(merged);
	}

	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}

		@Override
		public String toString() {
			return "" + val;
		}
	}

	public static ListNode mergeKLists(ListNode[] lists) {

		int len = lists.length;

		ListNode root = lists[0];

		for (int i = 1; i < len; i++) {
			root = simpleMerge(root, lists[i]);
		}

		return root;
	}

	public static ListNode simpleMerge(ListNode a, ListNode b) {

		if (a == null) {
			return b;
		}

		if (b == null) {
			return a;
		}
		ListNode result = null;

		if (a.val <= b.val) {
			result = a;
			result.next = simpleMerge(a.next, b);

		} else {
			result = b;
			result.next = simpleMerge(a, b.next);
		}

		return result;

	}

	public static ListNode minHeapMerge(ListNode[] lists) {

		int len = lists.length;
		if (len == 0)
			return null;
		Queue<ListNode> q = new PriorityQueue<>(len, new Comparator<ListNode>() {

			@Override
			public int compare(ListNode o1, ListNode o2) {
				if (o1.val < o2.val)
					return -1;
				if (o1.val > o2.val)
					return 1;
				return 0;
			}
		});

		// adding head node of every list
		for (ListNode n : lists)
			if (n != null)
				q.add(n);

		ListNode root = null, currentHead = null;

		while (!q.isEmpty()) {

			ListNode ele = q.poll();
			if (ele.next != null)
				q.add(ele.next);

			ListNode s = new ListNode(ele.val);
			if (root == null) {
				root = s;
				currentHead = s;
			} else {
				currentHead.next = s;
				currentHead = currentHead.next;
			}

		}

		return root;
	}

	public static void print(ListNode l) {

		ListNode current = l;

		while (current != null) {
			System.out.print(current.val + "->");
			current = current.next;
		}
		System.out.println("null");
	}

}
