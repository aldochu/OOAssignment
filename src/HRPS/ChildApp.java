package HRPS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ChildApp {

	//private Guest[] hotelGuest = new Guest[30]; //created 20 array
	private ArrayList<Child> mychild = new ArrayList<Child>();
	private ChildData db = new ChildData();
	Scanner sc = new Scanner(System.in);
	
	
	
	///////////////////////////////////Load data from file////////////////////////////////////////
	protected ChildApp()
	{
		///////////////////////////Storing Child class to arraylist
		try {
			db.readClass("child.txt", mychild); //to read data from files
	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		///////////////////////////////////////////////////////////////////
	}
	
	public ArrayList<Child> getchild()
	{
		return mychild;
	}
	
	
	
	
	
}
