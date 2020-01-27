<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Countries</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css">

</head>
<body>

	<section class="content">

		<!-- START of header section -->
		<div class="header">
			<div class="container">
				<div class="row">
					<div class="col-sm-12 col-md-12 col-lg-12">
						<img alt="Banner"
							src="${pageContext.request.contextPath}/images/educationbanner.jpg"
							width="100%" height="200px" />
					</div>
				</div>
			</div>

		</div>
		<!-- END of header section -->

		<!-- START of main content -->
		<div class="maincontent">
			<div class="container">
				<div class="row">
					<div class="col-sm-12 welcome-header">
						<h2>Finalize countries</h2>
					</div>
				</div>

				<div class="row">
					<div class="col-sm-4 col-md-4 col-lg-3 left-content">
						<a class="btn btn-info btn-block"
							href="${pageContext.request.contextPath}/index">Student</a> <a
							class="btn btn-info btn-block"
							href="${pageContext.request.contextPath}/course/courses">Course</a>
						<a class="btn btn-info btn-block" href="${pageContext.request.contextPath}/country/countries">Country</a>
					</div>
					<div class="col-sm-8 col-md-8 col-lg-9 right-content">

						<!-- START of country search option -->
						<div class="row">
							<div class="col-sm-12">

								<form:form class="form-group"
									action="${pageContext.request.contextPath}/country/search/"
									modelAttribute="country">
									<div class="form-row">
										<div class="form-group col-md-6">
											<label for="countryCodeSearch">Search by country Code</label>
											<form:input type="text" class="form-control"
												placeholder="Country code" id="countryCodeSearch"
												path="countryCode" />
										</div>
									</div>
									<input type="submit" class="btn btn-success" name="submit"
										value="Search">
								</form:form>
								<br/>
							</div>
						</div>
						<!-- END of country search option -->

						<!-- START of Country list option -->
						<div class="row">
							<div class="col-sm-12">

								<h2>Country List:</h2>
								<table class="table table-bordered">
									<thead class="thead-light">
										<tr>
											<th scope="col">Id</th>
											<th scope="col">Country Code</th>
											<th scope="col" colspan="3">Country name</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${country_list}" var="country">

											<tr>
												<td>${country.id}</td>
												<td>${country.countryCode}</td>
												<td>${country.countryName}</td>
												<td><a class="btn"
													href="${pageContext.request.contextPath}/country/edit?countryCode=${country.countryCode}">Edit</a></td>
												<td><a class="btn"
													href="${pageContext.request.contextPath}/country/delete?countryCode=${country.countryCode}">Delete</a>
												</td>
											</tr>
										</c:forEach>
									</tbody>

								</table> </br>							

							</div>
						</div>
						<!-- END of country list option -->

						<!-- START country add option -->
						<div class="row">
							<div class="col-sm-12">

								<form:form class="form-group"
									action="${pageContext.request.contextPath}/country/add/"
									modelAttribute="country">

									<div class="form-row">
										<div class="form-group col-md-6">
											<label for="countryCodeEntry">Country Code</label>
											<form:input type="text" id="countryCodeEntry"
												class="form-control" placeholder="country code"
												path="countryCode" />
										</div>

										<div class="form-group col-md-6">
											<label for="countryNameEntry">Country Name</label>
											<form:input type="text" id="countryNameEntry"
												class="form-control" placeholder="country name"
												path="countryName" />
										</div>
									</div>

									<input class="btn btn-success" type="submit" name="submit"
										value="Add country" />

								</form:form>
							</div>
						</div>
						<!-- END of country add option -->

					</div>
				</div>

			</div>
		</div>
		<!-- END of main content -->

		<!-- START of footer section -->
		<div class="footer">
			<div class="container">
				<div class="row">
					<div class="col-sm-12">
						<h2 align="right">An investment in education pays the best
							interest.</h2>
						<p align="right">Benjamin Franklin</p>
					</div>

					<div class="row">
						<div class="col-sm-12">
							<h4 align="left">© All rights reserved to Education IT</h4>
						</div>
					</div>
				</div>


			</div>
		</div>
		<!-- END of footer section -->
	</section>

</body>
</html>