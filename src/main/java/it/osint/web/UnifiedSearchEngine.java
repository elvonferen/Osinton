package it.osint.web;

import it.osint.ai.AIAnalyst;
import it.osint.ai.SearchPlan;
import it.osint.ai.ollama.OllamaLLMAnalyzer;
import it.osint.model.PersonProfile;
import it.osint.model.SocialMatch;
import it.osint.search.*;
import it.osint.util.RegexExtract;

import java.util.List;

public class UnifiedSearchEngine {

    private final SearchEngine engine;
    private final AIAnalyst analyst = new AIAnalyst();
    private final OllamaLLMAnalyzer llm = new OllamaLLMAnalyzer();

    public UnifiedSearchEngine(String serpKey) {
        this.engine = new SerpApiEngine(serpKey);
    }

    public PersonProfile searchByFields(String name, String surname, String username, String email, String phone) throws Exception {

        PersonProfile p = new PersonProfile();
        p.setName(name);
        p.setSurname(surname);
        p.setUsername(username);

        SearchPlan plan = analyst.buildPlan(name, surname, username, email, phone);

        for (String q : plan.queries()) {
            List<SearchResult> results = engine.search(q);
            for (SearchResult r : results) {
                processResult(p, r, name, surname, username, email, phone);
            }
        }

        p.setAiSummary(llm.analyze(p));
        return p;
    }

    private void processResult(PersonProfile p, SearchResult r,
                               String name, String surname, String username, String email, String phone) {

        String url = RegexExtract.normalizeUrl(r.getUrl());
        if (url == null) return;

        p.addSource(url);

        String text = (r.getTitle() + " " + r.getSnippet()).toLowerCase();

        RegexExtract.emails(text).forEach(p::addEmail);
        RegexExtract.phones(text).forEach(p::addPhone);
        RegexExtract.addresses(text).forEach(p::addAddress);

        matchSocial(p, "Instagram", url, "instagram.com", username, name, surname, text);
        matchSocial(p, "Facebook", url, "facebook.com", username, name, surname, text);
        matchSocial(p, "LinkedIn", url, "linkedin.com", username, name, surname, text);
        matchSocial(p, "GitHub", url, "github.com", username, name, surname, text);
        matchSocial(p, "X/Twitter", url, "twitter.com", username, name, surname, text);
        matchSocial(p, "X/Twitter", url, "x.com", username, name, surname, text);

        if (email != null && text.contains(email.toLowerCase())) {
            p.getSocials().values().forEach(s -> s.addScore(10, "Email match"));
        }
        if (phone != null && text.contains(phone.toLowerCase())) {
            p.getSocials().values().forEach(s -> s.addScore(10, "Phone match"));
        }
    }

    private void matchSocial(PersonProfile p, String platform, String url, String domain,
                             String username, String name, String surname, String text) {

        if (!url.contains(domain)) return;

        SocialMatch sm = p.upsertSocial(platform, url);
        sm.addScore(25, "Domain match");

        if (username != null && url.toLowerCase().contains(username.toLowerCase())) {
            sm.addScore(45, "Username match");
        }

        String full = ((name == null ? "" : name) + " " + (surname == null ? "" : surname)).trim().toLowerCase();
        if (!full.isBlank() && text.contains(full)) {
            sm.addScore(20, "Full name in snippet");
        }
    }
}
