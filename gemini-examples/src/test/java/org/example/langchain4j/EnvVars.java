package org.example.langchain4j;

import static dev.langchain4j.internal.Utils.getOrDefault;

public class EnvVars {

    public static final String GOOGLE_AI_GEMINI_API_KEY = getOrDefault(System.getenv("GOOGLE_AI_GEMINI_API_KEY"), () -> null);

}
