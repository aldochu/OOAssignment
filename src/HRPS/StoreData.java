package HRPS;

import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;


public abstract class StoreData {
	
	public final String SEPARATOR = "|";
	
	
  // sub class override this function, read from file
	public abstract void readClass(String filename,Object[] o) throws IOException;

  // sub class override this function, save to file
	public abstract void saveClass(String filename, Object[] o) throws IOException;

	
  /** Write fixed content to the given file. */
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

  /** Read the contents of the given file. */
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

  public abstract void startup();
  
}