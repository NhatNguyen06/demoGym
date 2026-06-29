<%-- 
    Document   : detail
    Created on : Jun 3, 2026, 8:09:55 PM
    Author     : minhq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>

        <meta charset="UTF-8">

        <title>Membership Plan Detail</title>

        <style>

            body{
                background:#f4f6f9;
                font-family:Arial,sans-serif;
                padding:30px;
            }

            .card{
                max-width:800px;
                margin:auto;
                background:white;
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

            .description{
                background:#f8fafc;
                padding:15px;
                border-radius:6px;
                margin-top:10px;
            }

        </style>

    </head>

    <body>

        <div class="card">

            <h2>Membership Plan Detail</h2>

            <hr><br>

            <div class="row">
                <span class="label">ID:</span>
                ${plan.id}
            </div>

            <div class="row">
                <span class="label">Plan Name:</span>
                ${plan.name}
            </div>

            <div class="row">
                <span class="label">Duration:</span>
                ${plan.durationMonths} Months
            </div>

            <div class="row">
                <span class="label">Price:</span>
                $${plan.price}
            </div>

            <div class="row">

                <span class="label">Description:</span>

                <div class="description">
                    ${plan.description}
                </div>

            </div>

            <a href="${pageContext.request.contextPath}/membership-plans"
               class="btn">

                Back To List

            </a>

        </div>

    </body>
</html>