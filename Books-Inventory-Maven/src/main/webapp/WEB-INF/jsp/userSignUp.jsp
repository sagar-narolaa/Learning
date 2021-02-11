<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>        
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign Up </title>
</head>
<body style="margin-top: 50px;align-self: center">
	<form:form action="signup" method="post" modelAttribute="signUp">
		<label>First Name</label> <input type="text" placeholder="First Name" name="fname" class="form-control"  required="required"><br><br> 
		<label>Last Name</label> <input type="text" placeholder="Last Name" name="lname" class="form-control"  required="required"><br><br> 
		<label>Address Line 1</label> <input type="text" placeholder="Line 1" name="addrList[0].addrLine1" class="form-control"  required="required"><br><br> 
		<label>Address Line 2</label> <input type="text" placeholder="Line 2" name="addrList[0].addrLine2" class="form-control"  ><br><br> 
		<label>Address Line 3</label> <input type="text" placeholder="Line 3" name="addrList[0].addrLine3" class="form-control"  ><br><br> 
		<label>Pincode</label> <input type="text" placeholder="pincode" name="pincode" class="form-control"  required="required"><br><br> 
<!-- 							  <p>Address Type:</p>
					  
										  <div>
					  <input type="radio" id="huey" name="drone" value="huey"
					         checked>
					  <label for="huey">Home</label>
					</div>
					
					<div>
					  <input type="radio" id="dewey" name="drone" value="dewey">
					  <label for="dewey">Office</label>
					</div>
					
					<div>
					  <input type="radio" id="louie" name="drone" value="louie">
					  <label for="louie">Corporate</label>
					</div> -->

		
		<!-- <label>Address Type:</label> Home<input type="radio" name="home" class="form-control"  required="required">   <br><br> 
		<label></label> Office<input type="radio" name="office" class="form-control"  required="required">   <br><br> 
		<label></label> Corporate<input type="radio" name="corporate" class="form-control"  required="required">   <br><br>  -->
		<label>Email</label> <input type="email" placeholder="Email" class="form-control" name="email" required="required"><br><br>
		<label>Password</label> <input type="password" placeholder="Password" class="form-control" name="pwd" required="required"><br><br>		
		
	 	<button style=" display: block;width: 40%;background: green;text-align: center;border-radius: 5px;color: white;font-weight: bold;line-height: 25px " type="submit" class="btn btn-success">Sign Up</button>
	</form:form>

</body>
</html>