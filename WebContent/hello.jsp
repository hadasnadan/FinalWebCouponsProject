<%@ page language="java" contentType="text/html; charset=windows-1255"
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

    <title>Welcome</title>

    <!-- Bootstrap core CSS -->
    <link href="../../dist/css/bootstrap.min.css" rel="stylesheet">

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
<body>
 <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
   		  <a class="navbar-brand" href="#">Final Coupon Project</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <form method="post" action="/FinalWebCouponsProject/controller/admin?logged=0" class="navbar-form navbar-right">
            <div class="form-group">
              <input name="user" type="text" placeholder="User name" class="form-control">
            </div>
            <div class="form-group">
              <input name="pwd" type="password" placeholder="Password" class="form-control">
            </div>
            <button type="submit" class="btn btn-success">Admin Sign in</button>
          </form>
        </div><!--/.navbar-collapse -->
      </div>
    </nav>

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
      <div class="container">
        <h1>Hello Guest!</h1>
        <p>You have reached coupons platform. Here you can choose the coupon you desire.</p>
      </div>
    </div>

    <div class="container">
      <!-- Example row of columns -->
      <div class="row">
      	<div class="col-md-4">
          <h2>All</h2>
          <p>Review the full list of coupons and add your desired coupons to one dreamy cart. </p>
          <p>Revise la lista completa de los cupones y añadir tus cupones deseados a un carro de ensueño.</p>
          <p><a class="btn btn-default" href="/FinalWebCouponsProject/controller/store" role="button">View details &raquo;</a></p>
        </div>
        <div class="col-md-4">
          <h2>Location</h2>
          <p>Get a list of coupons that are located near you in one click.</p>
          <p>Obtenga una lista de cupones que se encuentran cerca de usted en un solo clic.</p>
          <p><a class="btn btn-default" href="/FinalWebCouponsProject/controller/location?longitude=100&latitude=200" role="button">View details &raquo;</a></p>
        </div>
        <div class="col-md-4">
          <h2>Category</h2>
          <p>Get a list of coupons according to a selected category.</p>
          <p>Obtenga una lista de cupones de acuerdo con una categoría seleccionada.</p>
          <p><a class="btn btn-default" href="/FinalWebCouponsProject/controller/category?category=Technology" role="button">View details &raquo;</a></p>
       </div>
      </div>

      <hr>

      <footer>
        <p>&copy; David Kahan & Hadas Nadan | Shenkar 2014</p>
      </footer>
    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="../../dist/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>