package HRPS;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Validation {

	public boolean CompareNoOfChar(String s,int k)
	{
		return (s.length()==k);
	}
	
	public boolean CheckExpiry(Date D) //this will check with the current date
	{
		Date CurrentDate = new Date();		
		return(D.compareTo(CurrentDate)>0);

	}
	
	public boolean CheckExpiryAfterNoOfDays(Date D,int k) //this will check D with the current date + k
	{
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, k); // Adding k days
		
		Date currentDatePlusk = c.getTime();
		return(D.compareTo(currentDatePlusk)>0);

	}
	
	public int CheckDateWithin(Date D,ArrayList<Payment> p) //this will check D with the current date + k
	{
		int number = 0;
		
		for(int i=0;i<p.size();i++)
		{
		Payment temp = p.get(i);
		if(D.compareTo(temp.checkInDate)>=0 && D.compareTo(temp.checkOutDate)<=0)
			number++;
		}
		
		return number;

	}
	
}
