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
 @author Aldo Chu
 @version 1.0
 @since 2018-04-18
 *
 */
public class GuestData extends StoreData {

	/**
	 * Override function from StoreData abstact function. Format the data read from
	 * text file and store into the arraylist
	 * @param filename The name of the text file to save to
	 * @param GuestArray The arraylist reference 
	 * @throws IOException
	 */
	public void readClass(String filename, ArrayList GuestArray) throws IOException {
		// TODO Auto-generated method stub
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); //for date variable

		// read String from text file
				ArrayList stringArray = (ArrayList)read(filename); 
				
				


		        for (int i = 0 ; i < stringArray.size() ; i++) {
		        	
		        	
		        	
						String st = (String)stringArray.get(i);
						// get individual 'fields' of the string separated by SEPARATOR
						StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
						
						Guest temp = new Guest();

						temp.name = star.nextToken().trim();	
						temp.ic = star.nextToken().trim();	
						temp.address = star.nextToken().trim();
						temp.contactNo = star.nextToken().trim();	
						temp.nationality = star.nextToken().trim();	
						temp.gender = Boolean.parseBoolean(star.nextToken().trim());
						String checknull = new String();
						checknull = star.nextToken().trim();
						if(!checknull.equals("null"))
						{
						temp.ccdetails.name = checknull;
						temp.ccdetails.cardNo = star.nextToken().trim();
						temp.ccdetails.cvv2 = star.nextToken().trim();
						temp.ccdetails.type = star.nextToken().trim();
						
						
						try {
							temp.ccdetails.expiry = df.parse(star.nextToken().trim());
						}
						 catch (Exception b)
						{
						b.printStackTrace();
						}
						}
						else
							temp.ccdetails = null;

						GuestArray.add(temp);
		        }	
		
	}

	@Override
	/**
	 * Override function from StoreData abstact function. Format the data from arraylist
	 * and store into the text file
	 * @param filename The name of the text file to save to
	 * @param GuestArray The arraylist reference 
	 * @throws IOException
	 */
	public void saveClass(String filename, ArrayList GuestArray) throws IOException {
		// TODO Auto-generated method stub
		List alw = new ArrayList() ;// to store Professors data
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); //for date variable
		Guest temp = new Guest();

        for (int i = 0 ; i < GuestArray.size(); i++) {
				StringBuilder st =  new StringBuilder() ;
				temp = (Guest) GuestArray.get(i);
				st.append(temp.name.trim());
				st.append(SEPARATOR);
				st.append(temp.ic.trim());
				st.append(SEPARATOR);
				st.append(temp.address.trim());
				st.append(SEPARATOR);
				st.append(temp.contactNo.trim());
				st.append(SEPARATOR);
				st.append(temp.nationality.trim());
				st.append(SEPARATOR);
				st.append(temp.gender);
				st.append(SEPARATOR);
				if(temp.ccdetails == null)
					st.append("null");
				else
				{
				st.append(temp.ccdetails.name.trim());
				st.append(SEPARATOR);
				st.append(temp.ccdetails.cardNo.trim());
				st.append(SEPARATOR);
				st.append(temp.ccdetails.cvv2.trim());
				st.append(SEPARATOR);
				st.append(temp.ccdetails.type.trim());
				st.append(SEPARATOR);
				st.append(df.format(temp.ccdetails.expiry));
				}
				alw.add(st.toString()) ;
        }
			write(filename,alw);
		
	}




}
