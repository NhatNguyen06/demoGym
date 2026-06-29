<%-- 
    Document   : form
    Created on : Jun 3, 2026, 8:09:43 PM
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
                background:#f4f6f9;
                font-family:Arial,sans-serif;
                padding:30px;
            }

            .card{
                background:white;
                max-width:800px;
                margin:auto;
                padding:30px;
                border-radius:10px;
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
            textarea{
                width:100%;
                padding:10px;
                border:1px solid #ccc;
                border-radius:5px;
            }

            textarea{
                resize:vertical;
                min-height:120px;
            }

            .btn{
                padding:10px 15px;
                border:none;
                border-radius:5px;
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

            <form:form method="post"
                       modelAttribute="plan"
                       action="${plan.id == 0 ?
                                 pageContext.request.contextPath.concat('/membership-plans/add')
                                 :
                                 pageContext.request.contextPath.concat('/membership-plans/edit')}">

                <form:hidden path="id"/>

                <div class="form-group">

                    <label>Plan Name</label>

                    <form:input path="name"/>

                    <form:errors path="name"
                                 cssClass="error"/>

                </div>

                <div class="form-group">

                    <label>Duration (Months)</label>

                    <form:input path="durationMonths"
                                type="number"/>

                    <form:errors path="durationMonths"
                                 cssClass="error"/>

                </div>

                <div class="form-group">

                    <label>Price</label>

                    <form:input path="price"
                                type="number"
                                step="0.01"/>

                    <form:errors path="price"
                                 cssClass="error"/>

                </div>

                <div class="form-group">

                    <label>Description</label>

                    <form:textarea path="description"/>

                </div>

                <button type="submit"
                        class="btn save">

                    Save

                </button>

                <a class="btn back"
                   href="${pageContext.request.contextPath}/membership-plans">

                    Back

                </a>

            </form:form>

        </div>

    </body>
</html>
