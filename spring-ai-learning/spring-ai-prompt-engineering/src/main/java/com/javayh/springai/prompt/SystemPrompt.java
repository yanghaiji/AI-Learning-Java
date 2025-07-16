package com.javayh.springai.prompt;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "system_prompt")
public class SystemPrompt {

    @Id
    private String id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String prompt;

    @Column(updatable = false)
    private LocalDateTime updatedAt;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}