package it.osint.ai.ollama;

import it.osint.util.HttpUtil;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OllamaQueryGenerator {

    private static final String MODEL = "llama3";
    private static final String URL = "http://localhost:11434/api/generate";

    public List<String> generate(String name, String surname, String username, String email, String phone) throws Exception {
        String full = ((name == null ? "" : name) + " " + (surname == null ? "" : surname)).trim();

        String prompt =
                "Generate 10 OSINT web search queries, one per line.\n" +
                "Name: " + (full.isBlank() ? "-" : full) + "\n" +
                "Username: " + nvl(username) + "\n" +
                "Email: " + nvl(email) + "\n" +
                "Phone: " + nvl(phone) + "\n";

        JSONObject req = new JSONObject()
                .put("model", MODEL)
                .put("prompt", prompt)
                .put("stream", false);

        String resp = HttpUtil.postJson(URL, req.toString());
        JSONObject json = new JSONObject(resp);
        String text = json.optString("response", "");

        List<String> out = new ArrayList<>();
        for (String line : text.split("\\R")) {
            if (!line.isBlank()) out.add(line.trim());
        }
        return out;
    }

    private String nvl(String s) {
        return s == null || s.isBlank() ? "-" : s;
    }
}
