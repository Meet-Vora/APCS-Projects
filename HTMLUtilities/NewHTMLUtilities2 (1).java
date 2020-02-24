/**
 *	Utilities for handling HTML
 *
 *	@author	Meet Vora
 *	@since	October 16th, 2018
 */
public class NewHTMLUtilities2 {
	
	// field variables
	private int counter;

	public NewHTMLUtilities2() {
		
		counter = 0;
	}
	
	/**
	 *	Break the HTML string into tokens. The array returned is
	 *	exactly the size of the number of tokens in the HTML string.
	 *	Example:	HTML string = "Goodnight moon goodnight stars"
	 *				returns { "Goodnight", "moon", "goodnight", "stars" }
	 *	@param str			the HTML string
	 *	@return				the String array of tokens
	 */
	public String[] tokenizeHTMLString(String str) {
		// make the size of the array large to start
		String[] result = new String[10000];
		String token = "";
		int tokenNum = 0;
		
		// while(counter < str.length()) {
// make sure to reset counter value after every line
			// System.out.println(str.length());
			
//			System.out.println(counter);
			if(str.charAt(counter) == '<') {
				
				token = "";
				token = tokenizeTags(str);
				result[tokenNum] = token;
				tokenNum++;
				// System.out.println(counter);
			}
			
			System.out.println(str.charAt(counter));
			if(counter == str.length()-1);
				counter = 0;
			if(str.charAt(counter) == '>') {

				System.out.println("HELLOOOOOOOOOOO");
				
				counter++;	// adds one to get away from '>' and checks the immediate character outside the tag
				while(!isTagStart(str,counter)) {			
					
					token = "";
					if(checkOutsideTag(str,counter) == 1) {		// token outside tag is a word
						
						token = tokenizeWords(str);
						result[tokenNum] = token;
						tokenNum++;
					}
					else if(checkOutsideTag(str,counter) == 2) {		// token outside tag is a number
						
						token = tokenizeDigits(str,counter);
						result[tokenNum] = token;
						tokenNum++;
					}
					else if(checkOutsideTag(str,counter) == 3) {
						
						token += str.charAt(counter);
						result[tokenNum] = token;
						tokenNum++;
					}
					
					if(counter == str.length() -1);
						counter = 0;
					// else if(checkOutsideTag(str,counter) = 4)
					// 	counter++;
					counter++;
				}
			}
		// }

		result = resizeArray(result,tokenNum);
		// return the correctly sized array
		return result;
	}
	
	public String tokenizeTags(String str) {
		
		String tag = "";
		char symbol = 0;
		
		while(symbol != '>') {
			
			symbol = str.charAt(counter);			
			tag += symbol;
			counter++;
		}
		return tag;
	}
	
	public String tokenizeWords(String str) {
		
		String word = "";
		char symbol = 0;
		
		while(symbol != ' ') {	// counter equals to the index of the space

			symbol = str.charAt(counter);
			word += symbol;
			counter++;
		}
		return word;
	}
	
	// maybe check for the decimal point or exponent, but not sure if you need to do that if you recognize the string is a number, and you save it till the space
	public String tokenizeDigits(String str, int counter) {		
		
		String number = "";
		char symbol = 0;
		
		while(symbol != ' '){
			
			symbol = str.charAt(counter);
			number += symbol;
			counter++;
		}
		return number;
	}

	public boolean isPunctuation(char symbol) {
		
		// char symbol = str.charAt(counter);

		if((symbol >= '!' && symbol <= '/') || symbol == '=' || symbol == '?')
			return true;

		return false;
	}
	
	public int checkOutsideTag(String str, int counter) {	// maybe change the symbol - 1 
		
		char symbol = str.charAt(counter);
		int charType = 0;
		
		if(Character.isLetter(symbol))
			charType = 1;
		else if(Character.isDigit(symbol) || symbol == '-' && Character.isDigit(symbol + 1))
			charType = 2;
		// checks if there is a punctuation after the tag ends
		else if(isPunctuation(symbol))
			charType = 3;
		else if(symbol == ' ')
			charType = 4;
		else
			charType = -1;

		return charType;
	}
	
	public String[] resizeArray(String[] result, int tokenNum) {

		String[] smallerResult = new String[tokenNum];
		// int index = 0;

		// while(!result[index].equals("")) {

		// 	smallerResult[index] = result[index];
		// 	index++;
		// }

		for(int a = 0; a < tokenNum; a++) {
			smallerResult[a] = result[a];
		}
		return smallerResult;
	}

	public boolean isTagStart(String str, int counter) {
		
		char symbol = str.charAt(counter);
		
		if(symbol == '<')
			return true;
	/*	
		// checks if there is a word after the html token ends
		if(symbol - 1 == '>' && Character.isLetter(symbol))
			return true;
		if(symbol - 1 == '>' && Character.isDigit(symbol))
			return true;
		// checks if there is a punctuation after the html token ends
		if(symbol - 1 == '>' && isPunctuation(symbol))
			return true;
	*/
		return false;
		// check everything: hyphen, dash, etc
		
	}
	
	public boolean isTagEnd(String str, int counter) {
		
		char symbol = str.charAt(counter);
		
		if(symbol == '>')
			return true;
		
		return false;
	}
	
	public boolean isStrEnd(String str, int counter) {
		
		if(str.length() == counter)
			return true;
		return false;
	}
	
	
	/**
	 *	Print the tokens in the array to the screen
	 *	Precondition: All elements in the array are valid String objects.
	 *				(no nulls)
	 *	@param tokens		an array of String tokens
	 */
	public void printTokens(String[] tokens) {
		if (tokens == null) return;
		for (int a = 0; a < tokens.length; a++) {
			if (a % 5 == 0) System.out.print("\n  ");
			System.out.print("[token " + a + "]: " + tokens[a] + " ");
		}
		System.out.println();
	}

}