package HRPS;
import java.text.SimpleDateFormat;
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
	
}
