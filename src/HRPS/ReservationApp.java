package HRPS;


import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import HRPS.Guest;
import HRPS.GuestApp;
import HRPS.GuestData;


		public class ReservationApp {
		GuestApp guestController = new GuestApp();
		private ArrayList<Reservation> reserve = new ArrayList<Reservation>();
		private Reservationdb db = new Reservationdb();
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
		
	







		public int createReservation()
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
		
		


		/*public void addguestic(Reservation reserve, String )
		{
		    myClassRoom.setTeacherName(TeacherName);
		}*/
		
		public int createRes()//pass by reference
		{
			
			Reservation res = new Reservation();
			Guest g = new Guest();
			GuestApp guest = new GuestApp();
			String var; //this var is for looping condition
			
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			//guestController.createGuest();
			
			Date d;
		
			
			System.out.println("Please enter the guestid:");
			res.guestId = sc.nextLine();
			
			g = guest.
				/*if(guestController.createGuest().equals(reserve.guestId)) {
					System.out.println("User ic is found");
				}*/
			
			//System.out.println("Please enter the room type(Single, Double, Deluxe, VIP Suite):");
			//res.roomType = sc.nextLine();
			//System.out.println("Please enter the bed type(single/double/master):");
			//res.bedType = sc.nextLine();
			System.out.println("Please enter the number of adults:");
			res.NoOfAdult = sc.nextInt();
			System.out.println("Please enter the number of children:");
			res.NoOfChild = sc.nextInt();
			System.out.println("Please enter the check in date MM/dd/yyyy");
			/*try {
				res.check_in = df.parse(sc.nextLine());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			try {
				d = df.parse(sc.next());
				System.out.println("CHECK IN DATE PARSE : " + d);
				res.check_in = d;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Please enter the check out date MM/dd/yyyy");
			try {
				d = df.parse(sc.next());
				System.out.println("CHECK OUT DATE PARSE : " + d);
				res.check_out = d;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println("Please enter the room type");
			//reserve.roomType = sc.nextLine();
			
			res.status= AppData.RES_STATUS_CONFIRMED;
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
			
			return 1;
			
			
			
			
		
			
			
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
						 reserve.roomType = sc.nextLine();
						
					 break;
					 case 6:
						 System.out.println("Please enter the ed type of your choice");
						 reserve.bedType = sc.nextLine();
						
					 break;
					 case 7: System.out.println("return to previous");
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
				System.out.println("Reservation ID: " + reserve.get(i).res_id);
				System.out.println("Check in date: " + reserve.get(i).check_in);
				System.out.println("Check out date: " + reserve.get(i).check_out);
				System.out.println("Number of Adult: " + reserve.get(i).NoOfAdult);
				System.out.println("NUmber of Children: " + reserve.get(i).NoOfChild + "\n");
				}
				
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


			  /*public static Date getToday(Date today2){
				 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			     Date today = new Date();
			     
			    // return new SimpleDateFormat(today).format(checkInDate);
				return dateFormat.format(today);
			 }*/
	
	
			public int getRoomStatus()
			{
			
				return 0 ;
			}

			  
			public Date getCHECK_IN_DATE(Date check_in) {
				return check_in;
			}


			public void statusOfReservation() {
				// TODO Auto-generated method stub
				
				Date today=  new Date();//("MM-dd-yyyy hh:mm:ss a");
				
				System.out.println("Please enter the reservation number:");
				String Res_Number = sc.nextLine();
				System.out.println(Res_Number);
				for(int i = 0;i<reserve.size();i++)
				{
					if(reserve.get(i).res_id.equals(Res_Number)) {
						System.out.println(reserve.get(i).res_id);
						System.out.println(getCHECK_IN_DATE(reserve.get(i).check_in));
						if(today.before(reserve.get(i).check_in)) {//if (getCHECK_IN_DATE(reserve[i].check_in).compareTo(getToday(today)) <=0){
							System.out.println("today is an earlier date than check_in date");
							break;
						}
				
						
						if(today.after(reserve.get(i).check_in)&& (today.before(reserve.get(i).check_out))) {
							System.out.println("Checked in");
							break;
					}
				}
					else {
						System.out.println("Reservation number not valid");
					}
				
				
				}

			}




			
}



