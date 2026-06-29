<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Equipment Management</title>

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
                color:#1cff7a;
                font-size:30px;
                margin-bottom:40px;
            }

            .logo span{
                font-size:28px;
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
                color:#1cff7a;
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

            .header-card h2{
                margin-bottom:5px;
            }

            .main-card{
                background:#071633;
                border-radius:20px;
                padding:25px;
            }

            .btn-green{
                background:#00d26a;
                color:white;
                border:none;
            }

            .btn-green:hover{
                background:#00b75c;
                color:white;
            }

            .btn-outline-light{
                border-radius:10px;
            }

            .form-control,
            .form-select{
                background:#0b2147;
                border:none;
                color:white;
            }

            .form-control:focus,
            .form-select:focus{
                background:#0b2147;
                color:white;
                box-shadow:none;
            }

            .table{
                color:white;
                margin-top:20px;
            }

            .table thead{
                background:#0d2348;
            }

            .table tbody tr:hover{
                background:#0d2348;
            }

            .badge-available{
                background:#198754;
            }

            .badge-maintenance{
                background:#ffc107;
                color:black;
            }

            .badge-broken{
                background:#dc3545;
            }

            .action-btn{
                text-decoration:none;
                margin-right:8px;
            }
        </style>
    </head>

    <body>

        <div class="wrapper">

            <!-- SIDEBAR -->
            <div class="sidebar">

                <div class="logo">
                    ⚡ <span>TheMiN GYM</span>
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

            <!-- CONTENT -->
            <div class="content">

                <div class="header-card">
                    <h2>Equipment Management</h2>
                    <p class="text-secondary mb-0">
                        Manage all gym equipment and inventory
                    </p>
                </div>

                <div class="main-card">

                    <!-- Top Actions -->
                    <div class="d-flex justify-content-between mb-4">

                        <div>
                            <a href="${pageContext.request.contextPath}/equipment/add"
                               class="btn btn-green">
                                <i class="fa-solid fa-plus"></i>
                                Add Equipment
                            </a>

                            <a href="${pageContext.request.contextPath}/equipment/low-stock"
                               class="btn btn-outline-warning">
                                Low Stock
                            </a>
                        </div>

                    </div>

                    <!-- Search + Filter -->
                    <div class="row mb-4">

                        <div class="col-md-6">
                            <form action="${pageContext.request.contextPath}/equipment/search"
                                  method="get">

                                <div class="input-group">

                                    <input type="text"
                                           class="form-control"
                                           name="keyword"
                                           value="${keyword}"
                                           placeholder="Search equipment...">

                                    <button class="btn btn-green">
                                        Search
                                    </button>

                                </div>

                            </form>
                        </div>

                        <div class="col-md-4">

                            <form action="${pageContext.request.contextPath}/equipment/status"
                                  method="get">

                                <div class="input-group">

                                    <select name="status"
                                            class="form-select">

                                        <option value="AVAILABLE">
                                            AVAILABLE
                                        </option>

                                        <option value="MAINTENANCE">
                                            MAINTENANCE
                                        </option>

                                        <option value="BROKEN">
                                            BROKEN
                                        </option>

                                    </select>

                                    <button class="btn btn-outline-light">
                                        Filter
                                    </button>

                                </div>

                            </form>

                        </div>

                    </div>

                    <!-- TABLE -->
                    <div class="table-responsive">

                        <table class="table table-dark table-hover align-middle">

                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Quantity</th>
                                    <th>Status</th>
                                    <th>Purchase Date</th>
                                    <th width="220">Actions</th>
                                </tr>
                            </thead>

                            <tbody>

                                <c:forEach items="${equipments}" var="equipment">

                                    <tr>

                                        <td>${equipment.id}</td>

                                        <td>
                                            <strong>${equipment.name}</strong>
                                        </td>

                                        <td>${equipment.quantity}</td>

                                        <td>

                                            <c:choose>

                                                <c:when test="${equipment.status == 'AVAILABLE'}">
                                                    <span class="badge badge-available">
                                                        AVAILABLE
                                                    </span>
                                                </c:when>

                                                <c:when test="${equipment.status == 'MAINTENANCE'}">
                                                    <span class="badge badge-maintenance">
                                                        MAINTENANCE
                                                    </span>
                                                </c:when>

                                                <c:otherwise>
                                                    <span class="badge badge-broken">
                                                        BROKEN
                                                    </span>
                                                </c:otherwise>

                                            </c:choose>

                                        </td>

                                        <td>${equipment.purchaseDate}</td>

                                        <td>

                                            <a class="btn btn-sm btn-info"
                                               href="${pageContext.request.contextPath}/equipment/detail/${equipment.id}">
                                                <i class="fa-solid fa-eye"></i>
                                            </a>

                                            <a class="btn btn-sm btn-warning"
                                               href="${pageContext.request.contextPath}/equipment/edit/${equipment.id}">
                                                <i class="fa-solid fa-pen"></i>
                                            </a>

                                            <a class="btn btn-sm btn-danger"
                                               href="${pageContext.request.contextPath}/equipment/delete/${equipment.id}"
                                               onclick="return confirm('Delete this equipment?')">
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