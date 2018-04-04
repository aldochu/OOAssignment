package HRPS;
import java.util.Scanner;

public class HrpsSystem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GuestApp guestController = new GuestApp();
		PromoApp promoController = new PromoApp();
		
		Scanner sc = new Scanner(System.in);
		int choice;
		
//		do {
//			System.out.println("Perform the following methods:");
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
//			 case 4: /* add countDigits() call */
//			 break;
//			 
//			 case 5: /* add position() call */
//			 break;
//			 
//			 case 6: 
//			 break; 
//			 case 7: System.out.println("Program terminating ….");
//			}
//			} while (choice < 7);
		
		do {
			System.out.println("Perform the following methods:");
			System.out.println("1: Add Promo");
			System.out.println("2: Delete Promo");
			System.out.println("3: Show all Promo");
			System.out.println("4: quit");
			choice = sc.nextInt();
			switch (choice) {
			 case 1: 
				 promoController.createPromo();
			 break;
			 
			 case 2: 
				 promoController.deletePromo();
				 break;
			 
			 case 3: 
				 promoController.printPromo();
				 break;
			 case 4: System.out.println("Program terminating ….");
			}
			} while (choice < 4);

	}

}
