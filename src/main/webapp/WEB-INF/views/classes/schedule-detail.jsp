<%-- 
    Document   : schedule-detail
    Created on : Jun 3, 2026, 8:16:51 PM
    Author     : minhq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Schedule Detail</title>
    </head>

    <body>

        <h2>Schedule Detail</h2>

        <p>ID: ${schedule.id}</p>

        <p>Class ID: ${schedule.classId}</p>

        <p>Day: ${schedule.dayOfWeek}</p>

        <p>Start Time: ${schedule.startTime}</p>

        <p>End Time: ${schedule.endTime}</p>

        <p>Room: ${schedule.room}</p>

    </body>
</html>