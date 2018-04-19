package HRPS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is the manager class of Room, it contains all the functional logic required by the system
 * @author Rou Shan
 * @version 1.0
 * @since 2018-04-19
 */
public class RoomApp {
	
	/**
	 * An arraylist of room data type to store and manipulate data
	 */
	private ArrayList<Room> hotelRoom = new ArrayList<Room>(); 
	private RoomData db = new RoomData();
	private float occupancyPercentage;
	
	/**
	 * variables to keep track of the various room types and bed types occupied by guests in the hotel
	 */
	private int vipCount = 0, deluxeCount = 0, singleCount = 0, doubleCount = 0;
	private int twoDouble = 0, oneKing = 0, oneDouble = 0, twoSingle = 0;
	Scanner sc = new Scanner(System.in);
	
	/**
	 * Default constructor, using the data access class to store data from text file to the Room arraylist
	 */
	protected RoomApp()
	{
		try {
			db.readClass("room.txt", hotelRoom); //to read data from files
			
			for (int k = 0 ; k < hotelRoom.size() ; k++) {
				if(!hotelRoom.get(k).status.equals("Vacant")) {
					if(hotelRoom.get(k).roomType.equals("Single")) singleCount++;
					else if(hotelRoom.get(k).roomType.equals("Double")) {
						doubleCount++;
						if(hotelRoom.get(k).bedType.equals("1 Double Bed")) oneDouble++;
						else twoSingle++;
					}
					else if(hotelRoom.get(k).roomType.equals("Deluxe")) {
						deluxeCount++;
						if(hotelRoom.get(k).bedType.equals("1 King Bed")) oneKing++;
						else twoDouble++;
					}
					else if(hotelRoom.get(k).roomType.equals("VIP")) vipCount++;
				}
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	/**
	 * This function will assign a room with respects to the guest's preferences (user inputs)
	 * It will return the assigned room if a vacant room which met the requests is assigned
	 * And it returns a null if there are no room with the guest's preferences are available 
	 * or there is invalid input
	 * @param guestIC
	 * @return Room
	 */
	public Room assignRoom(String guestIC)
	{
		int choice;
		String bedType, roomType;
		boolean city = false, smoke = false;
		
		//48 rooms; 6 floors - level 2 to 7; 8 rooms each floor
		//top floor: 2 VIP, 6 deluxe

		//16 single rooms
		//24 double rooms

		//smoking floors: 2, 4, 6, 7
		//city view: room 1-4 of every floor except floor 7
		
		System.out.println("Please enter the room type:");
		System.out.println("Single/Double/Deluxe/VIP");
		roomType = sc.next();
		
		//VIP
		if(roomType.equals("VIP") && vipCount < 2) {
			vipCount++;
			bedType = "2 King Beds";
			city = true;
			smoke = true;
		}
		
		//Deluxe
		else if(roomType.equals("Deluxe") && deluxeCount < 6) {
			deluxeCount++;
			city = true;
			smoke = true;
				System.out.println("Choice of Bed Type: ");
				System.out.println("1. 1 King Bed");
				System.out.println("2. 2 Double Beds");
				choice = sc.nextInt();
				if(choice == 1 && oneKing < 3) {
					oneKing++;
					bedType = "1 King Bed";
				}
					
				else if(choice == 2 && twoDouble < 3) {
					twoDouble++;
					bedType = "2 Double Beds";
				}
					
				else 
					return null;
		}
		
		//Double
		else if(roomType.equals("Double") && doubleCount < 20) {
			doubleCount++;
				System.out.println("Choice of Bed Type");
				System.out.println("1. 1 Double Bed");
				System.out.println("2. 2 Single Beds");
				choice = sc.nextInt();
				if(choice == 1 && oneDouble < 16) {
					oneDouble++;
					bedType = "1 Double Bed";
				}
					
				else if(choice == 2 && twoSingle < 8) {
					twoSingle++;
					bedType = "2 Single Beds";
				}
				else 
					return null;
		}
		
		//Single
		else if(roomType.equals("Single") && singleCount < 20) {
			singleCount++;
			bedType = "1 Single Bed";
			}
		
		else 
			return null;
		
		if(roomType.equals("Double") || roomType.equals("Single")) {
			System.out.println("Do you wish to have city view? Y: Yes");
			if(sc.next().equals("Y")) {
				city = true;
				
			}
			System.out.println("Do you wish to have smoking area? Y: Yes");
			if(sc.next().equals("Y")) smoke = true;
			
		}
			
			for(int i = 0; i<hotelRoom.size(); i++) {
				if(hotelRoom.get(i).status.equals("Vacant") && hotelRoom.get(i).roomType.equals(roomType) && hotelRoom.get(i).bedType.equals(bedType)
						&& hotelRoom.get(i).cityView == city && hotelRoom.get(i).smoking == smoke) {
					//assign room
					hotelRoom.get(i).guestIc = guestIC;
					hotelRoom.get(i).status = "Occupied";
					
					System.out.println("Do you wish to have breakfast included? Y: Yes");
					if(sc.next().equals("Y")) hotelRoom.get(i).breakfast = true;
					System.out.println("Do you wish to have WiFi enabled? Y: Yes");
					if(sc.next().equals("Y")) hotelRoom.get(i).wifi = true;
					hotelRoom.get(i).rate = calculateRate(guestIC);
					
					System.out.println("Your room is " + hotelRoom.get(i).roomId + " Thank you. ");
					
					try {
						db.saveClass("room.txt", hotelRoom);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} //to read data from files
					
					return hotelRoom.get(i); //assigned					
				}
			}
			return null;
	}
	
	/**
	 * This function unassigns a room and resets it to the default settings
	 * @param _hotelRoom
	 * @return true if unassign is successful
	 */
	public boolean unassignRoom(Room _hotelRoom) {
		boolean checked = false;
		
		_hotelRoom.status = "Vacant";
		_hotelRoom.breakfast = false;
		_hotelRoom.guestIc = null;
		_hotelRoom.rate = 0;
		_hotelRoom.wifi = false;
		
		if(_hotelRoom.roomType.equals("Single")) singleCount--;
		else if(_hotelRoom.roomType.equals("Double")) {
			doubleCount--;
			if(_hotelRoom.bedType.equals("1 Double Bed")) oneDouble--;
			else twoSingle--;
		}
		else if(_hotelRoom.roomType.equals("Deluxe")) {
			deluxeCount--;
			if(_hotelRoom.bedType.equals("1 King Bed")) oneKing--;
			else twoDouble--;
		}
		else if(_hotelRoom.roomType.equals("VIP")) vipCount--;
		checked = true;
		
		try {
			db.saveClass("room.txt", hotelRoom);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //to read data from files
		
		return checked;
	}
	
	/**
	 * This function takes in the roomId and changes the status of the room to Occupied
	 * @param roomId
	 */
	public void checkIn(String roomId)
	{
		boolean checked = false;
		for(int i = 0; i<hotelRoom.size(); i++) {
			if(hotelRoom.get(i).roomId.equals(roomId)) {
				hotelRoom.get(i).status = "Occupied";
				checked = true;
				System.out.println("Successfully checked in");
				
				try {
					db.saveClass("room.txt", hotelRoom);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} //to read data from files
			}
		}
		if(checked == false) System.out.println("Failed to check in");
	}
	
	/**
	 * This function takes in the roomId and changes the status of the room to Vacant and resets the 
	 * parameters of the room back to default using the unassignRoom function
	 * @param roomId
	 */
	public void checkOut(String roomId)
	{
		boolean checked = false;
		for(int i = 0; i<hotelRoom.size(); i++) {
			if(hotelRoom.get(i).roomId.equals(roomId)) {
				
				checked = unassignRoom(hotelRoom.get(i));
				System.out.println("Successfully checked out");
				
				try {
					db.saveClass("room.txt", hotelRoom);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} //to read data from files
			}
		}
		if(checked == false) System.out.println("Failed to check out");
	}
	
	/**
	 * This function is to update room details, it will ask for room ID and traverse the arraylist to look
	 * for the same insert ID, once found it will call the updateRoomDetails function and pass
	 * in the found room and waiting for the return of the updated room. The new updated room will
	 * then replace it's own place in the arraylist 
	 * @return 1 if update is successful
	 */
	public int updateRoom()
	{
		System.out.println("Please enter the room ID(eg. 02-08):");
		String id = sc.nextLine();
		for(int i = 0;i<hotelRoom.size();i++)
		{
			if(hotelRoom.get(i).roomId.equals(id))
			{
				Room temp = hotelRoom.get(i);
				temp = updateRoomDetails(temp); //pass by reference
				hotelRoom.remove(i); //delete from the searched index
				hotelRoom.add(i, temp); //add the new updated record into the deleted index
				try {
					db.saveClass("room.txt", hotelRoom);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} //to read data from files
				System.out.println("Successfully updated");
				return 1;
			}
		}
		System.out.println("Room not found");
		return 0;
	}
	
	/**
	 * This function is called by the function updateRoom after it has traversed the arraylist
	 * to get the requested room from the arraylist to update. This function will ask which parameter the 
	 * user would like the update and proceed to edit based on the choices and return back to the 
	 * function updateRoom
	 * @param _hotelRoom
	 * @return the updated Room
	 */
	private Room updateRoomDetails(Room _hotelRoom)

	{
		int choice;
		do {
			System.out.println("Which information do you want to update?");
			System.out.println("1. Guest IC");
			System.out.println("2. Status");
			System.out.println("3. Room Type");
			System.out.println("4. Wifi Enabled");
			System.out.println("5. Breakfast");
			System.out.println("6. Quit");
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
				case 1: System.out.println("Please enter the updated Guest IC: ");
						_hotelRoom.guestIc = sc.nextLine();
						break;
				case 2: System.out.println("Please enter the updated Status: ");
						_hotelRoom.status = sc.nextLine();
						break;
				case 3: Room temp = new Room();
						temp = assignRoom(_hotelRoom.guestIc);
						unassignRoom(_hotelRoom);
						break;
				case 4: System.out.println("Change WiFi option from " + _hotelRoom.wifi + " to " + !_hotelRoom.wifi + "?");
						System.out.println("Y: Yes, N: No");
						if(sc.nextLine().equals("Y")) _hotelRoom.wifi = !_hotelRoom.wifi;
						break;
				case 5: System.out.println("Change breakfast option from " + _hotelRoom.breakfast + " to " + !_hotelRoom.breakfast + "?");
						System.out.println("Y: Yes, N: No");
						if(sc.nextLine().equals("Y")) _hotelRoom.breakfast = !_hotelRoom.breakfast;
						break;
				case 6: System.out.println("Bye");
						break;
				default: System.out.println("Invalid option. Please try again.");
			}
		} while (choice < 6);
		
		return _hotelRoom;
	}

	/**
	 * This function updates the status of a room to Reserved
	 * @param roomId
	 * @param guestId
	 */
	public void updateToReserved(String roomId,String guestId)
	{
		for(int i = 0; i<hotelRoom.size(); i++) {
			if(hotelRoom.get(i).roomId.equals(roomId)) 
				{
					hotelRoom.get(i).status = "Reserved";
					hotelRoom.get(i).guestIc = guestId;
				}
		}
		
		try {
			db.saveClass("room.txt", hotelRoom);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //to read data from files
	}
	
	/**
	 * This function updates the status of a room to Vacant
	 * @param roomId
	 */
	public void updateToVacant(String roomId)
	{
		for(int i = 0; i<hotelRoom.size(); i++) {
			if(hotelRoom.get(i).roomId.equals(roomId)) hotelRoom.get(i).status = "Vacant";
		}
		try {
			db.saveClass("room.txt", hotelRoom);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //to read data from files
	}
	
	/**
	 * This function traverse the arraylist for room with the given guest ic and returns the 
	 * corresponding room
	 * @param guestIc
	 * @return room
	 */
	public Room getRoomDetails(String guestIc)
	{
		for(int i = 0; i<hotelRoom.size(); i++ ) {
			if(hotelRoom.get(i).guestIc.equals(guestIc)) return hotelRoom.get(i);
		}
		return null;
	}
	
	/**
	 * This function calculates the rate of the room per night
	 * @param guestIc
	 * @return rate per night
	 */
	public double calculateRate(String guestIc)
	{
		//per night
		//Room type: single = 100, double = 150, deluxe = 200, vip = 300
		//Bed type: single = 50, double = 75, queen = 100, king = 150
		//no smoking area = 25
		//wifi = 10
		//city view = 25
		//breakfast = 15
		
		double result = 0;
		
 		for(int i = 0; i<hotelRoom.size(); i++) {
			if(hotelRoom.get(i).guestIc.equals(guestIc)) {
				if(hotelRoom.get(i).roomType.equals("Single")) result += 100;
				else if(hotelRoom.get(i).roomType.equals("Double")) result += 150;
				else if(hotelRoom.get(i).roomType.equals("Deluxe")) result += 200;
				else if(hotelRoom.get(i).roomType.equals("VIP")) result += 300;
				
				if(hotelRoom.get(i).smoking == false) result += 25;
				
				if(hotelRoom.get(i).wifi == true) result += 10;
				
				if(hotelRoom.get(i).cityView == true) result += 25;
				
				if(hotelRoom.get(i).breakfast == true) result += 15;
				
				hotelRoom.get(i).rate = result;
				System.out.println("The rate per night for Room " + hotelRoom.get(i).roomId + " is :" + hotelRoom.get(i).rate);
				return result;
			}
		}
		return 0;
	}
	
	/**
	 * This function will print the details of the room 
	 */
	public void displayRoom()
	{
		String roomid;
		System.out.println("Please enter room ID (eg. 02-01): ");
		roomid = sc.next();
		for(int i = 0; i<hotelRoom.size(); i++) {
			if(hotelRoom.get(i).roomId.equals(roomid)) {
				System.out.println("Room Id: " + hotelRoom.get(i).roomId);
				System.out.println("Guest IC: " + hotelRoom.get(i).guestIc);
				System.out.println("Room Type: " + hotelRoom.get(i).roomType);
				System.out.println("Bed Type: " + hotelRoom.get(i).bedType);
				System.out.println("Smoking: " + hotelRoom.get(i).smoking);
				System.out.println("City View: " + hotelRoom.get(i).cityView);
				System.out.println("Wifi enabled: " + hotelRoom.get(i).wifi);
				System.out.println("Breakfast Included: " + hotelRoom.get(i).breakfast);
			}
		}
	}
	
	/**
	 * This function traverse the arraylist for the room with the given room ID or guest IC
	 * and prints the availability of the room
	 * @return 1 if room is found
	 */
	public int checkAvailability()
	{
		String check;
		System.out.println("Please enter the room ID or guest IC: ");
		check = sc.nextLine();
		System.out.println("here");
		for(int i = 0; i<hotelRoom.size(); i++) {
			if(hotelRoom.get(i).roomId.equals(check) || hotelRoom.get(i).guestIc.equals(check)) {
				if(hotelRoom.get(i).status.equals("Vacant")) 
					System.out.println("Room number: " + hotelRoom.get(i).roomId + " is Available");
				else
					System.out.println("Sorry, Room number: " + hotelRoom.get(i).roomId + " is " + hotelRoom.get(i).status);
				return 1;
			}
		}
		System.out.println("Room not found");
		return 0;
	}
	
	/**
	 * this function will generate the room occupancy report
	 */
	public void roomOccupancyReport()
	{
		float count=0;
		for(int i=1; i<=hotelRoom.size(); i++) {
			if(hotelRoom.get(i).status == "Occupied") count++;
		}
		occupancyPercentage = (float) ((count/48.0) * 100.0);
		System.out.println("Occupancy Percentage: " + occupancyPercentage + "%");
	}
	
	/**
	 * this function will generate two types of room statistic report: Room type occupancy rate (Single,
	 * Double etc.) and room status (Vacant, Reserved etc.)
	 */
	public void roomStatisticReport()
	{
		int choice;
		StringBuilder one =  new StringBuilder() ;
		StringBuilder two =  new StringBuilder() ;
		StringBuilder three =  new StringBuilder() ;
		StringBuilder four =  new StringBuilder() ;
		
		System.out.println("Print Statistic Report by");
		System.out.println("1. Room Type Occupancy Rate (display how vacancy)");
		System.out.println("2. Room Status");
		choice = sc.nextInt();
		
		switch(choice) {
		case 1:
			for(int i = 0; i<hotelRoom.size(); i++) {
				if(hotelRoom.get(i).roomType.equals("Single") && hotelRoom.get(i).status.equals("Vacant")) 
					one.append(hotelRoom.get(i).roomId + "|");
				else if(hotelRoom.get(i).roomType.equals("Double") && hotelRoom.get(i).status.equals("Vacant")) 
					two.append(hotelRoom.get(i).roomId + "|");
				else if(hotelRoom.get(i).roomType.equals("Deluxe") && hotelRoom.get(i).status.equals("Vacant")) 
					three.append(hotelRoom.get(i).roomId + "|");
				else if(hotelRoom.get(i).roomType.equals("VIP") && hotelRoom.get(i).status.equals("Vacant")) 
					four.append(hotelRoom.get(i).roomId + "|");
			}
			
			System.out.println("Single: " + (16-singleCount) + " out of 16");
			System.out.println("Rooms: " + one);
			System.out.println("Double: " + (24-doubleCount) + " out of 24");
			System.out.println("Rooms: " + two);
			System.out.println("Deluxe: " + (6-deluxeCount) + " out of 6");
			System.out.println("Rooms: " + three);
			System.out.println("VIP: " + (2-vipCount) + " out of 2");
			System.out.println("Rooms: " + four);
			break;
			
		case 2:
			for(int i = 0; i<hotelRoom.size(); i++) {
				if(hotelRoom.get(i).status.equals("Vacant")) 
					one.append(hotelRoom.get(i).roomId + "|");
				else if(hotelRoom.get(i).status.equals("Occupied")) 
					two.append(hotelRoom.get(i).roomId + "|");
				else if(hotelRoom.get(i).status.equals("Reserved")) 
					three.append(hotelRoom.get(i).roomId + "|");
				else if(hotelRoom.get(i).status.equals("Under Maintenance")) 
					four.append(hotelRoom.get(i).roomId + "|");
			}
			
			System.out.println("Vacant: ");
			System.out.println("Rooms: " + one);
			System.out.println("Occupied: ");
			System.out.println("Rooms: " + two);
			System.out.println("Reserved: ");
			System.out.println("Rooms: " + three);
			System.out.println("Under Maintenance: ");
			System.out.println("Rooms: " + four);
			break;
			
		default: 
			System.out.println("Invalid Choice");
		}
	}
	

}
