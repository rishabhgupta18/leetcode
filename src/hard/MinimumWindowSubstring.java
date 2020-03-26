package hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinimumWindowSubstring {

	public static void main(String[] args) {
		System.out.println(minWindow("ADOBECODEBANC", "ABC"));
		System.out.println(minWindow("aa", "aa"));
	}

	static class Pair<K, V> implements Map.Entry<K, V> {

		private K k;
		private V v;

		public Pair(K k, V v) {
			this.k = k;
			this.v = v;
		}

		@Override
		public K getKey() {
			return k;
		}

		@Override
		public V getValue() {
			return v;
		}

		@Override
		public V setValue(V v) {
			return this.v = v;
		}

		@Override
		public String toString() {
			return "{" + k + "," + v + "}";
		}

	}

	public static String minWindow(String s, String t) {

		if (s.length() == 0 || t.length() == 0)
			return "";

		char[] sc = s.toCharArray();
		// to store t's character and occurrence
		Map<Character, Integer> tp = new HashMap<>();
		for (int i = 0; i < t.length(); i++)
			tp.put(t.charAt(i), tp.getOrDefault(t.charAt(i), 0) + 1);

		int totalSize = tp.size();
		if (totalSize > sc.length)
			return "";

		// Create a Pair List and
		// Store the index along with the character
		// This reduce the traversal time
		List<Pair<Integer, Character>> tIndexVsChar = new ArrayList<>();
		for (int i = 0; i < sc.length; i++) {
			if (tp.containsKey(sc[i]))
				tIndexVsChar.add(new Pair<>(i, sc[i]));

		}
		int left = 0, right = 0;
		int minLen = sc.length + 1, start = 0, end = 0;
		int len = tIndexVsChar.size();
		// if formed == totalSize
		// Then we found the word
		int formed = 0;
		Map<Character, Integer> wordCount = new HashMap<>();
		while (right < len) {

			// getElement from tIndexVsChar
			Pair<Integer, Character> p = tIndexVsChar.get(right);
			// add this to wordCount
			char c = p.getValue();
			int count = wordCount.getOrDefault(c, 0) + 1;
			wordCount.put(c, count);

			// check if this character matches the count
			if (wordCount.get(c).intValue() == tp.get(c).intValue()) {
				formed++;
			}

			// in cases where we encountered some char more than the required
			// we increment left and remove those char
			// and then validate the same
			while (formed == totalSize && left <= right) {

				Pair<Integer, Character> leftP = tIndexVsChar.get(left);

				int size = p.getKey() - leftP.getKey() + 1;
				if (size < minLen) {
					minLen = size;
					start = leftP.getKey();
					end = p.getKey();

				}
				char lc = leftP.getValue();
				wordCount.put(lc, wordCount.get(lc) - 1);
				// if after removing character matches the count
				// then reloop and calculate
				// Otherwise decrease the formed count
				if (wordCount.get(lc).intValue() < tp.get(lc).intValue()) {
					formed--;
				}
				// continue moving left
				// To reduce the size
				left++;
			}

			right++;

		}

		return minLen == sc.length + 1 ? "" : s.substring(start, end + 1);
	}
}
