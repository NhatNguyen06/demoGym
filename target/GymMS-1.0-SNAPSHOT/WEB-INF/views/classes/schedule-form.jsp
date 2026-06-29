<%-- 
    Document   : schedule-form
    Created on : Jun 3, 2026, 8:17:22 PM
    Author     : minhq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Schedule Form</title>
    </head>

    <body>

        <c:choose>

            <c:when test="${schedule.id == 0}">
                <c:set var="actionUrl"
                       value="/classes/schedule/add"/>
            </c:when>

            <c:otherwise>
                <c:set var="actionUrl"
                       value="/classes/schedule/edit"/>
            </c:otherwise>

        </c:choose>

        <form:form method="post"
                   modelAttribute="schedule"
                   action="${pageContext.request.contextPath}${actionUrl}">

            <form:hidden path="id"/>

            <p>

                Class

                <form:select path="classId">

                    <c:forEach items="${classes}" var="gymClass">

                        <form:option value="${gymClass.id}">
                            ${gymClass.name}
                        </form:option>

                    </c:forEach>

                </form:select>

            </p>

            <p>

                Day

                <form:select path="dayOfWeek">

                    <form:option value="MONDAY">Monday</form:option>
                    <form:option value="TUESDAY">Tuesday</form:option>
                    <form:option value="WEDNESDAY">Wednesday</form:option>
                    <form:option value="THURSDAY">Thursday</form:option>
                    <form:option value="FRIDAY">Friday</form:option>
                    <form:option value="SATURDAY">Saturday</form:option>
                    <form:option value="SUNDAY">Sunday</form:option>

                </form:select>

            </p>

            <p>

                Start Time

                <form:input path="startTime"/>

            </p>

            <p>

                End Time

                <form:input path="endTime"/>

            </p>

            <p>

                Room

                <form:input path="room"/>

            </p>

            <button type="submit">
                Save
            </button>

        </form:form>

    </body>
</html>
