<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Form</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

	<section class="content">

	<!-- header section start -->
	<div class="header">
		<div class="container">
			
			<div class="row">
				<div class="col-sm-12 col-md-12 col-lg-12">

					<img src="${pageContext.request.contextPath}/images/educationbanner.jpg" width="100%" height="200px"/>				

				</div>
			</div>		
		</div>	
	</div>
	<!-- header section end -->

	<!-- main content section start -->

	<div class="maincontent">
		<div class="container">

			<div class="row">
				<div class="col-sm-12 welcome-header">
					<h2>Welcome to registration</h2>
				</div>
			</div>

			<div class="row">
				<div class="col-sm-6 col-md-4 col-lg-3 left-content">				

					<a class="btn btn-info btn-block" href="${pageContext.request.contextPath}/index">Student</a>
					<a class="btn btn-info btn-block" href="${pageContext.request.contextPath}/course/courses">Course</a>
					<a class="btn btn-info btn-block" href="${pageContext.request.contextPath}/country/countries">Country</a>
					
				</div>

				<div class="col-sm-6 col-md-8 col-lg-9 right-content">

					
				</div>
			</div>
			
		</div>

	</div>	
	<!-- main content section end -->				

	<!-- footer section start -->
	<div class="footer">
		<div class="container">
			<div class="row">
			<div class="col-sm-12">			
				<h2 align="right" >An investment in education pays the best interest.</h2>
				<p align="right">Benjamin Franklin</p>
			</div>
			
			<div class="row">
				<div class="col-sm-12">
					<h4 align="left">� All rights reserved to Education IT </h4>
				</div>
			</div>
			</div>
		
			
		</div>
		
	</div>
	<!-- footer section end -->


	</section>
</body>
</html>