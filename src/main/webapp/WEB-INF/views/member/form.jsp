<%-- 
    Document   : form
    Created on : Jun 3, 2026, 8:00:07 PM
    Author     : minhq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

    <head>

        <meta charset="UTF-8">

        <title>Member Form</title>

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
                width:250px;
                height:100vh;
                background:#1e293b;
                position:fixed;
                color:white;
                padding:20px;
            }

            .sidebar h2{
                margin-bottom:30px;
                text-align:center;
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
                background:#334155;
            }

            .main-content{
                margin-left:250px;
            }

            .navbar{
                background:white;
                padding:15px 25px;
                box-shadow:0 2px 5px rgba(0,0,0,.1);
            }

            .container{
                padding:30px;
            }

            .card{
                background:white;
                border-radius:10px;
                padding:25px;
                box-shadow:0 2px 5px rgba(0,0,0,.1);
            }

            .form-group{
                margin-bottom:15px;
            }

            label{
                display:block;
                margin-bottom:5px;
                font-weight:bold;
            }

            input,
            select{
                width:100%;
                padding:10px;
                border:1px solid #ccc;
                border-radius:6px;
            }

            .btn{
                padding:10px 16px;
                border:none;
                border-radius:6px;
                cursor:pointer;
                color:white;
                text-decoration:none;
            }

            .btn-primary{
                background:#2563eb;
            }

            .btn-secondary{
                background:#6b7280;
            }

            .error{
                color:red;
                font-size:13px;
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

            <a href="${pageContext.request.contextPath}/subscriptions">
                Subscriptions
            </a>

            <a href="${pageContext.request.contextPath}/payments">
                Payments
            </a>

        </div>

        <div class="main-content">

            <div class="navbar">

                <h2>

                    <c:choose>

                        <c:when test="${member.id > 0}">
                            Edit Member
                        </c:when>

                        <c:otherwise>
                            Add Member
                        </c:otherwise>

                    </c:choose>

                </h2>

            </div>

            <div class="container">

                <div class="card">

                    <c:choose>

                        <c:when test="${member.id > 0}">
                            <c:set var="actionUrl"
                                   value="${pageContext.request.contextPath}/members/edit"/>
                        </c:when>

                        <c:otherwise>
                            <c:set var="actionUrl"
                                   value="${pageContext.request.contextPath}/members/add"/>
                        </c:otherwise>

                    </c:choose>

                    <form:form method="post"
                               action="${actionUrl}"
                               modelAttribute="member">

                        <form:hidden path="id"/>

                        <div class="form-group">

                            <label>Full Name</label>

                            <form:input path="fullName"/>

                            <form:errors path="fullName"
                                         cssClass="error"/>

                        </div>

                        <div class="form-group">

                            <label>Email</label>

                            <form:input path="email"/>

                            <form:errors path="email"
                                         cssClass="error"/>

                        </div>

                        <div class="form-group">

                            <label>Phone</label>

                            <form:input path="phone"/>

                            <form:errors path="phone"
                                         cssClass="error"/>

                        </div>

                        <div class="form-group">

                            <label>Date of birth</label>

                            <form:input path="dob"
                                        type="date"/>
                            <form:errors path="dob" cssClass="error"/>

                        </div>
                        <div class="form-group">

                            <label>Joined Date</label>

                            <form:input path="joinDate"
                                        type="date"/>
                            <form:errors path="joinDate" cssClass="error"/>
                            

                        </div>

                        <div class="form-group">

                            <label>Gender</label>

                            <form:select path="gender">

                                <form:option value="MALE">
                                    Male
                                </form:option>

                                <form:option value="FEMALE">
                                    Female
                                </form:option>

                            </form:select>

                        </div>

                        <div class="form-group">

                            <label>Status</label>

                            <form:select path="status">

                                <form:option value="ACTIVE">
                                    Active
                                </form:option>

                                <form:option value="INACTIVE">
                                    Inactive
                                </form:option>

                                <form:option value="SUSPENDED">
                                    Suspended
                                </form:option>

                            </form:select>

                        </div>

                        <button class="btn btn-primary"
                                type="submit">

                            Save

                        </button>

                        <a class="btn btn-secondary"
                           href="${pageContext.request.contextPath}/members">

                            Back

                        </a>

                    </form:form>

                </div>

            </div>

        </div>

    </body>
</html>
