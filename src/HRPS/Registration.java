package HRPS;

import java.util.Date;
/**
 * 
Represents Registration details for walk in, attributes passed down to child class Reservation
 @author Li Feng
 @version 1.0
 @since 2018-04-18
 *
 */
public class Registration {
	
	/**
	 * The Reservation ID(Auto Generated)
	 */
	public String res_id;
	
	/**
	 * The Guest IC
	 */
	public String guestId;
	
	/**
	 * The Check In Date
	 */
	public Date check_in;
	
	/**
	 * The Check Out Date
	 */
	public Date check_out;
	
	/**
	 * Number of Adults
	 */
	public int NoOfAdult;
	
	/**
	 * Number of Children
	 */
	public int NoOfChild;
	
	/**
	 * Room ID(null if wailist)
	 */
	public String room_id;
	
	/**
	 * The Room Type
	 */
	public String roomType;
	
	/**
	 * The status of the walk in/reservation
	 */
	public int status;
}
