package org.example.langchain4j;

import static dev.langchain4j.internal.Utils.getOrDefault;

public class EnvVars {

    public static final String WATSONX_API_KEY = getOrDefault(System.getenv("WATSONX_API_KEY"),
                                                              () -> getOrDefault(System.getenv("IBM_CLOUD_API_KEY"), () -> null));

    public static final String MODEL_ID = getOrDefault(System.getenv("MODEL_ID"), () -> "ibm/granite-3-8b-instruct");
    public static final String PROJECT_ID = getOrDefault(System.getenv("PROJECT_ID"), () -> null);
}
