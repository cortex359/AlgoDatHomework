import com.sun.source.tree.Tree;

import java.lang.reflect.Array;
import java.util.ArrayList;

/** Ein binaerer Suchbaum mit ganzen Zahlen als Datensatz:
  * Vorlage fuer die A1 von algo-pr05 und fuer die A1 von algo-h06.
  * Als Operationen stehen `contains' und `insert' zur Verfuegung
  */
public class BinarySearchTree {

	/** Die Knotenklasse als statische innere Klasse. */
	public static class TreeNode {
		private int value;
		private TreeNode left;
		private TreeNode right;

		public TreeNode(int value) {
			this.value = value;
		}

		public String toString() {
			return this.value + " ";
		}

		public int getValue() {
		  return this.value;
		}

		public TreeNode getLeft() {
		  return this.left;
		}

		public TreeNode getRight() {
		  return this.right;
		}

		public void setValue(int value) {
		  this.value = value;
		}

		public void setLeft(TreeNode node) {
		  this.left = node;
		}

		public void setRight(TreeNode node) {
		  this.right= node;
		}
	}

	/** Baumwurzel */
	protected TreeNode root;

	
	public BinarySearchTree(int count, int min, int max){
		if(count <= 0)
			return;
		this.root = new TreeNode((int) (Math.random()*(max-min+1)+min) );
		root.left = new BinarySearchTree(count/2, min, root.value - 1).root;
		root.right = new BinarySearchTree(count/2-1, root.value + 1, max).root;
	}
	/**
	 * Herausfinden, ob ein gewisser Datensatz schon im binaeren Suchbaum enthalten ist.
	 *
	 * @param   data  zu suchender Datensatz
	 * @return        true: Datensatz ist vorhanden; false: Datensatz ist nicht vorhanden.
	 */
	public boolean contains(int data) {
		TreeNode temp = root;
		while(temp != null) {
			if (temp.getValue() == data) {
				return false;
			}
			if (temp.getValue() > data) {
				temp = temp.getLeft();
			} else {
				temp = temp.getRight();
			}
		}
		return false;
	}

	/**
	 * Einen neuen Datensatz in den binaeren Suchbaum einfuegen.
	 *
	 * @param   data  einzufuegender Datensatz
	 * @return        true: Datensatz wurde eingefuegt; false: Datensatz war schon vorhanden.
	 */
	public boolean insert(int data) {
		if (root == null) {
			root = new TreeNode(data);
			return true;
		}
		
		TreeNode temp = root;
		while(temp.getValue() != data) {
			if (temp.getValue() > data) {
				if(temp.getLeft() == null) {
					temp.setLeft(new TreeNode(data));
					return true;
				}
				temp = temp.getLeft();
			} else {
				if (temp.getRight() == null) {
					temp.setRight(new TreeNode(data));
					return true;
				}
				temp = temp.getRight();
			}
		}
		return false;
	}

	public void printPreOrder(){
		ArrayList<Integer> elems = getElementsPreorder(root);
		System.out.println(elems);
		System.out.println(elems.size());
	}
	
	private ArrayList<Integer> getElementsPreorder(TreeNode node){
		ArrayList<Integer> elems = new ArrayList<>();
		if(node == null){
			return elems;
		}
		elems.add(node.value);
		
		elems.addAll(getElementsPreorder(node.left));
		elems.addAll(getElementsPreorder(node.right));
		return elems;
	}
	
	public ArrayList<Integer> getLeaves(){
		return getLeaves(root);
	}
	
	private ArrayList<Integer> getLeaves(TreeNode node){
		ArrayList<Integer> elems = new ArrayList<>();
		if(node == null){
			return elems;
		}
		if(node.left == null && node.right == null) {
			elems.add(node.value);
		}
		elems.addAll(getElementsPreorder(node.left));
		elems.addAll(getElementsPreorder(node.right));
		return elems;
	}
	
	public int getMaxPathSum(){
		return getMaxPathSum(root);
	}
	
	private int getMaxPathSum(TreeNode node){
		if(node == null){
			return 0;
		}
		return node.value + Math.max(getMaxPathSum(node.left), getMaxPathSum(node.right));
	}
	
	public ArrayList<Integer> getElementsInLevel(int level){
		return getElementsInLevel(root, level);
	}
	
	private ArrayList<Integer> getElementsInLevel(TreeNode node, int remainingDepth){
		ArrayList<Integer> elems = new ArrayList<>();
		if(node == null)
			return elems;
		if(remainingDepth == 0) {
			elems.add(node.value);
			return elems;
		}
		elems.addAll(getElementsInLevel(node.left, remainingDepth-1));
		elems.addAll(getElementsInLevel(node.right, remainingDepth-1));
		return elems;
	}
	
	public boolean isComplete(){
		return isComplete(root);
	}
	
	private boolean isComplete(TreeNode node){
		if(node == null){
			return true;
		}
		if(node.left == null ^ node.right == null)
			return false;
		
		return isComplete(node.left) && isComplete(node.right);
	}
	
	public boolean isAVL(){
		return isAVL(root);
	}
	
	private boolean isAVL(TreeNode node){
		return true;
	}
}

