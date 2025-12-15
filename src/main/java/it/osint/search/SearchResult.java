package it.osint.search;

public class SearchResult {

    private final String title;
    private final String snippet;
    private final String url;
    private final String source;

    public SearchResult(String title, String snippet, String url, String source) {
        this.title = title;
        this.snippet = snippet;
        this.url = url;
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public String getSnippet() {
        return snippet;
    }

    public String getUrl() {
        return url;
    }

    public String getSource() {
        return source;
    }
}
