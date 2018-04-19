package HRPS;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.StringTokenizer;
public class RoomServiceData extends StoreData{
	SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH-mm-ss"); //for date variable
    public void readClass(String filename, ArrayList ServiceArray) throws IOException {
	// TODO Auto-generated method stub
	// read String from text file
			ArrayList stringArray = (ArrayList)read(filename); 

	        for (int i = 0 ; i < stringArray.size() ; i++) {
	        	
	        	
	        	
					String st = (String)stringArray.get(i);
					// get individual 'fields' of the string separated by SEPARATOR
					StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
					
					RoomService temp = new RoomService();
					temp.RoomNo = star.nextToken().trim();
					temp.orderNumber = Integer.parseInt(star.nextToken().trim());
					try {
					temp.date = df.parse(star.nextToken().trim());	
					}
					catch (Exception b)
					{
						b.printStackTrace();
					}	
					String myfoodlist = star.nextToken().trim();
					StringTokenizer star2 = new StringTokenizer(myfoodlist , "*");
					temp.remark = star.nextToken().trim();
					temp.status = star.nextToken().trim();
					temp.paid = Boolean.parseBoolean(star.nextToken().trim());
					Food myfoodList = new Food();
					while(star2.hasMoreTokens())
					{
						String food = star2.nextToken().trim();
						double price = Double.parseDouble(star2.nextToken().trim());
						myfoodList.name = food;
						myfoodList.price = price;
						temp.foodList.add(myfoodList);
					}
					
				    ServiceArray.add(temp);
	        }	
	
}

@Override
   public void saveClass(String filename, ArrayList ServiceArray) throws IOException {
	// TODO Auto-generated method stub
	List alw = new ArrayList() ;// to store Professors data
	RoomService temp = new RoomService();

    for (int i = 0 ; i < ServiceArray.size(); i++) {
			StringBuilder st =  new StringBuilder() ;
			temp = (RoomService) ServiceArray.get(i);
			st.append(temp.RoomNo.trim());
			st.append(SEPARATOR);
			st.append(temp.orderNumber);
			st.append(SEPARATOR);
			st.append(df.format(temp.date));
			st.append(SEPARATOR);
			for(int j=0;j<temp.foodList.size();j++)
			{
			st.append(temp.foodList.get(j).name.trim());
			st.append("*");
			st.append(temp.foodList.get(j).price);
			st.append("*");
			}
			st.append(SEPARATOR);
			st.append(temp.remark.trim());
			st.append(SEPARATOR);
			st.append(temp.status.trim());
			st.append(SEPARATOR);
			st.append(temp.paid);
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