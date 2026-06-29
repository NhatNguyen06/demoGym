<%-- 
    Document   : form
    Created on : Jun 3, 2026, 8:04:55 PM
    Author     : minhq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
    <head>

        <meta charset="UTF-8">

        <title>${pageTitle}</title>

        <style>

            body{
                font-family:Arial;
                background:#f4f6f9;
                padding:30px;
            }

            .card{
                background:white;
                max-width:700px;
                margin:auto;
                padding:30px;
                border-radius:10px;
            }

            .form-group{
                margin-bottom:15px;
            }

            input{
                width:100%;
                padding:10px;
            }

            .btn{
                padding:10px 15px;
                border:none;
                color:white;
                cursor:pointer;
            }

            .save{
                background:#2563eb;
            }

            .back{
                background:#6b7280;
                text-decoration:none;
            }

            .error{
                color:red;
                font-size:13px;
            }

        </style>

    </head>

    <body>

        <div class="card">

            <h2>${pageTitle}</h2>

            <br>

            <form:form modelAttribute="trainer"
                       method="post"
                       action="${trainer.id == 0 ?
                                 pageContext.request.contextPath.concat('/trainers/add')
                                 :
                                 pageContext.request.contextPath.concat('/trainers/edit')}">

                <form:hidden path="id"/>

                <div class="form-group">

                    <label>Full Name</label>

                    <form:input path="fullName"/>

                    <form:errors path="fullName"
                                 cssClass="error"/>

                </div>

                <div class="form-group">

                    <label>Specialization</label>

                    <form:input path="specialization"/>

                    <form:errors path="specialization"
                                 cssClass="error"/>

                </div>

                <div class="form-group">

                    <label>Phone</label>

                    <form:input path="phone"/>

                    <form:errors path="phone"
                                 cssClass="error"/>

                </div>

                <div class="form-group">

                    <label>Schedule</label>

                    <form:input path="schedule"/>

                </div>

                <button type="submit"
                        class="btn save">

                    Save

                </button>

                <a class="btn back"
                   href="${pageContext.request.contextPath}/trainers">

                    Back

                </a>

            </form:form>

        </div>

    </body>
</html>
