package HRPS;


import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import HRPS.Guest;
import HRPS.GuestApp;
import HRPS.GuestData;

/**
 * 
This class is the manager class of Reservation, it contains all the functional logic required by the system
 @author Li Feng
 @version 1.0
 @since 2018-04-18
 *
 */
		public class ReservationApp {
		private static final TimeUnit MILLISECONDS = null;
		private static final int NoOfFloor = 0;
		private static final int NoOfRoomPerFloor = 0;
		GuestApp guestController = new GuestApp();
		RoomApp roomController = new RoomApp();
		
		/**
		 * An arraylist of guest data type to store and manipulate data
		 */
		private static ArrayList<Reservation> reserve = new ArrayList<Reservation>(); 
		private static Reservationdb db = new Reservationdb();
		private Room rm;
		Scanner sc = new Scanner(System.in);
		
		///////////////////////////////////Load data from file////////////////////////////////////////
		/**
		 * Default constructor, using the data access class to store data from text file to the Guest arraylist
		 */
		public ReservationApp()
		{
		try {
		db.readClass("reservation.txt", reserve); //to read data from files
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		}
		
		private String tem;
		private String temRoom;
		
		/**
		 * This function checks if the guest is null
		 * @param guest to check if null
		 * @return true if guest exists
		 */
		public boolean getuser(Guest guest)
		{
			if(guest==null) {
				return false;
			}
			else {
			return true;
			}
		}
		
		/**
		 * This function checks if the roomNo is valid
		 * @param rmTemp to check if room is null
		 * @return true and updates rm to rmTemp if room is valid,
		 * false is room is null 
		 */
		public boolean checkRoom(Room rmTemp)
		{
			if(rmTemp == null)
			{
				return false;
			}
			else
			{
				rm = rmTemp;
				return true;
			}
		}
		
		/**
		 * This function creates a new reservation. It will ask for user input for
		 * check in and check out date. It will then call the
		 * data access class function upon creation to update the text file.
		 * @param guestId the guest IC
		 */
		public void createRes(String guestId)//pass by reference
		{
			Room rm = new Room();
			Reservation res = new Reservation();
			Validation v =new Validation();
			
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			
			Date d;
			String temp;
			String forDate="";
		
			res.guestId=guestId;
			
			try {
				
				do {
					System.out.println("Please enter the check in date MM/dd/yyyy");
					forDate=sc.nextLine();
					if(!forDate.equals("")) {
						d = df.parse(forDate);
					}
					else {
						forDate=sc.nextLine();
						d = df.parse(forDate);
					}
					if(v.CheckValidCheckInDate(d)==false) {
						System.out.println("Invalid checkin date,please enter again!");
					}
				}
				while(v.CheckValidCheckInDate(d)==false);
					
				res.check_in = d;	
				
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			try {
				
				do {
					System.out.println("Please enter the check out date MM/dd/yyyy");
					forDate=sc.nextLine();
					if(!forDate.equals("")) {
						d = df.parse(forDate);
					}
					else {
						forDate=sc.nextLine();
						d = df.parse(forDate);
					}
				}
				while(v.CheckValidCheckOutDate(res.check_in,d)==false);
				
				
				res.check_out = d;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			rm = v.CheckRoomVacancyFromReservation(res.check_in,res.check_out, reserve);

			res.roomType = rm.roomType;
			if(rm.roomId != null){
				System.out.println("Reservation is confirmed");
				
			res.status= AppData.RES_STATUS_CONFIRMED;
			res.room_id = rm.roomId;
			}
			else if(rm.roomId == null){			

				System.out.println("Your reservation is in waitlist");
				res.status=AppData.RES_STATUS_WAITLIST;
				res.room_id="waitlist";
			}
			
			
			System.out.println("Reservation room id "+res.room_id);

			
			System.out.println("Please enter the number of adults:");
			res.NoOfAdult=sc.nextInt();
			
			System.out.println("Please enter the number of children:");
			res.NoOfChild = sc.nextInt();
			
			
			res.res_id=UUID.randomUUID().toString();
			
			reserve.add(res);
			
			System.out.println("Reservation created successfully!");
			printResByResId(res.res_id);
			
			try {
				db.saveClass("reservation.txt", reserve);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //to read data from files
			
			System.out.println("Reservation created successfully!");


		}
		
		/**
		 * This function updates an existing reservation. It prompts the user to
		 * input reservation number. When found, it will call updateReservation to update the details 
		 * and call the data access class function upon updating the reservation to update the text file
		 * @return 1 on success, 0 on failure
		 */
			public int updateRes()
			{
				
				System.out.println("Please enter the reservation number:");
				String Res_Number = sc.nextLine();
				
				for(int i = 0;i<reserve.size();i++)
				{
					if(reserve.get(i).res_id.equals(Res_Number))
					{
						Reservation temp = reserve.get(i);
						updatereservation(temp); //pass by reference
						reserve.remove(i); //delete from the searched index
						reserve.add(i, temp); //add the new updateed record into the deleted index
						return 1;
					}	
				
				}
				 
				return 0;
			}	
			
			
			
			/**
			 * This function updates an existing reservation. It prompts the user to
			 * input the number that corresponds to the field to update. 
			 * Then, it will return the updated value back to updateRes()
			 * @param reserve the reservation details of the guest
			 */
			private void updatereservation(Reservation reserve)
			{
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				printResOnly();
				Validation v=new Validation();
				
				
				int choice;
				do {
					System.out.println("Which information do you want to update:");
					System.out.println("1: Check in date");
					System.out.println("2: Check out date");
					System.out.println("3: Number of adults");
					System.out.println("4: Number of children");
					System.out.println("5: Room Type");
					System.out.println("6: quit");
					choice = sc.nextInt();
					switch (choice) {
					 case 1: 
						 System.out.println("Please enter the updated check in date (MM/DD/YYYY)"
						 		+ "");
						 
						 try {
							 reserve.check_in = df.parse(sc.next());
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	 
					 break;
					 case 2: 
						 System.out.println("Please enter the updated check out date (MM/DD/YYYY)");
						 try {
								reserve.check_out = df.parse(sc.next());
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						
							
					 break;
					 case 3: 
						 System.out.println("Please enter the updated number of adults");
						 reserve.NoOfAdult = sc.nextInt();
						 
					 break;
					 case 4:
						 System.out.println("Please enter the updated number of children");
						 reserve.NoOfChild = sc.nextInt();
						
					 break;
					 case 5:
						 System.out.println("Please enter the room type of your choice:");
						 reserve.roomType = sc.nextLine();
						
					 break;
					 case 6:
						 System.out.println("return to previous");
						
					
					}
					} while (choice < 7);
				
				
			}
		
			/**
			 * This function searches the arraylist for a valid reservation. It prompts the user for 
			 * reservation number input and uses it to check for validity.
			 * @return the reservation data of the reservation ID if found, else returns null
			 */
			public Reservation SearchRes()
			{
				System.out.println("Please enter the reservation number:");
				String Res_Number = sc.nextLine();
				for(int i = 0;i<reserve.size();i++)
				{
					if(reserve.get(i).res_id.equals(Res_Number))
					{
					
						return reserve.get(i); //Search Successfully
					}
				}
				return null; //failed to create
			}
			
			/**
			 * This function prints details of all the reservations
			 * @param  
			 */
			public void printResOnly() {
				if(reserve == null)
					return;
				for(int i = 0; i < reserve.size(); i++)
				{
					
				System.out.println("Guest ID: " + reserve.get(i).guestId);
				System.out.println("Reservation ID: " + reserve.get(i).res_id);
				System.out.println("Check in date: " + reserve.get(i).check_in);
				System.out.println("Check out date: " + reserve.get(i).check_out);
				System.out.println("Number of Adult: " + reserve.get(i).NoOfAdult);
				System.out.println("NUmber of Children: " + reserve.get(i).NoOfChild + "\n");
				}
				
				}
			
			/**
			 * This function prints the reservation according to reservation ID
			 * @param res_id the reservation number
			 */
			public void printResByResId(String res_id) {
				if(reserve == null)
					return;
				
				for(int i = 0; i < reserve.size(); i++)
				{
					if(res_id.equals(reserve.get(i).res_id)){
				System.out.println("Guest ID: " + reserve.get(i).guestId);
				System.out.println("Reservation ID: " + reserve.get(i).res_id);
				System.out.println("Check in date: " + reserve.get(i).check_in);
				System.out.println("Check out date: " + reserve.get(i).check_out);
				System.out.println("Number of Adult: " + reserve.get(i).NoOfAdult);
				System.out.println("NUmber of Children: " + reserve.get(i).NoOfChild + "\n");
					}
				}
				
				}
			
			
			/**
			 * This function deletes the reservation based on the user input 
			 * reservation ID. It will then call the
			 * data access class function upon deletion to update the text file. 
			 */
			public void deleteRes()
			{
				RoomApp rm = new RoomApp();
				String res_No;
				String roomType;
				String roomNo;
				Date in;
				Date out;
				System.out.println("Please enter reservation number to delete");
				res_No = sc.nextLine();
				int flag = 0;
				for(int i = 0; i<reserve.size();i++)
				{
					if(reserve.get(i).res_id.equals(res_No))
					{
						in = reserve.get(i).check_in;
						out = reserve.get(i).check_out;
						roomNo = reserve.get(i).room_id;
						roomType = reserve.get(i).roomType;
						
						for(int j = 0; j < reserve.size();j++)
						{
							if(reserve.get(j).roomType.equals(roomType) && reserve.get(j).status == AppData.RES_STATUS_WAITLIST)
							{
								if((reserve.get(j).check_in.equals(in) && reserve.get(j).check_out.equals(out)) || (reserve.get(j).check_in.equals(in) && reserve.get(j).check_out.before(out)) || (reserve.get(j).check_in.after(in) && reserve.get(j).check_in.equals(out)) || (reserve.get(j).check_in.after(in) && reserve.get(j).check_in.before(out) && reserve.get(j).check_out.before(out))) 
								{
									reserve.get(j).room_id = roomNo;
									reserve.get(j).status = AppData.RES_STATUS_CONFIRMED;
									System.out.println(reserve.get(j).guestId + " reservation status is now " +reserve.get(j).status);
									break;
								}
							}
						}
						reserve.remove(i);
						flag = 1;
						System.out.println("Reservation Deleted");
						break;
					}
				}
				
				if(flag == 0)
				{
					System.out.println("Reservation does not exists");
				}
				try
				{
					db.saveClass("reservation.txt", reserve);
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} //to read data from files
			}
			
			/**
			 * This function updates the reservation ID status to reserved
			 * It will then call the data access class function upon updating
			 * to update the text file.
			 * @param resId the reservation ID 
			 */
			public static void updateToReserved(String resId)
			{				
				for(int i = 0;i<reserve.size();i++)
				{
					if(reserve.get(i).res_id.equals(resId))
					{
						reserve.get(i).status= AppData.RES_STATUS_CONFIRMED; //Search Successfully
					}
				}
				
				try {
					db.saveClass("reservation.txt", reserve);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} //to read data from files

			}
			
			/**
			 * This function updates the reservation ID status to checked out
			 * It will then call the data access class function upon updating
			 * to update the text file.
			 */
			public void checkOut(String guestId)
			{				
				for(int i = 0;i<reserve.size();i++)
				{
					if(reserve.get(i).res_id.equals(guestId))
					{
						reserve.get(i).status= AppData.RES_STATUS_CHECKED_OUT; //Search Successfully
					}
				}
				
				try {
					db.saveClass("reservation.txt", reserve);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} //to read data from files

			}
			
			/**
			 * This function updates the reservation ID status to checked in
			 * It will then call the data access class function upon updating
			 * to update the text file.
			 * @param Res_Number the reservation ID
			 */
			public void checkIn(String Res_Number)
			{				
				for(int i = 0;i<reserve.size();i++)
				{
					if(reserve.get(i).res_id.equals(Res_Number))
					{
						reserve.get(i).status= AppData.RES_STATUS_CHECKED_IN; //Search Successfully
					}
				}
				
				try {
					db.saveClass("reservation.txt", reserve);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} //to read data from files

			}
			
			
			public void waitlist()
			{				
				System.out.println("Please enter the guest number:");
				String guest_id = sc.nextLine();
				for(int i = 0;i<reserve.size();i++)
				{
					if(reserve.get(i).guestId.equals(guest_id))
					{
						reserve.get(i).status= AppData.RES_STATUS_WAITLIST; //Search Successfully
						
						
					}
				}
				
				try {
					db.saveClass("reservation.txt", reserve);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} //to read data from files

			}
			
			/**
			 * This function takes the difference of the check in time and current time
			 * to validate expiry.
			 * the duration of stay in days
			 * @return the time difference in hours
			 */
			private static long compareTime()
			{
				Calendar checkInTime = Calendar.getInstance();
				Calendar today = Calendar.getInstance();
				long diff,mil1,mil2,diffhr;
				
				Date now = new Date();
				checkInTime.set(Calendar.HOUR_OF_DAY, 13);
				checkInTime.set(Calendar.MINUTE, 00);
				today.setTime(now);
				
				mil1 = checkInTime.getTimeInMillis();
				mil2 = today.getTimeInMillis();
				
				diff = mil2 - mil1;
				
				diffhr = diff/(60*1000);
				System.out.println(diffhr);
				return diffhr;
			}

			/**
			 * This function returns the check in date
			 * @param check_in the check in date
			 * @return the check in date
			 */
			public Date getCHECK_IN_DATE(Date check_in) {
				return check_in;
			}
			
			/**
			 * This function sets reservations that are past the chec in time
			 * to expired. it will call updateReservation to update the details 
			 * and call the data access class function upon updating the reservation to update the text file
			 */
			public static void getExpired() {
				// TODO Auto-generated method stub
				
				Date today=  new Date();//("MM-dd-yyyy hh:mm:ss a");
				DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				System.out.println("Checking for expired....");
				for(int i = 0;i<reserve.size();i++)
				{
					if(reserve.get(i)!=null) {
						String todaydate=sdf.format(today);
						String checkindate=sdf.format(reserve.get(i).check_in);
						
						
						if(todaydate.equals(checkindate))
						{
							System.out.println("Checked in date is equal to today");
							if(compareTime()<=120 && compareTime() >=0)
							{
								System.out.println("Checked in within the set time");
							}
							
							else {
								reserve.get(i).status= AppData.RES_STATUS_EXPIRED;
								System.out.println("Checked in time expired");
								
								try {
									db.saveClass("reservation.txt", reserve);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} //to read data from files
							}
						}
					}	
					else
						break;
					}
					
				}
			
			/**
			 * This function updates the room for reservations on the current day
			 * to reserved status.
			 */
			public static void compareCheckinWithToday() {
				// TODO Auto-generated method stub
				RoomApp rm = new RoomApp();
				Date today=  new Date();//("MM-dd-yyyy hh:mm:ss a");
				DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				System.out.println("Checking for expired....");

				for(int i = 0;i<reserve.size();i++)
				{
					if(reserve.get(i)!=null) {

						String todaydate=sdf.format(today);
						String checkindate=sdf.format(reserve.get(i).check_in);
						
						
						if(todaydate.equals(checkindate))
						{
							System.out.println(reserve.get(i).guestId + "Check in date is equal to today");

							rm.updateToReserved(reserve.get(i).room_id,reserve.get(i).guestId);
							updateToReserved(reserve.get(i).res_id);
						}
					}	
					else
						break;
					}
					
				}
			
			/**
			 * This function searches the arraylist for a valid reservation by guest IC
			 * @param guestId the IC of the guest
			 * @return the reservation data of the guest IC if found, else returns null
			 */
			public Reservation SearchResByGuestId(String guestId)
			{				
				for(int i = 0;i<reserve.size();i++)
				{
					
					if((reserve.get(i).guestId.equals(guestId)) && (reserve.get(i).status != AppData.RES_STATUS_CHECKED_OUT))
					{
						return reserve.get(i); //Search Successfully
					}
				}
				return null; //failed to create
			}
			
			/**
			 * This function automatically updates the room status of
			 * current day reservations to reserved, and also checks the expiry of any reservations past
			 * the specified timing. The timings are hardcoded to 1-2PM for check in time and 
			 * midnight for room status updates for purposes to resemble an actual hotel service
			 */
			public static void DemoScheduler() {
				 
			        // Create a calendar instance
			        Calendar calendar = Calendar.getInstance();
			 
			        // Set time of execution. Here, we have to run every day 3:00 AM; so,
			        // setting all parameters.
			        calendar.set(Calendar.HOUR, 0);
			        calendar.set(Calendar.MINUTE, 0);
			        calendar.set(Calendar.SECOND, 0);
			        calendar.set(Calendar.AM_PM, Calendar.AM);
			 
			        Long currentTime = new Date().getTime();
			 
			        // Check if current time is greater than our calendar's time. If So,
			        // then change date to one day plus. As the time already pass for
			        // execution.
			        if (calendar.getTime().getTime() < currentTime) {
			            calendar.add(Calendar.DATE, 1);
			        }
			 
			        // Calendar is scheduled for future; so, it's time is higher than
			        // current time.
			        long startScheduler = calendar.getTime().getTime() - currentTime;
			 
			        // Setting stop scheduler at 4:21 PM. Over here, we are using current
			        // calendar's object; so, date and AM_PM is not needed to set
			        calendar.set(Calendar.HOUR, 11);
			        calendar.set(Calendar.MINUTE, 59);
			        calendar.set(Calendar.AM_PM, Calendar.PM);
			 
			        // Calculation stop scheduler
			        long stopScheduler = calendar.getTime().getTime() - currentTime;
			 
			        // Executor is Runnable. The code which you want to run periodically.
			        Runnable task = new Runnable() {
			 
			            @Override
			            public void run() {
			            	compareCheckinWithToday();
			            	getExpired();
			            }
			        };
			 
			        // Get an instance of scheduler
			        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
			        // execute scheduler at fixed time.
			        scheduler.scheduleAtFixedRate(task, startScheduler, stopScheduler, TimeUnit.MILLISECONDS);
			}	
}



