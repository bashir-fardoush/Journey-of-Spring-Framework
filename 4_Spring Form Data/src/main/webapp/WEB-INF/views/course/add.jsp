<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Course</title>
</head>
<body>

<h2>Add new Course: </h2>
<form:form action="${pageContext.request.contextPath}/course/add" modelAttribute="course">
	<form:input path="courseCode"/><br/>
	<form:input path="courseName"/><br/>
	
	<input type="submit" name="submit" value="Add Course" >

</form:form>

</body>
</html>