<%-- 
    Document   : detail
    Created on : Jun 3, 2026, 8:23:46 PM
    Author     : minhq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Payment Detail</title>
    </head>

    <body>

        <h2>Payment Detail</h2>

        <p><strong>ID:</strong> ${payment.id}</p>

        <p><strong>Member ID:</strong> ${payment.memberId}</p>

        <p><strong>Amount:</strong> ${payment.amount}</p>


        <p><strong>Method:</strong> ${payment.method}</p>

        <p><strong>Status:</strong> ${payment.status}</p>

        <%
            uef.edu.vn.model.User user
                    = (uef.edu.vn.model.User) request.getAttribute("loggedUser");

            String backUrl
                    = user.getRole() == uef.edu.vn.model.Role.MEMBER
                    ? "/dashboard"
                    : "/payments";
        %>

        <a 
           href="${pageContext.request.contextPath}<%= backUrl%>">
            Back
        </a>

    </body>
</html>