<%-- 
    Document   : list
    Created on : Jun 3, 2026, 8:09:19 PM
    Author     : minhq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>

        <meta charset="UTF-8">
        <title>Membership Plans</title>

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
                width:250px;
                height:100vh;
                background:#1e293b;
                position:fixed;
                color:white;
                padding:20px;
            }

            .sidebar h2{
                margin-bottom:30px;
            }

            .sidebar a{
                display:block;
                color:white;
                text-decoration:none;
                padding:12px;
                margin-bottom:8px;
                border-radius:6px;
            }

            .sidebar a:hover{
                background:#334155;
            }

            .main{
                margin-left:250px;
                padding:30px;
            }

            .topbar{
                background:white;
                padding:15px;
                border-radius:10px;
                margin-bottom:20px;
            }

            .card{
                background:white;
                padding:20px;
                border-radius:10px;
            }

            table{
                width:100%;
                border-collapse:collapse;
            }

            th,td{
                border:1px solid #ddd;
                padding:12px;
            }

            th{
                background:#2563eb;
                color:white;
            }

            .btn{
                padding:8px 12px;
                border-radius:5px;
                color:white;
                text-decoration:none;
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

            .search-box{
                margin-bottom:20px;
            }

            .search-box input{
                padding:10px;
                width:300px;
            }

        </style>

    </head>

    <body>

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

        <div class="main">

            <div class="topbar">
                <h2>Membership Plans</h2>
            </div>

            <div class="card">

                <p style="margin-bottom:20px">

                    <a href="${pageContext.request.contextPath}/membership-plans/add"
                       class="btn btn-primary">

                        Add Plan

                    </a>

                </p>

                <table>

                    <thead>

                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Duration</th>
                            <th>Price</th>
                            <th>Actions</th>
                        </tr>

                    </thead>

                    <tbody>

                        <c:forEach items="${plans}"
                                   var="plan">

                            <tr>

                                <td>${plan.id}</td>

                                <td>${plan.name}</td>

                                <td>${plan.durationMonths} Months</td>

                                <td>$${plan.price}</td>

                                <td>

                                    <a class="btn btn-info"
                                       href="${pageContext.request.contextPath}/membership-plans/detail/${plan.id}">
                                        Detail
                                    </a>

                                    <a class="btn btn-warning"
                                       href="${pageContext.request.contextPath}/membership-plans/edit/${plan.id}">
                                        Edit
                                    </a>

                                    <a class="btn btn-danger"
                                       href="${pageContext.request.contextPath}/membership-plans/delete/${plan.id}"
                                       onclick="return confirm('Delete this plan?')">
                                        Delete
                                    </a>

                                </td>

                            </tr>

                        </c:forEach>

                    </tbody>

                </table>

            </div>

        </div>

    </body>
</html>