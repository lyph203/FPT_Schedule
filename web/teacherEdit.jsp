<%-- 
    Document   : teacherEdit
    Created on : Jul 12, 2023, 11:33:13 AM
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
            Code: <input type="text" name="code" value="${tea.getCode()}"><br>
            Name: <input type="text" name="name" value="${tea.getName()}"><br>
            Email: <input type="text" name="email" value="${tea.getEmail()}"><br>
            Account: <input type="text" name="acc" value="${tea.getAccountID()}"><br>
            <input type="hidden" value="${tea.getTeacherID()}" name="teachId">
            <input type="submit" value="Add" name="addTeachbtn">  
            <input type="submit" value="Update" name="updateTeachbtn">   
        </form>
    </body>
</html>
