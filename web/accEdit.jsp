<%-- 
    Document   : accEdit
    Created on : Jul 12, 2023, 9:21:04 AM
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
            Username: <input type="text" name="name" value="${acc.getUsername()}"><br>
            Password: <input type="text" name="password" value="${acc.getPassword()}"><br>
            Role: <input type="text" name="role" value="${acc.getRoleNum()}"><br>
            <input type="hidden" value="${acc.getID()}" name="accId">
            <input type="submit" value="Add" name="addAccbtn">  
            <input type="submit" value="Update" name="updateAccbtn">   
        </form>
    </body>
</html>
