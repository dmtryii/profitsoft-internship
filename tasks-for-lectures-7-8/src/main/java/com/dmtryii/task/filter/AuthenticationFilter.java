package com.dmtryii.task.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = "/*")
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        if(checkStatus((HttpServletRequest) req, (HttpServletResponse) resp)) return;

        chain.doFilter(req, resp);
    }

    private boolean checkStatus(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Boolean isAuthentication = (Boolean) req.getSession().getAttribute("authenticated");

        switch (req.getRequestURI()) {
            case "/login", "/" : {
                if(isAuthentication != null && isAuthentication) {
                    req.getRequestDispatcher("/home.jsp").forward(req, resp);
                    return true;
                }
            }
            case "/info", "/home" : {
                if(isAuthentication == null || !isAuthentication) {
                    req.setAttribute("credentials_error", "Please login to view this page!");
                    req.getRequestDispatcher("login.jsp").forward(req, resp);
                    return true;
                }
            }
        }

        return false;
    }
}
