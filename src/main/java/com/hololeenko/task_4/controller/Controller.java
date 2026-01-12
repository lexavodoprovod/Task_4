package com.hololeenko.task_4.controller;

import java.io.*;
import java.util.List;

import com.hololeenko.task_4.command.Command;
import com.hololeenko.task_4.command.CommandType;
import com.hololeenko.task_4.exception.CommandException;
import com.hololeenko.task_4.model.pool.ConnectionPool;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", urlPatterns = {"/controller", "*.do"})
public class Controller extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String commandStr = request.getParameter("command");
        Command command = CommandType.define(commandStr);
        String page ;
        try {
            page = command.execute(request);
            request.getRequestDispatcher(page).forward(request, response);
 //           response.sendRedirect(page);
        } catch (CommandException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    public void destroy() {
        ConnectionPool.getInstance().destroyPool();
    }
}