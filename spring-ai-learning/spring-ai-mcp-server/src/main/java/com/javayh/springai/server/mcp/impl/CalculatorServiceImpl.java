package com.javayh.springai.server.mcp.impl;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;
import com.javayh.springai.server.mcp.CalculatorService;

/**
 * <p>
 *
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2025-07-07
 */
@Service
public class CalculatorServiceImpl implements CalculatorService {

    /**
     * 重写add方法，用于计算两个整数的和
     *
     * @param a 第一个加数
     * @param b 第二个加数
     * @return 两个整数的和
     */
    @Tool(description = "计算两个整数的和")
    @Override
    public int add(int a, int b) {
        return a + b;
    }

}
