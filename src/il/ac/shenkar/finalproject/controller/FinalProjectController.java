package il.ac.shenkar.finalproject.controller;

import il.ac.shenkar.finalproject.listeners.SessionCounterListener;
import il.ac.shenkar.finalproject.model.*;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class FinalProjectController
 */
@WebServlet("/controller/*")
public class FinalProjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	* Log4j logger
	*/
	static Logger log4j = Logger.getLogger(FinalProjectController.class);
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinalProjectController() {
        super();
    }
    
    /**
     * Init fucntion for servlet
     */
   public void init() {
	   log4j.info("---Server initiation---");
	   /* Set global attribute - inventory */
	   try {
			getServletContext().setAttribute("inventory", MySQLCouponsDAO.getInstance());
		} 
    	catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getPathInfo();
		RequestDispatcher dispatcher = null;
		
		/* Operations according user path */
		
		/* Welcome page */
		if(path.endsWith("hello")){ 
			dispatcher = getServletContext().getRequestDispatcher("/hello.jsp");
			dispatcher.forward(request, response);
		} 
		
		/* Admin Section */
		
		/* Admin page */
		else if(path.contains("admin")) {	
			try {
				MySQLCouponsDAO inventory= (MySQLCouponsDAO) getServletContext().getAttribute("inventory");
				/* Check if user authorized - if the value is null it means that user  
				 * try to type the URL directly with no authentication details
				 */
				if(!isLogged(request.getParameter("logged"))) {
					/* user try to login - check user details */
					String name = request.getParameter("user");
					String pass = request.getParameter("pwd");
					if(!authentication(name, pass, inventory)) {
						/* Wrong deatils - error */
						throw new CouponsException("User name or password invalid");
					}
				}
				/* Navigate to admin page */
				request.setAttribute("coupons", inventory.getCoupons());
				dispatcher = getServletContext().getRequestDispatcher("/admin.jsp");
				dispatcher.forward(request, response);
			} catch(CouponsException e) {
				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				dispatcher.forward(request,response);
			}
		} 
		/* Admin - navigate to sessions control page */
		else if(path.endsWith("sessions")){ 
			try {
				/* Check if user authorized */
				if(isLogged(request.getParameter("logged"))) {
					HttpSession session = request.getSession();
					/* Get all HTTP sessions and append to request */
					SessionCounterListener sl = new SessionCounterListener();
					request.setAttribute("sessions", SessionCounterListener.getActivesessions());
					System.out.println(SessionCounterListener.getActivesessions().size());
					dispatcher = getServletContext().getRequestDispatcher("/sessions.jsp");
				} else {
					dispatcher = getServletContext().getRequestDispatcher("/hello.jsp");
				}
				dispatcher.include(request, response);
			} catch (Exception e) {
				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
			}
		
		}
		/* Admin - navigate to add coupon page */
		else if(path.endsWith("addcoupon")){
			/* Check if user authorized */
			try {
				if(isLogged(request.getParameter("logged"))) {
					dispatcher = getServletContext().getRequestDispatcher("/add.jsp");
				} else {
					dispatcher = getServletContext().getRequestDispatcher("/hello.jsp");
				}
			} catch (CouponsException e) {
				e.printStackTrace();
			}
			dispatcher.forward(request, response);
		
		}
		/* Admin - navigate to update coupon page */
		else if(path.endsWith("updatecoupon")){
			/* Check if user authorized */
			try {
				if(isLogged(request.getParameter("logged"))) {
					dispatcher = getServletContext().getRequestDispatcher("/update.jsp");
				} else {
					dispatcher = getServletContext().getRequestDispatcher("/hello.jsp");
				}
			} catch (CouponsException e) {
				e.printStackTrace();
			}
			dispatcher.forward(request, response);
		
		}
		/* Admin - navigate to delete coupon page */
		else if(path.endsWith("deletecoupon")){
			/* Check if user authorized */
			try {
				if(isLogged(request.getParameter("logged"))) {
					dispatcher = getServletContext().getRequestDispatcher("/delete.jsp");
				} else {
					dispatcher = getServletContext().getRequestDispatcher("/hello.jsp");
				}
			} catch (CouponsException e) {
				e.printStackTrace();
			}
			dispatcher.forward(request, response);
			
		}
		/* Admin - add coupon operation */
		else if(path.endsWith("add")){ 
			/* New coupon details from form */
			String name=request.getParameter("name");
			String description=request.getParameter("description");
			String category=request.getParameter("category");
			int longitude= Integer.parseInt(request.getParameter("longitude"));
			int latitude= Integer.parseInt(request.getParameter("latitude"));
			int id= Integer.parseInt(request.getParameter("ID"));
			String date= request.getParameter("date"); 
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
			Date parsedDate;
			/* Add coupon to DB */
			try {
				parsedDate = dateFormat.parse(date);
				Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
				MySQLCouponsDAO.getInstance().addCoupon(new Coupon(description,category,name,id,timestamp,longitude,latitude));
				dispatcher = getServletContext().getRequestDispatcher("/add.jsp");
				dispatcher.forward(request, response);
			} catch (ParseException | CouponsException e) {
				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				dispatcher.forward(request,response);
			}
		}
		/* Admin - delete coupon operation */
		else if(path.endsWith("delete")){ 
			/* Get coupon id from request path */
			int id= Integer.parseInt(request.getParameter("ID"));
			/* Delete coupon from DB */
			try {
				MySQLCouponsDAO.getInstance().deleteCoupon(id);
				dispatcher = getServletContext().getRequestDispatcher("/delete.jsp");
				dispatcher.forward(request, response);
			} catch (CouponsException e) {
				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				dispatcher.forward(request,response);
			}
		} 
		/* Admin - update coupon operation */
		else if(path.endsWith("update")){ 
			/* Coupon's new details from form */
			String name=request.getParameter("name");
			String description=request.getParameter("description");
			String category=request.getParameter("category");
			int longitude= Integer.parseInt(request.getParameter("longitude"));
			int latitude= Integer.parseInt(request.getParameter("latitude"));
			int id= Integer.parseInt(request.getParameter("ID"));
			String date= request.getParameter("date"); 
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
			Date parsedDate;
			/* Update coupon on DB*/
			try {
				parsedDate = dateFormat.parse(date);
				Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
				MySQLCouponsDAO.getInstance().updateCoupon(new Coupon(description,category,name,id,timestamp,longitude,latitude));
				dispatcher = getServletContext().getRequestDispatcher("/update.jsp");
				dispatcher.forward(request, response);
			} catch (ParseException | CouponsException e) {
				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				dispatcher.forward(request,response);
			}
		}
		
		/* User Section */
		
		/* Present coupons by location */
		else if(path.endsWith("location")) { 
			/* Get user longtitude and latitude from path */
			String longitude = request.getParameter("longitude");
			String latitude = request.getParameter("latitude");
			if (longitude != null && latitude!= null) {
				/* Get from DB coupons by user location */
				try {
					MySQLCouponsDAO inventory= (MySQLCouponsDAO) getServletContext().getAttribute("inventory");
					request.setAttribute("coupons", inventory.getCouponsLocation(latitude, longitude));
					dispatcher = getServletContext().getRequestDispatcher("/location.jsp");
					/* Set auto refresh for out of date coupons */
					response.addHeader("Refresh", "600");
					dispatcher.forward(request, response);
				} catch(CouponsException e) {
					dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
					dispatcher.forward(request,response);
				}
			}
		} 
		/* Present coupons by categories */
		else if (path.endsWith("category")) {
			/* Get requested category from path */
			String category = request.getParameter("category");
			if (category != null) {
				try {
					/* Get coupons by category from DB */
					MySQLCouponsDAO inventory= (MySQLCouponsDAO) getServletContext().getAttribute("inventory");
					request.setAttribute("coupons", inventory.getCouponsCategory(category));
					dispatcher = getServletContext().getRequestDispatcher("/category.jsp");
					/* Set auto refresh for out of date coupons */
					dispatcher.forward(request, response);
				} catch(CouponsException e) {
					dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
					dispatcher.forward(request,response);
				}
			}
		} 
		/* All coupons page */
		else if(path.endsWith("store")) {
			try {
				/* Get all coupons from DB */
				MySQLCouponsDAO inventory= (MySQLCouponsDAO) getServletContext().getAttribute("inventory");
				request.setAttribute("coupons", inventory.getCouponsUpdated());
				dispatcher = getServletContext().getRequestDispatcher("/store.jsp");
				/* Set auto refresh for out of date coupons */
				response.addHeader("Refresh", "120");
				dispatcher.forward(request, response);
			} catch(Exception e) {
				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				dispatcher.forward(request,response);
			}	
		} 
		/* User shopping cart */
		else if(path.endsWith("shoppingcart")) {
			try {
				HttpSession session = request.getSession();
				/* Crate user cart if not exist */
				if(session.getAttribute("cart") == null) {
					session.setAttribute("cart", new ShoppingCart());
				}
				ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
				/* Check if coupon was added */
				String couponId = request.getParameter("id");
				if(couponId != null) {
					/* Adding coupon to shoppingcart */
					Coupon coupon = MySQLCouponsDAO.getInstance().getCoupon(Integer.parseInt(couponId));
					cart.addCoupon(coupon);
				}
				/* Set attribute for shoppingcart coupons lines */
				request.setAttribute("cart", cart.getLines());
				/* Navigate to shoppingcart page */
				dispatcher = getServletContext().getRequestDispatcher("/shoppingcart.jsp");
				/* Set auto refresh for out of date coupons */
				response.addHeader("Refresh", "120");
				dispatcher.forward(request,response);
			} catch (Exception e) {
				e.printStackTrace();
				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}


	/**
	 * Responsible for check user input for name and password.
	 */
	private boolean authentication(String user, String pwd, MySQLCouponsDAO inventory) {
		log4j.info("Authentication attempt with details: "+ user +", "+pwd);
		String password;
		try {
			 /* Get user password from request */
			password = inventory.getPassword(user);
			if(password != null) {
				 /* Check deatils */
				if(password.equals(secureMD5(pwd))) {
					log4j.info("Admin logged in");
					return true;
				}
			}
		} catch (CouponsException e) {
			log4j.info("Failed to login");
			log4j.info(e.getMessage());
			e.printStackTrace();
		}
		
		return false;
	}


	/**
	 * MD5 algorithm
	 */
	private String secureMD5(String passwordToHash) {
		String generatedPassword = null;
	    /* Create MessageDigest instance for MD5 */
	    MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			/* Add password bytes to digest */
	        md.update(passwordToHash.getBytes());
	        /* Get the hash's bytes */
	        byte[] bytes = md.digest();
	        /* This bytes[] has bytes in decimal format;
	         * Convert it to hexadecimal format
	         */
	        StringBuilder sb = new StringBuilder();
	        for(int i=0; i< bytes.length ;i++) {
	            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        /* Get complete hashed password in hex format */
	        generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}
	
	private boolean isLogged(String flag) throws CouponsException {
		if(flag != null) {
			if(flag.equals("0")) {
				return false;
			} else {
				return true;
			}
		} else {
			throw new CouponsException("invalid use");
		}
	}
}

