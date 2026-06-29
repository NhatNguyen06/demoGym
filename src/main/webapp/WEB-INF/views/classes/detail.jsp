<%-- 
    Document   : detail
    Created on : Jun 3, 2026, 8:16:06 PM
    Author     : minhq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Class Detail</title>

        <style>

            body{
                background:#f4f6f9;
                font-family:Arial;
            }

            .card{
                width:900px;
                margin:30px auto;
                background:white;
                padding:30px;
                border-radius:10px;
            }

            table{
                width:100%;
                border-collapse:collapse;
            }

            th,td{
                border:1px solid #ddd;
                padding:10px;
            }

            th{
                background:#2563eb;
                color:white;
            }

        </style>

    </head>

    <body>

        <div class="card">

            <h2>Class Detail</h2>

            <p><b>ID:</b> ${gymClass.id}</p>
            <p><b>Name:</b> ${gymClass.name}</p>
            <p><b>Trainer ID:</b> ${gymClass.trainerId}</p>
            <p><b>Capacity:</b> ${gymClass.capacity}</p>
            <p><b>Type:</b> ${gymClass.type}</p>

            <hr>

            <h3>Schedules</h3>

            <table>

                <tr>
                    <th>ID</th>
                    <th>Day</th>
                    <th>Start</th>
                    <th>End</th>
                    <th>Room</th>
                </tr>

                <c:forEach items="${schedules}" var="schedule">

                    <tr>

                        <td>${schedule.id}</td>
                        <td>${schedule.dayOfWeek}</td>
                        <td>${schedule.startTime}</td>
                        <td>${schedule.endTime}</td>
                        <td>${schedule.room}</td>

                    </tr>

                </c:forEach>

            </table>

        </div>

    </body>
</html>