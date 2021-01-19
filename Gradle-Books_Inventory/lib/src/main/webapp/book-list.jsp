<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>Book Management Application</title>
        </head>

        <body>


            <br>

            <div class="row">
                <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

                <div class="container">
                    <h2 style="text-align: center" class="text-center">List of Books</h2>
                    <hr>
                    <div class="container text-left">

                        <a style=" margin-left:auto;margin-right:auto ;text-decoration:none; ;display: block;width: 115px;height: 25px;background: #4E9CAF;padding: 10px;text-align: center;border-radius: 5px;color: white;font-weight: bold;line-height: 25px;" href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
     New Book</a>
                    </div>
                    <br>
                    <table style="margin-left: auto;margin-right: auto" class="table table-bordered" border=1>
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
                                    </td>

                                    <td><a href="edit?id=<c:out value='${book.id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete?id=<c:out value='${book.id}' />">Delete</a></td>
                                </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>

                    </table>
                </div>
            </div>
        </body>

        </html>