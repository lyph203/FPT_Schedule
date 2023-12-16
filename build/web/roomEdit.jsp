<%-- 
    Document   : roomEdit
    Created on : Jul 11, 2023, 9:46:02 PM
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
            Name: <input type="text" name="name" value="${room.getName()}"><br>
            <input type="hidden" value="${room.getID()}" name="roomId">
            <input type="submit" value="Add" name="addRoombtn">  
            <input type="submit" value="Update" name="updateRoombtn">   
        </form>
    </body>
</html>
