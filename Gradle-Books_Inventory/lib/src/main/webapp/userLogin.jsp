<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Login</title>
</head>
<body style="margin-top: 50px;align-self: center">
	<form action="login" method="post">
		<label>Email</label> <input type="email" placeholder="Email" class="form-control" name="email" required="required"><br><br>
		<label>Password</label> <input type="password" placeholder="Password" class="form-control" name="pwd" required="required"><br><br>
		
	 	<button style=" display: block;width: 40%;background: green;text-align: center;border-radius: 5px;color: white;font-weight: bold;line-height: 25px " type="submit" class="btn btn-success">Login</button>
	</form>
	
		<a style=" margin-top:40px;text-decoration:none; ;display: block;width: 115px;height: 25px;background: chocolate;padding: 10px;text-align: center;border-radius: 5px;color: white;font-weight: bold;line-height: 25px;" href="<%=request.getContextPath()%>/signupPage" class="btn btn-success">Register</a>
	
	
	<a style=" margin-top:40px;text-decoration:none; ;display: block;width: 115px;height: 25px;background: chocolate;padding: 10px;text-align: center;border-radius: 5px;color: white;font-weight: bold;line-height: 25px;" href="<%=request.getContextPath()%>/list" class="btn btn-success">List Of Books</a>


</body>
</html>