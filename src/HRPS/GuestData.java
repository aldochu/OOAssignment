package HRPS;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;


public class GuestData extends StoreData {

	@Override
	public void readClass(String filename, Object[] o) throws IOException {
		// TODO Auto-generated method stub
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); //for date variable

		// read String from text file
				ArrayList stringArray = (ArrayList)read(filename); 


		        for (int i = 0 ; i < stringArray.size() ; i++) {
		        	o[i]=new Guest();
		        	

						String st = (String)stringArray.get(i);
						// get individual 'fields' of the string separated by SEPARATOR
						StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","

						((Guest)o[i]).name = star.nextToken().trim();	
						((Guest)o[i]).ic = star.nextToken().trim();	
						((Guest)o[i]).address = star.nextToken().trim();
						((Guest)o[i]).contactNo = star.nextToken().trim();	
						((Guest)o[i]).nationality = star.nextToken().trim();	
						((Guest)o[i]).gender = Boolean.parseBoolean(star.nextToken().trim());
						((Guest)o[i]).ccdetails.name = star.nextToken().trim();
						((Guest)o[i]).ccdetails.cardNo = star.nextToken().trim();
						((Guest)o[i]).ccdetails.cvv2 = star.nextToken().trim();
						((Guest)o[i]).ccdetails.type = star.nextToken().trim();
						
						try {
							((Guest)o[i]).ccdetails.expiry = df.parse(star.nextToken().trim());
						}
						 catch (Exception b)
						{
						b.printStackTrace();
						}
			
		        }	
		
	}

	@Override
	public void saveClass(String filename, Object[] o) throws IOException {
		// TODO Auto-generated method stub
		List alw = new ArrayList() ;// to store Professors data
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); //for date variable

        for (int i = 0 ; i < o.length ; i++) {
        	if(o[i]!=null)
        	{
				StringBuilder st =  new StringBuilder() ;
				st.append(((Guest)o[i]).name.trim());
				st.append(SEPARATOR);
				st.append(((Guest)o[i]).ic.trim());
				st.append(SEPARATOR);
				st.append(((Guest)o[i]).address.trim());
				st.append(SEPARATOR);
				st.append(((Guest)o[i]).contactNo.trim());
				st.append(SEPARATOR);
				st.append(((Guest)o[i]).nationality.trim());
				st.append(SEPARATOR);
				st.append(((Guest)o[i]).gender);
				st.append(SEPARATOR);
				st.append(((Guest)o[i]).ccdetails.name.trim());
				st.append(SEPARATOR);
				st.append(((Guest)o[i]).ccdetails.cardNo.trim());
				st.append(SEPARATOR);
				st.append(((Guest)o[i]).ccdetails.cvv2.trim());
				st.append(SEPARATOR);
				st.append(((Guest)o[i]).ccdetails.type.trim());
				st.append(SEPARATOR);
				st.append(df.format(((Guest)o[i]).ccdetails.expiry));
				alw.add(st.toString()) ;
				
							
			}
        }
			write(filename,alw);
		
	}

	@Override
	public void startup() {
		// TODO Auto-generated method stub
		
	}

}
