package HRPS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class RoomApp {
	
	private ArrayList<Room> hotelRoom = new ArrayList<Room>(); 
	private RoomData db = new RoomData();
	private float occupancyPercentage;
	private int vipCount = 0, deluxeCount = 0, singleCount = 0, doubleCount = 0;
	private int twoDouble = 0, oneKing = 0, oneDouble = 0, twoSingle = 0;
	Scanner sc = new Scanner(System.in);
	
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
	
	public Room assignRoom(String guestIC)
	{
		//48 rooms; 6 floors - level 2 to 7; 8 rooms each floor
		//top floor: 2 VIP, 6 deluxe

		//16 single rooms
		//24 double rooms

		//smoking floors: 2, 4, 6, 7
		//city view: room 1-4 of every floor except floor 7
		
		int choice;
		String bedType, roomType;
		boolean city = false, smoke = false;
		
		System.out.println("Please enter the room type:");
		System.out.println("Single/Double/Deluxe/VIP");
		roomType = sc.nextLine();
		
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
					hotelRoom.get(i).status = "Reserved";
					
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
	
	private void assignBed(Room room, String bed) {
		boolean assign = false;
		for(int i = 0; i<hotelRoom.size(); i++) {
			if(hotelRoom.get(i).status.equals("Vacant") && hotelRoom.get(i).bedType.equals(bed) 
					&& hotelRoom.get(i).cityView == room.cityView && hotelRoom.get(i).smoking == room.smoking) {
				hotelRoom.get(i).guestIc = room.guestIc;
				hotelRoom.get(i).status = "Reserved";
				hotelRoom.get(i).breakfast = room.breakfast;
				hotelRoom.get(i).wifi = room.wifi;
				assign = true;
				System.out.println("Your new room is : " + hotelRoom.get(i).roomId);
			}
		}
		
		try {
			db.saveClass("room.txt", hotelRoom);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //to read data from files
		
		if(assign == false) System.out.println("Sorry, no rooms available");
	}
	
	private boolean unassignRoom(String roomid) {
		boolean checked = false;
		for(int i = 0; i<hotelRoom.size(); i++) {
			if(hotelRoom.get(i).roomId.equals(roomid)) {
				hotelRoom.get(i).status = "Vacant";
				hotelRoom.get(i).guestIc = null;
				hotelRoom.get(i).rate = 0.0;
				hotelRoom.get(i).breakfast = false;
				hotelRoom.get(i).wifi = false;
				if(hotelRoom.get(i).roomType.equals("Single")) singleCount--;
				else if(hotelRoom.get(i).roomType.equals("Double")) {
					doubleCount--;
					if(hotelRoom.get(i).bedType.equals("1 Double Bed")) oneDouble--;
					else twoSingle--;
				}
				else if(hotelRoom.get(i).roomType.equals("Deluxe")) {
					deluxeCount--;
					if(hotelRoom.get(i).bedType.equals("1 King Bed")) oneKing--;
					else twoDouble--;
				}
				else if(hotelRoom.get(i).roomType.equals("VIP")) vipCount--;
				checked = true;
			}
		}
		
		try {
			db.saveClass("room.txt", hotelRoom);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //to read data from files
		
		return checked;
	}
	
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
	
	public void checkOut(String roomId)
	{
		boolean check;
		check = unassignRoom(roomId);
		if(check == false) System.out.println("Failed to check out");	
		else	
		System.out.println("Successfully checked out");
	}
	
	public int updateRoom()
	{
		System.out.println("Please enter the room ID(eg. 02-08):");
		String id = sc.nextLine();
		for(int i = 0;i<hotelRoom.size();i++)
		{
			if(hotelRoom.get(i).roomId.equals(id))
			{
				Room temp = hotelRoom.get(i);
				updateRoomDetails(temp); //pass by reference
				hotelRoom.remove(i); //delete from the searched index
				hotelRoom.add(i, temp); //add the new updated record into the deleted index
				return 1;
			}
		}
		return 0;
	}
	
	private void updateRoomDetails(Room _hotelRoom)

	{
		int choice;
		do {
			System.out.println("Which information do you want to update?");
			System.out.println("1. Guest IC");
			System.out.println("2. Status");
			System.out.println("3. Room Type");
			System.out.println("4. Bed Type");
			System.out.println("5. Wifi Enabled");
			System.out.println("6. Breakfast");
			System.out.println("7. Quit");
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
						if(temp != null) unassignRoom(temp.roomId);
						else System.out.println("Sorry, no rooms available");
						break;
						
				case 4: System.out.println("Your current Bed Type: " + _hotelRoom.bedType);
						String bed = null;
						if(_hotelRoom.roomType.equals("Deluxe")) {
							if(_hotelRoom.bedType.equals("1 King Bed") && (twoDouble < 3)) bed = "2 Double Beds";
							else if(_hotelRoom.bedType.equals("2 Double Beds") && (oneKing < 3)) bed = "1 King Bed";
						}
						else if(_hotelRoom.roomType.equals("Double")) {
							if(_hotelRoom.bedType.equals("1 Double Bed") && (twoSingle < 16)) bed = "2 Single Beds";
							else if(_hotelRoom.bedType.equals("2 Single Beds") && (oneDouble < 8)) bed = "1 Double Bed";
						}
						assignBed(_hotelRoom, bed);
						unassignRoom(_hotelRoom.roomId);
						break;
						
				case 5: System.out.println("Change WiFi option from " + _hotelRoom.wifi + " to " + !_hotelRoom.wifi + "?");
						System.out.println("Y: Yes, N: No");
						if(sc.nextLine().equals("Y")) _hotelRoom.wifi = !_hotelRoom.wifi;
						break;
						
				case 6: System.out.println("Change breakfast option from " + _hotelRoom.breakfast + " to " + !_hotelRoom.breakfast + "?");
						System.out.println("Y: Yes, N: No");
						if(sc.nextLine().equals("Y")) _hotelRoom.breakfast = !_hotelRoom.breakfast;
						break;
						
				case 7: System.out.println("Bye");
						break;
						
				default: System.out.println("Invalid option. Please try again.");
			}
		} while (choice < 7);
	}

	//update status to reserved 
	public void updateToReserved(String roomId)
	{
		for(int i = 0; i<hotelRoom.size(); i++) {
			if(hotelRoom.get(i).roomId.equals(roomId)) hotelRoom.get(i).status = "Reserved";
		}
	}
	
	//update status to vacant
	public void updateToVacant(String roomId)
	{
		for(int i = 0; i<hotelRoom.size(); i++) {
			if(hotelRoom.get(i).roomId.equals(roomId)) hotelRoom.get(i).status = "Vacant";
		}
	}
	
	
	public Room getRoomDetails(String guestIc)
	{
		for(int i = 0; i<hotelRoom.size(); i++ ) {
			if(hotelRoom.get(i).guestIc.equals(guestIc)) return hotelRoom.get(i);
		}
		return null;
	}
	
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
		System.out.println("Invalid Entry. Please try again.");
		return 0;
	}
	
	public void roomOccupancyReport()
	{
		float count=0;
		for(int i=1; i<=hotelRoom.size(); i++) {
			if(hotelRoom.get(i).status == "Occupied") count++;
		}
		occupancyPercentage = (float) ((count/48.0) * 100.0);
		System.out.println("Occupancy Percentage: " + occupancyPercentage + "%");
	}
	
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
