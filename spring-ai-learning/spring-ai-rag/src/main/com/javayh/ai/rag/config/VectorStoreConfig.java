package com.javayh.ai.rag.config;

import java.io.IOException;
import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import com.alibaba.cloud.ai.dashscope.embedding.DashScopeEmbeddingModel;

/**
 * <p>
 *
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2025-07-08
 */
@Component
public class VectorStoreConfig {


    @Bean
    public TokenTextSplitter tokenTextSplitter() {
        return new TokenTextSplitter();
    }

    // 加载本地PDF文件到向量存储
    @Bean
    public SimpleVectorStore vectorStore(
        @Value("classpath:/knowledge.pdf") Resource pdfResource,
        TokenTextSplitter tokenTextSplitter, DashScopeEmbeddingModel embeddingModel) throws IOException {

        // 配置PDF阅读器
        PdfDocumentReaderConfig config = PdfDocumentReaderConfig.builder()
            .withPageExtractedTextFormatter(ExtractedTextFormatter.builder()
                .withNumberOfTopTextLinesToDelete(3)
                .build())
            .build();

        // 读取PDF文档
        PagePdfDocumentReader pdfReader = new PagePdfDocumentReader(pdfResource, config);
        List<Document> documents = pdfReader.get();

        // 分割文档
        List<Document> splitDocs = tokenTextSplitter.apply(documents);

        // 创建内存向量存储
        SimpleVectorStore vectorStore = SimpleVectorStore.builder(embeddingModel).build();
        vectorStore.add(splitDocs);
        return vectorStore;
    }


}
