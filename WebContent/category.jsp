<%@ page language="java" contentType="text/html; charset=windows-1255"
	import="java.util.*,il.ac.shenkar.finalproject.model.*"
    pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html, width=device-width, initial-scale=1; charset=windows-1255" name="viewport">
<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
<style>
th {
    border-bottom: 1px solid #d6d6d6;
}

tr:nth-child(even) {
    background: #e9e9e9;
}
</style>
<title>Store</title>
</head>
<body>
	  <div data-role="page">
	  <div data-role="header">
	    <h1>Coupons By Category</h1>
	    <div data-role="navbar">
	      <ul>
	        <li><a href="/FinalWebCouponsProject/controller/store" data-icon="grid">All Coupons</a></li>
			<li><a href="/FinalWebCouponsProject/controller/location?longitude=100&latitude=200" data-icon="grid">By Location</a></li>
	     	<li><a href="/FinalWebCouponsProject/controller/shoppingcart" data-icon="grid">Cart</a></li>
	      </ul>
	    </div>
	    <div data-role="navbar">
	      <ul>
	        <li><a href="/FinalWebCouponsProject/controller/category?category=Technology" data-icon="star">Technology</a></li>
			<li><a href="/FinalWebCouponsProject/controller/category?category=Food" data-icon="star">Food</a></li>
			<li><a href="/FinalWebCouponsProject/controller/category?category=Culture" data-icon="star">Culture</a></li>
	     	<li><a href="/FinalWebCouponsProject/controller/category?category=Travel" data-icon="star">Travel</a></li>
	      </ul>
	    </div>
	  </div>
  	<div data-role="main" class="ui-content">
    	<table data-role="table" data-mode="columntoggle" class="ui-responsive ui-shadow" id="myTable">
            <thead>
              <tr>
                <th>ID</th>
                <th data-priority="1">Name</th>
                <th data-priority="2">Description</th>
                <th data-priority="3">Category</th>
                <th data-priority="4">Longitude</th>
                <th data-priority="5">Latitude</th>
                <th data-priority="6">Expiration Date</th>
                <th>Add To Cart</th>
              </tr>
            </thead>
            <tbody>
            	<%@ taglib uri="/WEB-INF/tld/tdcoupon.tld" prefix="coupons" %>	
				<coupons:couponstable coupons="${coupons}"></coupons:couponstable>
            </tbody>
       </table>
   </div>
</body>
</html>