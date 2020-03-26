package hard;

import java.util.ArrayDeque;
import java.util.Deque;

public class TrappingRainWater {
	public static void main(String[] args) {
		System.out.println(trap(new int[] { 0, 1, 0, 2, 1, 0, 1, 3 }));
		System.out.println(trap(new int[] { 4, 2, 3 }));
		
		System.out.println(trapUsingPointers(new int[] { 0, 1, 0, 2, 1, 0, 1, 3 }));
		System.out.println(trapUsingPointers(new int[] { 4, 2, 3 }));
	}

	// Time O(n), Space O(n)
	public static int trap(int[] height) {

		if (height.length == 0)
			return 0;
		int result = 0;
		Deque<Integer> stack = new ArrayDeque<>();
		// store indexes into the stack
		// only if height[i] > height[i+1]
		for (int i = 0; i < height.length; i++) {
			if (stack.isEmpty() && height[i] == 0)
				continue;

			// push as long as the height[i] > height[i+1]
			// Otherwise
			// pop the elements from the stack
			// until we find next element greater than height[i]
			// Also Calculate the height
			while (!stack.isEmpty() && height[stack.peek()] < height[i]) {

				int ele = stack.pop();
				// if there is no element greater
				// Overflow means no water can be hold
				if (stack.isEmpty())
					break;

				// Distance between peek and current node
				// subtract width of current
				int distance = i - stack.peek() - 1;
				int hei = Math.min(height[i], height[stack.peek()]) - height[ele];
				result += distance * hei;

			}

			stack.push(i);
		}

		return result;
	}

	//Time O(n) and Space O(1)
	public static int trapUsingPointers(int[] height) {

		if (height.length == 0)
			return 0;
		int result = 0;
		// initialize the pointers
		int left = 0, leftmax = 0, right = height.length - 1, rightmax = height.length - 1;

		while (left < right) {

			if (height[left] < height[right]) {

				// if current left is smaller then left most max
				// Then water can be hold
				if (height[left] < height[leftmax])
					result += height[leftmax] - height[left];
				else
					// since leftmax is smaller
					// no water can be hole
					// update the leftmax
					leftmax = left;

				left++;

			} else {
				// if current right is smaller then right most max
				// Then water can be hold
				if (height[right] < height[rightmax])
					result += height[rightmax] - height[right];
				else
					// since rightmax is smaller
					// no water can be hole
					// update the rightmax
					rightmax = right;

				right--;

			}

		}

		return result;

	}
}
