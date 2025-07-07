package com.javayh.springai.server.config;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.javayh.springai.server.mcp.CalculatorService;

/**
 * <p>
 * mcp server 配置类
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2025-07-07
 */
@Configuration
public class McpProviderConfig {


    /**
     * 配置并创建一个ToolCallbackProvider实例，用于处理工具对象的回调
     * 此处特别用于计算器服务，通过将其实例传递给MethodToolCallbackProvider的构建器，
     * 我们能够定义当特定方法被调用时应该如何处理回调
     *
     * @param calculatorService 计算器服务实例，作为工具对象提供回调功能
     * @return ToolCallbackProvider的实例，用于提供工具对象的回调机制
     */
    @Bean
    public ToolCallbackProvider toolCallbackProvider(CalculatorService calculatorService) {
        return MethodToolCallbackProvider.builder().toolObjects(calculatorService).build();
    }
}
