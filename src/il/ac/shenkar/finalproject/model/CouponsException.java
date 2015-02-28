package il.ac.shenkar.finalproject.model;

/**
 * Exception for describing coupon platform exceptions
 */
@SuppressWarnings("serial")
public class CouponsException extends Exception {
	/**
	 * Exception constructor
	 * @param String - expection message
	 */
	public CouponsException(String msg) {
		super(msg);
	}
	
	/**
	 * Exception constructor
	 * @param String - expection message
	 * @param Throwable - throwable object
	 */
	public CouponsException(String msg, Throwable throwable) {
		super(msg,throwable);
	}
}