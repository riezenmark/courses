package servlets.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import logic.ValidateService;
import models.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class JSONServlet extends HttpServlet {
    private final ValidateService logic = ValidateService.getINSTANCE();
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        ArrayNode arrayNode = mapper.createArrayNode();
        for (var user : users.values()) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("id", user.getId());
            objectNode.put("name", user.getName());
            objectNode.put("login", user.getLogin());
            objectNode.put("email", user.getEmail());
            objectNode.put("password", user.getPassword());
            objectNode.put("role", user.getRole().getName());
            objectNode.put("created", user.getCreateDate().getTime());
            arrayNode.add(objectNode);
        }
        writer.append(arrayNode.toPrettyString());
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader reader = req.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        reader.lines().forEach(stringBuilder::append);
        JsonNode tree = mapper.readTree(stringBuilder.toString());
        User user = new User(
                tree.get("id").asInt(),
                tree.get("name").asText(),
                tree.get("login").asText(),
                tree.get("email").asText(),
                tree.get("password").asText(),
                tree.get("role").asText(),
                tree.get("created").asLong()
        );
        users.put(Integer.parseInt(user.getId()), user);
    }

    @Override
    public void init() {
        List<User> list = logic.getAll();
        for (var user : list) {
            users.put(Integer.parseInt(user.getId()), user);
        }
    }
}
