package servlets;

import logic.ValidateService;
import models.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;

public class UsersServlet extends HttpServlet {
    private final ValidateService logic = ValidateService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<User> users = logic.getAll();
        PrintWriter writer = new PrintWriter(resp.getOutputStream());

        users.sort(Comparator.comparingInt(User::getId));
        resp.setContentType("text/html");
        writer.append(
                "<!DOCTYPE html>\n"
              + "<html lang=\"en\">\n"
              + "<head>\n"
              + "    <meta charset=\"UTF-8\">\n"
              + "    <title>Users</title>\n"
              + "</head>\n"
              + "<body>\n"
              + "<table>\n"
              + "    <tbody>\n"
              + "    <tr>\n"
              + "        <td>ID</td>"
              + "<td>Name</td>"
              + "<td>Login</td>"
              + "<td>Email</td>"
              + "<td>Created</td>"
              + "<td>Actions</td>\n"
              + "    </tr>\n"
        );
        for (var user : users) {
            writer.append("    <tr>\n")
                    .append("        <td>").append(String.valueOf(user.getId())).append("</td>")
                    .append("        <td>").append(user.getName()).append("</td>\n")
                    .append("        <td>").append(user.getLogin()).append("</td>\n")
                    .append("        <td>").append(user.getEmail()).append("</td>\n")
                    .append("        <td>").append(user.getCreateDate().toString()).append("</td>\n")
                    .append("        <td>\n")
                    .append("            <form action=\"").append(req.getContextPath())
                    .append("/edit\" method=\"get\">\n")
                    .append("                <input type=\"hidden\" name=\"id\" value=\"")
                    .append(String.valueOf(user.getId())).append("\"/>\n")
                    .append("                <input type=\"submit\" value=\"Update\"/>\n")
                    .append("            </form>\n")
                    .append("            <form action=\"").append(req.getContextPath())
                    .append("/list\" method=\"post\">\n")
                    .append("                <input type=\"hidden\" name=\"id\" value=\"")
                    .append(String.valueOf(user.getId())).append("\"/>\n")
                    .append("                <input type=\"submit\" value=\"Delete\"/>\n")
                    .append("            </form>\n")
                    .append("        </td>\n")
                    .append("    </tr>\n");
        }
        writer.append(
                "    </tbody>\n")
                .append("</table>\n<br/>")
                .append("<form action=\"").append(req.getContextPath()).append("/create\" method=\"get\">\n")
                .append("    <input type=\"submit\" value=\"Create\"/>\n")
                .append("</form>\n")
                .append("</body>\n"
                       + "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logic.delete(new User(Integer.parseInt(req.getParameter("id"))));
        resp.setContentType("text/html");
        doGet(req, resp);
    }
}
