package com.javayh.springai.prompt;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemPromptRepository extends JpaRepository<SystemPrompt, String> {

    Optional<SystemPrompt> findById(String id);

}