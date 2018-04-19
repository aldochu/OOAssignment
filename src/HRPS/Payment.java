package HRPS;

import java.util.Date;
/**
 * 
Represents payment details of a guest who is checking out.
 @author Daniel
 @version 1.0
 @since 2018-04-18
 *
 */
public class Payment
{
	/**
	 * Current Date
	 */
	public Date rDate;
	
	/**
	 * The payment ID of the reservation/walk in
	 */
	public String paymentId;
	
	/**
	 * The Guest IC
	 */
	public String guestId;
	
	/**
	 * The Guest Name
	 */
	public String GuestName;
	
	/**
	 * The room number of the reservation
	 */
	public String roomNumber;
	
	/**
	 * The room type and bed type of the room
	 */
	public String roomType,bedType;
	
	/**
	 * Number of adults/children checking out
	 */
	public int adults,child;
	
	/**
	 * Check In Date
	 */
	public Date checkInDate;
	
	/**
	 * Check Out Date
	 */
	public Date checkOutDate;
	
	/**
	 * The Duration of stay
	 */
	public long duration;
	
	/**
	 * The room cost excluding GST
	 */
	public Double roomcost;
	
	/**
	 * The roomservice cost
	 */
	public Double roomsvc;
	
	/**
	 * 17% of the roomcost
	 */
	public Double roomtax;
	
	/**
	 * roomtax + roomsvc + roomcost
	 */
	public Double tcost;
	
	/**
	 * Payment type(visa/cash/amex etc) based on guest details
	 */
	public String payType;
	
	/**
	 * The roomservice of the room
	 */
	public RoomService rsvc = new RoomService();
	
	/**
	 * The order number of the romservice for the room
	 */
	Integer onum;
}
