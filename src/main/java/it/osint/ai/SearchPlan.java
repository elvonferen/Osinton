package it.osint.ai;

import java.util.ArrayList;
import java.util.List;

public class SearchPlan {

    private final List<String> queries = new ArrayList<>();

    public void add(String q) {
        if (q != null && !q.isBlank()) queries.add(q.trim());
    }

    public List<String> queries() {
        return queries;
    }
}
