package it.osint.search;

import it.osint.util.HttpUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SerpApiEngine implements SearchEngine {

    private final String apiKey;

    public SerpApiEngine(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public String name() {
        return "SerpAPI";
    }

    @Override
    public List<SearchResult> search(String query) throws Exception {
        List<SearchResult> results = new ArrayList<>();
        if (query == null || query.isBlank()) return results;

        String q = URLEncoder.encode(query, StandardCharsets.UTF_8);
        String url = "https://serpapi.com/search.json?engine=google&q=" + q + "&api_key=" + apiKey;

        JSONObject json = new JSONObject(HttpUtil.get(url));
        JSONArray arr = json.optJSONArray("organic_results");
        if (arr == null) return results;

        for (int i = 0; i < arr.length(); i++) {
            JSONObject o = arr.getJSONObject(i);
            results.add(new SearchResult(
                    o.optString("title", ""),
                    o.optString("snippet", ""),
                    o.optString("link", ""),
                    name()
            ));
        }
        return results;
    }
}
