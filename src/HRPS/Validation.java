package HRPS;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Validation {
	
	public Date CovertToDate(String s)//this function take in string and return date
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		 try {
			 return df.parse(s);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	}

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
	
	public String CheckDateWithinWithRoomNo(Date D,ArrayList<Reservation> R, String type) //this will check D with the current date + k
	{	
		
		
		
		switch(type)
		{
		case "1": //VIP room 07-01 - 07-02
			
			//1st check whether there's any reservation for this room
			int floor = 7;
			int room = 1;
			
			int totalNoOfRoom = 2;
			boolean roomBook=false; //this is to check whether there's this room in the reservation
			
			for(int i=0;i<totalNoOfRoom;i++) //this forloop will run through all room
			{
				String roomNo = "0"+floor + "-" + "0"+room;
				for(int k=0;k<R.size();k++)
				{
					
					//1st check whether there's any reservation for this room
					Reservation temp = R.get(k);
					
					if(temp.bedType!=null)
					{
						if(temp.bedType.equals(roomNo))
						{
							roomBook=true; //this room exist
						}
					}
					
				}
				
				if(roomBook==true)
				{
				room++;
				roomBook=false; //reset counter
				}
				else
					return roomNo; //there's no this room in reservation so can book
			}
			
			
			
			//2nd if the room exist in reservation, then check their date
			floor = 7;
			room = 1;
			
			totalNoOfRoom = 2;
			
			for(int i=0;i<totalNoOfRoom;i++) //this forloop will run through all room
			{
				String roomNo = "0"+floor + "-" + "0"+room; //format roomno as string
				for(int k=0;k<R.size();k++)
				{
					//1st check whether there's any reservation for this room
					Reservation temp = R.get(k);
					
					if(temp.bedType!=null) //to ensure that the roomtype is not null
					{
						
						if(temp.bedType.equals(roomNo))//if it's the same room
						{
							if(!(D.compareTo(temp.check_in)>=0 && D.compareTo(temp.check_out)<0))
							{
								return roomNo;
							
							}
						}
			
					}		
				}
				room++;
			}			
			break;	
			
			
		case "2":  //Deluxe| room 07-03 - 07-08
			
			//1st check whether there's any reservation for this room
			floor = 7;
			room = 3;
			
			totalNoOfRoom = 6;
			roomBook=false; //this is to check whether there's this room in the reservation
			
			for(int i=0;i<totalNoOfRoom;i++) //this forloop will run through all room
			{
				String roomNo = "0"+floor + "-" + "0"+room;
				for(int k=0;k<R.size();k++)
				{
					
					//1st check whether there's any reservation for this room
					Reservation temp = R.get(k);
					
					if(temp.bedType!=null)
					{
						if(temp.bedType.equals(roomNo))
						{
							roomBook=true; //this room exist
						}
					}
					
				}
				
				if(roomBook==true)
				{
				room++;
				roomBook=false; //reset counter
				}
				else
					return roomNo; //there's no this room in reservation so can book
			}
			
			
			
			//2nd if the room exist in reservation, then check their date
			floor = 7;
			room = 3;
			
			totalNoOfRoom = 6;
			
			for(int i=0;i<totalNoOfRoom;i++) //this forloop will run through all room
			{
				String roomNo = "0"+floor + "-" + "0"+room; //format roomno as string
				for(int k=0;k<R.size();k++)
				{
					//1st check whether there's any reservation for this room
					Reservation temp = R.get(k);
					
					if(temp.bedType!=null) //to ensure that the roomtype is not null
					{
						
						if(temp.bedType.equals(roomNo))//if it's the same room
						{
							if(!(D.compareTo(temp.check_in)>=0 && D.compareTo(temp.check_out)<0))
							{
								return roomNo;
							
							}
						}
			
					}		
				}
				room++;
			}			
			break;
			
		case "3":  //Single room 02-01 - 02-08 & 03-01 - 03-08 
			
			//1st check whether there's any reservation for this room
			floor = 2;
			room = 1;
			
			int totalNoOffloor = 2;
			totalNoOfRoom = 8;
			roomBook=false; //this is to check whether there's this room in the reservation
			
			for(int t=0;t<totalNoOffloor;t++)
			{
				for(int i=0;i<totalNoOfRoom;i++) //this forloop will run through all room
				{
					String roomNo = "0"+floor + "-" + "0"+room;
					for(int k=0;k<R.size();k++)
					{
						
						//1st check whether there's any reservation for this room
						Reservation temp = R.get(k);
						
						if(temp.bedType!=null)
						{
							if(temp.bedType.equals(roomNo))
							{
								roomBook=true; //this room exist
							}
						}
						
					}
					
					if(roomBook==true)
					{
					room++;
					roomBook=false; //reset counter
					}
					else
						return roomNo; //there's no this room in reservation so can book
				}
				floor++;
			}
			
			
			
			//2nd if the room exist in reservation, then check their date
			floor = 2;
			room = 1;
			
			totalNoOffloor = 2;
			totalNoOfRoom = 8;
			for(int t=0;t<totalNoOffloor;t++)
			{
				for(int i=0;i<totalNoOfRoom;i++) //this forloop will run through all room
				{
					String roomNo = "0"+floor + "-" + "0"+room; //format roomno as string
					for(int k=0;k<R.size();k++)
					{
						//1st check whether there's any reservation for this room
						Reservation temp = R.get(k);
						
						if(temp.bedType!=null) //to ensure that the roomtype is not null
						{
							
							if(temp.bedType.equals(roomNo))//if it's the same room
							{
								if(!(D.compareTo(temp.check_in)>=0 && D.compareTo(temp.check_out)<0))
								{
									return roomNo;
								
								}
							}
				
						}		
					}
					room++;
				}	
				floor++;
			}
			break;
			
		case "4":  //Single room 04-01 - 04-08 & 05-01 - 05-08 & 06-01 - 06-08 
			
			//1st check whether there's any reservation for this room
			floor = 4;
			room = 1;
			
			totalNoOffloor = 3;
			totalNoOfRoom = 8;
			roomBook=false; //this is to check whether there's this room in the reservation
			
			for(int t=0;t<totalNoOffloor;t++)
			{
				for(int i=0;i<totalNoOfRoom;i++) //this forloop will run through all room
				{
					String roomNo = "0"+floor + "-" + "0"+room;
					for(int k=0;k<R.size();k++)
					{
						
						//1st check whether there's any reservation for this room
						Reservation temp = R.get(k);
						
						if(temp.bedType!=null)
						{
							if(temp.bedType.equals(roomNo))
							{
								roomBook=true; //this room exist
							}
						}
						
					}
					
					if(roomBook==true)
					{
					room++;
					roomBook=false; //reset counter
					}
					else
						return roomNo; //there's no this room in reservation so can book
				}
				floor++;
			}
			
			
			
			//2nd if the room exist in reservation, then check their date
			floor = 4;
			room = 1;
			
			totalNoOffloor = 2;
			totalNoOfRoom = 8;
			for(int t=0;t<totalNoOffloor;t++)
			{
				for(int i=0;i<totalNoOfRoom;i++) //this forloop will run through all room
				{
					String roomNo = "0"+floor + "-" + "0"+room; //format roomno as string
					for(int k=0;k<R.size();k++)
					{
						//1st check whether there's any reservation for this room
						Reservation temp = R.get(k);
						
						if(temp.bedType!=null) //to ensure that the roomtype is not null
						{
							
							if(temp.bedType.equals(roomNo))//if it's the same room
							{
								if(!(D.compareTo(temp.check_in)>=0 && D.compareTo(temp.check_out)<0))
								{
									return roomNo;
								
								}
							}
				
						}		
					}
					room++;
				}	
				floor++;
			}
			break;
			
		}
		

		//if all is occupied
		return null;
	}
	
}
