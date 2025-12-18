package org.example.langchain4j;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.V;
import org.example.langchain4j.domain.LoanApplication;

public class Agents {

    public static class DroolsExpert {

        @Agent("loan approval rule engine.")
        public boolean approve(@V("request") String loanApplication) {
            // run drools
            System.out.println("Running Drools rules for loanApplication: " + loanApplication);
            return true;
        }
    }
}
