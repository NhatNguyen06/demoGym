<%-- 
    Document   : list
    Created on : Jun 3, 2026, 7:53:23 PM
    Author     : minhq
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

    <head>

        <meta charset="UTF-8">

        <title>Member Management</title>

        <style>

            *{
                margin:0;
                padding:0;
                box-sizing:border-box;
                font-family:Arial,sans-serif;
            }

            body{
                background:#f4f6f9;
            }

            .sidebar{
                position:fixed;
                left:0;
                top:0;
                width:250px;
                height:100vh;
                background:#1e293b;
                color:white;
                padding:20px;
            }

            .sidebar h2{
                margin-bottom:30px;
                text-align:center;
            }

            .sidebar a{
                display:block;
                padding:12px;
                margin-bottom:10px;
                color:white;
                text-decoration:none;
                border-radius:6px;
            }

            .sidebar a:hover{
                background:#334155;
            }

            .main-content{
                margin-left:250px;
            }

            .navbar{
                background:white;
                padding:15px 25px;
                box-shadow:0 2px 5px rgba(0,0,0,.1);
                display:flex;
                justify-content:space-between;
                align-items:center;
            }

            .container{
                padding:25px;
            }

            .page-header{
                display:flex;
                justify-content:space-between;
                align-items:center;
                margin-bottom:20px;
            }

            .btn{
                padding:10px 16px;
                border:none;
                border-radius:6px;
                cursor:pointer;
                text-decoration:none;
                color:white;
            }

            .btn-primary{
                background:#2563eb;
            }

            .btn-info{
                background:#0891b2;
            }

            .btn-warning{
                background:#f59e0b;
            }

            .btn-danger{
                background:#dc2626;
            }

            .card{
                background:white;
                border-radius:10px;
                padding:20px;
                box-shadow:0 2px 5px rgba(0,0,0,.1);
            }

            table{
                width:100%;
                border-collapse:collapse;
                margin-top:15px;
            }

            th,td{
                padding:12px;
                border:1px solid #ddd;
                text-align:left;
            }

            th{
                background:#e5e7eb;
            }

            #searchInput{
                width:300px;
                padding:10px;
                border:1px solid #ccc;
                border-radius:6px;
            }

            .action-group{
                display:flex;
                gap:5px;
            }
            .btn-success{
                background:#16a34a;
            }

            .btn-success:hover{
                background:#15803d;
            }

            .btn-danger-export{
                background:#dc2626;
            }

            .btn-danger-export:hover{
                background:#b91c1c;
            }

        </style>

    </head>

    <body>

        <!-- SIDEBAR -->

        <div class="sidebar">

            <h2>GYM SYSTEM</h2>

            <c:if test="${loggedUser.role == 'ADMIN'}">

                <a href="${pageContext.request.contextPath}/members">
                    Members
                </a>

                <a href="${pageContext.request.contextPath}/trainers">
                    Trainers
                </a>

                <a href="${pageContext.request.contextPath}/membership-plans">
                    Plans
                </a>

                <a href="${pageContext.request.contextPath}/subscriptions">
                    Subscriptions
                </a>

                <a href="${pageContext.request.contextPath}/classes">
                    Classes
                </a>

                <a href="${pageContext.request.contextPath}/payments">
                    Payments
                </a>

                <a href="${pageContext.request.contextPath}/equipment">
                    Equipment
                </a>

                <a href="${pageContext.request.contextPath}/attendance">
                    Attendance
                </a>

            </c:if>
            <c:if test="${loggedUser.role == 'MANAGER'}">

                <a href="${pageContext.request.contextPath}/trainers">
                    Trainers
                </a>

                <a href="${pageContext.request.contextPath}/membership-plans">
                    Plans
                </a>

                <a href="${pageContext.request.contextPath}/classes">
                    Classes
                </a>

                <a href="${pageContext.request.contextPath}/equipment">
                    Equipment
                </a>

            </c:if>
            <c:if test="${loggedUser.role == 'RECEPTIONIST'}">

                <a href="${pageContext.request.contextPath}/members">
                    Members
                </a>

                <a href="${pageContext.request.contextPath}/subscriptions">
                    Subscriptions
                </a>

                <a href="${pageContext.request.contextPath}/payments">
                    Payments
                </a>

                <a href="${pageContext.request.contextPath}/attendance">
                    Attendance
                </a>

            </c:if>
            <c:if test="${loggedUser.role == 'TRAINER'}">

                <a href="${pageContext.request.contextPath}/classes">
                    Classes
                </a>

            </c:if>
            <c:if test="${loggedUser.role == 'MEMBER'}">

                <a href="${pageContext.request.contextPath}/subscriptions">
                    My Subscription
                </a>

                <a href="${pageContext.request.contextPath}/attendance/history">
                    My Attendance
                </a>

                <a href="${pageContext.request.contextPath}/payments">
                    My Payments
                </a>

            </c:if>
            <c:if test="${loggedUser == null}">
                <a href="${pageContext.request.contextPath}/login">
                    Login
                </a>
            </c:if>

            <c:if test="${loggedUser != null}">
                <a href="${pageContext.request.contextPath}/logout">
                    Logout
                </a>
            </c:if>



        </div>

        <!-- MAIN -->

        <div class="main-content">

            <!-- NAVBAR -->

            <div class="navbar">

                <h3>Member Management</h3>

                <span>Administrator</span>

            </div>

            <div class="container">

                <div class="page-header">

                    <h1>Members</h1>

                    <div style="display:flex;gap:10px;">

                        <a href="${pageContext.request.contextPath}/members/export/excel"
                           class="btn btn-success">
                            📗 Export Excel
                        </a>

                        <a href="${pageContext.request.contextPath}/members/export/pdf"
                           class="btn btn-danger-export">
                            📕 Export PDF
                        </a>

                        <a href="${pageContext.request.contextPath}/members/add"
                           class="btn btn-primary">
                            + Add Member
                        </a>

                    </div>

                </div>

            </div>

            <div class="card">

                <input type="text"
                       id="searchInput"
                       placeholder="Search Member..."
                       onkeyup="searchTable()">

                <table id="memberTable">

                    <thead>

                        <tr>
                            <th>ID</th>
                            <th>Full Name</th>
                            <th>Email</th>
                            <th>Phone</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>

                    </thead>

                    <tbody>

                        <c:forEach items="${members}" var="member">

                            <tr>

                                <td>${member.id}</td>

                                <td>${member.fullName}</td>

                                <td>${member.email}</td>

                                <td>${member.phone}</td>

                                <td>${member.status}</td>

                                <td>

                                    <div class="action-group">

                                        <a class="btn btn-info"
                                           href="${pageContext.request.contextPath}/members/detail/${member.id}">
                                            Detail
                                        </a>

                                        <a class="btn btn-warning"
                                           href="${pageContext.request.contextPath}/members/edit/${member.id}">
                                            Edit
                                        </a>

                                        <a class="btn btn-danger"
                                           href="${pageContext.request.contextPath}/members/delete/${member.id}"
                                           onclick="return confirmDelete();">
                                            Delete
                                        </a>

                                    </div>

                                </td>

                            </tr>

                        </c:forEach>

                    </tbody>

                </table>

            </div>

        </div>

    </div>

    <script>

        function confirmDelete() {

            return confirm(
                    "Are you sure you want to delete this member?"
                    );

        }

        function searchTable() {

            let input =
                    document.getElementById("searchInput");

            let filter =
                    input.value.toLowerCase();

            let rows =
                    document.querySelectorAll(
                            "#memberTable tbody tr"
                            );

            rows.forEach(function (row) {

                let text =
                        row.textContent.toLowerCase();

                row.style.display =
                        text.includes(filter)
                        ? ""
                        : "none";

            });

        }

    </script>

</body>

</html>


