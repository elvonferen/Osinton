package it.osint.model;

import java.util.ArrayList;
import java.util.List;

public class SocialMatch {

    private final String platform;
    private final String url;
    private int confidence;
    private final List<String> reasons = new ArrayList<>();

    public SocialMatch(String platform, String url) {
        this.platform = platform;
        this.url = url;
    }

    public void addScore(int value, String reason) {
        confidence = Math.min(100, confidence + value);
        reasons.add(reason);
    }

    @Override
    public String toString() {
        return platform + " (" + confidence + "%)\n" +
                url + "\n" +
                String.join(", ", reasons);
    }
}
