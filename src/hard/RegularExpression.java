package hard;

public class RegularExpression {

	public static void main(String[] args) {
		System.out.println(isMatch("mississippi", "mis*is*p*."));
	}

	public static boolean isMatch(String s, String p) {

		if (p.length() == 0)
			return false;

		int pLen = p.length();
		int sLen = s.length();
		char[] pc = p.toCharArray();
		char[] sc = s.toCharArray();
		boolean[][] st = new boolean[sLen + 1][pLen + 1];
		st[0][0] = true;
		// precheck the top
		for (int i = 1; i <= pLen; i++) {
			if (pc[i - 1] == '*')
				st[0][i] = st[0][i - 2];
		}
		// fill the table
		for (int i = 1; i <= sLen; i++) {

			for (int j = 1; j <= pLen; j++) {

				if (pc[j - 1] == '*') {
					st[i][j] = st[i][j - 2];
					if (pc[j - 2] == '.' || pc[j - 2] == sc[i - 1]) {
						st[i][j] = st[i][j] | st[i - 1][j];
					}
				} else if (pc[j - 1] == '.' || sc[i - 1] == pc[j - 1]) {
					st[i][j] = st[i - 1][j - 1];
				} else {
					st[i][j] = false;
				}

			}

		}
		print(sc, pc, st);
		return st[sLen][pLen];
	}

	public static void print(char[] sc, char[] pc, boolean[][] st) {
		System.out.print("\t\t");
		for (char cc : pc) {
			System.out.print(cc + " \t");
		}
		System.out.println("\n");

		int index = -1;
		for (boolean[] ss : st) {
			if (index < 0) {
				index++;
				System.out.print(" \t");
			} else {
				System.out.print(sc[index++] + "\t");
			}

			for (boolean _s : ss) {
				System.out.print(_s + "\t");
			}
			System.out.println("\n");
		}
	}
}
