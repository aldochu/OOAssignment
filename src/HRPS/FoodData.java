package HRPS;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * 
A data access class extends from the StoreData abstract class
 @author Kevin Jonathan
 @version 1.0
 @since 2018-04-20
 *
 */

public class FoodData extends StoreData{
	/**
	 * Override function from StoreData abstact function. Format the data read from
	 * text file and store into the arraylist
	 * @param filename The name of the text file to save to
	 * @param FoodArray The arraylist reference 
	 * @throws IOException
	 */
	public void readClass(String filename, ArrayList FoodArray) throws IOException {
		// TODO Auto-generated method stub
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); //for date variable

		// read String from text file
				ArrayList stringArray = (ArrayList)read(filename); 
				
				


		        for (int i = 0 ; i < stringArray.size() ; i++) {
		        	
		        	
		        	
						String st = (String)stringArray.get(i);
						// get individual 'fields' of the string separated by SEPARATOR
						StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
						
						Food temp = new Food();

						temp.name = star.nextToken().trim();	
						temp.description = star.nextToken().trim();	
						temp.price = Double.parseDouble(star.nextToken().trim());
						FoodArray.add(temp);
		        }	
		
	}
	/**
	 * Override function from StoreData abstact function. Format the data from arraylist
	 * and store into the text file
	 * @param filename The name of the text file to save to
	 * @param FoodArray The arraylist reference 
	 * @throws IOException
	 */
	@Override
	public void saveClass(String filename, ArrayList FoodArray) throws IOException {
		// TODO Auto-generated method stub
		List alw = new ArrayList() ;// to store Professors data
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); //for date variable
		Food temp = new Food();

        for (int i = 0 ; i < FoodArray.size(); i++) {
				StringBuilder st =  new StringBuilder() ;
				temp = (Food) FoodArray.get(i);
				st.append(temp.name.trim());
				st.append(SEPARATOR);
				st.append(temp.description.trim());
				st.append(SEPARATOR);
				st.append(temp.price);
				alw.add(st.toString()) ;
        }
			write(filename,alw);
		
	}

}
