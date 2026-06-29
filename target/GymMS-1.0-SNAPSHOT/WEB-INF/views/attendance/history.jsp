<%-- 
    Document   : history
    Created on : Jun 10, 2026, 7:21:53 PM
    Author     : minhq
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>

    <head>

        <meta charset="UTF-8">

        <title>Attendance History</title>

        <style>

            body{
                font-family:Arial,sans-serif;
                background:#f4f6f9;
                margin:20px;
            }

            .container{
                max-width:1100px;
                margin:auto;
            }

            .card{
                background:white;
                padding:25px;
                border-radius:10px;
                box-shadow:0 2px 8px rgba(0,0,0,.1);
            }

            h2{
                margin-bottom:20px;
            }

            table{
                width:100%;
                border-collapse:collapse;
                margin-top:20px;
            }

            th,td{
                border:1px solid #ddd;
                padding:12px;
                text-align:left;
            }

            th{
                background:#2563eb;
                color:white;
            }

            .btn{
                display:inline-block;
                padding:10px 15px;
                border-radius:5px;
                text-decoration:none;
                color:white;
            }

            .back{
                background:#6b7280;
            }

            .present{
                color:green;
                font-weight:bold;
            }

            .absent{
                color:red;
                font-weight:bold;
            }

        </style>

    </head>

    <body>

        <div class="container">

            <div class="card">

                <h2>

                    Attendance History - Member ID: ${memberId}

                </h2>

                <%
                    uef.edu.vn.model.User user
                            = (uef.edu.vn.model.User) request.getAttribute("loggedUser");

                    String backUrl
                            = user.getRole() == uef.edu.vn.model.Role.MEMBER
                            ? "/dashboard"
                            : "/subscriptions";
                %>

                <a class="btn back"
                   href="${pageContext.request.contextPath}<%= backUrl%>">
                    Back To Attendance
                </a>

                <table>

                    <tr>

                        <th>ID</th>

                        <th>Member ID</th>

                        <th>Date</th>

                        <th>Check In Time</th>

                        <th>Status</th>

                    </tr>

                    <c:forEach items="${attendances}"
                               var="attendance">

                        <tr>

                            <td>

                                ${attendance.id}

                            </td>

                            <td>

                                ${attendance.memberId}

                            </td>

                            <td>

                                ${attendance.date}

                            </td>

                            <td>

                                ${attendance.checkInTime}

                            </td>

                            <td>

                                <c:choose>

                                    <c:when test="${attendance.status == 'PRESENT'}">

                                        <span class="present">

                                            ${attendance.status}

                                        </span>

                                    </c:when>

                                    <c:otherwise>

                                        <span class="absent">

                                            ${attendance.status}

                                        </span>

                                    </c:otherwise>

                                </c:choose>

                            </td>

                        </tr>

                    </c:forEach>

                </table>

            </div>

        </div>

    </body>

</html>

