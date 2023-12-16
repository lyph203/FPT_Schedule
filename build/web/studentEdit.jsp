<%-- 
    Document   : studentEdit
    Created on : Jun 27, 2023, 1:40:32 PM
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
            Full name: <input type="text" name="name" value="${stu.getFullname()}"><br>
            Gender:
            <input type="radio" id="m" name="gender" value="1" ${stu.isGender() eq "1" ? 'checked="checked"' : ''} checked>
            <label for="m">Male</label>
            <input type="radio" id="f" name="gender" value="0" ${stu.isGender() eq "0" ? 'checked="checked"' : ''}>
            <label for="f">Female</label><br>
            Date of Birth: <input type="date" name="dob" value="${stu.getDate()}"> <br>
            Address: <input type="text" name="address" value="${stu.getAddress()}"><br>
            <c:if test="${updateStudent eq 1}">
                Account ID: <input type="text" name="aId" value="${stu.getAccountID()}"><br>
            </c:if>
            <input type="hidden" value="${stu.getStudentID()}" name="stuId">
            <input type="submit" value="Add" name="addStudentbtn">  
            <input type="submit" value="Update" name="updateStudentbtn">   
        </form>
    </body>
</html>
