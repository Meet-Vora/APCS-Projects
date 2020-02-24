public class Palindrome {

	String word;

	public static void main(String[] args) {
		Palindrome pal = new Palindrome();
		pal.run();
	}
	
	public void run() {

		String input = "";

		printIntroduction();
		do {
			input = getFunction();
		} while (!input.equals("1") && !input.equals("2"));

		word = getInput();

		if(input.equals("1"))
			System.out.println("Here's your Palindrome: " + makePalindrome(word));
		else {
			if(checkWord(word))
				System.out.println("Congratulations! Your word is a Palindrome!");
			else
				System.out.println("Sorry. Your word is not a Palindrome");
		}
	}

	public String makePalindrome(String word) {

		for(int a = word.length() - 1; a >= 0; a--) {
			word += word.charAt(a);
		}
		return word;
	}

	public boolean checkWord(String word) {

		int first = 0;
		int second = word.length() - 1;
		
		for(first = 0; first < second; first++) {
			if(word.charAt(first) != word.charAt(second))
				return false;
			second--;
		}
		return true;
	}

	public String getFunction() {

		String input = Prompt.getString("1 - create your own Palindrome. " + 
			"2 - enter word to check if it is a Palindrome");
		return input;
	}

	public String getInput() {
		String input = Prompt.getString("Enter word");
		return input;
	}

	public void printIntroduction() {

		System.out.println("\nWelcome to Palindrome.java!");
	}
}

