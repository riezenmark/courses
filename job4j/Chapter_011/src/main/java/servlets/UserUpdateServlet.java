package servlets;

import logic.ValidateService;
import models.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserUpdateServlet extends HttpServlet {
    private final ValidateService logic = ValidateService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = logic.findById(Integer.parseInt(req.getParameter("id")));
        PrintWriter writer = new PrintWriter(resp.getOutputStream());

        resp.setContentType("text/html");
        writer.append("<!DOCTYPE html>\n")
                .append("<html lang=\"en\">\n")
                .append("<head>\n")
                .append("    <meta charset=\"UTF-8\">\n")
                .append("    <title>Update</title>\n")
                .append("</head>\n")
                .append("<body>\n")
                .append("<form action=\"").append(req.getContextPath()).append("/edit\" method=\"post\">\n")
                .append("    <input type=\"hidden\" name=\"id\" value=\"")
                .append(String.valueOf(user.getId())).append("\"/>\n")
                .append("    Name: <input type=\"text\" name=\"name\" value=\"")
                .append(user.getName()).append("\"/><br/>\n")
                .append("    Login: <input type=\"text\" name=\"login\" value=\"")
                .append(user.getLogin()).append("\"/><br/>\n")
                .append("    Email: <input type=\"text\" name=\"email\" value=\"")
                .append(user.getEmail()).append("\"/><br/>\n")
                .append("    <input type=\"submit\" value=\"Update\">\n")
                .append("</form><br/>\n")
                .append("<form action=\"").append(req.getContextPath()).append("/list\" method=\"get\">\n")
                .append("    <input type=\"submit\" value=\"List\"/>\n")
                .append("</form>\n")
                .append("</body>\n")
                .append("</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logic.update(
                new User(
                        Integer.parseInt(req.getParameter("id")),
                        req.getParameter("name"),
                        req.getParameter("login"),
                        req.getParameter("email")
                )
        );
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        resp.setContentType("text/html");
        writer.append("<!DOCTYPE html>\n")
                .append("<html lang=\"en\">\n")
                .append("<head>\n")
                .append("    <meta charset=\"UTF-8\">\n")
                .append("    <title>Update</title>\n")
                .append("</head>\n")
                .append("<body>\n")
                .append("User has been updated.<br/>")
                .append("<form action=\"").append(req.getContextPath()).append("/list\" method=\"get\">\n")
                .append("    <input type=\"submit\" value=\"List\"/>\n")
                .append("</form>\n")
                .append("</body>\n")
                .append("</html>");
        writer.flush();
    }
}
