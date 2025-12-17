package org.example.langchain4j;

import dev.langchain4j.agentic.AgenticServices;
import dev.langchain4j.agentic.scope.ResultWithAgenticScope;
import dev.langchain4j.agentic.supervisor.SupervisorAgent;
import dev.langchain4j.agentic.supervisor.SupervisorResponseStrategy;
import org.junit.jupiter.api.Test;

import static org.example.langchain4j.Models.baseModel;
import static org.example.langchain4j.Models.plannerModel;

public class SupervisorAgentWithContextIT {

    // use SupervisorAgentService.supervisorContext
    // not seeing much difference from SupervisorAgentIT for now
    @Test
    void loop_test() {
        Agents.CreativeWriter creativeWriter = AgenticServices.agentBuilder(Agents.CreativeWriter.class)
                .chatModel(baseModel())
                .outputKey("story")
                .build();

        Agents.StyleEditor styleEditor = AgenticServices.agentBuilder(Agents.StyleEditor.class)
                .chatModel(baseModel())
                .outputKey("story")
                .build();

        Agents.StyleScorer styleScorer = AgenticServices.agentBuilder(Agents.StyleScorer.class)
                .chatModel(baseModel())
                .outputKey("score")
                .build();

        SupervisorAgent supervisorAgent = AgenticServices.supervisorBuilder()
                .chatModel(plannerModel())
                .responseStrategy(SupervisorResponseStrategy.SUMMARY)
                .supervisorContext("At first, create a task list based on the user request." +
                        " Then, keep track of completed tasks and remaining tasks." +
                        " Prioritize the tasks based on their importance and urgency." +
                        " Continuously update the task list as new information becomes available." +
                        " Ensure that all tasks are completed in a timely manner.")
                .subAgents(creativeWriter, styleEditor, styleScorer)
                .outputKey("summary")
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
