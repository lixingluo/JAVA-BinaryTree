/**
 * 请完成一个函数，如果一个二叉树，该函数输出它的镜像。
 */
package binarytree;

public class Client {
	public static void main(String[] args) throws CloneNotSupportedException {
		String preOrderStrInput = "ABD#GI##J###CE#HK###F##";
		TreeNode root = createPreOrderTree(preOrderStrInput);
//		TreeNode mirTree = (TreeNode)root.clone();
		TreeNode mirTree = new TreeNode(root);
		printPreOrderTree(root);
		System.out.println(" - 1");
		mirrorTree(mirTree);
		printPreOrderTree(mirTree);
		System.out.println(" - 2");
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
	
	public static TreeNode mirrorTree(TreeNode root) {
		if(null == root) {
			return null;
		}
		
		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;
		
		mirrorTree(root.left);
		mirrorTree(root.right);
		
		return root;
	}
	
	public static void printPreOrderTree(TreeNode pNode) {
		if(null != pNode) {
			System.out.print(pNode.value);
			printPreOrderTree(pNode.left);
			printPreOrderTree(pNode.right);
		}
	}
		
	public static TreeNode destoryTree(TreeNode root) {
		if(null == root) {
			return null;
		}
		destoryTree(root.left);
		destoryTree(root.right);
		root = null;
		return root;
	}
}
