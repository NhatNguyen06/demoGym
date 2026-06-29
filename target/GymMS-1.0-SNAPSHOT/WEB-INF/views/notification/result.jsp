<%-- 
    Document   : result
    Created on : Jun 27, 2026, 7:36:10 PM
    Author     : minhq
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Kết Quả Gửi Email</title>
    </head>
    <body>
        <div style="max-width:600px; margin:60px auto; text-align:center;">

            <c:choose>
                <c:when test="${sentCount > 0}">
                    <!-- Gửi thành công -->
                    <div style="background:#d4edda; border:1px solid #c3e6cb;
                         padding:30px; border-radius:8px;">
                        <h2 style="color:#155724;">✅ Gửi Email Thành Công</h2>
                        <p style="font-size:18px; color:#155724;">
                            Đã gửi <strong>${sentCount}</strong> email nhắc nhở
                            hết hạn gói tập.
                        </p>
                    </div>
                </c:when>
                <c:otherwise>
                    <!-- Không có gói nào sắp hết hạn -->
                    <div style="background:#fff3cd; border:1px solid #ffc107;
                         padding:30px; border-radius:8px;">
                        <h2 style="color:#856404;">ℹ️ Không Có Email Nào Được Gửi</h2>
                        <p style="color:#856404;">
                            Hiện không có gói tập nào sắp hết hạn trong 7 ngày tới.
                        </p>
                    </div>
                </c:otherwise>
            </c:choose>

            <br/>
            <a href="${pageContext.request.contextPath}/dashboard"
               style="display:inline-block; padding:10px 24px;
               background:#0d6efd; color:#fff;
               border-radius:6px; text-decoration:none;">
                ← Về Dashboard
            </a>
        </div>
    </body>
</html>