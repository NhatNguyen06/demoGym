/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uef.edu.vn.interceptor;

import jakarta.servlet.http.*;

import java.util.Arrays;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import uef.edu.vn.annotation.RoleRequired;
import uef.edu.vn.model.User;

@Component
public class AuthorizationInterceptor
        implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {

        if (!(handler instanceof HandlerMethod)) {

            return true;
        }

        HandlerMethod method = (HandlerMethod) handler;

        RoleRequired roleRequired
                = method.getMethodAnnotation(RoleRequired.class);

        if (roleRequired == null) {
            roleRequired = method.getBeanType().getAnnotation(RoleRequired.class);
        }

// nếu không có role requirement → cho đi luôn
        if (roleRequired == null) {
            return true;
        }

        HttpSession session = request.getSession(false);

        if (session == null) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/login"
            );

            return false;
        }

        User user = (User) session.getAttribute("loggedUser");

        if (user == null || user.getRole() == null) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/login"
            );

            return false;
        }

        boolean allow
                = Arrays.asList(
                        roleRequired.value()
                )
                        .contains(
                                user.getRole()
                        );

        if (!allow) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/error/403"
            );

            return false;
        }

        return true;
    }

}
