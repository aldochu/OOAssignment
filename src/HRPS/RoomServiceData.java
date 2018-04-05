package HRPS;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import roomserviceapp.RoomService;

public class RoomServiceData {

	// TODO Auto-generated method stub
	
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH-mm-ss"); //for date variable

		// read String from text file
				ArrayList stringArray = (ArrayList)read(filename); 

		        for (int i = 0 ; i < stringArray.size() ; i++) {
		        	
		        	
		        	
						String st = (String)stringArray.get(i);
						// get individual 'fields' of the string separated by SEPARATOR
						StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
						
						RoomService temp = new RoomService();
						temp.RoomNo = Integer.parseInt(star.nextToken().trim());
						temp.orderNumber = Integer.parseInt(star.nextToken().trim());
						try {
						temp.date = df.parse(star.nextToken().trim());	
						}
						catch (Exception b)
						{
							b.printStackTrace();
						}
						temp.foodList.name = star.nextToken().trim();
						temp.remark = star.nextToken().trim();
						temp.status = star.nextToken().trim();
					    ServiceArray.add(temp);
		        }	
		
	}

	@Override
	public void saveClass(String filename, ArrayList ServiceArray) throws IOException {
		// TODO Auto-generated method stub
		List alw = new ArrayList() ;// to store Professors data
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); //for date variable
		RoomService temp = new RoomService();

	    for (int i = 0 ; i < ServiceArray.size(); i++) {
				StringBuilder st =  new StringBuilder() ;
				temp = (RoomService) ServiceArray.get(i);
				st.append(temp.RoomNo);
				st.append(SEPARATOR);
				st.append(temp.orderNumber);
				st.append(SEPARATOR);
				st.append(temp.date);
				st.append(SEPARATOR);
				st.append(temp.foodList.name.trim());
				st.append(SEPARATOR);
				st.append(temp.remark.trim());
				st.append(SEPARATOR);
				st.append(temp.status.trim());
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
