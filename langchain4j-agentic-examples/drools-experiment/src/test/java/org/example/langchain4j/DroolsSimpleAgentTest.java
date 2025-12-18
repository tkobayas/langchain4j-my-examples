package org.example.langchain4j;

import dev.langchain4j.agentic.AgenticServices;
import dev.langchain4j.agentic.scope.ResultWithAgenticScope;
import dev.langchain4j.agentic.supervisor.SupervisorAgent;
import dev.langchain4j.agentic.supervisor.SupervisorResponseStrategy;
import org.junit.jupiter.api.Test;

import static org.example.langchain4j.Models.plannerModel;

public class DroolsSimpleAgentTest {

    @Test
    void testDrools() {
        Agents.DroolsExpert droolsExpert = new Agents.DroolsExpert();

        SupervisorAgent supervisorAgent = AgenticServices.supervisorBuilder()
                .chatModel(plannerModel())
                .responseStrategy(SupervisorResponseStrategy.SUMMARY)
                .subAgents(droolsExpert)
                .outputKey("summary")
                .build();

        ResultWithAgenticScope<String> result = supervisorAgent
                .invokeWithAgenticScope("""
                                                Evaluate a loan application for a 45 year old person requesting a loan of $5000.
                                                """);

        System.out.println("===================================================");
        System.out.println("summary: " + result.agenticScope().readState("summary"));
    }
}
