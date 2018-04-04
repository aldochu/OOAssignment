package HRPS;

import java.util.Scanner;

public class RoomApp {
	
	private Room[] hotelRoom = new Room[48]; //roomId
	private float occupancyPercentage;
	Scanner sc = new Scanner(System.in);
	
	public int newRoom(String name, String roomType, String bedType, boolean wifi, boolean smoking, boolean cityView, boolean breakkie)
	{
		//48 rooms; 6 floors - level 2 to 7; 4 rooms each floor
		for(int i = 0; i<hotelRoom.length; i++) {
			if(hotelRoom[i].status == "Available") {
				hotelRoom[i].bedType = bedType;
				hotelRoom[i].roomType = roomType;
				hotelRoom[i].guest = name;
				hotelRoom[i].cityView = cityView;
				hotelRoom[i].smoking = smoking;
				hotelRoom[i].wifi = wifi;
				hotelRoom[i].breakfast = breakkie;
				hotelRoom[i].roomNo = i%8;
				hotelRoom[i].levelNo = i/8 + 2;
				hotelRoom[i].status = "Occupied";
				return i;
			}
		}
		return -1; //no rooms available
	}
	
	public void updateRoomDetails(int roomId)
	{
		int choice;
		String change;
		do {
			System.out.println("Which information do you want to update?");
			System.out.println("1. Guest");
			System.out.println("2. Status");
			System.out.println("3. Room Type");
			System.out.println("4. Bed Type");
			System.out.println("5. Smoking");
			System.out.println("6. Wifi Enabled");
			System.out.println("7. City View");
			System.out.println("8. Breakfast");
			System.out.println("9. Quit");
			choice = sc.nextInt();
			switch (choice) {
				case 1: System.out.println("Please enter the updated Guest Name: ");
						hotelRoom[roomId].guest = sc.nextLine();
						break;
				case 2: System.out.println("Please enter the updated Status: ");
						hotelRoom[roomId].status = checkInput(sc.nextLine(), 3);
						break;
				case 3: System.out.println("Please enter the updated Room Type: ");
						hotelRoom[roomId].roomType = checkInput(sc.nextLine(), 1);
						break;
				case 4: System.out.println("Please enter the updated Bed Type: ");
						hotelRoom[roomId].bedType = checkInput(sc.nextLine(), 2);
						break;
				case 5: System.out.println("Change smoking option from " + hotelRoom[roomId].smoking + " to " + !hotelRoom[roomId].smoking + "?");
						System.out.println("Y: Yes, N: No");
						change = sc.nextLine();
						if(change == "Y") hotelRoom[roomId].smoking = !hotelRoom[roomId].smoking;
						break;
				case 6: System.out.println("Change WiFi option from " + hotelRoom[roomId].wifi + " to " + !hotelRoom[roomId].wifi + "?");
						System.out.println("Y: Yes, N: No");
						change = sc.nextLine();
						if(change == "Y") hotelRoom[roomId].wifi = !hotelRoom[roomId].wifi;
						break;
				case 7: System.out.println("Change city view option from " + hotelRoom[roomId].cityView + " to " + !hotelRoom[roomId].cityView + "?");
						System.out.println("Y: Yes, N: No");
						change = sc.nextLine();
						if(change == "Y") hotelRoom[roomId].cityView = !hotelRoom[roomId].cityView;
						break;
				case 8: System.out.println("Change breakfast option from " + hotelRoom[roomId].breakfast + " to " + !hotelRoom[roomId].breakfast + "?");
						System.out.println("Y: Yes, N: No");
						change = sc.nextLine();
						if(change == "Y") hotelRoom[roomId].breakfast = !hotelRoom[roomId].breakfast;
						break;
				case 9: System.out.println("Bye");
						break;
				default: System.out.println("Invalid option. Please try again.");
			}
		} while (choice < 9);
	}
	
	public void calculateRate(int roomId)
	{
		//per night
		//Room type: single = 100, double = 150, deluxe = 200, vip = 300
		//Bed type: single = 50, double = 75, queen = 100, king = 150
		//no smoking area = 25
		//wifi = 10
		//city view = 25
		//breakfast = 15
		double result = 0;
		
		if(hotelRoom[roomId].roomType == "Single") result += 100;
		else if(hotelRoom[roomId].roomType == "Double") result += 150;
		else if(hotelRoom[roomId].roomType == "Deluxe") result += 200;
		else if(hotelRoom[roomId].roomType == "VIP") result += 300;
		
		if(hotelRoom[roomId].bedType == "Single") result += 50;
		else if(hotelRoom[roomId].bedType == "Double") result += 75;
		else if(hotelRoom[roomId].bedType == "Queen") result += 100;
		else if(hotelRoom[roomId].bedType == "King") result += 150;
		
		if(hotelRoom[roomId].smoking == false) result += 25;
		
		if(hotelRoom[roomId].wifi == true) result += 10;
		
		if(hotelRoom[roomId].cityView == true) result += 25;
		
		if(hotelRoom[roomId].breakfast == true) result += 15;
		
		hotelRoom[roomId].rate = result;
	}
	
	public void displayRoom(int roomId)
	{
		System.out.println("Room number: " + String.format("%02d", hotelRoom[roomId].levelNo) + "-" + String.format("%02d", hotelRoom[roomId].roomNo));
		System.out.println("Guest: " + hotelRoom[roomId].guest);
		System.out.println("Room Type: " + hotelRoom[roomId].roomType);
		System.out.println("Bed Type: " + hotelRoom[roomId].bedType);
		System.out.println("Smoking: " + hotelRoom[roomId].smoking);
		System.out.println("City View: " + hotelRoom[roomId].cityView);
		System.out.println("Wifi enabled: " + hotelRoom[roomId].wifi);
		System.out.println("Breakfast Included: " + hotelRoom[roomId].breakfast);
	}
	
	public int checkAvailability(int roomId)
	{
		//return 1 for vacant, 0 for unavailable
		if(hotelRoom[roomId].status == "Vacant") {
			System.out.println("Room number: " + String.format("%02d", hotelRoom[roomId].levelNo) + "-" + String.format("%02d", hotelRoom[roomId].roomNo) + " is Available");
			return 1;
		}
		else {
			System.out.println("Sorry, Room number: " + String.format("%02d", hotelRoom[roomId].levelNo) + "-" + String.format("%02d", hotelRoom[roomId].roomNo) + " is " + hotelRoom[roomId].status);
			return 0;
		}
	}
	
	public void roomOccupancyReport()
	{
		int count=0;
		for(int i=1; i<=hotelRoom.length; i++) {
			if(hotelRoom[i].status == "Occupied") count++;
		}
		occupancyPercentage = (float)(count/hotelRoom.length) * 100;
		System.out.println("Occupancy Percentage: " + occupancyPercentage + "%");
	}
	
	public void roomStatusReport(String status)
	{
		int count = 0;
		int[] list = {};
		System.out.println(status + " : ");
		for(int i = 0; i<hotelRoom.length; i++) {
			if(hotelRoom[i].status == status) {
				list[count] = i;
				count++;
				System.out.print(String.format("%02d", hotelRoom[i].levelNo) + "-" + String.format("%02d", hotelRoom[i].roomNo));
			}
		}
	}
	
	private String checkInput(String input, int type)
	{
		boolean result = false;
		switch (type) {
			case 1: if(input == "Single" || input == "Double" || input == "Deluxe" || input == "VIP") result = true;
					break;
			case 2: if(input == "Single" || input == "Double" || input == "Queen" || input == "King") result = true;
					break;
			case 3: if(input == "Vacant" || input == "Occupied" || input == "Reserved" || input == "Under Maintenance") result = true;
					break;
		}
		if(result == false) {
			System.out.println("Invalid input. Please try again.");
			return null;
		}
		else return input;
	}

}
