package com.javayh.ai.rag.web;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.generation.augmentation.ContextualQueryAugmenter;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;

@RestController
@RequestMapping("/api/rag")
public class RagController {


    @Autowired
    private DashScopeChatModel chatModel;


    @Autowired
    private VectorStore vectorStore;

    @GetMapping("/ask")
    public String chatWithRag(@RequestParam(value = "input") String input) {
        Advisor retrievalAugmentationAdvisor = RetrievalAugmentationAdvisor.builder()
            .documentRetriever(VectorStoreDocumentRetriever.builder()
                .similarityThreshold(0.5)
                .vectorStore(vectorStore)
                .build())
            .queryAugmenter(ContextualQueryAugmenter.builder()
                .allowEmptyContext(true)
                .build())
            .build();
        return ChatClient.builder(chatModel).defaultAdvisors().build()
            .prompt()
            .advisors(new SimpleLoggerAdvisor())
            .advisors(retrievalAugmentationAdvisor)
            .user(input)
            .call()
            .content();
    }


}