/**
 * 给定一个二叉树的节点，如何找出中序遍历的下一节点。有两个指向左右子树的指针，还有一个指向父节点的指针。
 */
package binarytree;

public class Client {
	public static void main(String[] args) {
//		String preOrderStrInput = "ABC##D##E#G##";
		String preOrderStrInput = "ABD#GI##J###CE#HK###F##";
//		String preOrderStrInput = "ABD#GI##J###";
		TreeNode root = createPreOrderTree(preOrderStrInput);
		printPreOrderTree(root);
		System.out.println();
		printInOrderTree(root);
		System.out.println();
		TreeNode targetNode = findNode(root, 'J');
		TreeNode nextNode = findNextInOrderNode(targetNode);
		if(null != nextNode) {
			System.out.println(nextNode.value);
			System.out.println(targetNode.value);
		} 
		else {
			System.out.println(nextNode);
			System.out.println(targetNode);
		}
	}
	
	private static int index = 0;
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
		if(null != root.left) {
			root.left.next = root;
		}
		root.right = createPreOrderTree(preOrderStr);
		if(null != root.right) {
			root.right.next = root;
		}
		return root;
	}
	
	public static void printPreOrderTree(TreeNode root) {
		if(null != root) {
			System.out.print(root.value);
			printPreOrderTree(root.left);
			printPreOrderTree(root.right);
		}
	}
	
	public static void printInOrderTree(TreeNode root) {
		if(null != root) {
			printInOrderTree(root.left);
			System.out.print(root.value);
			printInOrderTree(root.right);
		}
	}
	
	public static TreeNode findNode(TreeNode root, char value) {
		if(null == root || value == '\0') {
			return null;
		}
		TreeNode result = null;
		if(root.value == value) {
			result = root;
		} 
		if(null == result) {
			result = findNode(root.left, value);
		}
		if(null == result) {
			result = findNode(root.right, value);
		}
		return result;
	}
	
	/**
	 * 有子右节点
	 * 	右子节点有无子左节点
	 * 		无 —— 右子节点就是当前结点下一节
	 * 		有 —— 递归寻找右子节点的左子节点就是下一节点
	 * 
	 * 无子右节点
	 * 	无父节点 —— 无下一结点
	 * 	有父节点
	 * 		当前结点作为父节点的子左节点 —— 下一结点为父节点
	 * 		当前结点作为父节点的子右节点 —— 向父节点递归寻找能作为左子节点的父结点就是下一节点
	 * @return
	 */
	public static TreeNode findNextInOrderNode(TreeNode pNode) {
		if(null == pNode) {
			return null;
		}
		if(null != pNode.right) {
			pNode = pNode.right;
			while(null != pNode.left) {
				pNode = pNode.left;
			}
			return pNode;
		} 
		else {
//			if(null == pNode.next) {
//				return null;
//			}
//			else {
//				if(pNode == pNode.next.left) {
//					pNode = pNode.next;
//				}
//				else {
//					while(null != pNode.next && pNode != pNode.next.left) {
//						pNode = pNode.next;
//					}
//					pNode = pNode.next;
//				}
//				return pNode;
//			}
			while(null != pNode.next) {
				if(pNode == pNode.next.left) {
					return pNode.next;
				}
				pNode = pNode.next;
			}
			return null;
		}
	}
}
