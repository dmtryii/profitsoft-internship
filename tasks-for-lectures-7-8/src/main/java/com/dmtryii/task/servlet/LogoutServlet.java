package com.dmtryii.task.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LogoutServlet", value = "/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        removeSession(req.getSession());
        req.getRequestDispatcher("/login").forward(req, resp);
    }

    private void removeSession(HttpSession session) {
        session.removeAttribute("authenticated");
        session.removeAttribute("credentials_error");
        session.removeAttribute("userInfo");
    }

}
