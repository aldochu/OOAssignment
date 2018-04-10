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
	Scanner sc = new Scanner(System.in);
	protected RoomServiceApp()
	{
		try {
			dbFood.readClass("food.txt", hotelFood); //to read data from files
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	sc.nextLine();
	System.out.println("Please enter food name:");
	_hotelFood.name = sc.nextLine();
	System.out.println("Please enter food description:");
	_hotelFood.description = sc.nextLine();
	System.out.println("Please enter food price (in SGD):");
	_hotelFood.price = sc.nextDouble();
	hotelFood.add(_hotelFood);
	System.out.println("Food created successfully!");
	try {
		dbFood.saveClass("food.txt", hotelFood);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} //to read data from files
	return 1;
    }
	
	public int updateFood()
	{
		sc.nextLine();
		System.out.println("List of foods(name|description|price):");
		printFoodList();
		System.out.println("Please enter the name of the food:");
		String foodname = sc.nextLine();
		for(int i = 0;i<hotelFood.size();i++)
		{
			if(hotelFood.get(i).name.equals(foodname))
			{
				Food temp = hotelFood.get(i);
				updateFoodDetails(temp); //pass by reference
				hotelFood.remove(i); //delete from the searched index
				hotelFood.add(i, temp); //add the new updated record into the deleted index
				return 1;
			}
		}	
		try {
			dbFood.saveClass("food.txt", hotelFood);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //to read data from files
		return 0;
	}
	
	private void updateFoodDetails(Food hotelfood)
	{
		int choice;
		do {
		
			System.out.println("Which information do you want to update:");
			System.out.println("1: Name");
			System.out.println("2: Description");
			System.out.println("3: Price");
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice)
			  {
			  case 1: 
			  {
				 System.out.println("Please enter the updated name");
			 	 hotelfood.name = sc.nextLine();
			  }
			  break;
			  case 2:
			  {
				 System.out.println("Please enter the updated description");
				 hotelfood.description = sc.nextLine();
			  }
			  break;
			  case 3:
			  {
				 System.out.println("Please enter the updated price");
				 hotelfood.price = sc.nextDouble();
			  }
			  break;
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
		for(int i = 0;i<hotelFood.size();i++)
			printFood(hotelFood.get(i));
	}
	
	public int createOrder()
	{
		sc.nextLine();
		RoomService _roomService = new RoomService();
		System.out.println("Please enter room number:");
		_roomService.RoomNo = sc.nextLine();
		_roomService.orderNumber = 1;
		for(int i = 0;i<hotelService.size();i++)
		{
	      	_roomService.orderNumber = hotelService.get(i).orderNumber +1 ;
		}
		if (_roomService.orderNumber>1000)
			_roomService.orderNumber = _roomService.orderNumber - 1000;
		String orderagain = "no";
		int i = 0;
		System.out.println("List of foods(name|description|price):");
		printFoodList();
		do
		{
		String food;
		do 
		{
		sc.nextLine();
		System.out.println("Please enter food you want to order:");
		food = sc.nextLine();
		_roomService.foodList.add(SearchFood(food));
		//sc.nextLine();
		} while (SearchFood(food)==null);
		System.out.println("Do you want to order another food(yes/no):");
		orderagain=sc.nextLine();
		i++;
		} while(orderagain=="yes");
		System.out.println("Please enter remarks for the food:");
		_roomService.remark = sc.nextLine();
		System.out.println("Please enter status for the food:");
		_roomService.status = sc.nextLine();
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
	
	public int updateOrder()
	{
		sc.nextLine();
		System.out.println("Please enter order number:");
		Integer order =(Integer) sc.nextInt();
		for(int i = 0;i<hotelService.size();i++)
		{
			if(hotelService.get(i).orderNumber.equals(order))
			{
				RoomService temp = hotelService.get(i);
				updateOrderDetails(temp); //pass by reference
				hotelService.remove(i); //delete from the searched index
				hotelService.add(i, temp); //add the new updated record into the deleted index
				return 1;
			}
		}
		try {
			dbSvc.saveClass("order.txt", hotelService);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //to read data from files
		return 0;
	}
	
	private void updateOrderDetails(RoomService hotelService)
	{
		int choice;
		do {
			System.out.println("Which information do you want to update:");
			System.out.println("1: Remarks");
			System.out.println("2: Status");
			System.out.println("3: Add more food");
			System.out.println("4: Remove food");
			System.out.println("5: Paid status");
			choice = sc.nextInt();
			switch (choice)
			  {
			  case 1: 
			  {
				 System.out.println("Please enter the updated remarks");
			 	 hotelService.remark = sc.nextLine();
			     break;
			  }
			  case 2:
			  {
				 System.out.println("Please enter the updated status");
				 hotelService.status = sc.nextLine();
			     break;
			  }
			  case 3:
			  {
				  String food;
					do 
					{
					sc.nextLine();
					System.out.println("Please enter the food you want to order:");
					food = sc.nextLine();
					hotelService.foodList.add(SearchFood(food));
					//sc.nextLine();
					} while (SearchFood(food)==null);
				break;
			  }
			  case 4:
			  {
				  String food;
					do 
					{
					sc.nextLine();
					System.out.println("Please enter food you want to remove:");
					food = sc.nextLine();
					for(int i= 0;i<hotelService.foodList.size();i++)
						{
						if (SearchFood(hotelService.foodList.get(i).name)!=null)
							hotelService.foodList.remove(i);
						}
					//sc.nextLine();
					} while (SearchFood(food)==null);
				   break;
			  }
			  case 5:
			  {
				  String status;
				  System.out.println("Please enter the updated status(paid/unpaid)");
					 status = sc.nextLine();
					 if(status == "paid")
						 hotelService.paid = true;
				  break;
			  }
			  }
			} while (choice >5);
		//return hotelService;
	}
	
    public ArrayList<RoomService> GetRoomService(String roomA)
	{
    	ArrayList <RoomService> temp = new ArrayList <RoomService>();
    	for(int i = 0;i<hotelService.size();i++)
		{
    		if(hotelService.get(i).RoomNo.equals(roomA))
    			temp.add(hotelService.get(i));
		}
    	for(int i=0;i<temp.size();i++)
    	{
    		if(temp.get(i).paid==false)
    			temp.remove(i);
    	}
    	return temp;
	}
    
	public void removeOrder()
	{
		sc.nextLine();
		System.out.println("Please enter order number:");
		Integer order = (Integer) sc.nextInt();
		for(int i = 0;i<hotelService.size();i++)
		{
			if(hotelService.get(i).orderNumber.equals(order))
			{
				hotelService.remove(i); //delete from the searched index
			}
		}
		try {
			dbSvc.saveClass("order.txt", hotelService);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //to read data from files
	}
	
	private void printRoomService(Food hotelfood,RoomService hotelService)
	{
		if(hotelfood == null)
			return;
		if(hotelService == null)
			return;
		System.out.print(hotelService.RoomNo +"|"+hotelService.orderNumber + "|"+hotelService.date + "|");	
		for(int i= 0;i<hotelService.foodList.size();i++)
		{
			System.out.print(hotelService.foodList.get(i).name+"*"+hotelService.foodList.get(i).price);
		}
		System.out.println(hotelService.remark +"|"+hotelService.status + "|"+hotelService.paid);
	}
}




