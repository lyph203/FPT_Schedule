<%-- 
    Document   : editTerm
    Created on : Jul 12, 2023, 9:00:56 AM
    Author     : Pham Huong Ly
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="admin" method="POST">
            Name: <input type="text" name="name" value="${term.getName()}"><br>
            <input type="hidden" value="${term.getID()}" name="termId">
            <input type="submit" value="Add" name="addTermbtn">  
            <input type="submit" value="Update" name="updateTermbtn">   
        </form>
    </body>
</html>
