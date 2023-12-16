<%-- 
    Document   : adminControl
    Created on : Jun 21, 2023, 8:22:31 PM
    Author     : Pham Huong Ly
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">  
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin</title>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&display=swap" rel="stylesheet">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <link href='${pageContext.request.contextPath}/css/adminControl.css' rel='stylesheet'>
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
        
        <div class="edit">
            <p>Slot</p>
            <a href="admin?addSlot=1">Add</a>
            <table border = 1
                   style="margin-top: 20px;
                   width: 100%;
                   text-align: center;">
                <tr style="height: 70px;">
                    <td>Slot ID</td>
                    <td>Slot Number</td>
                    <td>Class</td>
                    <td>Date</td>
                    <td>Teacher</td>
                    <td>Room</td>
                    <td>Subject</td>
                </tr>

                <c:forEach items="${listSlot}" var="optSlot">
                    <tr style="height: 70px;">
                        <td>${optSlot.getSlotID()}</td>
                        <td>${optSlot.getSlotNum()}</td>
                        <c:forEach items="${listClassroom}" var="optClass">
                            <c:if test="${optClass.getID() == optSlot.getClassID()}">
                                <td>${optClass.getName()}</td>
                            </c:if>
                        </c:forEach>
                        <td>${optSlot.getDate()}</td>
                        <c:forEach items="${listTeacher}" var="optTeacher">
                            <c:if test="${optTeacher.getTeacherID() == optSlot.getTeacherID()}">
                                <td>${optTeacher.getCode()}</td>
                            </c:if>
                        </c:forEach>
                        <c:forEach items="${listRoom}" var="optRoom">
                            <c:if test="${optRoom.getID() == optSlot.getRoomID()}">
                                <td>${optRoom.getName()}</td>
                            </c:if>
                        </c:forEach>
                        <c:forEach items="${listSubject}" var="optSub">
                            <c:if test="${optSub.getID() == optSlot.getSubjectID()}">
                                <td style="text-align: left;">${optSub.getName()}</td>
                            </c:if>
                        </c:forEach>     
                        <td><a href="admin?updateSlot=1&slotId=${optSlot.getSlotID()}">Update</a></td>
                        <td><a href="admin?delSlot=1&slotId=${optSlot.getSlotID()}">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <div class="edit">
            <p>Student</p>
            <a href="admin?addStudent=1">Add</a>
            <table border = 1
                   style="margin-top: 20px;
                   width: 100%;
                   text-align: center;">
                <tr style="height: 70px;">
                    <td>ID</td>
                    <td>Name</td>
                    <td>Gender</td>
                    <td>Address</td>
                    <td>Date</td>
                    <td>Account</td>
                </tr>

                <c:forEach items="${listStudent}" var="optStudent">
                    <tr style="height: 70px;">
                        <td>${optStudent.getStudentID()}</td>
                        <td style="text-align: left;">${optStudent.getFullname()}</td>
                        <td>${optStudent.isGender()}</td>
                        <td>${optStudent.getAddress()}</td>
                        <td>${optStudent.getDate()}</td>
                        <td>${optStudent.getAccountID()}</td>
                        <td><a href="admin?updateStudent=1&stuId=${optStudent.getStudentID()}">Update</a></td>
                        <td><a href="admin?delStudent=1&stuId=${optStudent.getStudentID()}">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <div class="edit">
            <p>Class</p>
            <a href="admin?addClass=1">Add</a>
            <table border = 1
                   style="margin-top: 20px;
                   width: 50%;
                   text-align: center;">
                <tr style="height: 70px;">
                    <td>ID</td>
                    <td>Name</td>
                </tr>

                <c:forEach items="${listClassroom}" var="optClass">
                    <tr style="height: 70px;">
                        <td>${optClass.getID()}</td>
                        <td>${optClass.getName()}</td>
                        <td><a href="admin?updateClass=1&classId=${optClass.getID()}">Update</a></td>
                        <td><a href="admin?delClass=1&classId=${optClass.getID()}">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <div class="edit">
            <p>Room</p>
            <a href="admin?addRoom=1">Add</a>
            <table border = 1
                   style="margin-top: 20px;
                   width: 50%;
                   text-align: center;">
                <tr style="height: 70px;">
                    <td>ID</td>
                    <td>Name</td>
                </tr>

                <c:forEach items="${listRoom}" var="optRoom">
                    <tr style="height: 70px;">
                        <td>${optRoom.getID()}</td>
                        <td>${optRoom.getName()}</td>
                        <td><a href="admin?updateRoom=1&roomId=${optRoom.getID()}">Update</a></td>
                        <td><a href="admin?delRoom=1&roomId=${optRoom.getID()}">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <div class="edit">
            <p>Subject</p>
            <a href="admin?addSubject=1">Add</a>
            <table border = 1
                   style="margin-top: 20px;
                   width: 100%;
                   text-align: center;">
                <tr style="height: 70px;">
                    <td>ID</td>
                    <td>Code</td>
                    <td>Name</td>
                    <td>Plan</td>
                    <td>Term</td>
                    <td>Block</td>
                </tr>

                <c:forEach items="${listSubject}" var="optSub">
                    <tr style="height: 70px;">
                        <td>${optSub.getID()}</td>
                        <td>${optSub.getCode()}</td>
                        <td style="text-align: left;">${optSub.getName()}</td>
                        <td>${optSub.getPlan()}</td>
                        <c:forEach items="${listTerm}" var="optTerm">
                            <c:if test="${optSub.getTerm() == optTerm.getID()}">
                                <td>${optTerm.getName()}</td>
                            </c:if>
                        </c:forEach>
                        <td>${optSub.getBlock()}</td>
                        <td><a href="admin?updateSubject=1&subId=${optSub.getID()}">Update</a></td>
                        <td><a href="admin?delSubject=1&subId=${optSub.getID()}">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <div class="edit">
            <p>Term</p>
            <a href="admin?addTerm=1">Add</a>
            <table border = 1
                   style="margin-top: 20px;
                   width: 50%;
                   text-align: center;">
                <tr style="height: 70px;">
                    <td>ID</td>
                    <td>Name</td>
                </tr>

                <c:forEach items="${listTerm}" var="optTerm">
                    <tr style="height: 70px;">
                        <td>${optTerm.getID()}</td>
                        <td>${optTerm.getName()}</td>
                        <td><a href="admin?updateTerm=1&subId=${optTerm.getID()}">Update</a></td>
                        <td><a href="admin?delTerm=1&subId=${optTerm.getID()}">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <div class="edit">
            <p>Account</p>
            <a href="admin?addAccount=1">Add</a>
            <table border = 1
                   style="margin-top: 20px;
                   width: 50%;
                   text-align: center;">
                <tr style="height: 70px;">
                    <td>ID</td>
                    <td>Username</td>
                    <td>Password</td>
                    <td>Role</td>
                </tr>

                <c:forEach items="${listAccounts}" var="optAcc">
                    <tr style="height: 70px;">
                        <td>${optAcc.getID()}</td>
                        <td>${optAcc.getUsername()}</td>
                        <td>${optAcc.getPassword()}</td>
                        <td>
                            <c:choose>
                                <c:when test="${optAcc.getRoleNum() == 1}"> student</c:when>
                                <c:when test="${optAcc.getRoleNum() == 2}"> admin</c:when>
                                <c:when test="${optAcc.getRoleNum() == 3}"> teacher</c:when>
                            </c:choose>
                        </td>
                        <td><a href="admin?updateAccount=1&accId=${optAcc.getID()}">Update</a></td>
                        <td><a href="admin?delAccount=1&accId=${optAcc.getID()}">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <div class="edit">
            <p>Attendance</p>
            <a href="admin?addAttendance=1">Add</a>
            <table border = 1
                   style="margin-top: 20px;
                   width: 50%;
                   text-align: center;">
                <tr style="height: 70px;">
                    <td>Teacher</td>
                    <td>Student</td>
                    <td>Attendance</td>
                    <td>Comment</td>
                    <td>Slot</td>
                </tr>

                <c:forEach items="${listAttendance}" var="optAtt">
                    <tr style="height: 70px;">
                        <c:forEach items="${listTeacher}" var="optTeacher">
                            <c:if test="${optTeacher.getTeacherID() == optAtt.getTeacherID()}">
                                <td>${optTeacher.getCode()}</td>
                            </c:if>
                        </c:forEach>
                        <c:forEach items="${listStudent}" var="optStu">
                            <c:if test="${optStu.getStudentID() == optAtt.getStudentID()}">
                                <td>${optStu.getFullname()}</td>
                            </c:if>
                        </c:forEach>
                        <td>${optAtt.getisAbsent()}</td>
                        <td>${optAtt.getComment()}</td>
                        <td>${optAtt.getSlotID()}</td>
                        <td><a href="admin?updateAttendance=1&teaId=${optAtt.getTeacherID()}&stuId=${optAtt.getStudentID()}&slot=${optAtt.getSlotID()}">Update</a></td>
                        <td><a href="admin?delAttendance=1&teaId=${optAtt.getTeacherID()}&stuId=${optAtt.getStudentID()}&slot=${optAtt.getSlotID()}">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <div class="edit">
            <p>Teacher</p>
            <a href="admin?addTeacher=1">Add</a>
            <table border = 1
                   style="margin-top: 20px;
                   width: 50%;
                   text-align: center;">
                <tr style="height: 70px;">
                    <td>ID</td>
                    <td>Code</td>
                    <td>Name</td>
                    <td>Email</td>
                    <td>Account</td>
                </tr>

                <c:forEach items="${listTeacher}" var="optTea">
                    <tr style="height: 70px;">
                        <td>${optTea.getTeacherID()}</td>
                        <td>${optTea.getCode()}</td>
                        <td>${optTea.getName()}</td>
                        <td>${optTea.getEmail()}</td>
                        <td>${optTea.getAccountID()}</td>
                        <td><a href="admin?updateTeacher=1&teachId=${optTea.getTeacherID()}">Update</a></td>
                        <td><a href="admin?delTeacher=1&teachId=${optTea.getTeacherID()}">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>

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
