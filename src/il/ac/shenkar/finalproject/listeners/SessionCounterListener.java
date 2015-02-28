package il.ac.shenkar.finalproject.listeners;

import il.ac.shenkar.finalproject.controller.FinalProjectController;
import il.ac.shenkar.finalproject.model.Coupon;
import il.ac.shenkar.finalproject.model.MySQLCouponsDAO;

import java.util.HashMap;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

/**
 * Provide session manegment for admin
 */
public class SessionCounterListener implements HttpSessionListener {
	
	/**
	* Log4j logger
	*/
	static Logger log4j = Logger.getLogger(MySQLCouponsDAO.class);
	
	private static int totalSessions;
	private static HashMap<String, HttpSession> activesessions = new HashMap<String, HttpSession>();
	
	public SessionCounterListener() { }
	
	public static HashMap<String, HttpSession> getActivesessions() {
		log4j.info("Returning all active sessions");
		return activesessions;
	}

	/**
	 * Get total sessions counter
	 * @return Integer - counter
	 */
	  public static int getAllSession(){
		return totalSessions;
	  }
	 
	  /**
	   * Count all sessions and append them to a Map object
	   * @param HttpSessionEvent - for new session
	   */
	  @Override
	  public void sessionCreated(HttpSessionEvent sessEvent) {
		  log4j.info("New sessions created - append to map");
		  totalSessions++;
		  activesessions.put(sessEvent.getSession().getId(), sessEvent.getSession());
	  }
	 
	  /**
	   * Remove session from Map and update counter
	   * 
	   */
	  @Override
	  public void sessionDestroyed(HttpSessionEvent sessEvent) {
		  log4j.info("Session destroyed - remove from map");
		  totalSessions--;
		  activesessions.remove(sessEvent.getSession().getId());
	  }	
	}