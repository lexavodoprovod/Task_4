package com.hololeenko.task_4.controller.filter;

import com.hololeenko.task_4.model.entity.UserRole;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static com.hololeenko.task_4.command.ConstantAttribute.*;

@WebFilter(urlPatterns = {"/pages/admin.jsp/*", "/pages/all_users.jsp/*"})
public class RoleFilter implements Filter {

    private static final String START_PAGE = "/";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        UserRole role = null;

        if(session != null){
            role = (UserRole) session.getAttribute(USER_ROLE);
        }

        if(role == UserRole.ADMIN){
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            response.sendRedirect(request.getContextPath() + START_PAGE);
        }
    }
}
