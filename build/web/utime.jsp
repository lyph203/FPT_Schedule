<%-- 
    Document   : utime
    Created on : Jun 10, 2023, 9:34:15 PM
    Author     : Pham Huong Ly
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>University Timetable</title>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&display=swap" rel="stylesheet">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" href="${pageContext.request.contextPath}\css\utime.css"/>
    </head>
    <body>
        <%@page import="java.util.ArrayList" %>
        <%
            String plan = "";
            if(request.getAttribute("plan")!= null){
                plan = request.getAttribute("plan") + " slots";
            }
        %>
        <!-- sidebar -->
        <nav class="sidebar">
            <div class="sidebar-inner">
                <header class="sidebar-header">
                    <button type="button" class="sidebar-burger" onclick="toggleSidebar()">
                        <i class='bx bx-menu'></i>
                    </button>
                    <img src="https://i.pinimg.com/474x/bd/58/e1/bd58e10dd230eee0f0172a084ed81027.jpg" class="sidebar-logo" alt="">
                </header>
                <nav class="sidebar-menu">
                    <a href="login?aName=${aName}&home=1"><i class='bx bxs-data'></i>
                        <span>Information</span>
                    </a>
                    <a href=""><i class='bx bxs-spreadsheet'></i>
                        <span>Registration</span>
                    </a>
                    <a href=""><i class='bx bxs-report'></i>
                        <span>Reports</span>
                    </a>
                    <a href=""><i class='bx bx-home-alt'></i>
                        <span>Regulations</span>
                    </a>
                    <a href=""><i class='bx bxs-book'></i>
                        <span>Courses</span>
                    </a>
                    <a href=""><i class='bx bxs-quote-single-right'></i>
                        <span>Feedback</span>
                    </a>
                    <a href=""><i class='bx bxs-layer-plus'></i>
                        <span>Others</span>
                    </a>
                </nav>
            </div>
        </nav>
        <!-- search all subject in department-->
        <form action="utime" method="POST" class="select-sub" id="cSelect">
            <ul>
                <c:forEach items="${listTerm}" var="optTerm">
                    <li><a href='utime?optTerm=${optTerm.ID}' onclick="submit"><c:out value="${optTerm.name}"/></a></li>
                </c:forEach>
            </ul>
            <ul>
                <c:forEach items="${listSubject}" var="optSub">
                <li><a href='utime?optTerm=${optLastTerm}&optSub=${optSub.ID}' onclick='document.getElementById("cSelect").submit();'><c:out value="${optSub.name}"/></a></li>
                <input type="hidden" value="${optLastSub}" name="optLastSub">
                <input type="hidden" value="${optLastTerm}" name="optLastTerm">
                </c:forEach>
            </ul>
            <input type="submit" class="search-btn" value="Tìm kiếm"></button>
        </form>

        <!-- list all class course-->
        <div class="list-course">
            <table border = 1>
                <th>List of Course</th>
                <tr>
                    <td>CLASS</td>
                    <td>TEACHER</td>
                    <td>PLAN</td>
                </tr>
                <c:forEach items="${listClassroom}" var="optClass">
                    <tr><td><c:out value="${optClass.name}"/></td>
                    <c:forEach items="${listTeacher}" var="teacher">
                        <c:if test="${optClass.teachId eq teacher.teacherID}">
                            <td><c:out value="${teacher.code}"/></td>
                        </c:if>
                    </c:forEach>
                    <td><%=plan%></td>        
                    </tr>    
                </c:forEach>
            </table>
        </div>

        <!-- footer -->
        <div class="footer">
            <p>Mọi góp ý, thắc mắc xin liên hệ: Phòng dịch vụ sinh viên: Email: dichvusinhvien@fe.edu.vn. Điện thoại:
                (024)7308.13.13</p>
            <p>© Powered by Lyreon</p>
        </div>

        <script type="text/javascript">
            const toggleSidebar = () => document.body.classList.toggle("open");
        </script>
    </body>
</html>
