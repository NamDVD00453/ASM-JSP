package com.assignmentjavaweb.filter;

import com.assignmentjavaweb.security.Security;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

public class GuestFilter implements Filter {
    Security security = new Security();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println(new Date() + "Filtering by Guest Filter !");
        security.writeLog("Filtering by Guest Filter !");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpSession httpSession = httpServletRequest.getSession();
        String username = (String) httpSession.getAttribute("logUsername");
        if (username != null && username.length() > 0) {
            httpServletRequest.setAttribute("loggedIn", username);
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
//            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            httpServletResponse.sendRedirect("/login");
        }
    }

    @Override
    public void destroy() {

    }
}
