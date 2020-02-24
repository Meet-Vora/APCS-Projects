/**
 *	Target.java
 *
 *	To compile:	javac -cp .:acm.jar SimpleGraphics.java
 *	To execute:	java -cp .:acm.jar SimpleGraphics
 *
 *	@author	Meet Vora
 *	@since	September 18th, 2018
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

public class Target extends GraphicsProgram {
	
	/*	All fields and constants should be declared here.
	 *	Only constants (final) are initialized here. */
	 
	private GOval[] circles;			// Array to store all circles 
	private final double RADIUS = 25;
	private double panelWidth;  		// Constant radius of circles drawn
	private double panelHeight;  		// Constant radius of circles drawn
	
	/**	The init() method is executed before the run() method.
	 *	All initialization steps should be performed here.
	 */
	public void init() 
	{
		circles = new GOval[5];			// Initializes array of circles
		panelWidth = getWidth();		
		panelHeight = getHeight();		
	}
	
	/**	The run() method is executed after init().
	 *	The bulk of the program should be performed here.
	 *	Exercise hint: Use one-dimensional arrays for the GOval's and GRect's.
	 */
	public void run()
	{
		// shifts the top-leftmost horizontal coordinate of each circle
		// double addRadius = 37.5;
		double xCoord = 0.0;
		double yCoord = 0.0;

		
		/** The for-loop creates each circle and changes its dimensions after
		 *	each loop. Adds each circle to the panel and alternates its color
		 *	based on which circle is created.
		 */

		for(int a = 0; a < circles.length; a++)
		{
			xCoord = panelWidth/2 - ((18-3*a)*RADIUS)/2;
			yCoord = panelHeight - ((18-3*a)*RADIUS)/2;
			
			circles[a] = new GOval(xCoord, yCoord, (18-3*a)*RADIUS, (18-3*a)*RADIUS);
			circles[a].setFilled(true);
			
			if(a==0)
				circles[a].setFillColor(Color.RED);
			else if(a%2 == 1)
				circles[a].setFillColor(Color.WHITE);
			else
				circles[a].setFillColor(Color.RED);
				
			add(circles[a]);
		}

		System.out.println(getHeight());
		System.out.println(xCoord + 0.5);
		System.out.println(yCoord - 0.5);

	}

}