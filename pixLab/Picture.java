import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture. This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Meet Vora
 * @since  February 5th, 2019
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
      }
    }
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  /*
   * Removes colors green and red from all pixels in picture, leaving only the 
   * color blue.
   */
  public void keepOnlyBlue() {
	  
	Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(0);
        pixelObj.setGreen(0);
      }
    }
  }
  
  /*
   * Invert's the colors of each pixel in the image by subtracting its red,
   * green, and blue values from 255 and applying those colors to each pixel.
   */
  public void negate() {
	Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        // set red, blue, and green values to inverse of their original value
        pixelObj.setRed(255 - pixelObj.getRed());
        pixelObj.setBlue(255 - pixelObj.getBlue());
        pixelObj.setGreen(255 - pixelObj.getGreen());
      }
    }
  }
  
  /*
   * Get the average of the red, green, and blue color values, and then set each
   * of them to the average for each pixel in the image
   */
  public void grayscale() {
	Pixel[][] pixels = this.getPixels2D();
	
	  // get values of blue, red, green, and get avg. Then set color to that color value  
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        // gets average color value of each pixel
		    int avg = (int)pixelObj.getAverage();
        pixelObj.setRed(avg);
        pixelObj.setBlue(avg);
        pixelObj.setGreen(avg);
      }
    }
  }
  
  /** To pixelate by dividing area into size x size
   *  @param size    Side length of square area to pixelate. 
   */ 
  public void pixelate(int size) {
  	Pixel[][] pixels = this.getPixels2D();
  	Pixel[][] newPixels = this.getPixels2D();
  	int height = 0, width = 0;
	

  	for(int a = 0; a < pixels.length; a += size) {
  		for(int b = 0; b < pixels[0].length; b += size) {
  			
        // add the red, blue, and green values of all the pixels in the
        // size x size square
  			int red = 0, blue = 0, green = 0, counter = 0;				
  			for(height = a; height < size + a; height++) {
  				for(width = b; width < size + b; width++) {
  					if(height < pixels.length && width < pixels[0].length) {
  						red += pixels[height][width].getRed();
  						blue += pixels[height][width].getBlue();
  						green += pixels[height][width].getGreen();
  						counter++;
  					}
  				}
  			}
  			
        // set each pixel in the size x size square to the average value of the
        // red, green, and blue values
  			for(height = a; height < size + a; height++) {
  				for(width = b; width < size + b; width++) {
  					if(height < pixels.length && width < pixels[0].length) {
  						newPixels[height][width].setRed(red/counter);
  						newPixels[height][width].setBlue(blue/counter);
  						newPixels[height][width].setGreen(green/counter);
  					}
  				}
  			}
  		}
  	}	
  }

 /** Method that blurs the picture 
  *  @param size    Blur size, greater is more blur 
  *  @return   	 	  Blurred picture 
  */ 
  public Picture blur(int size) {
	  Pixel[][] pixels = this.getPixels2D(); 
    Picture result = new Picture(pixels.length, pixels[0].length); 
    Pixel[][] resultPixels = result.getPixels2D();
   	int height = 0, width = 0;
    
    for(int a = 0; a < pixels.length; a++) {
		  for(int b = 0; b < pixels[0].length; b++) {
			 
        // add the values of the red, green, and blue colors in the size x size
        // square
  			int red = 0, blue = 0, green = 0, counter = 0;	
  			for(height = a-1; height < size + a - 1; height++) {
  				for(width = b-1; width < size + b - 1; width++) {
  					if(height < pixels.length && width < pixels[0].length && height >= 0 & width >= 0) {
  						red += pixels[height][width].getRed();
  						blue += pixels[height][width].getBlue();
  						green += pixels[height][width].getGreen();
  						counter++;
  					}
  				}
  			}
        // set the RGB values at the current pixel in the resultPixel array to the average value.
  			resultPixels[a][b].setRed(red/counter);
  			resultPixels[a][b].setBlue(blue/counter);
  			resultPixels[a][b].setGreen(green/counter);
  		}
  	}
	  return result;
  }
  
  /** Method that enhances a picture by getting average Color around
   * a pixel then applies the following formula:
   *
   * pixelColor <- 2 * currentValue - averageValue
   *
   * size is the area to sample for blur.
   *
   * @param size Larger means more area to average around pixel
   * and longer compute time.
   * @return enhanced picture
   */
  public Picture enhance(int size) {
    Pixel[][] pixels = this.getPixels2D();
    Picture result = new Picture(pixels.length, pixels[0].length);
    Pixel[][] resultPixels = result.getPixels2D();

    int height = 0, width = 0;
    
    for(int a = 0; a < pixels.length; a++) {
      for(int b = 0; b < pixels[0].length; b++) {
        
        // add the values of the red, green, and blue colors in the size x size
        // square
        int red = 0, blue = 0, green = 0, counter = 0;
        int currentRed = pixels[a][b].getRed(), currentBlue = pixels[a][b].getBlue(), currentGreen = pixels[a][b].getGreen(); 
        for(height = a-1; height < size + a - 1; height++) {
          for(width = b-1; width < size + b - 1; width++) {
            if(height < pixels.length && width < pixels[0].length && height >= 0 & width >= 0) {
              red += pixels[height][width].getRed();
              blue += pixels[height][width].getBlue();
              green += pixels[height][width].getGreen();
              counter++;
            }
          }
        }

        // set the RGB values of the current pixel by multiplying them by 2 and
        // subtracting each value by its average color value
        resultPixels[a][b].setRed(2*currentRed - red/counter);
        resultPixels[a][b].setBlue(2*currentBlue - blue/counter);
        resultPixels[a][b].setGreen(2*currentGreen - green/counter);
      }
    } 
    return result;
  }

  /**
   *  Shifts the original picture to the right by a given percent.
   *  @param  percent    Percent of the picture to shift right 
   *  @return            Picture that is shifted right
   */
  public Picture shiftRight(int percent) {
    Pixel[][] pixels = this.getPixels2D();
    Picture result = new Picture(pixels.length, pixels[0].length);
    Pixel[][] resultPixels = result.getPixels2D();

    // number of pixels needed to shift right
    int shift = (int)(pixels[0].length*(percent/100.0));

    for(int a = 0; a < pixels.length; a++) {
      for(int b = 0; b < pixels[0].length; b++) {
        
        int red = 0, blue = 0, green = 0;
        red = pixels[a][b].getRed();
	 	    blue = pixels[a][b].getBlue();
	   	  green = pixels[a][b].getGreen();
        
        // sets the resultPixels at coordinate (a,b+shift) equal to pixels at (a,b) 
        if(b+shift < pixels[0].length) {
    			resultPixels[a][b+shift].setRed(red);
    			resultPixels[a][b+shift].setBlue(blue);
    			resultPixels[a][b+shift].setGreen(green);
  		  }

        // handles wrap-around for the picture
        else {
    			resultPixels[a][(b+shift)%pixels[0].length].setRed(red);
    			resultPixels[a][(b+shift)%pixels[0].length].setBlue(blue);
    			resultPixels[a][(b+shift)%pixels[0].length].setGreen(green);
		    }
	    }
    }
  	return result;
  }
  
  /**
   *  Shifts parts of the picture by a set value to make a stairstep pattern in
   *  the picture.
   *  @param shiftCount   shifts each stair by a given value
   *  @param steps        number of steps the picture will have
   *  @return             stair-stepped picture
   */
  public Picture stairStep(int shiftCount, int steps) {	// fix method
	  Pixel[][] pixels = this.getPixels2D();
    Picture result = new Picture(pixels.length, pixels[0].length);
    Pixel[][] resultPixels = result.getPixels2D();
    
    // height of each stairstep
    int vertShift = pixels.length/steps, counter = 1;
    // amount of shifting for each step
    int horizShift = shiftCount;
    
    for(int a = vertShift; a < pixels.length; a += vertShift) {
      
      for(int row = a; row < a + vertShift; row++) {
        for(int b = 0; b < pixels[0].length; b++) {
          int red = 0, blue = 0, green = 0;
          red = pixels[row][b].getRed();
          blue = pixels[row][b].getBlue();
          green = pixels[row][b].getGreen();

          // if pixel within bounds of picture, shift right by a factor of 
          // shiftCount
          if(b + horizShift*counter < pixels[0].length) {
            resultPixels[row][b+horizShift*counter].setRed(red);
            resultPixels[row][b+horizShift*counter].setBlue(blue);
            resultPixels[row][b+horizShift*counter].setGreen(green);
          }
          // else handle wrap-around and set old pixel color shifted new pixel array
          else {
            resultPixels[row][(b+horizShift*counter)%pixels[0].length].setRed(red);
            resultPixels[row][(b+horizShift*counter)%pixels[0].length].setBlue(blue);
            resultPixels[row][(b+horizShift*counter)%pixels[0].length].setGreen(green);
          }
        }
      }
      counter++;
    }
    return result;
  }  
  
  /**
   *  Turns the picture 90 degrees clockwise.
   *  @return   Rotated picture
   */
  public Picture turn90() {
	  Pixel[][] pixels = this.getPixels2D();
    Picture result = new Picture(pixels[0].length, pixels.length);
    Pixel[][] resultPixels = result.getPixels2D();
    
    for(int a = 0; a < pixels.length; a++) {
      for(int b = 0; b < pixels[0].length; b++) {
    		int red = 0, blue = 0, green = 0;
    		red = pixels[a][b].getRed();
    		blue = pixels[a][b].getBlue();
    		green = pixels[a][b].getGreen();

        // save color of pixels to different location to rotate image
        resultPixels[b][pixels.length-1-a].setRed(red);
    		resultPixels[b][pixels.length-1-a].setBlue(blue);
    		resultPixels[b][pixels.length-1-a].setGreen(green);
	    }
    }
    return result;
  }

  /**
   *  Zoom into the top left quarter of the original picture.
   *  @return Zoomed-in picture
   */
  public Picture zoomUpperLeft() {
    Pixel[][] pixels = this.getPixels2D();
    Picture result = new Picture(pixels.length,pixels[0].length);
    Pixel[][] resultPixels = result.getPixels2D();
    
    for(int a = 0; a < pixels.length/2; a++) {
      for(int b = 0; b < pixels[0].length/2; b++) {
        int red = 0, blue = 0, green = 0; 
        red = pixels[a][b].getRed();
        blue = pixels[a][b].getBlue();
        green = pixels[a][b].getGreen();

        // save each original pixel four times in a 2x2 square in the new array
        for(int c = a*2; c < a*2 + 2; c++) {
          for(int d = b*2; d < b*2 + 2; d++) {
            resultPixels[c][d].setRed(red);
            resultPixels[c][d].setBlue(blue);
            resultPixels[c][d].setGreen(green);
          }
        }
      }
    }
    return result;
  }

  /**
   *  Mirror an image about its long edge, short edge, and bottom-right corner,
   *  and print all attached together
   *  @return mirrored image
   */
  public Picture tileMirror() {
    Pixel[][] pixels = this.getPixels2D();
    Picture result = new Picture(pixels.length,pixels[0].length);
    Pixel[][] resultPixels = result.getPixels2D();

    Picture upLeft = new Picture(pixels.length/2,pixels[0].length/2);
    Pixel[][] upLeftPixels = upLeft.getPixels2D();
    
    // get the smaller version of the actual picture, and place it in the top 
    // left corner of the result 2D array
    for(int a = 0; a < pixels.length; a += 2) {
      for(int b = 0; b < pixels[0].length; b += 2) {

        int red = 0, blue = 0, green = 0, counter = 0;
        for(int height = a; height < a + 2; height++) {
          for(int width = b; width < b + 2; width++){
            if(height < pixels.length && width < pixels[0].length) {
              red += pixels[a][b].getRed();
              blue += pixels[a][b].getBlue();
              green += pixels[a][b].getGreen();
              counter++;
            }
          }
        }
        resultPixels[a/2][b/2].setRed(red/counter);
        resultPixels[a/2][b/2].setBlue(blue/counter);
        resultPixels[a/2][b/2].setGreen(green/counter);

        upLeftPixels[a/2][b/2].setRed(red/counter);
        upLeftPixels[a/2][b/2].setBlue(blue/counter);
        upLeftPixels[a/2][b/2].setGreen(green/counter);
      }
    }


    // mirror the small picture to the top right, bottom right, and bottom left
    // quarters of resultPixels
    for(int a = 0; a < pixels.length/2; a++) {
      for(int b = 0; b < pixels[0].length/2; b++) {
        int red = 0, blue = 0, green = 0;
        red = upLeftPixels[a][b].getRed();
        blue = upLeftPixels[a][b].getBlue();
        green = upLeftPixels[a][b].getGreen();

        // mirror to top right
        resultPixels[a][pixels[0].length-1-b].setRed(red);
        resultPixels[a][pixels[0].length-1-b].setBlue(blue);
        resultPixels[a][pixels[0].length-1-b].setGreen(green);

        // mirror to bottom left
        resultPixels[pixels.length-1-a][b].setRed(red);
        resultPixels[pixels.length-1-a][b].setBlue(blue);
        resultPixels[pixels.length-1-a][b].setGreen(green);

        //mirror to bottom right
        resultPixels[pixels.length-1-a][pixels[0].length-1-b].setRed(red);
        resultPixels[pixels.length-1-a][pixels[0].length-1-b].setBlue(blue);
        resultPixels[pixels.length-1-a][pixels[0].length-1-b].setGreen(green);
      }
    }
    return result;
  }

  /**
   *  Checks if each pixel in the image is at an edge. If true, makes the color
   *  of the pixel black, else makes it white. Returns outline of original image
   *  @param threshold  value to which the difference in the two pixels' color
   *                         values are compared to.
   *  @return           Outlined image 
   */
  public Picture edgeDetectionBelow(int threshold) {
    Pixel[][] pixels = this.getPixels2D();
    Picture result = new Picture(pixels.length,pixels[0].length);
    Pixel[][] resultPixels = result.getPixels2D();

    for(int row = 0; row < pixels.length-1; row++) {
      for(int col = 0; col < pixels[0].length; col++) {
        if (pixels[row][col].colorDistance(pixels[row+1][col].getColor()) > 
            threshold)
          resultPixels[row][col].setColor(Color.BLACK);
        else
          resultPixels[row][col].setColor(Color.WHITE);
      }
    }
    return result;
  }

  /**
   *  Pastes two images with a green-screen background onto another image,
   *  reflecting how real green-screens function.
   *  @param mouse   picture of mouse
   *  @param cat     picture of cat
   *  @return        Superimposed picture
   */
  public Picture greenScreen(Picture mouse, Picture cat) {
    Pixel[][] pixels = this.getPixels2D();
    Pixel[][] catPix = cat.getPixels2D();
    Pixel[][] mousePix = mouse.getPixels2D();
    Picture result = new Picture(pixels.length,pixels[0].length);
    Pixel[][] resultPixels = result.getPixels2D();
    
    // copies entire background image onto resultPixels
    for(int a = 0; a < pixels.length; a++) {
      for(int b = 0; b < pixels[0].length; b++) {
      resultPixels[a][b].setColor(pixels[a][b].getColor());
      }
    }
    
    // removes green screen from mouse picture. Checks if difference between 
    // green and red or blue values  is less than 25. If so, then save the pixel
    int mouseRow = 325, mouseCol = 640;
    for(int c = 0; c < mousePix.length; c++) {
      for(int d = 0; d < mousePix[0].length; d++) {
        if(mousePix[c][d].getGreen() - mousePix[c][d].getRed() < 25 || mousePix[c][d].getGreen() - mousePix[c][d].getBlue() <25)
            resultPixels[mouseRow][mouseCol].setColor(mousePix[c][d].getColor());
        mouseCol++;
      }
      mouseRow++; mouseCol = 640;
    }
    
    // removes green screen from cat picture. Checks if difference between 
    // green and red or blue values  is less than 25. If so, then save the pixel
    int catRow = 230, catCol = 0;
    for(int e = 0; e < catPix.length; e++) {
      for(int f = 0; f < catPix[0].length; f++) {
        if(catPix[e][f].getGreen() - catPix[e][f].getRed() < 25 || catPix[e][f].getGreen() - catPix[e][f].getBlue() < 25)
            resultPixels[catRow][catCol].setColor(catPix[e][f].getColor());
        catCol++;
      }
      catRow++; catCol = 0;
    }
    return result;
  }
} // this } is the end of class Picture, put all new methods before this