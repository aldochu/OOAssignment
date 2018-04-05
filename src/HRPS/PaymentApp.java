package HRPS;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class PaymentApp
{
	private ArrayList<Payment> pay = new ArrayList<Payment>();
	private PaymentData db = new PaymentData();
	Scanner sc = new Scanner(System.in);
	Date currentDate = new Date();

	protected PaymentApp()
	{
		try 
		{
			db.readClass("payment.txt", pay);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createPayment()//pass by reference
	{
		String guestId;
		int roomId = 2;
		int option;
		String c;
		Double disc,promoCost = 0.00;
		
		Promo p = new Promo();
		Payment pm= new Payment();
		Guest guest = new Guest();
		Room rm = new Room();
		
		PromoApp pa = new PromoApp();
		GuestApp g = new GuestApp();
		RoomApp r = new RoomApp();
		
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		df.format(currentDate);
		
		System.out.println("Press 1 to Enter Guest IC");
		System.out.println("Press 2 to Enter Guest Name");
		option = sc.nextInt();
		
		if(option == 1)
		{
			System.out.println("Enter Guest IC");
			guestId = sc.next();
			guest = g.SearchGuestByIc(guestId);
		}
		else if(option == 2)
		{
			System.out.println("Enter Guest Name");
			guestId = sc.next();
			guest = g.SearchGuest(guestId);
		}
		else
		{
			System.out.println("Error Input");		
		}
		
		if(guest == null)
		{	
			System.out.println("Guest Does Not Exist");
			return;
		}
		
		System.out.println("Booking for " + guest.name + " found");
		pm.rDate = currentDate;
		pm.paymentId = "Walk In";
		pm.guestId = guest.ic;
		pm.GuestName = guest.name;
		pm.roomNumber = roomId;
		pm.roomType = "VIP";
		pm.bedType = "King";
		pm.adults = 1;
		pm.child = 0;
		pm.checkInDate = currentDate;
		pm.checkOutDate = currentDate;
		pm.duration = timeDiff(pm.checkOutDate, pm.checkOutDate);
		pm.roomcost = 10.00;
		pm.roomsvc = 15.00;		
		pm.roomtax = 1.17 * pm.roomcost;
		pm.tcost = pm.roomcost + pm.roomsvc + pm.roomtax;
		
		if(guest.ccdetails.type == null)
		{
			pm.payType = "Cash";
		}
		else
		{
			pm.payType = guest.ccdetails.type;
		}
		
		System.out.println("Billing Date : " + pm.rDate);
		System.out.println("Payment ID : " + pm.paymentId);
		System.out.println("Guest ID : " + pm.guestId);
		System.out.println("Guest Name : " + pm.GuestName);
		System.out.println("Room Number : " + pm.roomNumber);
		System.out.println("Room Type : " + pm.roomType);
		System.out.println("Bed Type : " + pm.bedType);
		System.out.println("No. Adults : " + pm.bedType);
		System.out.println("No. Children : " + pm.bedType);
		System.out.println("Check In Date : " + pm.checkInDate);
		System.out.println("Check Out Date : " + pm.checkOutDate);
		System.out.println("Total Stay Duration : " + pm.duration);
		System.out.println("Room Cost (w/o GST) : " + pm.roomcost);
		System.out.println("Room Service Cost : " + pm.roomsvc);		
		System.out.println("Room Tax (10% Service Charge + 7% GST) : " + pm.roomtax);
		System.out.println("Total Cost : " + pm.tcost);
		System.out.println("Payment By : " + pm.payType);
		
		System.out.println("Please Check if the above details are correct(Y/N)");
		c = sc.next();
		if(c.equals("y") || c.equals("Y"))
		{
			System.out.println("Do you have a promo code to use?");
			c = sc.next();
			if(c.equals("y") || c.equals("Y"))
			{
				System.out.println("Please Enter The Promo Code");
				c = sc.next();
				p = pa.SearchPromo(c);
				if(p == null)
				{
					System.out.println("Promo Does Not Exist");
				}
				else
				{
					disc = p.discount;
					promoCost = pm.tcost - ((disc/100) * pm.tcost);
					Double pc = (double) Math.round(promoCost * 100) / 100;
					System.out.println("Your Final Cost is : " + pc);
					pm.tcost = promoCost;
				}
			}
			System.out.println("Pay Now?");
			c = sc.next();
			if(c.equals("y") || c.equals("Y"))
			{
				pay.add(pm);
				System.out.println("Payment successfully added!\nThank You, Please Come Again!");
			}
			else if(c.equals("n") || c.equals("N"))
			{
				System.out.println("Payment incomplete.");
				return;
			}
		}
		else if(c.equals("n") || c.equals("N"))
		{
			System.out.println("Please Hold On While We Ammend Your Details");
			return;
		}
		
			
		try 
		{
			db.saveClass("payment.txt", pay);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //to read data from files
	}
	
	public void printPayments()
	{		
		System.out.println("The Promo Codes below are valid as at " + currentDate);
		System.out.println("------------------------------------------------------------");
		for(int i = 0;i<pay.size();i++)
		{
			boolean b = pay.get(i).rDate.equals(currentDate);
			if(b)
			{
				System.out.println(pay.get(i).paymentId + "|" + pay.get(i).guestId + "\t|" + pay.get(i).GuestName + "\t|" + pay.get(i).checkInDate + "\t|" + pay.get(i).checkOutDate  + "\t|" + pay.get(i).duration  + "\t|" + pay.get(i).tcost  + "\t|" + pay.get(i).rDate);
			}
		}
	}
	
	private long timeDiff(Date d1, Date d2)
	{
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		long diff,mil1,mil2,diffdays;
		
		c1.setTime(d1);
		c2.setTime(d2);
		
		mil1 = c1.getTimeInMillis();
		mil2 = c2.getTimeInMillis();
		
		diff = mil2 - mil1;
		
		diffdays = diff/(24*60*60*1000);
		return diffdays;
	}
}