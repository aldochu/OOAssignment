package HRPS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class RoomApp {
	
	private ArrayList<Room> hotelRoom = new ArrayList<Room>(); 
	private RoomData db = new RoomData();
	private float occupancyPercentage;
	private int vipCount = 0, deluxeCount = 0, singleCount = 0, doubleCount = 0; 
	Scanner sc = new Scanner(System.in);
	
	protected RoomApp()
	{
		try {
			db.readClass("room.txt", hotelRoom); //to read data from files
			
			for (int k = 0 ; k < hotelRoom.size() ; k++) {
				if(!hotelRoom.get(k).status.equals("Vacant")) {
					if(hotelRoom.get(k).roomType.equals("Single")) singleCount++;
					else if(hotelRoom.get(k).roomType.equals("Double")) doubleCount++;
					else if(hotelRoom.get(k).roomType.equals("Deluxe")) deluxeCount++;
					else if(hotelRoom.get(k).roomType.equals("VIP")) vipCount++;
				}
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int assignRoom(String guestIC)// do method of overloading to get the roomid
	{
		int level, room;
		String roomId, bedType, roomType;
		
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
			bedType = "2 King Beds";
			level = 7;
			room = vipCount;
			System.out.println("here");
			roomId = String.format("%02d", level) + "-" + String.format("%02d", room);
		}
		
		//Deluxe
		else if(roomType.equals("Deluxe") && deluxeCount < 6) {
			deluxeCount++;
				System.out.println("Would you rather 2 Double Beds? Y:Yes");
				if(sc.nextLine() == "Y") 
					bedType = "2 Double Beds";
				else 
					bedType = "1 King Bed";
			level = 7;
			room = deluxeCount + 2;
			roomId = String.format("%02d", level) + "-" + String.format("%02d", room);
		}
		
		//Double
		else if(roomType.equals("Double") && doubleCount < 20) {
			doubleCount++;
				System.out.println("Would you rather 2 Single Beds? Y:Yes");
				if(sc.nextLine() == "Y") 
					bedType = "2 Single Beds";
				else 
					bedType = "1 Double Bed";
			level = (doubleCount + singleCount) / 8 + 2;
			room = (doubleCount + singleCount) % 8;
			if(room == 0) room = 8;
			roomId = String.format("%02d", level) + "-" + String.format("%02d", room);
		}
		
		//Single
		else if(roomType.equals("Single") && singleCount < 20) {
			singleCount++;
			bedType = "1 Single Bed";
			level = (doubleCount + singleCount) / 8 + 2;
			room = (doubleCount + singleCount) % 8;
			if(room == 0) room = 8;
			roomId = String.format("%02d", level) + "-" + String.format("%02d", room);
		}
		else {
			System.out.println("No room available. Sorry");
			return 0;
		}
		
			for(int i = 0; i<hotelRoom.size(); i++) {
				if(hotelRoom.get(i).roomId.equals(roomId)) {
					hotelRoom.get(i).levelNo = level;
					hotelRoom.get(i).roomNo = room;
					hotelRoom.get(i).bedType = bedType;
					hotelRoom.get(i).roomType = roomType;
					
					System.out.println("Do you wish to have city view? Y: Yes");
					if(sc.nextLine().equals("Y")) hotelRoom.get(i).cityView = true;
					System.out.println("Do you wish to have breakfast included? Y: Yes");
					if(sc.nextLine().equals("Y")) hotelRoom.get(i).breakfast = true;
					System.out.println("Do you wish to have WiFi enabled? Y: Yes");
					if(sc.nextLine().equals("Y")) hotelRoom.get(i).wifi = true;
					System.out.println("Do you wish to have smoking area? Y: Yes");
					if(sc.nextLine().equals("Y")) hotelRoom.get(i).smoking = true;
					System.out.println("Your room is " + hotelRoom.get(i).roomId);
		
					hotelRoom.get(i).guestIc = guestIC;
					hotelRoom.get(i).status = "Reserved";

					
					System.out.println("Your room is " + hotelRoom.get(i).roomId + "Thank you.");
					
					try {
						db.saveClass("room.txt", hotelRoom);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} //to read data from files
					
					
					
					return 1; //assigned

					return 1;
					
				}
			}
			return 0;
	}
	
	public Room assignRoomWithIdReturn(String guestIC)// do method of overloading to get the roomid
	{
		int level, room;
		String roomId, bedType, roomType;
		
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
			bedType = "2 King Beds";
			level = 7;
			room = vipCount;
			System.out.println("here");
			roomId = String.format("%02d", level) + "-" + String.format("%02d", room);
		}
		
		//Deluxe
		else if(roomType.equals("Deluxe") && deluxeCount < 6) {
			deluxeCount++;
				System.out.println("Would you rather 2 Double Beds? Y:Yes");
				if(sc.nextLine() == "Y") 
					bedType = "2 Double Beds";
				else 
					bedType = "1 King Bed";
			level = 7;
			room = deluxeCount + 2;
			roomId = String.format("%02d", level) + "-" + String.format("%02d", room);
		}
		
		//Double
		else if(roomType.equals("Double") && doubleCount < 20) {
			doubleCount++;
				System.out.println("Would you rather 2 Single Beds? Y:Yes");
				if(sc.nextLine() == "Y") 
					bedType = "2 Single Beds";
				else 
					bedType = "1 Double Bed";
			level = (doubleCount + singleCount) / 8 + 2;
			room = (doubleCount + singleCount) % 8;
			if(room == 0) room = 8;
			roomId = String.format("%02d", level) + "-" + String.format("%02d", room);
		}
		
		//Single
		else if(roomType.equals("Single") && singleCount < 20) {
			singleCount++;
			bedType = "1 Single Bed";
			level = (doubleCount + singleCount) / 8 + 2;
			room = (doubleCount + singleCount) % 8;
			if(room == 0) room = 8;
			roomId = String.format("%02d", level) + "-" + String.format("%02d", room);
		}
		else {
			System.out.println("No room available. Sorry");
			return null;
		}
		
			for(int i = 0; i<hotelRoom.size(); i++) {
				if(hotelRoom.get(i).roomId.equals(roomId)) {
					hotelRoom.get(i).levelNo = level;
					hotelRoom.get(i).roomNo = room;
					hotelRoom.get(i).bedType = bedType;
					hotelRoom.get(i).roomType = roomType;
					
					System.out.println("Do you wish to have city view? Y: Yes");
					if(sc.nextLine().equals("Y")) hotelRoom.get(i).cityView = true;
					System.out.println("Do you wish to have breakfast included? Y: Yes");
					if(sc.nextLine().equals("Y")) hotelRoom.get(i).breakfast = true;
					System.out.println("Do you wish to have WiFi enabled? Y: Yes");
					if(sc.nextLine().equals("Y")) hotelRoom.get(i).wifi = true;
					System.out.println("Do you wish to have smoking area? Y: Yes");
					if(sc.nextLine().equals("Y")) hotelRoom.get(i).smoking = true;
					System.out.println("Your room is " + hotelRoom.get(i).roomId);
		
					hotelRoom.get(i).guestIc = guestIC;
					hotelRoom.get(i).status = "Reserved";

					
					System.out.println("Your room is " + hotelRoom.get(i).roomId + "Thank you.");
					
					try {
						db.saveClass("room.txt", hotelRoom);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} //to read data from files
					
					
					
					return hotelRoom.get(i); //Please return room
					
				}
			}
			return null;
	}
	
	
	public void checkIn(String roomId)
	{
		boolean checked = false;
		for(int i = 0; i<hotelRoom.size(); i++) {
			if(hotelRoom.get(i).roomId.equals(roomId)) {
				hotelRoom.get(i).status = "Occupied";
				checked = true;
				System.out.println("Successfully checked in");
			}
		}
		if(checked == false) System.out.println("Failed to check in");
	}
	
	public void checkOut(String roomId)
	{
		boolean checked = false;
		for(int i = 0; i<hotelRoom.size(); i++) {
			if(hotelRoom.get(i).roomId.equals(roomId)) {
				hotelRoom.get(i).status = "Vacant";
				hotelRoom.get(i).guestIc = null;
				hotelRoom.get(i).rate = 0;
				hotelRoom.get(i).breakfast = false;
				hotelRoom.get(i).cityView = false;
				hotelRoom.get(i).wifi = false;
				hotelRoom.get(i).smoking = false;
				if(hotelRoom.get(i).roomType.equals("Single")) singleCount--;
				else if(hotelRoom.get(i).roomType.equals("Double")) doubleCount--;
				else if(hotelRoom.get(i).roomType.equals("Deluxe")) deluxeCount--;
				else if(hotelRoom.get(i).roomType.equals("VIP")) vipCount--;
				hotelRoom.get(i).roomType = null;
				hotelRoom.get(i).bedType = null;
				checked = true;
				System.out.println("Successfully checked out");
			}
		}
		if(checked == false) System.out.println("Failed to check out");
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
			System.out.println("5. Smoking");
			System.out.println("6. Wifi Enabled");
			System.out.println("7. City View");
			System.out.println("8. Breakfast");
			System.out.println("9. Quit");
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
				case 1: System.out.println("Please enter the updated Guest IC: ");
						_hotelRoom.guestIc = sc.nextLine();
						break;
				case 2: System.out.println("Please enter the updated Status: ");
						_hotelRoom.status = checkInput(sc.nextLine(), 3);
						break;
				case 3: System.out.println("Please enter the updated Room Type: ");
						_hotelRoom.roomType = checkInput(sc.nextLine(), 1);
						break;
				case 4: System.out.println("Please enter the updated Bed Type: ");
						_hotelRoom.bedType = checkInput(sc.nextLine(), 2);
						break;
				case 5: System.out.println("Change smoking option from " + _hotelRoom.smoking + " to " + !_hotelRoom.smoking + "?");
						System.out.println("Y: Yes, N: No");
						if(sc.nextLine().equals("Y")) _hotelRoom.smoking = !_hotelRoom.smoking;
						break;
				case 6: System.out.println("Change WiFi option from " + _hotelRoom.wifi + " to " + !_hotelRoom.wifi + "?");
						System.out.println("Y: Yes, N: No");
						if(sc.nextLine().equals("Y")) _hotelRoom.wifi = !_hotelRoom.wifi;
						break;
				case 7: System.out.println("Change city view option from " + _hotelRoom.cityView + " to " + !_hotelRoom.cityView + "?");
						System.out.println("Y: Yes, N: No");
						if(sc.nextLine().equals("Y")) _hotelRoom.cityView = !_hotelRoom.cityView;
						break;
				case 8: System.out.println("Change breakfast option from " + _hotelRoom.breakfast + " to " + !_hotelRoom.breakfast + "?");
						System.out.println("Y: Yes, N: No");
						if(sc.nextLine().equals("Y")) _hotelRoom.breakfast = !_hotelRoom.breakfast;
						break;
				case 9: System.out.println("Bye");
						break;
				default: System.out.println("Invalid option. Please try again.");
			}
		} while (choice < 9);
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
	
	public void displayRoom(Room _hotelRoom)
	{
		System.out.println("Room Id: " + _hotelRoom.roomId);
		System.out.println("Guest IC: " + _hotelRoom.guestIc);
		System.out.println("Room Type: " + _hotelRoom.roomType);
		System.out.println("Bed Type: " + _hotelRoom.bedType);
		System.out.println("Smoking: " + _hotelRoom.smoking);
		System.out.println("City View: " + _hotelRoom.cityView);
		System.out.println("Wifi enabled: " + _hotelRoom.wifi);
		System.out.println("Breakfast Included: " + _hotelRoom.breakfast);
	}
	
	public int checkAvailability()
	{
		String check;
		System.out.println("Please enter the room ID or guest IC: ");
		check = sc.nextLine();
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
		System.out.println("1. Room Type Occupancy Rate");
		System.out.println("2. Room Status");
		choice = sc.nextInt();
		
		switch(choice) {
		case 1:
			for(int i = 0; i<hotelRoom.size(); i++) {
				if(hotelRoom.get(i).roomType.equals("Single")) 
					one.append(hotelRoom.get(i).roomId + "|");
				else if(hotelRoom.get(i).roomType.equals("Double")) 
					two.append(hotelRoom.get(i).roomId + "|");
				else if(hotelRoom.get(i).roomType.equals("Deluxe")) 
					three.append(hotelRoom.get(i).roomId + "|");
				else if(hotelRoom.get(i).roomType.equals("VIP")) 
					four.append(hotelRoom.get(i).roomId + "|");
			}
			
			System.out.println("Single: " + singleCount + " out of 20");
			System.out.println("Rooms: " + one);
			System.out.println("Double: " + doubleCount + " out of 20");
			System.out.println("Rooms: " + two);
			System.out.println("Deluxe: " + deluxeCount + " out of 6");
			System.out.println("Rooms: " + three);
			System.out.println("VIP: " + vipCount + " out of 2");
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
	
	private String checkInput(String input, int type)
	{
		boolean result = false;
		switch (type) {
			case 1: if(input.equals("Single") || input.equals("Double") || input.equals("Deluxe") || input.equals("VIP")) result = true;
					break;
			case 2: if(input.equals("1 Single Bed") || input.equals("2 Single Beds") || input.equals("1 Double Bed") || input.equals("2 Double Beds") || input.equals("1 King Bed") || input.equals("2 King Beds")) result = true;
					break;
			case 3: if(input.equals("Vacant") || input.equals("Occupied" ) || input.equals("Reserved") || input.equals("Under Maintenance")) result = true;
					break;
		}
		if(result == false) {
			System.out.println("Invalid input. Please try again.");
			return null;
		}
		else return input;
	}

}
