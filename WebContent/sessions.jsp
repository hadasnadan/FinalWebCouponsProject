<%@ page language="java" contentType="text/html; charset=windows-1255"
	import="java.util.*,il.ac.shenkar.finalproject.model.*"
    pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>admin</title>

    <!-- Bootstrap core CSS -->
    <link href="../../dist/css/bootstrap.min.css" rel="stylesheet">
    
	<!-- Bootstrap theme -->
    <link href="../../dist/css/bootstrap-theme.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="theme.css" rel="stylesheet">
    
    <!-- Custom styles for this template -->
    <link href="jumbotron.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../../assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  
  <script type="text/javascript" src="6_S3_"></script>
</head>
 <body role="document">

    <!-- Fixed navbar -->
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Hello Admin!</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li><a href="/FinalWebCouponsProject/controller/admin?logged=1">Home</a></li>
            <li><a href="/FinalWebCouponsProject/controller/addcoupon?logged=1">Add Coupon</a></li>
            <li><a href="/FinalWebCouponsProject/controller/deletecoupon?logged=1">Delete Coupon</a></li>
            <li><a href="/FinalWebCouponsProject/controller/updatecoupon?logged=1">Update Coupon</a></li>
            <li class="active"><a href="/FinalWebCouponsProject/controller/sessions?logged=1">Sessions Control</a></li>
            <li><a href="/FinalWebCouponsProject/controller/hello">Exit</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>

    <div class="container theme-showcase" role="main">     

      <div class="page-header" style="margin-top: 100px">
        <h1>Sessions Control</h1>
      </div>
      <div class="row">
          <table class="table table-striped">
           <% HashMap<String, HttpSession> sessions = (HashMap<String, HttpSession>)request.getAttribute("sessions");
           	Iterator iterator = sessions.entrySet().iterator();
           	System.out.println("sessions size: " +sessions.size());
			for(Map.Entry<String, HttpSession> entry : sessions.entrySet() ) {
				HttpSession current_session = entry.getValue();
				ArrayList<String> attributes = new ArrayList<String>();
				/* print session Id */
				out.print("<tr>"+
						"<th style=\"color:gray;\" class=\"col-sm-1\">Session Id</th> <th style=\"color:gray;\" class=\"col-sm-1\">"+
						entry.getKey()+"</th>"+
						"</tr>"+
						"<tr> <th class=\"col-sm-1\">Attribute Name</th> <th class=\"col-sm-1\">Attribute Value</th> </tr>"
				);
				/* print session attributes */
				String attribute;
				for(Object o : Collections.list(current_session.getAttributeNames() )) {
					attribute = current_session.getAttributeNames().nextElement();
					out.print("<tr>"+ 
							"<td>"+o.toString()+"</td><td>"+
							current_session.getAttribute(o.toString().toString())+"</td>"+
							"</tr>");
				}
			}	
			%>
          </table>
        </div>
	     
    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="../../dist/js/bootstrap.min.js"></script>
    <script src="../../assets/js/docs.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
  
</body>
</html>