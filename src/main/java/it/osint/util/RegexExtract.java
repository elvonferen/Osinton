package it.osint.util;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExtract {

    private static final Pattern EMAIL =
            Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");

    private static final Pattern PHONE =
            Pattern.compile("(\\+\\d{1,3}[\\s-]?)?\\d{6,14}");

    private static final Pattern ADDRESS =
            Pattern.compile("\\b\\d{1,5}\\s+[A-Za-zÀ-ÿ'.\\-\\s]{3,},\\s*[A-Za-zÀ-ÿ'.\\-\\s]{2,}\\b");

    public static Set<String> emails(String text) {
        return find(text, EMAIL, 10);
    }

    public static Set<String> phones(String text) {
        return find(text, PHONE, 10);
    }

    public static Set<String> addresses(String text) {
        return find(text, ADDRESS, 5);
    }

    private static Set<String> find(String text, Pattern p, int max) {
        Set<String> out = new LinkedHashSet<>();
        if (text == null) return out;
        Matcher m = p.matcher(text);
        while (m.find() && out.size() < max) out.add(m.group());
        return out;
    }

    public static String normalizeUrl(String url) {
        if (url == null) return null;
        int idx = url.indexOf('#');
        return idx > 0 ? url.substring(0, idx) : url;
    }
}
