package HRPS;
import java.util.Scanner;

public class HrpsSystem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RoomApp roomController = new RoomApp();
		Scanner sc = new Scanner(System.in);
		int choice;
/*		GuestApp guestController = new GuestApp();
		PromoApp promoController = new PromoApp();

		PaymentApp payController = new PaymentApp();

		ReservationApp ReservationController = new ReservationApp();

		RoomServiceApp roomSvcController = new RoomServiceApp();

		
		

		
		
		do {
		System.out.println("Perform the following methods:");
			System.out.println("1: Add guest");
			System.out.println("2: Update guest");
			System.out.println("3: Show guest details");
			System.out.println("4: Show the list of customers together with their seat numbers in the order of the customer ID");
			System.out.println("5: Assign a customer to a seat");
			System.out.println("6: Remove a seat assignment");
			System.out.println("7: quit");
			choice = sc.nextInt();
			switch (choice) {
			 case 1: 
				 guestController.createGuest();
			 break;
			 
			 case 2: 
				 guestController.updateGuest();
			 break;
			 
			 case 3: 
				 guestController.printGuestDetail();
			 break;
			 
			 case 4:  add countDigits() call 
			 break;
			 
			 case 5:  add position() call 
			 break;
			 
			 case 6: 
			 break; 
			 case 7: System.out.println("Program terminating ….");
			}
			} while (choice < 7);
		
		do {
			System.out.println("Perform the following methods:");
			System.out.println("1: Add Promo");
			System.out.println("2: Delete Promo");
			System.out.println("3: Show all Promo");
			System.out.println("4: quit");
			choice = sc.nextInt();
			switch (choice) {
			 case 1: 
				 payController.createPayment();
			 break;
			 
			 case 2: 
				 payController.printPayments();
				 break;
			 
			 case 3: 
				 break;
			 case 4: System.out.println("Program terminating ….");
			}
<<<<<<< HEAD
			} while (choice < 4);
<<<<<<< Updated upstream

//		do 
//		{
//			System.out.println("Perform the following methods:");
//			System.out.println("1: Add Payment");
//			System.out.println("2: Display todays bills");
//			choice = sc.nextInt();
//			switch (choice) 
//			{
//			 case 1:
//				 ReservationController.createRes();
//			 break;
//			 
//			 case 2: 
//				 ReservationController.updateRes();
//			 break;
//			 
//			 case 3: 
//				 ReservationController.printResOnly();
//			 break;
//			 case 4:
//				 System.exit(0);
//				 break;
//			} 	
//		}while (choice < 4);
=======
		do {
			System.out.println("Perform the following methods:");
			System.out.println("1: Add food catalogue");
			System.out.println("2: Update food catalogue");
			System.out.println("3: Show list of foods(name,description,price)");
			System.out.println("4: Add order");
			System.out.println("5: Update order status");
			System.out.println("6: Remove Order ");
			System.out.println("7: quit");
			choice = sc.nextInt();
			switch (choice) {
				 case 1: 
				 {
					 roomSvcController.createFoodData();
				     break;
				 }
				 case 2:
				 {
					 roomSvcController.updateFood();
				     break;
				 }
				 case 3:
				 {
					 roomSvcController.printFoodList();
				     break;
				 }
				 case 4:
				 {
					 roomSvcController.createOrder();
					 break;
				 }
				 case 5:
				 {
					 roomSvcController.updateOrder();
					 break;
				 }
				 case 6: 
				 {
					 roomSvcController.removeOrder();
					 break;
				 }
				 case 7:
	                 System.out.println("Program terminating ….");
				}
			}
			  while (choice < 7);
		 }
>>>>>>> adding classes for room service, will update further changes soon
=======
*/
		Room temp = new Room();
		do {
			System.out.println("Perform the following methods:");
			System.out.println("1: Assign Room");
			System.out.println("2: Update Room");
			System.out.println("3: Check Availability");
			System.out.println("4: Room Status Statistic Report");
			System.out.println("5: Calculate Rate");
			System.out.println("6: Print Room");
			System.out.println("7. Check In");
			System.out.println("8: Quit");
			choice = sc.nextInt();
			switch (choice) {
			 case 1: 
				 System.out.println("Please enter your NRIC: ");
				 roomController.assignRoom(sc.nextLine());
			 break;
			 
			 case 2: 
				 roomController.updateRoom();
				 break;
			 
			 case 3: 
				 roomController.checkAvailability();
				 break;
			 case 4:
				 roomController.roomStatisticReport();
				 break;
				 
			 case 5:
				 String guestIc;
				 System.out.println("Please enter guest IC: ");
				guestIc = sc.nextLine();
				 roomController.calculateRate(guestIc);
				 break;
				 
			 case 6: 
				 roomController.displayRoom(temp);
				 break;
				 
			 case 7:
				 System.out.println("Please enter the room ID for checking in: ");
				 roomController.checkIn(sc.nextLine());
				 break;
				 
			 case 8: System.out.println("Program terminating ….");
			}
			} while (choice < 8);

	}

}
