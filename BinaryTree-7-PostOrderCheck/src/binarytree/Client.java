/**
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后续遍历。如果是返回 true，如果不是返回 false。假设输入的任意两个数字互不相同。
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
		String postOrderStr = "IJGDBKHEFCA";
		postOrderStr = "BDCAEGHIKJF";
		System.out.println(isPostOrderTree(postOrderStr));
		
	}
	
	/**
	 * 重点：
	 * 1）序列的最后一个数为根节点
	 * 2）根据二叉树的特点，左子节点小于根节点，右子节点大于根节点
	 * 3) 一定要记得，后序遍历中，右子节点树是不包括尾节点，所以last index是substring(xxx, strLen - 1)
	 * @param postOrderStr
	 * @return
	 */
	public static Boolean isPostOrderTree(String postOrderStr) {
		if(null == postOrderStr || postOrderStr.length() <= 0) {
			return false;
		}
		int strLen = postOrderStr.length();
		char rootVal = postOrderStr.charAt(postOrderStr.length() - 1);
		int leftMaxIndex = 0;
		/**
		 * 不能拿根节点的值来做判断
		 */
		for(;leftMaxIndex < strLen - 1;leftMaxIndex++) {
			if(postOrderStr.charAt(leftMaxIndex) > rootVal) {
				break;
			}
		}
		/**
		 * leftMaxIndex - 1才是真正的leftMaxIndex
		 */
		int rightMinIndex = leftMaxIndex;
		for(;rightMinIndex < strLen - 1;rightMinIndex++) {
			if(postOrderStr.charAt(rightMinIndex) < rootVal) {
				return false;
			}
		}
		
		Boolean left = true;
		if(leftMaxIndex > 0) {
			left = isPostOrderTree(postOrderStr.substring(0, leftMaxIndex));
		} 
		
		System.out.println(postOrderStr + "," + leftMaxIndex);
		Boolean right = true;
		/**
		 * strLen - 1才是postOrderStr最后的index; substring(..., strLen - 1)才能逐渐缩小string
		 */
		if(leftMaxIndex < strLen - 1) {
			right = isPostOrderTree(postOrderStr.substring(leftMaxIndex, strLen - 1));
		}

		return left && right;
	}
}
