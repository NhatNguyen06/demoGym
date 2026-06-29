<%-- 
    Document   : index
    Created on : Jun 2, 2026, 9:46:48 PM
    Author     : minhq
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Gym Admin Dashboard</title>

        <!-- Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Icons -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <style>
            body {
                margin: 0;
                background: #0b1220;
                color: #e5e7eb;
            }

            /* SIDEBAR */
            .sidebar {
                width: 260px;
                height: 100vh;
                position: fixed;
                background: #0f172a;
                border-right: 1px solid rgba(255,255,255,0.05);
                padding: 20px;
            }

            .logo {
                font-size: 20px;
                font-weight: 700;
                color: #22c55e;
                margin-bottom: 30px;
                display: flex;
                align-items: center;
                gap: 10px;
            }

            .menu a {
                display: flex;
                align-items: center;
                gap: 10px;
                padding: 10px 12px;
                border-radius: 10px;
                color: #cbd5e1;
                text-decoration: none;
                margin-bottom: 8px;
                transition: 0.2s;
            }

            .menu a:hover {
                background: rgba(34,197,94,0.1);
                color: #22c55e;
            }

            .menu a.active {
                background: rgba(34,197,94,0.15);
                color: #22c55e;
            }

            /* MAIN */
            .main {
                margin-left: 260px;
                padding: 20px;
            }

            /* TOPBAR */
            .topbar {
                display: flex;
                justify-content: space-between;
                align-items: center;
                padding: 15px 20px;
                background: #111827;
                border-radius: 14px;
                margin-bottom: 20px;
            }

            .badge-online {
                background: rgba(34,197,94,0.15);
                color: #22c55e;
                padding: 5px 10px;
                border-radius: 20px;
                font-size: 12px;
            }

            /* CARDS */
            .card-box {
                background: #111827;
                border: 1px solid rgba(255,255,255,0.05);
                border-radius: 14px;
                padding: 18px;
                transition: 0.2s;
            }

            .card-box:hover {
                transform: translateY(-4px);
            }

            .stat {
                font-size: 26px;
                font-weight: 700;
            }

            .muted {
                color: #94a3b8;
            }

            /* QUICK ACTION */
            .action {
                background: #111827;
                border-radius: 12px;
                padding: 12px;
                display: flex;
                gap: 10px;
                align-items: center;
                text-decoration: none;
                color: white;
                border: 1px solid rgba(255,255,255,0.05);
                transition: 0.2s;
            }

            .action:hover {
                background: #1f2937;
            }

            .icon {
                width: 40px;
                height: 40px;
                display: flex;
                align-items: center;
                justify-content: center;
                background: rgba(34,197,94,0.15);
                color: #22c55e;
                border-radius: 10px;
            }
            .chart-card {
                background: #111827;
                border: 1px solid rgba(255,255,255,0.05);
                border-radius: 14px;
                padding: 20px;
                height: 100%;
            }

            .chart-title {
                color: #cbd5e1;
                font-weight: 600;
                margin-bottom: 15px;
            }

            canvas {
                max-height: 350px;
            }
        </style>
    </head>

    <body>

        <!-- SIDEBAR -->
        <div class="sidebar">
            <div class="logo">
                <i class="bi bi-lightning-charge-fill"></i>
                TheMiN GYM
            </div>

            <div class="menu">
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
                <c:if test="${loggedUser.role.name() == 'MEMBER'}">

                    <a href="${pageContext.request.contextPath}/subscriptions/detail/${loggedUser.id}">
                        My Subscription
                    </a>

                    <a href="${pageContext.request.contextPath}/attendance/member/${loggedUser.id}">
                        My Attendance
                    </a>

                    <a href="${pageContext.request.contextPath}/payments/detail/${loggedUser.id}">
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
        </div>

        <!-- MAIN CONTENT -->
        <div class="main">

            <!-- TOPBAR -->
            <div class="topbar">
                <div>
                    <h5 class="mb-0">Dashboard Overview</h5>
                    <small class="muted">Welcome back, Admin 👋</small>
                </div>

                <div class="badge-online">
                    ● System Online
                </div>
            </div>

            <!-- STATS -->
            <div class="row g-3">

                <div class="col-md-3">
                    <div class="card-box">
                        <div class="muted">Members</div>
                        <div class="stat text-success">${totalMembers}</div>
                    </div>
                </div>

                <div class="col-md-3">
                    <div class="card-box">
                        <div class="muted">Trainers</div>
                        <div class="stat text-info">${totalTrainers}</div>
                    </div>
                </div>

                <div class="col-md-3">
                    <div class="card-box">
                        <div class="muted">Active Subs</div>
                        <div class="stat text-warning">${activeSubscriptions}</div>
                    </div>
                </div>

                <div class="col-md-3">
                    <div class="card-box">
                        <div class="muted">Expired Subs</div>
                        <div class="stat text-danger">${expiredSubscriptions}</div>
                    </div>
                </div>

                <div class="col-md-3">
                    <div class="card-box">
                        <div class="muted">Plans</div>
                        <div class="stat">${totalPlans}</div>
                    </div>
                </div>

                <div class="col-md-3">
                    <div class="card-box">
                        <div class="muted">Revenue</div>
                        <div class="stat text-success">$${totalRevenue}</div>
                    </div>
                </div>

            </div>

            <c:if test="${loggedUser != null && loggedUser.role == 'ADMIN'}">

                <!-- CHARTS -->
                <div class="row g-3 mt-3">

                    <div class="col-lg-6">
                        <div class="chart-card">
                            <div class="chart-title">Membership Status</div>
                            <canvas id="subscriptionChart"></canvas>
                        </div>
                    </div>

                    <div class="col-lg-6">
                        <div class="chart-card">
                            <div class="chart-title">Members vs Trainers</div>
                            <canvas id="memberTrainerChart"></canvas>
                        </div>
                    </div>

                    <div class="col-lg-6">
                        <div class="chart-card">
                            <div class="chart-title">Subscription Overview</div>
                            <canvas id="subscriptionOverviewChart"></canvas>
                        </div>
                    </div>

                    <div class="col-lg-6">
                        <div class="chart-card">
                            <div class="chart-title">Equipment Status</div>
                            <canvas id="equipmentChart"></canvas>
                        </div>
                    </div>

                </div>

            </c:if>

            <!-- QUICK ACTIONS -->

        </div>
        <script>
            window.onload = function () {

                Chart.defaults.color = "#cbd5e1";

                // Membership Status
                new Chart(
                        document.getElementById('subscriptionChart'),
                        {
                            type: 'doughnut',
                            data: {
                                labels: [
                                    'Active',
                                    'Expired'
                                ],
                                datasets: [{
                                        data: [
            ${activeSubscriptions},
            ${expiredSubscriptions}
                                        ],
                                        backgroundColor: [
                                            '#22c55e',
                                            '#ef4444'
                                        ]
                                    }]
                            }
                        }
                );

                // Members vs Trainers
                new Chart(
                        document.getElementById('memberTrainerChart'),
                        {
                            type: 'bar',
                            data: {
                                labels: [
                                    'Members',
                                    'Trainers'
                                ],
                                datasets: [{
                                        label: 'Total',
                                        data: [
            ${totalMembers},
            ${totalTrainers}
                                        ],
                                        backgroundColor: [
                                            '#3b82f6',
                                            '#06b6d4'
                                        ]
                                    }]
                            },
                            options: {
                                responsive: true
                            }
                        }
                );

                // Equipment Status
                new Chart(
                        document.getElementById('equipmentChart'),
                        {
                            type: 'doughnut',
                            data: {
                                labels: [
                                    'Available',
                                    'Maintenance',
                                    'Broken'
                                ],
                                datasets: [{
                                        data: [
            ${availableEquipments},
            ${maintenanceEquipments},
            ${brokenEquipments}
                                        ],
                                        backgroundColor: [
                                            '#22c55e',
                                            '#f59e0b',
                                            '#ef4444'
                                        ],
                                        borderWidth: 2
                                    }]
                            },
                            options: {
                                responsive: true,
                                plugins: {
                                    legend: {
                                        position: 'bottom',
                                        labels: {
                                            color: '#cbd5e1'
                                        }
                                    }
                                }
                            }
                        }
                );

                // Subscription Overview
                new Chart(
                        document.getElementById('subscriptionOverviewChart'),
                        {
                            type: 'bar',
                            data: {
                                labels: [
                                    'Total',
                                    'Active',
                                    'Expired'
                                ],
                                datasets: [{
                                        label: 'Subscriptions',
                                        data: [
            ${totalSubscriptions},
            ${activeSubscriptions},
            ${expiredSubscriptions}
                                        ],
                                        backgroundColor: [
                                            '#6366f1',
                                            '#22c55e',
                                            '#ef4444'
                                        ]
                                    }]
                            }
                        }
                );

            };
        </script>            
    </body>
</html>
