package HRPS;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class PaymentData extends StoreData
{
	public void readClass(String filename, ArrayList PaymentArray) throws IOException
	{
		// TODO Auto-generated method stub
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy"); //for date variable
		// read String from text file
		ArrayList stringArray = (ArrayList)read(filename); 
	    for (int i = 0 ; i < stringArray.size() ; i++)
	    {
	        String st = (String)stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
				
			Payment temp = new Payment();
			temp.paymentId = star.nextToken().trim();
			temp.guestId = star.nextToken().trim();
			temp.GuestName = star.nextToken().trim();
			temp.roomNumber = star.nextToken().trim();
			temp.roomType = star.nextToken().trim();
			temp.bedType = star.nextToken().trim();
			temp.adults = Integer.parseInt(star.nextToken().trim());
			temp.child = Integer.parseInt(star.nextToken().trim());
			try
			{
				temp.checkInDate = df.parse(star.nextToken().trim());
				temp.checkOutDate = df.parse(star.nextToken().trim());
			}
			catch (Exception b)
			{
				 b.printStackTrace();
			}

			temp.duration = Long.parseLong(star.nextToken().trim());
			temp.roomcost = Double.parseDouble((star.nextToken().trim()));
			temp.roomsvc = Double.parseDouble((star.nextToken().trim()));
			temp.roomtax = Double.parseDouble((star.nextToken().trim()));
			temp.tcost = Double.parseDouble(star.nextToken().trim());
			
			try
			{ 
				temp.rDate = df.parse(star.nextToken().trim());
			}
			catch (Exception b)
			{
				 b.printStackTrace();
			}
			PaymentArray.add(temp);
	    }
	}

	@Override
	public void saveClass(String filename, ArrayList paymentArray) throws IOException 
	{
		// TODO Auto-generated method stub
		List alw = new ArrayList() ;// to store Professors data
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); //for date variable
		Payment temp = new Payment();

        for (int i = 0 ; i < paymentArray.size(); i++)
        {
			StringBuilder st =  new StringBuilder() ;
			temp = (Payment) paymentArray.get(i);
			st.append(temp.paymentId.trim());
			st.append(SEPARATOR);
			st.append(temp.guestId.trim());
			st.append(SEPARATOR);
			st.append(temp.GuestName.trim());
			st.append(SEPARATOR);
			st.append(temp.roomNumber.trim());
			st.append(SEPARATOR);
			st.append(temp.roomType.trim());
			st.append(SEPARATOR);
			st.append(temp.bedType.trim());
			st.append(SEPARATOR);
			st.append(temp.adults);
			st.append(SEPARATOR);
			st.append(temp.child);
			st.append(SEPARATOR);
			st.append(df.format(temp.checkInDate));
			st.append(SEPARATOR);
			st.append(df.format(temp.checkOutDate));
			st.append(SEPARATOR);
			st.append(temp.duration);
			st.append(SEPARATOR);
			st.append(temp.roomcost);
			st.append(SEPARATOR);
			st.append(temp.roomtax);
			st.append(SEPARATOR);
			st.append(temp.roomsvc);
			st.append(SEPARATOR);
			st.append(temp.tcost);
			st.append(SEPARATOR);
			st.append(df.format(temp.rDate));
			alw.add(st.toString()) ;
        }
		write(filename,alw);
	}

	@Override
	public void startup() 
	{
		// TODO Auto-generated method stub
		
	}


}
