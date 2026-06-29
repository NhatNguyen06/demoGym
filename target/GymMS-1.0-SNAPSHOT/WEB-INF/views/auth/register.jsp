<%-- 
    Document   : register
    Created on : Jun 10, 2026, 7:14:10 PM
    Author     : minhq
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>

<html>
    <head>

        <meta charset="UTF-8">

        <title>Register</title>

        <style>

            body{
                background:#f4f6f9;
                font-family:Arial,sans-serif;
            }

            .card{
                width:500px;
                margin:50px auto;
                background:white;
                padding:30px;
                border-radius:10px;
                box-shadow:0 2px 8px rgba(0,0,0,.15);
            }

            .form-group{
                margin-bottom:15px;
            }

            label{
                display:block;
                margin-bottom:5px;
                font-weight:bold;
            }

            input{
                width:100%;
                padding:10px;
                border:1px solid #ccc;
                border-radius:5px;
            }

            .btn{
                width:100%;
                padding:12px;
                background:#16a34a;
                color:white;
                border:none;
                border-radius:5px;
            }

        </style>

    </head>

    <body>

        <div class="card">

            <h2 align="center">

                Create Member Account

            </h2>

            <form:form method="post"
                       action="${pageContext.request.contextPath}/register"
                       modelAttribute="user">

                <div class="form-group">

                    <label>Username</label>

                    <form:input path="username"/>

                </div>

                <div class="form-group">

                    <label>Password</label>

                    <form:password path="password"/>

                </div>

                <button class="btn"
                        type="submit">

                    Register

                </button>

            </form:form>

        </div>

    </body>
</html>

