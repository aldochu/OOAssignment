package HRPS;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class PaymentApp
{
	private ArrayList<Payment> pay = new ArrayList<Payment>();
	private PromoData db = new PromoData();
	Scanner sc = new Scanner(System.in);
	Date currentDate = new Date();

	protected PaymentApp()
	{
		try 
		{
			db.readClass("payment.txt", pay); //to read data from files
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createPayment()//pass by reference
	{
		String bnum;
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		df.format(currentDate);
		Payment pm= new Payment();
		
		System.out.println("Enter Booking Number");
		bnum = sc.nextLine();
		
//		for(int i = 0; i < res.size(); i++)
//		{
//			if(bnum.equals(res.get(i).bookingNum))
//			{
//				System.out.println("Booking found");
//				pm.bookingNum = bnum;
//				pm.GuestName = res.;
//				pm.paymentId = ;
//				pm.roomcost = ;
//				pm.roomsvc = ;
//				pm.roomtax = ;
//				pm.tcost = pm.roomcost + pm.roomsvc + pm.roomtax;
//				break;
//			}
//			
//			if((i == pay.size() - 1) && (!bnum.equals(pay.get(i).bookingNum)))
//			{
//				System.out.println("Booking not found");
//				return;
//			}
//		}
//		
		pm.roomtax = 1.17 * pm.roomcost;
		
		pay.add(pm);

		System.out.println("Payment successfully added!");
		
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
}