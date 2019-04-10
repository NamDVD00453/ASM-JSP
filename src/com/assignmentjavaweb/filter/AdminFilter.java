package com.assignmentjavaweb.filter;

import com.assignmentjavaweb.security.Security;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminFilter implements Filter {
    Security security = new Security();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Filtering by Admin Filter !");
        security.writeLog("Filtering by Admin Filter !");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpSession httpSession = httpServletRequest.getSession();
        String username = (String) httpSession.getAttribute("logUsername");
        Integer role = (Integer) httpSession.getAttribute("logUserRole");
        System.out.println(role + " - " + username);
        if (username != null && username.length() > 0 && role == 1) {
            httpServletRequest.setAttribute("loggedIn", username);
            httpServletRequest.setAttribute("role", role);
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
//            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//            System.out.println(username + " - " + role);
            httpServletResponse.sendRedirect("/login");
        }
    }

    @Override
    public void destroy() {

    }
}
