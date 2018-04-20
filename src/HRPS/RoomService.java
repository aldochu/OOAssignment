package HRPS;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Date;
/**
 * 
Represents list of food available for order.
 A guest need to register before it can check in.
 @author Kevin Jonathan
 @version 1.0
 @since 2018-04-20
 *
 */
public class RoomService {

	/**
	 * The order number, the remarks, and the status of room service
	 */
	
	Integer orderNumber;
	String remark,status;
	/**
	 * The room number tied to the service
	 */
	String RoomNo;
	/**
	 * The list of food ordered
	 */
	public ArrayList<Food> foodList= new ArrayList<Food>();
	/**
	 * The date the order is last updated
	 */
	Date date;
	/**
	 * The status of its payment
	 */
	boolean paid=false;
}
