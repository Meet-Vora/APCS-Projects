/**
 *	FirstAssignment.java
 *	Display a brief description of your summer vacation on the screen.
 *
 *	To compile:	javac -cp .:acm.jar FirstAssignment.java
 *	To execute:	java -cp .:acm.jar FirstAssignment
 *
 *	@author	Meet Vora
 *	@since	8/23/18
 */
import java.awt.Font;

import acm.program.GraphicsProgram;
import acm.graphics.GLabel;

public class FirstAssignment extends GraphicsProgram {
    
    public void run() 
    {
    	//	The font to be used
    	Font f = new Font("Serif", Font.BOLD, 18);
    	
    	//	Line 1
    	GLabel s1 = new GLabel("What I did on my summer vacation ...", 10, 20);
    	s1.setFont(f);
    	add(s1);
    	    	
    	//	Continue adding lines until you have 12 to 15 lines
    	
    	GLabel s2 = new GLabel("I went to Angel Island near San Francisco for two days with my dad and his friend,", 10, 40);
    	s2.setFont(f);
    	add(s2);
		
    	GLabel s3 = new GLabel("Bhargav. The trip began with my dad and I driving to Bhargav's apartment", 10, 60);
    	s3.setFont(f);
    	add(s3);
		
    	GLabel s4 = new GLabel("in San Francisco. We went to his place and stayed there overnight to catch the ", 10, 80);
    	s4.setFont(f);
    	add(s4);
		
    	GLabel s5 = new GLabel("ferry to Angel Island early the next morning. We woke up at 7:00 AM, got ready, ", 10, 100);
    	s5.setFont(f);
    	add(s5);
		
    	GLabel s6 = new GLabel("and prepared some food for a picnic. After that, we drove out to Pier 41, parked ", 10, 120);
    	s6.setFont(f);
    	add(s6);
		
        GLabel s7 = new GLabel("our car, and boarded the ferry. On the way to Angel Island, we passed the infamous ", 10, 140);
        s7.setFont(f);
        add(s7);
        
        GLabel s8 = new GLabel("Alcatraz prison. Once we got to Angel island, we immediately set out on a trail ", 10, 160);
        s8.setFont(f);
        add(s8);
        
        GLabel s9 = new GLabel("that would take us to the very top of the mountain. Along the way, we passed ", 10, 180);
        s9.setFont(f);
        add(s9);
        
        GLabel s10 = new GLabel("some beautiful scenery of the bay, the Golden Gate Bridge, and the cityscape ", 10, 200);
        s10.setFont(f);
        add(s10);
        
        GLabel s11 = new GLabel("of San Francisco. Unfortunately for us, once we reached the top, the sky became", 10, 220);
        s11.setFont(f);
        add(s11);
       
        GLabel s12 = new GLabel("very cloudy, which inhibited our 360 degree view of the bay. A little bit ", 10, 240);
        s12.setFont(f);
        add(s12);
        
        GLabel s13 = new GLabel("disappointed but still happy with the trip, we descended down the mountain ", 10, 260);
        s13.setFont(f);
        add(s13);
        
        GLabel s14 = new GLabel("and found our way back to the ferry. We took it back to Pier 41, dropped off ", 10, 280);
        s14.setFont(f);
        add(s14);
        
        GLabel s15 = new GLabel("Bhargav, and made our way home after a successful but tiring trip.", 10, 300);
        s15.setFont(f);
        add(s15);
		
    }
    
}