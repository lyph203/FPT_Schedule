<%-- 
    Document   : home
    Created on : Jun 10, 2023, 9:15:19 PM
    Author     : Pham Huong Ly
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&display=swap" rel="stylesheet">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${pageContext.request.contextPath}\css\home.css"/>
        <title>Home FAP</title>
    </head>
    <body>
        <nav class="sidebar">
            <div class="sidebar-inner">
                <header class="sidebar-header">
                    <button type="button" class="sidebar-burger" 
                    onclick="toggleSidebar()">
                        <i class='bx bx-menu'></i>
                    </button>
                    <img src="https://i.pinimg.com/474x/bd/58/e1/bd58e10dd230eee0f0172a084ed81027.jpg" class="sidebar-logo" alt="">
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
        
        <div class="userInf">
            Username: <c:out value="${acc.getUsername()}" /><br>
            Role: <c:choose>
                        <c:when test="${acc.getRoleNum() == 1}"> student</c:when>
                        <c:when test="${acc.getRoleNum() == 2}"> admin</c:when>
                        <c:when test="${acc.getRoleNum() == 3}"> teacher</c:when>
                    </c:choose><br>
            <a href="loginFAP.jsp">Logout</a>          
        </div>

        <div class="information">
            <ul>
                <li><a href="utime">University timetable (Lịch học)</a></li>
                <li><a href="wtime?aName=${acc.getUsername()}">Weekly timetable (Thời khóa biểu từng tuần)</a></li>
                <li><a href="">Blended Online Course (BLOC) Schedules <br>
                    (Lịch học các môn theo phương pháp BLOC trong kỳ)</a></li>
                <li><a href="ctime?aName=${acc.getUsername()}">Class timetable (Xem thời khóa biểu của một lớp)</a></li>
                <li><a href="">View exam schedule (Xem lịch thi) </a></li>
            </ul>

            <ul>
                <li><a href="">Tuition fee per course (Biểu học phí)</a></li>
                <li><a href="">View Syllabuses(Xem đề cương môn học)</a></li>
                <li><a href="">EduNext student guideline</a></li>
                <li><a href="">Help/Hỗ trợ</a></li>
                <li><a href="">Tài liệu hướng dẫn: Định hướng cho sinh viên</a></li>
                 <!-- take attendance -->
                 <c:if test="${acc.getRoleNum() eq 3}">
                     <li><a href="attendance?aId=${acc.getID()}">Take Attendance</a></li>
                 </c:if>
            </ul>
        </div>

        <div class="footer">
            <p>Mọi góp ý, thắc mắc xin liên hệ: Phòng dịch vụ sinh viên: Email: dichvusinhvien@fe.edu.vn. Điện thoại: (024)7308.13.13</p>
            <p>© Powered by Lyreon</p> 
        </div>

        <script type="text/javascript">
            const toggleSidebar = () => document.body.classList.toggle("open");
        </script>
    </body>
</html>
