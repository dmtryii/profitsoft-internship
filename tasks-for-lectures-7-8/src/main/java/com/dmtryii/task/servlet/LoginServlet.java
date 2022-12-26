package com.dmtryii.task.servlet;

import com.dmtryii.task.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = {"/", "/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        isValidData(req, resp, login, password);
    }

    private void isValidData(HttpServletRequest req, HttpServletResponse resp, String login, String password)
            throws ServletException, IOException {

        if(UserRepository.isValidData(login, password)) {
            req.getSession().setAttribute("authenticated", true);
            req.getRequestDispatcher("home.jsp").forward(req, resp);
        } else {
            req.setAttribute("credentials_error", "Incorrect login or password. Try again.");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

}
