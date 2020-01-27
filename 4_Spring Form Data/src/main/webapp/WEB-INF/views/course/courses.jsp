<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Courses</title>
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
						<h2>Finalize courses Here</h2>
					</div>
				</div>

				<div class="row">
					<div class="col-sm-6 col-md-4 col-lg-3 left-content">

						<a class="btn btn-info btn-block"
							href="${pageContext.request.contextPath}/index">Student</a> 
							<a class="btn btn-info btn-block" href="${pageContext.request.contextPath}/course/courses">Course</a> 
							<a	class="btn btn-info btn-block" href="${pageContext.request.contextPath}/country/countries">Country</a>

					</div>

					<div class="col-sm-6 col-md-8 col-lg-9 right-content">

						<!--START of course search option -->
						<div class="row">
							<div class="col-sm-12">
								<form:form 
									action="${pageContext.request.contextPath}/course/search"
									modelAttribute="course">
									<div class="form-row">
										<div class="form-group col-md-6">

											<label for="courseCodeSearch">Search course by course
												code: </label>
											<form:input type="text" class="form-control"
												id="courseCodeSearch" placeholder="Enter course code"
												path="courseCode" />
										</div>									
									</div>
										<input type="submit" name="submit" class="btn btn-primary"
											value="search" />
								</form:form>
								<br/>
							</div>
						</div>
						<!--END of course search option -->

						<!--START of course List show -->
						<div class="row">
							<div class="col-sm-12">

								<h3>List of available courses:</h3>
								<table class="table table-bordered">
									<thead class="thead-light">
										<tr>
											<th scope="col">ID</th>
											<th scope="col">Course code</th>
											<th scope="col" colspan="3">Course Name</th>

										</tr>
									</thead>

									<tbody>
										<c:forEach items="${course_list}" var="course">
											<tr>
												<td>${course.id}</td>
												<td>${course.courseCode}</td>
												<td>${course.courseName}</td>
												<td><a
													href="${pageContext.request.contextPath}/course/edit?courseCode=${course.courseCode}">Edit</a></td>
												<td><a
													href="${pageContext.request.contextPath}/course/delete?courseCode=${course.courseCode}">Delete</a></td>
											</tr>

										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<!--END of course List show -->

						<!-- START of  add new course -->
						<div class="row">
							<div class="col-sm-12">

								<h3>Add new course to list</h3>

								<form:form  class="form-group"
									action="${pageContext.request.contextPath}/course/add"
									modelAttribute="course">
									<div class="form-row">
										<div class="form-group col-md-6">

											<label for="courseCodeEntry">Course Code</label>
											<form:input type="text" class="form-control"
												id="courseCodeEntry" placeholder="Course Code"
												path="courseCode" />
										</div>
										<div class="form-group col-md-6">
											<label for="courseameEntry">Course Name</label>
											<form:input type="text" class="form-control"
												id="courseameEntry" placeholder="Course Node"
												path="courseName" />
										</div>
										<input type="submit" name="submit" class="btn btn-primary"
											value="Add Course" />

									</div>

								</form:form>

							</div>
						</div>
						<!-- END of add new course -->

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














