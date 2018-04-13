package HRPS;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class HrpsSystem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RoomApp roomController = new RoomApp();
		Scanner sc = new Scanner(System.in);
		int choice;
		GuestApp guestController = new GuestApp();
		PromoApp promoController = new PromoApp();

		PaymentApp payController = new PaymentApp();

		ReservationApp ReservationController = new ReservationApp();
		RegistrationApp RegistrationController = new RegistrationApp();
		//RoomServiceApp roomSvcController = new RoomServiceApp();

		
		
		ReservationApp.DemoScheduler(roomController.updateToReserved(guestId));
		
		
//		do {
//		System.out.println("Perform the following methods:");
//			System.out.println("1: Add guest");
//			System.out.println("2: Update guest");
//			System.out.println("3: Show guest details");
//			System.out.println("4: Show the list of customers together with their seat numbers in the order of the customer ID");
//			System.out.println("5: Assign a customer to a seat");
//			System.out.println("6: Remove a seat assignment");
//			System.out.println("7: quit");
//			choice = sc.nextInt();
//			switch (choice) {
//			 case 1: 
//				 guestController.createGuest();
//			 break;
//			 
//			 case 2: 
//				 guestController.updateGuest();
//			 break;
//			 
//			 case 3: 
//				 guestController.printGuestDetail();
//			 break;
//			 
//			 case 4:  add countDigits() call 
//			 break;
//			 
//			 case 5:  add position() call 
//			 break;
//			 
//			 case 6: 
//			 break; 
//			 case 7: System.out.println("Program terminating ….");
//			}
//			} while (choice < 7);
		

//		do {
//			System.out.println("Perform the following methods:");
//			System.out.println("1: Add Payment");
//			System.out.println("2: Show todays Payments");
//			System.out.println("4: quit");
//			choice = sc.nextInt();
//			switch (choice) {
//			 case 1: 
//				 payController.createPayment();
//			 break;
//			 
//			 case 2: 
//				 payController.printPayments();
//				 break;
//			 
//			 case 3: 
//				 break;
//			 case 4: System.out.println("Program terminating ….");
//			}
//			} while (choice < 4);
//		do 
//		{
//			System.out.println("Perform the following methods:");
//			System.out.println("1: Add Payment");
//			System.out.println("2: Show Todays Payments");
//			System.out.println("3: Show Past Payments On Date");
//			System.out.println("4: Show All Payments");
//			System.out.println("5: Show Occupancy Report On Date");
//			System.out.println("7: quit");
//			choice = sc.nextInt();
//			switch (choice) {
//			 case 1: 
//				 boolean g;
//				 String resId;
//				 System.out.println("Enter Guest IC");
//				 sc.nextLine();
//				 String guestId = sc.nextLine();
//				 g = payController.checkGuest(guestController.SearchGuestByIc(guestId));
//				 if(g == false)
//				 {
//					 System.out.println("Guest " + guestId + " does not exist");
//					 break;
//				 }
//				 
//				 g = payController.checkRes(ReservationController.SearchResByGuestId(guestId));
//				 if(g == false)
//				 {
//					 System.out.println("Reservation for " + guestId + " does not exist");
//					 break;
//				 }
//				 
//				 g = payController.checkRoom(roomController.getRoomDetails(guestId));
//				 if(g == false)
//				 {
//					 System.out.println("Guest " + guestId + " is not assigned to any room");
//					 break;
//				 }
//				 else
//				 {
//					 resId = ReservationController.SearchResByGuestId(guestId).res_id; 
//				 }
//				 
//				 g = payController.createPayment(guestId);
//				 if(g == false)
//				 {
//					 System.out.println("Payment Unsuccessful");
//				 }
//				 else
//				 {
//					 System.out.println("Payment Successful");
//					 ReservationController.checkOut(resId);
//				 }
//			 break;
//			 
//			 case 2: 
//				 payController.printTodayPayments();
//				 break;
//
//			 case 3: 
//				 payController.printPastPayments();
//				 break;
//			 case 4: 
//				 payController.printAllPayments();
//				 break;
//			 case 5: 
//				 payController.printOccupancyReportByDate();
//				 break;
//			 case 7: System.out.println("Program terminating ….");
//			}
//			} while (choice < 7);
		do 
		{
			System.out.println("Perform the following methods:");
			System.out.println("1: Create walk in");
			System.out.println("2: Add Reservation");
			System.out.println("3: Update Reservation");
			System.out.println("4: Print Reservation");
			System.out.println("5: Check status");
			choice = sc.nextInt();
			switch (choice) 
			{
			 case 1:
				 boolean g;
				 boolean rmno;
//				 System.out.println("Please enter the guest id");
//				 sc.nextLine();
//					String guestId = sc.nextLine();
//				 g=ReservationController.getuser(guestController.SearchGuestByIc(guestId));
//				 if(g==false)
//					 System.out.println("Guest does not exists");
//				 
//				 rmno=ReservationController.getRoomId(roomController.assignRoom(guestId));
//				 if(g==true) {
//					 System.out.println("Room number in reservation");
//				 }
				 System.out.println("Please enter the guest id");
				 sc.nextLine();
					String guestId = sc.nextLine();
					g=ReservationController.getuser(guestController.SearchGuestByIc(guestId));
				 if(g==false)
					 System.out.println("Guest does not exists");
				 Room newRegRoom = roomController.assignRoomWithIdReturn(guestId);				 
				 RegistrationController.createRegistration(guestId, newRegRoom.roomId);
			
				 
			 break;
			 
			 case 2:
				 
				 System.out.println("Please enter the guest id");
				 sc.nextLine();
					guestId = sc.nextLine();
				 g=ReservationController.getuser(guestController.SearchGuestByIc(guestId));
				 if(g==false)
					 System.out.println("Guest does not exists");
				 
				 Room newRoom = roomController.assignRoomWithIdReturn(guestId);				 
				 boolean vaidRoom=ReservationController.checkRoom(newRoom);
				 if(vaidRoom==false) {
					 System.out.println("Room number in reservation");
					 
				 }
				
				 
				 ReservationController.createRes(guestId,newRoom.roomId);
				 
				 
				 break;
			 
			 case 3: 
				 ReservationController.updateRes();
			 break;
			 
			 case 4: 
				 ReservationController.printResOnly();
			 break;
			 case 5: 
				 
				 
				 System.out.println("Please enter the guest id");
				 sc.nextLine();
					guestId = sc.nextLine();
				 g=ReservationController.getuser(guestController.SearchGuestByIc(guestId));
				 if(g==false)
					 System.out.println("Guest does not exists");
				 
				 Room waitlistRoom = roomController.assignRoomWithIdReturn(guestId);				 
				 boolean waitRoom=ReservationController.checkRoom(waitlistRoom);
				 if(vaidRoom==false) {
					 System.out.println("Room number in reservation");
					 
				 }
				 else {
					 
				 }
				
				 
				 ReservationController.createRes(guestId,newRoom.roomId);
				 
				 
				
			 break;
			 case 6:
				 System.exit(0);
				 break;
			} 	
		}while (choice < 6);

//		do {
//			System.out.println("Perform the following methods:");
//			System.out.println("1: Add food catalogue");
//			System.out.println("2: Update food catalogue");
//			System.out.println("3: Show list of foods(name,description,price)");
//			System.out.println("4: Add order");
//			System.out.println("5: Update order status");
//			System.out.println("6: Remove Order ");
//			System.out.println("7: quit");
//			choice = sc.nextInt();
//			switch (choice) {
//				 case 1: 
//				 {
//					 roomSvcController.createFoodData();
//				     break;
//				 }
//				 case 2:
//				 {
//					 roomSvcController.updateFood();
//				     break;
//				 }
//				 case 3:
//				 {
//					 roomSvcController.printFoodList();
//				     break;
//				 }
//				 case 4:
//				 {
//					 roomSvcController.createOrder();
//					 break;
//				 }
//				 case 5:
//				 {
//					 roomSvcController.updateOrder();
//					 break;
//				 }
//				 case 6: 
//				 {
//					 roomSvcController.removeOrder();
//					 break;
//				 }
//				 case 7:
//	                 System.out.println("Program terminating ….");
//				}
//			}
//			  while (choice < 7);
//		 }
//>>>>>>> adding classes for room service, will update further changes soon
//=======

//		Room temp = new Room();
//		do {
//			System.out.println("Perform the following methods:");
//			System.out.println("1: Assign Room");
//			System.out.println("2: Update Room");
//			System.out.println("3: Check Availability");
//			System.out.println("4: Room Status Statistic Report");
//			System.out.println("5: Calculate Rate");
//			System.out.println("6: Print Room");
//			System.out.println("7. Check In");
//			System.out.println("8: Quit");
//			choice = sc.nextInt();
//			switch (choice) {
//			 case 1: 
//				 System.out.println("Please enter your NRIC: ");
//				 roomController.assignRoom(sc.next());
//			 break;
//			 
//			 case 2: 
//				 roomController.updateRoom();
//				 break;
//			 
//			 case 3: 
//				 roomController.checkAvailability();
//				 break;
//			 case 4:
//				 roomController.roomStatisticReport();
//				 break;
//				 
//			 case 5:
//				 String guestIc;
//				 System.out.println("Please enter guest IC: ");
//				guestIc = sc.nextLine();
//				 roomController.calculateRate(guestIc);
//				 break;
//				 
//			 case 6: 
//				 roomController.displayRoom(sc.nextLine());
//				 break;
//				 
//			 case 7:
//				 System.out.println("Please enter the room ID for checking in: ");
//				 roomController.checkIn(sc.nextLine());
//				 break;
//				 
//			 case 8: System.out.println("Program terminating ….");
//			}
//			} while (choice < 8); 
//
//	} 

	}

}