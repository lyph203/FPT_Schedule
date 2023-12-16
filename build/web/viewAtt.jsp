<%-- 
    Document   : viewAtt
    Created on : Jun 27, 2023, 3:08:32 PM
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
        <form action="attendance" method="POST">
            <select name="slotId">
                <c:forEach items="${listSlot}" var="optSlot">
                    <option value="${optSlot.slotID}">${optSlot.slotID} : ${optSlot.slotNum} - ${optSlot.subjectCode} - ${optSlot.date}</option>
                </c:forEach>
            </select>
            <input type="hidden" name="mod1" value="1">
            <input type="hidden" name="aId" value="${aId}">
            <input type="hidden" name="cId" value="${cId}">
            <input type="submit" class="search-btn" value="Tìm kiếm" name="search"></button>
        </form>
        <table border = 1>
            <tr>
                <td>Full name</td>
                <td>Attendance</td>
                <td>Comment</td>
            </tr>

            <c:forEach items="${listAttendance}" var="optAtt">
                <tr>
                    <td>${optAtt.getStudentName()}</td>
                    <td>
                        <input type="radio" id="m" name="${optAtt.getStudentID()}" value="1" ${optAtt.getisAbsent() == "1" ? 'checked="checked"' : ''} disabled>
                        <label for="m">Absent</label>
                        <input type="radio" id="f" name="${optAtt.getStudentID()}" value="0" ${optAtt.getisAbsent() == "0" ? 'checked="checked"' : ''} disabled>
                        <label for="f">Present</label><br>
                    </td>
                    <td>${optAtt.getComment()}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
