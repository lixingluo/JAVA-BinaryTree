package binarytree;

public class TreeNode implements Cloneable {
	char value;
	TreeNode left = null;
	TreeNode right = null;
	public TreeNode(char value) {
		this.value = value;
	}
	public TreeNode(TreeNode pNode) {
		this.value = pNode.value;
		this.left = pNode.left;
		this.right = pNode.right;
	}
	/**
	 * Don't use
	 */
	public Object clone() throws CloneNotSupportedException {
		TreeNode clone = null;
		try {
			clone = (TreeNode)super.clone();
		} catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return clone;
	}
}
