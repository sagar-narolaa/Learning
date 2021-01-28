<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>Book Inventory</title>
        </head>

        <body>

            <!-- <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                        <a href="https://www.javaguides.net" class="navbar-brand"> Book Management App </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Vehicles</a></li>
                    </ul>
                </nav>
            </header> -->
            <br>
            <div style="margin-left: 20%; margin-right: 20%;max-width: 50%" class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                        <c:if test="${book != null}">
                            <form action="update" method="post">
                        </c:if>
                        <c:if test="${book == null}">
                            <form action="insert" method="post">
                        </c:if>

                        <caption>
                            <h2 style=" margin-top:40px;text-decoration:none;display: block;height: 25px;background: crimson;padding: 10px;text-align: center;color: white;font-weight: bold;line-height: 25px;">
                                <c:if test="${book != null}">
                                    Edit Book
                                </c:if>
                                <c:if test="${book == null}">
                                    Add Book
                                </c:if>
                            </h2>
                        </caption>

                        <c:if test="${book != null}">
                            <input type="hidden" name="id" value="<c:out value='${book.id}' />" />
                        </c:if>

                        <!-- <fieldset class="form-group"> -->
                            <label>Book Name</label> <input type="text" value="<c:out value='${book.name}' />" class="form-control" name="name" required="required"><br><br>
                        <!-- </fieldset> -->

                        <!--  <fieldset class="form-group">-->
                            <label>ISBN</label> <input type="text" value="<c:out value='${book.ISBN}' />" class="form-control" name="ISBN"><br><br>
                        <!-- </fieldset> -->

                        <!--  <fieldset class="form-group">-->
                           <label>Author</label> <input type="text" value="<c:out value='${book.author}' />" class="form-control" name="Author"><br><br>
                        <!-- </fieldset> -->

                        <button style=" display: block;width: 40%;background: green;text-align: center;border-radius: 5px;color: white;font-weight: bold;line-height: 25px " type="submit" class="btn btn-success">Save</button>
                        
                        <a style=" margin-top:40px;text-decoration:none; ;display: block;width: 115px;height: 25px;background: chocolate;padding: 10px;text-align: center;border-radius: 5px;color: white;font-weight: bold;line-height: 25px;" href="<%=request.getContextPath()%>/list" class="btn btn-success">List Of Books</a>
                        
                    </div>
                </div>
            </div>
        </body>

        </html>