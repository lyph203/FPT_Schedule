<%-- 
    Document   : subjectEdit
    Created on : Jul 11, 2023, 9:58:01 PM
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
            Code: <input type="text" name="code" value="${sub.getCode()}"><br>
            Name: <input type="text" name="name" value="${sub.getName()}"><br>
            Plan: <input type="text" name="plan" value="${sub.getPlan()}"><br>
            Term: <select name="termid">
                <c:forEach items="${listTerm}" var="optTerm">
                    <option ${optTerm.getID() eq sub.getTerm() ? 'selected="selected"' : ''} value="${optTerm.getID()}">${optTerm.getName()}</option>
                </c:forEach></select><br>
            Block: <input type="text" name="block" value="${sub.getBlock()}"><br>    
            <input type="hidden" value="${sub.getID()}" name="subId">
            <input type="submit" value="Add" name="addSubbtn">  
            <input type="submit" value="Update" name="updateSubbtn">   
        </form>
    </body>
</html>
