<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Show Courses</title>
</head>
<body>

<h2>${message}</h2>

<table>
	<tr>
		<th>Id</th>
		<th>Course Code</th>
		<th>Course Name</th>
	</tr>
	
 	<c:forEach items="${course_list}" var="course">
		<tr>
			<td>${course.id}</td>
			<td>${course.courseCode}</td>
			<td>${course.courseName}</td>
		</tr>
	 </c:forEach> 
	
</table>


</body>
</html>