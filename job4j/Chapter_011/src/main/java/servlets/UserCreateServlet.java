package servlets;

import logic.ValidateService;
import models.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserCreateServlet extends HttpServlet {
    private final ValidateService logic = ValidateService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = new PrintWriter(resp.getOutputStream());

        resp.setContentType("text/html");
        writer.append("<!DOCTYPE html>\n")
                .append("<html lang=\"en\">\n")
                .append("<head>\n")
                .append("    <meta charset=\"UTF-8\">\n")
                .append("    <title>Create</title>\n")
                .append("</head>\n")
                .append("<body>\n")
                .append("<form action=\"").append(req.getContextPath()).append("/create\" method=\"post\">\n")
                .append("    <input type=\"hidden\" name=\"id\" value=\"")
                .append(String.valueOf(logic.count())).append("\"/>\n")
                .append("    Name: <input type=\"text\" name=\"name\"/><br/>\n")
                .append("    Login: <input type=\"text\" name=\"login\"/><br/>\n")
                .append("    Email: <input type=\"text\" name=\"email\"/><br/>\n")
                .append("    <input type=\"submit\" value=\"Create\">\n")
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
        logic.add(
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
                .append("    <title>Create</title>\n")
                .append("</head>\n")
                .append("<body>\n")
                .append("User has been created.<br/>")
                .append("<form action=\"").append(req.getContextPath()).append("/list\" method=\"get\">\n")
                .append("    <input type=\"submit\" value=\"List\"/>\n")
                .append("</form>\n")
                .append("</body>\n")
                .append("</html>");
        writer.flush();
    }
}
