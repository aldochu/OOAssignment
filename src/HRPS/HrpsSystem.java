package HRPS;
import java.text.ParseException;
import java.util.Scanner;

public class HrpsSystem {

	public static void main(String[] args) {
		int choice;
		RoomApp roomController = new RoomApp();
		Scanner sc = new Scanner(System.in);
		GuestApp guestController = new GuestApp();
		PromoApp promoController = new PromoApp();
		PaymentApp payController = new PaymentApp();
		ReservationApp ReservationController = new ReservationApp();
		RegistrationApp RegistrationController = new RegistrationApp();
		RoomServiceApp roomSvcController = new RoomServiceApp();
		
		do {
			ReservationApp.DemoScheduler();
			System.out.println("Choose the following methods:");
				System.out.println("1: Guests Management");
				System.out.println("2: Reservations/Walk In Management");
				System.out.println("3: Payments Management");
				System.out.println("4: Rooms Management");
				System.out.println("5: Room Service Management");
				System.out.println("6: Promo Codes Management");
				System.out.println("7: Exit");
				choice = sc.nextInt();
				switch (choice) {
				 case 1:
					 do {
							System.out.println("Perform the following methods:");
							System.out.println("1: Add guest");
							System.out.println("2: Update guest");
							System.out.println("3: Show guest details");
							System.out.println("4: Return");
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
							 case 4:
								 break;
								 
							default: System.out.println("invalid input");
							}
						} while (choice != 4);
					 break;
		 case 2 :
			do 
			{
			ReservationApp.DemoScheduler();
			System.out.println("Perform the following methods:");
			System.out.println("1: Create walk in");
			System.out.println("2: Add Reservation");
			System.out.println("3: Update Reservation");
			System.out.println("4: Print Reservation");
			System.out.println("5: Delete Reservation");
			System.out.println("6: Return to menu");
			choice = sc.nextInt();
			switch (choice) 
			{
			 case 1:
				 boolean g;
				 String guestId;
				 System.out.println("Please enter the guest id");
					guestId = sc.next();
					g=ReservationController.getuser(guestController.SearchGuestByIc(guestId));
				 if(g==false)
					 System.out.println("Guest does not exists");
				 Room newRegRoom = roomController.assignRoom(guestId);
				 try {
					 System.out.println(guestId);
					 System.out.println(newRegRoom.roomId);
					 System.out.println(newRegRoom.roomType);
					RegistrationController.createRegistration(guestId, newRegRoom.roomId, newRegRoom.roomType);
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	 
			 break;
			 
			 case 2:
				 do
				 {
					 System.out.println("Please enter the guest id");
					 guestId = sc.next();
					 g=ReservationController.getuser(guestController.SearchGuestByIc(guestId));
					 
					 if(g == false)
					 {
						 System.out.println("Guest does not exist. Enter Again");
					 }
					 else
					 {
						 ReservationController.createRes(guestId);
						 break;
					 }
				 }while(g == false);
			break;
			case 3: 
				ReservationController.updateRes();
				break;
			case 4: 
				ReservationController.printResOnly();
				break;
			case 5: 
				ReservationController.deleteRes();
				break;							
			case 6:
				break;
				default: System.out.println("invalid input");
					}
				 } while (choice != 6);
				 break;
				 
				 case 3: 
				 do 
				 {
					 System.out.println("Perform the following methods:");
					 System.out.println("1: Add Payment");
					 System.out.println("2: Show Todays Payments");
					 System.out.println("3: Show Past Payments On Date");
					 System.out.println("4: Show All Payments");
					 System.out.println("5: Show Occupancy Report On Date");
					 System.out.println("6: Return");
					 choice = sc.nextInt();
					 switch (choice) {
					 case 1: 
					 	 boolean g;
					 	 int flag = 0;
					 	 String resId,roomId,guestId;
					 	do
						 {
						 	 System.out.println("Enter Guest IC");
						 	 guestId = sc.nextLine();
						 	 g = payController.checkGuest(guestController.SearchGuestByIc(guestId));
							 
							 if(g == false)
							 {
								 System.out.println("Guest does not exist. Enter Again");
							 }
							 else
							 {
								 break;
							 }
						 }while(g == false);
						 		 
						 g = payController.checkRes(ReservationController.SearchResByGuestId(guestId));
						 if(g == false)
						 {
							 System.out.println("Reservation for " + guestId + " does not exist or Guest status is not checked in");
							 System.out.println("Checking Walk In for " + guestId);
							 
							 g = payController.checkReg(RegistrationController.SearchRegByGuestId(guestId));
							 if(g == false)
							 {
								 System.out.println("Walk In for " + guestId + " does not exist or Guest status is not checked in");
								 break;
							 }
							 else 
							 {
								 resId = RegistrationController.SearchRegByGuestId(guestId).res_id; 
								 roomId = RegistrationController.SearchRegByGuestId(guestId).room_id;
								 flag = 1;
							 }
						 }
						 else
						 {
							 resId = ReservationController.SearchResByGuestId(guestId).res_id; 
							 roomId = ReservationController.SearchResByGuestId(guestId).room_id; 
							 flag = 0;
						 }
								 
						 g = payController.checkRoom(roomController.getRoomDetails(guestId));
						 if(g == false)
						 {
							 System.out.println("Guest " + guestId + " is not assigned to any room");
							 break;
						 }
						 
						 g = payController.createPayment(guestId);
						 if(g == false)
						 {
							 System.out.println("Payment Unsuccessful");
						 }
						 else
						 {
							 System.out.println("Payment Successful");
							 if(flag == 1)
							 {
								 RegistrationController.checkOut(resId);
							 }
							 else if(flag == 0)
							 {
								 ReservationController.checkOut(resId);
							 }
							 roomController.checkOut(roomId);
						 }
						 break;
							 
						 case 2: 
							 payController.printTodayPayments();
							 break;
						 case 3: 
							 payController.printPastPayments();
							 break;
						 case 4: 
							 payController.printAllPayments();
							 break;
						 case 5: 
							 payController.printOccupancyReportByDate();
								 break;
						 case 6:
							 break;
						 default: System.out.println("invalid input");
					}
				 } while (choice != 6);
				 break;
				 
				 case 4:
					 do {
						 System.out.println("Perform the following methods:");
						 System.out.println("1: Assign Room");
						 System.out.println("2: Update Room");
						 System.out.println("3: Check Availability");
						 System.out.println("4: Room Status Statistic Report");
						 System.out.println("5: Calculate Rate");
						 System.out.println("6: Print Room");
						 System.out.println("7. Check In");
						 System.out.println("8. Check Out");
						 System.out.println("9: Return");
						 choice = sc.nextInt();
						 switch (choice) {
						 	 case 1: 
								 System.out.println("Please enter your NRIC: ");
								 roomController.assignRoom(sc.next());
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
								 roomController.displayRoom();
								 break;
								 
							 case 7:
								 System.out.println("Please enter the room ID for checking in: ");
								 roomController.checkIn(sc.next());
								 break;
								 
							 case 8:
								 System.out.println("Please enter the room ID for checking out: ");
								 roomController.checkOut(sc.next());
								 break;
								 
							 case 9:
								 break;
							 default: System.out.println("invalid input");
						 }
					 } while(choice != 9);
					 break;
					 
				 case 5: 
					 do
					 {
						System.out.println("Perform the following methods:");
						System.out.println("1: Add food to catalogue");
						System.out.println("2: Update food in catalogue");
						System.out.println("3: Remove food from catalogue");
						System.out.println("4: Add order");
						System.out.println("5: Update order status");
						System.out.println("6: Remove Order ");
						System.out.println("7: Return");
						choice = sc.nextInt();
						switch (choice) 
						{
							 case 1: 
								 roomSvcController.createFoodData();
								 break;
								 
							 case 2:
								 roomSvcController.updateFood();
							     break;
								 
							 case 3:
								 roomSvcController.removeFood();
							     break;
							 case 4:
								 roomSvcController.createOrder();
								 break;
							 case 5:
								 roomSvcController.updateOrder();
								 break;
							 case 6: 
								 roomSvcController.removeOrder();
								 break;
							 case 7:
								 break;
							 default: System.out.println("invalid input");
						}
					}while (choice != 7); 
				 break;
				 
				 case 6:
					 do 
					 {
						 System.out.println("Perform the following methods:");
						 System.out.println("1: Add Promo");
						 System.out.println("2: Delete Promo");
						 System.out.println("3: Show Current Promos");
						 System.out.println("4: Return");
						 choice = sc.nextInt();
						 switch (choice) 
						 {
						 	 case 1: 
						 		 promoController.createPromo();
							 break;
								 
							 case 2: 
								 promoController.deletePromo();;
								 break;
							 case 3: 
								 promoController.printPromo();
								 break;
							 case 4:
								 break;
							 default: System.out.println("invalid input");
						}
					 } while (choice != 4);
				 break; 


				 
				 case 7: System.out.println("Exit program ….");
				 break;
				 default: System.out.println("invalid input");
				}
			} while (choice != 7);
		sc.close();
	}


}