package HRPS;


import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import HRPS.Guest;
import HRPS.GuestApp;
import HRPS.GuestData;


		public class ReservationApp {
		private static final TimeUnit MILLISECONDS = null;
		private static final int NoOfFloor = 0;
		private static final int NoOfRoomPerFloor = 0;
		GuestApp guestController = new GuestApp();
		RoomApp roomController = new RoomApp();
		private static ArrayList<Reservation> reserve = new ArrayList<Reservation>();
		private static Reservationdb db = new Reservationdb();
		private Room rm;
		Scanner sc = new Scanner(System.in);
		
		///////////////////////////////////Load data from file////////////////////////////////////////
		public ReservationApp()
		{
		try {
		db.readClass("reservation.txt", reserve); //to read data from files
		//for (int k = 0 ; k < reserve.size() ; k++)
			//System.out.println(reserve.get(k).res_id);
		//printResOnly());
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		}
		
	

		/*public int createReservation()
		{
			
			
			try {
				db.readClass("reservation.txt", reserve); //to read data from files
				
				for(int i = 0;i<reserve.size();i++)
				{
					
						createRes(); //pass by reference
									
					}
				
				return 0;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		

		}
		
		*/

		
		private String tem;
		private String temRoom;
		
		public boolean getuser(Guest guest)
		{
			if(guest==null) {
				return false;
			}
			else {
			return true;
			}
		}
		
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
		
		
		public void createRes(String guestId)//pass by reference
		{
			
			Reservation res = new Reservation();
			Validation v =new Validation();

//			String var; //this var is for looping condition
			
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			//guestController.createGuest();
			
			Date d;
			String temp;
	
		
				
			res.guestId=guestId;
			
			
			
			
			try {
				
				do {
					System.out.println("Please enter the check in date MM/dd/yyyy");
					d = df.parse(sc.nextLine());
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
					d = df.parse(sc.next());
					if(v.CheckValidCheckOutDate(res.check_in,d)==false) {
						System.out.println("Invalid checkout date,please enter again!");
					}
				}
				while(v.CheckValidCheckOutDate(res.check_in,d)==false);
				
				
				res.check_out = d;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			res.room_id = v.CheckRoomVacancyFromReservation(res.check_in,res.check_out, reserve);
			
			if( res.room_id!= null){
				System.out.println("Reservation is confirmed");
				
			res.status= AppData.RES_STATUS_CONFIRMED;
		
			System.out.println(res.status);
			}
			else {
				System.out.println("Your reservation is in waitlist");
				res.status=AppData.RES_STATUS_WAITLIST;
				System.out.println(res.status);
				res.room_id="waitlist";
			}
			
			
			System.out.println("Reservation room id"+res.room_id);
			
//			res.room_id=roomId;
//			System.out.println("Reservation room id"+res.room_id);
			
		
			//new RoomApp().assignRoom(g.ic);

			
			System.out.println("Please enter the number of adults:");
			res.NoOfAdult = sc.nextInt();
			System.out.println("Please enter the number of children:");
			res.NoOfChild = sc.nextInt();
			
			//System.out.println("Please enter the room type");
			//reserve.roomType = sc.nextLine();
			
			
			
			
			
			res.res_id=UUID.randomUUID().toString();
			
			reserve.add(res);
			
			System.out.println("Guest created successfully!");
			
			
			try {
				db.saveClass("reservation.txt", reserve);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //to read data from files
			
			System.out.println("Reservation created successfully!");


		}
		
		

		
		/*public int validateguestId(Reservation reserve,Guest hotelGuest) {
			for(int i = 0;i<reserve.length;i++)
			{
		
				if(reserve.guestId.equals(hotelGuest.guestId))
				{
					updatereservation(reserve[i]); //pass by reference
					return 1;
				}
			}
		}*/
		
			/*public void getResStatus(String[] res_status) {
			String todayDate="15-03-2018";
			if(todayDate.compareTo(check_in)>0 && rm_status="occupied"){
	            System.out.println("Today day is after Checkin");
	            System.out.println("Checked_in");
	        }else if(check_in.compareTo(todayDate)<0){
	            System.out.println("Check in is before Today date");
	        }
			
	        else{
	            System.out.println("Date1 is equal to Date2");
	        }
			
		}*/
		
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
					//error message for incorrect reservation id
					
				}
				
				
				return 0;
			}	
			
			
			
			
			private void updatereservation(Reservation reserve)
			{
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				printResOnly();
				
				int choice;
				do {
					System.out.println("Which information do you want to update:");
					System.out.println("1: Check in date");
					System.out.println("2: Check out date");
					System.out.println("3: Number of adults");
					System.out.println("4: Number of children");
					System.out.println("5: Room Type");
					System.out.println("6: Bed Type");
					System.out.println("7: quit");
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
						 reserve.room_id = sc.nextLine();
						
					 break;
					 case 6:
						 System.out.println("return to previous");
						
					
					}
					} while (choice < 7);
				
				
			}
		

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
			




			/*public int reservationStatus() {
				
				Date now = new Date();
			      System.out.println(now.toString());
				if(now.equals(reserve.check_in) ) {
					System.out.println("Check in date is today");
				}
				return 0;
				
			}*/
			
			
			/*public boolean isExpire(String checkInDate){
			    if(checkInDate.isEmpty() || checkInDate.trim().equals("")){
			        return false;
			    }else{
			            SimpleDateFormat sdf =  new SimpleDateFormat("MM/DD/YYYY");//("MM-dd-yyyy hh:mm:ss a"); // Jan-20-2015 1:30:55 PM
			               Date d=null;
			               Date d1=null;
			            Date today=   getToday("MM/DD/YYYY");//("MM-dd-yyyy hh:mm:ss a");
			            try {
			                System.out.println("checkindate>> "+checkInDate);
			                System.out.println("today>> "+today+"\n\n");
			                d = sdf.parse(checkInDate);
			                d1 = sdf.parse(today);
			                if(d1.compareTo(d) <0){// not expired
			                    return false;
			                }else if(d.compareTo(d1)==0){// both date are same
			                            if(d.getTime() < d1.getTime()){// not expired
			                                return false;
			                            }else if(d.getTime() == d1.getTime()){//expired
			                                return true;
			                            }else{//expired
			                                return true;
			                            }
			                }else{//expired
			                    return true;
			                }
			            } catch (ParseException e) {
			                e.printStackTrace();                    
			                return false;
			            }
			    }
			}*/


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
//			 public boolean compareTime()   
//			    {   
//			        Calendar calendar = Calendar.getInstance();   
//			        String am_pm;   
//			        boolean check = true;
//			        int hour = calendar.get( Calendar.HOUR );   
//			        int minute = calendar.get( Calendar.MINUTE );   
//			        // int second = calendar.get(Calendar.SECOND);   
//			        if( calendar.get( Calendar.AM_PM ) == 0 )
//			        {   
//			            am_pm = "PM";   
//			            if(hour >=1 && hour<2)   //Within check in time 
//			            {
//			            	check = true;
//			            }
//			        }               
//			        else if(hour >2)  //check in time expired
//					{
//			        	check = false;
//					}
//			        return check;
//			    }
	
	
			public int getRoomStatus()
			{
			
				return 0 ;
			}

			  
			public Date getCHECK_IN_DATE(Date check_in) {
				return check_in;
			}
			public void getExpired() {
				// TODO Auto-generated method stub
				
				Date today=  new Date();//("MM-dd-yyyy hh:mm:ss a");
				DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				System.out.println("Checking for expired....");
//				String Res_Number = sc.nextLine();
//				System.out.println(Res_Number);
				for(int i = 0;i<reserve.size();i++)
				{
					if(reserve.get(i)!=null) {
//					if(reserve.get(i).res_id.equals(Res_Number)) 
//					{
//						System.out.println(reserve.get(i).res_id);
//						System.out.println(getCHECK_IN_DATE(reserve.get(i).check_in));
//						if(today.before(reserve.get(i).check_in)) {//if (getCHECK_IN_DATE(reserve[i].check_in).compareTo(getToday(today)) <=0){
//							System.out.println("today is an earlier date than check_in date");
//							break;
//						}
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

			

			public static void compareCheckinWithToday() {
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
							
							
					
								
								try {
									db.saveClass("reservation.txt", reserve);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} //to read data from files
							
						}
					}	
					else
						break;
					}
					
				}

			
			





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
			
			public static void DemoScheduler() {
				 
				
			        // Create a calendar instance
			        Calendar calendar = Calendar.getInstance();
			 
			        // Set time of execution. Here, we have to run every day 3:00 AM; so,
			        // setting all parameters.
			        calendar.set(Calendar.HOUR, 6);
			        calendar.set(Calendar.MINUTE, 48);
			        calendar.set(Calendar.SECOND, 0);
			        calendar.set(Calendar.AM_PM, Calendar.PM);
			 
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
			        calendar.set(Calendar.HOUR, 9);
			        calendar.set(Calendar.MINUTE, 5);
			        calendar.set(Calendar.AM_PM, Calendar.PM);
			 
			        // Calculation stop scheduler
			        long stopScheduler = calendar.getTime().getTime() - currentTime;
			 
			        // Executor is Runnable. The code which you want to run periodically.
			        Runnable task = new Runnable() {
			 
			            @Override
			            public void run() {
			            	 compareCheckinWithToday();
			 
			            }
			        };
			 
			        // Get an instance of scheduler
			        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
			        // execute scheduler at fixed time.
			        scheduler.scheduleAtFixedRate(task, startScheduler, stopScheduler, TimeUnit.MILLISECONDS);
			    
			




			}




			


			
}



