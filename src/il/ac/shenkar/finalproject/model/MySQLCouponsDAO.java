package il.ac.shenkar.finalproject.model;

import il.ac.shenkar.finalproject.controller.FinalProjectController;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Provides the CouponsDAO interface implementation 
 */
public class MySQLCouponsDAO implements CouponsDAO {

	/**
	* Log4j logger
	*/
	static Logger log4j = Logger.getLogger(MySQLCouponsDAO.class);
	
	private static MySQLCouponsDAO instance;
	private SessionFactory factory;

	/**
	 * Get the singleton MySQLCouponsDAO instance
	 * @return MySQLCouponsDAO object
	 */
	public static MySQLCouponsDAO getInstance() {
		if (instance == null) {
			instance = new MySQLCouponsDAO();
		}
		return instance;
	}

	private MySQLCouponsDAO() {
		log4j.info("New MySQLCouponsDAO was created - factory initiation");
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
	}

	/**
	 * Get all coupons from DB
	 * @return Iterator of coupons
	 */
	@Override
	public Iterator<Coupon> getCoupons() throws CouponsException {
		log4j.info("Returning full coupons list");
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List coupons = session.createQuery("from Coupon").list();
			tx.commit();
			return coupons.iterator();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			log4j.info("Problem with getting all coupons");
			log4j.info(e.getMessage());
			throw new CouponsException("problem with getting all coupons",e);
		} finally {
			if(session!=null)session.close();
		}
	}
	
	/**
	 * Get all up to date coupons from DB
	 * @return Iterator of coupons
	 */
	@Override
	public Iterator<Coupon> getCouponsUpdated() throws CouponsException {
		log4j.info("Returning up to date coupons list");
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List coupons = session.createQuery("from Coupon where date >now()").list();
			tx.commit();
			return coupons.iterator();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			log4j.info("Problem with getting up to date coupons");
			log4j.info(e.getMessage());
			throw new CouponsException("problem with getting up to date coupons",e);
		} finally {
			if(session!=null)session.close();
		}
	}
	
	/**
	 * Get coupons by category
	 * @param String - for requested category
	 * @return Iterator of coupons
	 */
	@Override
	public Iterator<Coupon> getCouponsCategory(String category)throws CouponsException {
		log4j.info("Returning coupons by category "+ category);
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List coupons = session.createQuery("from Coupon where category='" + category + "'and date > now()").list();
			tx.commit();
			return coupons.iterator();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			log4j.info("Problem with getting category coupons");
			log4j.info(e.getMessage());
			throw new CouponsException("problem with getting category coupons",e);
		} finally {
			if(session!=null)session.close();
		}
	}
	
	/**
	 * Get coupons by location
	 * @param String - longtitude
	 * @param String - latitude
	 * @return Iterator of coupons
	 */
	@Override
	public Iterator<Coupon> getCouponsLocation(String latitude, String longitude) throws CouponsException {
		log4j.info("Returning coupons by location - longtitude "+longitude+" latitude "+latitude);
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List coupons = session.createQuery("from Coupon C where sqrt(('" + latitude + "'- latitude)*('" + latitude + "'- latitude) + ('" + longitude + "'- longitude)*('" + longitude + "'- longitude)) < 200 AND date > now() order by (sqrt(('" + latitude + "'- latitude)*('" + latitude + "'- latitude) + ('" + longitude + "'- longitude)*('" + longitude + "'- longitude))) ").list();
			tx.commit();
			return coupons.iterator();
		}catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			log4j.info("Problem with getting coupons by location");
			log4j.info(e.getMessage());
			throw new CouponsException("problem with getting coupons by location",e);
		} finally {
			if(session!=null)session.close();
		}
	}

	/**
	 * Add coupon to DB
	 * @param Coupon - coupon object to add
	 * @return Boolean value - if operation succeeded
	 */
	@Override
	public boolean addCoupon(Coupon coupon) throws CouponsException {
		log4j.info("Adding new coupon to DB");
		log4j.info(coupon);
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(coupon);
			tx.commit();
			return true;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			log4j.info("Problem with adding coupon "+ coupon);
			log4j.info(e.getMessage());
			throw new CouponsException("problem with adding "+coupon,e);
		} finally {
			if(session!=null)session.close();
		}
	}

	/**
	 * Delete coupon from DB
	 * @param Integer - coupon id
	 * @return Boolean value - if operation succeeded
	 */
	@Override
	public boolean deleteCoupon(int id) throws CouponsException {
		log4j.info("Delete coupon with id "+ id);
		Session session = factory.openSession();
		Coupon coupon = instance.getCoupon(id);
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(coupon);
			tx.commit();
			return true;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			log4j.info("Problem with deleting "+ coupon);
			log4j.info(e.getMessage());
			throw new CouponsException("problem with deleting "+coupon,e);
		} finally {
			if(session!=null)session.close();
		}
	}

	/**
	 * Update coupon on DB
	 * @param Coupon - updated coupon object to replace
	 * @return Boolean value - if operation succeeded
	 */
	@Override
	public boolean updateCoupon(Coupon coupon) throws CouponsException {
		log4j.info("Update coupon "+ coupon);
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(coupon);
			tx.commit();
			return true;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			log4j.info("problem with updating "+coupon);
			log4j.info(e.getMessage());
			throw new CouponsException("problem with updating "+coupon,e);
		} finally {
			if(session!=null)session.close();
		}
	}

	/**
	 * Get specific coupon
	 * @param Integer - coupon id
	 * @return Coupon object
	 */
	@Override
	public Coupon getCoupon(int id) throws CouponsException {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List coupons = session.createQuery("from Coupon where id = "+id).list();
			tx.commit();
			Iterator<Coupon> iterator = coupons.iterator();
			while(iterator.hasNext()) {
				Coupon coupon = (Coupon)iterator.next();
				if (coupon.getId()==id) {
					log4j.info("Returning coupon " + coupon);
					return coupon;
				}
			}
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			log4j.info("problem with returning coupon "+id);
			log4j.info(e.getMessage());
			return null;
		} finally {
			if(session!=null)session.close();
		}
		return null;
	}

	/**
	 * Get password according to user name
	 * @param String - user name
	 * @return password string
	 */
	public String getPassword(String name) throws CouponsException {
		log4j.info("Searching password for "+name);
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<User> user = session.createQuery("from User as User where name ='" + name +"'").list();
			tx.commit();
			Iterator<User> it = user.iterator();
			while(it.hasNext()) {
				User usr = (User)it.next();
				return usr.getPass();
			}
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			log4j.info("problem with getting user "+name);
			log4j.info(e.getMessage());
			throw new CouponsException("problem with getting user",e);
		} finally {
			if(session!=null)session.close();
		}
		return null;
	}
}


