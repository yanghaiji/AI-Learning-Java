package com.javayh.springai.prompt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;

/**
 * <p>
 *
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2025-07-16
 */
@RestController
@RequestMapping("/api")
public class ChatPrompt {

    @Autowired
    private DashScopeChatModel chatModel;

    @Autowired
    private SystemPromptService systemPromptService;

    @GetMapping("/ask")
    public String chatWithRag(@RequestParam(value = "input") String input, @RequestParam(value = "id")String id) {

        return ChatClient.builder(chatModel).defaultAdvisors().build()
            .prompt()
            .advisors(new SimpleLoggerAdvisor())
            .system(systemPromptService.getPromptById(id).get().getPrompt())
            .user(input)
            .call()
            .content();
    }

}
