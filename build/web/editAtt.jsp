<%-- 
    Document   : editAtt
    Created on : Jun 27, 2023, 3:08:46 PM
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
            <input type="hidden" id="cId" name="cId" value="${cId}">
            <input type="hidden" name="aId" value="${aId}">
            <input type="submit" class="search-btn" value="Tìm kiếm" name="search"></button>
            <input type="submit" id="edit" class="search-btn" value="Chỉnh sửa" name="edit" onclick="disEdit()"></button>

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
                            <input type="radio" id="m" name="${optAtt.getStudentID()}" value="1" ${optAtt.getisAbsent() == "1" ? 'checked="checked"' : ''} checked>
                            <label for="m">Absent</label>
                            <input type="radio" id="f" name="${optAtt.getStudentID()}" value="0" ${optAtt.getisAbsent() == "0" ? 'checked="checked"' : ''}>
                            <label for="f">Present</label><br>
                        </td>
                        <td><input name="${optAtt.getStudentID()}comment" value="${optAtt.getComment()}"></td>
                    </tr>
                </c:forEach>
            </table>
        </form>
        <script>
            var date = new Date();
            var cId = document.getElementById("cId").value;
            alert(document.cookie);
            
            let cookieValue = document.cookie.split('; ')[0];
            let expiryDate = cookieValue.toString().split('=')[1];
            let expiry = new Date(expiryDate);
            
            let cookieValue2 = document.cookie.split('; ')[1];
            let cIdValue = cookieValue2.toString().split('=')[1];
            
            alert(expiry + "=" + new Date());
            alert(cId + "=" + cIdValue);
            
            // So sánh ngày hết hạn với thời gian hiện tại
            if (expiry < new Date() && cId === cIdValue) {
                document.getElementById("edit").disabled = true;
            } else {
                document.getElementById("edit").disabled = false;
            }

            function disEdit() {
                var date = new Date();
                date.setTime(date.getTime() + (60*1000));
                var expires = 'expires=' + date.toUTCString();
                var cId = 'cId=' + document.getElementById('cId').value;
                document.cookie = expires;
                document.cookie = cId;
            }
        </script>
    </body>
</html>
