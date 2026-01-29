package com.hololeenko.task_4.controller;

import java.io.*;
import java.util.List;

import com.hololeenko.task_4.command.Command;
import com.hololeenko.task_4.command.CommandType;
import com.hololeenko.task_4.command.Router;
import com.hololeenko.task_4.exception.CommandException;
import com.hololeenko.task_4.model.pool.ConnectionPool;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import static com.hololeenko.task_4.command.ConstantAttribute.*;

@WebServlet(name = "helloServlet", urlPatterns = {"/controller", "*.do"})
public class Controller extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String commandStr = req.getParameter(COMMAND);
        Command command = CommandType.define(commandStr);
        Router router;
        try {
            router = command.execute(req);
            String page = router.getPage();
            if(router.getType() == Router.Type.REDIRECT){
                resp.sendRedirect(req.getContextPath() + page);
            }else{
                req.getRequestDispatcher(page).forward(req, resp);
            }
        } catch (CommandException e) {
            throw new ServletException(e);
        }
    }

    public void destroy() {
        ConnectionPool.getInstance().destroyPool();
    }
}