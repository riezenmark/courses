package servlets;

import logic.ValidateService;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class UserServlet extends HttpServlet {
    private final ValidateService logic = ValidateService.INSTANCE;
    private final Map<String, Function<User, Boolean>> actions = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final String SEPARATOR = "<br>";
        List<User> users = logic.getAll();
        PrintWriter writer = resp.getWriter();

        users.sort(Comparator.comparingInt(User::getId));
        resp.setContentType("text/html");
        writer.println("Users:" + SEPARATOR);
        for (var user : users) {
            writer.println(user.toString() + SEPARATOR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html");
        actions.get(req.getParameter("action")).apply(
                new User(
                        Integer.parseInt(req.getParameter("id")),
                        req.getParameter("name"),
                        req.getParameter("login"),
                        req.getParameter("email")
                )
        );
    }

    @Override
    public void init() throws ServletException {
        super.init();
        this.fillMap();
    }

    private void fillMap() {
        actions.put("add", this.add());
        actions.put("update", this.update());
        actions.put("delete", this.delete());
    }

    private Function<User, Boolean> add() {
        return logic::add;
    }

    private Function<User, Boolean> update() {
        return logic::update;
    }

    private Function<User, Boolean> delete() {
        return logic::delete;
    }
}
