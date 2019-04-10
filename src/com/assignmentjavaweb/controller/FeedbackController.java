package com.assignmentjavaweb.controller;

import com.assignmentjavaweb.entity.Feedback;
import com.assignmentjavaweb.model.FeedbackModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FeedbackController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("title","feedback");
        req.getRequestDispatcher("/feedback.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession httpSession = req.getSession();
        int userId = (int) httpSession.getAttribute("logUserId");
        System.out.println(userId);
        String content = req.getParameter("content");
        String title = req.getParameter("title");
        String username = (String) httpSession.getAttribute("logUsername");

        Feedback feedback = new Feedback(content, title);

        if (!feedback.isValid()){
            HashMap<String, ArrayList<String>> errors = feedback.getErrors();
            HttpSession session = req.getSession();
            req.setAttribute("logTitle", title);
            req.setAttribute("logContent",content);
            req.setAttribute("errors",errors);
            session.setAttribute("logContent", content);
            session.setAttribute("logTitle", title);
            session.setAttribute("logUserId", userId);


//            resp.sendRedirect("/feedback");logUsername

            req.getRequestDispatcher("feedback.jsp").forward(req,resp);
        }
        FeedbackModel feedbackModel = new FeedbackModel();
        feedbackModel.addFeedback(title, content, userId, username);
        resp.sendRedirect("/feedbacks");
    }
}
