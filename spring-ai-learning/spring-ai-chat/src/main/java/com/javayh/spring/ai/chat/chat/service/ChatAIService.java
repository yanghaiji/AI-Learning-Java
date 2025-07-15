package com.javayh.spring.ai.chat.chat.service;


import com.javayh.spring.ai.chat.chat.ChatRequestDto;

/**
 * <p>
 * 聊天接口
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2025-07-15
 */
public interface ChatAIService {

    /**
     * 提问
     *
     * @param chatRequestDto 问题
     * @return 回答
     */
    String ask(ChatRequestDto chatRequestDto);
}
