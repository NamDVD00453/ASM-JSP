package com.assignmentjavaweb.controller;

import com.assignmentjavaweb.entity.User;
import com.assignmentjavaweb.model.UserModels;
import com.assignmentjavaweb.security.Security;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("title","Login Page");
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        Security security = new Security();

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserModels userModels = new UserModels();
        User user = userModels.check(username, password);
        if (user != null){
            HttpSession session = req.getSession();
            session.setAttribute("logUsername", user.getUsername());
            session.setAttribute("logUserId", user.getId());
            session.setAttribute("logUserRole", user.getRole());
            System.out.println("Logged in sucess !!! " + user.getRole());
            if (user.getRole() == 1) {
                resp.sendRedirect("/admin");

            }else {
                resp.sendRedirect("/feedback");
            }
        } else {
            resp.getWriter().println("Sai thông tin tài khoản.");
        }
    }
}
