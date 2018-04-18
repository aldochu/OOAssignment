package HRPS;
import java.util.Date;

/**
 * 
Represents a credit card that belongs to a guest.
 @author Aldo Chu
 @version 1.0
 @since 2018-04-18
 *
 */
public class CreditCard {

	/**
	 * The name on the credit card
	 */
	public String name;
	
	/**
	 * The card number of the credit card
	 */
	public String cardNo;
	
	/**
	 * The cvv2 number of the credit card
	 */
	public String cvv2;
	
	/**
	 * The credit card type
	 */
	public String type;
	
	/**
	 * The expiry of the credit card
	 */
	public Date expiry;
}
