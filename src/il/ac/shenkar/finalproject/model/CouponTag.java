package il.ac.shenkar.finalproject.model;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class CouponTag extends SimpleTagSupport {
	private Iterator<Coupon> iterator;
	public void setCoupons(Iterator<Coupon> it) {
		iterator = it;
	} 
	
	public void doTag() throws JspException, IOException {
	 JspWriter out = getJspContext().getOut();
	 while(iterator.hasNext()) {
			Coupon coupon = (Coupon)iterator.next();
			out.print(	"<tr>"+
						"<td>"+coupon.getName()+"</td>"+
						"<td>"+coupon.getDescription()+"</td>"+
						"<td>"+coupon.getCategory()+"</td>"+
						"<td>"+coupon.getLongitude()+"</td>"+
						"<td>"+coupon.getLatitude()+"</td>"+
						"<td>"+coupon.getDate()+"</td>"+
						"<td><a href=\'/FinalWebCouponsProject/controller/shoppingcart?id="+coupon.getId()+"\'>Add to cart</a></td>"+
						"<tr>");
		}
	 }
}
