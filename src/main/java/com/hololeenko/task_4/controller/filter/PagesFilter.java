package com.hololeenko.task_4.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/pages/main.jsp/*"})
public class PagesFilter implements Filter {

    private static final String START_PAGE = "/";


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        if(session != null && session.getAttribute("user_role") != null){
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            response.sendRedirect(request.getContextPath() + START_PAGE);
        }
    }
}
