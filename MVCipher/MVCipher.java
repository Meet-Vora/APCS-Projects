import java.util.Scanner;
import java.io.PrintWriter;
import java.lang.Character;

/**
 *	MVCipher - Utilizing a key, MVCipher.java encrypts or decrypts a pre-written
 			   message and prints it to an external file. The user is prompted
 			   if they would like their file to be encrypted or decrypted, and 
 			   which file they would like their crypted message to be printed to

 *	Requires Prompt and FileUtils classes.
 *	
 *	@author	Meet Vora
 *	@since	September 20th, 2018
 */
public class MVCipher {
	
	// fields go here
	private boolean isEncrypt;

	private char[] keyArray;

	private Scanner input;
	private PrintWriter writer;
	
	private String key;
	private String inputFile;
	private String outputFile;

		
	/** 
	 *	Initializes field variables to basic values that will be changed later
	 *	in the program.
	 */
	public MVCipher() {

		isEncrypt = false;

		input = null;
		writer = null;

		key = "";		
		inputFile = "";		// get it from user input
		outputFile = "";	// get it from user input

		// keyArray = new int[key.length()];
		// cryptionPhraseArray = new int[getCryptionPhrase.length()];
	}
	
	public static void main(String[] args) {

		MVCipher mvc = new MVCipher();
		mvc.run();
	}
	
	/**
	 *	Prints welcome message for user. Also prompts user for a key that will 
	 *	be used to crypt their message, whether they want to encrypt or decrypt 
	 *	their file, and which file they would like to have crypted and which 
	 *	file they would like their crypted message to be printed to. Prints a
	 *	completion message when the cryption is complete.
	 */	
	
	public void run() {
		
		int getCryption = 0;
		boolean isKeyLetters = false;
		String encryptedPhrase = "";
		String decryptedPhrase = "";
		String typeOfFile = "";

		System.out.println("\n Welcome to the MV Cipher machine!\n");
		
		/* Prompt for a key and change to uppercase
		   Do not let the key contain anything but alpha
		   Use the Prompt class to get user input */

		
		// Makes sure the key is not an empty String and contains no
		// non-alpha characters 	   
		do {
			
			key = Prompt.getString("Please input a word to use as key "
				+ "(letters only)");
			isKeyLetters = isKeyLetters();
		
		} while (isKeyLetters == false);

		key = key.toUpperCase();

		/* Prompt for encrypt or decrypt */
	

		// If 1 is inputted, the program will encrypt. If 2, it will decrypt
		if (Prompt.getInt("\nEncrypt or decrypt", 1, 2) == 1)
			isEncrypt = true;
		else
			isEncrypt = false;

		/* Prompt for an input file name */
		if(isEncrypt) {
		
			inputFile = Prompt.getString("Name of file to encrypt");
			typeOfFile = "encrypted";
		}
		else {

			inputFile = Prompt.getString("Name of file to decrypt");
			typeOfFile = "decrypted";	
		}

		input = FileUtils.openToRead(inputFile);

		/* Prompt for an output file name */
		outputFile = Prompt.getString("Name of output file");
		writer = FileUtils.openToWrite(outputFile);

		/* Read input file, encrypt or decrypt, and print to output file */
		encryptOrDecrypt();

		System.out.println("\nThe " + typeOfFile + " file " + outputFile + 
			" has been created using the keyword -> " + key + "\n");

		writer.close();
	}

	/**
	 *	Initializes an array of char containing a letter of the key in each 
	 *	of its indices.
	 */
	public void makeKeyArray() {

		// keyArray = new char[key.length()];

		for (int a = 0; a <= key.length() - 1; a++)
			keyArray[a] = key.charAt(a);
	}
	
	/**
	 *	Makes sure the key is not an empty String or has any non-alpha 
	 *	characters.
	 *
	 *	@return 	True or false based on the characters in the key
	 */

	public boolean isKeyLetters() {

		if (key.equals(""))
			return false;

		for(int a = 0; a <= key.length() - 1; a++) {
			
			if(Character.isLetter(key.charAt(a)) == false)
				return false;
		}

		return true;
	}

	/**
	 *	Reads the given file line-by-line, encrypts or decrypts the message, 
	 *	then prints the result to the given output file.
	 */


	public void encryptOrDecrypt() {

		String originalInput = "";	// Exact same text as each line in input
		
		char updatedInput = 0;	
		char oneInputCharacter = 0;	
		char keyChar = 0;	
		
		int countKeyIndex = 0; 
		int addASCIIValue = 0;

		keyArray = new char[key.length()];

		do {

			// Reads each line of the file and stores it as a String
			originalInput = input.nextLine();
			
			// Assigns each character of each line to be checked if needed to
			// be crypted
			for (int a = 0; a <= originalInput.length() - 1; a++) {
				oneInputCharacter = originalInput.charAt(a);

				// Makes sure each character is a letter before they are crypted
				if(isInputLetters(originalInput, oneInputCharacter)) {

					// keyChar = key.charAt(countKeyIndex % key.length());
					keyArray[countKeyIndex] = key.charAt(countKeyIndex);

					if (isEncrypt) {

						addASCIIValue = keyArray[countKeyIndex] - 'A' + 1;
						// addASCIIValue = keyChar - 'A' + 1;
						updatedInput = (char)(addASCIIValue + oneInputCharacter);
						updatedInput = encryptEachChar(updatedInput, oneInputCharacter);
						writer.write(updatedInput);
					}
					else {
						
						// addASCIIValue = keyChar - 'A';
						addASCIIValue = keyArray[countKeyIndex] - 'A';
						updatedInput = (char)(oneInputCharacter - addASCIIValue);
						updatedInput = decryptEachChar(updatedInput, oneInputCharacter);
						writer.write(updatedInput);
					}

					countKeyIndex = checkKeyCounter(countKeyIndex);
				}
				// If character is not a letter, write it to file as it is
				else
					writer.write(oneInputCharacter);

			}
			writer.println();

		}while(input.hasNext());	// Ends loop when no file reaches end
	}

	/**
	 *	Makes sure each encrypted character is an alphabetcal character by 
	 *	checking if it is greater than the ASCII value of 'z' or 'Z', and loops 
	 *	it back as an alphabet character if it is out of those bounds.
	 *
	 *	@param updatedInput			The encrypted character
	 *	@param oneInputCharacter	The non-encrypted character
	 *	@return updatedInput		The new character if it was not within the 
	 									set character bounds
	 */

	public char encryptEachChar(char updatedInput, char oneInputCharacter) {

		// range of lowercase letters
		boolean inLowerRange = oneInputCharacter >= 'a' && 
			oneInputCharacter <= 'z';

		// range of uppercase letters
		boolean inUpperRange = oneInputCharacter >= 'A' && 
			oneInputCharacter <= 'Z';

		while (updatedInput > 'z' && inLowerRange)
			updatedInput = (char)(updatedInput - 'z' + 'a' - 1);
		
		while (updatedInput > 'Z' && inUpperRange)
			updatedInput = (char)(updatedInput - 'Z' + 'A' - 1);

		return updatedInput;
	}

/**
	 *	Checks if each letter in the input is within the ASCII values of an
	 *	alphabetical character.
	 *
	 *	@param originalInput		The non-encrypted String of each line
	 *	@param oneInputCharacter	The non-encrypted character
	 *	@return 					True or false
	 */
	

	public boolean isInputLetters(String originalInput, char oneInputCharacter) {

		for (int a = 0; a <= originalInput.length() - 1; a++ ) {
			
			if((oneInputCharacter >= 'a' && oneInputCharacter <= 'z') ||
				(oneInputCharacter >= 'A' && oneInputCharacter <= 'Z'))   
				
				return true;				
		}
		
		return false;
	}

	/**
	 *	Checks the counter used to go through the indices of the keyArray, and 
	 *	changes its values if already beyond a certain value.
	 *
	 *	@param counter		The un-changed index counter for keyArray
	 *	@return counter		The changed index counter for keyArray	 
	 */

	public int checkKeyCounter(int counter) {

		if(counter == key.length() - 1) {
			counter = -1;
		}

		if (key.length() > 1)			
			counter++;
			
		return counter;
	}

	/**
	 *	Makes sure each decrypted character is an alphabetcal character by 
	 *	checking if it is less than the ASCII value of 'a' or 'A', and loops it 
	 *	back as an alphabet character if it is out of those bounds.
	 *
	 *	@param updatedInput			The encrypted character
	 *	@param oneInputCharacter	The non-encrypted character
	 *	@return updatedInput		The new character if it was not within the 
	 									set character bounds
	 */
	
	public char decryptEachChar(char updatedInput, char oneInputCharacter) {

		// boolean completed used to see if String has been looped from Z to A
		boolean completed = false;

		// range for lowercase characters
		boolean inLowerRange = oneInputCharacter >= 'a' && 
			oneInputCharacter <= 'z';

		// range for uppercase characters
		boolean inUpperRange = oneInputCharacter >= 'A' && 
			oneInputCharacter <= 'Z';

		while (updatedInput <= 'a' && inLowerRange) {
			
			updatedInput = (char)(updatedInput + 'z' - 'a');
			completed = true;
		}
		
		while (updatedInput <= 'A' && inUpperRange) {
			
			updatedInput = (char)(updatedInput + 'Z' - 'A');
			completed = true;		
		}

		if(completed == false)
			updatedInput--;

		return updatedInput;
	}
}
