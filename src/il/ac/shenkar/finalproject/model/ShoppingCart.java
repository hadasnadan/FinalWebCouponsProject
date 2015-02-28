package il.ac.shenkar.finalproject.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * User (Session) shoppingcart - hold the coupons list per user
 */
public class ShoppingCart {
	
	private Map<Coupon, ShoppingCartLine> lines;
	
	/**
	 * ShoppingCart constructor - lines initiation
	 */
	public ShoppingCart() {
		lines = new HashMap<Coupon, ShoppingCartLine>();
	}

	/**
	 * Add new coupon line to shoppingcart
	 * @param ShoppingCartLine new line
	 */
	public void addShoppingCartLine(ShoppingCartLine line) {
		lines.put(line.getCoupon(), line);
	}
	
	/**
	 * Add new coupon to shoppingcart (update qunatity if line exists)
	 * @param Coupon - new coupon
	 */
	public void addCoupon(Coupon Coupon) {
		if(lines.containsKey(Coupon)) {
			lines.get(Coupon).setQuantity(lines.get(Coupon).getQuantity()+1);
		} else {
			addShoppingCartLine(new ShoppingCartLine(Coupon, 1));
		}
	}
	
	/**
	 * Get lines of shoppingcart
	 * @return Iterator of ShoppingCartLine
	 */
	public Iterator<ShoppingCartLine> getLines() {
		Collection<ShoppingCartLine> printlines = lines.values();
		Iterator<ShoppingCartLine> it = printlines.iterator();
		return it;
	
	}
}
