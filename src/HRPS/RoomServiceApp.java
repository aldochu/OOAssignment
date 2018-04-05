package HRPS;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Date;
import java.util.Scanner;
public class RoomServiceApp 
{
	private ArrayList<Food> hotelFood = new ArrayList<Food>();
	private FoodData dbFood = new FoodData();
	private ArrayList<RoomService> hotelService = new ArrayList<RoomService>();
	private RoomServiceData dbSvc = new RoomServiceData();
	
	protected LoadFoodApp()
	{
		try {
			dbFood.readClass("food.txt", hotelFood); //to read data from files
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	protected LoadOrderApp()
	{
		try {
			dbSvc.readClass("order.txt", hotelService); //to read data from files
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int createFoodData()
	{
	Food _hotelFood = new Food();
	Scanner scFood = new Scanner(System.in);
	System.out.println("Please enter food name:");
	_hotelFood.name = scFood.nextLine();
	System.out.println("Please enter food description:");
	Scanner scDesc = new Scanner(System.in);
	_hotelFood.description = scDesc.nextLine();
	System.out.println("Please enter food price (in SGD):");
	Scanner scPrc = new Scanner(System.in);
	_hotelFood.price = scPrc.nextDouble();
	hotelFood.add(_hotelFood);
	System.out.println("Food created successfully!");
	saveFoodData();
	try {
		dbFood.saveClass("food.txt", hotelFood);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} //to read data from files
	return 1;
    }
	
	public void updateFood()
	{
	    loadFoodData();
		Scanner scUpd = new Scanner(System.in);
		System.out.println("Please enter the name of the food:");
		String foodname = scUpd.nextLine();
		for(int i = 0;i<hotelFood.size();i++)
		{
			if(hotelFood.get(i).name.equals(foodname))
			{
				Food temp = hotelFood.get(i);
				updateFoodDetails(temp); //pass by reference
				hotelFood.remove(i); //delete from the searched index
				hotelFood.add(i, temp); //add the new updated record into the deleted index
			}
		}	
	}
	
	private void updateFoodDetails(Food hotelfood)
	{
		Scanner sc = new Scanner(System.in);
		int choice;
		do {
			System.out.println("Which information do you want to update:");
			System.out.println("1: Name");
			System.out.println("2: Description");
			System.out.println("3: Price");
			choice = sc.nextInt();
			switch (choice)
			  {
			  case 1: 
			  {
				 System.out.println("Please enter the updated name");
				 Scanner scUpdFood = new Scanner(System.in);
			 	 hotelfood.name = scUpdFood.nextLine();
			     break;
			  }
			  case 2:
			  {
				 System.out.println("Please enter the updated description");
				 Scanner scUpdDesc = new Scanner(System.in);
				 hotelfood.description = scUpdDesc.nextLine();
			     break;
			  }
			  case 3:
			  {
				 System.out.println("Please enter the updated price");
				 Scanner scUpdPrc = new Scanner(System.in);
				 hotelfood.price = scUpdPrc.nextDouble();
			     break;
			  }
			  }
			} while (choice > 3);
	}
	
	public Food SearchFood(String name)
	{
		for(int i = 0;i<hotelFood.size();i++)
		{
			if(hotelFood.get(i).name.equals(name))
			{
				return hotelFood.get(i);
			}
		}	
		return null; 
	}
	
	private void printFood(Food hotelfood)
	{
		if(hotelfood == null)
			return;
		System.out.println(hotelfood.name +"|"+hotelfood.description + "|"+hotelfood.price);	
	}
	
	public void printFoodList()
	{
		loadFoodData();
		for(int i = 0;i<hotelFood.size();i++)
			printFood(hotelFood.get(i));
	}
	
	public int createOrder()
	{
		Food _hotelFood = new Food();
		RoomService _roomService = new RoomService();
		Scanner scRoom = new Scanner(System.in);
		System.out.println("Please enter room number:");
		_roomService.RoomNo = (Integer) scRoom.nextInt();
		_roomService.orderNumber = 1;
		for(int i = 0;i<hotelService.size();i++)
		{
	      	_roomService.orderNumber = hotelService.get(i).orderNumber +1 ;
		}
		if (_roomService.orderNumber>100)
			_roomService.orderNumber = _roomService.orderNumber - 100;
		do 
		{
		System.out.println("Please enter food you want to order:");
		Scanner scFoodOrder = new Scanner(System.in);
		_roomService.foodList.name = scFoodOrder.nextLine();
		} while (SearchFood(_roomService.foodList.name)==null);
		System.out.println("Please enter remarks for the food:");
		Scanner scFoodRem = new Scanner(System.in);
		_roomService.remark = scFoodRem.nextLine();
		System.out.println("Please enter status for the food:");
		Scanner scFoodStat = new Scanner(System.in);
		_roomService.status = scFoodStat.nextLine();
		_roomService.date =  new Date();
		hotelService.add(_roomService);
		System.out.println("Order created successfully!");
		try {
			dbSvc.saveClass("order.txt", hotelService);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //to read data from files
		return 1;
	 }
	
	public void updateOrder()
	{
	    loadOrderData();
		Scanner scUpdOrd = new Scanner(System.in);
		System.out.println("Please enter order number:");
		Integer order = scUpdOrd.nextInt();
		for(int i = 0;i<hotelService.size();i++)
		{
			if(hotelService.get(i).orderNumber.equals(order))
			{
				RoomService temp = hotelService.get(i);
				updateOrderDetails(temp); //pass by reference
				hotelService.remove(i); //delete from the searched index
				hotelService.add(i, temp); //add the new updated record into the deleted index
			}
		}
	}
	
	private void updateOrderDetails(RoomService hotelService)
	{
		Scanner sc = new Scanner(System.in);
		int choice;
		do {
			System.out.println("Which information do you want to update:");
			System.out.println("1: Remarks");
			System.out.println("2: Status");
			choice = sc.nextInt();
			switch (choice)
			  {
			  case 1: 
			  {
				 System.out.println("Please enter the updated remarks");
				 Scanner scUpdRem = new Scanner(System.in);
			 	 hotelService.remark = scUpdRem.nextLine();
			     break;
			  }
			  case 2:
			  {
				 System.out.println("Please enter the updated status");
				 Scanner scUpdStat = new Scanner(System.in);
				 hotelService.status = scUpdStat.nextLine();
			     break;
			  }
			  }
			} while (choice > 2);
		//return hotelService;
	}
	
	public void removeOrder()
	{
	    loadOrderData();
		Scanner scRemOrd = new Scanner(System.in);
		System.out.println("Please enter order number:");
		Integer order = scRemOrd.nextInt();
		for(int i = 0;i<hotelService.size();i++)
		{
			if(hotelService.get(i).orderNumber.equals(order))
			{
				RoomService temp = hotelService.get(i);
				updateOrderDetails(temp); //pass by reference
				hotelService.remove(i); //delete from the searched index
			}
		}
	}
}


