<%-- 
    Document   : classEdit
    Created on : Jul 11, 2023, 8:31:37 PM
    Author     : Pham Huong Ly
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="admin" method="POST">
            Name: <input type="text" name="name" value="${cla.getName()}"><br>
            <input type="hidden" value="${cla.getID()}" name="classId">
            <input type="submit" value="Add" name="addClassbtn">  
            <input type="submit" value="Update" name="updateClassbtn">   
        </form>
    </body>
</html>
