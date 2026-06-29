<%-- 
    Document   : detail
    Created on : Jun 3, 2026, 8:26:34 PM
    Author     : minhq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Equipment Detail</title>
    </head>

    <body>

        <h1>Equipment Detail</h1>

        <table border="1" cellpadding="10">

            <tr>
                <th>ID</th>
                <td>${equipment.id}</td>
            </tr>

            <tr>
                <th>Name</th>
                <td>${equipment.name}</td>
            </tr>

            <tr>
                <th>Quantity</th>
                <td>${equipment.quantity}</td>
            </tr>

            <tr>
                <th>Status</th>
                <td>${equipment.status}</td>
            </tr>

            <tr>

                <th>Purchase Date</th>

                <td>${equipment.purchaseDate}</td>

            </tr>

        </table>

        <br>

        <a href="${pageContext.request.contextPath}/equipment/edit/${equipment.id}">
            Edit
        </a>

        |

        <a href="${pageContext.request.contextPath}/equipment">
            Back To List
        </a>

    </body>
</html>