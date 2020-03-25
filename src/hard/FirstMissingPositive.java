package hard;

public class FirstMissingPositive {
	public static void main(String[] args) {
		System.out.println(firstMissingPositive(new int[] { -1, -1, -2 }));
		System.out.println(firstMissingPositive(new int[] { -1, -1, 1 }));
		System.out.println(firstMissingPositive(new int[] { -1, 3, 0, 4, -2, 1 }));
		System.out.println(firstMissingPositive(new int[] { 3, 4, -1, 1 }));
		System.out.println(firstMissingPositive(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 }));
		System.out.println(firstMissingPositive(new int[] { 1, 2, 3, 4, 5, 6, 0, 7, 9, 10, 11, 12 }));
		System.out.println(firstMissingPositive(new int[] { 1, 2, -1 }));
		System.out.println(firstMissingPositive(new int[] { 2, 3, 7, 6, 8, -1, -10, 15 }));
		System.out.println(firstMissingPositive(new int[] { 1, 1, 0, -1, -2 }));
		System.out.println(firstMissingPositive(new int[] { 2, 3, -7, 6, 8, 1, -10, 15 }));
		System.out.println(firstMissingPositive(new int[] { 0, 10, 2, -10, -20, 1 }));
	}

	public static int firstMissingPositive(int[] nums) {

		// if nums is empty
		if (nums.length == 0)
			return 1;

		// shift negative and zero to the right
		// As we need to find the positive number

		int end = nums.length - 1;

		for (int i = 0; i < end; i++) {

			if (nums[i] <= 0) {
				// find next positive element
				while (end > i && nums[end] <= 0) {
					end--;
				}

				if (end == i)
					break;

				// swap elements
				int tmp = nums[i];
				nums[i] = nums[end];
				nums[end--] = tmp;

			}

		}

		// check if end is -ve
		if (nums[end] <= 0)
			end--;

		if (end < 0)
			return 1;
		// Since we need to find first minimum +ve number
		// We can use array indexes to know

		for (int i = 0; i <= end; i++) {

			int element = nums[i];
			element = (element < 0 ? element * -1 : element) - 1;
			// if not in range of array
			// Leave that element as it is
			if (element > end || nums[element] <= 0)
				continue;
			else
				// mark it -ve
				// This means this element is present in the array
				// Hence cannot be a missing number
				nums[element] *= -1;
		}

		// Now search the array to find first +ve number
		// The index of that number will be the first +ve missing number
		int result = -1;
		for (int i = 0; i <= end; i++) {
			if (nums[i] > 0) {
				result = i + 1;
				break;
			}
		}
		// if result is -1, then no element found in array
		// so the next index + 1 should be the answer
		result = result == -1 ? end + 2 : result;

		return result;

	}

}
