package il.ac.shenkar.finalproject.model;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

/**
* MySQLCouponsDAO Test class with JUnit
*/
public class MySQLCouponsDAOTest {

	private MySQLCouponsDAO inventory;
	
	@Before
	public void setUp() throws Exception {
		inventory = MySQLCouponsDAO.getInstance();
	}

	/**
	* getPassword method test - check that correct password returned
	*/
	@Test
	public void testGetPassword() {
		try {
			assertTrue(inventory.getPassword("admin").equals("e10adc3949ba59abbe56e057f20f883e"));
		} catch (CouponsException e) {
			e.printStackTrace();
		}
	}
	/**
	* getCouponsCategory method test - check that coupons are from the requested category
	*/
	@Test
	public void testGetCouponsCategory() {
		try {
			Iterator<Coupon> it = inventory.getCouponsCategory("Travel");
			while(it.hasNext()) {
				Coupon coupon = (Coupon)it.next();
				assertTrue(coupon.getCategory().equals("Travel"));
			}
		} catch (CouponsException e) {
			e.printStackTrace();
		}
	}

	/**
	* addCoupon method test - check that new coupon was added to DB
	*/
	@Test
	public void testAddCoupon() {
		try {
			String date = "2010-01-01 23:00:00";
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
			Date parsedDate;
			parsedDate = dateFormat.parse(date);
			Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
			Coupon newCop = new Coupon("test", "Test", "test", 0, timestamp, -1, -1);
			inventory.addCoupon(newCop);
			Coupon testCop = inventory.getCoupon(0);
			assertTrue(testCop.getName().equals("test"));
			inventory.deleteCoupon(0);
		} catch (ParseException | CouponsException e) {
			e.printStackTrace();
		}
	}

	/**
	* getCoupon method test - check that requested coupon returned
	*/
	@Test
	public void testGetCoupon() {
		try {
			Coupon testCop = inventory.getCoupon(1);
			assertTrue(testCop.getId() == 1);
		} catch (CouponsException e) {
			e.printStackTrace();
		}
	}

	

}
