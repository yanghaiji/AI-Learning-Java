package com.javayh.spring.ai.chat.chat.service.impl;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
@Service
public class ChatAIServiceImpl implements ChatAIService {


    @Autowired
    private ChatClient dashScopeChatClient;

    @Autowired
    private ChatMemory chatMemory;

    /**
     * 提问
     *
     * @param chatRequestDto 问题
     * @return 回答
     */
    @Override
    public String ask(ChatRequestDto chatRequestDto) {
        String conversationId = chatRequestDto.getConversationId();
        chatMemory.add(conversationId,new UserMessage(chatRequestDto.getQuestion()));
        List<Message> messages = chatMemory.get(conversationId);
        ChatClient.CallResponseSpec call = dashScopeChatClient.prompt().messages(messages).call();
        return call.content();
    }
}
