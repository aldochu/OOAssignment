package HRPS;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class GuestApp {
	

	private Guest[] hotelGuest = new Guest[30]; //created 20 array
	Scanner sc = new Scanner(System.in);
	
	///////////////////////////////////Create Guest//////////////////////////////////////////////
	public int createGuest()
	{
		for(int i = 0;i<hotelGuest.length;i++)
		{
			if(hotelGuest[i]==null)
			{
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
		hotelGuest.ic = sc.next();
		System.out.println("Please enter the guest nationality:");
		hotelGuest.nationality = sc.nextLine();
		System.out.println("Please enter the guest contact number:");
		hotelGuest.contactNo = sc.next();
		System.out.println("Please enter the guest address:");
		hotelGuest.address = sc.nextLine();
		//loop
		do{
		System.out.println("Please enter the guest gender M/F:");
		var = sc.next();
		if(var == "M")
		{
		hotelGuest.gender = true;
		break;
		}
		else 
			hotelGuest.gender = false;
		}while(var != "M" || var != "F");
		
		do{
			System.out.println("Does the guest have credit card? Y/N");
			var = sc.next();
			if(var == "Y")
			{
				addCCDetail(hotelGuest);
			break;
			}
			else if(var == "N")
				hotelGuest.ccdetails = null;
			}while(var != "Y" || var != "N");

		System.out.println("Guest created successfully!");
		return 1; //failed to create
		
	}
	
	private void addCCDetail(Guest hotelGuest)//pass by reference
	{
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		
		System.out.println("Please enter the name on the credit card:");
		hotelGuest.ccdetails.name = sc.nextLine();
		System.out.println("Please enter the credit card number:");
		hotelGuest.ccdetails.cardNo = sc.next();
		System.out.println("Please enter the cvv2 behind the credit card:");
		hotelGuest.ccdetails.cvv2 = sc.next();
		System.out.println("Please enter the type of the credit card:");
		hotelGuest.ccdetails.type = sc.next();
		System.out.println("Please enter the expiry date of the credit card: mm/dd/yyyy");
		
		try {
			hotelGuest.ccdetails.expiry = df.parse(sc.next());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
///////////////////////////////////End Of Create Guest//////////////////////////////////////////////
	
	
///////////////////////////////////Update Guest//////////////////////////////////////////////
	public int updateGuest(String name)
	{
		for(int i = 0;i<hotelGuest.length;i++)
		{
			if(hotelGuest[i].name==name)
			{
				updateGuestDetails(hotelGuest[i]); //pass by reference
				return 1;
			}
		}
		return 0;
	}
	
	private void updateGuestDetails(Guest hotelguest)
	{
		
	}
	
	
///////////////////////////////////End of Update Guest//////////////////////////////////////////////
	
	public Guest SearchGuest(String name)
	{
		for(int i = 0;i<hotelGuest.length;i++)
		{
			if(hotelGuest[i].name==name)
			{
				return hotelGuest[i]; //Search Successfully
			}
		}
		return null; //failed to create
	}

	
}
