<%-- 
    Document   : checkin
    Created on : Jun 10, 2026, 10:30:53 PM
    Author     : minhq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h2>Member Check In</h2>

<form method="post"
      action="${pageContext.request.contextPath}/attendance/checkin">

    <label>Select Member</label>

    <select name="memberId">

        <c:forEach items="${members}" var="member">

            <option value="${member.id}">
                ${member.id} - ${member.username}
            </option>

        </c:forEach>

    </select>

    <br><br>

    <button type="submit">
        Check In
    </button>

</form>
