<%-- 
    Document   : list
    Created on : Jun 10, 2026, 7:14:25 PM
    Author     : minhq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>
    <head>

        <meta charset="UTF-8">

        <title>Attendance List</title>

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
            }

            th{
                background:#2563eb;
                color:white;
            }

            .btn{
                background:#16a34a;
                color:white;
                text-decoration:none;
                padding:6px 10px;
                border-radius:5px;
            }

            .present{
                color:green;
                font-weight:bold;
            }

        </style>

    </head>

    <body>

        <h2>

            Attendance Management

        </h2>

        <a class="btn"
           href="${pageContext.request.contextPath}/dashboard">

            Back Dashboard

        </a>

        <a href="${pageContext.request.contextPath}/attendance/checkin">
            Check In
        </a>
        <br><br>

        <table>

            <tr>

            <tr>
                <th>ID</th>
                <th>Member ID</th>
                <th>Date</th>
                <th>Check In Time</th>
                <th>Status</th>
                <th>Action</th>
            </tr>

        </tr>

        <c:forEach items="${attendances}"
                   var="attendance">

            <tr>

                <td>${attendance.id}</td>

                <td>${attendance.memberId}</td>

                <td>${attendance.date}</td>

                <td>${attendance.checkInTime}</td>

                <td>

                    <span class="present">

                        ${attendance.status}

                    </span>

                </td>
                <td>

                    <a class="btn"
                       href="${pageContext.request.contextPath}/attendance/member/${attendance.memberId}">
                        History
                    </a>

                </td>

            </tr>

        </c:forEach>

    </table>

</body>
</html>

