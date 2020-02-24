import java.util.List;		// used by expression evaluator
import java.util.ArrayList;

/**
 *	<Description goes here>
 *
 *	@author	
 *	@since	
 */
public class OldSimpleCalc {
	
	private ExprUtils utils;	// expression utilities
	
	private ArrayStack<Double> valueStack;		// value stack
	private ArrayStack<String> operatorStack;	// operator stack
	private List<String> tokens;	// operator stack

	// constructor	
	public OldSimpleCalc() {
		utils = new ExprUtils();
		valueStack = new ArrayStack<Double>();
		operatorStack = new ArrayStack<String>();
		tokens = new ArrayList<String>();
	}
	
	public static void main(String[] args) {
		OldSimpleCalc oldsc = new OldSimpleCalc();
		oldsc.run();
	}
	
	public void run() {
		System.out.println("\nWelcome to SimpleCalc!!!");
		runCalc();
		System.out.println("\nThanks for using SimpleCalc! Goodbye.\n");
	}
	
	/**
	 *	Prompt the user for expressions, run the expression evaluator,
	 *	and display the answer.
	 */
	public void runCalc() {
		boolean quit = false; 
		double answer = 0.0;
		String input = "";

		// System.out.println("Welcome to SimpleCalc!!!");
		
		// puts entire method in while loop and have it keep going until quit entered
		do { 

			// keep prompting user for expression until valid expression entered
			do{	
				input = Prompt.getString("Enter an expression to evaluate");
			} while(input.length() == 0);
			
			// if input is "h", print help menu
			if(input.equals("h"))
				printHelp();
			// if input is "q", quit the program
			if(input.equals("q"))
				quit = true;
			
			// Tokenize expression
			tokens = utils.tokenizeExpression(input);
			
			// run expression evaluator
			answer = evaluateExpression(tokens);
			
			// Print answer
			System.out.println(answer);

		} while(!quit);
	}
	
	/**	
	 *	Print help menu
	 */
	public void printHelp() {
		System.out.println("Help:");
		System.out.println("  h - this message\n  q - quit\n");
		System.out.println("Expressions can contain:");
		System.out.println("  integers or decimal numbers");
		System.out.println("  arithmetic operators +, -, *, /, %, ^");
		System.out.println("  parentheses '(' and ')'");
	}
	
	/**
	 *	Evaluate expression and return the value
	 *	@param tokens	a List of String tokens making up an arithmetic expression
	 *	@return			a double value of the evaluated expression
	 */
	public double evaluateExpression(List<String> tokens) {
		// tokens = new ArrayList<String>();
		
		// if parenthesis, then add everything in it to the stack, then say until operator is equal to left parenthesis, do stuff in the while loop after the for loop
		// to check if safe to do operation, check if at parenthesis currently, if peek() is an operator, and if it is empty, and if it hasPrecedence (Did last 2)
		double calc = 1.0;
		double first = 0.0;
		double second = 0.0;
		// double value = 0.0;
		// double num = 0.0;
		// double operand = 0.0;
		// String operator = "";

		for(int a = 0; a < tokens.size(); a++) {
			String token = tokens.get(a);
			// if a number, store in valueStack
			if(!utils.isOperator(token.charAt(0))) {
				// System.out.println("HELLOOOOOO");
				valueStack.push(Double.parseDouble(token));
				System.out.println(valueStack.peek());
			}
				
			// if a token,
			if(utils.isOperator(token.charAt(0))) {

				// and if operatorStack is empty or token has precedence, add to stack
				if(operatorStack.isEmpty() || hasPrecedence(operatorStack.peek(),token))
					operatorStack.push(token);

				if(token.equals("(")) {
					while(!token.equals(")")) {
						valueStack.push(calculate(valueStack.pop(),valueStack.pop(),operatorStack.pop().charAt(0)));
					}
				}

				// if(!token.equals("(") && operator.peek() hasPrecedence(token,operatorStack.peek()))

				// and if token has precedence, then add it to operatorStack
				// if()
					// operatorStack.push(token);

				// if the next token does not have precedence, then perform 
				// calculation and push value back into valueStack
				// else {
				// 	calc = calculate(valueStack.pop(), valueStack.pop(), operatorStack.pop().charAt(0));
				// 	// System.out.println("calc = " + calc);
				// 	valueStack.push(calc);
					/*operator = token;
					value1 = valueStack.pop();
					value2 = valueStack.pop();
					valueStack.push(value1*/
				// }
			}
		}
		
		/*
		if(!operatorStack.isEmpty()) {
			while(!operatorStack.isEmpty()) {
				second = valueStack.pop();
				first = valueStack.pop();
				valueStack.push(calculate(first, second, operatorStack.pop().charAt(0)));
			}
		}*/
		return valueStack.peek();
	}		
				
			
			/**Old code
			/*** Get number and operator ***
			if(isOperator(tokens.get(a))
				operator = tokens.get(a);
			else
				value = Double.parseDouble(tokens.get(a));
			
			// if empty/near empty stacks, just add value
			if(operatorStack.isEmpty()) 
				operatorStack.push(operator);
			if(valueStack.isEmpty() || valueStack.size() == 1)
				valueStack.push(value);
				
			// get another 
			**/
		
	
	public double calculate(double value1, double value2, char operator) {
		double answer = 0.0;
		switch(operator) {
			case '(': break;	// not sure about this
			case ')': break;	// not sure about this
			case '+': answer = value1 + value2;	break;
			case '-': answer = value1 - value2;	break;
			case '*': answer = value1 * value2;	break;
			case '/': answer = value1/value2;	break;
			case '%': answer = value1%value2;	break;
		}
		return answer;
	}	
	
	/**
	 *	Precedence of operators
	 *	@param op1	operator 1
	 *	@param op2	operator 2
	 *	@return		true if op2 has higher or same precedence as op1; false otherwise
	 *	Algorithm:
	 *		if op1 is exponent, then false
	 *		if op2 is either left or right parenthesis, then false
	 *		if op1 is multiplication or division or modulus and 
	 *				op2 is addition or subtraction, then false
	 *		otherwise true
	 */
	private boolean hasPrecedence(String op1, String op2) {
		if (op1.equals("^")) return false;
		if (op2.equals("(") || op2.equals(")")) return false;
		if ((op1.equals("*") || op1.equals("/") || op1.equals("%")) 
				&& (op2.equals("+") || op2.equals("-")))
			return false;
		return true;
	}
}
	/** 
	 *	
	 *	@param tokens 	List of tokens in the expression
	 
	public double evaluateExpression(List<String> tokens) {
		

		for(int a = 0; a < tokens.size(); a++) {
			String operator = "";
			double number = 0.0;


			if(utils.isOperator(tokens.get(a)))
				// operatorStack.add(tokens.get(a));
				operator = tokens.get(a);
			else
				// valueStack.add(tokens.get(a));
				number = tokens.get(a);

			
			if(operatorStack.isEmpty())
				operatorStack.add(tokens.get(a));





		}
	}
	*/
	