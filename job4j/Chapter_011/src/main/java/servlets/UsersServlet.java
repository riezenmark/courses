package servlets;

import logic.ValidateService;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsersServlet extends HttpServlet {
    private final ValidateService logic = ValidateService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("users", logic.getAll());
        req.getRequestDispatcher("WEB-INF/views/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logic.delete(new User(Integer.parseInt(req.getParameter("id"))));
        resp.setContentType("text/html");
        resp.sendRedirect(String.format("%s/list", req.getContextPath()));
    }
}
