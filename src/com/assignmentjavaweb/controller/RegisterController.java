package com.assignmentjavaweb.controller;

import com.assignmentjavaweb.data.DataConnectionHelper;
import com.assignmentjavaweb.entity.User;
import com.assignmentjavaweb.model.UserModels;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

public class RegisterController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("title","Register");
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new User(username,password);
        if (!user.isValid()){
            HashMap<String, ArrayList<String>> errors = user.getErrors();
            req.setAttribute("user",user);
            req.setAttribute("errors",errors);
            req.getRequestDispatcher("register.jsp").forward(req,resp);
        }
        UserModels userModels = new UserModels();
        User a = userModels.addUser(user.getUsername(), user.getPassword(), 0, 1);
        resp.getWriter().println(a.getSalt());
        resp.getWriter().println("SUCCESS");
        resp.getWriter().println(user.getUsername());
        resp.getWriter().println(user.getPassword());
    }
}
