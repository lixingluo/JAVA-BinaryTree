/**
 * 已知前序遍历为{1,2,4,7,3,5,6,8}，中序遍历为{4,7,2,1,5,3,8,6}，它的二叉树是怎么样的？
 */
package binarytree;

import java.util.Arrays;

public class Client {
	public static void main(String[] args) {
		int[] preOrderArrayInput = {1,2,4,7,3,5,6,8};
		int[] inOrderArrayInput = {4,7,2,1,5,3,8,6};
		String preOrderStrInput = Arrays.toString(preOrderArrayInput).replaceAll("(?:\\[|null|\\]| +)|,", "");
		String inOrderStrInput = Arrays.toString(inOrderArrayInput).replaceAll("(?:\\[|null|\\]| +)|,", "");
		System.out.println("PreOrderStrInput: " + preOrderStrInput);
		System.out.println("InOrderStrInput: " + inOrderStrInput);
		
		TreeNode root = reconstructBinaryTree(preOrderStrInput, inOrderStrInput);
		System.out.println("Tree Depeth is:" + getTreeDepth(root));
		printPreOrderTree(root);
		System.out.println("----");
		printInOrderTree(root);
	}
	
	public static TreeNode reconstructBinaryTree(String preOrderStr, String inOrderStr) {
		if(null == preOrderStr || preOrderStr.length() <= 0 || null == inOrderStr || inOrderStr.length() <= 0) {
			return null;
		}
		
		for(int i = 0;i < inOrderStr.length();i++) {
			if(inOrderStr.charAt(i) == preOrderStr.charAt(0)) {
				TreeNode root = new TreeNode(preOrderStr.charAt(0));
				root.left = reconstructBinaryTree(preOrderStr.substring(1, i+1), inOrderStr.substring(0, i));
				root.right = reconstructBinaryTree(preOrderStr.substring(i+1), inOrderStr.substring(i+1));
				return root;
			}
		}
		return null;
	}
	
	public static int getTreeDepth(TreeNode root) {
		return null == root ? 0 : 1 + Math.max(getTreeDepth(root.left), getTreeDepth(root.right));
	}
	
	public static void printPreOrderTree(TreeNode root) {
		if(null != root) {
			System.out.print(root.value);
			printPreOrderTree(root.left);
			printPreOrderTree(root.right);
		}
//		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " is null");
	}
	
	public static void printInOrderTree(TreeNode root) {
		if(null != root) {
			printInOrderTree(root.left);
			System.out.print(root.value);
			printInOrderTree(root.right);
		}
//		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + " is null");
	}
}
