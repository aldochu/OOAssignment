package HRPS;

import java.util.Date;

/**
 * 
Represents Promo details
 @author Daniel
 @version 1.0
 @since 2018-04-18
 *
 */
public class Promo 
{
	/**
	 * The promocode name
	 */
	public String pname;
	
	/**
	 * The discount in %
	 */
	public Double discount;
	
	/**
	 * The Current date
	 */
	public Date today;
	
	/**
	 * The expiry date
	 */
	public Date expiry;
	
}
