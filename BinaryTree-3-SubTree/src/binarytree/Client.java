/**
 * 输入两棵二叉树 A 和 B，判断 B 是不是 A 的子结构
 */
package binarytree;

public class Client {
	public static void main(String[] args) {
//		String preOrderStrInputA = "ABD#GI##J###CE#HK###F##";
		String preOrderStrInputA = "ABBC##D##E##B##";
		String preOrderStrInputB = "ABD#GI##J###";
		preOrderStrInputB = "ABD#GI##J###CE#HK###F##";
		preOrderStrInputB = "BD#GI##J###";
		preOrderStrInputB = "CE#HK###F##";
		preOrderStrInputB = "GI##J##";
		preOrderStrInputB = "BC###";
		TreeNode A = createPreOrderTree(preOrderStrInputA);
		printPreOrderTree(A);
		System.out.println("");
		index = 0;
		TreeNode B = createPreOrderTree(preOrderStrInputB);
		printPreOrderTree(B);
		System.out.println("");
		System.out.print("Whether B is the A subtree? " + subTree(A,B));
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
	
	/**
	 * 判断两棵树的根节点否相同
	 * 	如果相同
	 * 		则递归判断树剩余的结点是否相同
	 * 
	 * 	如果不相同
	 * 		则递归树的左右子节点进行对比找到相同的根节点
	 * 
	 * 切记：
	 * 1) 对于输入的二叉树 A 和 B，A 和 B 都是树的根结点
	 * 2) 如果 B 为null，则 B 是所有树的字结构，这点比较同时适用于 B 的子节点与 A 的子节点之间的比较
	 * 3）二叉树的结点互不相同？？？可以相同！有subTree(A.left, B) || subTree(A.right, B)，可以判断所有
	 */
	
	public static Boolean subTree(TreeNode A, TreeNode B) {
		/**
		 * 这里却又必须先 A 后 B
		 */
		if(null == A) {
			return false;
		}
		if(null == B) {
			return true;
		}
		if(A.value == B.value) {
			return match(A, B);
		}
		return subTree(A.left, B) || subTree(A.right, B);
	}
	 
	public static Boolean match(TreeNode A, TreeNode B) {
		/**
		 * 必须先 B 后 A，具体思考 A 和 B 的子节点都为null时情况
		 */
		if(null == B) {
			return true;
		}
		if(null == A) {
			return false;
		}
		System.out.println("2 - " + A.value);
		if(A.value == B.value) {
			return match(A.left, B.left) && match(A.right, B.right);
		}
		return false;
	}
}
