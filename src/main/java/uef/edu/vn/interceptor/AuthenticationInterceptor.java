/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uef.edu.vn.interceptor;

import jakarta.servlet.http.*;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import uef.edu.vn.model.User;

@Component
public class AuthenticationInterceptor
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

        HttpSession session
                = request.getSession(false);

        User user = null;

        if (session != null) {

            user = (User) session.getAttribute(
                    "loggedUser"
            );
        }

        if (user == null) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/login"
            );

            return false;
        }
        request.setAttribute(
                "loggedUser",
                user
        );
        return true;
    }

}
