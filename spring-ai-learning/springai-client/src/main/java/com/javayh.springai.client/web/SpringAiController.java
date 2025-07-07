package com.javayh.springai.client.web;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/gen")
    public String gen(@RequestParam(value = "msg") String msg) {
        ChatClient client = ChatClient.builder(openAiChatModel).defaultToolCallbacks(toolCallbackProvider).build();
        ChatClient.CallResponseSpec call = client.prompt(msg).call();
        return call.content();
    }
}
