import java.util.ArrayList;

/**
 *	Binary Tree of Comparable values.
 *	The tree only has unique values. It does not add duplicate values.
 *	
 *	@author	Meet Vora
 *	@since	May 7th, 2019
 */
public class BinaryTree<E extends Comparable<E>> {

	private TreeNode<E> root;		// the root of the tree
	
	private final int PRINT_SPACES = 3;	// print spaces between tree levels
										// used by printTree()
										
	
	/**	constructor for BinaryTree */
	public BinaryTree() {
		root = null;
	}
	
	/**	Field accessors and modifiers */
	
	/**	Add a node to the tree using an iterative algorithm.
	 *	@param value		the value to put into the tree
	 */
	/*
	public void add(E value) {
		// if nothing in binary tree, then initialize root as value
		if(root == null)
			root = new TreeNode<E>(value);
		// else, add a treenode to the tree
		else {
			TreeNode<E> current = root;
			TreeNode<E> newNode = new TreeNode<E>(value);
			boolean added = false;
						
			while(!added) {
				// if value given is smaller than the value of current node,
				// go left
				if(value.compareTo(current.getValue()) < 0) {
					// if the node to the left is null, set current as the new node
					if(current.getLeft() == null) {
						current.setLeft(newNode);
						added = true;
					}
					// else, make current equal to the node on the left
					else
						current = current.getLeft();
				}
				// if value is greater than current node's value, go right
				else {
					// if the node to the right is null, set current as the new node 
					if(current.getRight() == null) {
						current.setRight(newNode);
						added = true;
					}
					// else, make current equal to the node on the right
					else
						current = current.getRight();
				}
			}
		}
	}
	*/
	
	/**	Add a node to the tree. Adds the node by calling a method that uses a 
	 * 	recursive algorithm.
	 *	@param value		the value to put into the tree
	 */
	public void add(E value) {
		// call method that adds objects to tree recursively 
		addRecursive(value,null);

		// TreeNode<E> current = new TreeNode<E>(value);
		// if(root == null)
		// 	root = current;
		// // else, add current to tree using recursive algorithm
		// else
		// 	addRecursive(value, current);
	}
	
	/** Recursively adds nodes to a binary tree.
	 *	@param value		the value to put into the tree
	 *	@param current		the TreeNode object to pass through the method 
	 *						recursively
	 */
	public void addRecursive(E value, TreeNode<E> current) {
		// if root is null, set it to a node with the value
		if(root == null)
			root = new TreeNode<E>(value);
		// if root isn't null, add new node to tree
		else {
			// if current is null, set current to root and call method recursively
			// with new current value
			if(current == null) {
				current = root;
				addRecursive(value, current);
			} 
			// if value is smaller than current node's value, go left
			else if(value.compareTo(current.getValue()) < 0) {
				// if the node to the left is null, set current as the new node 
				if(current.getLeft() == null)
					current.setLeft(new TreeNode<E>(value));
				// else, call method recursively with current now as the left node
				else
					addRecursive(value, current.getLeft());
			}
			// if value is greater than current node's value, go right
			else {
				// if the node to the right is null, set current as the new node 
				if(current.getRight() == null)
					current.setRight(new TreeNode<E>(value));
				// else, call method recursively with current now as the right node
				else
					addRecursive(value, current.getRight());
			}
		}
	}
	
	/**
	 *	Calls another method to print binary tree inorder.
	 */
	public void printInorder() {
		inorderRecursive(root);
	}
	
	/**
	 *  Print Binary Tree Inorder
	 *  @param current		the TreeNode object to pass through the method 
	 *						recursively
	 */
	public void inorderRecursive(TreeNode<E> current) {
		if(current.getLeft() != null)
			inorderRecursive(current.getLeft());
		System.out.print(current.getValue() + " ");
		if (current.getRight() != null)
			inorderRecursive(current.getRight());
	}
	
	/**
	 *	Calls another method to print binary tree preorder.
	 */
	public void printPreorder() {
		preorderRecursive(root);
	}
	
	/**
	 *	Print Binary Tree Preorder
	 *  @param current		the TreeNode object to pass through the method 
	 *						recursively	
	 */
	public void preorderRecursive(TreeNode<E> current) {
		System.out.print(current.getValue() + " ");
		if(current.getLeft() != null)
			preorderRecursive(current.getLeft());
		if (current.getRight() != null)
			preorderRecursive(current.getRight());
	}
	
	/**
	 *	Calls another method to print binary tree postorder.
	 */
	public void printPostorder() {
		postorderRecursive(root);
	}
	
	/**
	 *	Print Binary Tree Postorder
	 *  @param current		the TreeNode object to pass through the method 
	 *						recursively
	 */
	public void postorderRecursive(TreeNode<E> current) {
		if(current.getLeft() != null)
			postorderRecursive(current.getLeft());
		if (current.getRight() != null)
			postorderRecursive(current.getRight());
		System.out.print(current.getValue() + " ");
	}
		
	/**	Return a balanced version of this binary tree
	 *	@return		the balanced tree
	 */
	public BinaryTree<E> makeBalancedTree() {
		BinaryTree<E> balancedTree = new BinaryTree<E>();
		ArrayList<E> inorder = new ArrayList<E>();
		balancedInorder(root, inorder);
		for(E value: inorder)
			System.out.print(value + " ");
		System.out.println();
		balanceTree(inorder,0,inorder.size()-1, balancedTree);
		return balancedTree;
	}
	
	/**
	 *	Sorts the objects inorder and adds them into an ArrayList
	 *  @param current		the TreeNode object to pass through the method 
	 *						recursively
	 *	@param inorder 		ArrayList that contains objects inorder 	
	 */
	public void balancedInorder(TreeNode<E> current, ArrayList<E> inorder) {
		if(current.getLeft() != null)
			balancedInorder(current.getLeft(),inorder);
		inorder.add(current.getValue());
		if (current.getRight() != null)
			balancedInorder(current.getRight(),inorder);
	}
	
	/**
	 *	Uses a recursive algorithm to balance the tree
	 *	@param inorder 		ArrayList that contains objects inorder
	 *	@param min 			minimum index to use to get elements from ArrayList
	 *	@param max 			maximum index to use to get elements from ArrayList
	 *	@param tree 		BinaryTree in which to add elements
	 */
	public void balanceTree(ArrayList<E> inorder, int min, int max, BinaryTree<E> tree) {
		// if min and max are not the same index, balance the tree
		if(min != max) {
			// find the median
			int median = (min + max)/2;
			// add the median to the tree
			tree.add(inorder.get(median));
			// recursively call the method to split up the arraylist into halves
			// each time and then add to the tree each time
			balanceTree(inorder, min, median, tree);
			balanceTree(inorder, median + 1, max, tree);
		}	
	}
	

	/*******************************************************************************/	
	/********************************* Utilities ***********************************/	
	/*******************************************************************************/	
	/**
	 *	Print binary tree
	 *	@param root		root node of binary tree
	 *
	 *	Prints in vertical order, top of output is right-side of tree,
	 *			bottom is left side of tree,
	 *			left side of output is root, right side is deepest leaf
	 *	Example Integer tree:
	 *			  11
	 *			/	 \
	 *		  /		   \
	 *		5			20
	 *				  /	  \
	 *				14	   32
	 *
	 *	would be output as:
	 *
	 *				 32
	 *			20
	 *				 14
	 *		11
	 *			5
	 ***********************************************************************/
	public void printTree() {
		printLevel(root, 0);
	}
	
	/**
	 *	Recursive node printing method
	 *	Prints reverse order: right subtree, node, left subtree
	 *	Prints the node spaced to the right by level number
	 *	@param node		root of subtree
	 *	@param level	level down from root (root level = 0)
	 */
	private void printLevel(TreeNode<E> node, int level) {
		if (node == null) return;
		// print right subtree
		printLevel(node.getRight(), level + 1);
		// print node: print spaces for level, then print value in node
		for (int a = 0; a < PRINT_SPACES * level; a++) System.out.print(" ");
		System.out.println(node.getValue());
		// print left subtree
		printLevel(node.getLeft(), level + 1);
	}
	
	
}