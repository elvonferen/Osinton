package it.osint.ai.ollama;

import it.osint.model.PersonProfile;

public interface LLMAnalyzer {
    String analyze(PersonProfile profile);
}
