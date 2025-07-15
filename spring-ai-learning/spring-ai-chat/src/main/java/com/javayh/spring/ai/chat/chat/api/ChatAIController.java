package com.javayh.spring.ai.chat.chat.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.javayh.spring.ai.chat.chat.ChatRequestDto;
import com.javayh.spring.ai.chat.chat.service.ChatAIService;

/**
 * <p>
 *
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2025-07-15
 */
@RestController
@RequestMapping(value = "/chat")
public class ChatAIController {

    @Autowired
    private ChatAIService chatAIService;

    @PostMapping(value = "/ask")
    public String ask(@RequestBody ChatRequestDto chatRequestDto) {
        return chatAIService.ask(chatRequestDto);
    }

}
