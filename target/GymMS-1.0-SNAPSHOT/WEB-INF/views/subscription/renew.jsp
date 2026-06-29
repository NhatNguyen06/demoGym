<%-- 
    Document   : renew
    Created on : Jun 3, 2026, 8:13:17 PM
    Author     : minhq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>

        <meta charset="UTF-8">
        <title>Renew Subscription</title>

        <style>

            body{
                background:#f4f6f9;
                font-family:Arial;
            }

            .card{
                width:600px;
                margin:50px auto;
                background:white;
                padding:30px;
                border-radius:10px;
            }

            .form-group{
                margin-bottom:20px;
            }

            input{
                width:100%;
                padding:10px;
            }

            button{
                background:#16a34a;
                color:white;
                border:none;
                padding:10px 20px;
            }

        </style>

    </head>

    <body>

        <div class="card">

            <h2>Renew Subscription</h2>

            <p>
                Subscription ID:
                <strong>${subscription.id}</strong>
            </p>

            <p>
                Current End Date:
                <strong>${subscription.endDate}</strong>
            </p>

            <form method="post"
                  action="${pageContext.request.contextPath}/subscriptions/renew">

                <input type="hidden"
                       name="subscriptionId"
                       value="${subscription.id}">

                <div class="form-group">

                    <label>Extend Months</label>

                    <input type="number"
                           name="months"
                           min="1"
                           required>

                </div>

                <button type="submit">
                    Renew Subscription
                </button>

            </form>

        </div>

    </body>
</html>