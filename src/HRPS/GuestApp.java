package HRPS;

import java.util.Date;

public class GuestApp {
	

	private Guest[] hotelGuest = new Guest[30]; //created 20 array
	
	public int createGuest(String name,String ic, String address, String country, String contactNo, String nationality, char gender,String ccName, String cardNo, String cvv2, String ccType, Date expiryDate )
	{
		for(int i = 0;i<hotelGuest.length;i++)
		{
			if(hotelGuest[i]==null)
			{
				hotelGuest[i].name = name;
				hotelGuest[i].ic = ic;
				hotelGuest[i].address = address;
				hotelGuest[i].country = country;
				hotelGuest[i].contactNo = contactNo;
				hotelGuest[i].nationality = nationality;
				
				if(gender == 'M')
				{
					hotelGuest[i].gender = true;
				}
				else
					hotelGuest[i].gender = false;
				
				hotelGuest[i].ccdetails.name = ccName;
				hotelGuest[i].ccdetails.cardNo  = cardNo;
				hotelGuest[i].ccdetails.cvv2  = cvv2;
				hotelGuest[i].ccdetails.type  = ccType;
				hotelGuest[i].ccdetails.expiry  = expiryDate;
				
				
				return 1; // successfully added
			}
		}
		return 0; //failed to create
	}
	
	public int updateGuest(String name)
	{
		return 0;//failed to  update
		//need to ask should we add the input function inside controller
	}
	
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
