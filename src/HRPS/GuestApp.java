package HRPS;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class GuestApp {
	

	//private Guest[] hotelGuest = new Guest[30]; //created 20 array
	private ArrayList<Guest> hotelGuest = new ArrayList<Guest>();
	private GuestData db = new GuestData();
	Scanner sc = new Scanner(System.in);
	
	///////////////////////////////////Load data from file////////////////////////////////////////
	protected GuestApp()
	{
		try {
			db.readClass("guest.txt", hotelGuest); //to read data from files
			
			for (int k = 0 ; k < hotelGuest.size() ; k++)
				System.out.println(hotelGuest.get(k).name);


			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	///////////////////////////////////Create Guest//////////////////////////////////////////////

	public int createGuest()//pass by reference
	{
		
		Guest _hotelGuest = new Guest();
		String var; //this var is for looping condition
		
		System.out.println("Please enter the guest name:");
		_hotelGuest.name = sc.nextLine();
		System.out.println("Please enter the guest ic:");
		_hotelGuest.ic = sc.nextLine();
		
		if(SearchGuestExist(_hotelGuest.ic))
		{
			System.out.println(_hotelGuest.ic + " is already registered as guest");
			System.out.println("Add guest action terminating . . . ");
			return 0;
		}
		else
		{
		System.out.println("Please enter the guest nationality:");
		_hotelGuest.nationality = sc.nextLine();
		System.out.println("Please enter the guest contact number:");
		_hotelGuest.contactNo = sc.nextLine();
		System.out.println("Please enter the guest address:");
		_hotelGuest.address = sc.nextLine();
		//loop
		do{
		System.out.println("Please enter the guest gender M/F:");
		var = sc.nextLine();
		if(var.equals("M"))
		{
			_hotelGuest.gender = true;
		break;
		}
		else if(var.equals("F"))
		{
			_hotelGuest.gender = false;
			break;
		}
		else
			System.out.println("Invalid input, try again");
		}while(!var.equals("M") && !var.equals("F"));
		
		do{
			System.out.println("Does the guest have credit card? Y/N");
			var = sc.nextLine();
			if(var.equals("Y"))
			{
				addCCDetail(_hotelGuest);
			break;
			}
			else if(var.equals("N"))
				_hotelGuest.ccdetails = null;
			}while(!var.equals("Y") && !var.equals("N"));
		
		hotelGuest.add(_hotelGuest);

		System.out.println("Guest created successfully!");
		
		try {
			db.saveClass("guest.txt", hotelGuest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //to read data from files
		
		return 1;
		}
		
	}
	
	private void addCCDetail(Guest hotelGuest)//pass by reference
	{
		boolean checkcvv2=false;
		boolean checkCCExpiry = false;
		Validation vs = new Validation();
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		
		System.out.println("Please enter the name on the credit card:");
		hotelGuest.ccdetails.name = sc.nextLine();
		System.out.println("Please enter the credit card number:");
		hotelGuest.ccdetails.cardNo = sc.nextLine();
		
		do{
		System.out.println("Please enter the cvv2 behind the credit card:");
		hotelGuest.ccdetails.cvv2 = sc.nextLine();
		checkcvv2=vs.CompareNoOfChar(hotelGuest.ccdetails.cvv2,3); //to check the number of string
		if(!checkcvv2)
			{
			System.out.println("Invalid cvv2 number, please try again");
			}
		}while(!checkcvv2);
		
		System.out.println("Please enter the type of the credit card:");
		hotelGuest.ccdetails.type = sc.nextLine();
		try {		
			do{
				System.out.println("Please enter the expiry date of the credit card: mm/dd/yyyy");
				hotelGuest.ccdetails.expiry = df.parse(sc.nextLine());
				checkCCExpiry=vs.CheckExpiry(hotelGuest.ccdetails.expiry); //to check the number of string
				if(!checkCCExpiry)
					{
					System.out.println("The card is already expired");
					}
				}while(!checkCCExpiry);		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
///////////////////////////////////End Of Create Guest//////////////////////////////////////////////
	
	
///////////////////////////////////Update Guest//////////////////////////////////////////////
	public int updateGuest()
	{
		
		System.out.println("Please enter the nric of the guest:");
		String ic = sc.nextLine();
		for(int i = 0;i<hotelGuest.size();i++)
		{
			if(hotelGuest.get(i).ic.equals(ic))
			{
				Guest temp = hotelGuest.get(i);
				updateGuestDetails(temp); //pass by reference
				hotelGuest.remove(i); //delete from the searched index
				hotelGuest.add(i, temp); //add the new updateed record into the deleted index
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
			sc.nextLine();
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
		//return hotelguest;
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
	
	
///////////////////////////////////Start of Search Guest//////////////////////////////////////////////
	
	public Guest SearchGuest(String name) //search by name
	{
		for(int i = 0;i<hotelGuest.size();i++)
		{
			if(hotelGuest.get(i).name.equals(name))
			{
				return hotelGuest.get(i);
			}
		}
		
		return null; //failed to create
	}
	
	public String SearchGuestExistAndReturnUserId() //search by name
	{
		
		System.out.println("Please enter the nric of the guest:");
		String ic = sc.nextLine();
		
		for(int i = 0;i<hotelGuest.size();i++)
		{
			if(hotelGuest.get(i).ic.equals(ic))
			{
				return hotelGuest.get(i).ic;
			}
		}
		
		return null;
	}
	
	public Guest SearchGuestByIc(String IC) //search by ic
	{
		for(int i = 0;i<hotelGuest.size();i++)
		{
			if(hotelGuest.get(i).ic.equals(IC))
			{
				return hotelGuest.get(i);
			}
		}
		
		return null; //failed to create
	}
	
	public boolean SearchGuestExist(String IC) //search by ic to check existence
	{
		for(int i = 0;i<hotelGuest.size();i++)
		{
			if(hotelGuest.get(i).ic.equals(IC))
			{
				return true;
			}
		}
		
		return false; //failed to create
	}
	
	
///////////////////////////////////END of Search Guest//////////////////////////////////////////////
	
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
	
	public void printGuestDetail()
	{
		System.out.println("Please enter the name of the guest:");
		String Name = sc.nextLine();
		printGuest(SearchGuest(Name));
	}

	
}
