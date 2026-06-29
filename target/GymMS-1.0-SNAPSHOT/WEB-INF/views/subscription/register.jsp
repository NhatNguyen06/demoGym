<%-- 
    Document   : register
    Created on : Jun 3, 2026, 8:12:43 PM
    Author     : minhq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>

        <meta charset="UTF-8">
        <title>Register Subscription</title>

        <style>

            body{
                background:#f4f6f9;
                font-family:Arial;
            }

            .card{
                background:white;
                width:700px;
                margin:40px auto;
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

            .btn{
                background:#2563eb;
                color:white;
                border:none;
                padding:10px 20px;
            }

        </style>

    </head>

    <body>

        <div class="card">

            <h2>Register Subscription</h2>

            <form method="post"
                  action="${pageContext.request.contextPath}/subscriptions/register">

                <div class="form-group">

                    <label>Member</label>

                    <select name="memberId" required>

                        <option value="">
                            -- Select Member --
                        </option>

                        <c:forEach items="${members}"
                                   var="member">

                            <option value="${member.id}">

                                ${member.id}
                                - ${member.fullName}

                            </option>

                        </c:forEach>

                    </select>

                </div>
                <div class="form-group">

                    <label>Membership Plan</label>

                    <select name="planId">

                        <c:forEach items="${plans}" var="plan">

                            <option value="${plan.id}">
                                ${plan.name}
                                (${plan.durationMonths} months)
                            </option>

                        </c:forEach>

                    </select>

                </div>

                <button type="submit"
                        class="btn">

                    Register

                </button>

            </form>

        </div>

    </body>
</html>