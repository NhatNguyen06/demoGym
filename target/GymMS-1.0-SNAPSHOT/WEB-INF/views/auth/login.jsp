<%-- 
    Document   : login
    Created on : Jun 10, 2026, 7:14:00 PM
    Author     : minhq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>

<html>
    <head>

        <meta charset="UTF-8">

        <title>Login</title>

        <style>

            body{
                background:#f4f6f9;
                font-family:Arial,sans-serif;
            }

            .card{
                width:450px;
                margin:80px auto;
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
                border:none;
                background:#2563eb;
                color:white;
                border-radius:5px;
                cursor:pointer;
            }

            .error{
                color:red;
                text-align:center;
                margin-bottom:10px;
            }

            .link{
                text-align:center;
                margin-top:15px;
            }

        </style>

    </head>

    <body>

        <div class="card">

            <h2 align="center">Gym Management Login</h2>

            <p class="error">
                ${error}
            </p>

            <form:form method="post"
                       action="${pageContext.request.contextPath}/login"
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

                    Login

                </button>

            </form:form>

            <div class="link">

                <a href="${pageContext.request.contextPath}/register">
                    Register New Account
                </a>

            </div>

        </div>

    </body>
</html>

