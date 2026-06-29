<%-- 
    Document   : form
    Created on : Jun 3, 2026, 8:12:57 PM
    Author     : minhq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>

        <meta charset="UTF-8">
        <title>Edit Subscription</title>

        <style>

            body{
                background:#f4f6f9;
                font-family:Arial;
            }

            .card{
                width:800px;
                margin:30px auto;
                background:white;
                padding:30px;
                border-radius:10px;
            }

            .form-group{
                margin-bottom:15px;
            }

            label{
                display:block;
                margin-bottom:5px;
            }

            input,select{
                width:100%;
                padding:10px;
            }

            .error{
                color:red;
            }

            .btn{
                background:#2563eb;
                color:white;
                padding:10px 20px;
                border:none;
            }

        </style>

    </head>

    <body>

        <div class="card">

            <h2>Edit Subscription</h2>

            <form:form method="post"
                       action="${pageContext.request.contextPath}/subscriptions/edit"
                       modelAttribute="subscription">

                <form:hidden path="id"/>

                <div class="form-group">

                    <label>Member ID</label>

                    <form:input path="memberId"/>

                </div>

                <div class="form-group">

                    <label>Plan</label>

                    <form:select path="planId">

                        <c:forEach items="${plans}" var="plan">

                            <form:option value="${plan.id}">
                                ${plan.name}
                            </form:option>

                        </c:forEach>

                    </form:select>

                </div>
                <div class="form-group">

                    <label>Start Date</label>

                    <form:input path="startDate"
                                type="date"/>

                    <form:errors path="startDate"
                                 cssClass="error"/>

                </div>

                <div class="form-group">

                    <label>End Date</label>

                    <form:input path="endDate"
                                type="date"/>

                    <form:errors path="endDate"
                                 cssClass="error"/>

                </div>

                <div class="form-group">

                    <label>Status</label>

                    <form:select path="status">

                        <form:option value="ACTIVE">ACTIVE</form:option>
                        <form:option value="EXPIRED">EXPIRED</form:option>
                        <form:option value="CANCELLED">CANCELLED</form:option>

                    </form:select>

                </div>

                <button class="btn"
                        type="submit">

                    Save

                </button>

            </form:form>

        </div>

    </body>
</html>