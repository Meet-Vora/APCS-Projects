/**
 *	Bricks.java
 *
 *	To compile:	javac -cp .:acm.jar SimpleGraphics.java
 *	To execute:	java -cp .:acm.jar SimpleGraphics
 *
 *	@author	Meet Vora
 *	@since  September 19th, 2018
 */
 
/*	All package classes should be imported before the class definition.
 *	"java.awt.Color" means package java.awt contains class Color. */
import java.awt.Color;

/*	The following libraries are in the acm.jar file. */
import acm.program.GraphicsProgram;
import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.graphics.GPoint;
import acm.graphics.GPolygon;
import acm.graphics.GRect;
import acm.graphics.GRectangle;

public class Bricks extends GraphicsProgram {
	
	/*	All fields and constants should be declared here.
	 *	Only constants (final) are initialized here. */
	
	//Arrays of rectangles for each row from 1-9
	private GRect[] row1;	
	private GRect[] row2;
	private GRect[] row3;
	private GRect[] row4;
	private GRect[] row5;
	private GRect[] row6;
	private GRect[] row7;
	private GRect[] row8;
	private GRect[] row9;

	private GRect row10;	// Last rectabgle of the pyramid

	private final int HEIGHT = 20;	// Constant height of each brick
	private final int WIDTH = 50;	// Constant width of each brick
	private double panelWidth;	// Constant width of each brick
	
	/**	The init() method is executed before the run() method.
	 *	All initialization steps should be performed here.
	 */
	public void init() 
	{
		// Initializes the arrays of rectangles for each row
		row1 = new GRect[10];
		row2 = new GRect[9];
		row3 = new GRect[8];
		row4 = new GRect[7];
		row5 = new GRect[6];
		row6 = new GRect[5];
		row7 = new GRect[4];
		row8 = new GRect[3];
		row9 = new GRect[2];
		// row10 = new GRect();

		panelWidth = getWidth();
	}
	
	/**	The run() method is executed after init().
	 *	The bulk of the program should be performed here.
	 *	Exercise hint: Use one-dimensional arrays for the GOval's and GRect's.
	 */
	public void run() 
	{
		// Changes top-leftmost horizontal coordinate of each brick
		int addWidth = 0;
		double xCoord = 0.0;



		/**	Each for loop adds a certain number of bricks based on their row number
		*	by changing their horizontal coordinates. For example, the for
		*	loop for row1 creates and adds 10 bricks, while the for loop for
		*	row9 only creates and adds 2 bricks.
		*/

		for(int a = 0; a < row1.length; a++)
		{
			xCoord = panelWidth/2 - 5*WIDTH;
			addWidth = 50*a;
			row1[a] = new GRect(xCoord + WIDTH*a, 0, WIDTH, HEIGHT);
			add(row1[a]);
		}

		for(int b = 0; b < row2.length; b++)
		{
			xCoord = panelWidth/2 - 4.5*WIDTH; 
			addWidth = 50*b;
			row2[b] = new GRect(xCoord + WIDTH*b, 20, WIDTH, HEIGHT);
			add(row2[b]);
		}

		for(int c = 0; c < row3.length; c++)
		{
			xCoord = panelWidth/2 - 4*WIDTH;
			addWidth = 50*c;
			row3[c] = new GRect(xCoord + WIDTH*c, 40, WIDTH, HEIGHT);
			add(row3[c]);
		}

		for(int d = 0; d < row4.length; d++)
		{
			xCoord = panelWidth/2 - 3.5*WIDTH;
			addWidth = 50*d;
			row4[d] = new GRect(xCoord + WIDTH*d, 60, WIDTH, HEIGHT);
			add(row4[d]);
		}
		
		for(int e = 0; e < row5.length; e++)
		{
			xCoord = panelWidth/2 - 3*WIDTH;
			addWidth = 50*e;
			row5[e] = new GRect(xCoord + WIDTH*e, 80, WIDTH, HEIGHT);
			add(row5[e]);
		}

		for(int f = 0; f < row6.length; f++)
		{
			xCoord = panelWidth/2 - 2.5*WIDTH;
			addWidth = 50*f;
			row6[f] = new GRect(xCoord + WIDTH*f, 100, WIDTH, HEIGHT);
			add(row6[f]);
		}

		for(int g = 0; g < row7.length; g++)
		{
			xCoord = panelWidth/2 - 2*WIDTH;
			addWidth =50*g;
			row7[g] = new GRect(xCoord + WIDTH*g, 120, WIDTH, HEIGHT);
			add(row7[g]);
		}

		for(int h = 0; h < row8.length; h++)
		{
			xCoord = panelWidth/2 - 1.5*WIDTH;
			addWidth = 50*h;
			row8[h] = new GRect(xCoord + WIDTH*h, 140, WIDTH, HEIGHT);
			add(row8[h]);
		}

		for(int i = 0; i < row9.length; i++)
		{
			xCoord = panelWidth/2 - WIDTH;
			addWidth = 50*i;
			row9[i] = new GRect(xCoord + WIDTH*i, 160, WIDTH, HEIGHT);
			add(row9[i]);
		}

		// Adds the last brick
		xCoord = (panelWidth - WIDTH)/2;
		// addWidth = 50*j;
		row10 = new GRect(xCoord, 180, WIDTH, HEIGHT);
		add(row10);

	}
}