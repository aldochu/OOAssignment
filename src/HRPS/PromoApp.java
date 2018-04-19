package HRPS;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * 
This class is the manager class of Promo, it contains all the functional logic required by the system
 @author Daniel
 @version 1.0
 @since 2018-04-18
 *
 */
public class PromoApp
{
	/**
	 * An arraylist of guest data type to store and manipulate data
	 */
	private ArrayList<Promo> promo = new ArrayList<Promo>();
	private PromoData db = new PromoData();
	Scanner sc = new Scanner(System.in);
	Date currentDate = new Date();
	Date expDate = new Date();
	
	/**
	 * Default constructor, using the data access class to store data from text file to the Guest arraylist
	 */
	protected PromoApp()
	{
		try 
		{
			db.readClass("promo.txt", promo); //to read data from files			
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This function creates a new promo, it will ask for user input for promoname and discount and call the
	 * data access class function upon creation to update the text file
	 */
	public void createPromo()//pass by reference
	{
		Calendar exp = Calendar.getInstance();
		exp.setTime(currentDate);
		exp.add(Calendar.MONTH, 1);
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		expDate = exp.getTime();
		df.format(expDate);
		df.format(currentDate);
		Promo promocode= new Promo();
		
		System.out.println("Enter Promo Code:");
		promocode.pname = sc.nextLine();
		
		for(int i = 0; i < promo.size(); i++)
		{
			if(promocode.pname.equals(promo.get(i).pname))
			{
				System.out.println("Promo Code already exists");
				return;
			}
		}
		
		System.out.println("Enter Discount in %");
		promocode.discount = sc.nextDouble();
		
		promocode.today = currentDate;
		promocode.expiry = expDate;
		promo.add(promocode);

		System.out.println("Promo Code created successfully!");
		
		try 
		{
			db.saveClass("promo.txt", promo);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //to read data from files
	}
	
	/**
	 * This function prints all promocodes that are not expired
	 */
	public void printPromo()
	{		
		System.out.println("The Promo Codes below are valid as at " + currentDate);
		System.out.println("------------------------------------------------------------");
		for(int i = 0;i<promo.size();i++)
		{
			boolean b = promo.get(i).expiry.after(currentDate);
			if(b)
			{
				System.out.println(promo.get(i).pname + "|" + promo.get(i).discount + "\t|" + promo.get(i).today + "\t|" + promo.get(i).expiry);
			}
		}
	}
	
	/**
	 * This function searches for a promo
	 * @param promoname to search promo array
	 * @return promo if promoname exists and is valid, null if promoname is invalid or expired
	 */
	@SuppressWarnings("unused")
	public Promo SearchPromo(String promoname) //search by name
	{
		for(int i = 0;i<promo.size();i++)
		{
			if(promo.get(i).pname.equals(promoname) && (currentDate.before(promo.get(i).expiry) || currentDate.equals(promo.get(i).expiry)))
			{
				return promo.get(i);
			}
			
			else if(promo.get(i).pname.equals(promoname) && currentDate.after(promo.get(i).expiry));
			{
				return null;
			}
			
		}
		
		return null; //failed to create
	}
	
	/**
	 * This function is to delete a promo, it will ask for user input for promoname and call the
	 * data access class function upon deletion to update the text file
	 */
	public void deletePromo()
	{
		String pname;
		System.out.println("Please enter promo name to delete");
		pname = sc.nextLine();
		int flag = 0;
		for(int i = 0; i<promo.size();i++)
		{
			if(promo.get(i).pname.equals(pname))
			{
				promo.remove(i);
				flag = 1;
				System.out.println("Promo Code Deleted");
				break;
			}
		}
		
		if(flag == 0)
		{
			System.out.println("No Such Promo Code Exists");
		}
		try
		{
			db.saveClass("promo.txt", promo);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //to read data from files
	}
}
