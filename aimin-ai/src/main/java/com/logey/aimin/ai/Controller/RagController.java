package com.logey.aimin.ai.Controller;

import com.logey.aimin.ai.service.FileUploadService;
import com.logey.aimin.ai.service.impl.FileUploadServiceImpl;
import com.logey.aimin.base.response.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/rag")
@RequiredArgsConstructor
public class RagController {

    private final FileUploadService fileUploadService;
    private final VectorStore vectorStore;

    @PostMapping("/upload")
    public Result<?> upload(@RequestParam("file") MultipartFile file) {
        fileUploadService.upload(file);
        return Result.success();
    }

    @GetMapping("/search")
    public Result<?> search( String question) {

        SearchRequest searchRequest = SearchRequest.builder()
                .query(question)
                .similarityThreshold(0.1)
                .topK(3)
                .build();

        List<Document> documents = vectorStore.similaritySearch(searchRequest);

        assert documents != null;
        System.out.println("搜索到文档数量：" + documents.size());
        for (Document document : documents) {
            System.out.println(document.getText());
        }

        return Result.success();
    }
}
