// imports go here
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
public class MVCipherArray {
	
	private boolean isEncrypt;

	private final int firstASCIILetter = 64;
	private final int lastASCIILetter = 90;

	private int[] keyArray;
	private int[] cryptionPhraseArray;
	
	private Scanner getInputFile;
	private Scanner getOutputFile;
	private PrintWriter writer;
	
	private String key;
	private String inputFileName;
	private String outputFileName;
	private String getCryptionPhrase;

	// fields go here
		
	/** Constructor */
	public MVCipherArray() {

		isEncrypt = false;

		getOutputFile = new Scanner(System.in);
		getInputFile = new Scanner(System.in);
		writer = new PrintWriter(System.out);

		key = "";		
		inputFileName = "";		// get it from user input
		outputFileName = "";	// get it from user input
		getCryptionPhrase = "";

		// keyArray = new int[key.length()];
		// cryptionPhraseArray = new int[getCryptionPhrase.length()];
	}
	
	public static void main(String[] args) {

		MVCipherArray mvca = new MVCipherArray();
		mvca.run();
	}
	
	/**
	 *	Method header goes here
	 */	
	/*
	public void run() {
		
		promptForCryption();
		
		// if(isEncrypt)	
		// 	doEncrytion();
		// else
		// 	doDecryption();
	}
	*/
			
			
		/* Prompt for an input file name */
		
		
		/* Prompt for an output file name */
		
		
		/* Read input file, encrypt or decrypt, and print to output file */
		
	public void run() {
		
		int getCryption = 0;
		boolean isKeyLetters = false;
		String[] encryptedPhrase = new String[getCryptionPhrase.length()];
		String[] decryptedPhrase = new String[getCryptionPhrase.length()];

		System.out.println("\n Welcome to the MV Cipher machine!\n");
		
		/* Prompt for a key and change to uppercase
		   Do not let the key contain anything but alpha
		   Use the Prompt class to get user input */

		do {
			
			key = Prompt.getString("Please input a word to use as key "
				+ "(letters only)");
			isKeyLetters = isKeyLetters();
		
		} while (isKeyLetters == false);

		/* Prompt for encrypt or decrypt */
	
		do {

			getCryption = Prompt.getInt("Encrypt or Decrypt? (1 - 2)");
		
		} while(getCryption != 1 && getCryption != 2);

		if(getCryption == 1) {

			isEncrypt = true;
			
			System.out.print("Name of file to encrypt -> ");
			readFile();	

			encryptedPhrase = doEncryption();

			System.out.print("Name of output file -> ");
			writeToFile(encryptedPhrase);

			System.out.println("\nThe encrypted file " + outputFileName + 
					" has been created using the keyword -> " + key + "\n");
		}
		else if(getCryption == 2) {

			isEncrypt = false;

			System.out.print("Name of file to decrypt -> ");
			readFile();

			decryptedPhrase = doDecryption();

			System.out.print("Name of output file -> ");
			writeToFile(decryptedPhrase);

			System.out.println("\n The decrypted file " + outputFileName + 
					" has been created using the keyword -> " + key + "\n");
		}
	}
		
		/* Don't forget to close your output file */
	// other methods go here
	
	public void readFile() {

		inputFileName = getInputFile.nextLine();
		getInputFile = FileUtils.openToRead(inputFileName);		// Checks and reads file stated by the user.

		while(getInputFile.hasNext()) {

			getCryptionPhrase = getCryptionPhrase + getInputFile.nextLine();  
		}

		getInputFile.close();
	}

	public void writeToFile(String[] cryptedPhrase) {

		outputFileName = getOutputFile.nextLine();
		writer = FileUtils.openToWrite(outputFileName);
		writer.println(cryptedPhrase);
		writer.close();
	}


	/*	Step 1: Make both the key and the phrase to be encrypted upper case
	 *	Step 2: Find the difference between the letter position of the the key and the encryption phrase
	 *	Step 3: Add that difference to the letter position of the encryption phrase
	 *					- Make sure it loops like the alphabet.
	 *	Step 4: Make sure to ignore non-letter characters - keep them the same
	 * 	Step 5: Send it back to the other method and print it in the outputFile
	*/

	public boolean isKeyLetters() {

		if (key.equals("")) {
			
			return false;
		}

		for(int a = 0; a <= key.length() - 1; a++) {
			
				if(Character.isLetter(key.charAt(a)) == false)
					return false;
		}

		return true;
	}

	public boolean isInputLetters() {

		for (int a = 0; a <= getCryptionPhrase.length() - 1; a++ ) {
			
			if(Character.isLetter(getCryptionPhrase.charAt(a)))
				return true;
		}
		
		return false;
	}

	public void makeUpperCase() {

		key = key.toUpperCase();
		getCryptionPhrase = getCryptionPhrase.toUpperCase();
	}

	public void makeIntoArray() {
		
		keyArray = new int[key.length()];
		cryptionPhraseArray = new int[getCryptionPhrase.length()];

		makeUpperCase();

		for(int a = 0; a <= key.length() - 1; a++)
			keyArray[a] = (int)key.charAt(a);
		
		for(int a = 0; a <= getCryptionPhrase.length() - 1; a++) {
			cryptionPhraseArray[a] = (int)getCryptionPhrase.charAt(a);
			// if (Character.isLetter(cryptionPhraseArray[a])) {
				
				
			// }
			// else
			// 	cryptionPhraseArray[a] = getCryptionPhrase.charAt(a);
			
		}
	}

	public int checkKeyCounter(int counter) {

		if(counter == keyArray.length - 1)
			counter = 0;

		if (keyArray.length > 1)			
			counter++;
			
		return counter;
	}

	public String[] doEncryption() {

		String[] newEncrypted = new String[getCryptionPhrase.length()];

		int counter = 0;
		int newASCIILetter = 0;

		boolean isInputLetters = isInputLetters();
		// int wrapAround = 13;

		makeIntoArray();

		for(int a = 0; a <= cryptionPhraseArray.length - 1; a++) {

			if(isInputLetters) {

				newASCIILetter = cryptionPhraseArray[a] + keyArray[counter] - 
					firstASCIILetter;
				
				while(newASCIILetter > lastASCIILetter) {

					newASCIILetter = newASCIILetter - lastASCIILetter + 
						firstASCIILetter;
				}

				newEncrypted[a] = "" + (char)(newASCIILetter);
			}

			else
				newEncrypted[a] = "" + (char)cryptionPhraseArray[a];

			counter = checkKeyCounter(counter);
		}
	
		// System.out.println(newEncrypted);

		return newEncrypted;
	}

	public String[] doDecryption() {
		
		String[] newDecrypted = new String[getCryptionPhrase.length()];
		
		int counter = 0;
		int newASCIILetter = 0;

		boolean isInputLetters = isInputLetters();
		// int wrapAround = 13;

		makeIntoArray();

		for(int a = 0; a <= cryptionPhraseArray.length - 1; a++) {

			if(isInputLetters) {

				newASCIILetter = cryptionPhraseArray[a] - keyArray[counter] + 	// done
					firstASCIILetter;
				
				while(newASCIILetter < firstASCIILetter) {

					newASCIILetter = newASCIILetter + lastASCIILetter - 
						firstASCIILetter;
				}

				newDecrypted[a] = "" + (char)(newASCIILetter);
			}
			else
				newDecrypted[a] = "" + (char)cryptionPhraseArray[a];

			counter = checkKeyCounter(counter);
		}

		// System.out.println(newDecrypted);

		return newDecrypted;
	}
}