package HRPS;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
/**
 * 
This class is the manager class of Guest, it contains all the functional logic required by the system
 @author Aldo Chu
 @version 1.0
 @since 2018-04-18
 *
 */
public class GuestApp {
	

	//private Guest[] hotelGuest = new Guest[30]; //created 20 array
	
	/**
	 * An arraylist of guest data type to store and manipulate data
	 */
	private ArrayList<Guest> hotelGuest = new ArrayList<Guest>();
	private GuestData db = new GuestData();
	Scanner sc = new Scanner(System.in);
	
	///////////////////////////////////Load data from file////////////////////////////////////////
	/**
	 * Default constructor, using the data access class to store data from text file to the Guest arraylist
	 */
	protected GuestApp()
	{
		try {
			db.readClass("guest.txt", hotelGuest); //to read data from files

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	///////////////////////////////////Create Guest//////////////////////////////////////////////

	/**
	 * This function is to create a new guest, it will ask for user input for name, ic, 
	 * nationality, contact, address and gender and call the
	 * data access class function upon creation to update the text file
	 * @return 1 if function executed successfully
	 */
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
			else
				System.out.println("Invalid input, please try again");
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
	
	/**
	 * this function is to add credit card detail to the referenced guest
	 * @param hotelGuest the reference of the updating guest
	 */
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
	/**
	 * This function is to update guest, it will ask for user IC and traverse the arraylist to look
	 * for the same insert IC, once found it will call the updateGuestDetails function and pass
	 * in the found guest and waiting for the return of the edited guest. The new edited guest will
	 * then replace it's own place in the arraylist 
	 * @return 1 if execute successfully
	 */
	public int updateGuest()
	{
		
		System.out.println("Please enter the IC of the guest:");
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
		System.out.println(ic+" does not exist");
		return 0;
	}
	
	/**
	 * This function is called by the function updateGuest after it has traversed the arraylist
	 * to get the guest from the arraylist to update. This function will ask which parameter the user
	 * would like the update and proceed to edit based on the choices and return back to the function
	 * updateGuest
	 * @param hotelguest the reference guest to update
	 */
	private void updateGuestDetails(Guest hotelguest)
	{
		printGuest(hotelguest);
		
		int choice;
		do {
			System.out.println("Which information do you want to update:");
			System.out.println("1: Name");
			System.out.println("2: Nationality");
			System.out.println("3: Contact number");
			System.out.println("4: Address");
			System.out.println("5: Gender");
			System.out.println("6: Credit card details");
			System.out.println("7: quit");
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			 case 1: 
				 System.out.println("Please enter the updated name");
				 hotelguest.name = sc.nextLine();
				 System.out.println("Information updated!");
			 break;
			 case 2: 
				 System.out.println("Please enter the updated nationality");
				 hotelguest.nationality = sc.nextLine();
				 System.out.println("Information updated!");
			 break;
			 case 3:
				 System.out.println("Please enter the updated contact number");
				 hotelguest.contactNo = sc.nextLine();
				 System.out.println("Information updated!");
			 break;
			 case 4: /* add position() call */
				 System.out.println("Please enter the updated address");
				 hotelguest.address = sc.nextLine();
				 System.out.println("Information updated!");
			 break;
			 case 5: 
				 System.out.println("Please enter the updated gender M/F");
				 hotelguest.ic = sc.next();
				 System.out.println("Information updated!");
			 break; 
			 case 6: 
				 if(hotelguest.ccdetails == null)
				 {
					 System.out.println("User do not have credit card, please add the information");
					 hotelguest.ccdetails = new CreditCard();
					 addCCDetail(hotelguest);
				 }
				 else
				 updateCCDetails(hotelguest);
				 System.out.println("Information updated!");
			 break; 
			 case 7: System.out.println("return to previous");
			 	try {
							db.saveClass("guest.txt", hotelGuest);
					} catch (IOException e) 
			 		{
								// TODO Auto-generated catch block
							e.printStackTrace();
					} //to read data from files
			 	break;
			 default: System.out.println("invalid input");	
					}		
			} while (choice != 7);
		//return hotelguest;
	}
	
	/**
	 * a function that is called by function updateGuestDetails if user choose to update the credit card details
	 * @param hotelguest the reference guest to update the credit card details
	 */
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
				 sc.nextLine();
				 hotelguest.ccdetails.name = sc.nextLine();
			 break;
			 case 2: 
				 System.out.println("Please enter the updated credit card number");
				 sc.nextLine();
				 hotelguest.ccdetails.cardNo = sc.next();
			 break;
			 case 3: 
				 System.out.println("Please enter the updated credit card cvv2 number");
				 sc.nextLine();
				 hotelguest.ccdetails.cvv2 = sc.next();
			 break;
			 case 4:
				 System.out.println("Please enter the updated credit card type");
				 sc.nextLine();
				 hotelguest.ccdetails.type = sc.next();
			 break;
			 case 5: /* add position() call */
				 System.out.println("Please enter the updated credit card Expiry date mm/dd/yyyy");
				 sc.nextLine();
				 try {
					 hotelguest.ccdetails.expiry = df.parse(sc.next());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	 
			 break; 
			 case 6: 
				 System.out.println("return to previous");
			 break;
			 default: System.out.println("invalid input");
				
				
			}
			
			
				
			} while (choice != 6);
	}
	
	
///////////////////////////////////End of Update Guest//////////////////////////////////////////////
	
	
///////////////////////////////////Start of Search Guest//////////////////////////////////////////////
	
	/**
	 * This is a searching function that traverse the arraylist with the give argument and return
	 * the found guest 
	 * @param name the name of the guest that user want to search
	 * @return searched guest if there's any
	 */
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
	
	/**
	 * A function to search whether guest is in the arraylist with the argument of the guest IC
	 * @return the guest IC if the guest exist else return null
	 */
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
	
	/**
	 * A function to search guest in the arraylist with the argument of the guest IC
	 * @param IC the IC of the guest
	 * @return the guest if found else return null
	 */
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
	
	
	
	/**
	 * A function to search whether guest is in the arraylist with the argument of the guest IC
	 * @param IC the guest IC
	 * @return boolean, true represent exist and false represent non-exist
	 */
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
	
	/**
	 * A function to print the guest detail
	 * @param hotelguest the reference of the guest to print
	 */
	private void printGuest(Guest hotelguest)
	{
		if(hotelguest == null)
		{
			System.out.println("No guest with this name exist");
			return;
		}
			
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
	
	/**
	 * A function to print the guest credit card details
	 * @param hotelguest the reference of the guest to print
	 */
	private void printGuestCC(Guest hotelguest)
	{
		System.out.println("Guest credit card name: " + hotelguest.ccdetails.name);
		System.out.println("Guest credit card number: " + hotelguest.ccdetails.cardNo);
		System.out.println("Guest credit card cvv2: " + hotelguest.ccdetails.cvv2);
		System.out.println("Guest credit card type: " + hotelguest.ccdetails.type);
		System.out.println("Expiry date of the credit card: " + new SimpleDateFormat("dd-MM-yyyy").format(hotelguest.ccdetails.expiry));
	}
	
	/**
	 * A function that ask the name of the guest to print their details
	 */
	public void printGuestDetail()
	{
		System.out.println("Please enter the name of the guest:");
		String Name = sc.nextLine();
		printGuest(SearchGuest(Name));
	}

	
}
