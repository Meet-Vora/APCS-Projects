import java.util.ArrayList;
import java.util.List;

/**
 *	ExpressionTree - Creates an expression tree from an expression given
 *				in infix notation.
 *
 *	@author	Meet Vora
 *	@since	May 17th, 2019
 */
public class ExpressionTree {
		
	private List<String> tokens;		// the tokens of the expression
	private int tokenIndex;				// index into tokens
	
	private String expr;				// expression
	
	private TreeNode<String> root;		// the root node of the expression tree
	
	private ExprUtils utils;			// utilities to tokenize expression
	
	private final int PRINT_SPACES = 3;	// number of spaces between tree levels
										// used by printTree()
	
	// constructor
	public ExpressionTree() {
		utils = new ExprUtils();
		tokenIndex = 0;
		expr = "";
		root = null;
	}
	
	public static void main(String[] args) {
		ExpressionTree et = new ExpressionTree();
		et.run();
	}
	
	/**
	 *	Prints introduction, exit message, and calls another method to
	 *	run the program interface.
	 */
	public void run() {
		System.out.println("\nWelcome to ExpressionTree!!!");
		treeMakerInterface();
		System.out.println("\nThanks for using ExpressionTree! Goodbye.\n");
	}
	
	/**
	 *	The user interface for the Expression Tree
	 */
	public void treeMakerInterface() {
		String input = "";
		boolean quit = false;

		// do while user doesn't enter quit
		do {
			// print the menu after every time the user enters something valid
			printMenu();
			// ask user for input. Use do-while to make sure input is valid
			do {
				input = Prompt.getString("");
				input = input.toLowerCase();
			}while(!input.equals("i") && !input.equals("pre") && !input.equals("in") 
				&& !input.equals("post") && !input.equals("e") && !input.equals("p")
				&& !input.equals("q"));
			
			// Run different functions for different user inputs
			switch(input) {
				// print tree in prefix order
				case "pre":  System.out.println("\nPrefix Order"); preorderRecursive(root);
								break;							
				// print tree in infix order
				case "in": 	 System.out.println("\nInfix Order"); inorderRecursive(root);
								break;
				// print tree in postfix order
				case "post": System.out.println("\nPostfix Order"); postorderRecursive(root);
								break;
				// evaluate the expression tree
				case "e": 	 System.out.println("\nAnswer: " + evaluateExpression());
								break;
				// print the expression tree
				case "p":    System.out.println("\nPrint Tree"); printTree();
								break;
				// quit the program
				case "q": 	 quit = true;
								break;
				// enter an expression
				case "i": 	 expr = Prompt.getString("Expression"); root = buildTree();
								break;
			}
		}while(!quit);
	}

	/**
	 * 	Recursively evaluates the expression tree.
	 * 	@param	 current	the TreeNode object to use to traverse through the tree
	 * 	@return 			the value of the evaluated expression tree
	 */
	public double recursiveEvaluation(TreeNode<String> current) {
		// if the left and right nodes of the current node are null, return the
		// value of the current node as a double
		if(current.getLeft() == null && current.getRight() == null)
			return Double.parseDouble(current.getValue());
		
		// save the returned number as a leftNum
		double leftNum = recursiveEvaluation(current.getLeft());
		// save the returned number as a rightNum
		double rightNum = recursiveEvaluation(current.getRight());
		// calculate the value of each expression, which will eventually
		// return the value of the entire tree
		return calculate(leftNum,rightNum,current.getValue());
	}
	
	/**	Builds a Binary Expression Tree from tokens.
	 *	@return		root of expression tree
	 *
	 *	Algorithm: 1. Make a stack
	 *			   2. Tokenize the expression in postfix order
	 *			   3. for every token in the tokens arraylist
	 *			   		3.1. if node is operator
	 *			   			3.1.1. Set first element on stack to right node of the
	 *									current operator 
	 *			   			3.1.2. Set second element on stack to left node of the
	 *									current operator 
	 *					3.2. if node is number
	 *						3.2.1. Add number to the stack
	 *			   4. return the last element left in the stack as the root of
	 *					the tree
	 */
	public TreeNode<String> buildTree() {
		// create an ArrayStack of TreeNodes
	 	ArrayStack<TreeNode<String>> treeStack = new ArrayStack<TreeNode<String>>();
	 	// store the tokenized expression in the tokens Arraylist
	 	tokens = utils.toPostfix(expr);

	 	for(tokenIndex = 0; tokenIndex < tokens.size(); tokenIndex++) {
	 		TreeNode<String> current = new TreeNode<String>(tokens.get(tokenIndex));
	 		// if current node is an operator, then set the right node to the first
	 		// element on the stack, and the left node to the second element on the
	 		// stack
	 		if(utils.isOperator(current.getValue())) {
	 			current.setRight(treeStack.pop());
	 			current.setLeft(treeStack.pop());
	 			treeStack.push(current);
	 		}
	 		// if a number, add it to the stack
	 		else
	 			treeStack.push(current);
	 	}
	 	tokenIndex = 0;
	 	// return remaining node as the root of the tree
	 	return treeStack.pop();
	}

	/**	Print help */
	public void printMenu() {
		System.out.println("\nCurrent expression: " + expr);
		System.out.println("\nChoose:");
		System.out.println("  (i) input new expression");
		System.out.println("  (pre) print prefix notation");
		System.out.println("  (in) print infix notation");
		System.out.println("  (post) print postfix notation");
		System.out.println("  (e) evaluate expression");
		System.out.println("  (p) print tree");
		System.out.println("  (q) quit");
	}
	
	/**
	 *	Evaluate the expression in the ExpressionTree
	 *	@return		the evaluated answer
	 */
	public double evaluateExpression() { return recursiveEvaluation(root); }
	
	/** 
	 *	Calculates the expression value. Reads the operator as a String, and
	 * 	performs operations on the two numbers accordingly.
	 * 	@param leftNum		the number on the left side
	 * 	@param rightNum 	the number on the right side
	 *  @param operator 	the operator used to evaluate the two numbers
	 * 	@return 			the value of the evaluated expression
	 */	
	public double calculate(double leftNum, double rightNum, String operator) {
		double answer = 0.0;
		// if operator is '+', add numbers
		if(operator.equals("+"))
			answer = leftNum + rightNum;
		// if operator is '-', subtract right number from left number
		if(operator.equals("-"))
			answer = leftNum - rightNum;
		// if operator is '*', multiply numbers
		if(operator.equals("*"))
			answer = leftNum * rightNum;
		// if operator is '/', divide left number by right number
		if(operator.equals("/"))
			answer = leftNum/rightNum;
		// if operator is '+', put the right number to the power of the left number
		if(operator.equals("^"))
			answer = Math.pow(leftNum,rightNum);
		// if operator is '%', mod numbers
		if(operator.equals("%"))
			answer = leftNum % rightNum;
		return answer;
	}
	
	/**
	 *	Print expression tree
	 *	@param root		root node of binary tree
	 *
	 *	Prints in vertical order, top of output is right-side of tree,
	 *			bottom is left side of tree,
	 *			left side of output is root, right side is deepest leaf
	 *	Example tree (expression "5 + 2 * 3""):
	 *			  +
	 *			/	\
	 *		  /		  \
	 *		5			*
	 *				  /	  \
	 *				2		3
	 *
	 *	would be output as:
	 *
	 *				3
	 *			*
	 *				2
	 *		+
	 *			5
	 */
	public void printTree() {
		printLevel(root, 0);
	}
	
	/**
	 *  Print Binary Tree Inorder
	 *  @param current		the TreeNode object to pass through the method 
	 *						recursively
	 */
	public void inorderRecursive(TreeNode<String> current) {
		// if left node isn't null, keep going left by calling the method recursively
		// with current as the left node
		if(current.getLeft() != null)
			inorderRecursive(current.getLeft());
		// print the node
		System.out.print(current.getValue() + " ");
		// if right node isn't null, keep going right by calling the method recursively
		// with current as the right node
		if (current.getRight() != null)
			inorderRecursive(current.getRight());
	}
	
	/**
	 *	Print Binary Tree Preorder
	 *  @param current		the TreeNode object to pass through the method 
	 *						recursively	
	 */
	public void preorderRecursive(TreeNode<String> current) {
		// print the node
		System.out.print(current.getValue() + " ");
		// if left node isn't null, keep going left by calling the method recursively
		// with current as the left node
		if(current.getLeft() != null)
			preorderRecursive(current.getLeft());
		// if right node isn't null, keep going right by calling the method recursively
		// with current as the right node
		if (current.getRight() != null)
			preorderRecursive(current.getRight());
	}
	
	/**
	 *	Print Binary Tree Postorder
	 *  @param current		the TreeNode object to pass through the method 
	 *						recursively
	 */
	public void postorderRecursive(TreeNode<String> current) {
		// if left node isn't null, keep going left by calling the method recursively
		// with current as the left node
		if(current.getLeft() != null)
			postorderRecursive(current.getLeft());
		// if right node isn't null, keep going right by calling the method recursively
		// with current as the right node	
		if (current.getRight() != null)
			postorderRecursive(current.getRight());
		// print the node
		System.out.print(current.getValue() + " ");
	}
	
	/**
	 *	Recursive node printing method
	 *	Prints reverse order: right subtree, node, left subtree
	 *	Prints the node spaced to the right by level number
	 *	@param node		root of subtree
	 *	@param level	level down from root (root level = 0)
	 */
	public void printLevel(TreeNode<String> node, int level) {
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