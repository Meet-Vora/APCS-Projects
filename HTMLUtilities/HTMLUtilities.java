/**
 *	Utilities for handling HTML
 *
 *	Tokenizes all HTML code including several tags such as <body>, <html>, 
 *	<br> and <p>, as well as punctuation and whitespace. Demands an unformatted
 *	String with the HTML code and returns a tokenized String array of the same size
 *	as the number of tokens.
 *
 *	@author Meet Vora
 *	@since October 16, 2018
 */
public class HTMLUtilities {

	private boolean comment; // whether the formatter is inside a comment
	private boolean preformat;
	
	/**
	 * The main utility in this class. It takes a string as input, then
	 * tokenizes the string and passes back an array of string tokens.
	 * Precondition: str is not a null pointed
	 * @param str		the input string to tokenize
	 * @return			the array of string tokens 
	 */
	
	public String[] tokenizeHTMLString(String str) {

		String[] result = new String[10000];	// make the size of the array large!
		int tokenNum = -1; 
		int counter = 0;
		
		// string to hold the token
		String token = "";
		
		while (counter < str.length()) {

			char symbol = str.charAt(counter);
			
			// check if it's the end of a comment
			if (str.indexOf("-->", counter) == counter) {
				comment = false;
				counter += 2;
				// make sure no tokens are recorded
				token = "";
			}
			// check if it's the beginning of the comment
			else if (str.indexOf("<!--", counter) == counter) {
				// set comment to true so that no tokens are recorded
				comment = true;
				// make sure no tokens are recorded
				token = "";
			}
			else if(str.indexOf("<pre>",counter) == counter && !comment) {

				preformat = true;
				counter += 4;
				tokenNum++;
				result[tokenNum] = "<pre>";
				token = "";
			}
			else if(preformat) {
				
				while(counter < str.length() && !str.substring(0,counter).equals("</pre>")) {

					symbol = str.charAt(counter);

					token += symbol;
					counter++;
				}
				
				if (token.length() > 0) {
					tokenNum++;
					result[tokenNum] = token;
					// reset token
					token = "";
				}
			}

			// check if beginning of a non-comment token
			else if (symbol == '<' && !comment) {
				
				int temp = counter;
				// if there is already a token, end it as long as it is
				// length > 0
				if (token.length() > 0) {
					tokenNum++;
					result[tokenNum] = token;
					// reset token
					token = "";
				}
				// add every character of the token to the token String
				for (int a = counter; a <= str.indexOf('>', counter); a++) 
					token += str.charAt(a);
				
				counter = str.indexOf('>', temp);
				symbol = str.charAt(counter);
			}
			// else if(counter < str.length() - 1 && isPunctuation(symbol) && 
			// 	((Character.isLetter(str.charAt(counter + 1))  && Character.isLetter(str.charAt(counter - 1))) ||  
			// 	(Character.isDigit(str.charAt(counter + 1)) && Character.isDigit(counter - 1))) && !comment) {
			else if (counter < str.length() - 1 && isPunctuationAroundChar(str,counter)
				&& isPunctuation(symbol) && !comment) {
			
				// if (token.length() > 0) {

				// 	tokenNum++;
				// 	result[tokenNum] = token;
				// 	// reset token
				// 	token = "";
				// }

				token = "";
				counter--;
				symbol = str.charAt(counter);

				do {
					// symbol = str.charAt(counter);
					if (counter >= 0 && (Character.isLetter(symbol) || Character.isDigit(symbol))) {
						counter--;
					}
					if(counter >= 0)
						symbol = str.charAt(counter);

				} while(counter >= 0 && (Character.isLetter(symbol) || Character.isDigit(symbol)));

				counter++;
				symbol = str.charAt(counter);

				do {

					if (Character.isLetter(symbol) || Character.isDigit(symbol)
						|| isPunctuation(symbol)) {
							
						token += symbol;
						if(counter < str.length())	
							counter++;	// ends at space
					}
					symbol = str.charAt(counter);
				} while(counter < str.length() - 1 
					&& symbol != '<' && (Character.isLetter(symbol) 
					|| Character.isDigit(symbol) || isPunctuation(symbol)));

				if(symbol == '<')
					counter--;

				// if(counter == str.length() - 1 && 
				// 	Character.isDigit(str.charAt(counter - 1)) && symbol == '.') {

				// 	tokenNum++;
				// 	result[tokenNum] = "" + '.';
				// }
			}
			
			// check if it is non-comment punctuation (including ellipsis)
			else if (isPunctuation(symbol) && !comment) {
				// if there is already a token, end it as long as it is
				// length > 0
				if (token.length() > 0) {
					tokenNum++;
					result[tokenNum] = token;
					// reset token
					token = "";
				}
				// check for ellipsis and if not ellipsis, add that char
				if (str.indexOf("...", counter) == counter) {
					token += "...";
					counter += 2;
					symbol = str.charAt(counter);
				}
				else 
					token += symbol;
			}
			// add non-whitespace and non-comment characters if all fails
			else if (!Character.isWhitespace(symbol) && !comment ) {
				token += symbol;
			}
			if (token.length() > 0 && (Character.isWhitespace(symbol) || symbol == '>' ||
					symbol == '<' || isPunctuation(symbol))){
				tokenNum++;
				result[tokenNum] = token;
				// reset token
				token = "";
			}
			counter++;
		}
		// in case last token goes to end of string
		if (token.length() > 0) {
			tokenNum++;
			result[tokenNum] = token;
		}
		
		result = resizeArray(result, tokenNum + 1);
		
		return result;
	}
	
	public boolean isPunctuationAroundChar(String str, int counter) {

		if(counter - 1 >= 0 && counter < str.length() 
			&& Character.isLetter(str.charAt(counter + 1))
			&& Character.isLetter(str.charAt(counter - 1))
			|| Character.isDigit(str.charAt(counter + 1)))

			return true;

		return false;
	}

	/** Takes a large string array as input and outputs a
	 *  copy string array that is exactly the size of the 
	 *  number of valid elements.
	 * 	@param arr		the input String array
	 * 	@param num 		the number of valid elements in array
	 * 	@return a 		a copy of the String array with exactly the number
	 * 					of valid elements
	 */
	 public String[] resizeArray(String[] result, int number) {

		// new array with size as the valid elements in array
		String[] tokenArray = new String[number];

		// loop through array and assign each value	
		for (int a = 0; a <= number - 1; a++) 
			tokenArray[a] = result[a];
	
		return tokenArray;
	 } 
	
	/**
	 *	Checks if the character given is punctuation (not
	 *	letters or numbers) and then returns whether a 
	 *	boolean value.
	 *	@param symbol		the character in the main String
	 *	@return 		whether it is punctuation
	 */
	private boolean isPunctuation(char symbol) {

		// punctuation if it is not whitespace, letter or digit
		if (!Character.isWhitespace(symbol) && !Character.isLetter(symbol) && !Character.isDigit(symbol))
			return true;
		return false;
	}
	
	/**
	 *	Print the tokens in the array
	 *	Precondition: All elements in the array are valid String objects. (no nulls)
	 *	@param tokens	an array of String tokens
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
