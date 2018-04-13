
package HRPS;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class RoomPendingData extends StoreData {
	
	public void readClass(String filename, ArrayList PendingArray) throws IOException
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
				
			RoomPending temp = new RoomPending();
			temp.roomId = star.nextToken().trim();
			temp.guestIc = star.nextToken().trim();
			temp.roomType = star.nextToken().trim();
			temp.bedType = star.nextToken().trim();
			temp.breakfast = Boolean.parseBoolean(star.nextToken().trim());
			temp.wifi = Boolean.parseBoolean(star.nextToken().trim());
			temp.cityView = Boolean.parseBoolean(star.nextToken().trim());
			temp.smoking = Boolean.parseBoolean(star.nextToken().trim());
			try
			{
				temp.checkIn = df.parse(star.nextToken().trim());
				temp.checkOut = df.parse(star.nextToken().trim());
			}
			catch (Exception b)
			{
				 b.printStackTrace();
			}
			PendingArray.add(temp);
	    }
	}

	@Override
	public void saveClass(String filename, ArrayList PendingArray) throws IOException 
	{
		// TODO Auto-generated method stub
		List alw = new ArrayList() ;// to store Professors data
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); //for date variable
		RoomPending temp = new RoomPending();

        for (int i = 0 ; i < PendingArray.size(); i++)
        {
			StringBuilder st =  new StringBuilder() ;
			temp = (RoomPending) PendingArray.get(i);
			st.append(temp.roomId.trim());
			st.append(SEPARATOR);
			st.append(temp.guestIc.trim());
			st.append(SEPARATOR);
			st.append(temp.roomType.trim());
			st.append(SEPARATOR);
			st.append(temp.bedType.trim());
			st.append(SEPARATOR);
			st.append(temp.breakfast);
			st.append(SEPARATOR);
			st.append(temp.wifi);
			st.append(SEPARATOR);
			st.append(temp.cityView);
			st.append(SEPARATOR);
			st.append(temp.smoking);
			st.append(SEPARATOR);
			st.append(df.format(temp.checkIn));
			st.append(SEPARATOR);
			st.append(df.format(temp.checkOut));
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