package il.ac.shenkar.finalproject.model;

import java.util.*;

/**
 * Coupons platform DAO interface - all DAO method declarations
 */
public interface CouponsDAO {
	/**
	 * Get all coupons from DB
	 * @return Iterator of coupons
	 */
	public Iterator<Coupon> getCoupons() throws CouponsException;
	
	/**
	 * Get all up to date coupons from DB
	 * @return Iterator of coupons
	 */
	public Iterator<Coupon> getCouponsUpdated()throws CouponsException;
	
	/**
	 * Get coupons by category
	 * @param String - for requested category
	 * @return Iterator of coupons
	 */
	public Iterator<Coupon> getCouponsCategory(String category) throws CouponsException;
	
	/**
	 * Get coupons by location
	 * @param String - longtitude
	 * @param String - latitude
	 * @return Iterator of coupons
	 */
	public Iterator<Coupon> getCouponsLocation(String longitude, String latitude) throws CouponsException;
	
	/**
	 * Add coupon to DB
	 * @param Coupon - coupon object to add
	 * @return Boolean value - if operation succeeded
	 */
	public boolean addCoupon(Coupon coupon) throws CouponsException;
	
	/**
	 * Delete coupon from DB
	 * @param Integer - coupon id
	 * @return Boolean value - if operation succeeded
	 */
	public boolean deleteCoupon(int id) throws CouponsException; 
	
	/**
	 * Update coupon on DB
	 * @param Coupon - updated coupon object to replace
	 * @return Boolean value - if operation succeeded
	 */
	public boolean updateCoupon(Coupon coupon) throws CouponsException;
	
	/**
	 * Get specific coupon
	 * @param Integer - coupon id
	 * @return Coupon object
	 */
	public Coupon getCoupon(int id) throws CouponsException;
	
	/**
	 * Get password according to user name
	 * @param String - user name
	 * @return password string
	 */
	public String getPassword(String name) throws CouponsException;
}
