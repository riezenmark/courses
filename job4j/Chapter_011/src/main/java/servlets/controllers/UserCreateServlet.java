package servlets.controllers;

import logic.ValidateService;
import models.Role;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;

public class UserCreateServlet extends HttpServlet {
    private final ValidateService logic = ValidateService.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("count", logic.count());
        req.getRequestDispatcher("WEB-INF/views/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logic.add(
                new User(
                        Integer.parseInt(req.getParameter("id")),
                        req.getParameter("name"),
                        req.getParameter("login"),
                        req.getParameter("email"),
                        req.getParameter("password"),
                        Role.getRole(req.getParameter("role")),
                        Calendar.getInstance()
                )
        );
        resp.setContentType("text/html");
        resp.sendRedirect(String.format("%s/list", req.getContextPath()));
    }
}
