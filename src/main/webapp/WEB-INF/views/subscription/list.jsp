<%-- 
    Document   : list
    Created on : Jun 3, 2026, 8:12:06 PM
    Author     : minhq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Subscription Management</title>

        <style>

            body{
                margin:0;
                font-family:Arial,sans-serif;
                background:#f4f6f9;
            }

            .sidebar{
                width:250px;
                height:100vh;
                background:#1e293b;
                position:fixed;
                color:white;
            }

            .sidebar h2{
                padding:20px;
            }

            .sidebar a{
                display:block;
                padding:15px 20px;
                color:white;
                text-decoration:none;
            }

            .sidebar a:hover{
                background:#334155;
            }

            .main{
                margin-left:250px;
                padding:25px;
            }

            .card{
                background:white;
                border-radius:10px;
                padding:20px;
            }

            table{
                width:100%;
                border-collapse:collapse;
            }

            th,td{
                padding:12px;
                border:1px solid #ddd;
            }

            th{
                background:#2563eb;
                color:white;
            }

            .btn{
                padding:8px 12px;
                text-decoration:none;
                color:white;
                border-radius:5px;
            }

            .primary{
                background:#2563eb;
            }
            .info{
                background:#0891b2;
            }
            .warning{
                background:#f59e0b;
            }
            .danger{
                background:#dc2626;
            }
            .success{
                background:#16a34a;
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

            <div class="card">

                <h2>Subscription Management</h2>

                <br>

                <a class="btn primary"
                   href="${pageContext.request.contextPath}/subscriptions/register">
                    Register Subscription
                </a>

                <br><br>

                <table>

                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Member ID</th>
                            <th>Plan ID</th>
                            <th>Start Date</th>
                            <th>End Date</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>

                    <tbody>

                        <c:forEach items="${subscriptions}" var="subscription">

                            <tr>

                                <td>${subscription.id}</td>
                                <td>${subscription.memberId}</td>
                                <td>${subscription.planId}</td>
                                <td>${subscription.startDate}</td>
                                <td>${subscription.endDate}</td>
                                <td>${subscription.status}</td>

                                <td>

                                    <a class="btn info"
                                       href="${pageContext.request.contextPath}/subscriptions/detail/${subscription.id}">
                                        Detail
                                    </a>

                                    <a class="btn warning"
                                       href="${pageContext.request.contextPath}/subscriptions/edit/${subscription.id}">
                                        Edit
                                    </a>

                                    <a class="btn success"
                                       href="${pageContext.request.contextPath}/subscriptions/renew/${subscription.id}">
                                        Renew
                                    </a>

                                    <a class="btn danger"
                                       href="${pageContext.request.contextPath}/subscriptions/cancel/${subscription.id}">
                                        Cancel
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
