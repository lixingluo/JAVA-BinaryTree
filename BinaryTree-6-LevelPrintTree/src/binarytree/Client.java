/**
 * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。（按层遍历二叉树）
 */
package binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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
		String preOrderInputStr = "ABD#GI##J###CE#HK###F##";
//		preOrderInputStr = "ABD#GI##J###";
		TreeNode root = createPreOrderTree(preOrderInputStr);
		ArrayList<Character> result = printLevelPrintTree(root);
		System.out.println(result);
		ArrayList<ArrayList<Character>> result2 = printLevelPrintTree2(root);
		System.out.println(result2);
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
	
	public static ArrayList<Character> printLevelPrintTree(TreeNode pNode) {
		ArrayList<Character> result = new ArrayList<Character>();
		if(null == pNode) {
			return result;
		}
		Queue<TreeNode> pQueue = new LinkedList<>();
		pQueue.add(pNode);
		while(!pQueue.isEmpty()) {
			TreeNode head = pQueue.remove();
			if(null != head) {
				result.add(head.value);
				if(null != head.left) {
					pQueue.add(head.left);
				}
				if(null != head.right) {
					pQueue.add(head.right);
				}
			}
		}
		return result;
	}
	
	public static ArrayList<ArrayList<Character>> printLevelPrintTree2(TreeNode pNode) {
		ArrayList<ArrayList<Character>> result = new ArrayList<ArrayList<Character>>();
		if(null == pNode) {
			return null;
		}
		Queue<TreeNode> pQueue = new LinkedList<>();
		pQueue.add(pNode);
		while(!pQueue.isEmpty()) {
			ArrayList<Character> temp = new ArrayList<Character>();
			int levelLength = pQueue.size();
			for(int i = 0;i < levelLength;i++) {
				TreeNode head = pQueue.remove();
				if(null != head) {
					temp.add(head.value);
					if(null != head.left) {
						pQueue.add(head.left);
					}
					if(null != head.right) {
						pQueue.add(head.right);
					}
				}
			}
			result.add(temp);
		}
		return result;
	}
}
