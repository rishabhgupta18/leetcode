package hard;

public class BinaryTreeMaximumPathSum {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(-10);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);
		System.out.println(maxPathSum(root));

		root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		System.out.println(maxPathSum(root));
		
		root = new TreeNode(-3);
		System.out.println(maxPathSum(root));

	}

	public static int maxPathSum(TreeNode root) {

		Result r = new Result();
		r.val = Integer.MIN_VALUE;
		maxPathSum(root, r);
		return r.val;
	}

	private static int maxPathSum(TreeNode root, Result res) {

		if (root == null)
			return 0;

		// traverse left and right
		// get max
		int left = maxPathSum(root.left, res);
		int right = maxPathSum(root.right, res);

		// Maximum can be -
		// Only Root or Root + Either(Left or Right)
		// This is to satisfy at most one child
		int maxRes = Math.max(root.val, Math.max(left, right) + root.val);

		// Maximum can be -
		// All 3 - root + left + right
		int maxAll = Math.max(maxRes, root.val + left + right);

		// Check if maximumAmongRootLeftRight is greater than existed result
		res.val = Math.max(maxAll, res.val);
		return maxRes;

	}

	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	static class Result {
		int val;
	}

}
