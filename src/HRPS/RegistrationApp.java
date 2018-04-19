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

	/**
	 * 
	This class is the manager class of Registration, it contains all the functional logic required by the system
	 @author Li Feng
	 @version 1.0
	 @since 2018-04-18
	 *
	 */

	public class RegistrationApp {
			GuestApp guestController = new GuestApp();
			RoomApp roomController = new RoomApp();
			
			/**
			 * An arraylist of guest data type to store and manipulate data
			 */
			private ArrayList<Registration> register = new ArrayList<Registration>();
			private Reservationdb db = new Reservationdb();
			Scanner sc = new Scanner(System.in);
			
			///////////////////////////////////Load data from file////////////////////////////////////////
			/**
			 * Default constructor, using the data access class to store data from text file to the Guest arraylist
			 */
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
			
			private String tem;
			private String temRoom;
			private Room rm;
			private Guest g;
			
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
			 * @param roomNo to check if room number is not 0
			 * @return true if room number is valid,
			 * false if roomNo is 0
			 */
			public boolean getRoomId(int roomNo)
			{
				if(roomNo==0) {
					return false;
				}
				else {
				return true;
				}
			}
			
			/**
			 * This function creates a new walk in, it will ask for user input for number of adults,children ,
			 * check out date. As this is a walk in, the check in date will be the current date. It will then call the
			 * data access class function upon creation to update the text file. 
			 * @param guestId to set the guest ID
			 * @param roomId to set the room ID
			 * @param roomType to set the room type
			 * @throws ParseException to catch exceptions
			 */
			public void createRegistration(String guestId,String roomId, String roomType) throws ParseException//pass by reference
			{
				
				Registration reg = new Registration();
				Validation v=new Validation();
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				//guestController.createGuest();
				
				Date d = new Date();
				String temp;
				
				
				reg.guestId=guestId;
				
				System.out.println("Guest id is:" + reg.guestId);
				

				reg.room_id=roomId;
				System.out.println("Reservation room id"+reg.room_id);
				
				System.out.println("Please enter the number of adults:");
				reg.NoOfAdult = sc.nextInt();
				System.out.println("Please enter the number of children:");
				reg.NoOfChild = sc.nextInt();
				System.out.println("Please enter the check in date MM/dd/yyyy");
				System.out.println("CHECK IN DATE PARSE : " + d);
				reg.check_in = d;
				
				
				
				try {
					
					do {
						System.out.println("Please enter the check out date MM/dd/yyyy");
						d = df.parse(sc.next());
						if(v.CheckValidCheckOutDate(reg.check_in,d)==false) {
							System.out.println("Invalid checkout date,please enter again!");
						}
					}
					while(v.CheckValidCheckOutDate(reg.check_in,d)==false);
					
					
					reg.check_out = d;
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				reg.roomType = roomType;
				
				reg.status= AppData.RES_STATUS_CHECKED_IN;
				reg.res_id=UUID.randomUUID().toString();
				
				register.add(reg);
				
				System.out.println("Walk In created successfully!");
				
				
				try {
					db.saveClass("walkin.txt", register);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} //to read data from files
				
				System.out.println("Reservation created successfully!");
				}
			
				/**
				 * This function updates an existing walk in. It prompts the user to
				 * input reservation number. When found, it will call updateReservation to update the details 
				 * and call the data access class function upon updating the reservation to update the text file
				 * @return 1 on success, 0 on failure
				 */
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
							register.add(i, temp); //add the new updated record into the deleted index
							return 1;
						}						
					}
					return 0;
				}	
				
				/**
				 * This function updates an existing reservation. It prompts the user to
				 * input the number that corresponds to the field to update. 
				 * Then, it will return the updated value back to updateRes()
				 * @param register the walk in details of the guest
				 */
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
			
				/**
				 * This function searches the arraylist for a valid walk in. It prompts the user for 
				 * reservation number input and uses it to check for validity.
				 * @return the walk in data of the reservation ID if found, else returns null
				 */
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
			
				/**
				 * This function prints details of all the walk ins
				 */
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
				
				/**
				 * This function sets the status of a walk in to checked out upon successful checkout
				 * It will call the data access class function upon successful updating of the reservation 
				 * to update the text file
				 * @param guestId the guest IC
				 */
				public void checkOut(String guestId)
				{				
					for(int i = 0;i<register.size();i++)
					{
						if(register.get(i).res_id.equals(guestId))
						{
							register.get(i).status= AppData.RES_STATUS_CHECKED_OUT; //Search Successfully
						}
					}
					
					try {
						db.saveClass("walkin.txt", register);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} //to read data from files

				}

				/**
				 * This function searches the arraylist for a valid walk in. Instead of searching by
				 * reservation ID, this function searches by guest IC
				 * @param guestId the IC of the guest
				 * @return the walk in data of the reservation ID if found, else returns null
				 */
				public Registration SearchRegByGuestId(String guestId)
				{
					for(int i = 0;i<register.size();i++)
					{
						
						if((register.get(i).guestId.equals(guestId)) && (register.get(i).status != AppData.RES_STATUS_CHECKED_OUT))
						{
							return register.get(i); //Search Successfully
						}
					}
					return null; //failed to create
				}
			}






