<%-- 
    Document   : wtime
    Created on : Jun 10, 2023, 9:15:29 PM
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
        <title>Weekly Timetable</title>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&display=swap" rel="stylesheet">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <link href='${pageContext.request.contextPath}\css\wtime.css' rel='stylesheet'>
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

        <!-- Table select year and week -->
        <form action="wtime" method="POST" id="cSelect">
            <table style="margin-top: 60px;
                            margin-left: 140px;" border="1">
                <tr>
                    <td>
                        <select name="year" class="opt" onchange="document.getElementById('cSelect').submit()">
                            <c:forEach items="${listYear}" var="optYear">
                                <option ${optYear eq year ? 'selected="selected"' : ''} value="${optYear}"><c:out value="${optYear}"/></option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>MON</td>
                    <td>TUE</td>
                    <td>WED</td>
                    <td>THU</td>
                    <td>FRI</td>
                    <td>SAT</td>
                    <td>SUN</td>
                </tr>
                <tr>
                    <td>
                        <select name="week" class="opt" onchange="document.getElementById('cSelect').submit()">
                            <c:forEach items="${listWeek}" var="optWeek">
                                <option ${optWeek eq nowWeek ? 'selected="selected"' : ''} value="${optWeek}" ><c:out value="${optWeek}"/></option>
                            </c:forEach>
                        </select>
                    </td>
                    <c:forEach items="${listDayOfWeek}" var="optDayOfWeek">
                        <td>
                            <c:out value="${optDayOfWeek}"/>
                        </td>
                    </c:forEach>
                </tr>
                <tr>
                    <td>Slot 1</td>
                    <c:forEach items="${listDayOfWeek}" var="dayCheck">
                        <td>
                            <c:forEach items="${listSlot}" var="optSlot">
                                <c:if test="${optSlot.date == dayCheck && optSlot.slotNum == 1}">
                                    <c:out value="${optSlot.slotID}"/><br>
                                    <c:out value="${optSlot.teacherName}"/><br>
                                    <c:out value="${optSlot.roomName}"/><br>
                                    <c:out value="${optSlot.subjectCode}"/><br>
                                    (7:30 - 9:45)<br> 
                                    <c:forEach items="${listAttendance}" var="optAtt">
                                        <c:if test="${optSlot.slotID eq optAtt.slotID}">
                                            <c:out value="${optAtt.isAbsent}"/><br>
                                        </c:if>
                                    </c:forEach>
                                            <input type="hidden" value="${optSlot.slotID}" name="slotId">
                                        <input type="text" name="setSlotNum"><br>
                                        <input type="submit" name="updateSlotNum" value="Update Slot Num">
                                </c:if>
                            </c:forEach>
                        </td>
                    </c:forEach>
                </tr>
                <tr>
                    <td>Slot 2</td>
                    <c:forEach items="${listDayOfWeek}" var="dayCheck">
                        <td>
                            <c:forEach items="${listSlot}" var="optSlot">
                                <c:if test="${optSlot.date == dayCheck && optSlot.slotNum == 2}">
                                    <c:out value="${optSlot.slotID}"/><br>
                                    <c:out value="${optSlot.teacherName}"/><br>
                                    <c:out value="${optSlot.roomName}"/><br>
                                    <c:out value="${optSlot.subjectCode}"/><br>
                                    (9:55 - 12:20)<br> 
                                    <c:forEach items="${listAttendance}" var="optAtt">
                                        <c:if test="${optSlot.slotID eq optAtt.slotID}">
                                            <c:out value="${optAtt.isAbsent}"/><br>
                                        </c:if>
                                    </c:forEach>
                                            <input type="hidden" value="${optSlot.slotID}" name="slotId">
                                        <input type="text" name="setSlotNum"><br>
                                        <input type="submit" name="updateSlotNum" value="Update Slot Num">
                                </c:if>
                            </c:forEach>
                        </td>
                    </c:forEach>
                </tr>
                <tr>
                    <td>Slot 3</td>
                    <c:forEach items="${listDayOfWeek}" var="dayCheck">
                        <td>
                            <c:forEach items="${listSlot}" var="optSlot">
                                <c:if test="${optSlot.date == dayCheck && optSlot.slotNum == 3}">
                                    <c:out value="${optSlot.slotID}"/><br>
                                    <c:out value="${optSlot.teacherName}"/><br>
                                    <c:out value="${optSlot.roomName}"/><br>
                                    <c:out value="${optSlot.subjectCode}"/><br>
                                    (12:50 - 15:10)<br> 
                                    <c:forEach items="${listAttendance}" var="optAtt">
                                        <c:if test="${optSlot.slotID eq optAtt.slotID}">
                                            <c:out value="${optAtt.isAbsent}"/><br>
                                        </c:if>
                                    </c:forEach>
                                        <input type="hidden" value="${optSlot.slotID}" name="slotId">
                                        <input type="text" name="setSlotNum"><br>
                                        <input type="submit" name="updateSlotNum" value="Update Slot Num">
                                </c:if>
                            </c:forEach>
                        </td>
                    </c:forEach>
                </tr>
                <tr>
                    <td>Slot 4</td>
                    <c:forEach items="${listDayOfWeek}" var="dayCheck">
                        <td>
                            <c:forEach items="${listSlot}" var="optSlot">
                                <c:if test="${optSlot.date == dayCheck && optSlot.slotNum == 4}">
                                    <c:out value="${optSlot.slotID}"/><br>
                                    <c:out value="${optSlot.teacherName}"/><br>
                                    <c:out value="${optSlot.roomName}"/><br>
                                    <c:out value="${optSlot.subjectCode}"/><br>
                                    (15:20 - 17:40)<br> 
                                    <c:forEach items="${listAttendance}" var="optAtt">
                                        <c:if test="${optSlot.slotID eq optAtt.slotID}">
                                            <c:out value="${optAtt.isAbsent}"/><br>
                                        </c:if>
                                    </c:forEach>
                                            <input type="hidden" value="${optSlot.slotID}" name="slotId">
                                        <input type="text" name="setSlotNum"><br>
                                        <input type="submit" name="updateSlotNum" value="Update Slot Num">
                                </c:if>
                            </c:forEach>
                        </td>
                    </c:forEach>
                </tr>
                <tr></tr>
                <input type="hidden" value="${aName}" name="aName">
            </table>
        </form>
            
            <p></p>    

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
