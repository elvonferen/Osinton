package it.osint.ai;

import it.osint.ai.ollama.OllamaQueryGenerator;

public class AIAnalyst {

    private final OllamaQueryGenerator generator = new OllamaQueryGenerator();

    public SearchPlan buildPlan(String name, String surname, String username, String email, String phone) {
        SearchPlan plan = new SearchPlan();
        try {
            for (String q : generator.generate(name, surname, username, email, phone)) {
                plan.add(q);
            }
        } catch (Exception e) {
            fallback(plan, name, surname, username, email, phone);
        }
        if (plan.queries().isEmpty()) fallback(plan, name, surname, username, email, phone);
        return plan;
    }

    private void fallback(SearchPlan p, String name, String surname, String username, String email, String phone) {
        String full = ((name == null ? "" : name) + " " + (surname == null ? "" : surname)).trim();

        if (!full.isBlank()) {
            p.add("\"" + full + "\"");
            p.add(full + " linkedin");
            p.add(full + " instagram");
            p.add(full + " facebook");
            p.add(full + " twitter");
            p.add(full + " github");
        }

        if (username != null && !username.isBlank()) {
            p.add(username);
            p.add(username + " site:instagram.com");
            p.add(username + " site:linkedin.com");
            p.add(username + " site:github.com");
            p.add(username + " site:x.com OR site:twitter.com");
        }

        if (email != null && !email.isBlank()) {
            p.add("\"" + email + "\"");
        }

        if (phone != null && !phone.isBlank()) {
            p.add("\"" + phone + "\"");
        }
    }
}
