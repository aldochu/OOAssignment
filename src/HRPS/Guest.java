package HRPS;

/**
 * 
Represents a guest registered into the hotel.
 A guest need to register before it can check in.
 @author Aldo Chu
 @version 1.0
 @since 2018-04-18
 *
 */
public class Guest {
	
	/**
	 * The name of the guest
	 */
public String name;

/**
 * The identification number of the guest
 */
public String ic;

/**
 * The address of the guest
 */
public String address;

/**
 * The contact number 
 */
public String contactNo;

/**
 * The nationality of the guest
 */
public String nationality;

/**
 * The Credit card of the guest
 */
public CreditCard ccdetails = new CreditCard();

/**
 * The gender of the guest represent by True for male and False for female 
 */
public boolean gender; //true for male, false for female 
}

