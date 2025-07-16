package com.javayh.springai.prompt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SystemPromptService {

    @Autowired
    private SystemPromptRepository repository;

    public Optional<SystemPrompt> getPromptById(String id) {
        return repository.findById(id);
    }

    public SystemPrompt createPrompt(SystemPrompt prompt) {
        return repository.save(prompt);
    }

    public SystemPrompt updatePrompt(SystemPrompt prompt) {
        return repository.save(prompt);
    }

    public void deletePrompt(String id) {
        repository.deleteById(id);
    }
}