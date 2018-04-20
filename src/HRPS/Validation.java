package HRPS;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * 
This class contains functions that is used frequently by other class and provide method to validate
the data
 *
 */
public class Validation {
	
	/**
	 * A method to convert string to date
	 * @param s the date in string data type
	 * @return the date in Date data type
	 */
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

	/**
	 * a function to compare the number of char in string with the given integer
	 * @param s the string
	 * @param k the integer
	 * @return true if the number of char is the same as the given integer, else false
	 */
	public boolean CompareNoOfChar(String s,int k)
	{
		return (s.length()==k);
	}
	
	/**
	 * This function compare the given date with the current date to check whether the given date
	 * had already passed
	 * @param D the given date to check
	 * @return true or false
	 */
	public boolean CheckExpiry(Date D) //this will check with the current date
	{
		Date CurrentDate = new Date();		
		return(D.compareTo(CurrentDate)>0);

	}
	
	/**
	 * This function compare the given date with the current date in addition to a integer
	 * to add the current date ahead, it is to check whether the given date is it before the newly
	 * calculated date after the addition of days
	 * @param D the given date to check 
	 * @param k the number of days to add into the current date
	 * @return true or false
	 */
	public boolean CheckExpiryAfterNoOfDays(Date D,int k) //this will check D with the current date + k
	{
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, k); // Adding k days
		
		Date currentDatePlusk = c.getTime();
		return(D.compareTo(currentDatePlusk)>0);

	}
	
	/**
	 * this function will check for the number of occupancy rate of the hotel in the past
	 * @param D the date of the past 
	 * @param p the arraylist of payment
	 * @return the occupancy rate
	 */
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
	
	
	/**
	 * This function will compare the whether the date had already pass the current date
	 * @param checkin the date to check
	 * @return true or false
	 */
	public boolean CheckValidCheckInDate(Date checkin) //this will check D with the current date + k
	{
		Date today = new Date();
		
		if(checkin.after(today))
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * This function will compare two dates and check user input
	 * @param checkin the date that the guest checkin
	 * @param checkout the date that the guest checkout
	 * @return true or false
	 */
	public boolean CheckValidCheckOutDate(Date checkin, Date checkout) //this will check D with the current date + k
	{		
		if(checkout.after(checkin))
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * This function check whether there's room vacancy from the reservation list
	 * @param CheckIn the check in date of the guest
	 * @param CheckOut the check out date of the guest
	 * @param R the reference arraylist of reservation
	 * @return the room number of the vacant room
	 */
	public Room CheckRoomVacancyFromReservation(Date CheckIn,Date CheckOut,ArrayList<Reservation> R) //this will check D with the current date + k
	{
		int type = 0;
		int vipCount = 0, deluxeCount = 0, singleCount = 0, doubleCount = 0; 
		Scanner sc = new Scanner(System.in);
		Room hotelRoom =new Room();
		
		System.out.println("Please enter the room type:");
		System.out.println("Single/Double/Deluxe/VIP");
		String roomType = sc.nextLine();
		
		while(!roomType.equalsIgnoreCase("Single") && !roomType.equalsIgnoreCase("Double") && !roomType.equalsIgnoreCase("Deluxe") && !roomType.equalsIgnoreCase("VIP"))
		{
			System.out.println("Error input.Please enter the room type again:");
			System.out.println("Single/Double/Deluxe/VIP");
			roomType = sc.nextLine();
		}
		//48 rooms; 6 floors - level 2 to 7; 8 rooms each floor
		//top floor: 2 VIP, 6 deluxe

		//16 single rooms
		//24 double rooms

		//20 single rooms
		//20 double rooms
		
		//VIP
		
		if(roomType.equalsIgnoreCase("VIP") && vipCount < 2) {
			vipCount++;
			hotelRoom.bedType = "2 King Beds";
//			level = 7;
//			room = vipCount;
			hotelRoom.roomType=Integer.toString(AppData.ROOM_TYPE_VIP);
			type = 1;
//			roomId = String.format("%02d", level) + "-" + String.format("%02d", room);
		}
		
		//Deluxe
		else if(roomType.equalsIgnoreCase("Deluxe") && deluxeCount < 6) {
			deluxeCount++;
				System.out.println("Would you rather 2 Double Beds? Y:Yes N:No");
				String btype = sc.nextLine();
				while(!btype.equalsIgnoreCase("y") && !btype.equalsIgnoreCase("n"))
				{
					System.out.println("Would you rather 2 Double Beds? Y:Yes N:No");
					btype = sc.nextLine();
					
				}
				if(btype.equalsIgnoreCase("y")) 
				{
					hotelRoom.bedType = "2 Double Beds";
				}
				else if(btype.equalsIgnoreCase("n"))
				{
					hotelRoom.bedType = "1 King Bed";
				}
//			level = 7;
//			room = deluxeCount + 2;
				hotelRoom.roomType=Integer.toString(AppData.ROOM_TYPE_DELUXE);
				type = 2;
//			roomId = String.format("%02d", level) + "-" + String.format("%02d", room);
		}
		
		//Double
		else if(roomType.equalsIgnoreCase("Double") && doubleCount < 20) {
			doubleCount++;
				System.out.println("Would you rather 2 Single Beds? Y:Yes N:No");
				String btype = sc.nextLine();
				while(!btype.equalsIgnoreCase("y") && !btype.equalsIgnoreCase("n"))
				{
					System.out.println("Would you rather 2 Double Beds? Y:Yes N:No");
					btype = sc.nextLine();
					
				}
				if(btype.equalsIgnoreCase("y")) 
				{
					hotelRoom.bedType = "2 Double Beds";
				}
				else if(btype.equalsIgnoreCase("n"))
				{
					hotelRoom.bedType = "1 King Bed";
				}
				hotelRoom.roomType=Integer.toString(AppData.ROOM_TYPE_DOUBLE);
				type = 3;
//			level = (doubleCount + singleCount) / 8 + 2;
//			room = (doubleCount + singleCount) % 8;
//			if(room == 0) room = 8;
//			roomId = String.format("%02d", level) + "-" + String.format("%02d", room);
		}
		
		//Single
		else if(roomType.equalsIgnoreCase("Single") && singleCount < 20) {
			singleCount++;
			hotelRoom.roomType=Integer.toString(AppData.ROOM_TYPE_SINGLE);
			type = 4;
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
					
//			return hotelRoom;
		System.out.println(hotelRoom.roomType);
		
		switch(type)
		{
		case 1: //VIP room 07-01 - 07-02
			hotelRoom.roomId = SearchRoomFunction(CheckIn,CheckOut,R,7,1,1,2);
			return hotelRoom;
		case 2:  //Deluxe| room 07-03 - 07-08
			
			hotelRoom.roomId = SearchRoomFunction(CheckIn,CheckOut,R,7,3,1,6);
			return hotelRoom;
			
		case 3:  //Single room 02-01 - 02-08 & 03-01 - 03-08 
			
			hotelRoom.roomId = SearchRoomFunction(CheckIn,CheckOut,R,2,1,2,8);
			return hotelRoom;
			
		case 4:  //Single room 04-01 - 04-08 & 05-01 - 05-08 & 06-01 - 06-08 
			
			hotelRoom.roomId = SearchRoomFunction(CheckIn,CheckOut,R,4,1,3,8);
			return hotelRoom;
			
		}
		

		//if all is occupied
		return null;
	}
	
	/**
	 * This function is used by CheckRoomVacancyFromReservation, to separate the task of searching vacant room
	 * @param checkIn the check in date of guest
	 * @param checkOut the check out date of guest
	 * @param R the reference to the reservation arraylist
	 * @param startingFloor the starting floor of the unique room
	 * @param startingRoom the starting room number of the unique room
	 * @param NoOfFloor the number of floor there is for the unique room
	 * @param NoOfRoomPerFloor the number of room there is per floor for the unique room
	 * @return the vacant room number
	 */
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
