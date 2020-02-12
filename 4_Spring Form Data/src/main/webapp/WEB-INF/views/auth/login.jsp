<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login to continue</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<link href="${pageContext.request.contextPath}/css/login.css"
	rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
</head>
<body>

<c:if test="${error == 'true'}">
    <div class="alert alert-danger" role="alert">
    Wrong username or password
    </div>
</c:if>



	<div class="sidenav">



		<div class="login-main-text">
			<h2>
				Application<br> Login Page
			</h2>
			<p>Login or register from here to access.</p>



		</div>
	</div>
	<div class="main">
		<div class="col-md-6 col-sm-12">
			<div class="login-form">
				<form action="${pageContext.request.contextPath}/login-processing"
					method="POST">
					<div class="form-group">
						<label>User Name</label> <input type="text" name="username"
							class="form-control" placeholder="User Name">
					</div>
					<div class="form-group">
						<label>Password</label> <input type="password" name="password"
							class="form-control" placeholder="Password">
					</div>
					<button type="submit" class="btn btn-black">Login</button>
					<button type="submit" class="btn btn-secondary">Register</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>