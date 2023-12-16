<%-- 
    Document   : ctime
    Created on : Jun 10, 2023, 9:33:41 PM
    Author     : Pham Huong Ly
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">  
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Class Timetable</title>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&display=swap" rel="stylesheet">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <link href='${pageContext.request.contextPath}\css\ctime.css' rel='stylesheet'>
    </head>
    <body>
        <!-- sidebar -->
        <nav class="sidebar">
            <div class="sidebar-inner">
                <header class="sidebar-header">
                    <button type="button" class="sidebar-burger" 
                    onclick="toggleSidebar()">
                        <i class='bx bx-menu'></i>
                    </button>
                    <img src="DH-FPT-logo.jpg" class="sidebar-logo" alt="">
                </header>
                    <nav class="sidebar-menu">
                        <a href="login?aName=${aName}&home=1"><i class='bx bxs-data' ></i>
                            <span>Information</span>
                        </a>
                        <a href=""><i class='bx bxs-spreadsheet' ></i>
                            <span>Registration</span>
                        </a>
                        <a href=""><i class='bx bxs-report' ></i>
                            <span>Reports</span>
                        </a>
                        <a href=""><i class='bx bx-home-alt' ></i>
                            <span>Regulations</span>
                        </a>
                        <a href=""><i class='bx bxs-book' ></i>
                            <span>Courses</span>
                        </a>
                        <a href=""><i class='bx bxs-quote-single-right' ></i>
                            <span>Feedback</span>
                        </a>
                        <a href=""><i class='bx bxs-layer-plus' ></i>
                            <span>Others</span>
                        </a>
                    </nav>
            </div>
        </nav>
        <!-- search all subject in department-->
        <form action="ctime" method="POST" class="select-sub" id="cSelect">
            <ul>
                <c:forEach items="${listTerm}" var="optTerm">
                    <li><a href='ctime?mod=1&optTerm=${optTerm.ID}' onclick="submit"><c:out value="${optTerm.name}"/></a></li>
                </c:forEach>
            </ul>
            <ul>
                <c:forEach items="${listClassroomBegin}" var="optClassroom">
                    <li><a href='ctime?optTerm=${optLastTerm}&optClassroom=${optClassroom.ID}' onclick='document.getElementById("cSelect").submit();'><c:out value="${optClassroom.name}"/></a></li>
                    <input type="hidden" value="${optLastTerm}" name="optLastTerm">
                    <input type="hidden" value="${optLastClassroom}" name="optLastClassroom">
                </c:forEach>
            </ul>
            <input type="submit" class="search-btn" value="Tìm kiếm"></input>
        </form>

        <!-- timetable by group -->
        <!-- ten mon hoc dung vong for -->
        <!-- tat ca cac slot dung vong for-->
            
        <table border = 1>
            <th>Timetable by group</th>
            <c:forEach items="${listSubject}" var="optSubj">
                <tr><td style="font-weight: 600;"><c:out value="${optSubj.getName()}"/></td></tr>
                <tr> 
                    <td>DAY</td>
                    <td>SLOT</td>
                    <td>ROOM</td>
                    <td>TEACHER</td>
                    <td>ATTENDANCE</td>
                </tr>
                <c:forEach items="${listSlotByClass}" var="optSlot">
                    <tr>
                        <c:if test="${optSubj.getCode() == optSlot.getSubjectCode()}">
                            <td><c:out value="${optSlot.getDate()}"/></td>
                            <td><c:out value="${optSlot.getSlotNum()}"/></td>
                            <td><c:out value="${optSlot.getRoomName()}"/></td>
                            <td><c:out value="${optSlot.getTeacherName()}"/></td>
                            <c:forEach items="${listAttendance}" var="optAttendance">
                                <c:choose>
                                    <c:when test="${optAttendance.getSlotID() == optSlot.getSlotID()}">
                                        <td><c:out value="${optAttendance.getisAbsent()}"/></td>
                                        <%int count = 1;%>
                                    </c:when>
                                    <c:otherwise><%int count = 0;%></c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <c:if test="${count eq 0}"><td>Not Yet</td></c:if>
                        </c:if>
                    </tr>
                </c:forEach>
            </c:forEach>
        </table>

        <!-- footer -->
        <div class="footer">
            <p>Mọi góp ý, thắc mắc xin liên hệ: Phòng dịch vụ sinh viên: Email: dichvusinhvien@fe.edu.vn. Điện thoại: (024)7308.13.13</p>
            <p>© Powered by Lyreon</p> 
        </div>

        <script type="text/javascript">
            const toggleSidebar = () => document.body.classList.toggle("open");
        </script>
    </body>
</html>
