package com.javayh.springai.server.mcp;

/**
 * <p>
 * 提供统一的计算接口
 * 后续可以扩展为不同的计算服务
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2025-07-07
 */
public interface CalculatorService {

    /**
     * 计算两数之和
     *
     * @param a 第一个加数
     * @param b 第二个加数
     * @return 两个整数的和
     */
    int add(int a, int b);
}
