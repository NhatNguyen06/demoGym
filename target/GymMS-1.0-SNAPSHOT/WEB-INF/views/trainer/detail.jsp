<%-- 
    Document   : detail
    Created on : Jun 3, 2026, 8:05:09 PM
    Author     : minhq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>

        <meta charset="UTF-8">

        <title>Trainer Detail</title>

        <style>

            body{
                font-family:Arial;
                background:#f4f6f9;
                padding:30px;
            }

            .card{
                max-width:700px;
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
                border-radius:6px;
            }

        </style>

    </head>

    <body>

        <div class="card">

            <h2>Trainer Detail</h2>

            <hr><br>

            <div class="row">
                <span class="label">ID:</span>
                ${trainer.id}
            </div>

            <div class="row">
                <span class="label">Full Name:</span>
                ${trainer.fullName}
            </div>

            <div class="row">
                <span class="label">Specialization:</span>
                ${trainer.specialization}
            </div>

            <div class="row">
                <span class="label">Phone:</span>
                ${trainer.phone}
            </div>

            <div class="row">
                <span class="label">Schedule:</span>
                ${trainer.schedule}
            </div>

            <a class="btn"
               href="${pageContext.request.contextPath}/trainers">

                Back To List

            </a>

        </div>

    </body>
</html>
