package HRPS;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class GuestApp {
	

	private Guest[] hotelGuest = new Guest[30]; //created 20 array
	private GuestData db = new GuestData();
	Scanner sc = new Scanner(System.in);
	
	///////////////////////////////////Load data from file////////////////////////////////////////
	protected GuestApp()
	{
		try {
			db.readClass("guest.txt", (Object[])hotelGuest); //to read data from files
			printGuest(hotelGuest[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	///////////////////////////////////Create Guest//////////////////////////////////////////////
	public int createGuest()
	{
		for(int i = 0;i<hotelGuest.length;i++)
		{
			if(hotelGuest[i]==null)
			{
				hotelGuest[i] = new Guest();
				return addGuestDetails(hotelGuest[i]);
			}
		}
		return 0;
	}
	
	private int addGuestDetails(Guest hotelGuest)//pass by reference
	{
		String var; //this var is for looping condition
		
		System.out.println("Please enter the guest name:");
		hotelGuest.name = sc.nextLine();
		System.out.println("Please enter the guest ic:");
		hotelGuest.ic = sc.nextLine();
		System.out.println("Please enter the guest nationality:");
		hotelGuest.nationality = sc.nextLine();
		System.out.println("Please enter the guest contact number:");
		hotelGuest.contactNo = sc.nextLine();
		System.out.println("Please enter the guest address:");
		hotelGuest.address = sc.nextLine();
		//loop
		do{
		System.out.println("Please enter the guest gender M/F:");
		var = sc.nextLine();
		if(var.equals("M"))
		{
		hotelGuest.gender = true;
		break;
		}
		else 
			hotelGuest.gender = false;
		}while(!var.equals("M") || !var.equals("M"));
		
		do{
			System.out.println("Does the guest have credit card? Y/N");
			var = sc.nextLine();
			if(var.equals("Y"))
			{
				addCCDetail(hotelGuest);
			break;
			}
			else if(var.equals("N"))
				hotelGuest.ccdetails = null;
			}while(!var.equals("Y") || !var.equals("N"));

		System.out.println("Guest created successfully!");
		
		try {
			db.saveClass("guest.txt", (Object[])this.hotelGuest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //to read data from files
		
		
		return 1; //failed to create
		
	}
	
	private void addCCDetail(Guest hotelGuest)//pass by reference
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		
		System.out.println("Please enter the name on the credit card:");
		hotelGuest.ccdetails.name = sc.nextLine();
		System.out.println("Please enter the credit card number:");
		hotelGuest.ccdetails.cardNo = sc.nextLine();
		System.out.println("Please enter the cvv2 behind the credit card:");
		hotelGuest.ccdetails.cvv2 = sc.nextLine();
		System.out.println("Please enter the type of the credit card:");
		hotelGuest.ccdetails.type = sc.nextLine();
		System.out.println("Please enter the expiry date of the credit card: mm/dd/yyyy");
		
		try {
			hotelGuest.ccdetails.expiry = df.parse(sc.nextLine());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
///////////////////////////////////End Of Create Guest//////////////////////////////////////////////
	
	
///////////////////////////////////Update Guest//////////////////////////////////////////////
	public int updateGuest()
	{
		
		System.out.println("Please enter the name of the guest:");
		String Name = sc.nextLine();
		for(int i = 0;i<hotelGuest.length;i++)
		{
			if(hotelGuest[i].name==Name)
			{
				updateGuestDetails(hotelGuest[i]); //pass by reference
				return 1;
			}
		}
		return 0;
	}
	
	private void updateGuestDetails(Guest hotelguest)
	{
		printGuest(hotelguest);
		
		int choice;
		do {
			System.out.println("Which information do you want to update:");
			System.out.println("1: Name");
			System.out.println("2: IC");
			System.out.println("3: Nationality");
			System.out.println("4: Contact number");
			System.out.println("5: Address");
			System.out.println("6: Gender");
			System.out.println("7: Credit card details");
			System.out.println("8: quit");
			choice = sc.nextInt();
			switch (choice) {
			 case 1: 
				 System.out.println("Please enter the updated name");
				 hotelguest.name = sc.nextLine();
			 break;
			 case 2: 
				 System.out.println("Please enter the updated ic");
				 hotelguest.ic = sc.nextLine();
			 break;
			 case 3: 
				 System.out.println("Please enter the updated nationality");
				 hotelguest.nationality = sc.nextLine();
			 break;
			 case 4:
				 System.out.println("Please enter the updated contact number");
				 hotelguest.contactNo = sc.nextLine();
			 break;
			 case 5: /* add position() call */
				 System.out.println("Please enter the updated address");
				 hotelguest.address = sc.nextLine();
			 break;
			 case 6: 
				 System.out.println("Please enter the  updated gender M/F");
				 hotelguest.ic = sc.next();
			 break; 
			 case 7: 
				 updateCCDetails(hotelguest);
			 break; 
			 case 8: System.out.println("return to previous");
			}
			} while (choice < 8);
	}
	
	private void updateCCDetails(Guest hotelguest)
	{
		int choice;
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		do {
			System.out.println("Which credit card information do you want to update:");
			System.out.println("1: Credit card name");
			System.out.println("2: Credit card number");
			System.out.println("3: credit card cvv2");
			System.out.println("4: credit card type");
			System.out.println("5: credit card Expiry date");
			System.out.println("6: quit");
			choice = sc.nextInt();
			switch (choice) {
			 case 1: 
				 System.out.println("Please enter the updated credit card name");
				 hotelguest.ccdetails.name = sc.nextLine();
			 break;
			 case 2: 
				 System.out.println("Please enter the updated credit card number");
				 hotelguest.ccdetails.cardNo = sc.next();
			 break;
			 case 3: 
				 System.out.println("Please enter the updated credit card cvv2 number");
				 hotelguest.ccdetails.cvv2 = sc.next();
			 break;
			 case 4:
				 System.out.println("Please enter the updated credit card type");
				 hotelguest.ccdetails.type = sc.next();
			 break;
			 case 5: /* add position() call */
				 System.out.println("Please enter the updated credit card Expiry date mm/dd/yyyy");
				 try {
					 hotelguest.ccdetails.expiry = df.parse(sc.next());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	 
			 break; 
			 case 6: System.out.println("return to previous");
			}
			} while (choice < 7);
	}
	
	
///////////////////////////////////End of Update Guest//////////////////////////////////////////////
	
	public Guest SearchGuest(String name)
	{
		System.out.println("Please enter the name of the guest:");
		String Name = sc.nextLine();
		for(int i = 0;i<hotelGuest.length;i++)
		{
			if(hotelGuest[i].name==Name)
			{
				return hotelGuest[i]; //Search Successfully
			}
		}
		return null; //failed to create
	}
	
	private void printGuest(Guest hotelguest)
	{
		if(hotelguest == null)
			return;
		System.out.println("Guest name: " + hotelguest.name);
		System.out.println("Guest ic: " + hotelguest.ic);
		System.out.println("Guest nationality: " + hotelguest.nationality);
		System.out.println("Guest contact number: " + hotelguest.contactNo);
		System.out.println("Guest address: " + hotelguest.address);
		
		if(hotelguest.gender == true)
			System.out.println("Guest gender: M");
		else
			System.out.println("Guest gender: F");
		
		if(hotelguest.ccdetails != null)
		{
			printGuestCC(hotelguest);
		}
		else
			System.out.println("Guest credit card detail: - ");
		
	}
	
	private void printGuestCC(Guest hotelguest)
	{
		System.out.println("Guest credit card name: " + hotelguest.ccdetails.name);
		System.out.println("Guest credit card number: " + hotelguest.ccdetails.cardNo);
		System.out.println("Guest credit card cvv2: " + hotelguest.ccdetails.cvv2);
		System.out.println("Guest credit card type: " + hotelguest.ccdetails.type);
		System.out.println("Expiry date of the credit card: " + new SimpleDateFormat("dd-MM-yyyy").format(hotelguest.ccdetails.expiry));
	}
	
	public void printGuestDetail(String name)
	{
		printGuest(SearchGuest(name));
	}

	
}
