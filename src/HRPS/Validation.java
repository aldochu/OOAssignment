package HRPS;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

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
	
	public boolean CheckValidCheckInDate(Date checkin) //this will check D with the current date + k
	{
		Date today = new Date();
		
		if(checkin.after(today))
		{
			return true;
		}
		
		return false;
	}
	
	public boolean CheckValidCheckOutDate(Date checkin, Date checkout) //this will check D with the current date + k
	{		
		if(checkout.after(checkin))
		{
			return true;
		}
		
		return false;
	}
	
	public String CheckRoomVacancyFromReservation(Date CheckIn,Date CheckOut,ArrayList<Reservation> R) //this will check D with the current date + k
	{	
		
		int level, room;
		String roomId, bedType, roomType, type;
		int vipCount = 0, deluxeCount = 0, singleCount = 0, doubleCount = 0; 
		Scanner sc = new Scanner(System.in);
		Room hotelRoom =new Room();
		//48 rooms; 6 floors - level 2 to 7; 8 rooms each floor
		//top floor: 2 VIP, 6 deluxe

		//16 single rooms
		//24 double rooms

		//20 single rooms
		//20 double rooms

		
		System.out.println("Please enter the room type:");
		System.out.println("Single/Double/Deluxe/VIP");
		roomType = sc.nextLine();
		
		//VIP
		if(roomType.equals("VIP") && vipCount < 2) {
			vipCount++;
//			bedType = "2 King Beds";
//			level = 7;
//			room = vipCount;
			type=Integer.toString(AppData.ROOM_TYPE_VIP);
//			roomId = String.format("%02d", level) + "-" + String.format("%02d", room);
		}
		
		//Deluxe
		else if(roomType.equals("Deluxe") && deluxeCount < 6) {
			deluxeCount++;
				System.out.println("Would you rather 2 Double Beds? Y:Yes");
				if(sc.nextLine() == "Y") 
					bedType = "2 Double Beds";
				else 
					bedType = "1 King Bed";
//			level = 7;
//			room = deluxeCount + 2;
				type=Integer.toString(AppData.ROOM_TYPE_DELUXE);
//			roomId = String.format("%02d", level) + "-" + String.format("%02d", room);
		}
		
		//Double
		else if(roomType.equals("Double") && doubleCount < 20) {
			doubleCount++;
				System.out.println("Would you rather 2 Single Beds? Y:Yes");
				if(sc.nextLine() == "Y") 
					bedType = "2 Single Beds";
				else 
					bedType = "1 Double Bed";
				type=Integer.toString(AppData.ROOM_TYPE_DOUBLE);
//			level = (doubleCount + singleCount) / 8 + 2;
//			room = (doubleCount + singleCount) % 8;
//			if(room == 0) room = 8;
//			roomId = String.format("%02d", level) + "-" + String.format("%02d", room);
		}
		
		//Single
		else if(roomType.equals("Single") && singleCount < 20) {
			singleCount++;
			type=Integer.toString(AppData.ROOM_TYPE_SINGLE);
//			bedType = "1 Single Bed";
//			level = (doubleCount + singleCount) / 8 + 2;
//			room = (doubleCount + singleCount) % 8;
//			if(room == 0) room = 8;
//			roomId = String.format("%02d", level) + "-" + String.format("%02d", room);
		}
		else {
			System.out.println("No room available. Sorry");
			return null;
		}
		
//			for(int i = 0; i<hotelRoom.size(); i++) {
//				if(hotelRoom.get(i).roomId.equals(roomId)) {
//					hotelRoom.get(i).levelNo = level;
//					hotelRoom.get(i).roomNo = room;
//					hotelRoom.get(i).bedType = bedType;
//					hotelRoom.get(i).roomType = roomType;
//					
//					System.out.println("Do you wish to have city view? Y: Yes");
//					if(sc.nextLine().equals("Y")) hotelRoom.get(i).cityView = true;
//					System.out.println("Do you wish to have breakfast included? Y: Yes");
//					if(sc.nextLine().equals("Y")) hotelRoom.get(i).breakfast = true;
//					System.out.println("Do you wish to have WiFi enabled? Y: Yes");
//					if(sc.nextLine().equals("Y")) hotelRoom.get(i).wifi = true;
//					System.out.println("Do you wish to have smoking area? Y: Yes");
//					if(sc.nextLine().equals("Y")) hotelRoom.get(i).smoking = true;
//					System.out.println("Your room is " + hotelRoom.get(i).roomId);
//		
//					hotelRoom.get(i).guestIc = guestIC;
//					hotelRoom.get(i).status = "Reserved";
//
//					
//					System.out.println("Your room is " + hotelRoom.get(i).roomId + "Thank you.");
//					
//					
//					
//					
//					return hotelRoom.get(i); //Please return room
					
			
		
		
		switch(type)
		{
		case "1": //VIP room 07-01 - 07-02
			
			return SearchRoomFunction(CheckIn,CheckOut,R,7,1,1,2);
		case "2":  //Deluxe| room 07-03 - 07-08
			
			return SearchRoomFunction(CheckIn,CheckOut,R,7,3,1,6);
			
		case "3":  //Single room 02-01 - 02-08 & 03-01 - 03-08 
			
			return SearchRoomFunction(CheckIn,CheckOut,R,2,1,2,8);
			
		case "4":  //Single room 04-01 - 04-08 & 05-01 - 05-08 & 06-01 - 06-08 
			
			return SearchRoomFunction(CheckIn,CheckOut,R,4,1,3,8);
		}
		

		//if all is occupied
		return null;
	}
	
	public String SearchRoomFunction(Date checkIn,Date checkOut,ArrayList<Reservation> R,int startingFloor,int startingRoom, int NoOfFloor, int NoOfRoomPerFloor)
	{
		//1st check whether there's any reservation for this room
		int floor = startingFloor;
		int room = startingRoom;
		
		boolean roomBook=false; //this is to check whether there's this room in the reservation
		
		for(int t=0;t<NoOfFloor;t++)
		{
			for(int i=0;i<NoOfRoomPerFloor;i++) //this forloop will run through all room
			{
				String roomNo = "0"+floor + "-" + "0"+room;
				for(int k=0;k<R.size();k++)
				{
					
					//1st check whether there's any reservation for this room
					Reservation temp = R.get(k);
					
					if(temp.room_id!=null)
					{
						if(temp.room_id.equals(roomNo))
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
		floor = startingFloor;
		room = startingRoom;
		
		for(int t=0;t<NoOfFloor;t++)
		{
			for(int i=0;i<NoOfRoomPerFloor;i++) //this forloop will run through all room
			{
				String roomNo = "0"+floor + "-" + "0"+room; //format roomno as string
				for(int k=0;k<R.size();k++)
				{
					//1st check whether there's any reservation for this room
					Reservation temp = R.get(k);
					
					if(temp.room_id!=null) //to ensure that the roomtype is not null
					{
						
						if(temp.room_id.equals(roomNo))//if it's the same room
						{
							
							
//							if( !(checkIn.equals(temp.check_in)) ||  !( (checkIn.after(temp.check_in)) && (checkIn.before(temp.check_out)) )
//								|| !( (checkOut.after(temp.check_in)) && (checkOut.before(temp.check_in)) ))
//							{
//
//								return roomNo;
//						}
//							
//							if(   !( checkIn.after(temp.check_in) && checkOut.before(temp.check_out) )  || !( checkIn.equals(temp.check_in) )    
//									|| !( checkIn.before(temp.check_in)  && checkOut.before(temp.check_out) )   ) {
//								return roomNo;
//							}
//							
							if( ( checkIn.equals(temp.check_in) )    ||       (  ( checkIn.before(temp.check_in)  && checkOut.after(temp.check_in))   ) 
									||  (( checkIn.after(temp.check_in) && checkOut.before(temp.check_out) )  )      )
									{
								return null;
							}
							
							else {
								return roomNo;
							}

		
					}		
				}
				room++;
			}	
			floor++;
		}
		
		return null;
	}
		return null;

	}
	}
