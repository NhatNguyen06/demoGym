<%-- 
    Document   : list
    Created on : Jun 3, 2026, 8:23:20 PM
    Author     : minhq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Payment List</title>

        <style>
            body{
                font-family:Arial;
                background:#f4f6f9;
                margin:20px;
            }

            table{
                width:100%;
                border-collapse:collapse;
                background:white;
            }

            th,td{
                border:1px solid #ddd;
                padding:10px;
                text-align:left;
            }

            th{
                background:#2563eb;
                color:white;
            }

            .btn{
                padding:6px 10px;
                text-decoration:none;
                color:white;
                border-radius:4px;
            }

            .green{
                background:#16a34a;
            }
            .blue{
                background:#2563eb;
            }
            .orange{
                background:#f59e0b;
            }
            .red{
                background:#dc2626;
            }
        </style>
    </head>

    <body>

        <h2>Payment Management</h2>
        <div class="sidebar">
            <div class="logo">
                <i class="bi bi-lightning-charge-fill"></i>
                TheMiN GYM
            </div>

            <div class="menu">
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
        </div>  
        <p>
            <strong>Total Revenue:</strong>
            ${totalRevenue}
        </p>

        <div style="display:flex; gap:10px; flex-wrap:wrap; margin-bottom:15px;">

            <a class="btn blue"
               href="${pageContext.request.contextPath}/dashboard">
                Dashboard
            </a>

            <a class="btn green"
               href="${pageContext.request.contextPath}/payments/add">
                + Add Payment
            </a>

            <a class="btn green"
               href="${pageContext.request.contextPath}/payments/export/excel">
                📗 Export Excel
            </a>

            <a class="btn red"
               href="${pageContext.request.contextPath}/payments/export/pdf">
                📕 Export PDF
            </a>

        </div>

        <br><br>

        <table>

            <tr>
                <th>ID</th>
                <th>Member ID</th>
                <th>Amount</th>
                <th>Payment Date</th>
                <th>Method</th>
                <th>Action</th>
            </tr>

            <c:forEach items="${payments}" var="payment">

                <tr>

                    <td>${payment.id}</td>
                    <td>${payment.memberId}</td>
                    <td>${payment.amount}</td>
                    <td>${payment.method}</td>
                    <td>
                        <c:choose>

                            <c:when test="${payment.status == 'COMPLETED'}">
                                <span style="color:green;font-weight:bold;">
                                    COMPLETED
                                </span>
                            </c:when>

                            <c:when test="${payment.status == 'PENDING'}">
                                <span style="color:orange;font-weight:bold;">
                                    PENDING
                                </span>
                            </c:when>

                            <c:otherwise>
                                <span style="color:red;font-weight:bold;">
                                    FAILED
                                </span>
                            </c:otherwise>

                        </c:choose>
                    </td>
                    <td>

                        <a class="btn blue"
                           href="${pageContext.request.contextPath}/payments/detail/${payment.id}">
                            Detail
                        </a>

                        <a class="btn orange"
                           href="${pageContext.request.contextPath}/payments/edit/${payment.id}">
                            Edit
                        </a>

                        <a class="btn green"
                           href="${pageContext.request.contextPath}/payments/receipt/${payment.id}">
                            Receipt
                        </a>

                        <a class="btn red"
                           onclick="return confirm('Delete payment?')"
                           href="${pageContext.request.contextPath}/payments/delete/${payment.id}">
                            Delete
                        </a>

                    </td>

                </tr>

            </c:forEach>

        </table>

    </body>
</html>