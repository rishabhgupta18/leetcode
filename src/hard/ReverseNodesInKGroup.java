package hard;

import hard.MergeKSortedLists.ListNode;

public class ReverseNodesInKGroup {

	public static void main(String[] args) throws CloneNotSupportedException {
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(2);
		l1.next.next = new ListNode(3);
		l1.next.next.next = new ListNode(4);
		l1.next.next.next.next = new ListNode(5);
		l1.next.next.next.next.next = new ListNode(6);
		l1.next.next.next.next.next.next = new ListNode(7);
		l1.next.next.next.next.next.next.next = new ListNode(8);
		ListNode l2 = (ListNode) l1.clone();
		print(simpleReverseKGroup(l1, 3));
		print(enhanceReverseKGroup(l2, 3));

	}

	static class ListNode implements Cloneable {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}

		@Override
		public String toString() {
			return "" + val;
		}

		@Override
		protected Object clone() throws CloneNotSupportedException {
			ListNode n = new ListNode(this.val);
			if (this.next != null)
				n.next = (ListNode) this.next.clone();
			return n;
		}

	}

	// O(nk)
	public static ListNode simpleReverseKGroup(ListNode head, int k) {

		if (head == null)
			return null;
		if (k == 0)
			return head;

		ListNode root = null;
		ListNode start = head;
		ListNode end = head;
		ListNode prev = null;
		int index = 1;
		// O(n)
		while (start != null && end != null) {

			while (end != null && index < k) {
				end = end.next;
				index++;
			}

			if (end == null)
				break;

			ListNode nextP = end.next;
			end.next = null;
			// end is the root of the swapped list
			// start is now end
			// O(K)
			reverse(start);

			start.next = nextP;
			if (prev != null) {
				prev.next = end;
			} else {
				root = end;
			}

			prev = start;
			start = start.next;
			end = start;
			index = 1;
		}

		return root;
	}

	private static ListNode reverse(ListNode start) {

		if (start == null)
			return null;

		ListNode tmp = start;
		start = reverse(start.next);

		if (start != null) {
			start.next = tmp;
			start = tmp;
			start.next = null;
		} else {
			start = tmp;
		}
		return start;

	}

	// O(n)
	public static ListNode enhanceReverseKGroup(ListNode head, int k) {

		// calculated total length
		ListNode curr = head;
		int len = 0;
		while (curr != null) {
			curr = curr.next;
			len++;
		}
		return enhanceReverseKGroup(head, k, len);

	}

	public static ListNode enhanceReverseKGroup(ListNode head, int k, int len) {

		ListNode current = head;
		ListNode next = null;
		ListNode prev = null;
		int count = 0;

		if (len - k >= 0) {
			while (count < k && current != null) {
				next = current.next;
				current.next = prev;
				prev = current;
				current = next;
				count++;
			}

			// head is now pointing to last element after reverse
			if (next != null) {
				head.next = enhanceReverseKGroup(next, k, len - k);
			}
			// prev is now head
			return prev;
		} else {
			return head;
		}
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
