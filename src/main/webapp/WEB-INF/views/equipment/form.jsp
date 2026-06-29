<%-- 
    Document   : form
    Created on : Jun 3, 2026, 8:26:50 PM
    Author     : minhq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Equipment Form</title>
    </head>

    <body>

        <h1>Equipment Form</h1>

        <c:choose>

            <c:when test="${equipment.id == 0}">
                <c:set var="actionUrl"
                       value="/equipment/add"/>
            </c:when>

            <c:otherwise>
                <c:set var="actionUrl"
                       value="/equipment/edit"/>
            </c:otherwise>

        </c:choose>

        <form:form method="post"
                   modelAttribute="equipment"
                   action="${pageContext.request.contextPath}${actionUrl}">

            <form:hidden path="id"/>

            <p>

                Name

                <br>

                <form:input path="name"/>

                <form:errors path="name"/>

            </p>

            <p>

                Quantity

                <br>

                <form:input path="quantity"/>

                <form:errors path="quantity"/>

            </p>

            <p>

                Status

                <br>

                <form:select path="status">

                    <form:options items="${statuses}"/>

                </form:select>

                <form:errors path="status"/>
            <p>

                Purchase Date

                <br>

                <form:input path="purchaseDate"
                            type="date"/>

                <form:errors path="purchaseDate"/>

            </p>

        </p>

        <button type="submit">

            Save

        </button>

        <a href="${pageContext.request.contextPath}/equipment">

            Back

        </a>

    </form:form>

</body>
</html>