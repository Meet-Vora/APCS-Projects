/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	public void run() 
	{
		findMidpoint();
	}

	public void findMidpoint()	// finds the midpoint of the bottom row and leaves a beeper there
	{
		putBeeper();
		while(frontIsClear())
		{
			move();
		}
		putBeeper();
		
		turnLeft();
		turnLeft();
		move();
	
		while(noBeepersPresent())
		{
			layDownBeepers();
		}

		collectBeepers();
		moveWhenNoBeepers();
		move();
		collectBeepers();
		moveWhenNoBeepers();
	}

	public void layDownBeepers()	// lays down all the beepers in the bottom row
	{
		moveWhenNoBeepers();

		turnLeft();
		turnLeft();
		move();
		putBeeper();
		move();
	}

	public void collectBeepers()	// collects the beepers except the middle one after laying them down
	{
		while(beepersPresent())
		{
			while(frontIsClear())
			{
				pickBeeper();
				move();
			}
			pickBeeper();
		}

		turnLeft();
		turnLeft();
	}

	public void moveWhenNoBeepers()	// moves Karel when no beepers present in front of her
	{
		while(noBeepersPresent())
		{
			move();
		}
	}




}


