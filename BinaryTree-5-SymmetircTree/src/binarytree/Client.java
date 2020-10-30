/**
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 */
package binarytree;

public class Client {
	public static class TreeNode {
		char value;
		TreeNode left = null;
		TreeNode right = null;
		public TreeNode(char value) {
			this.value = value;
		}
	}
	
	public static void main(String[] args) {
		String preOrderInputStr = "ABC##D##BD##C##";
		preOrderInputStr = "ABD#GI##J###CE#HK###F##";
		preOrderInputStr = null;
		preOrderInputStr = "AAA##A##AA##A##";
		TreeNode root = createPreOrderTree(preOrderInputStr);
		printPreOrderTree(root);
		System.out.println();
		printPreOrderTreeReverse(root);
		System.out.println();
		System.out.println("PreOrderTree is symmetric ? " + isSymmetricTree(root));
	}
	
	public static int index = 0;
	public static TreeNode createPreOrderTree(String preOrderStr) {
		if(null == preOrderStr || preOrderStr.length() <= 0 || index >= preOrderStr.length()) {
			return null;
		}
		if(preOrderStr.charAt(index) == '#') {
			index++;
			return null;
		}		
		TreeNode root = new TreeNode(preOrderStr.charAt(index));
		index++;
		root.left = createPreOrderTree(preOrderStr);
		root.right = createPreOrderTree(preOrderStr);
		return root;
	}
	
	public static void printPreOrderTree(TreeNode root) {
		if(null != root) {
			System.out.print(root.value);
			printPreOrderTree(root.left);
			printPreOrderTree(root.right);
		}
	}
	
	public static void printPreOrderTreeReverse(TreeNode root) {
		if(null != root) {
			System.out.print(root.value);
			printPreOrderTreeReverse(root.right);
			printPreOrderTreeReverse(root.left);
		}
	}
	
	/**
	 * 思路：
	 * 1）新写一个printPreOrderTree, 从右到左打印
	 * 2) 利用递归，左节点和右节点的值判断是否相同，递归判断左节点的左节点的值和右节点的右节点的值
	 * 		以及左节点的右节点的值和右节点的左节点的值
	 * 3) 方法一有风险，如果是所有节点相同的二叉树，无法判断节点结构
	 */
	
	public static Boolean isSymmetricTree(TreeNode root) {
		if(null == root) {
			return true;
		}
//		if(null == root.left && null != root.right) {
//			return false;
//		}
//		if(null == root.right && null != root.left) {
//			return false;
//		}
//		if(root.left.value == root.right.value) {
//			return match(root.left, root.right);
//		}
//		return false;
		return match(root.left, root.right);
	}
	
	public static Boolean match(TreeNode left, TreeNode right) {
		if(null == left && null == right) {
			return true;
		}
		if(null == left && null != right) {
			return false;
		}
		if(null != left && null == right) {
			return false;
		}
		if(left.value == right.value) {
			return match(left.left, right.right) && match(left.right, right.left);
		}
		return false;
	}
}
