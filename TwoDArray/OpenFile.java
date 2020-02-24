import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
/**
 * Utilities for opening a text file.  The text file
 * can be opened and read from, or the file can be
 * opened (created) and written to.
 * @author   Ronald Chan
 * @version  1.0
 * @since    8/22/2017
 */
public class OpenFile
{
	public static void main(String [] args)
	{
		Scanner infile = OpenFile.openToRead("g.txt");
		PrintWriter outfile = OpenFile.openToWrite("g2.txt");
		String temp = null;
		while(infile.hasNext())
		{
			temp = infile.nextLine();
			System.out.println(temp);
			outfile.println(temp);
		}
		infile.close();
		outfile.close();
	}
	
	/**
	 * Opens a file for reading.
	 *
	 * @param filestring   The name of the file to be opened.
	 * @return             A Scanner instance of the file to be opened.
	 */
	public static Scanner openToRead (String fileString)
	{		
		Scanner fromFile=null;
		File name=new File(fileString);
		try
		{
			fromFile=new Scanner(name);
		}
		catch (FileNotFoundException e)
		{
			System.out.println("\n\n\nSorry, but no such file has been found.\n\n");
			System.exit(1);
		}
		return fromFile;
	}
	
	/**
	 * Opens a file for writing.
	 *
	 * @param filestring   The name of the file to be opened (created).
	 * @return             A PrintWriter instance of the file to be opened (created).
	 */
	public static PrintWriter openToWrite (String filestring)
	{
		PrintWriter toFile=null;
		try
		{
			toFile=new PrintWriter(filestring);
		}
		catch (Exception e)
		{
			System.out.println("\n\n\nSorry, file could not be created.\n\n");
			System.exit(2);
		}
		return toFile;
	}
}
