package it.osint.ai.ollama;

import it.osint.model.PersonProfile;
import it.osint.util.HttpUtil;
import org.json.JSONObject;

public class OllamaLLMAnalyzer implements LLMAnalyzer {

    private static final String MODEL = "llama3";
    private static final String URL = "http://localhost:11434/api/generate";

    @Override
    public String analyze(PersonProfile profile) {
        try {
            JSONObject req = new JSONObject()
                    .put("model", MODEL)
                    .put("prompt", profile.toPrettyString())
                    .put("stream", false);

            String resp = HttpUtil.postJson(URL, req.toString());
            JSONObject json = new JSONObject(resp);
            return json.optString("response", "");
        } catch (Exception e) {
            return "";
        }
    }
}
