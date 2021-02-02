<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>Books-Inventory-Application</title>
        </head>

        <body>

            <br>

            <div class="row">
                <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

                <div class="container">
                    <h2 style="text-align: center;background-color: bisque" class="text-center">List of Books &#x1F4DA &#x1F4D9</h2>
                    <hr>
                    <div style="display:flex" class="container text-left">

             <a style=" margin-left:auto;margin-right:auto;text-decoration:none; ;display: block;width: 115px;height: 25px;background: #4E9CAF;padding: 10px;text-align: center;border-radius: 5px;color: white;font-weight: bold;line-height: 25px;" href="<%=request.getContextPath()%>/add" class="btn btn-success">Add
     New Book</a>
     
             <a style=" margin-left:auto;margin-right:auto ;text-decoration:none; ;display: block;height: 25px;background: brown;padding: 10px;text-align: center;border-radius: 5px;color: white;font-weight: bold;line-height: 25px;" href="<%=request.getContextPath()%>/generateExcel" class="btn btn-success">Generate Excel Sheet &#x1F4CA</a>
                    
              <a style=" margin-left:auto;margin-right:auto;text-decoration:none; ;display: block;width: 115px;height: 25px;background: red;padding: 10px;text-align: center;border-radius: 5px;color: white;font-weight: bold;line-height: 25px;" href="<%=request.getContextPath()%>/logout" class="btn btn-success">Log Out</a>
                    
                    
                    </div>
	
                    <% int excel_status=(int) request.getAttribute("excelGenerated"); %>
                    
                    <% if(excel_status==1){ %>
 							<h3 style=" displ=ay:block;text-align:center;background-color: green;color:white;text-align:center">
 							Excel Sheet Generated
                    		</h3>
 					 <% } %>
 					 
 						
						 
			  <% if(excel_status==-1){ %>
 							<h3 style=" display:block;text-align:center;background-color: black;color:white;text-align:center">
 							Please Close The Excel Sheet And Try Again! &#x1F60F
                    		</h3>
 			  <% } %>
               

                    
                    <br>
                    <table style="width:70%;background: papayawhip;text-decoration: ;margin-left: auto;margin-right: auto" class="table table-bordered" border=1>
                        <thead>
                            <tr>
                                <!-- <th>ID</th> -->
                                <th>Name</th>
                                <th>ISBN</th>
                                <th>Author</th>
                                <th>Edit/Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!--   for (Todo todo: todos) {  -->
                            <c:forEach var="book" items="${listBooks}">

                                <tr>

                                    <td>
                                        <c:out value="${book.name}" />
                                    </td>
                                    <td>
                                        <c:out value="${book.ISBN}" />
                                    </td>	
                                    <td>
                                        <c:out value="${book.author}" />
                                    </td>																<%-- <a href="delete?id=${book.id}">Delete</a></td> --%>

                                    <td><a href="edit/${book.id}">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete/${book.id}">Delete</a></td>
                                </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>

                    </table>
                </div>
            </div>

        </body>

        </html> 