import java.util.Scanner;
import java.io.PrintWriter;
import java.lang.Character;

/**
 *	MVCipher - Add your description here
 *	Requires Prompt and FileUtils classes.
 *	
 *	@author	Meet Vora
 *	@since	September 20th, 2018
 */
public class MVCipher2 {
	
	private boolean isEncrypt;

	private char[] keyArray;
	private char[] cryptionPhraseArray;
	
	private Scanner input;
	private PrintWriter writer;
	
	private String key;
	private String inputFile;
	private String outputFile;

	// fields go here
		
	/** Constructor */
	public MVCipher2() {

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

		MVCipher2 mvc2 = new MVCipher2();
		mvc2.run();
	}
	
	/**
	 *	Method header goes here
	 */	
	/*
	
			
		
		
		
		
		/* Read input file, encrypt or decrypt, and print to output file */
		
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

		do {
			
			key = Prompt.getString("Please input a word to use as key "
				+ "(letters only)");
			isKeyLetters = isKeyLetters();
		
		} while (isKeyLetters == false);

		key = key.toUpperCase();

		/* Prompt for encrypt or decrypt */
	


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

		encryptOrDecrypt();

		System.out.println("\nThe " + typeOfFile + " file " + outputFile + 
			" has been created using the keyword -> " + key + "\n");

		writer.close();
	}

	public void makeKeyArray() {

		// keyArray = new char[key.length()];

		for (int a = 0; a <= key.length() - 1; a++)
			keyArray[a] = key.charAt(a);
	}
	
	public boolean isKeyLetters() {

		if (key.equals(""))
			return false;

		for(int a = 0; a <= key.length() - 1; a++) {
			
			if(Character.isLetter(key.charAt(a)) == false)
				return false;
		}

		return true;
	}

	public void encryptOrDecrypt() {

		String originalInput = "";	// Exact same text as each line in input
		
		char updatedInput = 0;	// Exact same text as each line in input
		char oneInputCharacter = 0;	// Exact same text as each line in input
		char keyChar = 0;	// Exact same text as each line in input
		
		int countKeyIndex = 0; 
		int addASCIIValue = 0;

		keyArray = new char[key.length()];


		// makeKeyArray();

		do {

			originalInput = input.nextLine();
			// System.out.println(originalInput);
			// isInputLetters = isInputLetters(originalInput);
			
			for (int a = 0; a <= originalInput.length() - 1; a++) {
				oneInputCharacter = originalInput.charAt(a);


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

					// countKeyIndex++;

					countKeyIndex = checkKeyCounter(countKeyIndex); 	// 	works
				}
				else
					writer.write(oneInputCharacter);

			}
			writer.println();

		}while(input.hasNext());	
	}

	public char encryptEachChar(char updatedInput, char oneInputCharacter) {

		// String oneInputLetter = Character.toString(oneInputCharacter);

		boolean inLowerRange = oneInputCharacter >= 'a' && oneInputCharacter <= 'z';
		boolean inUpperRange = oneInputCharacter >= 'A' && oneInputCharacter <= 'Z';

		while (updatedInput > 'z' && inLowerRange)
			updatedInput = (char)(updatedInput - 'z' + 'a' - 1);
		
		while (updatedInput > 'Z' && inUpperRange)
			updatedInput = (char)(updatedInput - 'Z' + 'A' - 1);

		return updatedInput;
	}

	public boolean isInputLetters(String originalInput, char oneInputCharacter) {

		for (int a = 0; a <= originalInput.length() - 1; a++ ) {
			
			if((oneInputCharacter >= 'a' && oneInputCharacter <= 'z') ||
				(oneInputCharacter >= 'A' && oneInputCharacter <= 'Z'))   
				
				return true;				
		}
		
		return false;
	}

	public int checkKeyCounter(int counter) {

		if(counter == key.length() - 1) {
			counter = -1;
		}

		if (key.length() > 1)			
			counter++;
			
		return counter;
	}
	
	public char decryptEachChar(char updatedInput, char oneInputCharacter) {

		// String oneInputLetter = Character.toString(oneInputCharacter);

		boolean completed = false;
		boolean inLowerRange = oneInputCharacter >= 'a' && oneInputCharacter <= 'z';
		boolean inUpperRange = oneInputCharacter >= 'A' && oneInputCharacter <= 'Z';

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
