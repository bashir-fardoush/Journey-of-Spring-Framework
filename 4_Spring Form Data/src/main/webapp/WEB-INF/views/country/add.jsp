<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add country</title>
</head>
<body>

<h2>Add new country: </h2>
<form:form action="${pageContext.request.contextPath}/country/add" modelAttribute="country">
	<form:input path="countryCode"/><br/>
	<form:input path="countryName"/><br/>
	
	<input type="submit" name="submit" value="Add country" >

</form:form>

</body>
</html>