/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment Karel4.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	public void run() {
		while(noBeepersPresent()) {
			// put 1 beeper, then move twice
			putBeeper();
			
			// if at the end and cannot move end the loop
			if (facingEast()) {
				if (frontIsBlocked()) {
					if (leftIsBlocked()) {
						return;
					}
				}
			} else {
				if (frontIsBlocked()) {
					if (rightIsBlocked()) {
						return;
					}
				}
			}
			
			// may have to turn after moving
			moveCheck();
			
			// if at the end and cannot move end the loop
			if (facingEast()) {
				if (frontIsBlocked()) {
					if (leftIsBlocked()) {
						return;
					}
				}
			} else {
				if (frontIsBlocked()) {
					if (rightIsBlocked()) {
						return;
					}
				}
			}
			
			// may have to turn after moving
			moveCheck();
		}
	}
	
	// move & check if clear, turn based on east clear or west clear
	public void moveCheck() {
		if (frontIsClear()) {
			move();
		} else {
			// turn left around if facing east
			if (facingEast()) {
				turnLeft();
				move();
				turnLeft();	
			// turn right around if facing west
			} else {
				turnRight();
				move();
				turnRight();
			}
		}
	}

}