/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	public void run() 
	{
		processMethods();
	}
	
	public void processMethods()
	{
		turnLeft();

		for(int a = 0; a<4; a++)
		{
			checkFirstRow();
			climbUpColumn();
			climbDownColumn();
			changeColumns();
		}

		turnRight();
	}

	public void checkFirstRow()
	{
		if(noBeepersPresent())
		{
			putBeeper();
		}
	}

	public void climbUpColumn()
	{
		while(frontIsClear())
		{
			putBeeperIfClear();
		}
	}		

	public void climbDownColumn()
	{
		if(frontIsBlocked())
		{
			turnLeft();
			turnLeft();
			
			while(frontIsClear())
			{
				move();
			}
			
			turnLeft();
		}
	}


	public void changeColumns()
	{
		if(frontIsClear())
		{
			for(int b = 0; b < 4; b++)
			{
				move();
			}
		}
		turnLeft();
	}

	public void putBeeperIfClear()
	{
		if(beepersPresent())
			move();

		if(noBeepersPresent())
		{
			putBeeper();
			if(frontIsClear())
				move();
		}
	}
}