<%-- 
    Document   : 404
    Created on : Jun 3, 2026, 8:33:38 PM
    Author     : minhq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

    <head>

        <meta charset="UTF-8">

        <title>404 Not Found</title>

        <style>

            *{
                margin:0;
                padding:0;
                box-sizing:border-box;
                font-family:Arial,sans-serif;
            }

            body{
                background:#f4f6f9;
                display:flex;
                justify-content:center;
                align-items:center;
                height:100vh;
            }

            .container{
                background:white;
                width:550px;
                text-align:center;
                padding:50px;
                border-radius:15px;
                box-shadow:0 5px 15px rgba(0,0,0,0.1);
            }

            .code{
                font-size:100px;
                font-weight:bold;
                color:#dc3545;
            }

            h2{
                margin-top:10px;
                margin-bottom:20px;
            }

            p{
                color:#666;
                margin-bottom:30px;
            }

            .btn{
                display:inline-block;
                text-decoration:none;
                padding:12px 25px;
                background:#198754;
                color:white;
                border-radius:8px;
            }

            .btn:hover{
                background:#157347;
            }

        </style>

    </head>

    <body>

        <div class="container">

            <div class="code">
                404
            </div>

            <h2>
                Page Not Found
            </h2>

            <p>

                <%= request.getAttribute("errorMessage") != null
                        ? request.getAttribute("errorMessage")
                        : "The requested resource does not exist."%>

            </p>

            <a class="btn"
               href="${pageContext.request.contextPath}/dashboard">

                Back To Dashboard

            </a>

        </div>

    </body>

</html>