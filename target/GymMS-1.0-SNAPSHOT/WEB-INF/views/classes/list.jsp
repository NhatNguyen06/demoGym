<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Gym Classes</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
              rel="stylesheet">

        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

        <style>

            *{
                margin:0;
                padding:0;
                box-sizing:border-box;
            }

            body{
                background:#040d24;
                color:white;
                font-family:'Segoe UI',sans-serif;
            }

            .wrapper{
                display:flex;
                min-height:100vh;
            }

            /* Sidebar */

            .sidebar{
                width:260px;
                background:#071633;
                padding:25px;
                border-right:1px solid rgba(255,255,255,.08);
            }

            .logo{
                color:#22c55e;
                font-size:30px;
                margin-bottom:40px;
                font-weight:bold;
            }

            .sidebar a{
                display:block;
                color:white;
                text-decoration:none;
                padding:14px 15px;
                border-radius:12px;
                margin-bottom:8px;
                transition:.3s;
            }

            .sidebar a:hover{
                background:#0f254d;
            }

            .sidebar a.active{
                background:#0f254d;
                color:#22c55e;
            }

            /* Content */

            .content{
                flex:1;
                padding:30px;
            }

            .header-card{
                background:#071633;
                border-radius:20px;
                padding:25px;
                margin-bottom:25px;
            }

            .main-card{
                background:#071633;
                border-radius:20px;
                padding:25px;
            }

            .btn-green{
                background:#16a34a;
                color:white;
                border:none;
            }

            .btn-green:hover{
                background:#15803d;
                color:white;
            }

            .table-dark{
                --bs-table-bg:#071633;
                --bs-table-striped-bg:#0d2348;
                --bs-table-hover-bg:#0d2348;
                border-color:#1e3a5f;
            }

            .badge-type{
                background:#2563eb;
                padding:6px 10px;
                border-radius:8px;
                font-size:12px;
            }

            .action-btn{
                margin-right:5px;
            }

        </style>

    </head>

    <body>

        <div class="wrapper">

            <!-- Sidebar -->
            <div class="sidebar">

                <div class="logo">
                    ⚡ TheMiN GYM
                </div>

                                <c:if test="${loggedUser.role == 'ADMIN'}">

                    <a href="${pageContext.request.contextPath}/members">
                        Members
                    </a>

                    <a href="${pageContext.request.contextPath}/trainers">
                        Trainers
                    </a>

                    <a href="${pageContext.request.contextPath}/membership-plans">
                        Plans
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

                    <a href="${pageContext.request.contextPath}/attendance">
                        Attendance
                    </a>

                </c:if>
                <c:if test="${loggedUser.role == 'MANAGER'}">

                    <a href="${pageContext.request.contextPath}/trainers">
                        Trainers
                    </a>

                    <a href="${pageContext.request.contextPath}/membership-plans">
                        Plans
                    </a>

                    <a href="${pageContext.request.contextPath}/classes">
                        Classes
                    </a>

                    <a href="${pageContext.request.contextPath}/equipment">
                        Equipment
                    </a>

                </c:if>
                <c:if test="${loggedUser.role == 'RECEPTIONIST'}">

                    <a href="${pageContext.request.contextPath}/members">
                        Members
                    </a>

                    <a href="${pageContext.request.contextPath}/subscriptions">
                        Subscriptions
                    </a>

                    <a href="${pageContext.request.contextPath}/payments">
                        Payments
                    </a>

                    <a href="${pageContext.request.contextPath}/attendance">
                        Attendance
                    </a>

                </c:if>
                <c:if test="${loggedUser.role == 'TRAINER'}">

                    <a href="${pageContext.request.contextPath}/classes">
                        Classes
                    </a>

                </c:if>
                <c:if test="${loggedUser.role == 'MEMBER'}">

                    <a href="${pageContext.request.contextPath}/subscriptions">
                        My Subscription
                    </a>

                    <a href="${pageContext.request.contextPath}/attendance/history">
                        My Attendance
                    </a>

                    <a href="${pageContext.request.contextPath}/payments">
                        My Payments
                    </a>

                </c:if>
                <c:if test="${loggedUser == null}">
                    <a href="${pageContext.request.contextPath}/login">
                        Login
                    </a>
                </c:if>

                <c:if test="${loggedUser != null}">
                    <a href="${pageContext.request.contextPath}/logout">
                        Logout
                    </a>
                </c:if>

            </div>

            <!-- Content -->
            <div class="content">

                <div class="header-card">

                    <h2>Gym Classes</h2>

                    <p class="text-secondary mb-0">
                        Manage all fitness classes and schedules
                    </p>

                </div>

                <div class="main-card">

                    <div class="d-flex justify-content-between mb-4">

                        <div>

                            <a class="btn btn-green"
                               href="${pageContext.request.contextPath}/classes/add">

                                <i class="fa-solid fa-plus"></i>
                                Add Class

                            </a>

                            <a class="btn btn-primary"
                               href="${pageContext.request.contextPath}/classes/schedules">

                                <i class="fa-solid fa-calendar-days"></i>
                                View Schedules

                            </a>

                        </div>

                    </div>

                    <div class="table-responsive">

                        <table class="table table-dark table-hover align-middle">

                            <thead>

                                <tr>

                                    <th>ID</th>
                                    <th>Class Name</th>
                                    <th>Trainer ID</th>
                                    <th>Capacity</th>
                                    <th>Type</th>
                                    <th width="220">Actions</th>

                                </tr>

                            </thead>

                            <tbody>

                                <c:forEach items="${classes}" var="gymClass">

                                    <tr>

                                        <td>${gymClass.id}</td>

                                        <td>
                                            <strong>${gymClass.name}</strong>
                                        </td>

                                        <td>${gymClass.trainerId}</td>

                                        <td>

                                            <span class="badge bg-success">
                                                ${gymClass.capacity}
                                            </span>

                                        </td>

                                        <td>

                                            <span class="badge-type">
                                                ${gymClass.type}
                                            </span>

                                        </td>

                                        <td>

                                            <a class="btn btn-info btn-sm action-btn"
                                               href="${pageContext.request.contextPath}/classes/detail/${gymClass.id}">

                                                <i class="fa-solid fa-eye"></i>

                                            </a>

                                            <a class="btn btn-warning btn-sm action-btn"
                                               href="${pageContext.request.contextPath}/classes/edit/${gymClass.id}">

                                                <i class="fa-solid fa-pen"></i>

                                            </a>

                                            <a class="btn btn-danger btn-sm"
                                               href="${pageContext.request.contextPath}/classes/delete/${gymClass.id}"
                                               onclick="return confirm('Delete this class?')">

                                                <i class="fa-solid fa-trash"></i>

                                            </a>

                                        </td>

                                    </tr>

                                </c:forEach>

                            </tbody>

                        </table>

                    </div>

                </div>

            </div>

        </div>

    </body>
</html>