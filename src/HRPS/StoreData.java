package HRPS;

import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
A data access abstract base class to read and save data into text file
 @author Aldo Chu
 @version 1.0
 @since 2018-04-18
 *
 */
public abstract class StoreData {
	/**
	 * A unique icon to separate the data 
	 */
	public final String SEPARATOR = "|";
	
	
  // sub class override this function, read from file
	/**
	 * An abstract function for the child class to implement, each child class has 
	 * its own unique attribute, therefore the formating of the data function has to be
	 * abstract 
	 * @param filename The name of the text file to read from
	 * @param o The arraylist reference
	 * @throws IOException
	 */
	public abstract void readClass(String filename,ArrayList o) throws IOException;

  // sub class override this function, save to file
	/**
	 * 
	 * @param filename The name of the text file to save to
	 * @param o The arraylist reference
	 * @throws IOException
	 */
	public abstract void saveClass(String filename,ArrayList o) throws IOException;

	
  /**
   * Write the data from the arraylist to the textfile specified
   * @param fileName The name of the text file to save to
   * @param data The arraylist data that had been formatted and save into list
   * @throws IOException
   */
  public void write(String fileName, List data) throws IOException  {
    PrintWriter out = new PrintWriter(new FileWriter(fileName));

    try {
		for (int i =0; i < data.size() ; i++) {
      		out.println((String)data.get(i));
		}
    }
    finally {
      out.close();
    }
  }

  /**
   * Read data from text file
   * @param fileName The name of the text file to read
   * @return return the list data type to the function that calls it
   * @throws IOException
   */
  public List read(String fileName) throws IOException {
	List data = new ArrayList() ;
    Scanner scanner = new Scanner(new FileInputStream(fileName));
    try {
      while (scanner.hasNextLine()){
        data.add(scanner.nextLine());
      }
    }
    finally{
      scanner.close();
    }
    return data;
  }

  
}