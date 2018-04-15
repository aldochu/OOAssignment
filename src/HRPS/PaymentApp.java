package HRPS;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class PaymentApp
{
	private ArrayList<Payment> pay = new ArrayList<Payment>();
	private PaymentData db = new PaymentData();
	Scanner sc = new Scanner(System.in);
	Date currentDate = new Date();

	private Guest guest;
	private Room rm;
	private Reservation res;
	private Registration reg;
	private RoomService rs;
	private Promo p;
	
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
	
	public boolean createPayment(String guestId)//pass by reference
	{
		boolean successful = true;
		String c;
		Double disc,promoCost = 0.00;

		PromoApp pa = new PromoApp();
		RoomServiceApp rsa = new RoomServiceApp();
		Payment pm= new Payment();
		
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		df.format(currentDate);
		
		pm.roomsvc = 0.00;
		
		pm.guestId = guestId;
		pm.GuestName = guest.name;
		pm.rDate = currentDate;
		if(reg == null)
		{
			pm.paymentId = res.res_id;
			pm.checkInDate = res.check_in;
			pm.checkOutDate = res.check_out;
			pm.adults = res.NoOfAdult;
			pm.child = res.NoOfChild;		
		}
		
		else if(res == null)
		{
			pm.paymentId = reg.res_id;
			pm.checkInDate = reg.check_in;
			pm.checkOutDate = reg.check_out;
			pm.adults = reg.NoOfAdult;
			pm.child = reg.NoOfChild;		
			
		}
		pm.roomNumber = rm.roomId;
		pm.roomType = rm.roomType;
		pm.bedType = rm.bedType;
		pm.duration = timeDiff(pm.checkInDate, pm.checkOutDate);
		pm.roomcost = rm.rate;
		pm.roomtax = 1.17 * pm.roomcost;
		
		rs = rsa.GetRoomService(pm.roomNumber);
		
		if(rs == null)
		{
			pm.onum = 0;
		}
		else
		{
			pm.onum = 0;
			for(int i = 0; i < rs.foodList.size(); i++)
			{
				pm.roomsvc += rs.foodList.get(i).price;
			}
		}

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
		System.out.println("No. Adults : " + pm.adults);
		System.out.println("No. Children : " + pm.child);
		System.out.println("Check In Date : " + pm.checkInDate);
		System.out.println("Check Out Date : " + pm.checkOutDate);
		System.out.println("Total Stay Duration : " + pm.duration + " Days");
		System.out.println("Room Cost (w/o GST) : SGD " + pm.roomcost);
		
		if(rs == null)
		{
			System.out.println("Room Service Breakdown : NONE" );
		}
		else
		{
			System.out.println("Room Service Breakdown : " );
			for(int i = 0; i < rs.foodList.size(); i++)
			{
				System.out.println(df.format(rs.date) + " " + rs.orderNumber + " " + rs.foodList.get(i).name + " " + rs.foodList.get(i).price);
			}
		}
		
		System.out.println("Room Service Cost : SGD " + pm.roomsvc);
		System.out.println("Room Tax (10% Service Charge + 7% GST) : SGD " + pm.roomtax);
		System.out.println("Total Cost : SGD " + pm.tcost);
		System.out.println("Payment By : " + pm.payType);
		
		System.out.println("Please Check if the above details are correct (Y/N)");
		c = sc.next();
		if(c.equals("y") || c.equals("Y"))
		{
			System.out.println("Do you have a promo code to use? (Y/N)");
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
					System.out.println("Your Final Cost is : SGD " + pc);
					pm.tcost = promoCost;
				}
			}
			
			System.out.println("Pay Now? (Y/N)");
			c = sc.next();
			if(c.equals("y") || c.equals("Y"))
			{
				rsa.paid(pm.onum);
				pay.add(pm);
			}
			else if(c.equals("n") || c.equals("N"))
			{
				System.out.println("Payment incomplete.");
				return false;
			}
		}
		else if(c.equals("n") || c.equals("N"))
		{
			System.out.println("Please Hold On While We Ammend Your Details");
			return false;
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
		return successful;
	}
	
	public void printTodayPayments()
	{
		Date date = new Date();
		DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

		System.out.println("The Payments below were made today, " + sdf.format(date));
		System.out.println("------------------------------------------------------------");
		for(int i = 0;i<pay.size();i++)
		{
			boolean b = sdf.format(pay.get(i).rDate).equals(sdf.format(date));
			if(b)
			{
				System.out.println(pay.get(i).paymentId + "|" + pay.get(i).guestId + "\t|" + pay.get(i).GuestName + "\t|" + pay.get(i).checkInDate + "\t|" + pay.get(i).checkOutDate  + "\t|" + pay.get(i).duration  + "\t|" + pay.get(i).tcost  + "\t|" + pay.get(i).rDate);
			}
		}
	}
	
	public void printPastPayments()
	{
		try
		{
			DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Date date;
			System.out.println("Please Enter Date MM/DD/YYYY : " );
			sc.nextLine();
			date = sdf.parse(sc.nextLine());
			
			System.out.println("The Payments below were made on " + sdf.format(date));
			System.out.println("------------------------------------------------------------");
			for(int i = 0;i<pay.size();i++)
			{
				boolean b = sdf.format(pay.get(i).rDate).equals(sdf.format(date));
				if(b)
				{
					System.out.println(pay.get(i).paymentId + "|" + pay.get(i).guestId + "\t|" + pay.get(i).GuestName + "\t|" + pay.get(i).checkInDate + "\t|" + pay.get(i).checkOutDate  + "\t|" + pay.get(i).duration  + "\t|" + pay.get(i).tcost  + "\t|" + pay.get(i).rDate);
				}
			}
		}
		catch (ParseException e)
		{
				// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void printAllPayments()
	{
		Date date = new Date();
		DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

		System.out.println("The Payments below are made as at " + sdf.format(date));
		System.out.println("------------------------------------------------------------");
		for(int i = 0;i<pay.size();i++)
		{
			System.out.println(pay.get(i).paymentId + "|" + pay.get(i).guestId + "\t|" + pay.get(i).GuestName + "\t|" + pay.get(i).checkInDate + "\t|" + pay.get(i).checkOutDate  + "\t|" + pay.get(i).duration  + "\t|" + pay.get(i).tcost  + "\t|" + pay.get(i).rDate);
		}
	}
	
	public void printOccupancyReportByDate()
	{
		try
		{
			Validation v = new Validation();
			Date date;
			int o = 0;
			int e = 48;
			double opercent = 0.00;
			double epercent = 100.00;
			
			DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			System.out.println("Please Enter Date MM/DD/YYYY : " );
			date = sdf.parse(sc.nextLine());
			
			o = v.CheckDateWithin(date, pay);
			e -= o;
			
			opercent = 100*o/48;
			epercent -= opercent;
			System.out.println("Occupancy Report For : " + sdf.format(date));
			System.out.println("Rooms Vacant : " + e + " Vacancy Percentage : " + epercent);
			System.out.println("Rooms Occupied : " + o + " Occupation Percentage : " + opercent);
		}
		catch (ParseException e)
		{
				// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public boolean checkGuest(Guest gTemp)
	{
		if(gTemp == null)
		{
			return false;
		}
		else
		{
			guest = gTemp;
			return true;
		}
	}

	public boolean checkRes(Reservation resTemp)
	{
		if(resTemp == null)
		{
			return false;
		}
		else
		{
			if(resTemp.status == AppData.RES_STATUS_CHECKED_IN)
			{
				res = resTemp;
				return true;
			}
		}
		return false;
	}
	
	public boolean checkReg(Registration regTemp)
	{
		if(regTemp == null)
		{
			return false;
		}
		else
		{
			if(regTemp.status == AppData.RES_STATUS_CHECKED_IN)
			{
				reg = regTemp;
				return true;
			}
		}
		return false;
	}
	
	public boolean checkRoom(Room rmTemp)
	{
		if(rmTemp == null)
		{
			return false;
		}
		else
		{
			rm = rmTemp;
			return true;
		}
	}
	
	public boolean checkPromo(Promo promo)
	{
		if(promo == null)
		{
			return false;
		}
		else
		{
			p = promo;
			return true;
		}
	}
}