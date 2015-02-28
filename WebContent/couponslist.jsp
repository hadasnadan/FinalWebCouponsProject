<%@ page language="java" contentType="text/html; charset=windows-1255"
    import="java.util.*,il.ac.shenkar.finalproject.model.*"
    pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>All Coupons list</title>
</head>

<body>
 
<table>
<tr>
	<th>id</th><th>name</th><th>description</th><th>category</th><th>longitude</th><th>latitude</th><th>date</th>
</tr>
<%
Iterator iterator = (Iterator) request.getAttribute("coupons");

while(iterator.hasNext())
{
	Coupon coupon = (Coupon)iterator.next();
	
%>
	<tr>
	<td><%= coupon.getId() %></td>
	<td><%= coupon.getName() %></td>
	<td><%= coupon.getDescription() %></td>
	<td><%= coupon.getCategory() %></td>
	<td><%= coupon.getLongitude() %></td>
	<td><%= coupon.getLatitude() %></td>
	<td><%= coupon.getDate() %></td>
	
	</tr>
	
<% 
}
%>

</table>

</body>

</html>