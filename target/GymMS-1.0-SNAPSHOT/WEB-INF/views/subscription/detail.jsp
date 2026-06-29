<%-- 
    Document   : detail
    Created on : Jun 3, 2026, 8:12:22 PM
    Author     : minhq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>

        <meta charset="UTF-8">
        <title>Subscription Detail</title>

        <style>

            body{
                background:#f4f6f9;
                font-family:Arial;
                padding:40px;
            }

            .card{
                background:white;
                max-width:800px;
                margin:auto;
                padding:30px;
                border-radius:10px;
            }

            .row{
                margin-bottom:15px;
            }

            .label{
                font-weight:bold;
            }

            .btn{
                display:inline-block;
                margin-top:20px;
                padding:10px 15px;
                background:#2563eb;
                color:white;
                text-decoration:none;
                border-radius:5px;
            }

        </style>

    </head>

    <body>

        <div class="card">

            <h2>Subscription Detail</h2>

            <hr><br>

            <div class="row">
                <span class="label">ID:</span>
                ${subscription.id}
            </div>

            <div class="row">
                <span class="label">Member ID:</span>
                ${subscription.memberId}
            </div>

            <div class="row">
                <span class="label">Plan ID:</span>
                ${subscription.planId}
            </div>

            <div class="row">
                <span class="label">Start Date:</span>
                ${subscription.startDate}
            </div>

            <div class="row">
                <span class="label">End Date:</span>
                ${subscription.endDate}
            </div>

            <div class="row">
                <span class="label">Status:</span>
                ${subscription.status}
            </div>

            <%
                uef.edu.vn.model.User user
                        = (uef.edu.vn.model.User) request.getAttribute("loggedUser");

                String backUrl
                        = user.getRole() == uef.edu.vn.model.Role.MEMBER
                        ? "/dashboard"
                        : "/subscriptions";
            %>

            <a class="btn"
               href="${pageContext.request.contextPath}<%= backUrl%>">
                Back
            </a>
        </div>

    </body>
</html>