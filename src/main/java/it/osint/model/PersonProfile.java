package it.osint.model;

import java.util.*;

public class PersonProfile {

    private String name;
    private String surname;
    private String username;

    private final Set<String> emails = new LinkedHashSet<>();
    private final Set<String> phones = new LinkedHashSet<>();
    private final Set<String> addresses = new LinkedHashSet<>();
    private final Map<String, SocialMatch> socials = new LinkedHashMap<>();
    private final List<String> sources = new ArrayList<>();

    private String aiSummary = "";

    public void setName(String v) { name = clean(v); }
    public void setSurname(String v) { surname = clean(v); }
    public void setUsername(String v) { username = clean(v); }

    public void addEmail(String v) { if (v != null) emails.add(v); }
    public void addPhone(String v) { if (v != null) phones.add(v); }
    public void addAddress(String v) { if (v != null) addresses.add(v); }
    public void addSource(String v) { if (v != null) sources.add(v); }

    public SocialMatch upsertSocial(String platform, String url) {
        return socials.computeIfAbsent(platform, k -> new SocialMatch(platform, url));
    }

    public void setAiSummary(String s) { aiSummary = s == null ? "" : s; }

    public Map<String, SocialMatch> getSocials() { return socials; }

    public String toPrettyString() {
        StringBuilder sb = new StringBuilder();

        sb.append("IDENTITY\n");
        sb.append("Name: ").append(nvl(name)).append("\n");
        sb.append("Surname: ").append(nvl(surname)).append("\n");
        sb.append("Username: ").append(nvl(username)).append("\n\n");

        sb.append("CONTACTS\n");
        sb.append("Emails: ").append(emails.isEmpty() ? "-" : emails).append("\n");
        sb.append("Phones: ").append(phones.isEmpty() ? "-" : phones).append("\n");
        sb.append("Addresses: ").append(addresses.isEmpty() ? "-" : addresses).append("\n\n");

        sb.append("SOCIALS\n");
        socials.values().forEach(s -> sb.append(s).append("\n\n"));

        sb.append("SOURCES\n");
        sources.stream().limit(25).forEach(s -> sb.append(s).append("\n"));

        if (!aiSummary.isBlank()) {
            sb.append("\nAI ANALYSIS\n").append(aiSummary);
        }

        return sb.toString();
    }

    private String clean(String s) { return s == null || s.isBlank() ? null : s; }
    private String nvl(String s) { return s == null ? "-" : s; }
}
