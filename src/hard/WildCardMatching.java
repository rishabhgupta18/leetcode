package hard;

public class WildCardMatching {

	public static void main(String[] args) {
		System.out.println(isMatch("aa", "a"));
		System.out.println(isMatch("aa", "*"));
		System.out.println(isMatch("cb", "?a"));
		System.out.println(isMatch("adceb", "*a*b"));
		System.out.println(isMatch("acdcb", "a*c?b"));
		System.out.println(isMatch("aab", "c*a*b"));
		System.out.println(isMatch("ho", "**ho"));

	}

	public static boolean isMatch(String s, String p) {

		if (s.length() == 0 && p.length() == 0)
			return true;
		if (p.length() == 0)
			return false;

		char[] sc = s.toCharArray();
		char[] pc = p.toCharArray();
		int slen = sc.length;
		int plen = pc.length;
		int i = 1;
		// remove extra *
		boolean isFirstStar = true;
		int pLimit = 0;
		for (i = 0; i < plen; i++) {

			if (pc[i] == '*') {
				if (isFirstStar) {
					pc[pLimit++] = pc[i];
					isFirstStar = false;
				}
			} else {
				pc[pLimit++] = pc[i];
				isFirstStar = true;
			}

		}
		// array to compute all cases
		boolean[][] st = new boolean[slen + 1][pLimit + 1];
		st[0][0] = true;

		if (pLimit > 0 && pc[0] == '*')
			st[0][1] = true;

		for (i = 1; i <= slen; i++) {
			for (int j = 1; j <= pLimit; j++) {
				if (pc[j - 1] == '*') {
					st[i][j] = st[i][j - 1] || st[i - 1][j];
				} else if (pc[j - 1] == '?' || pc[j - 1] == sc[i - 1]) {
					st[i][j] = st[i - 1][j - 1];
				}
			}
		}
		return st[slen][pLimit];
	}
}
