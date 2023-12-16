<%-- 
    Document   : takeAtt
    Created on : Jun 21, 2023, 9:42:23 AM
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
        <link href='${pageContext.request.contextPath}\css\takeAtt.css' rel='stylesheet'>
        <title>Take Attendance</title>
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
            Username: <c:out value="${teachCode}" /><br>
            Role: teacher
        </div>

        <table class="listClassroom">
            <c:forEach items="${listClassroom}" var="optClass">
                <tr>
                    <td><c:out value="${optClass.getName()}"/></td>
                    <!-- <td><select name="slotId">
                            <c:forEach items="${listSlot}" var="optSlot">
                                <option value="${optSlot.slotID}">${optSlot.slotID} : ${optSlot.slotNum} - ${optSlot.subjectCode} - ${optSlot.date}</option>
                            </c:forEach>
                        </select></td> -->
                    <td><a href="attendance?mod1=1&cId=${optClass.getID()}&aId=${aId}">View</a></td>
                    <td><a href="attendance?mod2=2&cId=${optClass.getID()}&aId=${aId}">Edit</a></td>
                </tr>
            </c:forEach>
        </table>    

        <div class="footer">
            <p>Mọi góp ý, thắc mắc xin liên hệ: Phòng dịch vụ sinh viên: Email: dichvusinhvien@fe.edu.vn. Điện thoại: (024)7308.13.13</p>
            <p>© Powered by Lyreon</p> 
        </div>    
            
        <script type="text/javascript">
            const toggleSidebar = () => document.body.classList.toggle("open");
        </script>
    </body>
</html>
