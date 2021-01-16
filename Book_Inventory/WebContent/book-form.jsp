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
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                        <c:if test="${book != null}">
                            <form action="update" method="post">
                        </c:if>
                        <c:if test="${book == null}">
                            <form action="insert" method="post">
                        </c:if>

                        <caption>
                            <h2>
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

                        <button type="submit" class="btn btn-success">Save</button>
                        
                    </div>
                </div>
            </div>
        </body>

        </html>