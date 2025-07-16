package com.javayh.springai.prompt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SystemPromptController {

    @Autowired
    private SystemPromptService service;



    // 按 ID 获取
    @GetMapping("/{id}")
    public Optional<SystemPrompt> getById(@PathVariable String id) {
        return service.getPromptById(id);
    }

    // 创建
    @PostMapping
    public SystemPrompt create(@RequestBody SystemPrompt prompt) {
        return service.createPrompt(prompt);
    }

    // 更新
    @PutMapping
    public SystemPrompt update(@RequestBody SystemPrompt prompt) {
        return service.updatePrompt(prompt);
    }

    // 删除
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.deletePrompt(id);
    }
}