package org.example.langchain4j;

import static dev.langchain4j.internal.Utils.getOrDefault;

public class ApiKeys {

    public static final String ANTHROPIC_API_KEY = getOrDefault(System.getenv("ANTHROPIC_API_KEY"),
                                                                "<<API_KEY>>");

}
