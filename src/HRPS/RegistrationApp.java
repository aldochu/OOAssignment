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

	import HRPS.Guest;
	import HRPS.GuestApp;
	import HRPS.GuestData;


	public class RegistrationApp {
			GuestApp guestController = new GuestApp();
			RoomApp roomController = new RoomApp();
			private ArrayList<Registration> register = new ArrayList<Registration>();
			private Reservationdb db = new Reservationdb();
			Scanner sc = new Scanner(System.in);
			
			///////////////////////////////////Load data from file////////////////////////////////////////
			public RegistrationApp()
			{
			try {
			db.readClass("walkin.txt", register); //to read data from files
			//for (int k = 0 ; k < register.size() ; k++)
				//System.out.println(register.get(k).res_id);
			//printResOnly());
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			}
			
		







			/*public int createReservation()
			{
				
				
				try {
					db.readClass("reservation.txt", register); //to read data from files
					
					for(int i = 0;i<register.size();i++)
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


			/*public void addguestic(Reservation register, String )
			{
			    myClassRoom.setTeacherName(TeacherName);
			}*/
			
			private String tem;
			private String temRoom;
			private Room rm;
			private Guest g;
			
			public boolean getuser(Guest guest)
			{
				if(guest==null) {
					return false;
				}
				else {
				return true;
				}
			}
			
			public boolean getRoomId(int i)
			{
				if(i==0) {
					return false;
				}
				else {
				return true;
				}
			}
			
			public void createRegistration(String guestId,String roomId)//pass by reference
			{
				
				Registration reg = new Registration();
//				GuestApp ga= new GuestApp();
//				Room rm = new Room();
//				RoomApp r = new RoomApp();
				String var; //this var is for looping condition
				
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				//guestController.createGuest();
				
				Date d;
				String temp;
				
				
				reg.guestId=guestId;
				
				System.out.println("Guest id is:" + reg.guestId);
				

				reg.room_id=roomId;
				System.out.println("Reservation room id"+reg.room_id);
				
				//reg.room_id=rm.roomId;
			
				//new RoomApp().assignRoom(g.ic);

				
				//System.out.println("Please enter the room type(Single, Double, Deluxe, VIP Suite):");
				//reg.roomType = sc.nextLine();
				//System.out.println("Please enter the bed type(single/double/master):");
				//reg.bedType = sc.nextLine();
				System.out.println("Please enter the number of adults:");
				reg.NoOfAdult = sc.nextInt();
				System.out.println("Please enter the number of children:");
				reg.NoOfChild = sc.nextInt();
				System.out.println("Please enter the check in date MM/dd/yyyy");
				/*try {
					reg.check_in = df.parse(sc.nextLine());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				try {
					d = df.parse(sc.next());
					System.out.println("CHECK IN DATE PARSE : " + d);
					reg.check_in = d;
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("Please enter the check out date MM/dd/yyyy");
				try {
					d = df.parse(sc.next());
					System.out.println("CHECK OUT DATE PARSE : " + d);
					reg.check_out = d;
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println("Please enter the room type");
				//register.roomType = sc.nextLine();
				
				reg.status= AppData.RES_STATUS_CHECKED_IN;
				reg.res_id=UUID.randomUUID().toString();
				
				register.add(reg);
				
				System.out.println("Guest created successfully!");
				
				
				try {
					db.saveClass("walkin.txt", register);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} //to read data from files
				
				System.out.println("Reservation created successfully!");


			}
			
			

			
			/*public int validateguestId(Reservation register,Guest hotelGuest) {
				for(int i = 0;i<register.length;i++)
				{
			
					if(register.guestId.equals(hotelGuest.guestId))
					{
						updatereservation(register[i]); //pass by reference
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
					
					for(int i = 0;i<register.size();i++)
					{
						if(register.get(i).res_id.equals(Res_Number))
						{
							Registration temp = register.get(i);
							updatereservation(temp); //pass by reference
							register.remove(i); //delete from the searched index
							register.add(i, temp); //add the new updateed record into the deleted index
							return 1;
						}
						//error message for incorrect reservation id
						
					}
					
					
					return 0;
				}	
				
				
				
				
				private void updatereservation(Registration register)
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
								 register.check_in = df.parse(sc.next());
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}	 
						 break;
						 case 2: 
							 System.out.println("Please enter the updated check out date (MM/DD/YYYY)");
							 try {
									register.check_out = df.parse(sc.next());
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							
								
						 break;
						 case 3: 
							 System.out.println("Please enter the updated number of adults");
							 register.NoOfAdult = sc.nextInt();
							 
						 break;
						 case 4:
							 System.out.println("Please enter the updated number of children");
							 register.NoOfChild = sc.nextInt();
							
						 break;
						 case 5:
							 System.out.println("Please enter the room type of your choice:");
							 register.room_id = sc.nextLine();
							
						 break;
						 case 6:
							 System.out.println("return to previous");
							
						
						}
						} while (choice < 7);
					
					
				}
			

				public Registration SearchRes()
				{
					System.out.println("Please enter the reservation number:");
					String Res_Number = sc.nextLine();
					for(int i = 0;i<register.size();i++)
					{
						if(register.get(i).res_id.equals(Res_Number))
						{
						
							return register.get(i); //Search Successfully
						}
					}
					return null; //failed to create
				}
				
			
				public void printResOnly() {
					if(register == null)
						return;
					for(int i = 0; i < register.size(); i++)
					{
					System.out.println("Guest ID: " + register.get(i).guestId);
					
					System.out.println("Reservation ID: " + register.get(i).res_id);
					System.out.println("Check in date: " + register.get(i).check_in);
					System.out.println("Check out date: " + register.get(i).check_out);
					System.out.println("Number of Adult: " + register.get(i).NoOfAdult);
					System.out.println("NUmber of Children: " + register.get(i).NoOfChild + "\n");
					}
					
					
					}
				
//				public void checkOut(String guestId)
//				{				
//					for(int i = 0;i<register.size();i++)
//					{
//						if(register.get(i).res_id.equals(guestId))
//						{
//							register.get(i).status= AppData.RES_STATUS_CHECKED_OUT; //Search Successfully
//						}
//					}
//					
//					try {
//						db.saveClass("walkin.txt", register);
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} //to read data from files
//
//				}
//				
//				public void checkIn(String Res_Number)
//				{				
//					for(int i = 0;i<register.size();i++)
//					{
//						if(register.get(i).res_id.equals(Res_Number))
//						{
//							register.get(i).status= AppData.RES_STATUS_CHECKED_IN; //Search Successfully
//						}
//					}
//					
//					try {
//						db.saveClass("walkin.txt", register);
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} //to read data from files
//
//				}
				
				
			


				/*public int reservationStatus() {
					
					Date now = new Date();
				      System.out.println(now.toString());
					if(now.equals(register.check_in) ) {
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


				private long compareTime()
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
//				 public boolean compareTime()   
//				    {   
//				        Calendar calendar = Calendar.getInstance();   
//				        String am_pm;   
//				        boolean check = true;
//				        int hour = calendar.get( Calendar.HOUR );   
//				        int minute = calendar.get( Calendar.MINUTE );   
//				        // int second = calendar.get(Calendar.SECOND);   
//				        if( calendar.get( Calendar.AM_PM ) == 0 )
//				        {   
//				            am_pm = "PM";   
//				            if(hour >=1 && hour<2)   //Within check in time 
//				            {
//				            	check = true;
//				            }
//				        }               
//				        else if(hour >2)  //check in time expired
//						{
//				        	check = false;
//						}
//				        return check;
//				    }
		
		
				public int getRoomStatus()
				{
				
					return 0 ;
				}

		
				

				public Registration SearchRegByGuestId(String guestId)
				{

					
					for(int i = 0;i<register.size();i++)
					{
						
						if(register.get(i).guestId.equals(guestId))
						{
						
							return register.get(i); //Search Successfully
						}
					}
					return null; //failed to create
				}

			








				


				
	}






