package HRPS;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * A data access class extends from the StoreData abstract class
 *@author Li Feng
 *@version 1.0
 *@since 2018-04-18
*/
public class Registrationdb extends StoreData{
	
	@Override
	/**
	 * Override function from StoreData abstact function. Format the data read from
	 * text file and store into the arraylist
	 * @param filename The name of the text file to save to
	 * @param RegistrationArray The arraylist reference 
	 * @throws IOException
	 */
	public void readClass(String filename, ArrayList RegistrationArray) throws IOException {
		// TODO Auto-generated method stub
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); //for date variable

		// read String from text file
				ArrayList stringArray = (ArrayList)read(filename); 


				for (int i = 0 ; i < stringArray.size() ; i++) {
					Registration temp = new Registration();

						String st = (String)stringArray.get(i);
				
						StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
						temp.status = Integer.parseInt(star.nextToken().trim());
						temp.res_id = star.nextToken().trim();
						temp.guestId = star.nextToken().trim();
						temp.room_id = star.nextToken().trim();
						temp.roomType= star.nextToken().trim();
						temp.NoOfAdult = Integer.parseInt(star.nextToken().trim());	
						temp.NoOfChild = Integer.parseInt(star.nextToken().trim());	
						
						
						try {
							temp.check_in = df.parse(star.nextToken().trim());
							temp.check_out = df.parse(star.nextToken().trim());
						}
						 catch (Exception b)
						{
						b.printStackTrace();
						}
						
						
						RegistrationArray.add(temp);
			
		        }	
	}

	@Override
	/**
	 * Override function from StoreData abstact function. Format the data from arraylist
	 * and store into the text file
	 * @param filename The name of the text file to save to
	 * @param RegistrationArray The arraylist reference 
	 * @throws IOException
	 */
	public void saveClass(String filename, ArrayList RegistrationArray) throws IOException {
		 

		List alw = new ArrayList() ;// to store Professors data
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); //for date variable
		Registration temp = new Registration();
		
		
		 for (int i = 0 ; i < RegistrationArray.size(); i++) {
		StringBuilder builder =  new StringBuilder();
		temp = (Registration) RegistrationArray.get(i);
		builder.append(String.valueOf(temp.status));
		builder.append(SEPARATOR);
		builder.append(temp.res_id.trim());
		builder.append(SEPARATOR);
		builder.append(temp.guestId.trim());
		builder.append(SEPARATOR);
		builder.append(temp.room_id.trim());
		builder.append(SEPARATOR);
		builder.append(temp.roomType.trim());
		builder.append(SEPARATOR);
		builder.append(temp.NoOfAdult);
		builder.append(SEPARATOR);
		builder.append(temp.NoOfChild);
		builder.append(SEPARATOR);
		
		System.out.println("CHECK IN IS IT SAVE AS : " + temp.check_in);
		System.out.println("CHECK IN IS IT FORMAT AS : " + df.format(temp.check_in));
		builder.append(df.format(temp.check_in));
		builder.append(SEPARATOR);
		
		System.out.println("CHECK OUT IS IT SAVE AS : " + temp.check_out);
		System.out.println("CHECK OUT IS IT FORMAT AS : " + df.format(temp.check_out));
		builder.append(df.format(temp.check_out));
		alw.add(builder.toString()) ;
		
		}
		 write(filename,alw);
	}
		
	


}
