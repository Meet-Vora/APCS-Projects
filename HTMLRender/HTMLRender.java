import java.util.Scanner;
/**
 *	HTMLRender
 *	This program renders HTML code into a JFrame window.
 *	It requires your HTMLUtilities class and
 *	the SimpleHtmlRenderer and HtmlPrinter classes.
 *
 *	The tags supported:
 *		<html>, </html> - start/end of the HTML file
 *		<body>, </body> - start/end of the HTML code
 *		<p>, </p> - Start/end of a paragraph.
 *					Causes a newline before and a blank line after. Lines are restricted
 *					to 80 characters maximum.
 *		<hr>	- Creates a horizontal rule on the following line.
 *		<br>	- newline (break)
 *		<b>, </b> - Start/end of bold font print
 *		<i>, </i> - Start/end of italic font print
 *		<q>, </q> - Start/end of quotations
 *		<hX>, </hX> - Start/end of heading with size X = 1, 2, 3, 4, 5, 6
 *		<pre>, </pre> - Preformatted text
 *
 *	@author	Meet Vora
 *	@version October 30th, 2018
 */
public class HTMLRender {
	
	// the array holding all the tokens of the HTML file
	private String [] tokens;
	private final int TOKENS_SIZE = 100000;	// size of array
	private int counter;

	// SimpleHtmlRenderer fields
	private SimpleHtmlRenderer render;
	private HtmlPrinter browser;

	// HTMLUtilities field
	private HTMLUtilities utils;

	public HTMLRender() {
		// Initialize token array
		tokens = new String[TOKENS_SIZE];
		
		// Initialize Simple Browser
		render = new SimpleHtmlRenderer();
		browser = render.getHtmlPrinter();
		
		// Initialize HTMLUtilities instance
		utils = new HTMLUtilities();
	}

	public static void main(String[] args) {

		HTMLRender hf = new HTMLRender();
		hf.run(args);
	}
	
	/**
	 *	Opens the HTML file specified on the command line then inputs each line 
	 *	and creates an array of Strings that comprises of tokens produced by 
	 *	HTMLUtilities.
	 *	@param args		the String array holding the command line arguments
	 */
	public void run(String[] args) {

		Scanner input = null;
		String fileName = "";
		int permArray = 0;

		// if the command line contains the file name, then store it
		if (args.length > 0)
			fileName = args[0];
		// otherwise print out usage message
		else {
			System.out.println("Usage: java HTMLTester <htmlFileName>");
			System.exit(0);
		}

		// Open the HTML file
		input = FileUtils.openToRead(fileName);
		
		// Read each line of the HTML file and tokenize tokens
		while (input.hasNext()) {
			String line = input.nextLine();
			// temporary array to store each line of tokenized Strings
			String[] nowTokens = utils.tokenizeHTMLString(line);
			// copy all temporary tokens to permament array of tokens
			for (int a = 0; a < nowTokens.length; a++) {
				tokens[permArray] = nowTokens[a];
				permArray++; // increment permanent array index number
			}
		}
		input.close();
		// call sortTokens to sort through tags
		sortTokens(tokens,permArray);
	}

	/**
	 *  Renders text onto screen based on tag locations. Uses several other
	 *	methods to sort through different tags and print different types and
	 *	lenghts of text, such as bold, italic, and different types of headers,
	 *	and handles breaks in the text as well.
	 *	@param tokens 		String array of HTML tokens
	 *	@param permArray 	Length of the tokens array
	 */
	public void sortTokens(String[] tokens, int permArray) {
		
		int tracker = 0;
		boolean pre = false;
		// boolean plain = false;

		while (counter < permArray) {

			String tag = tokens[counter].toLowerCase();

			// If tag that is needed, skip and increment counter
			if (tag.equals("<html>") || tag.equals("</html>") || tag.equals("<body>")
				|| tag.equals("</body>")) 
				
				counter++;
			// render paragraph style if paragraph token is detected
			else if (tag.equals("<p>")) {
				
				counter++;
				renderParagraph();
				tracker = 0;
			}
			// Print horizontal rule if tag is detected
			else if (tag.equals("<hr>")) {
				
				browser.printHorizontalRule();
				counter++;
				tracker = 0;
			}
			// Print break if break token detected
			else if (tag.equals("<br>")) {
				
				browser.printBreak();
				counter++;
				tracker = 0;
			}
			// Print bolded text if bold token detected
			else if (tag.equals("<b>")) {
				
				counter++;
				tracker = processBoldItalic(tracker, counter, true);
				
			}
			// Print italicized text if italics token detected
			else if (tag.equals("<i>")) {
				
				counter++;
				tracker = processBoldItalic(tracker, counter, false);
			}
			// Print text in quotes if quote token detected
			else if (tag.equals("<q>") || tag.equals("</q>")) {
				
				if (tag.equals("<q>")) { 
					browser.print(" \""); 
					tracker++;
				}
				else 
					browser.print("\"");
				
				tracker++; 
				counter++;
			}
			// Process type of header if any header token detected
			else if (tag.equals("<h1>") || tag.equals("<h2>") || tag.equals("<h3>")
				|| tag.equals("<h4>") || tag.equals("<h5>") || tag.equals("<h6>")) {
				
				counter++;
				findEndHeader(tag.charAt(2));
				tracker = 0;
			}
			// Print text as already formatted if preformat token detected
			else if (tag.equals("<pre>") || tag.equals("</pre>")) {
				
				counter++;
				renderPreformat();
				tracker = 0;
			}
			// Print plain text if no token detected
			else {
				tracker = renderPlainText(tracker, counter);
				counter++;
			}
		}
	}
	
	/**
	 *	Finds the location of the end of the header sequence, and uses a helper
	 *	method to print text in header format from the beginning index till the
	 *	end.
	 *	@param headerNum 	Header type, such as h1, h2, h3, etc. 
	 */
	public void findEndHeader(char headerNum) {
		
		int lastTagIndex = 0;
		int tracker = 0; 

		// finds corresponding header tag to end sequence
		for (int a = tokens.length - 1; a > counter; a--) 
			if (tokens[a] != null 
				&& tokens[a].toLowerCase().equals("</h" + headerNum + ">")) 
				lastTagIndex = a;

		for (int a = counter; a < lastTagIndex; a++) 
			tracker = printHeaderText(tracker,a,headerNum);
		
		// Increments counter to one more than the header tag index
		counter = lastTagIndex + 1;

		browser.println();
		browser.println();
	}
	
	/**
	 * 	Prints headers with correct formatting based on their header number,
	 *	their current location on the screen, and the index of the tokens array.
	 *	Also regulates the character limit for each different type of header.
	 * 	@param tracker 		Location on screen
	 * 	@param counter		Index of the String array of tokens
	 * 	@param headerNum	Header type, such as h1, h2, h3, etc.
	 * 	@return 			Changed location on the screen
	 */
	public int printHeaderText(int tracker, int counter, char headerNum) {
		// Goes to next line if over 80 character limit
		if (tracker + tokens[counter].length() > 80) {
			browser.println();
			tracker = 0;
		}

		// Checks if punctuation. If yes, then doesn't print space before token
		if (tracker == 0 || tokens[counter].length() == 1 && 
				utils.isPunctuation(tokens[counter].charAt(0))) {
			
			tracker += tokens[counter].length();

			if(headerNum == '1')
				browser.printHeading1(tokens[counter]);
			else if(headerNum == '2')
				browser.printHeading2(tokens[counter]);
			else if(headerNum == '3')
				browser.printHeading3(tokens[counter]);
			else if(headerNum == '4')
				browser.printHeading4(tokens[counter]);
			else if(headerNum == '5')
				browser.printHeading5(tokens[counter]);
			else if(headerNum == '6')
				browser.printHeading6(tokens[counter]);
		}
		
		// If no punctuation, then prints space before token	
		else {

			if(headerNum == '1')
				browser.printHeading1(" " + tokens[counter]);
			else if(headerNum == '2')
				browser.printHeading2(" " + tokens[counter]);
			else if(headerNum == '3')
				browser.printHeading3(" " + tokens[counter]);
			else if(headerNum == '4')
				browser.printHeading4(" " + tokens[counter]);
			else if(headerNum == '5')
				browser.printHeading5(" " + tokens[counter]);
			else if(headerNum == '6')
				browser.printHeading6(" " + tokens[counter]);

			tracker += tokens[counter].length() + 1;
		}

		// returns current location of pointer
		return tracker;
	}

	/**
	 * 	Prints regular text with no special formatting when given the index of 
	 *	the tokens array. Limits character count to 80 per line.
	 *	@param tracker 	Location on screen
	 *	@param counter 	Index of the String array of tokens
	 */
	public int renderPlainText(int tracker, int counter) {

		// Goes to next line if over 80 character limit
		if (tracker + tokens[counter].length() > 80) {
			browser.println();
			tracker = 0;
		}

		// If at beginning of line or at punctuation, do not add space
		if (tracker == 0 || tokens[counter].length() == 1 
			&& utils.isPunctuation(tokens[counter].charAt(0))) {

			tracker += tokens[counter].length();
			browser.print(tokens[counter]);
		}
		// If not at beginning of line or at punctuation, then add space
		else {
			browser.print(" " + tokens[counter]);
			tracker += tokens[counter].length() + 1;
		}
		// returns current location of pointer
		return tracker;
	}

	/**
	 *  Prints paragraph when a paragraph tag is detected. Searches for nested
	 *	italics and bold tags, and uses a helper method to handle them 
	 *	accordingly. If no nested tags, prints plain text. Also keeps track of
	 *	80 character limit per line.
	 */
	public void renderParagraph() {

		int lastTagIndex = 0;
		int tracker = 0;

		// Next line to start paragraph
		browser.println();

		// Finds end paragraph tag
		for (int a = tokens.length - 1; a > counter; a--) 
			if (tokens[a] != null && tokens[a].toLowerCase().equals("</p>")) 
				lastTagIndex = a;

		// Checks for nested bold or italic tags
		for (int a = counter; a < lastTagIndex; a++) {
			if (tokens[a].toLowerCase().equals("<i>")) {
				a++;
				tracker = processBoldItalic(tracker,a,false);
				a = this.counter;
			}
			// Calls another method if bold tag is detected
			else if (tokens[a].toLowerCase().equals("<b>")) {
				a++;
				tracker = processBoldItalic(tracker,a,true);
				a = this.counter;
			}

			tracker = renderPlainText(tracker, a);

		}
		counter = lastTagIndex + 1;

		browser.println();
		browser.println();

	}

	/**
	 *	Prints text within the preformat tokens as provided. Goes on until end
	 *	preformat token is detected.
	 */
	public void renderPreformat() {

		int lastTagIndex = 0;
		int index = 0;

		// Finds index of corresponding end tag
		for (int a = tokens.length - 1; a > counter; a--) {

			if (tokens[a] != null && tokens[a].toLowerCase().equals("</pre>")) 
				lastTagIndex = a;
		}

		// Prints tags with same format as provided
		for (int a = counter; a < lastTagIndex; a++) {
 
			browser.print(tokens[a]);
			// browser.print("HELLOOO");
			browser.println();
			counter++;
		}

		browser.println();
	}

	/**
	 *	Processes un-nested bold and italic tags, and determines which one to format. Using
	 *	a helper method, prints text with bold or italic formatting to screen.
	 * 	@param tracker 		Location on screen
	 * 	@param counter		Index of the String array of tokens
	 * 	@param bold			true if bold; false otherwise
	 * 	@return 			Changed location on the screen
	 */
	public int processBoldItalic(int tracker, int counter, boolean bold) {
		
		int lastTagIndex = 0;
		// Finds index of corresponding end tag for either bold or italics using
		// bold boolean
		for (int i = tokens.length - 1; i > counter; i--) 
			if (!bold && tokens[i] != null && tokens[i].toLowerCase().equals("</i>")) 
				lastTagIndex = i;
			else if (bold && tokens[i] != null && tokens[i].toLowerCase().equals("</b>")) 
				lastTagIndex = i;
		// Prints bold or italic text
		for (int i = counter; i < lastTagIndex; i++) 
			tracker = printBoldItalic(tracker, i, bold);

		// Increments counter to one past the tag index
		this.counter = lastTagIndex + 1;

		// returns current location of pointer
		return tracker;
	}

	/**
	 *  Prints text with either bold or italic formatting based on processed
	 *	tags to screen. Limits character count to 80 per line as well.
	 *	@param tracker 		Location on screen
	 * 	@param counter		Index of the String array of tokens
	 * 	@param bold			true if bold; false otherwise
	 * 	@return 			Changed location on the screen
	 */
	public int printBoldItalic(int tracker, int counter, boolean bold) {
		
		// Goes to next line if over 80 character limit
		if (tracker + tokens[counter].length() > 80) {
			browser.println();
			tracker = 0;
		}
		// If at beginning of line or at punctuation, do not add space
		if (tracker == 0 || tokens[counter].length() == 1
			&& utils.isPunctuation(tokens[counter].charAt(0))) {
			
			tracker += tokens[counter].length();
			if (bold)
				browser.printBold(tokens[counter]);
			else
				browser.printItalic(tokens[counter]);
		}
		// If not at beginning of line or at punctuation, then add space
		else {
			if (bold)
				browser.printBold(" " + tokens[counter]);
			else
				browser.printItalic(" " + tokens[counter]);
			tracker += tokens[counter].length() + 1;
		}

		// returns current location of pointer
		return tracker;

	}
}