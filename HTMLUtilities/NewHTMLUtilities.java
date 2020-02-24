/**
 *	Utilities for handling HTML
 *
 *	Description: Tokenizes HTML code, including code inside and outside of tags.
 *				 Avoids HTML code in comments, and keeps the correct format of
 *				 code that is between the markers "<pre>" and "</pre>".
 *				  
 *
 *	@author Meet Vora
 *	@since October 16, 2018
 */
public class NewHTMLUtilities {

	// private boolean comment; // whether the formatter is inside a comment
	// private boolean preformat;
	// NONE = not nested in a block, COMMENT = inside a comment block
	// PREFORMAT = inside a pre-format block
	private enum TokenState { NONE, COMMENT, PREFORMAT };
	
	// the current tokenizer state
	private TokenState state; 
	
	/**
	 *	Break the HTML string into tokens. The array returned is
	 *	exactly the size of the number of tokens in the HTML string.
	 *	Example:	HTML string = "Goodnight moon goodnight stars"
	 *				returns { "Goodnight", "moon", "goodnight", "stars" }
	 *	@param str			the HTML string
	 *	@return				the String array of tokens
	 */
	public String[] tokenizeHTMLString(String str) {

		// make the size of the array large!
		String[] result = new String[10000];
		
		int tokenNum = -1; 
		int counter = 0;
		
		// String to hold each token from str
		String token = "";
		
		while (counter < str.length()) {

			char symbol = str.charAt(counter);	// character at each index in str
			
			// checks for end of comment
			if (str.indexOf("-->", counter) == counter) {
				state = TokenState.NONE;
				counter += 2;
				token = "";		// confirm that no token is saved 
			}

			// checks for beginning of comment
			else if (str.indexOf("<!--", counter) == counter) {
				// makes sure no tokens are saved while in a comment 
				state = TokenState.COMMENT;
				// confirm that no token is saved
				token = "";
			}

			// checks for beginning of preformatted block of code
			else if(str.indexOf("<pre>",counter) == counter 
				&& state != TokenState.COMMENT) {

				state = TokenState.PREFORMAT;
				counter += 4;
				tokenNum++;
				result[tokenNum] = "<pre>";

				// confirm that no token is saved
				token = "";
			}

			// if in the preformatted block, saves each line as a full token
			else if(state == TokenState.PREFORMAT) {
				
				// keeps going till counter reaches end of string or the
				// preformatted block ends
				while(counter < str.length() && 
					!str.substring(0,counter).equals("</pre>")) {

					symbol = str.charAt(counter);

					token += symbol;
					counter++;
				}
				
				// records anything in token string before resetting it
				if (token.length() > 0) {
					tokenNum++;
					result[tokenNum] = token;
					// confirm that no token is saved
					token = "";
				}
			}

			// checks for beginning of a regular token
			else if (symbol == '<' && state != TokenState.COMMENT) {
				
				int temp = counter;
			
				// records anything in token string before resetting it
				if (token.length() > 0) {
					tokenNum++;
					result[tokenNum] = token;
					
					// confirm that no token is saved
					token = "";
				}
				// adds every character in the token into the token String
				for (int a = counter; a <= str.indexOf('>', counter); a++) 
					token += str.charAt(a);
				
				counter = str.indexOf('>', temp);
				symbol = str.charAt(counter);
			}

			// checks for punctuation that is within a word or number
			else if (counter < str.length() - 1 && isPunctuationAroundChar(str,counter)
				&& isPunctuation(symbol) && state != TokenState.COMMENT) {

				// confirm that no token is saved
				token = "";
				counter--;

				if(counter >= 0)
					symbol = str.charAt(counter);

				// Retreats back to start of word or number
				do {

					if (counter >= 0 && (Character.isLetter(symbol) 
						|| Character.isDigit(symbol))) {
						counter--;
					}
					if(counter >= 0)
						symbol = str.charAt(counter);

				} while(counter >= 0 && (Character.isLetter(symbol) 
					|| Character.isDigit(symbol)));

				
				counter++;
				symbol = str.charAt(counter);

				// Goes up the word or number again, recording the punctuation
				// first found into the token
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

				// makes sure tag isn't saved
				if(symbol == '<')
					counter--;
			}

			// checks for regular punctuation that is not within words or numbers
			else if (isPunctuation(symbol) && state != TokenState.COMMENT) {

				// records anything in token string before resetting it
				if (token.length() > 0) {
					tokenNum++;
					result[tokenNum] = token;
				
					// confirm that no token is saved
					token = "";
				}
				// checks for ellipsis. Adds the character if not one
				if (str.indexOf("...", counter) == counter) {
					token += "...";
					counter += 2;
					symbol = str.charAt(counter);
				}
				else 
					token += symbol;
			}
			// If nothing else works, adds a whitespace non-comment characters
			else if (!Character.isWhitespace(symbol) && state != TokenState.COMMENT ) {
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

		// adds the last token of the string to the result array
		if (token.length() > 0) {
			tokenNum++;
			result[tokenNum] = token;
		}
		
		// resizes the array to make sure there are no empty strings in any index
		result = resizeArray(result, tokenNum + 1);
		
		// return the correctly sized array
		return result;
	}
	
	/**
	 *	Upon arriving at a punctuation, checks if it is a part of a word or a
	 *	number by checking the characters around that original punctuation.
	 *	@param str 		Original string from which the characters are read
	 *	@param counter	Index of original string
	 *	@return 		true if punctuation is part of word or number; false
	 *						otherwise
	*/
	public boolean isPunctuationAroundChar(String str, int counter) {

		if(counter - 1 >= 0 && counter < str.length() 
			&& Character.isLetter(str.charAt(counter + 1))
			&& Character.isLetter(str.charAt(counter - 1))
			|| Character.isDigit(str.charAt(counter + 1)))

			return true;

		return false;
	}

	/** 
	 *	Based on how many indices are filled in the original array of Strings,
	 *	this method resizes the array to take out any spaces with empty strings.
	 *	The resized array is then passed back. 
	 * 	@param result	the original array of Strings
	 * 	@param tokenNum	the number filled indices in the array
	 * 	@return  		copy of the originial array of Strings with only the
	 *						number of filled indices as its length
	 */
	 public String[] resizeArray(String[] result, int tokenNum) {

		// new array with size as the valid elements in array
		String[] tokenArray = new String[tokenNum];

		// Loops through the result array and assigns each value to another array
		for (int a = 0; a <= tokenNum - 1; a++) 
			tokenArray[a] = result[a];
	
		// return the new resized array
		return tokenArray;
	 } 
	
	/**
	 *	Checks if the passed character as the parameter is a punctuation mark.
	 *	Checks this by verifying the character is not a blankspace, a letter,
	 *	or a number.
	 *	@param symbol	the character in the String
	 *	@return 		true if character is punctuation; false otherwise
	 */
	private boolean isPunctuation(char symbol) {

		// If character is not a whitespace, letter, or digit, it is punctuation
		if (!Character.isWhitespace(symbol) && !Character.isLetter(symbol) && !Character.isDigit(symbol))
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
