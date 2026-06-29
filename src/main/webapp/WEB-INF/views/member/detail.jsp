<%-- 
    Document   : detail
    Created on : Jun 3, 2026, 8:39:36 PM
    Author     : minhq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

    <head>

        <meta charset="UTF-8">

        <title>Member Detail</title>

        <style>

            *{
                margin:0;
                padding:0;
                box-sizing:border-box;
                font-family:Arial,sans-serif;
            }

            body{
                background:#f4f6f9;
            }

            .sidebar{
                width:240px;
                height:100vh;
                background:#1f2937;
                position:fixed;
                left:0;
                top:0;
                color:white;
                padding:20px;
            }

            .sidebar h2{
                text-align:center;
                margin-bottom:30px;
            }

            .sidebar a{
                display:block;
                color:white;
                text-decoration:none;
                padding:12px;
                margin-bottom:10px;
                border-radius:6px;
            }

            .sidebar a:hover{
                background:#374151;
            }

            .main{
                margin-left:260px;
                padding:30px;
            }

            .navbar{
                background:white;
                padding:15px 25px;
                border-radius:10px;
                box-shadow:0 2px 8px rgba(0,0,0,0.1);
                margin-bottom:25px;
            }

            .card{
                background:white;
                padding:25px;
                border-radius:10px;
                box-shadow:0 2px 10px rgba(0,0,0,0.1);
            }

            .card h2{
                margin-bottom:20px;
                color:#333;
            }

            table{
                width:100%;
                border-collapse:collapse;
            }

            th{
                width:220px;
                text-align:left;
                background:#f8f9fa;
            }

            th,
            td{
                padding:12px;
                border:1px solid #ddd;
            }

            .btn{
                display:inline-block;
                padding:10px 18px;
                text-decoration:none;
                color:white;
                border-radius:5px;
                margin-right:8px;
            }

            .btn-warning{
                background:#ffc107;
                color:black;
            }

            .btn-primary{
                background:#0d6efd;
            }

            .btn-secondary{
                background:#6c757d;
            }

            .action{
                margin-top:20px;
            }

        </style>

    </head>

    <body>

        <div class="sidebar">

            <h2>GYM SYSTEM</h2>

            <a href="${pageContext.request.contextPath}/dashboard">
                Dashboard
            </a>

            <a href="${pageContext.request.contextPath}/members">
                Members
            </a>

            <a href="${pageContext.request.contextPath}/trainers">
                Trainers
            </a>

            <a href="${pageContext.request.contextPath}/plans">
                Membership Plans
            </a>

            <a href="${pageContext.request.contextPath}/subscriptions">
                Subscriptions
            </a>

            <a href="${pageContext.request.contextPath}/classes">
                Classes
            </a>

            <a href="${pageContext.request.contextPath}/payments">
                Payments
            </a>

            <a href="${pageContext.request.contextPath}/equipment">
                Equipment
            </a>

        </div>

        <div class="main">

            <div class="navbar">

                <h1>Member Detail</h1>

            </div>

            <div class="card">

                <h2>Member Information</h2>

                <table>

                    <tr>
                        <th>ID</th>
                        <td>${member.id}</td>
                    </tr>

                    <tr>
                        <th>Full Name</th>
                        <td>${member.fullName}</td>
                    </tr>

                    <tr>
                        <th>Email</th>
                        <td>${member.email}</td>
                    </tr>

                    <tr>
                        <th>Phone</th>
                        <td>${member.phone}</td>
                    </tr>

                    <tr>
                        <th>Gender</th>
                        <td>${member.gender}</td>
                    </tr>

                    <tr>
                        <th>Date of birth</th>
                        <td>${member.dob}</td>
                    </tr>
                    <tr>
                        <th>Joined Date</th>
                        <td>${member.joinDate}</td>
                    </tr>

                    <tr>
                        <th>Status</th>
                        <td>${member.status}</td>
                    </tr>

                </table>

                <div class="action">

                    <a href="${pageContext.request.contextPath}/members/edit/${member.id}"
                       class="btn btn-warning">
                        Edit
                    </a>

                    <a href="${pageContext.request.contextPath}/members"
                       class="btn btn-secondary">
                        Back To List
                    </a>

                </div>

            </div>

        </div>

    </body>

</html>
