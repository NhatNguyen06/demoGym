<%-- 
    Document   : 403
    Created on : Jun 10, 2026, 8:10:02 PM
    Author     : minhq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>

        <meta charset="UTF-8">

        <title>403 - Access Denied</title>

        <style>

            body{
                margin:0;
                padding:0;
                font-family:Arial,sans-serif;
                background:#f4f6f9;
                display:flex;
                justify-content:center;
                align-items:center;
                height:100vh;
            }

            .card{
                background:white;
                padding:40px;
                width:600px;
                text-align:center;
                border-radius:12px;
                box-shadow:0 4px 20px rgba(0,0,0,0.1);
            }

            h1{
                font-size:80px;
                margin:0;
                color:#ef4444;
            }

            h2{
                margin-top:10px;
                color:#374151;
            }

            p{
                color:#6b7280;
                margin:20px 0;
            }

            .btn{
                display:inline-block;
                padding:12px 24px;
                background:#2563eb;
                color:white;
                text-decoration:none;
                border-radius:6px;
                font-weight:bold;
            }

            .btn:hover{
                background:#1d4ed8;
            }

        </style>

    </head>

    <body>

        <div class="card">

            <h1>403</h1>

            <h2>Access Denied</h2>

            <p>

                You do not have permission to access this page.

            </p>

            <p>

                Please contact the administrator if you believe this is a mistake.

            </p>

            <a class="btn"
               href="${pageContext.request.contextPath}/dashboard">

                Back to Dashboard

            </a>

        </div>

    </body>
</html>