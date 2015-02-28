package il.ac.shenkar.finalproject.model;


/**
 * Line of user shoppingcart - represent coupon and its qunatity
 */
public class ShoppingCartLine {
	
	private Coupon Coupon;
	private int quantity;
	
	/**
	 * ShoppingCartLine constructor
	 * @param Coupon - coupon object for line
	 * @param Integer - coupon quantity
	 */
	public ShoppingCartLine(Coupon Coupon, int quantity) {
		this.setCoupon(Coupon);
		this.setQuantity(quantity);
	}

	/**
	 * Get line coupon
	 * @return Coupon - coupon object
	 */
	public Coupon getCoupon() {
		return Coupon;
	}

	/**
	 * Set line coupon
	 * @param Coupon - coupon object
	 */
	public void setCoupon(Coupon Coupon) {
		this.Coupon = Coupon;
	}

	/**
	 * Get line quantity
	 * @return Integer - coupon quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Set line quantity
	 * @param Integer - coupon quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
