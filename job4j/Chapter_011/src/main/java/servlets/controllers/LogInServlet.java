package servlets.controllers;

import logic.ValidateService;
import models.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogInServlet extends HttpServlet {
    private final ValidateService logic = ValidateService.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (logic.isCredential(login, password)) {
            HttpSession session = req.getSession();
            session.setAttribute("login", login);
            session.setAttribute("role", Role.getRole(logic.getRoleID(login, password)).getName());
            session.setAttribute("user_id", logic.getUserID(login, password));
            session.setMaxInactiveInterval(3600);
            resp.sendRedirect(String.format("%s/list", req.getContextPath()));
        } else {
            req.setAttribute("error", "Invalid Login or Password.");
            doGet(req, resp);
        }
    }
}
