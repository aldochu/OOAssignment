package HRPS;

/**
 * Represents the attributes of a hotel room
 * @author Rou Shan
 * @version 1.0
 * @since 2018-04-18
 *
 */

public class Room {
	/**
	 * Room number and level number of room
	 */
	public int roomNo, levelNo;
	
	/**
	 * Room Type: Single/Double/Deluxe/VIP
	 * Bed Type: King/Double/Single Beds
	 * GuestIc: Identification of guest
	 * RoomId: Level number - Room number
	 */
	public String roomType, bedType, guestIc, roomId;
	
	/**
	 * Rate of room per night
	 */
	public double rate;
	
	/**
	 * Details of room includes wifi, smoking area, city view and breakfast included
	 */
	public boolean wifi = false, smoking = false, cityView = false, breakfast = false;
	
	/**
	 * Status of room : Vacant/Reserved/Occupied/Under Maintenance
	 */
	public String status = "Vacant";
	
}
