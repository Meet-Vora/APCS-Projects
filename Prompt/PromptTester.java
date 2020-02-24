/**
 *	Test the Prompt class
 *	@author	Mr Greenstein
 *	@since	August 8, 2018
 */

public class PromptTester {

	public static void main(String[] args) {
		String str = Prompt.getString("Provide me a string");
		System.out.println("Here it is -> " + str);
		
		int a = Prompt.getInt("Give me an integer");
		System.out.println("Here it is -> " + a);
		
		a = Prompt.getInt("Give me an integer greater than 20 but less than 40", 20, 40);
		System.out.println("Here it is -> " + a);
		
		char character = Prompt.getChar("Provide me a character");
		System.out.println("Here it is -> " + character);
		
		double dble = Prompt.getDouble("Give me a decimal number");
		System.out.println("Here it is -> " + dble); 
		
		
	}

}