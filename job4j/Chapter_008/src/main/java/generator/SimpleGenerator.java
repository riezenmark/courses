package generator;

import java.util.HashMap;
import java.util.Map;

public class SimpleGenerator implements Generator {

    @Override
    public String generate(String template, Object[] data) {
        Map<String, String> map = new HashMap<>();
        for (var s : data) {
            String[] pair = s.toString().split(" -> ");
            map.put(pair[0], pair[1]);
        }

        for (String s : map.keySet()) {
            String pattern = "\\$\\{" + s + "}";
            template = template.replaceAll(pattern, map.get(s));
        }

        return template;
    }
}
