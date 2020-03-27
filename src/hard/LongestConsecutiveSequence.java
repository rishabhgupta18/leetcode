package hard;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class LongestConsecutiveSequence {

	public static void main(String[] args) {
		System.out.println(longestConsecutive(new int[] { 100, 4, 200, 1, 3, 2 }));
	}
	
	public static int longestConsecutive(int[] nums) {

		if (nums.length == 0)
			return 0;

		int ans = 0;
		// To check a number in O(1)
		Set<Integer> set = new HashSet<>();

		// Insert all elements of nums to set
		for (int n : nums)
			set.add(n);

		Iterator<Integer> iterator = set.iterator();
		while (iterator.hasNext()) {

			int num = iterator.next();

			if (!set.contains(num - 1)) {

				int c = num;
				while (set.contains(c)) {
					c++;
				}

				ans = Math.max(ans, c - num);

			}

		}
		return ans;
	}
	
}
