package hard;

import java.util.ArrayDeque;
import java.util.Deque;

public class LongestValidParentheses {

	public static void main(String[] args) {
		System.out.println("-------- With Stack ---------");
		System.out.println(longestValidParenthesesWithStack(")())"));
		System.out.println(longestValidParenthesesWithStack("()(((((()()((((((()"));
		System.out.println(longestValidParenthesesWithStack("((((((()())"));
		System.out.println(longestValidParenthesesWithStack("()(()"));
		System.out.println(longestValidParenthesesWithStack("()"));
		System.out.println(longestValidParenthesesWithStack("("));
		
		System.out.println("-------- Without Stack ---------");
		System.out.println(longestValidParenthesesWithoutStack(")())"));
		System.out.println(longestValidParenthesesWithoutStack("()(((((()()((((((()"));
		System.out.println(longestValidParenthesesWithoutStack("((((((()())"));
		System.out.println(longestValidParenthesesWithoutStack("()(()"));
		System.out.println(longestValidParenthesesWithoutStack("()"));
		System.out.println(longestValidParenthesesWithoutStack("("));
	}

	// Using stack
	// Time - O(n); Space - O(n)
	public static int longestValidParenthesesWithStack(String s) {

		if (s.length() == 0)
			return 0;

		int maxLen = 0;
		char[] c = s.toCharArray();
		Deque<Integer> stack = new ArrayDeque<>(c.length);
		stack.push(-1);
		for (int i = 0; i < c.length; i++) {

			if (c[i] == '(') {
				stack.push(i);
			} else if (!stack.isEmpty()) {
				stack.pop();
				if (!stack.isEmpty()) {
					maxLen = Math.max(maxLen, i - stack.peek());
				} else {
					stack.push(i);
				}
			}

		}

		return maxLen;
	}

	// Time - O(n); Space - O(1)
	public static int longestValidParenthesesWithoutStack(String s) {

		if (s.length() == 0)
			return 0;
		int maxLen = 0;
		char[] c = s.toCharArray();
		// Scan from left to right for pairs
		// having more '(' than ')'
		int left = 0, right = 0;
		for (int i = 0; i < c.length; i++) {

			if (c[i] == '(')
				left++;
			else
				right++;

			if (left == right)
				maxLen = Math.max(maxLen, left * 2);
			else if (right > left)
				// this means that there are too many ')' as compare to '('
				// so reset and check for next '('
				left = right = 0;

		}

		// Now Scan from right to left for pairs
		// having more ')' than '('
		left = right = 0;
		for (int i = c.length - 1; i >= 0; i--) {

			if (c[i] == ')')
				right++;
			else
				left++;

			if (left == right)
				// as left and right are equal
				// we can use any one
				maxLen = Math.max(maxLen, right * 2);
			else if (left > right)
				// this means that there are too many '(' as compare to ')'
				// so reset and check for next ')'
				left = right = 0;

		}

		return maxLen;
	}

}
