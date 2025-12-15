package it.osint.search;

import java.util.List;

public interface SearchEngine {
    String name();
    List<SearchResult> search(String query) throws Exception;
}
