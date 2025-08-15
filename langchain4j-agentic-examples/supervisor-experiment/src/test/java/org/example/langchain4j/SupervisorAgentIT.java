package org.example.langchain4j;

import dev.langchain4j.agentic.AgenticServices;
import dev.langchain4j.agentic.scope.ResultWithAgenticScope;
import dev.langchain4j.agentic.supervisor.SupervisorAgent;
import dev.langchain4j.agentic.supervisor.SupervisorResponseStrategy;
import org.junit.jupiter.api.Test;

import static org.example.langchain4j.Models.baseModel;
import static org.example.langchain4j.Models.plannerModel;

public class SupervisorAgentIT {

    // Writer Agent Tests for the SupervisorAgent
    // This test demonstrates how to let the SupervisorAgent manage a loop of agents,
    // which was done in langchain4j-agentic test case WorkflowAgentsIT/SupervisorAndWorkflowAgentsIT using loopBuilder
    @Test
    void loop_test() {
        Agents.CreativeWriter creativeWriter = AgenticServices.agentBuilder(Agents.CreativeWriter.class)
                .chatModel(baseModel())
                .outputName("story")
                .build();

        Agents.StyleEditor styleEditor = AgenticServices.agentBuilder(Agents.StyleEditor.class)
                .chatModel(baseModel())
                .outputName("story")
                .build();

        Agents.StyleScorer styleScorer = AgenticServices.agentBuilder(Agents.StyleScorer.class)
                .chatModel(baseModel())
                .outputName("score")
                .build();

        SupervisorAgent supervisorAgent = AgenticServices.supervisorBuilder()
                .chatModel(plannerModel())
                .responseStrategy(SupervisorResponseStrategy.SUMMARY)
                .subAgents(creativeWriter, styleEditor, styleScorer)
                .outputName("summary")
                .build();

        ResultWithAgenticScope<String> result = supervisorAgent.invokeWithAgenticScope("Write a story about dragons and wizards in a comedy style." +
                " Firstly, write a story without a specific style." + // For test purpose, intentionally create a lower score
                " Let styleScorer score the story." + // Without this, styleEditor is used earlier, so a loop doesn't happen
                " Repeatedly use styleEditor to brush-up the style until styleScorer gives a score of at least 0.8." +
                " The loop should not exceed 5 iterations.");

        System.out.println("===================================================");
        System.out.println("story: " + result.agenticScope().readState("story"));
        System.out.println("score: " + result.agenticScope().readState("score"));
        System.out.println("summary: " + result.agenticScope().readState("summary"));
    }
}
