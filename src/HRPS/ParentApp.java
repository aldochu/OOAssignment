package HRPS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ParentApp {

	//private Guest[] hotelGuest = new Guest[30]; //created 20 array
	private ArrayList<Parent> myParent = new ArrayList<Parent>();
	private ArrayList<Child> mychild = new ArrayList<Child>();
	private ParentData db = new ParentData();
	Scanner sc = new Scanner(System.in);
	
	
	public void LoadParent(ArrayList<Child> childarray)
	{
		mychild = childarray;
		
		try {
			db.readClass2("parent.txt", myParent, childarray); //to read data from files
			
			for(int i =0;i<myParent.size();i++)
			{
			
			System.out.println("Parent Name:"+myParent.get(i).name);
			
			for (int k = 0 ; k < myParent.get(i).mychild.size() ; k++)
			{
				
				System.out.print("child name:" + myParent.get(i).mychild.get(k).myname + " ");
			}
			System.out.println("\n");
			}


			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createparent()
	{
		Parent p = new Parent();
		p.name = "ParentC";
		p.gender = "Female";
		
		
		p.mychild.add(mychild.get(0));
		p.mychild.add(mychild.get(2));
		p.mychild.add(mychild.get(3));
		
		myParent.add(p);
	}
	
	public void SaveParent(ArrayList<Child> childarray)
	{
		
			try {
				db.saveClass2("parent.txt", myParent, childarray);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //to read data from files
			
	}
}
