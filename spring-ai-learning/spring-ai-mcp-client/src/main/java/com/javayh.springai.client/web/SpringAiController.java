package com.javayh.springai.client.web;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.cloud.ai.dashscope.agent.DashScopeAgent;
import com.alibaba.cloud.ai.dashscope.agent.DashScopeAgentOptions;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;

/**
 * <p>
 *
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2025-07-07
 */
@RequestMapping
@RestController
public class SpringAiController {

    @Autowired
    private ToolCallbackProvider toolCallbackProvider;

    @Autowired
    private OpenAiChatModel openAiChatModel;

    @Autowired
    private DashScopeAgent dashScopeAgent;

    @Autowired
    private DashScopeChatModel dashScopeChatModel;

    @Value("${spring.ai.dashscope.agent.app-id}")
    private String appId;

    /**
     * Processes user message using OpenAI chat model with tool callbacks enabled.
     *
     * @param msg The user message to process
     * @return AI-generated response as plain text
     */
    @GetMapping("/gen")
    public String gen(@RequestParam(value = "msg") String msg) {
        ChatClient client = ChatClient.builder(openAiChatModel).defaultToolCallbacks(toolCallbackProvider).build();
        ChatClient.CallResponseSpec call = client.prompt(msg).call();
        return call.content();
    }


    /**
     * Processes message using AliCloud DashScope Agent with app ID configuration.
     *
     * @param msg The user message to process
     * @return Structured agent response from DashScope platform
     */
    @GetMapping("/gen/ali")
    public String genAli(@RequestParam(value = "msg") String msg) {
        ChatResponse response = dashScopeAgent.call(new Prompt(msg, DashScopeAgentOptions.builder().withAppId(appId).build()));

        AssistantMessage app_output = response.getResult().getOutput();
        return app_output.getText();

    }

    /**
     * Processes message using AliCloud DashScope Chat model with tool integration.
     *
     * @param msg The user message to process
     * @return AI-generated chat response from DashScope model
     */
    @GetMapping("/gen/ali/chat")
    public String genAliChat(@RequestParam(value = "msg") String msg) {
        ChatClient client = ChatClient.builder(dashScopeChatModel).defaultToolCallbacks(toolCallbackProvider).build();
        ChatClient.CallResponseSpec call = client.prompt(msg).call();
        return call.content();

    }


}
