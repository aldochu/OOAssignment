package HRPS;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
A data access class extends from the StoreData abstract class
 @author Daniel
 @version 1.0
 @since 2018-04-18
 *
 */
public class PromoData extends StoreData
{
	/**
	 * Override function from StoreData abstact function. Format the data read from
	 * text file and store into the arraylist
	 * @param filename The name of the text file to save to
	 * @param PromoArray The arraylist reference 
	 * @throws IOException
	 */
	public void readClass(String filename, ArrayList PromoArray) throws IOException
	{
		// TODO Auto-generated method stub
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy"); //for date variable
		// read String from text file
		@SuppressWarnings("rawtypes")
		ArrayList stringArray = (ArrayList)read(filename); 
	    for (int i = 0 ; i < stringArray.size() ; i++)
	    {
	        String st = (String)stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
				
			Promo temp = new Promo();
			temp.pname = star.nextToken().trim();	
			temp.discount = Double.parseDouble(star.nextToken().trim());
		
			try
			{ 
				temp.today = df.parse(star.nextToken().trim());
				temp.expiry = df.parse(star.nextToken().trim());
			}
			catch (Exception b)
			{
				 b.printStackTrace();
			}
			PromoArray.add(temp);
	    }
	}

	@Override
	/**
	 * Override function from StoreData abstact function. Format the data from arraylist
	 * and store into the text file
	 * @param filename The name of the text file to save to
	 * @param promoArray The arraylist reference 
	 * @throws IOException
	 */
	public void saveClass(String filename, ArrayList promoArray) throws IOException 
	{
		// TODO Auto-generated method stub
		List alw = new ArrayList() ;// to store Professors data
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); //for date variable
		Promo temp = new Promo();

        for (int i = 0 ; i < promoArray.size(); i++)
        {
			StringBuilder st =  new StringBuilder() ;
			temp = (Promo) promoArray.get(i);
			st.append(temp.pname.trim());
			st.append(SEPARATOR);
			st.append(temp.discount);
			st.append(SEPARATOR);
			st.append(df.format(temp.today));
			st.append(SEPARATOR);
			st.append(df.format(temp.expiry));
			alw.add(st.toString()) ;
        }
		write(filename,alw);
	}



}
