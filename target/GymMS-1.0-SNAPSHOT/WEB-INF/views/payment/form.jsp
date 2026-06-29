<%-- 
    Document   : form
    Created on : Jun 3, 2026, 8:24:02 PM
    Author     : minhq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form"
          uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Payment Form</title>
    </head>

    <body>

        <c:choose>

            <c:when test="${payment.id == 0}">
                <c:set var="actionUrl"
                       value="/payments/add"/>
            </c:when>

            <c:otherwise>
                <c:set var="actionUrl"
                       value="/payments/edit"/>
            </c:otherwise>

        </c:choose>

        <h2>Payment Form</h2>

        <form:form method="post"
                   modelAttribute="payment"
                   action="${pageContext.request.contextPath}${actionUrl}">

            <form:hidden path="id"/>

            <p>
                Member

                <form:select path="memberId">

            <option value="">
                -- Select Member --
            </option>

            <c:forEach items="${members}" var="member">

                <option value="${member.id}">
                    ${member.id} - ${member.fullName}
                </option>

            </c:forEach>

        </form:select>

        <form:errors
            path="memberId"
            cssClass="error"/>

    </p>

    <p>
        Amount
        <form:input path="amount"/>
        <form:errors path="amount" cssClass="error"/>
    </p>

    <p>
        Payment Method
        <form:input path="method"/>
        <form:errors path="method" cssClass="error"/>
    </p>

    <p>
        Status
        <form:select path="status">
            <form:options items="${statuses}"/>
        </form:select>
        <form:errors path="status" cssClass="error"/>
    </p>


    <button type="submit">
        Save
    </button>

</form:form>

</body>
</html>