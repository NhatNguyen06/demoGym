<%-- 
    Document   : form
    Created on : Jun 3, 2026, 8:16:18 PM
    Author     : minhq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Class Form</title>

        <style>

            body{
                background:#f4f6f9;
                font-family:Arial;
            }

            .card{
                width:700px;
                margin:40px auto;
                background:white;
                padding:30px;
                border-radius:10px;
            }

            .form-group{
                margin-bottom:15px;
            }

            input,select{
                width:100%;
                padding:10px;
            }

            .error{
                color:red;
            }

            button{
                background:#2563eb;
                color:white;
                border:none;
                padding:10px 20px;
            }

        </style>

    </head>

    <body>

        <div class="card">

            <h2>Class Form</h2>

            <c:set var="actionUrl"
                   value="${gymClass.id == 0 ?
                            '/classes/add' :
                            '/classes/edit'}"/>

            <form:form method="post"
                       modelAttribute="gymClass"
                       action="${pageContext.request.contextPath}${actionUrl}">

                <form:hidden path="id"/>

                <div class="form-group">

                    <label>Name</label>

                    <form:input path="name"/>

                    <form:errors path="name"
                                 cssClass="error"/>

                </div>

                <div class="form-group">

                    <label>Trainer</label>

                    <form:select path="trainerId">

                        <c:forEach items="${trainers}" var="trainer">

                            <form:option value="${trainer.id}">
                                ${trainer.fullName}
                            </form:option>

                        </c:forEach>

                    </form:select>

                </div>

                <div class="form-group">

                    <label>Capacity</label>

                    <form:input path="capacity"/>

                </div>

                <div class="form-group">

                    <label>Type</label>

                    <form:input path="type"/>

                </div>

                <button type="submit">
                    Save
                </button>

            </form:form>

        </div>

    </body>
</html>
