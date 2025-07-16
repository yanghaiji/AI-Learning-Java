package com.javayh.springai.prompt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * <p>
 *
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2025-07-15
 */
@SpringBootApplication
@EnableJpaAuditing
public class PromptApp {

    public static void main(String[] args) {
        SpringApplication.run(PromptApp.class, args);
    }
}
