<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Form</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

	<section class="content">

		<!-- header section start -->
		<div class="header">
			<div class="container">

				<div class="row">
					<div class="col-sm-12 col-md-12 col-lg-12">

						<img
							src="${pageContext.request.contextPath}/images/educationbanner.jpg"
							width="100%" height="200px" />

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
						<h2>Edit registered Student</h2>
					</div>
				</div>

				<div class="row">
					<div class="col-sm-6 col-md-4 col-lg-3 left-content">

						<a class="btn btn-info btn-block"
							href="${pageContext.request.contextPath}/index">Student</a> <a
							class="btn btn-info btn-block"
							href="${pageContext.request.contextPath}/course/courses">Course</a>
						<a class="btn btn-info btn-block"
							href="${pageContext.request.contextPath}/country/countries">Country</a>

					</div>

					<div class="col-sm-6 col-md-8 col-lg-9 right-content">						

						<!-- START of Student Add option -->
						<div class="row">
							<div class="col-sm-12">
								<form:form class="form-group"
									action="${pageContext.request.contextPath}/student/edit"
									modelAttribute="student">
									
									<form:input type="hidden" path="id" value="${student.id}"/>

									<div class="row">
										<div class="col-md-6">

											<div class="form-group col-sm-12">
												<label for="studentNameAdd">Name</label>
												<form:input id="studentNameAdd" class="form-control"
													type="text" placeholder="Student Name" path="name" />
											</div>

											<div class="form-group col-sm-12">
												<label for="studentAgeAdd">Age</label>
												<form:input id="studentAgeAdd" class="form-control"
													type="number" placeholder="Student Age" path="age" />
											</div>



											<div class="form-group col-sm-12">
												<label for="studentEmailAdd">Email</label>
												<form:input id="studentEmailAdd" class="form-control"
													type="text" placeholder="Student Email" path="email" />
											</div>

											<div class="form-group  col-sm-12">
												<label>Select a country</label>
												<form:select class="form-control" path="countryCode">
													<form:options items="${countries}" itemLabel="countryName"
														itemValue="countryCode"></form:options>
												</form:select>
											</div>

											<div class="form-group  col-sm-12">
												<label>Select Gender</label></br>
												<div class="form-check display-block">
													<form:radiobutton class="form-check-input" path="gender"
														id="maleRadio" value="Male" />
													<label class="form-check-label" for="maleRadio">Male</label>
												</div>
												<div class="form-check display-block">
													<form:radiobutton class="form-check-input" path="gender"
														id="femaleRadio" value="Female" />
													<label class="form-check-label" for="femaleRadio">Female</label>
												</div>
												<div class="form-check display-block">
													<form:radiobutton class="form-check-input" path="gender"
														id="othersRadio" value="Others" />
													<label class="form-check-label" for="othersRadio">Others</label>
												</div>
											</div>

										</div>

										<div class="col-md-6">
											<!-- course list -->
											<div class="form-group">
												<label>Select courses</label>
												<form:checkboxes  path="courseCodes" items="${courses}"
													itemLabel="courseName" itemValue="courseCode" />

											</div>
										</div>

									</div>

									<input type="submit" name="submit" class="btn btn-info"
										value="Save Student">
								</form:form>

							</div>
						</div>

						<!-- END of Student Add option -->
						


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
		<!-- footer section end -->


	</section>
</body>
</html>