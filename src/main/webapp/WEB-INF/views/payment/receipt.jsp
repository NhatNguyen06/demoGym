<%-- 
    Document   : receipt
    Created on : Jun 3, 2026, 8:24:42 PM
    Author     : minhq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Payment Receipt</title>

        <style>

            body{
                font-family:Arial;
                margin:40px;
            }

            .receipt{
                width:600px;
                margin:auto;
                border:2px solid #333;
                padding:30px;
            }

            h1{
                text-align:center;
            }

            .row{
                margin-bottom:15px;
            }

        </style>

    </head>

    <body>

        <div class="receipt">

            <h1>GYM PAYMENT RECEIPT</h1>

            <hr>

            <div class="row">
                Receipt ID: ${payment.id}
            </div>

            <div class="row">
                Member ID: ${payment.memberId}
            </div>

            <div class="row">
                Amount: ${payment.amount}
            </div>


            <div class="row">
                Payment Method: ${payment.method}
            </div>

            <div class="row">
                Status: ${payment.status}
            </div>

            <hr>

            <h3 style="text-align:right">
                Thank You
            </h3>

        </div>

    </body>
</html>