import java.util.ArrayList;
import java.util.List;

/**
 *	Evaluate Tree
 *
 *	@author	
 *	@since	
 */
public class EvaluateTree {
	
	private TreeNode<String> root;		// root of the expression tree
	
	private final int PRINT_SPACES = 3;	// print spaces between tree levels
	
	public EvaluateTree() {}
	
	/**	Calls a method that uses a recursive algorithm to evaluate the 
	 * 	expression tree and return a result.
	 *	@return		the value of the evaluation
	 */
	public double evaluateTree() {
		// if the root is a number, return the number as the answer
		//if(isOperand(root))
			//return Double.parseDouble(root.getValue());
		// else, recursively evaluate the expression tree
		return recursiveEvaluation(root);
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
	public void printTree(TreeNode<String> root) {
		printLevel(root, 0);
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
	
	/*********************************************************************/
	/*************************** For Testing *****************************/
	/*********************************************************************/
	
	public static void main(String[] args) {
		EvaluateTree et = new EvaluateTree();
		et.run();
	}
	
	public void run() {
		System.out.println("\nExpression Tree\n------------------------\n");
		// Expression 1
		System.out.println("Expression: ((2+3)*5/6)+7*8 \n");
		buildExpression1();
		printTree(root);
		System.out.println("\nAnswer: " + evaluateTree() + "\n");
		// Expression 2
		System.out.println("Expression: 1.2 * (( 3.4 - 2.1) / 1.3 * 8.) \n");
		buildExpression2();
		printTree(root);
		System.out.println("\nAnswer: " + evaluateTree() + "\n");
	}
		
	/**	Build the tree with this expression: ((2+3)*5/6)+7*8 */
	public void buildExpression1() {
		String[] arr = { "2", "3", "+", "5", "*", "6", "/", "7", "8", "*", "+" };
		List<TreeNode<String>> nodes = new ArrayList<TreeNode<String>>();
		for (int a = 0; a < arr.length; a++) nodes.add(new TreeNode<String>(arr[a]));
		nodes.get(2).setLeft(nodes.get(0)); nodes.get(2).setRight(nodes.get(1));
		nodes.get(4).setLeft(nodes.get(2)); nodes.get(4).setRight(nodes.get(3));
		nodes.get(6).setLeft(nodes.get(4)); nodes.get(6).setRight(nodes.get(5));
		nodes.get(10).setLeft(nodes.get(6)); nodes.get(10).setRight(nodes.get(9));
		nodes.get(9).setLeft(nodes.get(7)); nodes.get(9).setRight(nodes.get(8));
		root = nodes.get(10);
	}
		
	/**	Build the tree with this expression: 1.2 * (( 3.4 - 2.1) / 1.3 * 8.) */
	public void buildExpression2() {
		String[] arr = { "1.2", "*", "3.4", "-", "2.1", "/", "1.3", "*", "8." };
		List<TreeNode<String>> nodes = new ArrayList<TreeNode<String>>();
		for (int a = 0; a < arr.length; a++) nodes.add(new TreeNode<String>(arr[a]));
		nodes.get(1).setLeft(nodes.get(0)); nodes.get(1).setRight(nodes.get(7));
		nodes.get(7).setLeft(nodes.get(5)); nodes.get(7).setRight(nodes.get(8));
		nodes.get(5).setLeft(nodes.get(3)); nodes.get(5).setRight(nodes.get(6));
		nodes.get(3).setLeft(nodes.get(2)); nodes.get(3).setRight(nodes.get(4));
		root = nodes.get(0);
	}	
}